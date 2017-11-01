/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.validator.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.umeframework.dora.exception.ValidationException;
import org.umeframework.dora.util.StringUtil;
import org.umeframework.dora.util.ValidatorUtil;
import org.umeframework.dora.validation.constraints.TextFormat;
import org.umeframework.ems.desc.dto.EmColDescDto;
import org.umeframework.ems.desc.dto.EmDescDto;
import org.umeframework.ems.entity.EmConsCodeCfgDto;
import org.umeframework.ems.message.MessageConst;
import org.umeframework.ems.validator.Validator;

/**
 * TableValidetorImpl
 * 
 * @author mayue
 *
 */
@Service
public class TableExValidator implements Validator,MessageConst {

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
		
		String colId = colCfg.getColId();
		String colName = colCfg.getColName();

		if (colValue == null || colValue.equals("")) {
			return;
		}
		
		//doValidateConstraint()
		
		// 扩展定义的数据类型检查
		String editFormat = colCfg.getEditFormat();
		if (editFormat != null) {
			doValidateTextFormat(ve, editFormat, colId, colName, colValue);
		}

		// 扩展定义的数据长度检查
		Integer dataLengthMin = colCfg.getDataLengthMin();
		Integer dataLengthMax = colCfg.getDataLengthMax();
		if (dataLengthMin != null && dataLengthMax != null && dataLengthMin == dataLengthMax && colValue.length() != dataLengthMin) {
			ve.add(UME_EMS_VEMSG_LEN_FIX, colName, dataLengthMin);
		} else if (dataLengthMin != null && dataLengthMax != null && (colValue.length() > dataLengthMax || colValue.length() < dataLengthMin)) {
			ve.add(UME_EMS_VEMSG_LEN_RANGE, colName, dataLengthMin, dataLengthMax);
		} else if (dataLengthMin != null && colValue.length() < dataLengthMin) {
			ve.add(UME_EMS_VEMSG_LEN_MIN, colName, dataLengthMin);
		} else if (dataLengthMax != null && colValue.length() > dataLengthMax) {
			ve.add(UME_EMS_VEMSG_LEN_MAX, colName, dataLengthMax);
		}

