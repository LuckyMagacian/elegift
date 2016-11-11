package com.lanxi.elegift.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class TestController implements Serializable {
	private static Logger logger=Logger.getLogger(TestController.class);
	public void in(HttpServletRequest req,HttpServletResponse res){
		try {
			req.setCharacterEncoding("GBK");
			res.setCharacterEncoding("GBK");
			String path=req.getRequestURL().toString();
			System.out.println(path);
		} catch (UnsupportedEncodingException e) {
			logger.error("不支持的字符集");
			e.printStackTrace();
		}
	}
}
