package com.lanxi.jfdh.elegift;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.in.Mcht;
import com.lanxi.elegift.bean.in.OrderInfoBean;
import com.lanxi.elegift.bean.in.ReqBean;
import com.lanxi.elegift.bean.in.ResBean;
import com.lanxi.elegift.bean.in.SmsBean;
import com.lanxi.elegift.bean.out.BaoWen;
import com.lanxi.elegift.bean.out.Sku;
import com.lanxi.elegift.bean.out.headbean.Head;
import com.lanxi.elegift.bean.out.headbean.ReqHead;
import com.lanxi.elegift.bean.out.headbean.ResHead;
import com.lanxi.elegift.bean.out.msgbean.Msg;
import com.lanxi.elegift.bean.out.msgbean.ReqMsg;
import com.lanxi.elegift.bean.out.msgbean.ResMsg;
import com.lanxi.elegift.dao.DoDAEL;
import com.lanxi.elegift.util.BeanUtil;
import com.lanxi.elegift.util.ConfUtil;
import com.lanxi.elegift.util.HttpUtil;
import com.lanxi.elegift.util.ParseUtil;
import com.lanxi.elegift.util.SerialNumUtil;
import com.lanxi.elegift.util.SignUtil;
import com.lanxi.elegift.util.TestUtil;
import com.lanxi.elegift.util.TimeUtil;

public class TestCase {

