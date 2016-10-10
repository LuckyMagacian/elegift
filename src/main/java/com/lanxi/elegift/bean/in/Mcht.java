package com.lanxi.elegift.bean.in;

import java.io.Serializable;
/**
 * 内部电子券类
 * @author 1
 *
 */
public class Mcht implements Serializable {
	private String code;//串码
	private String endTime;//过期时间
	private String startTime;//起始时间
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Override
	public String toString() {
		return "Mcht [getCode()=" + getCode() + ", getEndTime()=" + getEndTime() + ", getStartTime()=" + getStartTime()
				+ "]";
	}
	
}
