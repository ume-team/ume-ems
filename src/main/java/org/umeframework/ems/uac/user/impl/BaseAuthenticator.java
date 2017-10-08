/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.uac.user.impl;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.umeframework.dora.service.BaseDBComponent;
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
	 * sqlFindBizUser
	 */
	private String sqlFindBizUser = "org.umeframework.ems.uac.entity.UME_USER_FIND";
	/**
	 * keyUID
	 */
	private String keyUID = "userId";
	
	/**
	 * findBizUser
	 * 
	 * @param loginId
	 * @param loginPassword
	 * @param options
	 * @return
	 */
	abstract public Map<String, Object> findBizUser(String loginId, String loginPassword, String... options);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.com.UserAuthenticator#doAuthentication(java.lang.String, java.lang.String)
	 */
	@Override
	public UserAuthDto getUserObject(String loginId, String loginPassword, String... options) {
		Map<String, Object> bizUser = findBizUser(loginId, loginPassword, options);
		bizUser.remove(UmeUserDto.Property.userPassword);
		bizUser.remove(UmeUserDto.Property.userStatus);
		bizUser.remove(UmeUserDto.Property.createAuthor);
		bizUser.remove(UmeUserDto.Property.createDatetime);
		bizUser.remove(UmeUserDto.Property.updateAuthor);
		bizUser.remove(UmeUserDto.Property.updateDatetime);
		UserAuthDto userAuth = new UserAuthDto();
		userAuth.setUser(bizUser);
		String uid = bizUser.get(keyUID).toString();
		userAuth.setUid(uid);

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
					//e.put(UmeRoleAclDto.Property.accResId, e.get(UmeResourceDto.Property.resId));
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
	 * @param sqlFindUserACL the sqlFindUserACL to set
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
	 * @param sqlFindUserDefaultACL the sqlFindUserDefaultACL to set
	 */
	public void setSqlFindUserDefaultACL(String sqlFindUserDefaultACL) {
		this.sqlFindUserDefaultACL = sqlFindUserDefaultACL;
	}

	/**
	 * @return the sqlFindBizUser
	 */
	public String getSqlFindBizUser() {
		return sqlFindBizUser;
	}

	/**
	 * @param sqlFindBizUser the sqlFindBizUser to set
	 */
	public void setSqlFindBizUser(String sqlFindBizUser) {
		this.sqlFindBizUser = sqlFindBizUser;
	}



}
