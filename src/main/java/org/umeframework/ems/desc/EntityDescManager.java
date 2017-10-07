package org.umeframework.ems.desc;

import java.util.List;
import java.util.Map;

import org.umeframework.ems.desc.dto.EmColDescDto;
import org.umeframework.ems.desc.dto.EmDescDto;
import org.umeframework.ems.entity.EmConsCodeCfgDto;

/**
 * Entity define information interface declare.
 * 
 * @author Yue MA
 *
 */
public interface EntityDescManager {

    /**
     * Get table DESC information from DB dictionary
     *
     * @param dsId
     * @param schema
     * @param tblId
     * @return
     */
    List<EmColDescDto> getTableDescFromDictionary(
            String dsId,
            String schema,
            String tblId);

    /**
     * Get table list information from DB dictionary
     * 
     * @param dsId
     * @param schema
     * @param namingMode
     * @return
     */
    List<String> getTableListFromDictionary(
            String dsId,
            String schema,
            String tblIdLike);

    /**
     * Get table basic configuration (from RDBMS system) and extend configuration (from MasterConfig system)
     * 
     * @param tableName
     *            - table name
     * @return - table define information as EmTblDescDTO structure
     */
    EmDescDto getEntityConfig(
            String tableName);

    /**
     * Get column constraint by input column name
     * 
     * @param tableName
     *            - table name
     * @param columnName
     *            - column name
     * @return - column's display/store pairs of the table
     */
    List<EmConsCodeCfgDto> getEntityColumnConstraint(
            String tableName,
            String columnName);

    /**
     * Get all column constraint by input table name
     * 
     * @param tableName
     *            - table name
     * @return - all column's display/store pairs of the table
     */
    Map<String, List<EmConsCodeCfgDto>> getEntityConstraint(
            String tableName);

    /**
     * Get all entity configurations
     * 
     * @param entId
     * @return
     */
    EmDescDto getEmDesc(
            String entId);

}
