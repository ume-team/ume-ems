/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.common;

import java.util.List;

/**
 * System resource access interface define.<br>
 * 
 * @author Yue MA
 */
public interface ResourceManager {

    /**
     * Import tables into system resource configuration.
     * 
     * @param dsId
     *            - data source
     * @param schema
     *            - table schema/user
     * @param resGroup
     *            - group name
     * @param tableIdLike
     *            - table ID match string
     * @param maxLimit
     *            - max retrieve records limit
     * 
     * @return
     */
    List<String> importTableResource(
            String dsId,
            String schema,
            String resGroup,
            String tableIdLike,
            Integer maxLimit);

    /**
     * Remove entity and resource configuration by Group and table.
     * 
     * @param resGroup
     *            - group name
     * @param tableId
     *            - table
     * @return
     */
    List<String> removeTableResource(
            String resGroup,
            String tableId);

    /**
     * Remove entity and resource configuration by Group.
     * 
     * @param resGroup
     *            - group name
     * @return
     */
    List<String> removeGroupResource(
            String resGroup);

}
