/**
 * 
 */
package com.http.testhttprpc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Description: �����ṩ�� 
 * @Author chenkangxian   
 * @Date 2013-6-24 ����4:15:30 
 * @Copyright: 2012 chenkangxian, All rights reserved.
 **/
public class ServiceProvider  extends HttpServlet{
	
	private Map<String,BaseService> serviceMap ;
	

	@Override
	public void init() throws ServletException {
		//����map��ʼ��
		serviceMap = new HashMap<String,BaseService>();
		serviceMap.put("com.http.sayhello", new SayHelloService());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//��������
		String servicename = req.getParameter("service");
		String format = req.getParameter("format");
		
		Map parameters =  req.getParameterMap();
		
		BaseService service = serviceMap.get(servicename);
		Object result = service.execute(parameters);
		
		//����json�����
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(result);
		jsonResult.setMessage("success");
		jsonResult.setResultCode(200);
		
		String json = JsonUtil.getJson(jsonResult);
		resp.getWriter().write(json);
	}
}
