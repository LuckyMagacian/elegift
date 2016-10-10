package com.lanxi.elegift.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.lanxi.elegift.bean.in.EleGiftException;
/**
 * 读取配置文件
 * @author 1
 *
 */
@Component
public class ConfUtil implements Serializable {
	private static Properties conf;//配置文件
	private static Logger logger=Logger.getLogger(ConfUtil.class);
	/**
	 * 私有化构造方法 避免被实例化
	 */
	private ConfUtil(){};
	/**
	 * 配置文件初始化
	 * @throws EleGiftException 
	 */
	public static void init() throws EleGiftException{
		try {
			conf=new Properties();
			//使用classloade.getSystemLoader来获取流会在tomcat上报nullPointer
			conf.load(ConfUtil.class.getClassLoader().getResourceAsStream("properties/elegift.properties"));
			conf.load(ConfUtil.class.getClassLoader().getResourceAsStream("properties/smstemplet.properties"));
			logger.info("加载配置文件");
		} catch (IOException e) {
			throw new EleGiftException("文件io错误",e);
		}
	}
	/**
	 * 获取指定参数
	 * @param name
	 * @return
	 */
	public static String get(String name){
		if(conf==null)
			try {
				init();
			} catch (EleGiftException e) {
				new EleGiftException("初始化配置文件失败",e);
			}
		return conf.getProperty(name);
	}
	//获取url
	public static String getUrl(){
		return get("url");
	}
	//获取密钥
	public static String getKey(String keyName){
		return get(keyName);
	}
	public static String getKey(){
		return get("key");
	}
	//获取回调url
	public static String getBackUrl(){
		return get("backUrl");
	}
	//获取短信发送链接
	public static String getSmsUrl(){
		return get("smsUrl");
	}
	public static String getSmsKey(){
		return get("smsKey");
	}
	
}
