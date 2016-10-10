package com.lanxi.elegift.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 测试servlet
 * 用于测试请求是否接收等
 * @author 1
 *
 */
public class TestServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String method=req.getMethod();
		System.out.println(method);
		String url=req.getRequestURL().toString();
		System.out.println(url);
	}
	
}
