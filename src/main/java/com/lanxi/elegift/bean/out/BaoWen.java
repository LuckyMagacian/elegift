package com.lanxi.elegift.bean.out;

import java.io.Serializable;

import com.lanxi.elegift.bean.out.headbean.Head;
import com.lanxi.elegift.bean.out.msgbean.Msg;
/**
 * 报文bean
 * 由Head与Msg组成
 * @author 1
 *
 */
public class BaoWen implements Serializable {
	private Head head;//报文头,代表报文中必须信息部分
	private Msg	 msg;//报文消息,代表业务部分
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public Msg getMsg() {
		return msg;
	}
	public void setMsg(Msg msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "BaoWen [getHead()=" + getHead() + ", getMsg()=" + getMsg() + "]";
	}
	
}
