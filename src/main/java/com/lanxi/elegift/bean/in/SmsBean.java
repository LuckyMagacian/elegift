package com.lanxi.elegift.bean.in;

import java.io.Serializable;

public class SmsBean implements Serializable {
	private String mchtId;
	private String orderId;
	private String mobile;
	private String content;
	private String tradeDate;
	private String tradeTime;
	private String sign;
	private String tdId;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getTdId() {
		return tdId;
	}
	public void setTdId(String tdId) {
		this.tdId = tdId;
	}
	@Override
	public String toString() {
		return "SmsBean [mchtId=" + mchtId + ", orderId=" + orderId + ", mobile=" + mobile + ", content=" + content
				+ ", tradeDate=" + tradeDate + ", tradeTime=" + tradeTime + ", sign=" + sign + ", tdId=" + tdId + "]";
	}
	
}
