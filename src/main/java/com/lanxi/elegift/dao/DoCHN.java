package com.lanxi.elegift.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
@Repository
/**
 * CHN表dao接口
 * @author 1
 *
 */
public interface DoCHN extends Serializable {
	/**
	 * 根据渠道商代码获取渠道商名称
	 * @param qdsdm
	 * @return
	 */
	public String getQdsmcByQdsdm(String qdsdm);
}
