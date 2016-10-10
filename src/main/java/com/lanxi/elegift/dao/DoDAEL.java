package com.lanxi.elegift.dao;

import java.io.Serializable;

import com.lanxi.elegift.bean.in.OrderInfoBean;
/**
 * DAEL表操作接口
 * @author 1
 *
 */
public interface DoDAEL extends Serializable {
	public void add(OrderInfoBean order);
	public OrderInfoBean getOrderInfoBySomeInfo(OrderInfoBean order);
	public void update(OrderInfoBean order);
}
