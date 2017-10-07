package org.umeframework.ems.crud;

import java.util.Map;

import org.umeframework.ems.desc.dto.EmDescDto;

/**
 * Definae CRUD SQL building function for table base description.
 * 
 * @author mayue
 *
 */
public interface SqlBuilder {
	/**
	 * Create insert SQL
	 * 
	 * @param desc
	 *            - table configuration
	 * @param plantTextParam
	 *            - parameters as plant text format
	 */
	String createInsertSQL(
	        String databaseType,
			EmDescDto desc,
			Map<String, String> plantTextParam);
	
	/**
	 * Create update SQL
	 * 
	 * @param desc
	 *            - table configuration
	 * @param plantTextParam
	 *            - parameters as plant text format
	 * @return
	 */
	String createUpdateSQL(
            String databaseType,
			EmDescDto desc,
			Map<String, String> plantTextParam);
	
	/**
	 * Create delete SQL
	 * 
	 * @param desc
	 *            - table configuration
	 * @param plantTextParam
	 *            - parameters as plant text format
	 * @return
	 */
	String createDeleteSQL(
            String databaseType,
			EmDescDto desc,
			Map<String, String> plantTextParam);

	/**
	 * Create count SQL
	 * 
	 * @param desc
	 *            - table configuration
	 * @param param
	 *            - query parameters contains by Map
	 * @return
	 */
	String createCountSQL(
            String databaseType,
			EmDescDto desc,
			Map<String, String> param);
	
	/**
	 * Create select SQL
	 * 
	 * @param desc
	 *            - table configuration
	 * @param param
	 *            - query parameters contains by Map
	 * @return
	 */
	String createSelectSQL(
            String databaseType,
			EmDescDto desc,
			Map<String, String> param);
	
	/**
	 * Create select by primary key SQL
	 * 
	 * @param desc
	 *            - table configuration
	 * @param param
	 *            - query parameters contains by Map
	 * @return
	 */
	String createSelectByPrimaryKeySQL(
            String databaseType,
			EmDescDto desc,
			Map<String, String> param);
	
}
