package com.petrichor.java.utillearn.acommons;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;

/**
 * @author xinyufei
 * @date 2019/12/11
 */
public class FileUtilDemo {
    public static void main(String[] args) throws IOException {
        String path = "D:\\1 life\\3 文档\\4 压缩包\\4.3万本电子书小说\\（";
        File targetDir = new File(path);

        if (targetDir != null && targetDir.exists() && targetDir.isDirectory()) {
            /*
             * targetDir：不要为 null、不要是文件、不要不存在
             * 第二个 文件过滤 参数如果为 FalseFileFilter.FALSE ，则不会查询任何文件
             * 第三个 目录过滤 参数如果为 FalseFileFilter.FALSE , 则只获取目标文件夹下的一级文件，而不会迭代获取子文件夹下的文件
             */
            Collection<File> fileCollection = FileUtils.listFiles(targetDir, TrueFileFilter.INSTANCE, FalseFileFilter.FALSE);
            for (File file : fileCollection) {
                System.out.println(">>> " + file.getName());
                String newName = file.getName().replaceAll("[（［(]", "[").replaceAll("[］）)]", "]");
                String newPath = file.getParent() + "\\" + newName;
                System.out.println(newPath);
                File newFile = new File(newPath);
                file.renameTo(newFile);


//                char c = file.getName().charAt(0);
//                File file1 = new File(path + "\\"+c);

//                FileUtils.forceMkdir(file1);

//                FileUtils.copyFileToDirectory(file, file1);//文件不重命

            }
        }
    }

}

class CopyFileOrDirectory {
    public static void main(String[] args) throws Exception {
        File file1 = new File("path1");
        File file2 = new File("path2");
        File file3 = new File("path3");
        File file4 = new File("path4");
        File file5 = new File("path5");

        //将文件复制到指定文件夹中,保存文件日期的时间。
        // 该方法将指定源文件的内容复制到指定目标目录中相同名称的文件中。
        // 如果不存在，则创建目标目录。如果目标文件存在，则该方法将覆盖它。
        FileUtils.copyFileToDirectory(file1, file2);//文件不重命

        //将文件复制到一个新的地方(重命名文件)并保存文件日期的时间。
        FileUtils.copyFile(file1, file3);

        //复制文件夹到指定目录下,如果指定目录不存在则创建
        FileUtils.copyDirectoryToDirectory(file2, file4);

        //复制文件夹到指定目录下并重命名
        FileUtils.copyDirectory(file4, file5);

        //该方法将指定的源目录结构复制到指定的目标目录中。
        FileUtils.copyDirectory(file4, file5, DirectoryFileFilter.DIRECTORY);

        // 复制文件夹下第一级内容中指定后缀文件
        IOFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".txt");
        IOFileFilter txtFiles = FileFilterUtils.and(FileFileFilter.FILE, txtSuffixFilter);
        FileUtils.copyDirectory(file4, file5, txtFiles);

        // 复制文件目录结构及文件夹下第一级目录内指定后缀文件
        FileFilter filter = FileFilterUtils.or(DirectoryFileFilter.DIRECTORY, txtFiles);
        FileUtils.copyDirectory(file4, file5, filter, false);//preserveFileDate参数默认为true。

        //将字节从URL源复制到文件目的地。如果它们还不存在，则将创建到目的地的目录。如果已经存在，文件将被覆盖。
//        URL source = new URL("http://imgsrc.baidu.com/baike/pic/ewe.jpg");
//        FileUtils.copyURLToFile(source,file5,1000,1000);

        // 等待NFS传播文件创建，并强制执行超时。该方法重复测试File.exists()，直到它返回true，或直到秒内指定的最大时间。
        File file = new File("/abc/");
        boolean d = FileUtils.waitFor(file, 100);
        System.out.println(d);
    }
}