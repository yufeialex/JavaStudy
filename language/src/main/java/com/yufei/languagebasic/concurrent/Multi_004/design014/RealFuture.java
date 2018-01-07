package com.yufei.languagebasic.concurrent.Multi_004.design014;

public class RealFuture implements Future {

	private String result ;
	
	public RealFuture(String queryStr){
		System.out.println("小弟根据" + queryStr + "进行查询，这是一个很耗时的操作..");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("小弟操作完毕，获取结果");
		result = "查询结果";
	}
	
	@Override
	public String get() {
		return result;
	}
}
