package com.z.mif.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.z.mif.dao.AddrDao;
import com.z.mif.entity.AddrEObj;
import com.z.mif.entity.Pagination;
import com.z.mif.service.IAddrService;

@Service("addrService")
@Transactional
public class AddrServiceImpl implements IAddrService {

	@Autowired
	private AddrDao addrDAO;
	
	public List<AddrEObj> list(AddrEObj entity) throws Exception {
		return addrDAO.list(entity);
	}

	public AddrEObj get(AddrEObj t) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	public AddrEObj get(Integer id) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	public Integer add(AddrEObj t) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	public void update(AddrEObj t) throws Exception {
		// TODO 自动生成的方法存根

	}

	public Pagination list(AddrEObj t, Pagination paging) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Integer isUnId(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

}
