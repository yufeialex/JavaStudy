package com.petrichor.java.zakka.picture;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

public class ImageUtil {
    /**
     * 源图片路径名称如:c:/1.jpg
     */
    private String srcpath = "J:\\BaiduNetdiskDownload\\WD-jiemi-shenshicy.fun\\WANIMAL\\[WANIMAL王动] 2016年5月VIP套图 官方出品 [79P 1.25G]\\4.jpg";

    public ImageUtil() {
    }

    public static void main(String[] args) throws Exception {
        ImageUtil util = new ImageUtil();
        util.getImageSizeByImageReader(util.getSrcpath());
        util.getImageSizeByBufferedImage(util.getSrcpath());
    }

    /**
     * 使用ImageReader获取图片尺寸
     *
     * @param src 源图片路径
     */
    public void getImageSizeByImageReader(String src) {
        long beginTime = new Date().getTime();
        File file = new File(src);
        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader) readers.next();
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            reader.setInput(iis, true);
            System.out.println("width:" + reader.getWidth(0));
            System.out.println("height:" + reader.getHeight(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = new Date().getTime();
        System.out.println("使用[ImageReader]获取图片尺寸耗时：[" + (endTime - beginTime) + "]ms");
    }

    /**
     * 使用BufferedImage获取图片尺寸
     *
     * @param src 源图片路径
     */
    public void getImageSizeByBufferedImage(String src) {
        long beginTime = new Date().getTime();
        File file = new File(src);
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        BufferedImage sourceImg = null;
        try {
            sourceImg = javax.imageio.ImageIO.read(is);
            System.out.println("width:" + sourceImg.getWidth());
            System.out.println("height:" + sourceImg.getHeight());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        long endTime = new Date().getTime();
        System.out.println("使用[BufferedImage]获取图片尺寸耗时：[" + (endTime - beginTime) + "]ms");
    }

    public String getSrcpath() {
        return srcpath;
    }

    public void setSrcpath(String srcpath) {
        this.srcpath = srcpath;
    }
}
