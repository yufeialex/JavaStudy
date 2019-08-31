package com.petrichor.java.zakka.picture;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.*;

public class PicClassify {
    private static final Logger logger = LoggerFactory.getLogger(PicClassify.class);

    private static final HashSet<String> picTypes = new HashSet<>(Arrays.asList("jpg", "bmp", "jpeg", "png", "gif", "JPG", "BMP", "JPEG", "PNG", "GIF"));

    //        分为计算类型，IO类型 线程MAX数量计算类型推荐N+1 I/O类型 推荐使用cpu数量*2
//    private static final ExecutorService pool = Executors.newFixedThreadPool(1024);
    //        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    /**
     * 手动创建线程池
     */
    // 创建线程工厂
    private static final ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("xxx-pool-%d")
            .build();
    // 创建通用线程池
    /**
     * 参数含义：
     * corePoolSize : 线程池中常驻的线程数量。核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受存keepAliveTime限制。除非将allowCoreThreadTimeOut设置为true。
     * maximumPoolSize : 线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
     * keepAliveTime : 当线程数量多于 corePoolSize 时，空闲线程的存活时长，超过这个时间就会被回收
     * unit : keepAliveTime 的时间单位
     * workQueue : 存放待处理任务的队列，该队列只接收 Runnable 接口
     * threadFactory : 线程创建工厂
     * handler : 当线程池中的资源已经全部耗尽，添加新线程被拒绝时，会调用RejectedExecutionHandler的rejectedExecution方法，参考 ThreadPoolExecutor 类中的内部策略类
     */
    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(1024, 2048, 0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(4096),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws Exception {
        File dir = new File("J:\\sesede\\TAS22");
        long start = System.currentTimeMillis();

        checkFile(dir);

        pool.shutdown();
        while (!pool.isTerminated()) {
            // pass
        }
        logger.info("total cost:{}", System.currentTimeMillis() - start);
    }

    // 输入必须是一个文件夹
    private static void checkFile(File dir) throws Exception {
        File[] files = dir.listFiles();   //列出所有的子文件
        // 空文件夹不处理
        if (files == null) {
            return;
        }

        for (File file : files) {
            // 这个文件夹不能再处理，否则会永远循环
            if (file.getName().equals("vertical")) {
                continue;
            }
            //如果是文件，则提交线程池处理
            if (file.isFile()) {
                // 文件处理
                while (true) {
                    try {
                        pool.submit(() -> {
                            try {
                                fileProcess(file);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        break;
                    } catch (RejectedExecutionException exception) {
                        logger.info("线程池已满，有堆积情况！");
                        Thread.sleep(3000);
                    }
                }
            } else if (file.isDirectory()) { //如果是文件夹，则输出文件夹的名字，并递归遍历该文件夹
                logger.info("folder: {}", file.getName());
                checkFile(file);//递归遍历
            }
        }
    }

    private static void fileProcess(File picture) throws Exception {
        long start = System.currentTimeMillis();
        int i = picture.getName().lastIndexOf('.');
        // 没有后缀名不处理
        if (i <= 0) {
            return;
        }

        String extension = picture.getName().substring(i + 1);
        // 非图片不处理
        if (!picTypes.contains(extension)) {
            return;
        }
        long t2 = System.currentTimeMillis();

        FileInputStream picInputStream = new FileInputStream(picture);

        // 方法1：使用BufferedImage
//        BufferedImage sourceImg = ImageIO.read(picInputStream);
//        logger.info("{} 宽： {}, 高：{}", picture.getName(), sourceImg.getWidth(), sourceImg.getHeight());


        // 方法2：使用JpegInfoReader
        /*ImageInfo sourceImg = JpegInfoReader.getImageInfo(picInputStream);
        // 非jpeg不处理
        if (sourceImg == null) {
            return;
        }
        logger.info("{} 宽： {}, 高：{}", picture.getName(), sourceImg.getWidth(), sourceImg.getHeight());
        boolean isVertical = sourceImg.getWidth() < sourceImg.getHeight();*/


        // 方法3：使用第三方包
        /*Metadata metadata = ImageMetadataReader.readMetadata(picInputStream);
        Directory directory = null;
        switch (extension) {
            case ".PNG":
            case ".png":
                directory = metadata.getFirstDirectoryOfType(PngDirectory.class);
                break;
            default:
                directory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
        }
        long height = directory.getLong(JpegDirectory.TAG_IMAGE_HEIGHT);
        long weight = directory.getLong(JpegDirectory.TAG_IMAGE_WIDTH);
        logger.info("{} 宽： {}, 高：{}", picture.getName(), weight, height);
        boolean isVertical = weight < height;*/

        /*for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                //格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
                System.out.format("[%s] - %s = %s\n",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }*/

        // 方法4： imageReader
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(extension);
        ImageReader reader = readers.next();
        ImageInputStream iis = ImageIO.createImageInputStream(picture);
        reader.setInput(iis, true);
        logger.info("{} 宽： {}, 高：{}", picture.getName(), reader.getWidth(0), reader.getHeight(0));
        boolean isVertical = reader.getWidth(0) < reader.getHeight(0);
        iis.close();

        picInputStream.close();
        if (!isVertical) {
            return;
        }

        long t3 = System.currentTimeMillis();

        String verticalPath = picture.getParent() + "\\vertical";
        File vertical = new File(verticalPath);
        // 如果没有这个文件夹就创建
        if (!vertical.exists()) {
            vertical.mkdir();
        }

        long t4 = System.currentTimeMillis();
//                moveFileWithRename(file.getAbsolutePath(), verticalPath + "\\" + file.getName());
        moveFileWithCmd(picture.getAbsolutePath(), verticalPath + "\\" + picture.getName());
        long end = System.currentTimeMillis();
//        logger.info("total time:{}, basic check:{}, size check:{}, folder check:{}, move action:{}", end - start, t2 - start, t3 - t2, t4 - t3, end - t4);
    }

    private static void moveFileWithBinary(String from, String to) {
        try {
            File fromFile = new File(from);
            File toFile = new File(to);//定义移动后的文件路径
            toFile.createNewFile();//新建文件

            FileInputStream fromStream = new FileInputStream(fromFile);
            FileOutputStream toStream = new FileOutputStream(toFile);
            byte[] date = new byte[512];//定义byte数组
            while (fromStream.read(date) > 0) {//判断是否读到文件末尾
                toStream.write(date);//写数据
            }
            fromStream.close();//关闭流
            toStream.close();//关闭流

            boolean delete = fromFile.delete();//删除原文件
            logger.info("文件移动成功, 删除结果：{}", delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moveFileWithRename(String from, String to) {
        File startFile = new File(from);
        File destFile = new File(to);

        logger.info("startFile: {}", startFile);
        logger.info("destFile: {}", destFile);
        if (startFile.renameTo(destFile)) {
            logger.info("文件移动成功！");
        } else {
            logger.info("文件移动失败！");
        }
    }

    public static void moveFileWithCmd(String from, String to) {
        Runtime run = Runtime.getRuntime();

        try {
            // 文件名中有空格会导致误判，所以需要加双引号
            String cm = "cmd.exe /c move \"" + from + "\" \"" + to + "\"";
//            logger.info(cm);
            Process process = run.exec(cm);
            int exitVal = process.waitFor();
//            logger.info("Process exitValue: {} ", exitVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
