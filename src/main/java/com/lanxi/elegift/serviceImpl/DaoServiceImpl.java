package com.lanxi.elegift.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.in.Mcht;
import com.lanxi.elegift.bean.in.OpenBean;
import com.lanxi.elegift.bean.in.OrderInfoBean;
import com.lanxi.elegift.bean.in.ReqBean;
import com.lanxi.elegift.bean.in.ResBean;
import com.lanxi.elegift.bean.out.BaoWen;
import com.lanxi.elegift.bean.out.Sku;
import com.lanxi.elegift.bean.out.headbean.ReqHead;
import com.lanxi.elegift.bean.out.headbean.ResHead;
import com.lanxi.elegift.bean.out.msgbean.ReqMsg;
import com.lanxi.elegift.bean.out.msgbean.ResMsg;
import com.lanxi.elegift.dao.DoBrch;
import com.lanxi.elegift.dao.DoCHN;
import com.lanxi.elegift.dao.DoCOMM;
import com.lanxi.elegift.dao.DoDAEL;
import com.lanxi.elegift.dao.DoOpen;
import com.lanxi.elegift.dao.DoThin;
import com.lanxi.elegift.service.DaoService;
import com.lanxi.elegift.util.ParseUtil;
import com.lanxi.elegift.util.SignUtil;
@Service
/**
 * 因为部分数据需要从数据库中取得,不适合放在静态类中,故封装为一个daoService中的方法
 * @author 1
 *
 */
