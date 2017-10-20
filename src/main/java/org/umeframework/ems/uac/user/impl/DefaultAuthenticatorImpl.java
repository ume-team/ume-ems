/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.uac.user.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.util.CodecUtil;
import org.umeframework.dora.util.DateUtil;
import org.umeframework.ems.uac.entity.UmeUserDto;
import org.umeframework.ems.uac.user.dto.UserAuthDto;

/**
 * Implement user authentication and authorization by default data model.
 * 
 * @author Yue Ma
 */
@Service
public class DefaultAuthenticatorImpl extends BaseAuthenticator {
	/**
	 * sqlFindBizUser
	 */
	private String sqlFindBizUser = "org.umeframework.ems.uac.entity.UME_USER_FIND";

	/**
	 * findBizUser
	 * 
	 * @param loginId
	 * @param loginPassword
	 * @param options
	 * @return
	 */
	public Map<String, Object> findBizUser(String loginId, String loginPassword, String... options) {
		Map<String, Object> bizUser = getDao().queryForMap(this.getSqlFindBizUser(), loginId);
		if (bizUser == null) {
			throw new ApplicationException(UME_AUTH_MSG_001);
		}
		String password = (String) bizUser.get(UmeUserDto.Property.userPassword);

		loginPassword = CodecUtil.encodeMD5Hex(loginPassword);
		if (!loginPassword.equals(password)) {
			throw new ApplicationException(UME_AUTH_MSG_002);
		}
		Integer userStatus = (Integer) bizUser.get(UmeUserDto.Property.userStatus);
		if (0 == userStatus) {
			throw new ApplicationException(UME_AUTH_MSG_003);
		}
		return bizUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.ems.uac.user.impl.BaseAuthenticator#createToken(org.umeframework.ems.uac.user.dto.UserAuthDto)
	 */
	public String createToken(UserAuthDto userAuthDto) {
		// use default token generate rule if no business token provided
		String token = CodecUtil.encodeMD5Hex(DateUtil.dateToString(userAuthDto.getLastTransactionTime(), DateUtil.FORMAT.YYYYMMDDHHMMSSMMM))
		        + System.currentTimeMillis();
		return token;
	}

	/**
	 * @return the sqlFindBizUser
	 */
	public String getSqlFindBizUser() {
		return sqlFindBizUser;
	}

	/**
	 * @param sqlFindBizUser
	 *            the sqlFindBizUser to set
	 */
	public void setSqlFindBizUser(String sqlFindBizUser) {
		this.sqlFindBizUser = sqlFindBizUser;
	}

}
