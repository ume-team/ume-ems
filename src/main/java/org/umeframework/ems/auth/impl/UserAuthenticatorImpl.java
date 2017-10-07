package org.umeframework.ems.auth.impl;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.dora.service.user.UserAuthenticator;
import org.umeframework.ems.auth.dto.UserAuthDto;
import org.umeframework.ems.entity.EmResourceDto;
import org.umeframework.ems.entity.EmRoleAclDto;
import org.umeframework.ems.entity.EmRoleDto;
import org.umeframework.ems.entity.EmUserDto;

/**
 * Implement user authentication and authorization by actual data model.
 * 
 * @author mayue
 */
@Service
public class UserAuthenticatorImpl extends BaseDBComponent implements UserAuthenticator<UserAuthDto> {
    /**
	 * WildCard use in EM_ROLE_ACL.ACC_RES_ID
     */
    private static final String WILDCARD_ACC_RES_ID = "*"; 
    /**
     * keyOfSearchUserACL
     */
    private String keyOfSearchUserACL = "CMT.SEARCH_USER_ACL"; 
    /**
     * keyOfSearchResourcesAsACL
     */
    private String keyOfSearchResourcesAsACL = "CMT.SEARCH_ALL_RESOURCE_AS_USER_ACL"; 

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.com.UserAuthenticator#doAuthentication(java.lang.String, java.lang.String)
	 */
	@Override
	public UserAuthDto getUserObject(String loginId, String loginPassword, String... options) {

		Map<String, Object> bizUser = getDao().queryForMap(EmUserDto.SQLID.FIND, loginId);
		if (bizUser == null) {
			throw new ApplicationException("APMSG00001", new Object[] { loginId });
		}
		String password = (String) bizUser.get(EmUserDto.Property.userPassword);
		if (!loginPassword.equals(password)) {
			throw new ApplicationException("APMSG00002", new Object[] { loginId });
		}
		Integer userStatus = (Integer) bizUser.get(EmUserDto.Property.userStatus);
		if (0 == userStatus) {
			throw new ApplicationException("APMSG00003", new Object[] { loginId });
		}
		bizUser.remove(EmUserDto.Property.userPassword);
		bizUser.remove(EmUserDto.Property.userStatus);
		bizUser.remove(EmUserDto.Property.createAuthor);
		bizUser.remove(EmUserDto.Property.createDatetime);
		bizUser.remove(EmUserDto.Property.updateAuthor);
		bizUser.remove(EmUserDto.Property.updateDatetime);
		UserAuthDto userAuth = new UserAuthDto();
		userAuth.setUser(bizUser);
		String uid = bizUser.get(EmUserDto.Property.userId).toString();
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
		List<Map<String, Object>> accResList = getDao().queryForMapList(keyOfSearchUserACL, userAuth.getUid());

		for (Map<String, Object> e : accResList) {
			String roleId = (String) e.get(EmRoleDto.Property.roleId);
			Integer resType = (Integer) e.get(EmResourceDto.Property.resType);
			String accResId = (String) e.get(EmRoleAclDto.Property.accResId);
			// Integer accLevel = (Integer)e.get(EmRoleAclDTO.Property.accLevel);
			// String resId = (String)e.get(EmResourceDTO.Property.resId);
			// String resName = (String)e.get(EmResourceDTO.Property.resName);
			// String resGroup = (String)e.get(EmResourceDTO.Property.resGroup);
			// Integer resIndex = (Integer)e.get(EmResourceDTO.Property.resIndex);
			// String resLink = (String)e.get(EmResourceDTO.Property.resLink);

			if (accResId.contains("*")) {
				// mark and skip resource which contains '*'(such as 'image*') in 'EM_ROLE_ACL.ACC_RES_ID'
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
				String roleId = (String)value.get(EmRoleAclDto.Property.roleId);
				Integer accLevel = (Integer)value.get(EmRoleAclDto.Property.accLevel);
				accResList = getDao().queryForMapList(keyOfSearchResourcesAsACL, key);
				for (Map<String, Object> e : accResList) {
					e.put(EmRoleDto.Property.roleId, roleId);
					e.put(EmRoleAclDto.Property.accLevel, accLevel);
					this.saveAs(accResMapA, e);
					Integer resType = (Integer) e.get(EmResourceDto.Property.resType);
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
		String accResId = (String) current.get(EmRoleAclDto.Property.accResId);
		Integer accLevel = (Integer) current.get(EmRoleAclDto.Property.accLevel);
		if (!target.containsKey(accResId)) {
			target.put(accResId, current);
		} else {
			Map<String, Object> exist = target.get(accResId);
			// compute new access level and rewrite
			Integer existAccLevel = (Integer) exist.get(EmRoleAclDto.Property.accLevel);
			Integer newAccLevel = caculateAccLevel(existAccLevel, accLevel);
			exist.put(EmRoleAclDto.Property.accLevel, newAccLevel);
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

}
