package com.lanxi.elegift.dao;

import java.io.Serializable;

public interface DoThin extends Serializable {
	/**
	 * 根据商品编号获取商品类别
	 * @param spbh
	 * @return
	 */
	public String getSplbBySpbh(String spbh);
	/**
	 * 根据商品编号获取商品名称
	 * @param spbh
	 * @return
	 */
	public String getSpmcBySpbh(String spbh);
}
