package org.umeframework.ems.crud.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.umeframework.dora.dao.RdbDao;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.exception.ValidationException;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.ems.crud.EntityCrudManager;
import org.umeframework.ems.crud.SqlBuilder;
import org.umeframework.ems.desc.dto.EmDescDto;
import org.umeframework.ems.validator.impl.TableExValidator;
import org.umeframework.ems.validator.impl.TableValidator;

import javax.annotation.Resource;

/**
 * Implement CRUD function for table base description.
 * 
 * @author Yue MA
 */
@Service
public class EntityCrudManagerImpl extends BaseCrudComponent implements EntityCrudManager<Map<String, String>, Map<String, Object>> {
	/**
	 * SQL builder
	 */
	@Resource
	private SqlBuilder sqlBuilder;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableCRUDManager#retrieve(java.lang.String, java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> retrieve(String entId, Map<String, String> param) {
		EmDescDto desc = getEmDesc(entId);
		String dsId = desc.getEntCfg().getRefTblDatasource();
		// convert plant text parameters to actual object instance accord to
		// table DESC configuration
		Map<String, Object> actualParams = doParamConvert(desc, param);
		String dbType = super.getDatabaseType(dsId).toUpperCase();
		String sql = sqlBuilder.createSelectSQL(dbType, desc, param);
		List<Map<String, Object>> rows = null;
		try {
			RdbDao dao = super.getDynaDaoManager().getDao(dsId);
			rows = dao.queryForMapList(sql, actualParams);

		} catch (Exception ex) {
			throw new ApplicationException(ex, "APMSG20001", new Object[] { desc.getEntCfg().getEntId()});
		}

