package com.lanxi.elegift.util;

import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.in.ReqBean;
/**
 * 测试工具类
 * @author 1
 *
 */
public class TestUtil {
	/**
	 * 私有化构造方法 避免被实例化
	 */
	private TestUtil(){};
	/**
	 * 构造一个测试用 请求bean
	 * @return
	 */
	public static ReqBean getTestReqBean(){
		return getTestReqBean("1001");
	}
	/**
	 * 构造测试用 请求bean
	 * @param oper 商家平台 代码
	 * @return
	 */
	public static ReqBean getTestReqBean(String oper){
		ReqBean bean=new ReqBean();
		bean.setMchtId("40");
		bean.setOrderId(bean.getMchtId()+TimeUtil.getTime()+"0000");
		bean.setMobile("15068610940");
		bean.setTradeDate(TimeUtil.getDate());
		bean.setTradeTime(TimeUtil.getSmallTime());
		bean.setNumber("2");
		bean.setmHdbh("201603049688");
		bean.setOperate(oper);
		try {
			bean.setSign(SignUtil.getSign(bean));
		} catch (EleGiftException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
