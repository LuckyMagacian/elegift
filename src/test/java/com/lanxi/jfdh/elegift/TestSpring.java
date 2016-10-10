package com.lanxi.jfdh.elegift;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lanxi.elegift.bean.in.OpenBean;
import com.lanxi.elegift.bean.in.OrderInfoBean;
import com.lanxi.elegift.dao.DoBrch;
import com.lanxi.elegift.dao.DoCHN;
import com.lanxi.elegift.dao.DoCOMM;
import com.lanxi.elegift.dao.DoDAEL;
import com.lanxi.elegift.dao.DoOpen;
import com.lanxi.elegift.dao.DoThin;
import com.lanxi.elegift.util.ConfUtil;

public class TestSpring {
	private ApplicationContext ac;
	@Before
	public void init(){
		ac=new ClassPathXmlApplicationContext("xml/spring.xml");
	}
	@Test
	public void testAc(){
		System.out.println("111");
		System.out.println(ac);
	}
	@Test
	public void testDao(){
		DoOpen dao=(DoOpen) ac.getBean("doOpen");
		OpenBean open=new OpenBean();
		open.setJgdm("3301201507170001");
		open.setSpbh("1001");
		System.out.println(dao.getAmtByOpen(open));
	}
	@Test
	public void testConf(){
		System.out.println(ConfUtil.getUrl());
	}
	@Test
	public void testDaoChn(){
		DoCOMM dao1=(DoCOMM) ac.getBean("doCOMM");
		DoCHN dao2=(DoCHN) ac.getBean("doCHN");
		String qddm=dao1.getQdsdmBySpbh("3003");
		System.out.println(qddm);
		System.out.println(dao2.getQdsmcByQdsdm(qddm));
	}
	@Test
	public void testDaoCOMM(){
		DoCOMM dao=(DoCOMM) ac.getBean("doCOMM");
		System.out.println(dao.getQdsdmBySpbh("3088"));
		System.out.println(dao.getCbjBySpbh("111111"));
		System.out.println(dao.getQdsspbhBySpbh("3088"));
		System.out.println(dao.getSpbhByQdsspbh("201603049688"));
	}
	@Test
	public void testDaoDAEL(){
		DoDAEL dao=(DoDAEL)ac.getBean("doDAEL");
		OrderInfoBean someInfo=new OrderInfoBean();
		someInfo.setJyxh("20010001");
		someInfo.setFqjgh("3311111111111115");
		someInfo.setJyrq("20160927");
		System.out.println(dao.getOrderInfoBySomeInfo(someInfo));
	}
	@Test
	public void testDaoThin(){
		DoThin dao=(DoThin) ac.getBean("doThin");
		System.out.println(dao.getSplbBySpbh("5005"));
		System.out.println(dao.getSpmcBySpbh("5005"));
	}
	@Test
	public void testDaoOpen(){
		DoOpen dao=(DoOpen) ac.getBean("doOpen");
		System.out.println(dao.getSpbhsByJgdm("3311111111111110"));
	}
	@Test
	public void testDaoBrch(){
		DoBrch dao=(DoBrch) ac.getBean("doBrch");
		System.out.println(dao.getJgmcByJgdm("3311111111111110"));
	}

}
