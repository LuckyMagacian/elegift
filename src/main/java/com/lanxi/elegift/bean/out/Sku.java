package com.lanxi.elegift.bean.out;

import java.io.Serializable;
/**
 * 电子券类
 * 返回给第三方用
 * @author 1
 *
 */
public class Sku implements Serializable {
	private String Amt;//单价
	private String Code;//串码
	private String EndTime;//截止时间
	public String getAmt() {
		return Amt;
	}
	public void setAmt(String amt) {
		Amt = amt;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	@Override
	public String toString() {
		return "Sku [getAmt()=" + getAmt() + ", getCode()=" + getCode() + ", getEndTime()=" + getEndTime() + "]";
	}
	
}
