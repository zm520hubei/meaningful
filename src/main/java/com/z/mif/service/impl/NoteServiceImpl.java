package com.z.mif.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.z.mif.PageContext.PageContext;
import com.z.mif.dao.NoteDao;
import com.z.mif.entity.NoteEObj;
import com.z.mif.entity.Pagination;
import com.z.mif.service.INoteService;

@Service("noteService")
@Transactional
public class NoteServiceImpl implements INoteService {

	@Autowired
	private NoteDao noteDao;
	
	@Override
	public NoteEObj get(NoteEObj t) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public NoteEObj get(Integer id) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Integer add(NoteEObj t) throws Exception {
		return noteDao.add(t);
	}

	@Override
	public void update(NoteEObj t) throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public Pagination list(NoteEObj t, Pagination paging) throws Exception {
		PageContext.push(paging);
		paging.setDatas(noteDao.list(t));
		PageContext.unload();
		return paging;
	}

	@Override
	public List<NoteEObj> list(NoteEObj t) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Integer isUnId(String id) {
		return noteDao.isUnId(id);
	}

	@Override
	public NoteEObj get(String id) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

}
