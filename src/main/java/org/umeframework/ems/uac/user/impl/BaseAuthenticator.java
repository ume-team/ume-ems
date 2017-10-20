/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.uac.user.impl;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.umeframework.dora.bean.BeanUtil;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.dora.service.TableEntity;
import org.umeframework.dora.service.user.UserAuthenticator;
import org.umeframework.ems.uac.entity.UmeResourceDto;
import org.umeframework.ems.uac.entity.UmeRoleAclDto;
import org.umeframework.ems.uac.entity.UmeRoleDto;
import org.umeframework.ems.uac.entity.UmeUserDto;
import org.umeframework.ems.uac.message.MessageConst;
import org.umeframework.ems.uac.user.dto.UserAuthDto;

/**
 * Implement base user authentication and authorization logic.<br>
 * 
 * @author Yue Ma
 */
public abstract class BaseAuthenticator extends BaseDBComponent implements UserAuthenticator<UserAuthDto>, MessageConst {
	/**
	 * WildCard use in UME_ROLE_ACL.ACC_RES_ID
	 */
	private static final String WILDCARD_ACC_RES_ID = "*";
	/**
	 * sqlFindUserACL
	 */
	private String sqlFindUserACL = "org.umeframework.ems.uac.entity.SEARCH_USER_ACL";
	/**
	 * sqlFindUserDefaultACL
	 */
	private String sqlFindUserDefaultACL = "org.umeframework.ems.uac.entity.SEARCH_ALL_RESOURCE_AS_USER_ACL";
	/**
	 * keyUID
	 */
	private String keyUID = UmeUserDto.Property.userId;

	/**
	 * findBizUser
	 * 
	 * @param loginId
	 * @param loginPassword
	 * @param options
	 * @return
	 */
	abstract public Object findBizUser(String loginId, String loginPassword, String... options);

	/**
	 * createToken
	 * 
	 * @param userAuthDto
	 * @return
	 */
	abstract public String createToken(UserAuthDto userAuthDto);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.com.UserAuthenticator#doAuthentication(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UserAuthDto getUserObject(String loginId, String loginPassword, String... options) {
		Object bizUser = findBizUser(loginId, loginPassword, options);
		Map<String, Object> bizUserMap = null;

		if (bizUser instanceof Map) {
			bizUserMap = (Map<String, Object>) bizUser;
			if (bizUserMap.containsKey(UmeUserDto.Property.userPassword)) {
				bizUserMap.remove(UmeUserDto.Property.userPassword);
			}
			if (bizUserMap.containsKey(UmeUserDto.Property.userStatus)) {
				bizUserMap.remove(UmeUserDto.Property.userStatus);
			}
			if (bizUserMap.containsKey(UmeUserDto.Property.createAuthor)) {
				bizUserMap.remove(UmeUserDto.Property.createAuthor);
			}
			if (bizUserMap.containsKey(UmeUserDto.Property.createDatetime)) {
				bizUserMap.remove(UmeUserDto.Property.createDatetime);
			}
			if (bizUserMap.containsKey(UmeUserDto.Property.updateAuthor)) {
				bizUserMap.remove(UmeUserDto.Property.updateAuthor);
			}
			if (bizUserMap.containsKey(UmeUserDto.Property.updateDatetime)) {
				bizUserMap.remove(UmeUserDto.Property.updateDatetime);
			}
		} else if (bizUser instanceof TableEntity) {
			((TableEntity) bizUser).clearDefaultProperties();
			try {
				BeanUtil.setBeanProperty(bizUser, UmeUserDto.Property.userPassword, null);
			} catch (Exception e) {
				// Ignore set exception here
			}
		}
		// Prepare return instance
		UserAuthDto userAuth = new UserAuthDto();
		userAuth.setUser(bizUser);
		Object uid = null;
		try {
			uid = BeanUtil.getBeanProperty(bizUser, this.getKeyUID());
		} catch (Exception e) {
			// Ignore set exception here
		}
		if (uid != null) {
			userAuth.setUid(uid.toString());
		}
		String token = this.createToken(userAuth);
		userAuth.setToken(token);

