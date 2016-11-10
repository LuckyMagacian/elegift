package com.lanxi.elegift.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.elegift.bean.in.EleGiftException;
import com.lanxi.elegift.bean.in.Mcht;
import com.lanxi.elegift.bean.in.OrderInfoBean;
import com.lanxi.elegift.bean.in.ReqBean;
import com.lanxi.elegift.bean.in.ResBean;
import com.lanxi.elegift.bean.in.SmsBean;
import com.lanxi.elegift.bean.out.BaoWen;
import com.lanxi.elegift.bean.out.headbean.ReqHead;
import com.lanxi.elegift.bean.out.msgbean.ReqMsg;
import com.lanxi.elegift.service.BuyService;
import com.lanxi.elegift.util.BeanUtil;
import com.lanxi.elegift.util.ConfUtil;
import com.lanxi.elegift.util.HttpUtil;
import com.lanxi.elegift.util.ParseUtil;
import com.lanxi.elegift.util.SignUtil;
/**
 * 一次请求接口实现类
 * @author 1
 *
 */
@Service("buyService")
public class BuyServiceImpl implements BuyService {
	private static Logger logger=Logger.getLogger(BuyServiceImpl.class);
	@Resource
	private DaoServiceImpl daoService;
	
	public void buy(HttpServletRequest req,HttpServletResponse res){
		try {
			req.setCharacterEncoding("GBK");
			res.setCharacterEncoding("GBK");
			int    orderState=-1;
			//获取xml
			String xml=ParseUtil.getXml(req);//OK
			logger.info("请求xml数据包:"+xml);
			//转请求报文bean
			BaoWen baowen=(BaoWen)ParseUtil.toBean(xml);//OK
			ResBean resBean =new ResBean();
			ReqBean reqBean =new ReqBean();
			reqBean=ParseUtil.toReqBean(baowen);//OK
			int checkRs=orderState=check(baowen, resBean);
			if(checkRs!=OrderInfoBean.ORDER_STATE_SUCCESS&&checkRs!=99){
			//转二次请求bean
			logger.info("验证通过");
			try {
				//获取json
				String tempSpbh=reqBean.getmHdbh();
				daoService.setOperateAndSign(reqBean);
				String jStr=HttpUtil.postBean(reqBean);
				reqBean.setmHdbh(tempSpbh);
				logger.info("二次请求结果:"+jStr);
				//转二次响应bean
				resBean=ParseUtil.getResBean(jStr);
			} catch (EleGiftException e) {
					resBean.setRetCode("9999");
					resBean.setRetMsg("系统错误请联系管理员!");
					new EleGiftException("二次请求错误",e);
				}
			}
			//转响应报文bean
			//System.out.println(resBean);
			//System.out.println(orderState);
			
			
			BaoWen resBaowen=ParseUtil.toBaoWen(resBean, baowen);
			if(resBaowen.getMsg()!=null)
				daoService.setAMT(resBean,resBaowen);
			//响应报文签名
			SignUtil.getSignNoSort(resBaowen);
			logger.info("生成响应报文bean:"+resBaowen);
			//存储本次交易信息到数据库
			if(resBean.getMobile()!=null){
				if(orderState==OrderInfoBean.ORDER_STATE_NULL){
					logger.info("存储本次交易信息到数据库");
					daoService.saveOrderInfo(baowen, resBean,reqBean);
				}else{
					logger.info("更新本次交易信息到数据库");
					daoService.updateOrderInfo(baowen, resBean, reqBean);
				}
			}
			//转xml
			String resXml=ParseUtil.toXml(resBaowen);
			logger.info("生成响应xml数据包:"+resXml);
			//System.out.println(resXml);
			//发送响应
			String end=HttpUtil.postXml(resXml,res);
			logger.info("发送响应数据包完成");
			if(((ReqMsg)baowen.getMsg()).getNeedSend().trim().equals("0")&&resBean.getRetCode().equals("0000")){
				logger.info("需要下发短信");
				SmsBean sms=new SmsBean();
				sms.setMchtId("10");
				sms.setMobile(reqBean.getMobile());
				sms.setTradeDate(reqBean.getTradeDate());
				sms.setTradeTime(reqBean.getTradeTime());
				sms.setOrderId(reqBean.getOrderId().replaceFirst("40","10"));
				sms.setTdId("1");
				sms.setContent(getSmsContent(baowen,resBean));	
				logger.info("准备发送短信,内容为:"+sms.getContent());
				SignUtil.signSms(sms);
				String rs=BeanUtil.sendSms(sms);
				if(rs!=null&&((JSONObject)JSONObject.parseObject(rs)).get("retCode").equals("0000"))
				{
					logger.info("短信发送成功");
				}else {
					logger.info("尝试重发短信");
					{
						sms.setTdId("1");
						SignUtil.signSms(sms);
						rs=BeanUtil.sendSms(sms);
						if(rs!=null&&!((JSONObject)JSONObject.parseObject(rs)).get("retCode").equals("0000")){
							sms.setTdId("2");
							SignUtil.signSms(sms);
							rs=BeanUtil.sendSms(sms);
						}
						if(rs!=null&&((JSONObject)JSONObject.parseObject(rs)).get("retCode").equals("0000"))
						{
							logger.info("短信发送成功");
						}else {
							new EleGiftException("短信发送失败"+rs);
						}
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			new EleGiftException("响应过程中发生错误",e);
		} catch (EleGiftException e) {
			new EleGiftException("响应过程中发生错误",e);
		}
	}
	/**
	 * 报文内容校验
	 * @param baowen
	 * @param resBean
	 * @return
	 * @throws EleGiftException
	 */
	private int check(BaoWen baowen,ResBean resBean){
		int state=99;
		if(baowen==null)
			new EleGiftException("校验时报文为空");
		else
			try {
				if(!SignUtil.checkSign(baowen)){
					resBean.setRetCode("5001");
					resBean.setRetMsg("签名验证失败");
					new EleGiftException("签名验证失败");//记录日志
				}else if(!daoService.checkJgdm(((ReqHead)(baowen.getHead())).getSRC())){
					//验证交易发起机构
					resBean.setRetCode("9999");
					resBean.setRetMsg("交易发起方未注册");
					new EleGiftException("交易发起方未注册");//记录日志
				}else if (!daoService.checkSpbh(((ReqMsg)baowen.getMsg()).getSkuCode())) {
					//验证商品编号
					resBean.setRetCode("9999");
					resBean.setRetMsg("商品编号不存在");
					new EleGiftException("商品编号不存在");//记录日志
				}else if (!daoService.checkJgdmAndSpbh(((ReqHead)(baowen.getHead())).getSRC(),((ReqMsg)baowen.getMsg()).getSkuCode() )) {
					//验证交易发起机构及商品编号
					resBean.setRetCode("9999");
					resBean.setRetMsg("未查询到交易发起机构号对应的商品编号");
					new EleGiftException("未查询到交易发起机构号对应的商品编号");//记录日志
				}else if((state=daoService.checkOrder(baowen))==OrderInfoBean.ORDER_STATE_SUCCESS){
						//验证交易序号
						resBean.setRetCode("9999");
						resBean.setRetMsg("订单已存在且交易成功");
						//System.out.println(resBean);
						new EleGiftException("订单已存在且交易成功");//记录日志
				}
			} catch (EleGiftException e) {
				resBean.setRetCode("9999");
				resBean.setRetMsg(e.getMessage());
				new EleGiftException("验证失败",e);
			}
		return state;
	}
	
	private String getSmsContent(BaoWen baoWen,ResBean res){
		StringBuilder content=new StringBuilder();
		String jgdm=((ReqHead)baoWen.getHead()).getSRC();
		String spbh=((ReqMsg)baoWen.getMsg()).getSkuCode();
		String jgmc=daoService.getJgmcByJgdm(jgdm);
		String spmc=daoService.getSpmcBySpbh(spbh);
		String count=((ReqMsg)baoWen.getMsg()).getCount();
		String smsTemplate=null;
		if(jgmc.trim().equals("华夏银行")){
			if(spmc.contains("滴滴"))
				smsTemplate=ConfUtil.get(jgmc+2);
			else if(spmc.contains("惠多多"))
				smsTemplate=ConfUtil.get(jgmc+3);
			else if(spmc.contains("爱奇艺"))
				smsTemplate=ConfUtil.get(jgmc+4);
		}
		else
			smsTemplate=ConfUtil.get(jgmc);
		String[] params=smsTemplate.split("\\|{2}");
		content.append(params[0]).append(spmc);
		// TODO 统一
		if(Integer.parseInt(count)>0){
			content.append(count+"份，串码:");
			List<Mcht> tempList=res.getObject();
			for(int i=0;i<tempList.size();i++){
				content.append(tempList.get(i).getCode());
				if(i<tempList.size()-1)
					content.append("、");
			}
			content.append("有效期至:"+tempList.get(0).getEndTime());
		}
		else {
			content.append(params[1]);
			content.append(res.getObject().get(0).getCode());
			content.append(params[2]);
			content.append(res.getObject().get(0).getEndTime());
		}
		content.append(params[params.length-1]);
		return content.toString();
	}
}
