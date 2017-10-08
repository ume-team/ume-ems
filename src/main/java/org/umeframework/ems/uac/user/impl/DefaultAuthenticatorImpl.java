package org.umeframework.ems.uac.user.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.util.CodecUtil;
import org.umeframework.ems.uac.entity.UmeUserDto;

/**
 * Implement user authentication and authorization by default data model.
 * 
 * @author Yue Ma
 */
@Service
public class DefaultAuthenticatorImpl extends BaseAuthenticator { 
	/**
	 * findBizUser
	 * 
	 * @param loginId
	 * @param loginPassword
	 * @param options
	 * @return
	 */
	public Map<String, Object> findBizUser(String loginId, String loginPassword, String... options) {
		Map<String, Object> bizUser = getDao().queryForMap(super.getSqlFindBizUser(), loginId);
		if (bizUser == null) {
			throw new ApplicationException(M001);
		}
		String password = (String) bizUser.get(UmeUserDto.Property.userPassword);

		loginPassword = CodecUtil.encodeMD5Hex(loginPassword);
		if (!loginPassword.equals(password)) {
			throw new ApplicationException(M002);
		}
		Integer userStatus = (Integer) bizUser.get(UmeUserDto.Property.userStatus);
		if (0 == userStatus) {
			throw new ApplicationException(M003);
		}
		return bizUser;
	}
}
