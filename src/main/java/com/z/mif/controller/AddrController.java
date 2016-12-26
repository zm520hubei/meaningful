package com.z.mif.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z.mif.entity.AddrEObj;
import com.z.mif.service.IAddrService;
import com.z.mif.util.JSONUtil;


@Controller
@RequestMapping("/addr")
public class AddrController  extends BaseController{

	@Autowired
	@Qualifier("addrService")
	private IAddrService addrService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody String list(AddrEObj addr) throws Exception{
		List<AddrEObj> addrs = addrService.list(addr);
		String json = JSONUtil.uncode(addrs);
		return json;
	}
	
}
