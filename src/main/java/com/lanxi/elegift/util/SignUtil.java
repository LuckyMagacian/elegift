package com.lanxi.elegift.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.in.ReqBean;
import com.lanxi.elegift.bean.in.SmsBean;
import com.lanxi.elegift.bean.out.BaoWen;
import com.lanxi.elegift.bean.out.headbean.Head;
import com.lanxi.elegift.bean.out.headbean.ReqHead;
import com.lanxi.elegift.bean.out.headbean.ResHead;
import com.lanxi.elegift.bean.out.msgbean.Msg;
/**
 * 签名工具
 * @author 1
 *
 */
public class SignUtil implements Serializable {
	private static Logger logger=Logger.getLogger(SignUtil.class);
	/**
	 * 私有化构造方法 避免被实例化
	 */
	private SignUtil(){};
	/**
	 * 生成签名
	 * @param bean
	 * @return
	 * @throws EleGiftException 
	 */
	public static String getSign(ReqBean bean) throws EleGiftException{
		Map<String, String> map = null;
		try {
			map = BeanUtil.getParamMapNoSign(bean);
		} catch (EleGiftException e) {
			new EleGiftException("获取参数失败",e);
		}
		String sign;
		try {
			sign = md5LowerCase(ParseUtil.getParamStr(map)+ConfUtil.getKey(),"utf-8");
		} catch (EleGiftException e) {
			throw new EleGiftException("获取信息摘要失败",e);
		}
		bean.setSign(sign);
		return sign;
	}
	/**
	 * 从报文中提取除了签名以外的参数
	 * @param baowen 
	 * @return
	 * @throws EleGiftException 
	 */
	private static List<String> getParamsFromReqBaoWen(BaoWen baowen) throws EleGiftException{
		Head head=baowen.getHead();
		Msg  msg =baowen.getMsg();
		Field[] headFields=head.getClass().getDeclaredFields();
		Field[] msgFields = null;
		if(msg!=null)
			msgFields =msg.getClass().getDeclaredFields();
		
		List<String> params=new ArrayList<String>();
		try {
			for(Field each:headFields){
				each.setAccessible(true);
				if(each.getName().equals("Sign"))
					continue;
					params.add(each.get(head).toString());
			}
			if(msgFields!=null)
			for(Field each:msgFields){
				each.setAccessible(true);
				params.add(each.get(msg).toString());
			}
			} catch (IllegalArgumentException e) {
				throw new EleGiftException("参数不合法",e);
			} catch (IllegalAccessException e) {
				throw new EleGiftException("权限不足",e);
			}
		return params;
	}
	/**
	 * 从报文中提取除了签名以外的参数并进行计算签名
	 * 若输入报文是响应报文还会直接设置签名值
	 * @throws EleGiftException 
	 */
	public static String getSignNoSort(BaoWen baowen) throws EleGiftException{
		List<String> params = null;
		String sign=null;
		try {
			params = getParamsFromReqBaoWen(baowen);
		} catch (EleGiftException e) {
			throw new EleGiftException("获取参数失败",e);
		}
		StringBuilder temp=new StringBuilder();
		for(String each:params)
			temp.append(each);
		if(baowen.getHead() instanceof ReqHead)
			sign=md5LowerCase(temp.toString()+ConfUtil.getKey(((ReqHead)baowen.getHead()).getSRC()),"GBK");
		if(baowen.getHead() instanceof ResHead)
			sign=md5LowerCase(temp.toString()+ConfUtil.getKey(((ResHead)baowen.getHead()).getSRC()),"GBK");
		if(baowen.getHead() instanceof ResHead)
			((ResHead)baowen.getHead()).setSign(sign);
		return sign;
	}
	/**
	 * md5生成签名
	 * @param src
	 * @return
	 * @throws EleGiftException 
	 */
	public static String md5LowerCase(String src,String charset) throws EleGiftException{
		
		try {
			MessageDigest md;
				md = MessageDigest.getInstance("MD5");
			md.update(src.getBytes(charset));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();// 32位的加密
		} catch (NoSuchAlgorithmException e) {
			throw new EleGiftException("算法不存在",e);
		} catch (UnsupportedEncodingException e) {
			throw new EleGiftException("不支持的字符集",e);
		}
	}
	/**
	 * 签名验证
	 * @param baoWen
	 * @return
	 * @throws EleGiftException
	 */
	public static boolean checkSign(BaoWen baoWen) throws EleGiftException{
		ReqHead head=(ReqHead) baoWen.getHead();
		String sign=head.getSign();
		String signl;
		try {
			signl = getSignNoSort(baoWen);
		} catch (EleGiftException e) {
			throw new EleGiftException("报文签名失败",e);
		}
		logger.info("checkSign:报文中的签名"+sign+"生成的签名"+signl);
		return sign.equals(signl);
	}
	
	public static void signSms(SmsBean bean) throws EleGiftException{
		Field[] fields=bean.getClass().getDeclaredFields();
		Map<String, String> map=new HashMap<>();
		try{
			for(Field each:fields){
				each.setAccessible(true);
				if(!each.getName().equals("sign"))
					map.put(each.getName(), each.get(bean).toString());
				}		
		//  System.out.println(ParseUtil.getParamStr(map)+ConfUtil.getSmsKey());
		//	System.out.println("密钥:"+ConfUtil.getSmsKey());
			String sign=md5LowerCase(ParseUtil.getParamStr(map)+ConfUtil.getSmsKey(),"utf-8");
			bean.setSign(sign);
		} catch (IllegalArgumentException e) {
			throw new EleGiftException("短信签名失败,参数非法",e);
		} catch (IllegalAccessException e) {
			throw new EleGiftException("短信签名失败,权限不足,无法调用方法",e);
		}
	}
	

	
}
