package com.lanxi.elegift.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.in.Mcht;
import com.lanxi.elegift.bean.in.OpenBean;
import com.lanxi.elegift.bean.in.ReqBean;
import com.lanxi.elegift.bean.in.ResBean;
import com.lanxi.elegift.bean.out.BaoWen;
import com.lanxi.elegift.bean.out.Sku;
import com.lanxi.elegift.bean.out.headbean.Head;
import com.lanxi.elegift.bean.out.headbean.ReqHead;
import com.lanxi.elegift.bean.out.headbean.ResHead;
import com.lanxi.elegift.bean.out.msgbean.Msg;
import com.lanxi.elegift.bean.out.msgbean.ReqMsg;
import com.lanxi.elegift.bean.out.msgbean.ResMsg;
/**
 * 转换工具类
 * @author 1
 *
 */
public class ParseUtil implements Serializable {
	private static Logger logger=Logger.getLogger(ParseUtil.class);
	/**
	 * 私有化构造方法 避免被实例化
	 */
	private ParseUtil(){};
	/**
	 * 将map形式的参数 转换为字符串
	 * @param params
	 * @return
	 */
	public static String getParamStr(Map<String, String> params){
		List<String> keys=new ArrayList<String>();
		for(Entry<String, String> each:params.entrySet())
			keys.add(each.getKey());
		Collections.sort(keys);
		StringBuilder temp=new StringBuilder();
		for(String each:keys){
			temp.append(each+"=");
			temp.append(params.get(each));
			temp.append("&");
		}
		return temp.toString().substring(0,temp.length()-1);
	}
	/**
	 * bean转json
	 * @param obj
	 * @return
	 */
	public static String getJson(Object obj){
		return JSONObject.toJSONString(obj);
	}
	/**
	 * json转bean 
	 * @param str json字符
	 * @param clazz 目标class对象
	 * @return
	 */
	public static Object getObj(String str,Class<?> clazz){
		return JSONObject.parseObject(str, clazz);
	}
	/**
	 * json转二次响应bean
	 * @param str
	 * @return
	 */
	public static ResBean getResBean(String str){
		ResBean rs=(ResBean) getObj(str, ResBean.class);
		Date today=new Date();
		today.setDate(today.getDate()+59);//
		if(rs.getObject()!=null)
		for(Mcht each:rs.getObject()){
			each.setEndTime(TimeUtil.formatDate(today));
		}
		return rs;
	}
	/**
	 * 从请求报文中获取参数封装为请求bean
	 * @param baoWen
	 * @return
	 */
	public static ReqBean toReqBean(BaoWen baoWen){
		ReqHead head=(ReqHead) baoWen.getHead();
		ReqMsg  msg=(ReqMsg) baoWen.getMsg();
		ReqBean bean=new ReqBean();
		bean.setMchtId("40");
		bean.setMobile(msg.getPhone());
		bean.setmHdbh(msg.getSkuCode());
		bean.setNumber(msg.getCount());
		bean.setTradeDate(TimeUtil.getDate());
		bean.setTradeTime(TimeUtil.getSmallTime());
		bean.setOrderId(BeanUtil.getId(bean));
		
		return bean;
	}
	/**
	 * 从内部响应与请求报文 中提取参数 生成响应报文
	 * @param res	   二次请求响应结果
	 * @param baowen 请求报文
	 * @return
	 */
	public static BaoWen toBaoWen(ResBean res,BaoWen baowen){
		BaoWen rs=new BaoWen();
		ResHead head=new ResHead();
		ResMsg	msg=new ResMsg();
		
		ReqHead shead=(ReqHead) baowen.getHead();//请求报文的消息头
		ReqMsg	smsg=(ReqMsg) baowen.getMsg();//请求报文中的业务
		
		head.setVER(shead.getVER());
		head.setMsgNo(shead.getMsgNo());
		head.setWorkDate(shead.getWorkDate());
		head.setWorkTime(shead.getWorkTime());
		head.setADD(shead.getADD());
		head.setSRC(shead.getSRC());
		head.setDES(shead.getDES());
		head.setAPP(shead.getAPP());
		head.setMsgID(shead.getMsgID());
		head.setReserve(shead.getReserve());
		head.setCHKDate(shead.getCHKDate());
		head.setResCode(res.getRetCode());
		head.setResMsg(res.getRetMsg());
		//根据响应码判断是否成功获取电子券 成功则设置业务否则业务为空
		rs.setHead(head);
		if(head.getResCode().equals("0000")){
			rs.setMsg(msg);
		}
		return rs;
	}
	/**
	 * 请求xml转请求报文
	 * @param xmlStr
	 * @return
	 * @throws EleGiftException 
	 */
	public static Object toBean(String xmlStr) {
		Object obj = null;
		try {
			Document doc=DocumentHelper.parseText(xmlStr);
			Element  root=doc.getRootElement();
			Element  heade=root.element("HEAD");
			Head head=getHead(heade);
			Element  msge =root.element("MSG");
			Msg  msg=getMsg(msge);
			BaoWen reqBaowen=new BaoWen();
			reqBaowen.setHead(head);
			reqBaowen.setMsg(msg);
			obj=reqBaowen;
		} catch (DocumentException e) {
			 new EleGiftException("xml文档转报文bean时发生错误",e);
		} catch (EleGiftException e) {
			 new EleGiftException("报文解析错误",e);
		}
		return obj;
	}
	/**
	 * 响应报文 转 xml
	 * @param baowen
	 * @return
	 */
	public static String toXml(BaoWen baowen){
		
		ResHead head=(ResHead) baowen.getHead();
		ResMsg  msg =(ResMsg) baowen.getMsg();
		Document doc=DocumentHelper.createDocument();
		doc.setXMLEncoding("GBK");
		doc.addElement("JFDH");
		Element root=doc.getRootElement();
		root.addElement("HEAD");
		root.addElement("MSG");
		Element headEle=root.element("HEAD");
		Element  msgEle=root.element("MSG");
		try {
			for(Field each:head.getClass().getDeclaredFields()){
				each.setAccessible(true);
				Element temp=headEle.addElement(each.getName());
				temp.setText((String)each.get(head)==null?"":(String)each.get(head));
			}
			//如果获取电子券失败则不设置msg中的内容
			if(msg==null)
				return doc.asXML();
			msgEle.addElement("TotalAmt").setText(msg.getTotalAmt()==null?"":msg.getTotalAmt());
			Element listEle=msgEle.addElement("SkuList");
			if(msg.getSkuList()!=null)
			for(Sku each:msg.getSkuList()){
				listEle.addElement("Amt").setText(each.getAmt()==null?"":each.getAmt());
				listEle.addElement("Code").setText(each.getAmt()==null?"":each.getCode());
				listEle.addElement("EndTime").setText(each.getAmt()==null?"":each.getEndTime());
			}
		} catch (IllegalArgumentException e) {
			 new EleGiftException("参数非法",e);
		} catch (IllegalAccessException e) {
			 new EleGiftException("权限不足",e);
		}
		return doc.asXML();
	}
	/**
	 * xml Head元素转ReqHead
	 * @param ele
	 * @return
	 * @throws EleGiftException 
	 */
	private static Head getHead(Element ele) throws EleGiftException{
		Head head=new ReqHead();
		Field[] fields=head.getClass().getDeclaredFields();
		try {
			for(Field each:fields){
				each.setAccessible(true);
				if(ele.element(each.getName())==null)
					continue;
				//System.out.println(each.getName()+":"+ele.element(each.getName()).getText());
				each.set(head,ele.element(each.getName()).getText());
			}
		} catch (IllegalArgumentException e) {
			throw new EleGiftException("参数不合法",e);
		} catch (IllegalAccessException e) {
			throw new EleGiftException("权限不足",e);
		}
		return head;
	}
	/**
	 * xml MSG 转ResMsg
	 * @param ele
	 * @return
	 * @throws EleGiftException 
	 */
	private static Msg getMsg(Element ele) throws EleGiftException{
		Msg msg=new ReqMsg();
		Field[] fields=msg.getClass().getDeclaredFields();
		try{
			for(Field each:fields){
				each.setAccessible(true);
				if(ele.element(each.getName())==null)
					continue;
				each.set(msg, ele.element(each.getName()).getText());
			}
		} catch (IllegalArgumentException e) {
			throw new EleGiftException("参数不合法",e);
		} catch (IllegalAccessException e) {
			throw new EleGiftException("权限不足",e);
		}
		return msg;
	}
	/**
	 * 从请求中获取xml并以String形式返回
	 * @throws EleGiftException 
	 */
	public static String getXml(HttpServletRequest req) {
		StringBuilder rs=new StringBuilder();
		try {
			String temp=null;
			BufferedReader in=new BufferedReader(new InputStreamReader(req.getInputStream(), "GBK"));
			while((temp=in.readLine())!=null)
				rs.append(temp);
		} catch (IOException e) {
			 new EleGiftException("读取输入流错误",e);
		}
		return rs.toString();
	}
	/**
	 * 通过指定spbh和jgdm生成一个openbean用于Dao中doOpen作为参数
	 * @param spbh
	 * @param jgdm
	 * @return
	 */
	public static OpenBean toOpen(String spbh,String jgdm){
		OpenBean open=new OpenBean();
		open.setSpbh(spbh);
		open.setJgdm(jgdm);
		return open;
	}
}
