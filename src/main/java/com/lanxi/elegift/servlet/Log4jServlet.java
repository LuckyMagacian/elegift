package com.lanxi.elegift.servlet;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * log4初始化Servlet
 * @author 1
 *
 */
public class Log4jServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.err.println("log4j init----");
		//从ServlcetConfig中获取 配置文件相对路径 参数
		String location=config.getInitParameter("location");
		//判断参数是否存在
		if(location==null||location.trim().equals("")){
			System.err.println("no init file----");
			//调用默认配置
			BasicConfigurator.configure(); 
			return;
		}
		ServletContext context=config.getServletContext();
		//获取项目路径
		String webappPath=context.getRealPath("/");
		//获取配置文件绝对路径
		String logConf=webappPath+location;
		//读取文件
		File   confFile=new File(logConf);
		//判断文件是否存在
		if(confFile.exists()||confFile.isDirectory()){
			//获取初始化参数 中webapproot
			String webroot=context.getInitParameter("webAppRoot");
			//替换webapproot为项目路径
			System.setProperty(webroot,webappPath);
			System.err.println("init log4j by file "+logConf);
			//使用配置文件初始化log4j
			PropertyConfigurator.configure(logConf);
		}else{
			//使用默认配置
			System.err.println("config file not exists");
			BasicConfigurator.configure(); 
		}
		//记录日志
		Logger logger=Logger.getLogger(Log4jServlet.class);
		logger.info("log4j init finished !");
		//继续servlet初始化
		super.init(config);
	}	
}
