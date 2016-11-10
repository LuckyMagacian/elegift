package com.lanxi.jfdh.elegift;



import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.out.BaoWen;
import com.lanxi.elegift.util.HttpUtil;
import com.lanxi.elegift.util.ParseUtil;
import com.lanxi.elegift.util.SignUtil;

public class TestService {

	@Test
	public void sendReq() throws EleGiftException{
		StringBuilder xml=new StringBuilder();
		Document doc=DocumentHelper.createDocument();
		doc.setXMLEncoding("GBK");
		Element root=DocumentHelper.createElement("JFDH");
		root.addElement("HEAD");
		root.addElement("MSG");
		Element head=root.element("HEAD");
		Element msg=root.element("MSG");
		head.addElement("VER");
		head.element("VER").setText("1.0");
		head.addElement("APP");
		head.element("APP").setText("杭州蓝喜积分兑换平台");
		head.addElement("MsgNo");
		head.element("MsgNo").setText("1001");
		head.addElement("CHKDate");
		head.element("CHKDate").setText("20160928");
		head.addElement("WorkDate");
		head.element("WorkDate").setText("20160928");
		head.addElement("WorkTime");
		head.element("WorkTime").setText("165800");
		head.addElement("ADD");
		head.element("ADD").setText("11");;
		head.addElement("SRC");
		head.element("SRC").setText("3311111111111110");
		head.addElement("DES");
		head.element("DES").setText("1000000000000000");
		head.addElement("MsgID");
		head.element("MsgID").setText("20010001");
		head.addElement("Reserve");
		head.element("Reserve").setText("我就试试");
		head.addElement("Sign");
		head.element("Sign").setText("0ab2f634bbac4bfa26203ad8561ab830");
		
		
		msg.addElement("Phone");
		msg.element("Phone").setText("15068610940");
		msg.addElement("SkuCode");
		msg.element("SkuCode").setText("3004");
		msg.addElement("Type");
		msg.element("Type").setText("40");
		msg.addElement("Count");
		msg.element("Count").setText("3");
		msg.addElement("NeedSend");
		msg.element("NeedSend").setText("0");
		msg.addElement("Remark");
		msg.element("Remark").setText("我就试试");
		
		doc.add(root);
		System.out.println(doc.asXML());
		//HttpUtil.postXml(doc);
		BaoWen baowen=(BaoWen) ParseUtil.toBean(doc.asXML());
		System.out.println(SignUtil.getSignNoSort(baowen));
	}
	
	//@Test
	public void testAll() throws EleGiftException{
		
		final String xml="<?xml version=\"1.0\" encoding=\"GBK\"?>"+
				"<JFDH>"
					+ "<HEAD>"
						+ "<VER>1.0</VER>"
						+ "<APP>杭州蓝喜积分兑换平台</APP>"
						+ "<MsgNo>1001</MsgNo>"
						+ "<CHKDate>20161009</CHKDate>"
						+ "<WorkDate>20161009</WorkDate>"
						+ "<WorkTime>165900</WorkTime>"
						+ "<ADD>11</ADD>"
						+ "<SRC>3311111111111110</SRC>"
						+ "<DES>1000000000000000</DES>"
						+ "<MsgID>20010001</MsgID>"
						+ "<Reserve>我就试试</Reserve>"
						+ "<Sign>ab48b00317e187e32bb98</Sign>"
						+ "</HEAD>"
					+ "<MSG>"
						+ "<Phone>15068610940</Phone>"
						+ "<SkuCode>5004</SkuCode>"
						+ "<Type>50</Type>"
						+ "<Count>1</Count>"
						+ "<NeedSend>0</NeedSend>"
						+ "<Remark>我就试试</Remark>"
					+ "</MSG>"
				+ "</JFDH>";
		Thread[] threads=new Thread[1];
		Runnable task=new Runnable() {
			public void run() {
				try {
					for(int i=0;i<1;i++){
					String rs=HttpUtil.postXml(xml);
					System.out.println(Thread.currentThread().getName()+"第"+i+"次:");
					//System.out.println(rs);
					}
				} catch (EleGiftException e) {
					e.printStackTrace();
				}
			}
		};
		for(int i=0;i<threads.length;i++){
			threads[i]=new Thread(task);
			threads[i].start();
		}
	}
	public static void main(String[]  args) throws EleGiftException{
		TestService testService=new TestService();
		testService.testAll();
	}
}
