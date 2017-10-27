/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.uac.user;

import org.umeframework.ems.uac.entity.UmeUserDto;

/**
 * 用户信息管理服务接口声明。<br>
 * 
 * @author YUE MA
 */
public interface UserManager {

	/**
	 * 修改密码。<br>
	 * 
	 * @param userPassword
	 *            - old password
	 * @param newPassword
	 *            - new password
	 * @return true if change successful
	 */
	void changePassword(String uid, String userPassword, String newPassword);

	/**
	 * 创建新用户。<br>
	 * 
	 * @param user
	 * @return
	 */
	void createUser(UmeUserDto user);

	/**
	 * 更新用户信心。<br>
	 * 
	 * @param user
	 * @param userPassword
	 * @return
	 */
	void updateUser(UmeUserDto user);

}
