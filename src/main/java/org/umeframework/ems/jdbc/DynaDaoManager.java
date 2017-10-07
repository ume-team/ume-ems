package org.umeframework.ems.jdbc;

import org.umeframework.dora.dao.RdbDao;
import org.umeframework.dora.exception.SystemException;
import org.umeframework.ems.entity.EmDsCfgDto;

/**
 * Dynamic Dao and data source manager
 * 
 * @author Yue MA
 */
public interface DynaDaoManager {
	/**
	 * Get database input data source
	 * 
	 * @return
	 */
	String getDatebaseType(String dsId);

	/**
	 * Get Dao instance bind with input data source
	 * 
	 * @param dsId
	 * @return
	 */
	RdbDao getDao(String dsId);

	/**
	 * Begin JDBC transaction for input data source
	 * 
	 * @param dsId
	 */
	void beginTransaction(String dsId);

	/**
	 * Commit JDBC transaction for input data source
	 * 
	 * @param dsId
	 */
	void commitTransaction(String dsId);

	/**
	 * Roll back JDBC transaction for input data source
	 * 
	 * @param dsId
	 */
	void rollbackTransaction(String dsId);
	
	/**
	 * Init data source pool and Dao instance with input configuration
	 * 
	 * @param cfg
	 */
	void initDataSource(EmDsCfgDto cfg) throws SystemException;
	
	/**
	 * Init data source pool and Dao instance with input configuration and save configuration into database
	 * 
	 * @param cfg
	 */
	void addDataSource(EmDsCfgDto cfg);

}
