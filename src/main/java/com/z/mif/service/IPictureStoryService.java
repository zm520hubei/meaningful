package com.z.mif.service;

import com.z.mif.entity.PictureStoryEObj;

public interface IPictureStoryService extends BaseService<PictureStoryEObj> {

	PictureStoryEObj get(String id) throws Exception;
}
