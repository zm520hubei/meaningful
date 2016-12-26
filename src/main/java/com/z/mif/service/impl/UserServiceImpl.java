package com.z.mif.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.z.mif.dao.UserDao;
import com.z.mif.entity.Pagination;
import com.z.mif.entity.UserEObj;
import com.z.mif.service.IUserService;

@Service("userService")
@Transactional
public  class UserServiceImpl implements IUserService {

	@Autowired
    private UserDao userDAO;
	
	public UserEObj get(UserEObj user) throws Exception{
		return userDAO.get(user);
	}
	
	public UserEObj get(Integer id) throws Exception{
		UserEObj u = new UserEObj();
		u.setId(id);
		return userDAO.get(u);
	}
	
	public Integer add(UserEObj user) throws Exception {
		userDAO.add(user);
		return userDAO.getInsertId();
	}

	public void update(UserEObj user) throws Exception{
		userDAO.update(user);
	}

	public Pagination list(UserEObj t,Pagination paging) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}
	public List<UserEObj> list(UserEObj user) throws Exception {
		return userDAO.list(user);
	}
	
	public void updatePwd(UserEObj user) {
		userDAO.updatePwd(user);
	}

	@Override
	public Integer isUnId(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

}
