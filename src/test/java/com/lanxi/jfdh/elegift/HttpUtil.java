package com.lanxi.jfdh.elegift;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpUtil implements Serializable {
	
	public static void postXml(String xml){
		try {
			byte[] bytes=xml.getBytes("GBK");
			URL url=new URL("http://192.168.17.62/com.lanxi.elegift/buy.do");
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");  
		    conn.setRequestProperty("Content-Length",""+bytes.length); 
		    conn.connect();
		    DataOutputStream out=new DataOutputStream(conn.getOutputStream());
		    out.write(bytes);
		    out.flush();
		    out.close();
		    if(conn.getResponseCode()==200)
		    	System.out.println("finsished!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