		return rows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableCRUDManager#retrieveByPrimaryKey(java.lang. String, java.util.Map)
	 */
	@Override
	public Map<String, Object> retrieveByPrimaryKey(String entId, Map<String, String> param) {
		EmDescDto desc = getEmDesc(entId);
		String dsId = desc.getEntCfg().getRefTblDatasource();
		// convert plant text parameters to actual object instance accord to
		// table DESC configuration
		Map<String, Object> actualParams = doParamConvert(desc, param);
		String dbType = super.getDatabaseType(dsId).toUpperCase();
		String sql = sqlBuilder.createSelectByPrimaryKeySQL(dbType, desc, param);
		Map<String, Object> row = null;
		try {
			RdbDao dao = super.getDynaDaoManager().getDao(dsId);
			row = dao.queryForMap(sql, actualParams);
			// do column level pre display process
			for (Map.Entry<String, Object> entry : row.entrySet()) {
				String colId = entry.getKey();
				Object colValue = entry.getValue();
				String colPreProcDisp = desc.getColCfg(colId).getPreProcDisp();
				if (colPreProcDisp != null) {
					colValue = doConvert(colPreProcDisp.split(","), colValue);
					row.put(colId, colValue);
				}
			}
			// do table level pre display process
			String tblPreProcDisp = desc.getEntCfg().getPreProcDisp();
			if (tblPreProcDisp != null) {
				row = doConvert(tblPreProcDisp.split(","), row);
			}
		} catch (Throwable ex) {
			throw new ApplicationException(ex, "APMSG20001", new Object[] { desc.getEntCfg().getEntId()});
		}
		return row;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableCRUDManager#retrieve(java.lang.String, java.util.Map)
	 */
	@Override
	public Long count(String entId, Map<String, String> param) {
		EmDescDto desc = getEmDesc(entId);
		String dsId = desc.getEntCfg().getRefTblDatasource();
		// convert plant text parameters to actual object instance accord to
		// table DESC configuration
		Map<String, Object> actualParams = doParamConvert(desc, param);
		String dbType = super.getDatabaseType(dsId).toUpperCase();
		String sql = sqlBuilder.createCountSQL(dbType, desc, param);
		BigInteger result = null;
		try {
			RdbDao dao = super.getDynaDaoManager().getDao(dsId);
			result = dao.queryForObject(sql, actualParams, BigInteger.class);
		} catch (Exception ex) {
			throw new ApplicationException(ex, "APMSG20001", new Object[] { desc.getEntCfg().getEntId()});
		}
		return result.longValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableCRUDManager#create(java.lang.String, java.util.Map)
	 */
	@Override
	@TransactionRequired
	public Integer create(String entId, Map<String, String> param, Boolean deleteIfExist) {
		if (deleteIfExist != null && deleteIfExist) {
			this.delete(entId, param);
		}

		EmDescDto desc = getEmDesc(entId);
		String dsId = desc.getEntCfg().getRefTblDatasource();

		// do item check
		doValidate(desc, param);
		// convert plant text parameters to actual object instance accord to
		// table DESC configuration
		Map<String, Object> actualParams = doParamConvert(desc, param);
		doColPreProcSaveHandler(desc, actualParams);
		doTblPreProcSaveHandler(desc, actualParams);
		String dbType = super.getDatabaseType(dsId).toUpperCase();
		String sql = sqlBuilder.createInsertSQL(dbType, desc, param);
		int result = 0;
		try {
			RdbDao dao = super.getDynaDaoManager().getDao(dsId);
			result = dao.update(sql, actualParams);
		} catch (Exception ex) {
			throw new ApplicationException(ex, "APMSG20005", new Object[] { desc.getEntCfg().getEntId()});
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableCRUDManager#update(java.lang.String, java.util.Map)
	 */
	@Override
	@TransactionRequired
	public Integer update(String entId, Map<String, String> param, Boolean createIfNotExist) {
		EmDescDto desc = getEmDesc(entId);
		String dsId = desc.getEntCfg().getRefTblDatasource();
		// do item check
		doValidate(desc, param);
		// convert plant text parameters to actual object instance accord to
		// table DESC configuration
		Map<String, Object> actualParams = doParamConvert(desc, param);
		doColPreProcSaveHandler(desc, actualParams);
		doTblPreProcSaveHandler(desc, actualParams);

		String dbType = super.getDatabaseType(dsId).toUpperCase();
		String sql = sqlBuilder.createUpdateSQL(dbType, desc, param);
		int result = 0;
		try {
			RdbDao dao = super.getDynaDaoManager().getDao(dsId);
			
			if (createIfNotExist != null && createIfNotExist) {
				Object exist = this.retrieveByPrimaryKey(entId, param);
				if (exist == null) {
					result = this.create(entId, param, false);
				} else {
					result = dao.update(sql, actualParams);
				}
			} else {
				result = dao.update(sql, actualParams);
			}
			result = dao.update(sql, actualParams);
		} catch (Exception ex) {
			throw new ApplicationException(ex, "APMSG20002", new Object[] { desc.getEntCfg().getEntId()});
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.umeframework.dora.service.em.TableCRUDManager#delete(java.lang.String, java.util.Map)
	 */
	@Override
	@TransactionRequired
	public Integer delete(String entId, Map<String, String> param) {
		EmDescDto desc = getEmDesc(entId);
		String dsId = desc.getEntCfg().getRefTblDatasource();

		// convert plant text parameters to actual object instance accord to
		// table DESC configuration
		Map<String, Object> convertedParams = doParamConvert(desc, param);
		String dbType = super.getDatabaseType(dsId).toUpperCase();
		String sql = sqlBuilder.createDeleteSQL(dbType, desc, param);
		int result = 0;
		try {
			RdbDao dao = super.getDynaDaoManager().getDao(dsId);
			result = dao.update(sql, convertedParams);
		} catch (Exception ex) {
			throw new ApplicationException(ex, "APMSG20004", new Object[] { desc.getEntCfg().getEntId()});
		}
		return result;
	}


	/* (non-Javadoc)
	 * @see org.umeframework.dora.service.em.data.crud.EntityCRUDManager#createMulti(java.util.Map, java.lang.Boolean)
	 */
	@Override
	@TransactionRequired
	public Map<String, Integer> createMulti(Map<String, List<Map<String, String>>> entMap, Boolean deleteExisted) {
		Map<String, Integer> results = new HashMap<String, Integer>();
		if (entMap != null) {
			for (Map.Entry<String, List<Map<String, String>>> entry : entMap.entrySet()) {
				String entId = entry.getKey();
				List<Map<String, String>> params = entry.getValue();
				int count = 0;
				for (Map<String, String> param : params) {
					count += this.create(entId, param, deleteExisted);
				}
				results.put(entId, count);
			}
		}
		return results;
	}

	/* (non-Javadoc)
	 * @see org.umeframework.dora.service.em.data.crud.EntityCRUDManager#updateMulti(java.util.Map, java.lang.Boolean)
	 */
	@Override
	@TransactionRequired
	public Map<String, Integer> updateMulti(Map<String, List<Map<String, String>>> entMap, Boolean createIfNotExist) {
		Map<String, Integer> results = new HashMap<String, Integer>();
		if (entMap != null) {
			for (Map.Entry<String, List<Map<String, String>>> entry : entMap.entrySet()) {
				String entId = entry.getKey();
				List<Map<String, String>> params = entry.getValue();
				int count = 0;
				for (Map<String, String> param : params) {
					count += this.update(entId, param, createIfNotExist);
				}
				results.put(entId, count);
			}
		}
		return results;
	}

	/* (non-Javadoc)
	 * @see org.umeframework.dora.service.em.data.crud.EntityCRUDManager#deleteMulti(java.util.Map)
	 */
	@Override
	@TransactionRequired
	public Map<String, Integer> deleteMulti(Map<String, List<Map<String, String>>> entMap) {
		Map<String, Integer> results = new HashMap<String, Integer>();
		if (entMap != null) {
			for (Map.Entry<String, List<Map<String, String>>> entry : entMap.entrySet()) {
				String entId = entry.getKey();
				List<Map<String, String>> params = entry.getValue();
				int count = 0;
				for (Map<String, String> param : params) {
					count += this.delete(entId, param);
				}
				results.put(entId, count);
			}
		}
		return results;
	}
	
	/**
	 * Invoke validate process
	 * 
	 * @param desc
	 * @param param
	 */
	@Override
	protected void doValidate(EmDescDto desc, Map<String, String> param) throws ValidationException {
		super.doValidate(desc, param);
		new TableValidator().doValidate(desc, param);
		new TableExValidator().doValidate(desc, param);
	}



}
