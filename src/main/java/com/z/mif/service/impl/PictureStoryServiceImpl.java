package com.z.mif.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.z.mif.PageContext.PageContext;
import com.z.mif.dao.PictureStoryDao;
import com.z.mif.entity.Pagination;
import com.z.mif.entity.PictureStoryEObj;
import com.z.mif.service.IPictureStoryService;

@Service("pictureStoryService")
@Transactional
public class PictureStoryServiceImpl implements IPictureStoryService {

	@Autowired
	private PictureStoryDao pictureStoryDao;
	
	public PictureStoryEObj get(PictureStoryEObj entity) throws Exception {
		return pictureStoryDao.get(entity);
	}

	public PictureStoryEObj get(String id) throws Exception {
		PictureStoryEObj entity = new PictureStoryEObj();
		entity.setId(id);
		return pictureStoryDao.get(entity);
	}

	public Integer add(PictureStoryEObj entity) throws Exception {
		pictureStoryDao.add(entity);
		return null;
	}

	public void update(PictureStoryEObj entity) throws Exception {
		pictureStoryDao.update(entity);
	}

	public Pagination list(PictureStoryEObj entity,Pagination paging)
			throws Exception {
		PageContext.push(paging);
		paging.setDatas(pictureStoryDao.list(entity));
		PageContext.unload();
		return paging;
	}

	public List<PictureStoryEObj> list(PictureStoryEObj t) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Integer isUnId(String id) {
		return pictureStoryDao.isUnId(id);
	}

	@Override
	public PictureStoryEObj get(Integer id) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}
}
