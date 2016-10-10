package com.lanxi.elegift.bean.in;

import java.io.Serializable;
/**
 * 二次请求类
 * @author 1
 *
 */
public class ReqBean implements Serializable {
	private String mchtId;		//商家编号->req
	private String orderId;		//订单=商家编号+订单时间+随机4位数 流水号
	private String mHdbh;		//商品编号->req
	private String mobile;		//手机号码->req
	private String tradeDate;	//交易日期
	private String tradeTime;	//交易时间
	private String number;		//商品数量->req
	private String operate;		//商品类别(商家代码)???
	private String sign;		//签名
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getmHdbh() {
		return mHdbh;
	}
	public void setmHdbh(String mHdbh) {
		this.mHdbh = mHdbh;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "ReqBean [getMchtId()=" + getMchtId() + ", getOrderId()=" + getOrderId() + ", getmHdbh()=" + getmHdbh()
				+ ", getMobile()=" + getMobile() + ", getTradeDate()=" + getTradeDate() + ", getTradeTime()="
				+ getTradeTime() + ", getNumber()=" + getNumber() + ", getOperate()=" + getOperate() + ", getSign()="
				+ getSign() + "]";
	}
	
}
