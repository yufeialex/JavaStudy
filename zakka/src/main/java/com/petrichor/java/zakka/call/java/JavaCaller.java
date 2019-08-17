package com.petrichor.java.zakka.call.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaCaller {

    public static void main(String[] args) throws IOException, InterruptedException {
        test2();
//        dd();
    }

    public static void dd() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("javac");
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("<ERROR>");
            while ((line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("</ERROR>");
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void test2() throws IOException, InterruptedException {
        String exe = "java -jar ";
        String command = "C:\\Users\\xinyufei\\code\\github_download\\JavaStudy\\call\\target\\call.jar ";
//        String command = "C:\\\\Users\\\\xinyufei\\\\code\\\\github_download\\\\JavaStudy\\\\call\\\\target\\\\call.jar ";
        String inputFile = "E:\\test\\sourceData.txt ";
        String outputFile = "E:\\test\\result.txt ";
//        String[] cmdArr = new String[] {exe, command, inputFile, outputFile};
        String[] cmdArr = new String[]{exe};
        Process process = Runtime.getRuntime().exec(exe + command + inputFile + outputFile);
//        Process process = Runtime.getRuntime().exec(cmdArr);

        BufferedReader error = new BufferedReader(new InputStreamReader(
                process.getErrorStream()));

        BufferedReader in = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();

        String line1;
        while ((line1 = error.readLine()) != null) {
            System.out.println(line1);
        }
        error.close();

        process.waitFor();
        System.out.println("end");
    }
}