	@Test
	public void testGetTime(){
		System.out.println("Date:"+TimeUtil.getDate());
		System.out.println("Time:"+TimeUtil.getSmallTime());
		System.out.println("Full:"+TimeUtil.getTime());
	}
	@Test
	public void testGetUrl(){
		System.out.println(ConfUtil.getUrl());
	}
	@Test
	public void testGetMap() throws EleGiftException{
		System.out.println(BeanUtil.getParamMapWithSign(TestUtil.getTestReqBean()));
	}
	@Test
	public void testSign() throws EleGiftException{
		System.out.println(SignUtil.getSign(TestUtil.getTestReqBean()));
	}
	@Test
	public void testGetReqBean(){
		System.out.println(TestUtil.getTestReqBean());
	}
	@Test 
	public void testGetJson(){
		ResBean bean=new ResBean();
		List<Mcht> list=new ArrayList<Mcht>();
		Mcht mc1=new Mcht();
		mc1.setCode("1001");
		mc1.setStartTime("20160923");
		mc1.setEndTime("20160924");
		Mcht mc2=new Mcht();
		mc2.setCode("1002");
		mc1.setStartTime("20160923");
		mc2.setEndTime("20160924");
		list.add(mc1);
		list.add(mc2);
		bean.setObject(list);
		System.out.println(ParseUtil.getJson(bean));
		//System.out.println(Utils.getJson(Utils.getTestReqBean()));
	}
	@Test
	public void testGetParamStr() throws EleGiftException{
		System.out.println(ParseUtil.getParamStr(BeanUtil.getParamMapWithSign(TestUtil.getTestReqBean())));
	}
	@Test
	public void testHttpPost() throws EleGiftException{
		System.out.println(HttpUtil.postBean(TestUtil.getTestReqBean()));
	}
	@Test
	public void testGetResBean() throws EleGiftException{
		System.out.println(ParseUtil.getResBean(HttpUtil.postBean(TestUtil.getTestReqBean())));
	}
	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException, EleGiftException{
		Scanner input=new Scanner(System.in);
		ReqBean bean=new ReqBean();
		Field[] fields = bean.getClass().getDeclaredFields();
		System.err.println("Test start ~");
		
		//bean.setTradeDate(Utils.getDate());
		
		bean.setTradeDate("20160911");
		System.out.println("Create trade date : "+bean.getTradeDate());

		//bean.setTradeTime(Utils.getSmallTime());
		bean.setTradeTime("161401");
		System.out.println("Create trade time : "+bean.getTradeTime());
		
//		bean.setMchtId("40");
//		bean.setmHdbh("201603049688");
//		bean.setNumber("2");
//		bean.setMobile("15268828266");
		for(Field each:fields){
			each.setAccessible(true);
			if (each.get(bean)!=null||each.getName().equals("sign")||each.getName().equals("orderId"))
				continue;
			System.out.println("Please input "+each.getName()+" :");
			String arg=input.nextLine();
			each.set(bean, arg);
		}
		input.close();
		bean.setOrderId(bean.getMchtId()+bean.getTradeDate()+bean.getTradeTime()+"0000");
		System.out.println("Create trade order id : "+bean.getOrderId());
		
		bean.setSign(SignUtil.getSign(bean));
		System.out.println("Create sign : "+bean.getSign());
		
		
		System.out.println("Post testbean ---------------");
		String rs=HttpUtil.postBean(bean);
		System.out.println("Return string : "+rs);
		
		System.out.println("Parse string to bean -------- ");
		ResBean res=ParseUtil.getResBean(rs);
		System.out.println("Response bean : "+res);
		System.err.println("Test end ~");
	}
	@Test
	public void testRandom(){
		System.out.println(SerialNumUtil.getRandom(9));
	}
	@Test
	public void testClassLoader(){
		System.out.println(this.getClass().getClassLoader());
		System.out.println(ClassLoader.class);
		System.out.println(TestCase.class.getClassLoader());
		System.out.println(ClassLoader.getSystemClassLoader());
	}
	@Test
	public void testGetFields() throws EleGiftException{
		BaoWen baoWen=new BaoWen();
		Head head=new ReqHead();
		Msg  msg=new ReqMsg();
		baoWen.setHead(head);
		baoWen.setMsg(msg);
		System.out.println(SignUtil.getSignNoSort(baoWen));
	}
	@Test
	public void testStringContain(){
		System.out.println("�װ�,΢��,����,�ö�,����".contains("xxxx"));
	}
	@Test
	public void testBaoWenToXml() throws EleGiftException{
		BaoWen baoWen=new BaoWen();
		ResHead head=new ResHead();
		ResMsg  msg =new ResMsg();
		List<Sku> list=new ArrayList<Sku>();
		Sku t1=new Sku();
		t1.setAmt("1.0");
		t1.setCode("1");
		t1.setEndTime("1");
		list.add(t1);
		msg.setSkuList(list);
		baoWen.setHead(head);
		baoWen.setMsg(msg);
		System.out.println(ParseUtil.toXml(baoWen));
	}
	@Test
	public void testGetParams(){
		try {
			System.out.println(List.class.getMethod("toString", null));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testDate(){
		Date date=new Date();
		date.setDate(date.getDate()+59);
		System.out.println(TimeUtil.formatDate(date));
	}
	@Test
	public void testGetField(){
		Field[] fields=OrderInfoBean.class.getDeclaredFields();
		for(Field each:fields)
			System.out.println(each.getName()+"=#{"+each.getName()+"},");
	}
	@Test
	public void testException(){
		EleGiftException e=new EleGiftException("test",new Exception("test"));
	}
	@Test
	public void testSms() throws EleGiftException{
		SmsBean bean=new SmsBean();
		bean.setMchtId("10");
		bean.setMobile("15068610940");
		bean.setOrderId("10201609291455053161");
		bean.setTradeDate(TimeUtil.getDate());
		bean.setTradeTime(TimeUtil.getSmallTime());
		bean.setContent("您收到的电子券的串码为:串码0:4288967764截止日期:20161127串码1:8211379550截止日期:20161127请及时兑换,以免过期!");
		bean.setTdId("1");
		SignUtil.signSms(bean);
//		System.out.println(bean);
		System.out.println(BeanUtil.sendSms(bean));
	}
	@Test
	public void testSecReq() throws EleGiftException{
		ReqBean req=TestUtil.getTestReqBean("2001");
		System.out.println(req);
		String rs=HttpUtil.postBean(req);
		System.out.println(rs);
	}
	@Test
	public void testConf(){
		String[] strs=ConfUtil.get("华夏银行3").split("\\|{2}");
		System.out.println(ConfUtil.get("华夏银行3"));
		List<String> list=Arrays.asList(strs);
		System.out.println(list);
		System.out.println(ConfUtil.get("3302104332051437"));
	}
}