		// 扩展定义的数据范围检查
		if (colCfg.getDataRangeMin() != null || colCfg.getDataRangeMax() != null) {
			doValidateDataRange(ve, colCfg.getDataJdbcType(), colId, colName, colValue, colCfg.getDataRangeMin(), colCfg.getDataRangeMax());
		}
	}
	
	/**
	 * 检查文本格式
	 * 
	 * @param ve
	 * @param textFormat
	 * @param colId
	 * @param value
	 */
	protected void doValidateTextFormat(
			ValidationException ve,
			String textFormat,
			String colId,
			String colName,
			String value) {
		colName = StringUtil.isNotEmpty(colName) ? colName : colId;
		textFormat = textFormat.trim();
		if (TextFormat.Category.Alpha.toString().equals(textFormat)) {
			if (!ValidatorUtil.isAlpha(value)) {
				ve.add(UME_EMS_VEMSG_FMT_ALPHA, colName);
			}
		} else if (TextFormat.Category.AlphaNumeric.toString().equals(textFormat)) {
			if (!ValidatorUtil.isAlphaNumeric(value)) {
				ve.add(UME_EMS_VEMSG_FMT_ALPHANUM, colName);
			}
		} else if (TextFormat.Category.Ascii.toString().equals(textFormat)) {
			if (!ValidatorUtil.isAscii(value)) {
				ve.add(UME_EMS_VEMSG_FMT_ASCII, colName);
			}
		} else if (TextFormat.Category.AsciiNoneSpace.toString().equals(textFormat)) {
			if (!ValidatorUtil.isAsciiNS(value)) {
				ve.add(UME_EMS_VEMSG_FMT_ASCIINS, colName);
			}
		} else if (TextFormat.Category.Currency.toString().equals(textFormat)) {
			if (!ValidatorUtil.isCurrency(value)) {
				ve.add(UME_EMS_VEMSG_FMT_CURRENCY, colName);
			}
		} else if (TextFormat.Category.Date.toString().equals(textFormat)) {
			if (!ValidatorUtil.matchedDateFormat(value, "yyyy-MM-dd")) {
				ve.add(UME_EMS_VEMSG_FMT_DATE, colName, "yyyy-MM-dd");
			}
		} else if (TextFormat.Category.Datetime.toString().equals(textFormat)) {
			if (!ValidatorUtil.matchedDateFormat(value, "yyyy-MM-dd HH:mm:ss.SSS") && !ValidatorUtil.matchedDateFormat(value, "yyyy-MM-dd HH:mm:ss")) {
				ve.add(UME_EMS_VEMSG_FMT_DATETIME, colName, "yyyy-MM-dd HH:mm:ss.SSS or yyyy-MM-dd HH:mm:ss");
			}
		} else if (TextFormat.Category.Time.toString().equals(textFormat)) {
			if (!ValidatorUtil.matchedDateFormat(value, "HH:mm:ss")) {
				ve.add(UME_EMS_VEMSG_FMT_TIME, colName, "HH:mm:ss");
			}
		} else if (TextFormat.Category.Email.toString().equals(textFormat)) {
			if (!ValidatorUtil.isEmail(value)) {
				ve.add(UME_EMS_VEMSG_FMT_EMAIL, colName);
			}
		} else if (TextFormat.Category.Numeric.toString().equals(textFormat)) {
			if (!ValidatorUtil.isNumeric(value)) {
				ve.add(UME_EMS_VEMSG_FMT_DECIMAL, colName);
			}
		} else if (TextFormat.Category.Decimal.toString().equals(textFormat)) {
			if (!ValidatorUtil.isDecimal(value)) {
				ve.add(UME_EMS_VEMSG_FMT_DECIMAL, colName);
			}
		} else if (TextFormat.Category.MobileNumber.toString().equals(textFormat)) {
			if (!ValidatorUtil.isMobileNumber(value)) {
				ve.add(UME_EMS_VEMSG_FMT_MOBILE, colName);
			}
		} else if (TextFormat.Category.TelNumber.toString().equals(textFormat)) {
			if (!ValidatorUtil.isTelNumber(value)) {
				ve.add(UME_EMS_VEMSG_FMT_TEL, colName);
			}
		} else if (TextFormat.Category.ZipCode.toString().equals(textFormat)) {
			if (!ValidatorUtil.isZipCode(value)) {
				ve.add(UME_EMS_VEMSG_FMT_ZIPCODE, colName);
			}
		}
	}

	/**
	 * 检查数值，日期，及文字类型的值范围（最小值～最大值）
	 * 
	 * @param ve
	 * @param colType
	 * @param colId
	 * @param colValue
	 * @param dataRangeMin
	 * @param dataRangeMax
	 */
	protected void doValidateDataRange(
			ValidationException ve,
			Integer colType,
			String colId,
			String colName,
			String colValue,
			String dataRangeMin,
			String dataRangeMax) {
		colName = StringUtil.isNotEmpty(colName) ? colName : colId;
		try {
			switch (colType) {
			case java.sql.Types.DATE: {
				Date value = Date.valueOf(colValue);
				if (dataRangeMin != null) {
					Date min = Date.valueOf(dataRangeMin);
					if (value.compareTo(min) < 0) {
						ve.add(UME_EMS_VEMSG_VAL_MIN, colName, min);
					}
				}
				if (dataRangeMax != null) {
					Date max = Date.valueOf(dataRangeMax);
					if (value.compareTo(max) > 0) {
						ve.add(UME_EMS_VEMSG_VAL_MAX, colName, max);
					}
				}
				break;
			}
			case java.sql.Types.TIMESTAMP: {
				Timestamp value = Timestamp.valueOf(colValue);
				if (dataRangeMin != null) {
					Timestamp min = Timestamp.valueOf(dataRangeMin);
					if (value.compareTo(min) < 0) {
						ve.add(UME_EMS_VEMSG_VAL_MIN, colName, min);
					}
				}
				if (dataRangeMax != null) {
					Timestamp max = Timestamp.valueOf(dataRangeMax);
					if (value.compareTo(max) > 0) {
						ve.add(UME_EMS_VEMSG_VAL_MAX, colName, max);
					}
				}
				break;
			}
			case java.sql.Types.TIME: {
				Time value = Time.valueOf(colValue);
				if (dataRangeMin != null) {
					Time min = Time.valueOf(dataRangeMin);
					if (value.compareTo(min) < 0) {
						ve.add(UME_EMS_VEMSG_VAL_MIN, colName, min);
					}
				}
				if (dataRangeMax != null) {
					Time max = Time.valueOf(dataRangeMax);
					if (value.compareTo(max) > 0) {
						ve.add(UME_EMS_VEMSG_VAL_MAX, colName, max);
					}
				}
				break;
			}
			case java.sql.Types.FLOAT:
			case java.sql.Types.DOUBLE:
			case java.sql.Types.REAL:
			case java.sql.Types.DECIMAL:
			case java.sql.Types.NUMERIC:
			case java.sql.Types.SMALLINT:
			case java.sql.Types.TINYINT:
			case java.sql.Types.INTEGER:
			case java.sql.Types.BIGINT: {
				BigDecimal value = new BigDecimal(colValue);
				if (dataRangeMin != null) {
					BigDecimal min = new BigDecimal(dataRangeMin);
					if (value.compareTo(min) < 0) {
						ve.add(UME_EMS_VEMSG_VAL_MIN, colName, min);
					}
				}
				if (dataRangeMax != null) {
					BigDecimal max = new BigDecimal(dataRangeMax);
					if (value.compareTo(max) > 0) {
						ve.add(UME_EMS_VEMSG_VAL_MAX, colName, max);
					}
				}
				break;
			}
			default: {
				String value = colValue;
				if (dataRangeMin != null) {
					String min = dataRangeMin;
					if (value.compareTo(min) < 0) {
						ve.add(UME_EMS_VEMSG_VAL_MIN, colName, min);
					}
				}
				if (dataRangeMax != null) {
					String max = dataRangeMax;
					if (value.compareTo(max) > 0) {
						ve.add(UME_EMS_VEMSG_VAL_MAX, colName, max);
					}
				}
				break;
			}
			}
		} catch (Exception e) {
			if (ve.size() <= 0) {
				ve.add(UME_EMS_VEMSG_VAL_RANGE, colName, dataRangeMin == null ? "" : dataRangeMin, dataRangeMax == null ? "" : dataRangeMax);
			}
		}
	}

	/**
	 * doValidateConstraint
	 * 
	 * @param ve
	 * @param consList
	 * @param colId
	 * @param colName
	 * @param value
	 */
	protected void doValidateConstraint(
			ValidationException ve,
			List<EmConsCodeCfgDto> consList,
			String colId,
			String colName,
			String value) {
		colName = StringUtil.isNotEmpty(colName) ? colName : colId;
		HashSet<String> consSet = new HashSet<String>();
		for(EmConsCodeCfgDto cons : consList){
			consSet.add(cons.getStoreValue());
		}
		
		if (!consSet.contains(value)){
			ve.add(UME_EMS_VEMSG_COS_RANGE, colName);
		}
	}
}
