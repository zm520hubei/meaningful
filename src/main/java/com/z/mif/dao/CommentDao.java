package com.z.mif.dao;

import java.util.List;

import com.z.mif.entity.CommentEObj;

public interface CommentDao extends BaseDao<CommentEObj> {

	List<CommentEObj> recursion(Integer parentId);
	
	Integer Unread(Integer userId);
}
