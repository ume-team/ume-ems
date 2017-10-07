package org.umeframework.ems.jdbc.dto;

import java.util.Collection;

/**
 * Data structure contain information of SQL execute result.<br>
 * Return array of SQLConsoleResultDTO while execute multiple SQLs.<br>
 * 
 * @author Yue MA
 *
 */
public class SqlConsoleResultDto implements java.io.Serializable {
    /**
     * INSERT,UPDATE,DELETE
     */
    public static final String SQL_TYPE_DML = "DML";
    /**
     * SELECT ...
     */
    public static final String SQL_TYPE_DQL = "DQL";
    /**
     * GRANT,COMMIT,ROLLBACK
     */
    public static final String SQL_TYPE_DCL = "DCL";
    /**
     * CREATE/ALTER TABLE/VIEW/INDEX/SYN/CLUSTER ...
     */
    public static final String SQL_TYPE_DDL = "DDL";
    /**
     * CREATE/ALTER TABLE/VIEW/INDEX/SYN/CLUSTER ...
     */
    public static final String SQL_TYPE_OTHER = "OTHER";
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * SQL input from client
     */
    private String sql;
    /**
     * SQL type (DDL/DML/DQL/DCL/OTHER)
     */
    private String type;
    /**
     * SQL execute successful flag
     * Success: true
     * Fail: false
     */
    private Boolean executeSuccess;
    /**
     * SQL execute message return to client
     */
    private String executeMessage;
    /**
     * SQL query result list of DQL
     */
    private Collection<Object> executeResultForQuery;

    /**
     * @return the sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * @param sql the sql to set
     */
    public void setSql(
            String sql) {
        this.sql = sql;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(
            String type) {
        this.type = type;
    }

    /**
     * @return the executeSuccess
     */
    public Boolean getExecuteSuccess() {
        return executeSuccess;
    }

    /**
     * @param executeSuccess the executeSuccess to set
     */
    public void setExecuteSuccess(
            Boolean executeSuccess) {
        this.executeSuccess = executeSuccess;
    }

    /**
     * @return the executeMessage
     */
    public String getExecuteMessage() {
        return executeMessage;
    }

    /**
     * @param executeMessage the executeMessage to set
     */
    public void setExecuteMessage(
            String executeMessage) {
        this.executeMessage = executeMessage;
    }

    /**
     * @return the executeResultForQuery
     */
    public Collection<Object> getExecuteResultForQuery() {
        return executeResultForQuery;
    }

    /**
     * @param executeResultForQuery the executeResultForQuery to set
     */
    public void setExecuteResultForQuery(
            Collection<Object> executeResultForQuery) {
        this.executeResultForQuery = executeResultForQuery;
    }
}
