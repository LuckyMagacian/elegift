package com.lanxi.elegift.bean.in;

import java.io.Serializable;
/**
 * 机构业务开通表对象类
 * @author 1
 */
public class OpenBean implements Serializable {
	private String jgdm;//机构代码
	private String spbh;//商品编号
	private String splb;//商品类别
	private String kzlb;//商品单价
	private String beiy;//备用
	public String getJgdm() {
		return jgdm;
	}
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}
	public String getSpbh() {
		return spbh;
	}
	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}
	public String getSplb() {
		return splb;
	}
	public void setSplb(String splb) {
		this.splb = splb;
	}
	public String getKzlb() {
		return kzlb;
	}
	public void setKzlb(String kzlb) {
		this.kzlb = kzlb;
	}
	public String getBeiy() {
		return beiy;
	}
	public void setBeiy(String beiy) {
		this.beiy = beiy;
	}
	
}
