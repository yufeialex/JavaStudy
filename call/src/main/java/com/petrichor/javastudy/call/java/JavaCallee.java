package com.petrichor.javastudy.call.java;

import com.petrichor.javastudy.common.util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JavaCallee {
    public static void main(String[] args) throws IOException {
        String inFile = args[0];
        String outFile = args[1];

        String line; // 用来保存每行读取的内容

        // 用文件和文件的读写
        File is = new File(inFile);
        BufferedReader reader = new BufferedReader(new FileReader(is), 10 * 1024 * 1024);

        line = reader.readLine(); // 读取第一行
        StringBuffer buffer = new StringBuffer();
        while (line != null) { // 如果 line 为空说明读完了
            String substring = line.substring(line.indexOf(" ") + 1, line.length());
            buffer.append(substring); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        FileUtil.writeFile(buffer.toString(), outFile);
    }
}
