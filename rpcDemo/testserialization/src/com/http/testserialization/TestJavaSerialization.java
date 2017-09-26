/**
 * 
 */
package com.http.testserialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @Description: java���л� 
 * @Author chenkangxian   
 * @Date 2013-6-25 ����3:43:08 
 * @Copyright: 2012 chenkangxian, All rights reserved.
 **/
public class TestJavaSerialization {

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		
		Person zhansan = new Person();
		zhansan.setAddress("hangzhou");
		zhansan.setAge(30);
		zhansan.setBirth(new Date());
		zhansan.setName("zhansan");
		
		//����һ���ֽ����������
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		//���������
		ObjectOutputStream out = new ObjectOutputStream(os);
		//������д�뵽�ֽ�����������������л�
		out.writeObject(zhansan);
		byte[] zhansanByte = os.toByteArray();
		
		//�ֽ�����������
		ByteArrayInputStream is = new ByteArrayInputStream(zhansanByte);
		//ִ�з����л��������ж�ȡ����
		ObjectInputStream in = new ObjectInputStream(is);
		Person person = (Person)in.readObject();
		
		System.out.println("name : " + person.getName() + ", age : " + person.getAge());
	}
	
	
}
