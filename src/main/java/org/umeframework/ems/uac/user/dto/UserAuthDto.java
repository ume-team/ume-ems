/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.uac.user.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.umeframework.dora.service.UserEntity;

/**
 * Authenticate user and accessible resource list.<br>
 * 
 * @author mayue
 *
 */
public class UserAuthDto extends UserEntity implements Serializable {
	/**
	 * Generated serial version code
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * User properties<br>
	 * Require properties:<br>
	 * <li>uid
	 * <li>token
	 * <li>lastTransactionTime
	 */
	private Object user;
	/**
	 * User accessible resource list<br>
	 * Require properties:<br>
	 * <li>roleId
	 * <li>roleName
	 * <li>resId
	 * <li>resType
	 * <li>resName
	 * <li>resGroup
	 * <li>resIndex
	 * <li>resLink
	 * <li>accResId
	 * <li>accLevel
	 */
	private Set<Map<String, Object>> accResList;
	/**
	 * user accessible resource type list<br>
	 */
	private Set<Integer> accResTypeList;
	/**
	 * user accessible resource mapping role list<br>
	 */
	private Set<String> roleList;

	/**
	 * @return the user
	 */
	public Object getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(Object user) {
		this.user = user;
	}

	/**
	 * @return the roleSet
	 */
	public Set<String> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleSet
	 *            the roleSet to set
	 */
	public void setRoleList(Set<String> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the userAclSet
	 */
	public Set<Map<String, Object>> getAccResList() {
		return accResList;
	}

	/**
	 * @param userAclSet
	 *            the userAclSet to set
	 */
	public void setAccResList(Set<Map<String, Object>> accResList) {
		this.accResList = accResList;
	}

	/**
	 * @return the accResTypeSet
	 */
	public Set<Integer> getAccResTypeList() {
		return accResTypeList;
	}

	/**
	 * @param accResTypeList
	 *            the accResTypeList to set
	 */
	public void setAccResTypeList(Set<Integer> accResTypeList) {
		this.accResTypeList = accResTypeList;
	}

}
