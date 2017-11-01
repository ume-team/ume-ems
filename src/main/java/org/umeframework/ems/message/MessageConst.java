/*
 * Copyright 2014_2017 UME Framework Group, Apache License Version 2.";0
 */
package org.umeframework.ems.message;

/**
 * MessageConst
 * 
 * @author Yue Ma
 *
 */
public interface MessageConst {
	// EMS业务用消息定义
	String UME_EMS_MSG_001 = "UME_EMS_MSG_001::No found valid table configuration with this ID {0}, Please input correct entity ID.";
	String UME_EMS_MSG_002 = "UME_EMS_MSG_002::SQL execute failed {0}.";
	String UME_EMS_MSG_003 = "UME_EMS_MSG_003::Unsupport SQL statement which start of {0}, {1}.";
	String UME_EMS_MSG_004 = "UME_EMS_MSG_004::Unsupport SQL syntax {0}.";
	String UME_EMS_MSG_005 = "UME_EMS_MSG_005::No found {0} in {1}.";
	String UME_EMS_MSG_006 = "UME_EMS_MSG_006::{0} can not be empty.";

	String UME_EMS_MSG_201 = "UME_EMS_MSG_201::Retrieve {0} failed.";
	String UME_EMS_MSG_202 = "UME_EMS_MSG_202::Update {0} failed.";
	String UME_EMS_MSG_203 = "UME_EMS_MSG_203::Wrong parameter input {0}.";
	String UME_EMS_MSG_204 = "UME_EMS_MSG_204::Delete {0} failed.";
	String UME_EMS_MSG_205 = "UME_EMS_MSG_205::Create {0} failed.";

	String UME_EMS_MSG_301 = "UME_EMS_MSG_301::No found data convertor {0}.";
	String UME_EMS_MSG_302 = "UME_EMS_MSG_302::No found entity manager for {0}.";

	String UME_EMS_MSG_401 = "UME_EMS_MSG_401::Entity {0} does not support {1} operation.";
	String UME_EMS_MSG_402 = "UME_EMS_MSG_402::Failed in process {0} 's {1}.";
	
	// 数据项目校验用消息定义 Begin
	String UME_EMS_VEMSG_NOTNULL = "UME_EMS_VEMSG_NOTNULL::{0} can not be empty.";
	String UME_EMS_VEMSG_FMT_DATE = "UME_EMS_VEMSG_FMT_DATE::{0} is not corrent date format {1}.";
	String UME_EMS_VEMSG_FMT_DATETIME = "UME_EMS_VEMSG_FMT_DATETIME::{0} is not corrent datetime format {1}.";
	String UME_EMS_VEMSG_FMT_TIME = "UME_EMS_VEMSG_FMT_TIME::{0} is not corrent time format {1}.";
	String UME_EMS_VEMSG_FMT_DECIMAL = "UME_EMS_VEMSG_FMT_DECIMAL::{0} is not corrent decimal format.";
	String UME_EMS_VEMSG_FMT_INT = "UME_EMS_VEMSG_FMT_DECIMAL::{0} is not corrent integer format.";
	String UME_EMS_VEMSG_FMT_BOOL = "UME_EMS_VEMSG_FMT_BOOL::{0} is not corrent boolean format(true or false).";
	String UME_EMS_VEMSG_FMT_BIT = "UME_EMS_VEMSG_FMT_BIT::{0} is not corrent BIT format(0 or 1).";
	String UME_EMS_VEMSG_FMT_ALPHA = "UME_EMS_VEMSG_FMT_ALPHA::{0} is not corrent alpha char.";
	String UME_EMS_VEMSG_FMT_ALPHANUM = "UME_EMS_VEMSG_FMT_ALPHANUM::{0} is not corrent alpha or number char.";
	String UME_EMS_VEMSG_FMT_ASCII = "UME_EMS_VEMSG_FMT_ASCII::{0} is not corrent ASCII format.";
	String UME_EMS_VEMSG_FMT_ASCIINS = "UME_EMS_VEMSG_FMT_ASCIINS::{0} is not corrent ASCIINS format.";
	String UME_EMS_VEMSG_FMT_CURRENCY = "UME_EMS_VEMSG_FMT_CURRENCY::{0} is not corrent currency format.";
	String UME_EMS_VEMSG_FMT_EMAIL = "UME_EMS_VEMSG_FMT_EMAIL::{0} is not corrent Email format.";
	String UME_EMS_VEMSG_FMT_MOBILE = "UME_EMS_VEMSG_FMT_MOBILE::{0} is not corrent mobile number format.";
	String UME_EMS_VEMSG_FMT_TEL = "UME_EMS_VEMSG_FMT_TEL::{0} is not corrent telphone format.";
	String UME_EMS_VEMSG_FMT_ZIPCODE = "UME_EMS_VEMSG_FMT_ZIPCODE::{0} is not corrent post code format.";
	String UME_EMS_VEMSG_LEN_MAX = "UME_EMS_VEMSG_LEN_MAX::{0} max length is {1}.";
	String UME_EMS_VEMSG_LEN_MIN = "UME_EMS_VEMSG_LEN_MIN::{0} min length is {1}.";
	String UME_EMS_VEMSG_LEN_RANGE = "UME_EMS_VEMSG_LEN_RANGE::{0} length range between {1}～{2}.";
	String UME_EMS_VEMSG_LEN_FIX = "UME_EMS_VEMSG_LEN_FIX::{0} length fix on {1}.";
	String UME_EMS_VEMSG_LEN_PRECISION = "UME_EMS_VEMSG_LEN_PRECISION::{0} max precision is {1}.";
	String UME_EMS_VEMSG_LEN_SCALE = "UME_EMS_VEMSG_LEN_SCALE::{0} max scale is {1}.";
	String UME_EMS_VEMSG_VAL_MIN = "UME_EMS_VEMSG_VAL_MIN::{0} min value is {1}.";
	String UME_EMS_VEMSG_VAL_MAX = "UME_EMS_VEMSG_VAL_MAX::{0} max valye is {1}.";
	String UME_EMS_VEMSG_VAL_RANGE = "UME_EMS_VEMSG_VAL_RANGE::{0} range between {1}～{2}.";
	String UME_EMS_VEMSG_COS_RANGE = "UME_EMS_VEMSG_COS_RANGE::{0} out of constrant limit.";
	// 数据项目校验用消息定义 End

}
