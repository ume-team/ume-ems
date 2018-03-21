/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.util;

import org.umeframework.dora.util.CodecUtil;

/**
 * EncodeUtil
 * 
 * @author Yue MA
 */
public abstract class EncodeUtil {


	/**
	 * passwordToMD5
	 * 
	 * @param password
	 * @return
	 */
	public static String passwordToMD5(String password) {
		if (password.length() < 32) {
			return CodecUtil.encodeMD5Hex(password);
		} else {
			return password;
		}
	}

}
