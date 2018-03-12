/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.crud;

import java.util.List;
import java.util.Map;

/**
 * Entity CRUD functional interface declare.
 * 
 * @author Yue MA
 */
public interface EntityCrudManager<P, R> {

	/**
	 * Retrieve entity list
	 * 
	 * @param entId
	 *            - entity name
	 * @param param
	 *            - query parameter as text map structure
	 * @return - list of query result set
	 */
	List<R> retrieve(String entId, P param);

	/**
	 * Retrieve entity by unique key
	 * 
	 * @param entId
	 *            - entity name
	 * @param param
	 *            - query parameter as text map structure
	 * @return - query result object
	 */
	R retrieveByPrimaryKey(String entId, P param);

	/**
	 * Count matched entity number
	 * 
	 * @param entId
	 *            - entity name
	 * @param param
	 *            - query parameter as text map structure
	 * @return - list of query result set
	 */
	Long count(String entId, P param);

	/**
	 * Create one entity
	 * 
	 * @param entId
	 *            - entity name
	 * @param param
	 *            - entity data as text map structure, not null field must be include
	 * @param deleteIfExist
	 *            - delete exist record if the insert one existed
	 * @return - success as 1, fail as 0
	 */
	Integer create(String entId, P param, Boolean deleteIfExist);

	/**
	 * Update one entity
	 * 
	 * @param entId
	 *            - entity name
	 * @param param
	 *            - entity data as text map structure, primary key field must be include
	 * @param createIfNotExist
	 *            - create new entity if not exist
	 * @return - success as 1, fail as 0
	 */
	Integer update(String entId, P param, Boolean createIfNotExist);

	/**
	 * Delete one entity by unique key
	 * 
	 * @param entId
	 *            - entity name
	 * @param param
	 *            - entity primary key field as text map structure
	 * @return - success as 1, fail as 0
	 */
	Integer delete(String entId, P param);

	/**
	 * Create multiple entities
	 * 
	 * @param entMap
	 *            - index by entity map, key as entity ID and value as entity list
	 * @param deleteExisted
	 *            - delete exist record if the insert one existed
	 * @return
	 */
	Map<String, Integer> createMulti(Map<String, List<Map<String, String>>> entMap, Boolean deleteExisted);

	/**
	 * Update multiple entities
	 * 
	 * @param entMap
	 *            - index by entity map, key as entity ID and value as entity list
	 * @param createIfNotExist
	 *            - create new entity if not exist
	 * @return
	 */
	Map<String, Integer> updateMulti(Map<String, List<Map<String, String>>> entMap, Boolean createIfNotExist);

	/**
	 * Delete multiple entities
	 * 
	 * @param entMap
	 *            - index by entity map, key as entity ID and value as entity list
	 * @return
	 */
	Map<String, Integer> deleteMulti(Map<String, List<Map<String, String>>> entMap);

}
