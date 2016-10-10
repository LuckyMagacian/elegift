package com.lanxi.elegift.bean.out.headbean;
/**
 * 返回消息头
 * @author 1
 *
 */
public class ResHead implements Head {
	private String 	VER;		//消息版本
	private String 	MsgNo;		//消息编号
	private String  CHKDate;	//清空日期
	private String  WorkDate;	//交易日期
	private String  WorkTime;	//交易时间
	private String 	ADD;		//地址ַ
	private String 	SRC;		//交易发起方
	private String	DES;		//交易接收放
	private String  APP;		//应用平台
	private String	MsgID;		//消息id
	private String  Reserve;	//备注
	private String  Sign;		//签名
	private String ResCode;		//返回码
	private String ResMsg;		//返回消息
	
	
	public String getVER() {
		return VER;
	}
	public void setVER(String vER) {
		VER = vER;
	}
	public String getMsgNo() {
		return MsgNo;
	}
	public void setMsgNo(String msgNo) {
		MsgNo = msgNo;
	}
	public String getCHKDate() {
		return CHKDate;
	}
	public void setCHKDate(String cHKDate) {
		CHKDate = cHKDate;
	}
	public String getWorkDate() {
		return WorkDate;
	}
	public void setWorkDate(String workDate) {
		WorkDate = workDate;
	}
	public String getWorkTime() {
		return WorkTime;
	}
	public void setWorkTime(String workTime) {
		WorkTime = workTime;
	}
	public String getADD() {
		return ADD;
	}
	public void setADD(String aDD) {
		ADD = aDD;
	}
	public String getSRC() {
		return SRC;
	}
	public void setSRC(String sRC) {
		SRC = sRC;
	}
	public String getDES() {
		return DES;
	}
	public void setDES(String dES) {
		DES = dES;
	}
	public String getAPP() {
		return APP;
	}
	public void setAPP(String aPP) {
		APP = aPP;
	}
	public String getMsgID() {
		return MsgID;
	}
	public void setMsgID(String msgID) {
		MsgID = msgID;
	}
	public String getReserve() {
		return Reserve;
	}
	public void setReserve(String reserve) {
		Reserve = reserve;
	}
	public String getSign() {
		return Sign;
	}
	public void setSign(String sign) {
		Sign = sign;
	}
	public String getResCode() {
		return ResCode;
	}
	public void setResCode(String resCode) {
		ResCode = resCode;
	}
	public String getResMsg() {
		return ResMsg;
	}
	public void setResMsg(String resMsg) {
		ResMsg = resMsg;
	}
	@Override
	public String toString() {
		return "ResponseBean [getResCode()=" + getResCode() + ", getResMsg()=" + getResMsg() + ", getVER()=" + getVER()
				+ ", getMsgNo()=" + getMsgNo() + ", getCHKDate()=" + getCHKDate() + ", getWorkDate()=" + getWorkDate()
				+ ", getWorkTime()=" + getWorkTime() + ", getADD()=" + getADD() + ", getSRC()=" + getSRC()
				+ ", getDES()=" + getDES() + ", getAPP()=" + getAPP() + ", getMsgID()=" + getMsgID() + ", getReserve()="
				+ getReserve() + ", getSign()=" + getSign() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
