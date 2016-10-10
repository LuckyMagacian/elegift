package com.lanxi.elegift.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;

import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.in.ReqBean;
import com.lanxi.elegift.bean.in.SmsBean;

/**
 * http工具类
 * @author 1
 *
 */
public class HttpUtil implements Serializable {
	private static String defEnCharset="utf-8";//默认编码字符集
	private static String defDeCharset="utf-8";//默认解码字符集
	private static Logger logger=Logger.getLogger(HttpUtil.class);
	/**
	 * 私有化构造方法 避免被实例化
	 */
	private HttpUtil(){};
	/**
	 * 接受 post的url参数(字符串形式) 然后post
	 * @param str
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postStr(String str) throws EleGiftException{
		return postStr(str, defEnCharset,defEnCharset);
	}
	/**
	 * 接受 字符串形式的post参数 字符集 然后post
	 * @param str
	 * @param charset
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postStr(String str,String charset) throws EleGiftException{
		return postStr(str, charset, charset);
	}
	/**
	 * 接受 字符串形式的post参数 输出字符集 输入字符集 然后post
	 * @param str
	 * @param enCharset
	 * @param deCharset
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postStr(String str,String enCharset,String deCharset) throws EleGiftException{
		return postStr(str, ConfUtil.getUrl(), enCharset, deCharset);
	}
	/**
	 * 接受 字符串形式的参数 以及 url 编解码字符集 然后post
	 * @param str
	 * @param uRl
	 * @param enCharset
	 * @param deCharset
	 * @return
	 * @throws EleGiftException
	 */
	public static String postStr(String str,String uRl,String enCharset,String deCharset) throws EleGiftException{
		logger.info("请求url:"+uRl+"请求参数:"+str);
		StringBuilder rs=new StringBuilder();
		try {
			URL url;
				url = new URL(uRl);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			PrintWriter out=new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),enCharset));
			out.println(str);
			out.flush();
			out.close();
			
			BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream(), deCharset));
			String temp=null;
			while((temp=in.readLine())!=null)
				rs.append(temp);
			in.close();
			return rs.toString();
		} catch (MalformedURLException e) {
			throw new EleGiftException("url格式错误",e);
		} catch (UnsupportedEncodingException e) {
			throw new EleGiftException("不支持的字符集",e);
		} catch (IOException e) {
			throw new EleGiftException("输入输出流错误",e);
		}
	}
	/**
	 * 接受 map形式的post参数 然后post
	 * @param params
	 * @return
	 * @throws EleGiftException 
	 */
	public static String doPost(Map<String, String> params) throws EleGiftException{
		return doPost(params, defEnCharset, defEnCharset);
	}
	/**
	 * 接受 map形式的post参数 字符集 然后post
	 * @param params
	 * @param charset
	 * @return
	 * @throws EleGiftException 
	 */
	public static String doPost(Map<String,String> params,String charset) throws EleGiftException{
		return doPost(params,charset,charset);
	}
	/**
	 * 接受map形式的post参数 输入字符集 输出字符集 然后post
	 * @param params
	 * @param enCharset
	 * @param deCharset
	 * @return
	 * @throws EleGiftException 
	 */
	public static String doPost(Map<String, String> params,String enCharset,String deCharset) throws EleGiftException{
		String arg=ParseUtil.getParamStr(params);
		return postStr(arg, enCharset, deCharset);
	}
	
	/**
	 * 发送短信
	 * @param bean 短信bean
	 * @return Json  响应信息
	 * @throws EleGiftException
	 */
	public static String postSms(SmsBean bean) throws EleGiftException{
		Map<String, String> params=BeanUtil.getObjectParams(bean);
		String param=ParseUtil.getParamStr(params);
		return postStr(param, ConfUtil.getSmsUrl(), "utf-8", "utf-8");	
	}
	/**
	 * 接受 二次请求bean  然后内部处理后 post
	 * @param bean
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postBean(ReqBean bean) throws EleGiftException{
		return postBean(bean, defEnCharset, defEnCharset);
	}
	/**
	 * 接受二次请求bean 字符集 然后post
	 * @param bean
	 * @param charset
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postBean(ReqBean bean,String charset) throws EleGiftException{
		return postBean(bean, charset, charset);
	}
	/**
	 * 接受二次请求bean 编码字符集 解码字符集 然后post
	 * @param bean
	 * @param enCharset
	 * @param deCharset
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postBean(ReqBean bean,String enCharset,String deCharset) throws EleGiftException{
		Map<String, String> map = null;
		try {
			map = BeanUtil.getParamMapWithSign(bean);
		} catch (EleGiftException e) {
			new EleGiftException("",e);
		}
		return doPost(map);
	}
	/**
	 * 接受 doc形式的xml 然后post
	 * @param doc
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postXml(Document doc) throws EleGiftException{
		return postXml(doc.asXML());
	}
	/**
	 * 接受doc形式的xml 发送字符集 然后post
	 * @param doc
	 * @param charset
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postXml(Document doc,String charset) throws EleGiftException{
		return postXml(doc.asXML(), charset);
	}
	/**
	 * 接受 string形式的xml 然后以GBK方式post
	 * @param xml
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postXml(String xml) throws EleGiftException{
		return postXml(xml,"GBK"); 
	}
	/**
	 * 接受  String 形式的xml 字符集 然后post
	 * @param xml
	 * @param charset
	 * @return
	 * @throws EleGiftException 
	 */
	public static String postXml(String xml,String charset) throws EleGiftException{
		try {
			byte[] bytes=xml.getBytes(charset);
			URL url=new URL(ConfUtil.getBackUrl());
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/xml;charset="+charset);  
		    conn.setRequestProperty("Content-Length",""+bytes.length); 
		    conn.connect();
		    DataOutputStream out=new DataOutputStream(conn.getOutputStream());
		    out.write(bytes);
		    out.flush();
		    out.close();
		    if(conn.getResponseCode()==200){
		    	BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
		    	String tempStr=null;
		    	StringBuilder rs=new StringBuilder();
		    	while((tempStr=in.readLine())!=null)
		    		rs.append(tempStr);
		    	return rs.toString();
		    }
		} catch (UnsupportedEncodingException e) {
			throw new EleGiftException("不支持的字符集",e);
		} catch (MalformedURLException e) {
			throw new EleGiftException("url格式错误",e);
		} catch (IOException e) {
			throw new EleGiftException("输入输出流错误",e);
		}
		return "fail~";
	}
	/**
	 * 由httpServletResponse 响应xmldoc
	 * @param doc
	 * @param res
	 * @return
	 * @throws EleGiftException
	 */
	public static String postXml(Document doc,HttpServletResponse res) throws EleGiftException{
		return postXml(doc, res, "GBK");
	}
	/**
	 * 可以指定字符集
	 * @param doc
	 * @param res
	 * @param charset
	 * @return
	 * @throws EleGiftException
	 */
	public static String postXml(Document doc,HttpServletResponse res,String charset) throws EleGiftException{
		return postXml(doc.asXML(), res, charset);
	}
	/**
	 * String形式的 xml
	 * @param xml
	 * @param res
	 * @return
	 * @throws EleGiftException
	 */
	public static String postXml(String xml,HttpServletResponse res) throws EleGiftException{
		return postXml(xml, res, "GBK");
	}
	/**
	 * 完整实现 发送 String 形式xml文档
	 * @param xml
	 * @param res
	 * @param charset
	 * @return
	 * @throws EleGiftException
	 */
	public static String postXml(String xml,HttpServletResponse res,String charset) throws EleGiftException{
		try {
			res.setCharacterEncoding(charset);
			res.setContentType("text/xml;charset="+charset);
			res.setContentLength(xml.getBytes(charset).length);
			DataOutputStream out=new DataOutputStream(res.getOutputStream());
			out.write(xml.getBytes(charset));
			out.flush();
			out.close();
			if(res.getStatus()==200)
				return "return success";
		} catch (IOException e) {
			throw new EleGiftException("输入输出流错误",e);
		}
		return "return fail";
	}
}
