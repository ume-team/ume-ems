/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.desc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.umeframework.dora.cache.CacheManager;
import org.umeframework.dora.dao.RdbDao;
import org.umeframework.dora.dao.impl.JdbcTypeMapping;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.service.BaseComponent;
import org.umeframework.dora.util.StringUtil;
import org.umeframework.ems.desc.EntityDescManager;
import org.umeframework.ems.desc.dto.EmColDescDto;
import org.umeframework.ems.desc.dto.EmDescDto;
import org.umeframework.ems.desc.dto.EmEntDescDto;
import org.umeframework.ems.entity.EmConsCodeCfgDto;
import org.umeframework.ems.entity.EmConsSqlCfgDto;
import org.umeframework.ems.entity.EmTblCfgDto;
import org.umeframework.ems.entity.EmTblColCfgDto;
import org.umeframework.ems.jdbc.DynaDaoManager;
import org.umeframework.ems.message.MessageConst;

import javax.annotation.Resource;

/**
 * Entity define information implementation.
 *
 * @author Yue MA
 *
 */
@Service
public class EntityDescManagerImpl extends BaseComponent implements EntityDescManager, MessageConst {
	/**
	 * Cache configuration enable flag
	 */
	private boolean cacheEnable = false;
	/**
	 * Default read only columns
	 */
	private String[] defaultReadonlyCols = { "CREATE_AUTHOR", "CREATE_DATETIME", "UPDATE_AUTHOR", "UPDATE_DATETIME" };
	/**
	 * Dao selector
	 */
	@Resource
	private DynaDaoManager dynaDaoManager;
	/**
	 * MyBatis Dao instance which access configuration data
	 */
	@Resource(name = "doraRdbDao")
	private RdbDao masterCfgDao;
	/**
	 * CacheManager instance
	 */
	@Resource(name = "cacheManager")
	private CacheManager cacheManager;
	/**
	 * Table Desc Query SQLs define
	 */
	@Resource(name = "tableDescQuerySQLProp")
	private Properties tableDescQuerySQLProp;
	/**
	 * Table List Query SQLs define
	 */
	@Resource(name = "tableListQuerySQLProp")
	private Properties tableListQuerySQLProp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableDESCManager#getTableDescFromDictionary(java. lang.String)
	 */
	@Override
	public List<EmColDescDto> getTableDescFromDictionary(String dsId, String schema, String tblId) {
		String databaseType = dynaDaoManager.getDatebaseType(dsId);
		String queryId = tableDescQuerySQLProp.getProperty(databaseType);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("varTableId", tblId);
		if (schema != null) {
			queryId = queryId + " AND TABLE_SCHEMA = {varSchema}";
			params.put("varSchema", schema);
		}

		RdbDao dao = dynaDaoManager.getDao(dsId);
		List<EmColDescDto> result = dao.queryForObjectList(queryId, params, EmColDescDto.class);

		for (EmColDescDto e : result) {
			// clear unused property to reduce the elements transfer between
			// client and server
			if (e.getDataScale() != null && e.getDataScale() == 0) {
				e.setDataScale(null);
			}
			if (e.getDataLength() != null && e.getDataPrecision() != null && e.getDataLength().equals(e.getDataPrecision())) {
				e.setDataPrecision(null);
			}

			// use JDBC type replace DB type, only JDBC data type permit in
			// client side
			int dataJdbcType = new JdbcTypeMapping().mapDB2JDBC(e.getDataType().toUpperCase());
			e.setDataJdbcType(dataJdbcType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.EntityDESCManager#getTableListFromDictionary(java. lang.String, java.lang.String)
	 */
	@Override
	public List<String> getTableListFromDictionary(String dsId, String schema, String tableName) {
		if (StringUtil.isEmpty(schema)) {
			throw new ApplicationException(UME_EMS_MSG_005, new Object[] { schema, "Database" });
		}
		String queryId = tableListQuerySQLProp.getProperty(dynaDaoManager.getDatebaseType(dsId));

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("varSchema", schema);
		tableName = tableName == null ? "" : tableName;
		params.put("varNameLike", tableName);

		RdbDao dao = dynaDaoManager.getDao(dsId);
		List<EmTblCfgDto> dtos = dao.queryForObjectList(queryId, params, EmTblCfgDto.class);
		List<String> resultlist = new ArrayList<String>(dtos.size());

		for (EmTblCfgDto dto : dtos) {
			String entId = dto.getEntId();
			String entName = StringUtil.isEmpty(dto.getEntName()) ? dto.getEntId() : dto.getEntName();
			resultlist.add(entId + " " + entName);
			dto.clearDefaultProperties();
		}
		return resultlist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableDESCManager#getTableConfig(java.lang.String)
	 */
	@Override
	public EmDescDto getEntityConfig(String entId) {
		EmDescDto emDesc = new EmDescDto();

		// get table configuration
		EmTblCfgDto entCfgParam = new EmTblCfgDto();
		entCfgParam.setEntId(entId);
		EmTblCfgDto tblCfg = masterCfgDao.queryForObject(EmTblCfgDto.SQLID.FIND, entCfgParam, EmTblCfgDto.class);
		if (tblCfg == null) {
			throw new ApplicationException(UME_EMS_MSG_001, new Object[] { entId });
		}

		EmEntDescDto entDescDTO = new EmEntDescDto();
		entDescDTO.setEntId(tblCfg.getEntId());
		entDescDTO.setEntName(tblCfg.getEntName());
		entDescDTO.setEntType(tblCfg.getEntType());
		entDescDTO.setEntLayout(tblCfg.getEntLayout());
		entDescDTO.setRefTblDatasource(tblCfg.getRefTblDatasource());
		entDescDTO.setRefTblDisableCols(tblCfg.getRefTblDisableCols());
		entDescDTO.setRefTblHideCols(tblCfg.getRefTblHideCols());
		entDescDTO.setRefTblId(tblCfg.getRefTblId());
		entDescDTO.setRefTblReadonlyCols(tblCfg.getRefTblReadonlyCols());
		entDescDTO.setPreProcDisp(tblCfg.getPreProcDisp());
		entDescDTO.setPreProcSave(tblCfg.getPreProcSave());
		entDescDTO.setPreProcValidate(tblCfg.getPreProcValidate());
		entDescDTO.setCusEntityManager(tblCfg.getCusEntityManager());

		emDesc.setEntCfg(entDescDTO);
		// get table basic information
		String refTblSchema = null;
		String refTblId = entDescDTO.getRefTblId();
		if (refTblId.contains(".")) {
			refTblSchema = refTblId.substring(0, refTblId.indexOf("."));
			refTblId = refTblId.substring(refTblId.indexOf(".") + 1);
		}
		if (refTblSchema == null) {
			super.getLogger().warn("Schema was not specified for refer table:" + refTblId);
		}
		
		List<EmColDescDto> colDescDTOs = getTableDescFromDictionary(entDescDTO.getRefTblDatasource(), refTblSchema, refTblId);
		for (EmColDescDto e : colDescDTOs) {
			// put into index map for append configuration information
			if (StringUtil.isEmpty(e.getColName())) {
				e.setColName(e.getColId());
			}
			emDesc.addColCfg(e.getColId(), e);
		}

		Set<String> readonlyColsSet = emDesc.getReadonlyColSet();
		if (defaultReadonlyCols != null) {
			// add default columns into readonlyColsSet
			for (String defaultReadonlyCol : defaultReadonlyCols) {
				if (defaultReadonlyCol.contains(".")) {
					// match by table ID and column ID such as T01.C001
					String[] names = defaultReadonlyCol.split(".");
					if (names[0].equalsIgnoreCase(refTblId)) {
						readonlyColsSet.add(names[1]);
					}
				} else {
					readonlyColsSet.add(defaultReadonlyCol);
				}
			}
		}
		emDesc.setReadonlyColSet(readonlyColsSet);

		// get table column configuration
		// String tblId = entCfg.getRefTblId();
		EmTblColCfgDto colCfgParam = new EmTblColCfgDto();
		colCfgParam.setEntId(entId);
		List<EmTblColCfgDto> tblColCfgs = masterCfgDao.queryForObjectList(EmTblColCfgDto.SQLID.FIND_LIST, colCfgParam, EmTblColCfgDto.class);

		if (tblColCfgs != null) {
			for (EmTblColCfgDto e : tblColCfgs) {
				// append table customize configuration data
				EmColDescDto colDescDTO = emDesc.getColCfg(e.getColId());
				if (colDescDTO != null) {
					colDescDTO.setConstraintRef(e.getConstraintRef());
					colDescDTO.setConstraintType(e.getConstraintType());
					colDescDTO.setDataLengthMax(e.getDataLengthMax());
					colDescDTO.setDataLengthMin(e.getDataLengthMin());
					colDescDTO.setDataRangeMax(e.getDataRangeMax());
					colDescDTO.setDataRangeMin(e.getDataRangeMin());
					colDescDTO.setDataSubType(e.getDataSubType());
					colDescDTO.setDispType(e.getDispType());
					colDescDTO.setDispFormat(e.getDispFormat());
					colDescDTO.setEditFormat(e.getEditFormat());
					colDescDTO.setPreProcDisp(e.getPreProcDisp());
					colDescDTO.setPreProcSave(e.getPreProcSave());

					if (!StringUtil.isEmpty(e.getColName())) {
						colDescDTO.setColName(e.getColName());
					}
					if (e.getNotNull() != null) {
						colDescDTO.setNotNull(e.getNotNull());
					}
					if (!StringUtil.isEmpty(e.getDefaultValue())) {
						colDescDTO.setDefaultValue(e.getDefaultValue());
					}
					if (!StringUtil.isEmpty(colDescDTO.getConstraintRef()) && colDescDTO.getDispType() == null) {
						// default set display type with drop down when
						// constraint exist
						colDescDTO.setDispType(2);
					}
				}
			}
		}
		return emDesc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableDESCManager#getColumnRule(java.lang.String, java.lang.String)
	 */
	@Override
	public List<EmConsCodeCfgDto> getEntityColumnConstraint(String entId, String colId) {
		// get table column configuration
		EmTblColCfgDto colCfgParam = new EmTblColCfgDto();
		colCfgParam.setEntId(entId);
		colCfgParam.setColId(colId);

		EmTblColCfgDto colCfg = masterCfgDao.queryForObject(EmTblColCfgDto.SQLID.FIND_LIST, colCfgParam, EmTblColCfgDto.class);
		if (colCfg == null || colCfg.getConstraintRef() == null) {
			return null;
		}

		// get column refer rule configuration
		// query constraints define in code table firstly
		EmConsCodeCfgDto codeCfgParam = new EmConsCodeCfgDto();
		codeCfgParam.setConsId(colCfg.getConstraintRef());
		List<EmConsCodeCfgDto> consCodeCfgs = masterCfgDao.queryForObjectList(EmConsCodeCfgDto.SQLID.FIND_LIST, codeCfgParam, EmConsCodeCfgDto.class);

		// append constraints define by SQL query if exist
		EmConsSqlCfgDto sqlCfgParam = new EmConsSqlCfgDto();
		sqlCfgParam.setConsId(colCfg.getConstraintRef());
		EmConsSqlCfgDto consSqlCfg = masterCfgDao.queryForObject(EmConsSqlCfgDto.SQLID.FIND, sqlCfgParam, EmConsSqlCfgDto.class);
		if (consSqlCfg != null) {
			String sql = consSqlCfg.getConsSqlr();
			String ds = getEmDesc(entId).getEntCfg().getRefTblDatasource();

			RdbDao dao = dynaDaoManager.getDao(ds);
			List<Map<String, Object>> results = dao.queryForMapList(sql, null);
			int i = 1;
			for (Map<String, Object> e : results) {
				EmConsCodeCfgDto codeCfg = new EmConsCodeCfgDto();
				codeCfg.setConsId(consSqlCfg.getConsId());
				codeCfg.setConsName(consSqlCfg.getConsName());
				codeCfg.setConsSeq(i++);
				if (e.containsKey(EmConsCodeCfgDto.ColumnName.DISP_VALUE) && e.containsKey(EmConsCodeCfgDto.ColumnName.STORE_VALUE)) {
					String dispValue = e.get(EmConsCodeCfgDto.ColumnName.DISP_VALUE).toString();
					String storeValue = e.get(EmConsCodeCfgDto.ColumnName.STORE_VALUE).toString();
					dispValue = dispValue + "(" + storeValue + ")";
					codeCfg.setDispValue(dispValue);
					codeCfg.setStoreValue(storeValue);
				} else if (e.size() > 1) {
					Object[] arr = e.values().toArray();
					String dispValue = arr[0].toString();
					String storeValue = arr[1].toString();
					dispValue = dispValue + "(" + storeValue + ")";
					codeCfg.setDispValue(dispValue);
					codeCfg.setStoreValue(storeValue);
				}
				consCodeCfgs.add(codeCfg);
			}
		}

		return consCodeCfgs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableDESCManager#getAllColumnRule(java.lang.String)
	 */
	@Override
	public Map<String, List<EmConsCodeCfgDto>> getEntityConstraint(String entId) {
		EmTblColCfgDto colCfgParam = new EmTblColCfgDto();
		colCfgParam.setEntId(entId);
		List<EmTblColCfgDto> colCfgs = masterCfgDao.queryForObjectList(EmTblColCfgDto.SQLID.FIND_LIST, colCfgParam, EmTblColCfgDto.class);
		// get column refer rule configuration
		Map<String, List<EmConsCodeCfgDto>> consCodeCfgMap = new LinkedHashMap<String, List<EmConsCodeCfgDto>>();
		for (EmTblColCfgDto colCfg : colCfgs) {
			if (colCfg.getConstraintRef() != null) {
				List<EmConsCodeCfgDto> codeRules = getEntityColumnConstraint(entId, colCfg.getColId());
				consCodeCfgMap.put(colCfg.getColId(), codeRules);
			}
		}
		return consCodeCfgMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.EntityDESCManager#getEmDesc(java.lang.String)
	 */
	public EmDescDto getEmDesc(String entId) {
		if (cacheManager == null || !cacheEnable) {
			EmDescDto emDesc = getEntityConfig(entId);
			return emDesc;
		}

		EmDescDto emDesc = cacheManager.get(entId);
		if (emDesc != null) {
			return emDesc;
		} else {
			emDesc = getEntityConfig(entId);
			cacheManager.set(entId, emDesc);
			return emDesc;
		}
	}

	/**
	 * @return the cacheEnable
	 */
	public boolean isCacheEnable() {
		return cacheEnable;
	}

	/**
	 * @param cacheEnable
	 *            the cacheEnable to set
	 */
	public void setCacheEnable(boolean cacheEnable) {
		this.cacheEnable = cacheEnable;
	}

}
