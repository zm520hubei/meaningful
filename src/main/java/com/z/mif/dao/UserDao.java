package com.z.mif.dao;

import com.z.mif.entity.UserEObj;


public interface UserDao extends BaseDao<UserEObj>{

	UserEObj get(UserEObj entity);
	
	Integer add(UserEObj entity);
	
	void update(UserEObj entity);
	
	Integer getInsertId();
	
	void updatePwd(UserEObj entity);
}
