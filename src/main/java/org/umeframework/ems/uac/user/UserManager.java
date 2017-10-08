package org.umeframework.ems.uac.user;

import org.umeframework.ems.uac.entity.UmeUserDto;

/**
 * User information management interface.<br>
 * 
 * @author YUE MA
 */
public interface UserManager {

	/**
	 * Change password.<br>
	 * 
	 * @param userPassword
	 *            - old password
	 * @param newPassword
	 *            - new password
	 * @return true if change successful
	 */
	void changePassword(String uid, String userPassword, String newPassword);

	/**
	 * Create user record.<br>
	 * 
	 * @param user
	 * @return
	 */
	void createUser(UmeUserDto user);

	/**
	 * Update user record.<br>
	 * 
	 * @param user
	 * @param userPassword
	 * @return
	 */
	void updateUser(UmeUserDto user);

}
