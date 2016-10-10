package com.lanxi.elegift.controller;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lanxi.elegift.service.BuyService;
@Controller
public class OutController implements Serializable {
	@Resource
	private BuyService buyService;
	@RequestMapping(value="/buy.do",method = RequestMethod.POST, produces = { "application/xml;charset=GBK" })
	private void buy(HttpServletRequest req,HttpServletResponse res){
		buyService.buy(req, res);
	}
}
