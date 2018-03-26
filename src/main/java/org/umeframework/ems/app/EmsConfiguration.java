/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.app;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.umeframework.dora.service.user.UserAuthenticator;
import org.umeframework.ems.jdbc.DynaDaoManager;
import org.umeframework.ems.jdbc.impl.DynaDaoManagerImpl;
import org.umeframework.uac.user.dto.UserAclDto;
import org.umeframework.uac.user.impl.DefaultAuthenticatorImpl;
import org.umeframework.ems.util.UtilFactory;

/**
 * Cache Manager Configuration.<br>
 */
@Configuration
@ComponentScan(basePackages = "org.umeframework.ems")
public class EmsConfiguration {
	/**
	 * TABLE_DESC_QUERY_FOR_MYSQL
	 */
	private static final String TABLE_DESC_QUERY_FOR_MYSQL = "select "
	        + " COLUMN_NAME as 'colId',"
	        + " COLUMN_COMMENT as 'colName',"
	        + " DATA_TYPE as 'dataType',"
	        + " case when DATA_TYPE='bigint' or DATA_TYPE='tinyint' or DATA_TYPE='smallint' or DATA_TYPE='mediumint' or DATA_TYPE='int' then NUMERIC_PRECISION+1 when DATA_TYPE='decimal' or DATA_TYPE='double' or DATA_TYPE='float' then NUMERIC_PRECISION when DATA_TYPE='varchar' or DATA_TYPE='char' then CHARACTER_MAXIMUM_LENGTH else CHARACTER_OCTET_LENGTH end as 'dataLength',"
	        + " NUMERIC_PRECISION as 'dataPrecision',"
	        + " NUMERIC_SCALE as 'dataScale',"
	        + " case when COLUMN_KEY='PRI' then '1' else '0' end as 'pkFlag',"
	        + " case when IS_NULLABLE='NO' then '1' else '0' end as 'notNull',"
	        + " COLUMN_DEFAULT as 'defaultValue'"
	        + " from INFORMATION_SCHEMA.COLUMNS"
	        + " where TABLE_NAME = {varTableId}";

	/**
	 * TABLE_DESC_QUERY_FOR_DB2
	 */
	private static final String TABLE_DESC_QUERY_FOR_DB2 = "SELECT"
	        + " T1.COLNAME AS \"colId\","
	        + " T1.REMARKS AS \"colName\","
	        + " T1.TYPENAME AS \"dataType\","
	        + " CASE WHEN T1.TYPENAME ='INTEGER' THEN 10 WHEN T1.TYPENAME ='SMALLINT' THEN 5 WHEN T1.TYPENAME ='NUMERIC' THEN 31 ELSE T1.LENGTH END AS \"dataLength\","
	        + " T1.LENGTH AS \"dataPrecision\","
	        + " T1.SCALE AS \"dataScale\","
	        + " T1.NULLS AS \"notNull\","
	        + " NULL AS \"defaultValue\","
	        + " MAX(CASE WHEN T2.TYPE='P' THEN '1' ELSE '0' end ) as \"pkFlag\""
	        + " FROM SYSCAT.COLUMNS AS T1 LEFT JOIN ( SELECT A.TABSCHEMA, A.TABNAME, B.COLNAME, A.TYPE FROM syscat.tabconst A ,SYSCAT.KEYCOLUSE B WHERE A.CONSTNAME = B.CONSTNAME AND A.TYPE IN ('P','U')) AS T2  ON T1.TABSCHEMA=T2.TABSCHEMA AND T1.TABNAME=T2.TABNAME AND T1.COLNAME=T2.COLNAME WHERE T1.TABNAME = {varTableId}"
	        + " GROUP BY T1.COLNAME, T1.REMARKS, T1.TYPENAME, T1.LENGTH, T1.SCALE, T1.NULLS ORDER BY T1.COLNAME";

	/**
	 * tableDescQuerySQLProp
	 * 
	 * @return
	 */
	@Bean(name = "tableDescQuerySQLProp")
	public Properties tableDescQuerySQLProp() {
		Properties prop = new Properties();
		prop.setProperty("MYSQL", TABLE_DESC_QUERY_FOR_MYSQL);
		prop.setProperty("DB2", TABLE_DESC_QUERY_FOR_DB2);
		return prop;
	}

	@Bean(name = "tableListQuerySQLProp")
	public Properties tableListQuerySQLProp() {
		Properties prop = new Properties();
		prop.setProperty("MYSQL", "select TABLE_NAME as 'entId',TABLE_COMMENT as 'entName' from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = {varSchema} AND TABLE_NAME LIKE CONCAT('%', {varNameLike}, '%')");
		prop.setProperty("DB2", "select DISTINCT NAME  as \"entId\" from SYSIBM.SYSTABLES where CREATOR = {varSchema} AND NAME LIKE '%' concat {varNameLike} concat '%'");
		return prop;
	}

	/**
	 * converterFactory
	 * 
	 * @return
	 */
	@Bean(name = "converterFactory", initMethod = "init")
	public UtilFactory converterFactory() {
		Properties prop = new Properties();
		prop.setProperty("B64toLOB", "org.umeframework.ems.util.Base64Util#base64ToBlob");
		prop.setProperty("LOBtoB64", "org.umeframework.ems.util.Base64Util#blobToBase64");
		prop.setProperty("UTF8ENC", "org.umeframework.dora.util.CodecUtil#encodeAsUTF8");
		prop.setProperty("UTF8DEC", "org.umeframework.dora.util.CodecUtil#decodeAsUTF8");
		prop.setProperty("PasswordENC", "org.umeframework.ems.util.EncodeUtil#passwordToMD5");
		return new UtilFactory(prop);
	}

	/**
	 * validatorFactory
	 * 
	 * @return
	 */
	@Bean(name = "validatorFactory", initMethod = "init")
	public UtilFactory validatorFactory() {
		Properties prop = new Properties();
		prop.setProperty("tableValidator", org.umeframework.ems.validator.impl.TableValidator.class.getName() + "#doValidate");
		prop.setProperty("tableExValidator", org.umeframework.ems.validator.impl.TableExValidator.class.getName() + "#doValidate");
		return new UtilFactory(prop);
	}

	/**
	 * userAuthenticator
	 * 
	 * @return
	 */
	@Bean(name = "userAuthenticator")
	public UserAuthenticator<?> userAuthenticator() {
		UserAuthenticator<UserAclDto> instance = new DefaultAuthenticatorImpl();
		return instance;
	}
	
	/**
	 * dynaDaoManager
	 * 
	 * @return
	 */
	@Bean(name = "dynaDaoManager", initMethod = "init")
	public DynaDaoManager dynaDaoManager() {
		DynaDaoManager instance = new DynaDaoManagerImpl();
		return instance;
	}

}
