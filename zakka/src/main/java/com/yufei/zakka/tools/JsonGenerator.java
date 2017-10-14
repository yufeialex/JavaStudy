package com.yufei.zakka.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class JsonGenerator {

    public static String getRandomString(int length) { //length表示生成字符串的长度  
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        File node = new File("C://node.txt");
        File link = new File("C://link.txt");
        node.createNewFile();// 不存在则创建  
        link.createNewFile();// 不存在则创建  
        BufferedWriter output = new BufferedWriter(new FileWriter(node));
        BufferedWriter output1 = new BufferedWriter(new FileWriter(link));
        for (int i = 0; i < 1000; i++) {

            int length = random.nextInt(10);
            int group = random.nextInt(8) + 1;
            String a = getRandomString(length);
            String b = getRandomString(length);
            String c = getRandomString(length);
            String d = getRandomString(length);
            String e = getRandomString(length);

            String tom = "{\"id\": \"" + a + "\", \"group\": " + group + "}," + "\n";
            String tom1 = "{\"id\": \"" + b + "\", \"group\": " + group + "}," + "\n";
            String tom2 = "{\"id\": \"" + c + "\", \"group\": " + group + "}," + "\n";
            String tom3 = "{\"id\": \"" + d + "\", \"group\": " + group + "}," + "\n";
            String tom4 = "{\"id\": \"" + e + "\", \"group\": " + group + "}," + "\n";

            output.write(tom);
            output.write(tom1);
            output.write(tom2);
            output.write(tom3);
            output.write(tom4);

            String linkString = "{\"source\": \"" + a + "\", \"target\": \"" + b + "\", \"value\": 2}," + "\n";
            String linkString1 = "{\"source\": \"" + a + "\", \"target\": \"" + c + "\", \"value\": 2}," + "\n";
            String linkString2 = "{\"source\": \"" + a + "\", \"target\": \"" + d + "\", \"value\": 2}," + "\n";
            String linkString3 = "{\"source\": \"" + a + "\", \"target\": \"" + e + "\", \"value\": 2}," + "\n";
            String linkString111 = "{\"source\": \"" + b + "\", \"target\": \"" + a + "\", \"value\": 2}," + "\n";
            String linkString11 = "{\"source\": \"" + b + "\", \"target\": \"" + c + "\", \"value\": 2}," + "\n";
            String linkString21 = "{\"source\": \"" + b + "\", \"target\": \"" + d + "\", \"value\": 2}," + "\n";
            String linkString31 = "{\"source\": \"" + b + "\", \"target\": \"" + e + "\", \"value\": 2}," + "\n";
            output1.write(linkString);
            output1.write(linkString1);
            output1.write(linkString2);
            output1.write(linkString3);
            output1.write(linkString11);
            output1.write(linkString111);
            output1.write(linkString21);
            output1.write(linkString31);
        }

        output.close();
        output1.close();
    }
}
