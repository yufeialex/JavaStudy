package com.petrichor.java.language;

import java.io.*;

/**
 * 使用字节流类读取二进制文件
 *
 * @author xinyufei
 * @date 2019/12/21
 */
public class fileProcess {

    public static void main(String[] args) {

// 1、创建一个文件对象
        File file = new File("c:\\01.jpg");

// 2、使用字节流对象读入内存

        try {
            InputStream fileIn = new FileInputStream(file);
//DataInputStream in = new DataInputStream(fileIn);

// 使用缓存区读入对象效率更快
            BufferedInputStream in = new BufferedInputStream(fileIn);


            FileOutputStream fileOut = new FileOutputStream("D:\\3.jpg");
            DataOutputStream dataOut = new DataOutputStream(fileOut);

// 使用缓存区写入对象效率更快
//BufferedOutputStream dataOut=new BufferedOutputStream(fileOut);
            int temp;
            while ((temp = in.read()) != -1) {
                dataOut.write(temp);
            }

        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
