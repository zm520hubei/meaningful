package com.z.mif.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.z.mif.dao.CommentDao;
import com.z.mif.entity.CommentEObj;
import com.z.mif.entity.Pagination;
import com.z.mif.service.ICommentService;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<CommentEObj> recursion(Integer parentId) {
		return commentDao.recursion(parentId);
	}

	@Override
	public CommentEObj get(CommentEObj t) throws Exception {
		return commentDao.get(t);
	}

	@Override
	public CommentEObj get(Integer id) throws Exception {
		CommentEObj c = new CommentEObj();
		c.setId(id);
		return this.get(c);
	}

	@Override
	public Integer add(CommentEObj t) throws Exception {
		// TODO 自动生成的方法存根
		return commentDao.add(t);
	}

	@Override
	public Integer Unread(Integer userId) {
		return commentDao.Unread(userId);
	}

	@Override
	public void update(CommentEObj t) throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public Pagination list(CommentEObj t, Pagination paging) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<CommentEObj> list(CommentEObj t) throws Exception {
		// TODO 自动生成的方法存根
		return commentDao.list(t);
	}

	@Override
	public Integer isUnId(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

}
