package com.petrichor.javastudy.call.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonCaller {
    public static void main(String[] args) throws IOException, InterruptedException {
        test2();
    }

    public static void test1() {
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("python " + "C:\\Users\\xinyufei\\code\\github_download\\JavaStudy\\call\\src\\main\\java\\com\\petrichor\\javastudy\\call\\python\\Test.py");
//            proc.waitFor();
            System.out.println(proc.waitFor());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test2() throws IOException, InterruptedException {
        String exe = "python";
        String command = "C:\\Users\\xinyufei\\code\\python\\all_python\\qiyi\\Test.py";
        String num1 = "1";
        String num2 = "2";
        String[] cmdArr = new String[]{exe, command, num1, num2};
        Process process = Runtime.getRuntime().exec(cmdArr);

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
