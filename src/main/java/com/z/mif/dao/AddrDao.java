package com.z.mif.dao;

import java.util.List;

import com.z.mif.entity.AddrEObj;


public interface AddrDao extends BaseDao<AddrEObj>{

	List<AddrEObj> list(AddrEObj entity);
}
