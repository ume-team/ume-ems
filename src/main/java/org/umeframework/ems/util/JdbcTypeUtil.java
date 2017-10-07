package org.umeframework.ems.util;

import org.umeframework.dora.util.StringUtil;

abstract public class JdbcTypeUtil {
	
	/**
	 * stringToObject
	 * 
	 * @param value
	 * @param colJdbcType
	 * @return
	 * @throws Exception
	 */
	public static Object stringToObject(
			String value,int colJdbcType) throws Exception {
		if (value == null) {
			return null;
		}
		Object objValue = null;

		switch (colJdbcType) {
		case java.sql.Types.CHAR:
		case java.sql.Types.VARCHAR:
		case java.sql.Types.LONGVARCHAR:
		case java.sql.Types.LONGNVARCHAR: {
			objValue = value;
			break;
		}
		case java.sql.Types.DATE: {
			objValue = StringUtil.toDate(value);
			break;
		}
		case java.sql.Types.TIMESTAMP: {
			objValue = StringUtil.toTimestamp(value);
			break;
		}
		case java.sql.Types.TIME: {
			objValue = StringUtil.toTime(value);
			break;
		}
		case java.sql.Types.DECIMAL:
		case java.sql.Types.NUMERIC: {
			objValue = StringUtil.toBigDecimal(value);
			break;
		}
		case java.sql.Types.SMALLINT: {
			objValue = StringUtil.toShort(value);
			break;
		}
		case java.sql.Types.INTEGER: {
			objValue = StringUtil.toInteger(value);
			break;
		}
		case java.sql.Types.TINYINT: {
			objValue = StringUtil.toByte(value);
			break;
		}
		case java.sql.Types.BIGINT: {
			objValue = StringUtil.toBigInteger(value);
			break;
		}
		case java.sql.Types.FLOAT: {
			objValue = StringUtil.toFloat(value);
			break;
		}
		case java.sql.Types.DOUBLE: {
			objValue = StringUtil.toDouble(value);
			break;
		}
		case java.sql.Types.REAL: {
			objValue = StringUtil.toFloat(value);
			break;
		}
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.BIT: {
			objValue = StringUtil.toBoolean(value);
			break;
		}
		default: {
			objValue = value;
			break;
		}
		}

		return objValue;
	}
}
