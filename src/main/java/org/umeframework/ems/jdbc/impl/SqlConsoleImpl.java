/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.jdbc.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.umeframework.dora.dao.RdbDao;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.exception.DataAccessException;
import org.umeframework.dora.service.BaseComponent;
import org.umeframework.dora.util.StringUtil;
import org.umeframework.ems.jdbc.DynaDaoManager;
import org.umeframework.ems.jdbc.SqlConsole;
import org.umeframework.ems.jdbc.dto.SqlConsoleResultDto;

import javax.annotation.Resource;

/**
 * Implement the simple SQL console service to execute SQL statement input from
 * client.<br>
 * 
 * @author Yue MA
 *
 */
@Service
public class SqlConsoleImpl extends BaseComponent implements SqlConsole {
    /**
     * Data source selector
     */
    @Resource
    private DynaDaoManager dynaDaoManager;

	/**
	 * Split char for identify multiple SQLs input from client
	 */
	private String multiSqlSplitFlag = ";";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.sql.SQLConsole#getSqlType(java.lang.String)
	 */
	public String typeOfSQL(String sql) {
		String word = checkFirstWord(sql);
		word = word.trim().toUpperCase();

		if (word.startsWith("SELECT")) {
			return SqlConsoleResultDto.SQL_TYPE_DQL;
		}
		if (word.startsWith("INSERT") || word.startsWith("UPDATE") || word.startsWith("DELETE")) {
			return SqlConsoleResultDto.SQL_TYPE_DML;
		}
		if (word.startsWith("GRANT") || word.startsWith("COMMIT") || word.startsWith("ROLLBACK")) {
			return SqlConsoleResultDto.SQL_TYPE_DCL;
		}
		if (word.startsWith("CREATE") || word.startsWith("ALTER") || word.startsWith("DROP")) {
			return SqlConsoleResultDto.SQL_TYPE_DDL;
		}
		return "OTHER";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.SQLConsole#splitSQL(java.lang.String)
	 */
	public String[] splitStr(String str, String splitChar, String transferChar) {
		String[] arr = str.split(splitChar);
		if (transferChar != null) {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].contains(transferChar)) {
					arr[i] = arr[i].replace(transferChar, splitChar);
				}
			}
		}
		return arr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.sql.SQLConsole#queryAsObjectList(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public Collection<Object> queryAsObjectList(String dsId, String table, String field, Map<String, String> condition) {
		Collection<Map<String, Object>> result =  queryAsMapList(dsId, table, field, condition);
		return this.convertExecuteResultForQuery(result);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.sql.SQLConsole#queryAsMapList(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public Collection<Map<String, Object>> queryAsMapList(String dsId, String table, String field, Map<String, String> condition) {
		RdbDao dao = dynaDaoManager.getDao(dsId);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		table = table.trim();
		if (StringUtil.isNotEmpty(field)) {
			field = field.trim();
			field = field.replace(';', ',').replace('|', ',').replace(' ', ',');
			sql.append(field);
		} else {
			sql.append("*");
		}
		sql.append(" FROM ");
		sql.append(table.trim());
		if (condition != null) {
			sql.append(" WHERE 1=1 ");
			for (Map.Entry<String, String> entry : condition.entrySet()) {
				String key = entry.getKey().trim();
				String value = entry.getValue();
				String valuetrim = value.trim();
				sql.append(" AND ");
				if (key.equals("*")) {
					sql.append("(");
					sql.append(value);
					sql.append(") ");
				} else {
					sql.append(key);
					if (valuetrim.startsWith(">") || valuetrim.startsWith("<") || valuetrim.startsWith("=") || valuetrim.startsWith("<>")) {
						sql.append(value);
					} else {
						sql.append("=");
						if (!StringUtil.isNumeric(valuetrim) && !valuetrim.startsWith("'")) {
							sql.append("'");
							sql.append(value);
							sql.append("'");
						} else {
							sql.append(value);
						}
					}
				}
			}
		}
		List<Map<String, Object>> mapList = dao.queryForMapList(sql.toString(), null);
		return mapList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.sql.SQLConsole#execute(java.lang.String,
	 * java.lang.String)
	 */
	public List<SqlConsoleResultDto> execute(String dsId, String sqls, Boolean autoCommit) {

		Map<Integer, String> sqlList = readSqlList(new StringReader(sqls));
		String[] sqlArr = sqlList.values().toArray(new String[sqlList.size()]);

		return this.executeMulti(dsId, sqlArr, autoCommit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.SQLConsole#executeMulti(java.lang.String,
	 * java.lang.String)
	 */
	public List<SqlConsoleResultDto> executeMulti(String dsId, String[] sqls, Boolean autoCommit) {
		if (autoCommit == null) {
			autoCommit = false;
		}
		List<SqlConsoleResultDto> resultDTOs = new ArrayList<SqlConsoleResultDto>();
		if (autoCommit) {
			for (int i = 0; i < sqls.length; i++) {
				String sql = sqls[i];
				try {
					dynaDaoManager.beginTransaction(dsId);
					SqlConsoleResultDto consoleResultDTO = this.executeOne(dsId, sql);
					dynaDaoManager.commitTransaction(dsId);
					resultDTOs.add(consoleResultDTO);
				} catch (Throwable e) {
					dynaDaoManager.rollbackTransaction(dsId);
					SqlConsoleResultDto consoleResultDTO = new SqlConsoleResultDto();
					consoleResultDTO.setSql(sql);
					consoleResultDTO.setExecuteSuccess(false);
					consoleResultDTO.setExecuteMessage("Execute sql failed, " + sql + ", " + e.getMessage());
					resultDTOs.add(consoleResultDTO);
				}
			}
		} else {
			String sql = null;
			try {
				dynaDaoManager.beginTransaction(dsId);
				for (int i = 0; i < sqls.length; i++) {
					sql = sqls[i];
					SqlConsoleResultDto consoleResultDTO = null;
					consoleResultDTO = this.executeOne(dsId, sql);
					resultDTOs.add(consoleResultDTO);
				}
				dynaDaoManager.commitTransaction(dsId);
			} catch (Throwable e) {
				dynaDaoManager.rollbackTransaction(dsId);
				resultDTOs.clear();
				SqlConsoleResultDto consoleResultDTO = new SqlConsoleResultDto();
				consoleResultDTO.setSql(sql);
				consoleResultDTO.setExecuteSuccess(false);
				consoleResultDTO.setExecuteMessage("Execute sql failed, " + sql + ", " + e.getMessage());
				resultDTOs.add(consoleResultDTO);
			}
		}

		return resultDTOs;
	}

	/**
	 * executeOne
	 * 
	 * @param dsId
	 * @param sql
	 * @param autoCommit
	 * @return
	 */
	protected SqlConsoleResultDto executeOne(String dsId, String sql) {

		RdbDao dao = dynaDaoManager.getDao(dsId);
		String type = typeOfSQL(sql);

		SqlConsoleResultDto consoleResultDTO = new SqlConsoleResultDto();
		consoleResultDTO.setSql(sql);
		consoleResultDTO.setType(type);

		String msg = null;
		if (type.equals(SqlConsoleResultDto.SQL_TYPE_DQL)) {
			List<Map<String, Object>> mapList = dao.queryForMapList(sql, null);
			consoleResultDTO.setExecuteResultForQuery(convertExecuteResultForQuery(mapList));
			msg = "Query successful, " + mapList.size() + " rows returned.";
		} else if (type.equals(SqlConsoleResultDto.SQL_TYPE_DDL)) {
			int result = dao.update(sql, null);
			msg = "Execute successful, " + result + " rows affected.";
		} else if (type.equals(SqlConsoleResultDto.SQL_TYPE_DCL)) {
			msg = "DCL statement was ignore, 0 rows affected.";
		} else if (type.equals(SqlConsoleResultDto.SQL_TYPE_DML)) {
			int result = dao.update(sql, null);
			msg = "Update successful, " + result + " rows affected.";
		}
		consoleResultDTO.setExecuteSuccess(true);
		consoleResultDTO.setExecuteMessage(msg);
		return consoleResultDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.sql.SQLConsole#executeFile(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public List<SqlConsoleResultDto> executeFile(String dsId, String filePath, String charSet) {

		if (charSet == null) {
			charSet = "UTF-8";
		}

		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new ApplicationException("APMSG10005", new Object[] { filePath, "storage"});
		}
		Map<Integer, String> sqlList = null;
		try {
			sqlList = readSqlList(new InputStreamReader(new FileInputStream(file), charSet));
		} catch (IOException e) {
			throw new ApplicationException(e, "APMSG10005", new Object[] { filePath, "storage"});
		}

		return executeMulti(dsId, sqlList.values().toArray(new String[sqlList.values().size()]), true);
	}

	/**
	 * readSqlList
	 * 
	 * @param inReader
	 * @return
	 */
	protected Map<Integer, String> readSqlList(Reader inReader) {

		// line number count
		int lineNo = 1;
		// line object
		String line = null;
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(inReader);
			// read line
			line = reader.readLine();
			while (line != null) {
				lines.add(line.trim());
				line = reader.readLine();
				lineNo++;
			}
		} catch (Exception e) {
			throw new DataAccessException(e, e.getMessage(), new Object[] {line, lineNo});
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new DataAccessException(e, e.toString());
				}
			}
		}

		Map<Integer, String> sqlList = new LinkedHashMap<Integer, String>();
		StringBuilder builder = new StringBuilder();
		lineNo = 1;
		for (String eachLine : lines) {
			if (eachLine.endsWith(multiSqlSplitFlag)) {
				builder.append(eachLine.substring(0, eachLine.length() - 1));
				sqlList.put(lineNo, builder.toString());
				builder = new StringBuilder();
			} else {
				builder.append(eachLine);
				builder.append(' ');
			}
			lineNo++;
		}
		if (builder.length() > 0) {
			sqlList.put(lineNo, builder.toString());
		}
		return sqlList;
	}

	/**
	 * checkFirstWord
	 * 
	 * @param sql
	 * @return
	 */
	protected String checkFirstWord(String sql) {
		sql = sql.trim();
		String word = null;
		if (sql.contains(" ")) {
			word = sql.substring(0, sql.indexOf(' '));
		} else if (sql.contains("\t")) {
			word = sql.substring(0, sql.indexOf('\t'));
		} else {
			word = sql;
			// throw new ApplicationException("APMSG10004", sql);
		}
		return word;
	}

	/**
	 * convertExecuteResultForQuery
	 * 
	 * @param consoleResultDTO
	 * @param mapList
	 */
	protected Collection<Object> convertExecuteResultForQuery(Collection<Map<String, Object>> mapList) {
		Collection<Object> executeResultForQuery = null;
		if (mapList != null) {
			executeResultForQuery = new ArrayList<Object>();
			Iterator<Map<String, Object>> mapIterator = mapList.iterator();
			int i = 0;
			while (mapIterator.hasNext()) {
				Map<String, Object> mapObj = mapIterator.next();
				if (i == 0) {
					executeResultForQuery.add(mapObj.keySet());
				}
				executeResultForQuery.add(mapObj.values());
				i++;
			}
		}
		return executeResultForQuery;
	}

}
