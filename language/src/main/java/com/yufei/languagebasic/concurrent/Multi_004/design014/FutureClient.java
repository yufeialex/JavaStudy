package com.yufei.languagebasic.concurrent.Multi_004.design014;

public class FutureClient {

	public Future request(final String queryStr){
		//1 我想要一个代理对象（Data接口的实现类）先返回给发送请求的客户端，告诉他请求已经接收到，可以做其他的事情
		final ProxyFuture futureData = new ProxyFuture();
		//2 启动一个新的线程，去加载真实的数据，传递给这个代理对象
		new Thread(() -> {
            //3 这个新的线程可以去慢慢的加载真实对象，然后传递给代理对象
            RealFuture realData = new RealFuture(queryStr);
            futureData.setRealData(realData);
        }).start();
		
		return futureData;
	}
	
}
