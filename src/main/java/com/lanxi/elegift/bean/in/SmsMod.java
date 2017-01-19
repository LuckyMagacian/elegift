package com.lanxi.elegift.bean.in;

public class SmsMod {
	private String branchid;
	private String goodsid;
	private String smsmod;
	private String bak;
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getSmsmod() {
		return smsmod;
	}
	public void setSmsmod(String smsmod) {
		this.smsmod = smsmod;
	}
	public String getBak() {
		return bak;
	}
	public void setBak(String bak) {
		this.bak = bak;
	}
	@Override
	public String toString() {
		return "SmsMod [branchid=" + branchid + ", goodsid=" + goodsid + ", smsmod=" + smsmod + ", bak=" + bak + "]";
	}
	
}
