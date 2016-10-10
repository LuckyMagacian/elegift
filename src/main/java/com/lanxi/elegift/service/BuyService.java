package com.lanxi.elegift.service;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 一次请求Service接口
 * @author 1
 *
 */
public interface BuyService extends Serializable {
	public void buy(HttpServletRequest req,HttpServletResponse res);
}
