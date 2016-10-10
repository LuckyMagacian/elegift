package com.lanxi.elegift.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * 时间工具类
 * @author 1
 *
 */
public class TimeUtil implements Serializable {
	/**
	 * 私有化构造方法 避免被实例化
	 */
	private TimeUtil(){};
	/**
	 * 获取当前 日期 时间
	 * @return
	 */
	public static String getTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
		return sdf.format(new Date());
	}
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		return sdf.format(new Date());
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getSmallTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("HHmmss",Locale.CHINA);
		return sdf.format(new Date());
	}
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
}
