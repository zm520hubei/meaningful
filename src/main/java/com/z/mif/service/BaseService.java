package com.z.mif.service;

import java.util.List;

import com.z.mif.entity.Pagination;



public interface BaseService<T> {

	T get(T t) throws Exception;
	
	T get(Integer id) throws Exception;
	
	Integer add(T t) throws Exception;
	
	void update(T t) throws Exception;
	
	Pagination list(T t,Pagination paging) throws Exception;
	
	List<T> list(T t) throws Exception;
	
	Integer isUnId(String id);
}
