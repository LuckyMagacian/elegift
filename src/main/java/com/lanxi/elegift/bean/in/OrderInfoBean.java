package com.lanxi.elegift.bean.in;

import java.io.Serializable;
/**
 * 订单详情类,用于保存订单信息到数据库用
 * @author 1
 *
 */
public class OrderInfoBean implements Serializable {
	/**
	 * 订单不存在
	 */
	public static final int ORDER_STATE_NULL=0;
	/**
	 * 订单已成功
	 */
	public static final int ORDER_STATE_SUCCESS=-1;
	/**
	 * 订单失败
	 */
	public static final int ORDER_STATE_FAIL=1;//订单失败
	private Double jgfse;//机构发生额??
	private Double qdfse;//渠道发生额??
	
	private String bwbh;//报文编号 一次请求
	private String qdbh;//渠道编号
	private String fqjgh;//发起机构号 一次请求
	private String qsrq;//清算日期 一次请求
	private String jyxh;//交易序号 一次请求
	private String jsjgh;//接收机构号 一次请求
	private String dqh;//地区号 一次请求
	private String jyrq;//交易日期 一次请求
	private String jysj;//交易时间 一次请求
	private String sjhm;//手机号码 一次请求
	private String yhxm;//用户姓名  null
	private String splx;//商品类型 一次请求
	private String spbh;//商品编号 一次请求
	private String spsl;//商品数量 一次请求
	private String dxcm;//短信串码 二次响应
	private String sflsh;//三方流水号  兑换时
	private String ptlsh;//平台流水号 二次请求
	private String jyzt;//交易状态 ??
	private String sfxym;//三方响应码 兑换时
	private String sfxyx;//三方响应信息 兑换时
	private String xym;//响应码 一次响应
	private String xyxx;//响应信息 一次响应
	private String dzzt;//对账状态 0
	private String beiz;//备注 
	public String getBwbh() {
		return bwbh;
	}
	public void setBwbh(String bwbh) {
		this.bwbh = bwbh;
	}
	public String getQdbh() {
		return qdbh;
	}
	public void setQdbh(String qdbh) {
		this.qdbh = qdbh;
	}
	public String getFqjgh() {
		return fqjgh;
	}
	public void setFqjgh(String fqjgh) {
		this.fqjgh = fqjgh;
	}
	public String getQsrq() {
		return qsrq;
	}
	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}
	public String getJyxh() {
		return jyxh;
	}
	public void setJyxh(String jyxh) {
		this.jyxh = jyxh;
	}
	public String getJsjgh() {
		return jsjgh;
	}
	public void setJsjgh(String jsjgh) {
		this.jsjgh = jsjgh;
	}
	public String getDqh() {
		return dqh;
	}
	public void setDqh(String dqh) {
		this.dqh = dqh;
	}
	public String getJyrq() {
		return jyrq;
	}
	public void setJyrq(String jyrq) {
		this.jyrq = jyrq;
	}
	public String getJysj() {
		return jysj;
	}
	public void setJysj(String jysj) {
		this.jysj = jysj;
	}
	public String getPtlsh() {
		return ptlsh;
	}
	public void setPtlsh(String ptlsh) {
		this.ptlsh = ptlsh;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getYhxm() {
		return yhxm;
	}
	public void setYhxm(String yhxm) {
		this.yhxm = yhxm;
	}
	public String getSplx() {
		return splx;
	}
	public void setSplx(String splx) {
		this.splx = splx;
	}
	public String getSpbh() {
		return spbh;
	}
	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}
	public String getSpsl() {
		return spsl;
	}
	public void setSpsl(String spsl) {
		this.spsl = spsl;
	}
	public Double getJgfse() {
		return jgfse;
	}
	public void setJgfse(Double jgfse) {
		this.jgfse = jgfse;
	}
	public Double getQdfse() {
		return qdfse;
	}
	public void setQdfse(Double qdfse) {
		this.qdfse = qdfse;
	}
	public String getDxcm() {
		return dxcm;
	}
	public void setDxcm(String dxcm) {
		this.dxcm = dxcm;
	}
	public String getSflsh() {
		return sflsh;
	}
	public void setSflsh(String sflsh) {
		this.sflsh = sflsh;
	}
	public String getJyzt() {
		return jyzt;
	}
	public void setJyzt(String jyzt) {
		this.jyzt = jyzt;
	}
	public String getSfxym() {
		return sfxym;
	}
	public void setSfxym(String sfxym) {
		this.sfxym = sfxym;
	}
	public String getSfxyx() {
		return sfxyx;
	}
	public void setSfxyx(String sfxyx) {
		this.sfxyx = sfxyx;
	}
	public String getXym() {
		return xym;
	}
	public void setXym(String xym) {
		this.xym = xym;
	}
	public String getXyxx() {
		return xyxx;
	}
	public void setXyxx(String xyxx) {
		this.xyxx = xyxx;
	}
	public String getDzzt() {
		return dzzt;
	}
	public void setDzzt(String dzzt) {
		this.dzzt = dzzt;
	}
	public String getBeiz() {
		return beiz;
	}
	public void setBeiz(String beiz) {
		this.beiz = beiz;
	}
	@Override
	public String toString() {
		return "OrderInfoBean [bwbh=" + bwbh + ", qdbh=" + qdbh + ", fqjgh=" + fqjgh + ", qsrq=" + qsrq + ", jyxh="
				+ jyxh + ", jsjgh=" + jsjgh + ", dqh=" + dqh + ", jyrq=" + jyrq + ", jysj=" + jysj + ", ptlsh=" + ptlsh
				+ ", sjhm=" + sjhm + ", yhxm=" + yhxm + ", splx=" + splx + ", spbh=" + spbh + ", spsl=" + spsl
				+ ", jgfse=" + jgfse + ", qdfse=" + qdfse + ", dxcm=" + dxcm + ", sflsh=" + sflsh + ", jyzt=" + jyzt
				+ ", sfxym=" + sfxym + ", sfxyx=" + sfxyx + ", xym=" + xym + ", xyxx=" + xyxx + ", dzzt=" + dzzt
				+ ", beiz=" + beiz + "]";
	}
	
}
