package com.z.mif.service;

import java.util.List;

import com.z.mif.entity.CommentEObj;

public interface ICommentService extends BaseService<CommentEObj> {

	List<CommentEObj> recursion(Integer parentId);
	
	Integer Unread(Integer userId);
}
