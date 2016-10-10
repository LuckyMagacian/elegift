package com.lanxi.elegift.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.lanxi.elegift.bean.in.OpenBean;
@Repository
/**
 * Open表dao接口
 * @author 1
 *
 */
public interface DoOpen extends Serializable {
	/**
	 * 根据商品编号获取单价()
	 * @param SerialNum
	 * @return
	 */
	public String getAmtByOpen(OpenBean open);
	
	public String getSpbhsByJgdm(String jgdm);
	
}
