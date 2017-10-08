/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.util;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.umeframework.dora.util.CodecUtil;
import org.umeframework.dora.util.StringUtil;

public abstract class Base64Util {
	/**
	 * base64ToBlob
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static java.sql.Blob base64ToBlob(String value) throws Exception {
		if (value == null) {
			return null;
		}
		byte[] bytes = CodecUtil.decodeBase64(value);
		return new org.umeframework.dora.type.Blob(bytes);
	}
	

	/**
	 * blobToBase64
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	public static String blobToBase64(
			Object value) throws UnsupportedEncodingException, SQLException {
		if (value == null) {
			return null;
		}
		String strValue = null;
		if (value instanceof byte[]) {
			byte[] b64bytes = CodecUtil.encodeBase64((byte[]) value);
			strValue = StringUtil.bytesToString(b64bytes);
		} else if (value instanceof java.sql.Blob) {
			byte[] b64bytes = CodecUtil.encodeBase64((java.sql.Blob) value);
			strValue = StringUtil.bytesToString(b64bytes);
		} else {
			strValue = value.toString();
		}
		return strValue;
	}

}
