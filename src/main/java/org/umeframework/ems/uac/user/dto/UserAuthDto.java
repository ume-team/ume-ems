/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.uac.user.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.umeframework.dora.service.UserEntity;

/**
 * 用户鉴权信息对象类。<br>
 * 用户鉴权成功后，返回的整合信息对象。<br>
 * 
 * @author Yue Ma
 */
public class UserAuthDto extends UserEntity implements Serializable {
	/**
	 * Generated serial version code
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 鉴权用户对应的具体业务用户实体。<br>
	 */
	private Object user;
	/**
	 * 鉴权可访问的资源列表。<br>
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
