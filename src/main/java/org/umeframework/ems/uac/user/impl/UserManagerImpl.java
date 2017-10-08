/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.uac.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.dora.util.CodecUtil;
import org.umeframework.dora.util.StringUtil;
import org.umeframework.ems.uac.entity.UmeUserDto;
import org.umeframework.ems.uac.entity.crud.UmeUserCrudService;
import org.umeframework.ems.uac.message.MessageConst;
import org.umeframework.ems.uac.user.UserManager;

/**
 * User information management implementation class.<br>
 * 
 * 
 * @author YUE MA
 *
 */
@Service
public class UserManagerImpl extends BaseDBComponent implements UserManager, MessageConst {
	/**
	 * userService
	 */
	@Resource
	UmeUserCrudService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ume.auth.service.UserManager#changePassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	synchronized public void changePassword(String userId, String userPassword, String newPassword) {
		UmeUserDto user = new UmeUserDto();
		user.setUserId(userId);
		UmeUserDto exist = userService.find(user);
		if (exist == null) {
			throw new ApplicationException(M001);
		}
		if (!exist.getUserPassword().equals(CodecUtil.encodeMD5Hex(exist.getUserPassword()))) {
			throw new ApplicationException(M002);
		}
		user.setUserPassword(newPassword);
		userService.update(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ume.auth.service.UserManager#createUser(com.ume.auth.entity.UmeUserDto)
	 */
	@Override
	synchronized public void createUser(UmeUserDto user) {
		String userPassword = user.getUserPassword();
		if (StringUtil.isEmpty(userPassword)) {
			throw new ApplicationException(M002);
		}
		user.setUserPassword(CodecUtil.encodeMD5Hex(userPassword));
		userService.create(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ume.auth.service.UserManager#updateUser(com.ume.auth.entity.UmeUserDto)
	 */
	@Override
	public void updateUser(UmeUserDto user) {
		UmeUserDto param = new UmeUserDto();
		param.setUserId(user.getUserId());
		UmeUserDto exist = userService.find(param);
		if (exist == null) {
			throw new ApplicationException(M001);
		}
		if (!exist.getUserPassword().equals(CodecUtil.encodeMD5Hex(user.getUserPassword()))) {
			throw new ApplicationException(M002);
		}
		userService.update(user);
	}

}
