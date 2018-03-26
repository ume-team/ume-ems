/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.uac.user.impl;

import org.springframework.stereotype.Service;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.util.CodecUtil;
import org.umeframework.ems.uac.entity.UmeUserDto;

/**
 * 默认的用户鉴权实现类。<br>
 * 
 * @author Yue Ma
 */
@Service
public class DefaultAuthenticatorImpl extends BaseAuthenticator<UmeUserDto> {
	
	/**
	 * 根据loginId，loginPassword等获取业务用户对象实体并返回。<br>
	 * 
	 * @param loginId
	 * @param loginPassword
	 * @param options
	 * @return
	 */
	@Override
	public UmeUserDto findBizUser(String loginId, String loginPassword, String... options) {
		UmeUserDto bizUser = getDao().queryForObject(UmeUserDto.SQLID.FIND, loginId, UmeUserDto.class);
		if (bizUser == null) {
			throw new ApplicationException(UME_EMS_UAC_MSG_001);
		}
		String password = bizUser.getUserPassword();

		loginPassword = CodecUtil.encodeMD5Hex(loginPassword);
		if (!loginPassword.equals(password)) {
			throw new ApplicationException(UME_EMS_UAC_MSG_002);
		}
		Integer userStatus = (Integer) bizUser.getUserStatus();
		if (0 == userStatus) {
			throw new ApplicationException(UME_EMS_UAC_MSG_003);
		}
		return bizUser;
	}

}
