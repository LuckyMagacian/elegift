package com.lanxi.elegift.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.in.MapType;
import com.lanxi.elegift.bean.in.ReqBean;
import com.lanxi.elegift.bean.in.SmsBean;
/**
 * bean工具类,封装了以bean作为参数的一些静态方法
 * @author 1
 *
 */
public class BeanUtil implements Serializable {
	private static Logger logger=Logger.getLogger(BeanUtil.class);
	/**
	 * 私有化构造方法,防止被实例化
	 */
	private BeanUtil(){};
	/**
	 * 从请求bean中获取参数 已map形式返回 包括签名
	 * @param bean
	 * @return
	 * @throws EleGiftException 
	 */
	public static Map<String,String> getParamMapNoSign(ReqBean bean) throws EleGiftException{
		return getParamMap(bean, MapType.STANDARD);
	}
	/**
	 * 从请求bean中获取参数 已map形式返回 不包括签名
	 * @param bean
	 * @return
	 * @throws EleGiftException 
	 */
	public static Map<String,String> getParamMapWithSign(ReqBean bean) throws EleGiftException{
		return getParamMap(bean, MapType.SIGN);
	}
	/**
	 * 从请求bean中获取参数 已map形式返回 由type决定是否要返回签数
	 * @param bean
	 * @return
	 * @throws EleGiftException 
	 */
	public static Map<String,String> getParamMap(ReqBean bean,MapType type) throws EleGiftException{
		Map<String, String> rs=new HashMap<String, String>();
		List<Field> fields=Arrays.asList(bean.getClass().getDeclaredFields());
		
		try {
			for(Field each:fields){
				each.setAccessible(true);
				if(type!=null&&type.equals(MapType.STANDARD))
					if(each.getName().equals("sign"))
						continue;
				String key=each.getName();
				String value;
					value = (String) each.get(bean);
				rs.put(key, value);
			}
		} catch (IllegalArgumentException e) {
			throw new EleGiftException("方法调用参数不合法",e);
		} catch (IllegalAccessException e) {
			throw new EleGiftException("权限不足",e);
		}
	
		return rs;
	}
	

	/**
	 * 计算订单流水号
	 * @param bean
	 * @return
	 */
	public static String getId(ReqBean bean){
		return bean.getMchtId()+bean.getTradeDate()+bean.getTradeTime()+SerialNumUtil.getRandom(4);
	}
	public static Map<String, String> getObjectParams(Object obj) throws EleGiftException{
		Map<String, String> params=new HashMap<String, String>();
		Field[] fields=obj.getClass().getDeclaredFields();
		try {
			for(Field each:fields){
				each.setAccessible(true);
					params.put(each.getName(), each.get(obj).toString());
			}
			return params;
		} catch (IllegalArgumentException e) {
			throw new EleGiftException("获取参数失败,参数非法",e);
		} catch (IllegalAccessException e) {
			throw new EleGiftException("获取参数失败权限不足",e);
		}
	}
	/**
	 * 发送短信
	 * @param bean
	 * @return
	 */
	public static String sendSms(SmsBean bean){
		try {
			String rs=HttpUtil.postSms(bean);
			JSONObject obj=JSONObject.parseObject(rs);
			if(obj.get("retCode").equals("0000"))
				logger.info("短信发送成功");
			else
				logger.error("短信发送失败");
			return rs;
		} catch (EleGiftException e) {
			new EleGiftException("短信发送失败",e);
		}
		return null;
	}
}
