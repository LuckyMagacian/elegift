package com.lanxi.elegift.service;

import java.io.Serializable;
import java.util.List;

import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.in.ReqBean;
import com.lanxi.elegift.bean.in.ResBean;
import com.lanxi.elegift.bean.in.SmsMod;
import com.lanxi.elegift.bean.out.BaoWen;
/**
 * dao操作的接口 以及需要借助dao才能完成的操作的接口
 * @author 1
 *
 */
public interface DaoService extends Serializable {
	/**
	 * 从数据库中取得Operate并生成签名
	 * @param bean
	 * @return
	 * @throws EleGiftException 
	 */
	public ReqBean setOperateAndSign(ReqBean bean) throws EleGiftException;
	/**
	 * 从ResBean与数据库中提取数据封装成List<Sku>
	 * @param bean
	 * @return
	 */
	public void  setAMT(ResBean bean,BaoWen baowen);
	/**
	 * 保存交易信息到数据库
	 * @param baowen
	 * @param bean
	 */
	public void  saveOrderInfo(BaoWen baowen,ResBean res,ReqBean req);
	/**
	 * 查询机构代码是否存在
	 * @param jgdm
	 * @return
	 */
	public boolean  checkJgdm(String jgdm);
	/**
	 * 查询商品代码是否存在
	 * @param spbh
	 * @return
	 */
	public boolean  checkSpbh(String spbh);
	/**
	 * 查询商品代码与对应的机构代码是否存在
	 * @param jgdm
	 * @param spbh
	 * @return
	 */
	public boolean  checkJgdmAndSpbh(String jgdm,String spbh);
	/**
	 * 查询交易序号是否已存在
	 * @param jyxh
	 * @return
	 */
	public int  checkOrder(BaoWen baoWen);  
	/**
	 * 根据机构代码获取机构名称
	 * @param jgdm
	 * @return
	 */
	public String getJgmcByJgdm(String jgdm);
	/**
	 * 根据商品编号获取商品名称
	 * @param spbh
	 * @return
	 */
	public String getSpmcBySpbh(String spbh);
	
	public SmsMod getSmsMod(String branchId,String goodsId);
	public List<SmsMod> getSmsMods(String branchId);
}
