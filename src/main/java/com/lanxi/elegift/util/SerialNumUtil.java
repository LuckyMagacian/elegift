package com.lanxi.elegift.util;

import java.io.Serializable;
import java.util.Random;
/**
 * 序列号工具
 * @author 1
 *
 */
public class SerialNumUtil implements Serializable {
	/**
	 * 私有化构造方法 避免被实例化
	 */
	private  SerialNumUtil() {}
	/**
	 * 获取指定位数的随机数字
	 * @param count
	 * @return
	 */
	@SuppressWarnings("不保证不会生成相同的随机字符串")
	public static String getRandom(int count){
		StringBuilder rs=new StringBuilder();
		Random random=new Random();
		while(count-->0)
			rs.append(random.nextInt(9));
		return rs.toString();
	}
}
