package com.lanxi.elegift.bean.out;

import java.io.Serializable;
/**
 * 电子券类 集成
 * @author 1
 *
 */
public class ThinBean implements Serializable {
	private String amt;//单价
	private String serialNum;//商品号
	private String code;//串码
	private String startTime;//开始时间
	private String endTime;//截止时间
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
