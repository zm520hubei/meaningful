package com.z.mif.dao;

import java.util.List;

import com.z.mif.entity.PictureStoryEObj;

public interface PictureStoryDao extends BaseDao<PictureStoryEObj>{
	
	PictureStoryEObj get(PictureStoryEObj entity);
	
	Integer add(PictureStoryEObj entity);
	
	void update(PictureStoryEObj entity);
	
	Integer getInsertId();
	
	List<PictureStoryEObj> list(PictureStoryEObj entity);
	
	Integer isUnId(String id);

}
