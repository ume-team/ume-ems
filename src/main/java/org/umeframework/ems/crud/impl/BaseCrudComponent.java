/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.crud.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.exception.ValidationException;
import org.umeframework.dora.service.BaseComponent;
import org.umeframework.ems.desc.EntityDescManager;
import org.umeframework.ems.desc.dto.EmColDescDto;
import org.umeframework.ems.desc.dto.EmDescDto;
import org.umeframework.ems.jdbc.DynaDaoManager;
import org.umeframework.ems.util.JdbcTypeUtil;

/**
 * Base CRUD function for table base description.
 * 
 * @author Yue MA
 */
public abstract class BaseCrudComponent extends BaseComponent {
	/**
	 * Data source selector
	 */
	@Resource
	private DynaDaoManager dynaDaoManager;

	/**
	 * EntityDESCManager instance
	 */
	@Resource
	private EntityDescManager entityDESCManager;

	/**
	 * converterFactory
	 */
	@Resource(name = "converterFactory")
	private org.umeframework.ems.util.UtilFactory converterFactory;

	/**
	 * validatorFactory
	 */
	@Resource(name = "validatorFactory")
	private org.umeframework.ems.util.UtilFactory validatorFactory;

	/**
	 * Get entity DESC information
	 * 
	 * @param entId
	 *            - view name which mapping to table name
	 * @return
	 */
	protected EmDescDto getEmDesc(String entId) {
		return entityDESCManager.getEmDesc(entId);
	}

	/**
	 * Convert plant text parameters to actual parameters accord to JDBC type
	 * information.
	 * 
	 * @param desc
	 *            - table configuration information
	 * @param plantTextParam
	 *            - parameters as plant text format
	 * @return - actual object type parameters
	 */
	protected Map<String, Object> doParamConvert(EmDescDto desc, Map<String, String> plantTextParam) {
		if (plantTextParam == null) {
			return null;
		}
		Map<String, Object> mapObj = new LinkedHashMap<String, Object>();
		for (Map.Entry<String, String> entry : plantTextParam.entrySet()) {
			String colId = entry.getKey();
			if (!desc.getColCfgMap().containsKey(colId)) {
				continue;
			}
			String colStrValue = entry.getValue();
			EmColDescDto colCfg = desc.getColCfgMap().get(colId);
			try {
				Object colActValue = JdbcTypeUtil.stringToObject(colStrValue, colCfg.getDataJdbcType());
				mapObj.put(colId, colActValue);
			} catch (Exception ex) {
				throw new ApplicationException(ex, "APMSG20003", new Object[] { colId });
			}
		}

		return mapObj;
	}

	/**
	 * Invoke pre process before save column data
	 * 
	 * @param desc
	 * @param param
	 * @return
	 */
	protected void doColPreProcSaveHandler(EmDescDto desc, Map<String, Object> param) {
		if (param == null) {
			return;
		}
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			String colId = entry.getKey();
			String colPreProcSave = desc.getColCfg(colId).getPreProcSave();
			if (!desc.getColCfgMap().containsKey(colId) || colPreProcSave == null) {
				continue;
			}
			try {
				Object colValue = entry.getValue();
				colValue = doConvert(colPreProcSave.split(","), colValue);
				param.put(colId, colValue);
			} catch (Throwable ex) {
				throw new ApplicationException(ex, "APMSG20003", new Object[] { colId });
			}
		}
	}

	/**
	 * Invoke pre process before save table data
	 * 
	 * @param desc
	 * @param param
	 * @return
	 */
	protected void doTblPreProcSaveHandler(EmDescDto desc, Map<String, Object> param) {
		if (param == null) {
			return;
		}
		try {
			String preProcSave = desc.getEntCfg().getPreProcSave();
			if (preProcSave != null) {
				param = doConvert(preProcSave.split(","), param);
			}
		} catch (Throwable ex) {
			throw new ApplicationException(ex, "APMSG20003", new Object[] { desc.getEntCfg().getEntId() });
		}
	}

	/**
	 * Convert parameters by customize converters
	 * 
	 * @param converterList
	 * @param param
	 * @return
	 * @throws Exception
	 */
	protected <E> E doConvert(String[] converterList, E params) throws Throwable {
		for (String converterName : converterList) {
			if (converterFactory.has(converterName)) {
				params = converterFactory.execute(converterName, params);
			}
		}
		return params;
	}

	/**
	 * Invoke validate process
	 * 
	 * @param desc
	 * @param param
	 */
	protected void doValidate(EmDescDto desc, Map<String, String> param) throws ValidationException {
		ValidationException ve = new ValidationException();
		String preProcValidate = desc.getEntCfg().getPreProcValidate();
		if (preProcValidate != null) {
			String[] arr = preProcValidate.split(",");
			for (String validator : arr) {
				if (validatorFactory.has(validator)) {
					try {
						validatorFactory.execute(validator, param);
					} catch (ValidationException e) {
						ve.add(e);
					} catch (Throwable e) {
						throw new ValidationException(e);
					}
				}
			}
		}
		if (ve.size() > 0) {
			throw ve;
		}
	}

	/**
	 * Get database type by data source id
	 * 
	 * @param dsId
	 * @return
	 */
	protected String getDatabaseType(String dsId) {
		return dynaDaoManager.getDatebaseType(dsId);
	}

	/**
	 * @return the dynaDaoManager
	 */
	public DynaDaoManager getDynaDaoManager() {
		return dynaDaoManager;
	}

}
