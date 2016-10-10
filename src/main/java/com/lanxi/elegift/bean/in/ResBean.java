package com.lanxi.elegift.bean.in;


import java.io.Serializable;
import java.util.List;
/**
 * 二次请求响应bean
 * @author 1
 *
 */
public class ResBean implements Serializable {
	private String mHdbh;		//商品编号
	private String mchtId;		//商家编号
	private String mobile;		//手机号码
	private String number;		//商品数量
	private String operate;		//商品类别(商家代码)
	private String orderId;		//商品类别(商家代码)
	private List<Mcht> Object;	//商品列表
	private String retCode;		//返回码
	private String retMsg;		//返回信息
	private String tradeDate;	//交易日期
	private String tradeTime;	//交易时间
	public String getmHdbh() {
		return mHdbh;
	}
	public void setmHdbh(String mHdbh) {
		this.mHdbh = mHdbh;
	}
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<Mcht> getObject() {
		return Object;
	}
	public void setObject(List<Mcht> list) {
		this.Object = list;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	@Override
	public String toString() {
		return "ResBean [getmHdbh()=" + getmHdbh() + ", getMchtId()=" + getMchtId() + ", getMobile()=" + getMobile()
				+ ", getNumber()=" + getNumber() + ", getOperate()=" + getOperate() + ", getOrderId()=" + getOrderId()
				+ ", getObject()=" + getObject() + ", getRetCode()=" + getRetCode() + ", getRetMsg()=" + getRetMsg()
				+ ", getTradeDate()=" + getTradeDate() + ", getTradeTime()=" + getTradeTime() + "]";
	}
	
}
