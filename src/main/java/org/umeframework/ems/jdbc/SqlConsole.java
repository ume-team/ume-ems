package org.umeframework.ems.jdbc;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.umeframework.ems.jdbc.dto.SqlConsoleResultDto;

/**
 * Define the simple SQL console service to execute SQL statement input from client.<br>
 * 
 * @author Yue MA
 *
 */
public interface SqlConsole {
    
    /**
     * Return type of input SQL.
     * 
     * @param sql
     * @return
     */
    String typeOfSQL(
            String sql);
    
    /**
     * queryAsMapList
     * 
     * @param dsId
     * @param table
     * @param fields
     * @param condition
     * @return
     */
    Collection<Map<String, Object>> queryAsMapList(String dsId, String table, String field, Map<String, String> condition);
    
    /**
     * queryAsObjectList
     * 
     * @param dsId
     * @param table
     * @param field
     * @param condition
     * @return
     */
    Collection<Object> queryAsObjectList(String dsId, String table, String field, Map<String, String> condition);

    
    /**
     * Execute multiple SQL statements.
     * 
     * @param dsId
     * @param sqls
     * @return
     */
    List<SqlConsoleResultDto> executeMulti(
            String dsId,
            String[] sqls,
            Boolean autoCommit);
    
    /**
     * Execute generic SQL which may contains multiple SQLs which split by ';'
     * 
     * @param dsId
     * @param sqls
     * @return
     */
    List<SqlConsoleResultDto> execute(
            String dsId,
            String sqls,
            Boolean autoCommit);
    
    /**
     * Execute multiple SQLs which store in local file split by ';'
     * 
     * @param dsId
     * @param filePath
     * @param charSet
     * @return
     */
    List<SqlConsoleResultDto> executeFile(
            String dsId,
            String filePath,
            String charSet);
    
    /**
     * Split string with input split char
     * 
     * @param str
     * @param splitChar
     * @param transferChar
     * @return
     */
    String[] splitStr(String str, String splitChar, String transferChar);

}
