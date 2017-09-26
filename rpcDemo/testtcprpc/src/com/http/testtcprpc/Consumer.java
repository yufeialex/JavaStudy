/**
 * 
 */
package com.http.testtcprpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Description: ���������� 
 * @Author chenkangxian   
 * @Date 2013-6-20 ����8:18:56 
 * @Copyright: 2012 chenkangxian, All rights reserved.
 **/
public class Consumer {

	
	public static void main(String[] args) throws UnknownHostException, IOException, SecurityException, NoSuchMethodException, ClassNotFoundException{

		//�ӿ�����
		String interfacename= SayHelloService.class.getName();
		
		//��ҪԶ��ִ�еķ���
		Method method = SayHelloService.class.getMethod("sayHello", java.lang.String.class);

		//��Ҫ���ݵ�Զ�˵Ĳ���
		Object[] arguments = {"hello"};

		Socket socket = new Socket("127.0.0.1", 1234);

		//���������ƺͲ������ݵ�Զ��
		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
		output.writeUTF(interfacename); //�ӿ�����
		output.writeUTF(method.getName());  //��������
		output.writeObject(method.getParameterTypes());  
		output.writeObject(arguments);  

		//��Զ�˶�ȡ����ִ�н��
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());  
		Object result = input.readObject();
		
		//ʹ�ô������������ֱ�ӷ���string����
		
		System.out.println(result);
	}
}
