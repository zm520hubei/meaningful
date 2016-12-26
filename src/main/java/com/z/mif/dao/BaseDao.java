package com.z.mif.dao;

import java.util.List;

public interface BaseDao<T> {

	T get(T user);

	Integer add(T user);

	void update(T user);

	Integer getInsertId();
	
	List<T> list(T t);

}
