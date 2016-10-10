package com.lanxi.elegift.bean.in;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class EleGiftException extends Exception implements Serializable {
	private static Logger logger=Logger.getLogger(EleGiftException.class);
	public EleGiftException(String errMsg){
		super(errMsg);
		logger.error("发生了一个错误:\n"+
					 "错误信息:"+errMsg);
	}
	public EleGiftException(String errMsg,Throwable e){
		super(errMsg, e);
		logger.error("发生了一个错误:\n"+
					 "错误源:"+getCause()+"\n"+
					 "错误信息:"+errMsg);
	}
}
