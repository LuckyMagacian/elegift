package com.lanxi.elegift.bean.out.msgbean;

import java.util.List;

import com.lanxi.elegift.bean.out.Sku;
/**
 * 响应业务
 * @author 1
 *
 */
public class ResMsg implements Msg {
	private String TotalAmt;//总价
	private List<Sku>SkuList;//商品列表
	public String getTotalAmt() {
		return TotalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		TotalAmt = totalAmt;
	}
	public List<Sku> getSkuList() {
		return SkuList;
	}
	public void setSkuList(List<Sku> skuList) {
		SkuList = skuList;
	}
	@Override
	public String toString() {
		return "ResMsg [getTotalAmt()=" + getTotalAmt() + ", getSkuList()=" + getSkuList() + "]";
	}
	
}
