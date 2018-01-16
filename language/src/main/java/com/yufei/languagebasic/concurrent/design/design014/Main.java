package com.yufei.languagebasic.concurrent.design.design014;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        FutureClient fc = new FutureClient();
        Future future = fc.request("图谱名称");
        System.out.println("请求发送成功!");
        System.out.println("去查询并处理图谱数据了...");
        System.out.println("主线程开始解析图书数据...");
        Thread.sleep(6000);
        System.out.println("主线程解析完了图书数据...");
        String result = future.get();
        System.out.println("主线程从异步线程拿回了图谱数据：" + result);
        System.out.println("主线程开始综合处理");
    }
}