		// Invoke authorization process
		this.doAuthorization(userAuth);
		return userAuth;
	}

	/**
	 * doAuthorization
	 * 
	 * @param userAuth
	 * @param options
	 */
	protected void doAuthorization(UserAuthDto userAuth, String... options) {
		// prepare
		Set<String> roleList = new LinkedHashSet<String>();
		Set<Integer> accResTypeList = new LinkedHashSet<Integer>();
		Map<String, Map<String, Object>> accResMapA = new LinkedHashMap<String, Map<String, Object>>();
		Map<String, Map<String, Object>> accResMapB = new LinkedHashMap<String, Map<String, Object>>();

		// Query user accessible resource list by UID
		List<Map<String, Object>> accResList = getDao().queryForMapList(sqlFindUserACL, userAuth.getUid());

		for (Map<String, Object> e : accResList) {
			String roleId = (String) e.get(UmeRoleDto.Property.roleId);
			Integer resType = (Integer) e.get(UmeResourceDto.Property.resType);
			String accResId = (String) e.get(UmeRoleAclDto.Property.accResId);

			if (accResId.contains("*")) {
				// mark and skip resource which contains '*'(such as 'image*') in 'UME_ROLE_ACL.ACC_RES_ID'
				this.saveAs(accResMapB, e);
				continue;
			}
			this.saveAs(accResMapA, e);
			roleList.add(roleId);
			accResTypeList.add(resType);
		}

		if (accResMapB.size() > 0) {
			for (Map.Entry<String, Map<String, Object>> entry : accResMapB.entrySet()) {
				String key = entry.getKey().replace(WILDCARD_ACC_RES_ID, "");
				Map<String, Object> value = entry.getValue();
				String roleId = (String) value.get(UmeRoleAclDto.Property.roleId);
				Integer accLevel = (Integer) value.get(UmeRoleAclDto.Property.accLevel);
				accResList = getDao().queryForMapList(sqlFindUserDefaultACL, key);
				for (Map<String, Object> e : accResList) {
					e.put(UmeRoleDto.Property.roleId, roleId);
					e.put(UmeRoleAclDto.Property.accLevel, accLevel);
					// e.put(UmeRoleAclDto.Property.accResId, e.get(UmeResourceDto.Property.resId));
					this.saveAs(accResMapA, e);
					Integer resType = (Integer) e.get(UmeResourceDto.Property.resType);
					accResTypeList.add(resType);
				}
			}
		}

		Set<Map<String, Object>> allUserAclList = new LinkedHashSet<Map<String, Object>>(accResMapA.values());
		userAuth.setAccResList(allUserAclList);
		userAuth.setRoleList(roleList);
		userAuth.setAccResTypeList(accResTypeList);
		// CollectionUtil.sortAsc(user.getUserAclSet());

	}

	/**
	 * saveAs
	 * 
	 * @param target
	 * @param current
	 */
	protected void saveAs(Map<String, Map<String, Object>> target, Map<String, Object> current) {
		String accResId = (String) current.get(UmeRoleAclDto.Property.accResId);
		Integer accLevel = (Integer) current.get(UmeRoleAclDto.Property.accLevel);
		if (!target.containsKey(accResId)) {
			target.put(accResId, current);
		} else {
			Map<String, Object> exist = target.get(accResId);
			// compute new access level and rewrite
			Integer existAccLevel = (Integer) exist.get(UmeRoleAclDto.Property.accLevel);
			Integer newAccLevel = caculateAccLevel(existAccLevel, accLevel);
			exist.put(UmeRoleAclDto.Property.accLevel, newAccLevel);
		}
	}

	/**
	 * Calculate access level
	 * 
	 * @param existLevel
	 * @param currentLevel
	 * @return
	 */
	protected Integer caculateAccLevel(Integer existLevel, int currentLevel) {
		existLevel = existLevel | currentLevel;
		return existLevel;
	}

	/**
	 * @return the sqlFindUserACL
	 */
	public String getSqlFindUserACL() {
		return sqlFindUserACL;
	}

	/**
	 * @param sqlFindUserACL
	 *            the sqlFindUserACL to set
	 */
	public void setSqlFindUserACL(String sqlFindUserACL) {
		this.sqlFindUserACL = sqlFindUserACL;
	}

	/**
	 * @return the sqlFindUserDefaultACL
	 */
	public String getSqlFindUserDefaultACL() {
		return sqlFindUserDefaultACL;
	}

	/**
	 * @param sqlFindUserDefaultACL
	 *            the sqlFindUserDefaultACL to set
	 */
	public void setSqlFindUserDefaultACL(String sqlFindUserDefaultACL) {
		this.sqlFindUserDefaultACL = sqlFindUserDefaultACL;
	}

	/**
	 * @return the keyUID
	 */
	public String getKeyUID() {
		return keyUID;
	}

	/**
	 * @param keyUID
	 *            the keyUID to set
	 */
	public void setKeyUID(String keyUID) {
		this.keyUID = keyUID;
	}

}
