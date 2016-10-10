package com.lanxi.elegift.bean.out.msgbean;
/**
 * 请求业务
 * @author 1
 *
 */
public class ReqMsg implements Msg {
	private String Phone;//手机号码
	private String Type; //商品类型
	private String SkuCode;//商品编号
	private String Count;//商品数量
	private String NeedSend;//是否需要下发短信
	private String Remark;//备注
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getSkuCode() {
		return SkuCode;
	}
	public void setSkuCode(String skuCode) {
		SkuCode = skuCode;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	public String getNeedSend() {
		return NeedSend;
	}
	public void setNeedSend(String needSend) {
		NeedSend = needSend;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	@Override
	public String toString() {
		return "RequestMsg [getPhone()=" + getPhone() + ", getType()=" + getType() + ", getSkuCode()=" + getSkuCode()
				+ ", getCount()=" + getCount() + ", getNeedSend()=" + getNeedSend() + ", getRemark()=" + getRemark()
				+ "]";
	}
	
	
}
