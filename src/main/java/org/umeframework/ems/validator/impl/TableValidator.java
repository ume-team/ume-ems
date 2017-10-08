/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.validator.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.umeframework.dora.exception.ValidationException;
import org.umeframework.dora.util.StringUtil;
import org.umeframework.dora.util.ValidatorUtil;
import org.umeframework.ems.desc.dto.EmColDescDto;
import org.umeframework.ems.desc.dto.EmDescDto;
import org.umeframework.ems.validator.Validator;

/**
 * TableValidetorImpl
 * 
 * @author mayue
 *
 */
@Service
public class TableValidator implements Validator {

	/**
	 * 检查数据异常
	 * 
	 * @param desc
	 * @param param
	 * @return
	 */
	@Override
	public void doValidate(
			EmDescDto desc,
			Map<String, String> param) throws ValidationException {

		Map<String, EmColDescDto> colCfgMap = desc.getColCfgMap();
		ValidationException ve = new ValidationException();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			String colId = entry.getKey();
			String colStrValue = entry.getValue();
			EmColDescDto colCfg = colCfgMap.get(colId);
			doItemValidate(ve, colCfg, colStrValue);
		}
		if (ve.size() > 0) {
			throw ve;
		}
	}

	/**
	 * 单项目检查
	 * 
	 * @param ve
	 * @param colCfg
	 * @param colId
	 * @param colValue
	 */
	protected void doItemValidate(
			ValidationException ve,
			EmColDescDto colCfg,
			String colValue) {
		
		String colName = colCfg.getColName();
		colName = StringUtil.isNotEmpty(colName) ? colName : colCfg.getColId();
		// 主键和非空字段检查
		boolean notNull = colCfg.getNotNull() != null && colCfg.getNotNull() == 1;
		boolean pkFlag = colCfg.getPkFlag() != null && colCfg.getPkFlag() == 1;
		if ((notNull || pkFlag) && ValidatorUtil.isEmpty(colValue)) {
			ve.add("VEMSG-NOTNULL", colName);
			return;
		}
		if (colValue == null || colValue.equals("")) {
			return;
		}
		
		//doValidateConstraint()
		
		// 数据类型和值的匹配性检查
		Integer jdbcDataType = colCfg.getDataJdbcType();
		doValidateJdbcDataType(ve, jdbcDataType, colName, colName, colValue);

		Integer dataLength = colCfg.getDataLength();		
		Integer dataPrecision = colCfg.getDataPrecision();
		Integer dataScale = colCfg.getDataScale();
		// 数据长度检查
		if (dataLength != null && dataLength > 0 && dataLength < colValue.length()) {
			ve.add("VEMSG-LEN-MAX", colName, dataLength);
		}
		if (dataPrecision != null && dataPrecision > 0 && dataPrecision + 1 < colValue.length()) {
			ve.add("VEMSG-LEN-PRECISION", colName, dataPrecision);
		}
		if (dataScale != null && dataScale > 0  && colValue.contains(".")) {
			String scalePart = colValue.split("\\.")[1];
			if (dataScale < scalePart.length()) {
				ve.add("VEMSG-LEN-SCALE", colName, dataScale);
			}
		}
	}

	/**
	 * 检查数据类型的一致性
	 * 
	 * @param ve
	 * @param colType
	 * @param colId
	 * @param colValue
	 */
	protected void doValidateJdbcDataType(
			ValidationException ve,
			Integer colType,
			String colId,
			String colName,
			String colValue) {
		colName = StringUtil.isNotEmpty(colName) ? colName : colId;
		switch (colType) {
		case java.sql.Types.DATE: {
			if (!ValidatorUtil.matchedDateFormat(colValue, "yyyy-MM-dd")) {
				ve.add("VEMSG-FMT-DATE", colName, "yyyy-MM-dd");
			}
			break;
		}
		case java.sql.Types.TIMESTAMP: {
			if (!ValidatorUtil.matchedDateFormat(colValue, "yyyy-MM-dd HH:mm:ss.SSS") && !ValidatorUtil.matchedDateFormat(colValue, "yyyy-MM-dd HH:mm:ss")) {
				ve.add("VEMSG-FMT-DATETIME", colName, "yyyy-MM-dd HH:mm:ss.SSS or yyyy-MM-dd HH:mm:ss");
			}
			break;
		}
		case java.sql.Types.TIME: {
			if (!ValidatorUtil.matchedDateFormat(colValue, "HH:mm:ss")) {
				ve.add("VEMSG-FMT-TIME", colName, "HH:mm:ss");
			}
			break;
		}
		case java.sql.Types.FLOAT:
		case java.sql.Types.DOUBLE:
		case java.sql.Types.REAL:
		case java.sql.Types.DECIMAL:
		case java.sql.Types.NUMERIC: {
			if (!ValidatorUtil.isDecimal(colValue)) {
				ve.add("VEMSG-FMT-DECIMAL", colName);
			}
			break;
		}
		case java.sql.Types.SMALLINT:
		case java.sql.Types.TINYINT:
		case java.sql.Types.INTEGER:
		case java.sql.Types.BIGINT: {
			if (!ValidatorUtil.isInteger(colValue)) {
				ve.add("VEMSG-FMT-NUMERIC", colName);
			}
			break;
		}
		case java.sql.Types.BOOLEAN: {
			colValue = colValue.toLowerCase();
			if (!"true".equals(colValue) && !"false".equals(colValue)) {
				ve.add("VEMSG-FMT-BOOL", colName);
			}
			break;
		}
		case java.sql.Types.BIT: {
			if (!"0".equals(colValue) && !"1".equals(colValue)) {
				ve.add("VEMSG-FMT-BIT", colName);
			}
			break;
		}
		default: {
			break;
		}
		}
	}
}
