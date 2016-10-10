package com.lanxi.elegift.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
/**
 * COMM表dao接口
 * @author 1
 *
 */
public interface DoCOMM extends Serializable {
	/**
	 * 根据商品编号获取渠道商代码(成本价最低)
	 * @param spbh
	 * @return
	 */
	public String getQdsdmBySpbh(String spbh);
	/**
	 * 根据商品编号获取成本价
	 * @param spbh
	 * @return
	 */
	public String getCbjBySpbh(String spbh);
	/**
	 * 根据商品名称获取所有渠道商代码
	 * @param spbh
	 * @return
	 */
	public List<String> getQdsdmsBySpbh(String spbh);
	/**
	 * 根据渠道商商品编号获取商品编号
	 * @param qdsspbh
	 * @return
	 */
	public String getSpbhByQdsspbh(String qdsspbh);
	/**
	 * 根据商品编号获取渠道商商品编号,若存在多个取成本价最低的一个
	 * @param spbh
	 * @return
	 */
	public String getQdsspbhBySpbh(String spbh);
}
