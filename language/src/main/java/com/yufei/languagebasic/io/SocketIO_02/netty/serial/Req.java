package com.yufei.languagebasic.io.SocketIO_02.netty.serial;

import java.io.Serializable;

/**
 * req和resp在服务器和客户端都必须有，而且路径一样
 */
public class Req implements Serializable{

	private static final long  SerialVersionUID = 1L;
	
	private String id ;
	private String name ;
	private String requestMessage ;
	private byte[] attachment;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	
	


}