public class DaoServiceImpl implements DaoService {
	@Resource
	private DoOpen doOpen;
	@Resource
	private DoCOMM doComm;
	@Resource
	private DoCHN  doCHN;
	@Resource
	private DoDAEL doDael;
	@Resource
	private DoThin doThin;
	@Resource
	private DoBrch doBrch;
	/**
	 * 从数据库中取得Operate并生成签名
	 * @param bean
	 * @return
	 * @throws EleGiftException 
	 */
	public ReqBean setOperateAndSign(ReqBean bean) throws EleGiftException{
		//System.out.println(bean);
		String qdsdm=doComm.getQdsdmBySpbh(bean.getmHdbh());
		bean.setmHdbh(getQdsspbhBySpbh(bean.getmHdbh()));
		switch(qdsdm){
			case "10003":bean.setOperate("5001");break;
			case "10010":bean.setOperate("4001");break;
			case "10014":bean.setOperate("3001");break;
			case "10013":bean.setOperate("2001");break;
			case "10011":bean.setOperate("1001");break;
			default:throw new EleGiftException("渠道商不存在或不是电子券类商品");
		}
		bean.setSign(SignUtil.getSign(bean));
		//System.out.println(bean);
		return bean;
	}
	/**
	 * 从ResBean与返回报文中提取数据 用于完成返回报文中msg的封装
	 * @param bean
	 * @return
	 */
	public void setAMT(ResBean bean,BaoWen baowen){
		
		List<Sku> rs=new ArrayList<Sku>();
		
		ResMsg msg=(ResMsg) baowen.getMsg();
		ResHead head=(ResHead) baowen.getHead();
		
		double total=0.0;
		if(bean.getObject()!=null)
		for(Mcht each:bean.getObject()){
			Sku temp=new Sku();
			
			OpenBean tempOpen=new OpenBean();
			tempOpen.setJgdm(head.getSRC());
			tempOpen.setSpbh(bean.getmHdbh());
			if(doOpen.getAmtByOpen(tempOpen)==null){
				tempOpen.setSpbh(doComm.getSpbhByQdsspbh(tempOpen.getSpbh()));
			}
			temp.setAmt(doOpen.getAmtByOpen(tempOpen));
			total+=Double.parseDouble(temp.getAmt());
			temp.setCode(each.getCode());
			temp.setEndTime(each.getEndTime());
			rs.add(temp);
		}
		msg.setSkuList(rs);
		msg.setTotalAmt(""+total);
	}
	/**
	 * 保存交易信息到数据库
	 */
	public void saveOrderInfo(BaoWen baowen,ResBean res,ReqBean req){
		doDael.add(getOrder(baowen, res, req));
	}
	public void updateOrderInfo(BaoWen baowen,ResBean res,ReqBean req){
		doDael.update(getOrder(baowen, res, req));
	}
	/**
	 * 从原有信息中提取订单信息
	 * @param baowen
	 * @param res
	 * @param req
	 * @return
	 */
	private OrderInfoBean getOrder(BaoWen baowen,ResBean res,ReqBean req){
		OrderInfoBean order=new OrderInfoBean();
		
		ReqHead head=(ReqHead) baowen.getHead();
		ReqMsg  msg =(ReqMsg) baowen.getMsg();
		
		order.setBwbh(head.getMsgNo());  
		order.setQdbh(doComm.getQdsdmBySpbh(msg.getSkuCode()));
		order.setFqjgh(head.getSRC());
		order.setQsrq(head.getCHKDate());
		order.setJyxh(head.getMsgID());
		order.setJsjgh(head.getDES());
		order.setDqh(head.getADD());
		order.setJyrq(head.getWorkDate());
		order.setJysj(head.getWorkTime());
		order.setSjhm(req.getMobile());
		order.setYhxm(null);
		order.setSplx(msg.getType());
		order.setSpbh(req.getmHdbh());
		order.setSpsl(msg.getCount());
		order.setPtlsh(req.getOrderId());
		order.setXym(res.getRetCode());
		order.setXyxx(res.getRetMsg());
		if(order.getXym().equals("0000")){
			order.setQdfse(Double.parseDouble(doComm.getCbjBySpbh(req.getmHdbh()))*Double.parseDouble(order.getSpsl()));
			order.setJgfse(Double.parseDouble(doOpen.getAmtByOpen(ParseUtil.toOpen(order.getSpbh(), order.getFqjgh())))*Double.parseDouble(order.getSpsl()));
			StringBuilder tempStr=new StringBuilder();
			List<Mcht>    tempList=res.getObject();
			for(int i=0;i<tempList.size();i++){
				tempStr.append(tempList.get(i).getCode());
				if(i<tempList.size()-1)
					tempStr.append("、");
			}
			if(tempStr.length()<150)
				order.setDxcm(tempStr.toString());
			else{
				order.setDxcm("串码内容过长,尚未处理~");
				new EleGiftException("串码内容过长,无法存储到短信串码中");
			}
			order.setJyzt("01");
			order.setDzzt("0");
		}else{
			order.setJyzt("17");
		}
		return order;
	}
	/**
	 * 验证机构号是否存在
	 */
	public boolean checkJgdm(String jgdm) {
		if(doOpen.getSpbhsByJgdm(jgdm)!=null)
			return true;
		return false;
	}
	/**
	 * 验证商品编号是否存在
	 */
	public boolean checkSpbh(String spbh) {
		if(spbh==null)
			return false;
		if(doThin.getSplbBySpbh(spbh)!=null)
			return true;
		return false;
	}
	/**
	 * 根据渠道商商品编号获取商品编号
	 */
	public String getSpbhByQdsspbh(String qdsspbh){
		return doComm.getSpbhByQdsspbh(qdsspbh);
	}
	/**
	 * 验证机构代码及对应的商品编号 是否存在
	 */
	public boolean checkJgdmAndSpbh(String jgdm, String spbh) {
		if(spbh==null||jgdm==null)
			return false;
		if(doOpen.getAmtByOpen(ParseUtil.toOpen(spbh, jgdm))!=null)
			return true;
		return false;
	}
	/**
	 * 验证交易序号是否已存在
	 */
	@Override
	public int checkOrder(BaoWen baowen) {
		OrderInfoBean some=new OrderInfoBean();
		ReqHead head=(ReqHead) baowen.getHead();
		some.setJyxh(head.getMsgID());
		some.setJyrq(head.getWorkDate());
		some.setFqjgh(head.getSRC());
		OrderInfoBean detail=doDael.getOrderInfoBySomeInfo(some);
		if(detail==null)
			return OrderInfoBean.ORDER_STATE_NULL;
		if(detail.getJyzt().equals("16")||detail.getJyzt().equals("17"))
			return OrderInfoBean.ORDER_STATE_FAIL;
		return OrderInfoBean.ORDER_STATE_SUCCESS;
	}

	/**
	 * 根据商品编号获取渠道商商品编号,若存在多个取成本价最低的一个
	 * @param spbh
	 * @return
	 */
	public String getQdsspbhBySpbh(String spbh){
		return doComm.getQdsspbhBySpbh(spbh);
	}
	/**
	 * 根据机构代码获取机构名称
	 * @param jgdm
	 * @return
	 */
	public String getJgmcByJgdm(String jgdm){
		return doBrch.getJgmcByJgdm(jgdm);
	}
	@Override
	public String getSpmcBySpbh(String spbh) {
		return doThin.getSpmcBySpbh(spbh);
	}
	
}
