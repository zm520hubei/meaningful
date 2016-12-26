package com.z.mif.service;

import com.z.mif.entity.NoteEObj;

public interface INoteService extends BaseService<NoteEObj> {

	NoteEObj get(String id) throws Exception;
}
