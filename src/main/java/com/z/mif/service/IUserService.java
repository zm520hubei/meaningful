package com.z.mif.service;

import com.z.mif.entity.UserEObj;

public interface IUserService extends BaseService<UserEObj>{

	void updatePwd(UserEObj user);
}
