package com.petrichor.java.language.concurrent.util.cyclicBarrier3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by XinYufei on 2018/1/12.
 */

//            File file = new File("D:\\test\\" + i + ".txt");

//编写一个程序，在F盘下创建一个“Java学习”的文件夹，并在文件夹中一次性创建200个
//“视频教程001.txt”文件，文件名的序号从001—200，
//再编写一个工具类，将所有文件的名字修改为“JavaSE视频教程001.txt”序号保持不变。


public class CreateFile {
    public void rename(String filePath, String oldName, String newName) {
        File fa = new File(filePath);
        File[] allFiles = fa.listFiles();
        int count = 0;
        for (File ff : allFiles) {
            String oldFullName = ff.getName();
            String resultName = oldFullName.replace(oldName, newName);
            String newPath = fa.getAbsolutePath() + "\\" + resultName;
            File newPathFile = new File(newPath);
            boolean b = ff.renameTo(newPathFile);
            if (b) {
                System.out.println("改名成功");
                count++;
            } else {
                System.out.println("改名失败");
            }
        }

        System.out.println("一共有" + fa.length() + "个文件，修改了" + count + "份文件");
    }

    public static void main(String[] args) throws IOException {
        CreateFile fd = new CreateFile();
        fd.create();
//        fd.rename("F:\\java学习", "视频教程", "JavaSE视频教程");
    }

    private void create() throws IOException {
        DecimalFormat df = new DecimalFormat("000");
        File file = new File("D:\\java学习");
        boolean b = file.exists();
        if (b) {
            System.out.println("文件夹已创建");
        } else {
            file.mkdirs();
        }

        for (int i = 1; i <= 20000; i++) {
            String path = file.getAbsolutePath() + "\\" + i + ".txt";
            File f = new File(path);
//            File f = new File(file.getAbsolutePath() + "\\" + df.format(i) + ".txt");
            boolean c = f.createNewFile();
            if (c) {
                System.out.println("文件创建成功");
                BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                // 这里绝对不能直接给一个int，会有乱码
                writer.write(String.valueOf(i));
                writer.flush();
                writer.close();
            } else {
                System.out.println("文件创建失败");
            }

        }
    }
}