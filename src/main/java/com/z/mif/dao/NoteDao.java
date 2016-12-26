package com.z.mif.dao;

import java.util.List;

import com.z.mif.entity.NoteEObj;

public interface NoteDao extends BaseDao<NoteEObj>{
	
	NoteEObj get(NoteEObj entity);
	
	Integer add(NoteEObj entity);
	
	void update(NoteEObj entity);
	
	Integer getInsertId();
	
	List<NoteEObj> list(NoteEObj entity);
	
	Integer isUnId(String id);

}
