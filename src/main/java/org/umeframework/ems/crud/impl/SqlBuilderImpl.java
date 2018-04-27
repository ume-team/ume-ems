/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.crud.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.umeframework.dora.context.SessionContext;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.ems.crud.SqlBuilder;
import org.umeframework.ems.desc.dto.EmColDescDto;
import org.umeframework.ems.desc.dto.EmDescDto;
import org.umeframework.ems.desc.dto.EmEntDescDto;
import org.umeframework.ems.message.MessageConst;

/**
 * Implement CRUD SQL building function for table base EmDescDTO.
 * 
 * @author Yue MA
 */
@Service
public class SqlBuilderImpl implements SqlBuilder,MessageConst {
    /**
     * Default max query record start from 0
     */
    private String defaultQueryStartRowNum = "0";
    /**
     * Default max query record numbers
     */
    private String defaultQueryMaxLimit = "50";
    /**
     * Default insert property define: first create user
     */
    private String columnCreateUser = "CREATE_AUTHOR";
    /**
     * Default insert property define: first create time stamp
     */
    private String columnCreateTime = "CREATE_DATETIME";
    /**
     * Default insert property define: last update user
     */
    private String columnLastUpdateUser = "UPDATE_AUTHOR";
    /**
     * Default insert property define: last update time stamp
     */
    private String columnLastUpdateTime = "UPDATE_DATETIME";

    /**
     * value of last update time
     * 
     * @param databaseType
     * @return
     */
    protected String getValueOfColumnLastUpdateTime(String databaseType) {
        String result = "current_timestamp()";
        if (databaseType.equalsIgnoreCase("DB2")) {
            result = "CURRENT TIMESTAMP";
        } else if (databaseType.equalsIgnoreCase("ORACLE")) {
            result = "CURRENT_TIMESTAMP";
        } else if (databaseType.equalsIgnoreCase("POSTGRES") || databaseType.equalsIgnoreCase("POSTGRESQL")) {
            result = "CURRENT_TIMESTAMP";
        }

        return result;
    }

    /**
     * value of last update user
     * 
     * @return
     */
    protected String getValueOfColumnLastUpdateUser() {
        return SessionContext.open().getUid();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.umeframework.dora.service.em.EntityCRUDSQLBuilder#createInsertSQL(org.umeframework.dora.service.em.dto.EmDescDTO, java.util.Map)
     */
    @Override
    public String createInsertSQL(
            String databaseType,
            EmDescDto desc,
            Map<String, String> plantTextParam) {

        EmEntDescDto tblCfg = desc.getEntCfg();
        Map<String, EmColDescDto> colCfgMap = desc.getColCfgMap();
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(tblCfg.getRefTblId());
        sql.append("(");
        StringBuilder sqlParam = new StringBuilder();
        int i = 0;
        for (EmColDescDto colCfg : colCfgMap.values()) {
            String colId = colCfg.getColId();
            if (!plantTextParam.containsKey(colId)) {
                continue;
            }
            if (i > 0) {
                sql.append(",");
                sqlParam.append(",");
            }
            sql.append(colId);
            sqlParam.append("{" + colId + "}");
            i++;
        }

        sql.append("," + columnCreateUser + "," + columnCreateTime + "," + columnLastUpdateUser + "," + columnLastUpdateTime);
        sql.append(") VALUES (");
        sql.append(sqlParam);
        sql.append(",'" + getValueOfColumnLastUpdateUser() + "'");
        sql.append("," + getValueOfColumnLastUpdateTime(databaseType));
        sql.append(",'" + getValueOfColumnLastUpdateUser() + "'");
        sql.append("," + getValueOfColumnLastUpdateTime(databaseType));
        sql.append(")");

        return sql.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.umeframework.dora.service.em.EntityCRUDSQLBuilder#createUpdateSQL(org.umeframework.dora.service.em.dto.EmDescDTO, java.util.Map)
     */
    @Override
    public String createUpdateSQL(
            String databaseType,
            EmDescDto desc,
            Map<String, String> plantTextParam) {
        EmEntDescDto tblCfg = desc.getEntCfg();
        Map<String, EmColDescDto> colCfgMap = desc.getColCfgMap();
        Set<String> readonlyCols = desc.getReadonlyColSet();
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(tblCfg.getRefTblId());
        sql.append(" SET ");

        int i = 0;
        for (EmColDescDto colCfg : colCfgMap.values()) {
            String colId = colCfg.getColId();
            if (!plantTextParam.containsKey(colId)) {
                continue;
            }
            if (readonlyCols.contains(colId)) {
                continue;
            }
            if (colCfg.getPkFlag() != null && colCfg.getPkFlag() == 1) {
                continue;
            }

            if (i > 0) {
                sql.append(",");
            }
            sql.append(colId);
            sql.append("=");
            sql.append("{");
            sql.append(colId);
            sql.append("}");
            i++;
        }

        // add auto updated columns
        sql.append("," + columnLastUpdateUser + "=");
        sql.append("'" + getValueOfColumnLastUpdateUser() + "'");
        sql.append("," + columnLastUpdateTime + "=" + getValueOfColumnLastUpdateTime(databaseType));

        sql.append(" WHERE ");
        i = 0;
        for (EmColDescDto colCfg : colCfgMap.values()) {
            String colId = colCfg.getColId();
            if (colCfg.getPkFlag() != null && colCfg.getPkFlag() == 1) {
                if (i > 0) {
                    sql.append(" AND ");
                }
                sql.append(colId);
                sql.append("=");
                sql.append("{");
                sql.append(colId);
                sql.append("}");
                i++;
            }
        }
        return sql.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.umeframework.dora.service.em.EntityCRUDSQLBuilder#createDeleteSQL(org.umeframework.dora.service.em.dto.EmDescDTO, java.util.Map)
     */
    @Override
    public String createDeleteSQL(
            String databaseType,
            EmDescDto desc,
            Map<String, String> plantTextParam) {
        EmEntDescDto tblCfg = desc.getEntCfg();
        Map<String, EmColDescDto> colCfgMap = desc.getColCfgMap();
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(tblCfg.getRefTblId());
        sql.append(" WHERE ");
        int i = 0;
        for (EmColDescDto colCfg : colCfgMap.values()) {
            String colId = colCfg.getColId();
            if (colCfg.getPkFlag() != null && colCfg.getPkFlag() == 1) {
                if (i > 0) {
                    sql.append(" AND ");
                }
                sql.append(colId);
                sql.append("=");
                sql.append("{");
                sql.append(colId);
                sql.append("}");
                i++;
            }
        }
        return sql.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.umeframework.dora.service.em.EntityCRUDSQLBuilder#createCountSQL(org.umeframework.dora.service.em.dto.EmDescDTO, java.util.Map)
     */
    @Override
    public String createCountSQL(
            String databaseType,
            EmDescDto desc,
            Map<String, String> param) {
        EmEntDescDto tblCfg = desc.getEntCfg();
        StringBuilder sql = new StringBuilder("SELECT count(*) AS \"countSize\" FROM ");
        sql.append(tblCfg.getRefTblId());
        sql.append(createWhereConditions(desc, param, false));

        return sql.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.umeframework.dora.service.em.EntityCRUDSQLBuilder#createSelectSQL(org.umeframework.dora.service.em.dto.EmDescDTO, java.util.Map)
     */
    @Override
    public String createSelectSQL(
            String databaseType,
            EmDescDto desc,
            Map<String, String> param) {
        EmEntDescDto tblCfg = desc.getEntCfg();
        StringBuilder sql = new StringBuilder("SELECT ");
        Map<String, EmColDescDto> colCfgMap = desc.getColCfgMap();

        Set<String> hiddenColSet = desc.getHideColSet();
        Set<String> disableColSet = desc.getDisableColSet();

        // for summary list query
        int flag1 = 0;
        for (String e : colCfgMap.keySet()) {
            if (!hiddenColSet.contains(e) && !disableColSet.contains(e)) {
                if (flag1 > 0) {
                    sql.append(",");
                }
                sql.append(e);
                flag1++;
            }
        }

        sql.append(" FROM ");
        sql.append(tblCfg.getRefTblId());

        sql.append(createWhereConditions(desc, param, false));

        if (param.containsKey("theOrderByCondition")) {
            String theOrderByCondition = param.get("theOrderByCondition");
            sql.append(" ORDER BY ");
            sql.append(theOrderByCondition);
        } else if (desc.getPrimaryKeyColSet().size() > 0) {
            sql.append(" ORDER BY ");
            int flag2 = 0;
            for (String pkColId : desc.getPrimaryKeyColSet()) {
                if (flag2 > 0) {
                    sql.append(",");
                }
                sql.append(pkColId);
                flag2++;
            }

        }
        // Append fetch size for MySQL, default start row number set to 0 and default max fetch size set to 2048
        sql.append(" LIMIT ");
        if (param.containsKey("theFetchStart")) {
            sql.append(param.get("theFetchStart"));
        } else {
            sql.append(defaultQueryStartRowNum);
        }
        sql.append(",");
        if (param.containsKey("theFetchSize")) {
            sql.append(param.get("theFetchSize"));
        } else {
            sql.append(defaultQueryMaxLimit);
        }

        return sql.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.umeframework.dora.service.em.EntityCRUDSQLBuilder#createSelectByPrimaryKeySQL(org.umeframework.dora.service.em.dto.EmDescDTO, java.util.Map)
     */
    @Override
    public String createSelectByPrimaryKeySQL(
            String databaseType,
            EmDescDto desc,
            Map<String, String> param) {
        EmEntDescDto tblCfg = desc.getEntCfg();
        StringBuilder sql = new StringBuilder("SELECT ");
        Map<String, EmColDescDto> colCfgMap = desc.getColCfgMap();
        Set<String> disableColSet = desc.getDisableColSet();

        int cnt = 0;
        for (String e : colCfgMap.keySet()) {
            if (!disableColSet.contains(e)) {
                if (cnt > 0) {
                    sql.append(",");
                }
                sql.append(e);
                cnt++;
            }
        }

        sql.append(" FROM ");
        sql.append(tblCfg.getRefTblId());
        sql.append(createWhereConditions(desc, param, true));

        return sql.toString();
    }

    /**
     * Create where conditions for select
     * 
     * @param desc
     *            - table configuration
     * @param param
     *            - query parameters contains by Map
     * @return
     */
    protected String createWhereConditions(
            EmDescDto desc,
            Map<String, String> param,
            boolean selectByPK) {
        StringBuilder sql = new StringBuilder();
        StringBuilder paramConditions = new StringBuilder();
        if (param != null) {
            if (!selectByPK && param.containsKey("theSQLCondition")) {
                String theSQLCondition = param.get("theSQLCondition");
                sql.append(" WHERE ");
                sql.append(theSQLCondition);

            }

            Set<String> selectParam = new HashSet<String>();

            // Check if all query params are pk
            if (selectByPK) {
                Set<String> primaryKeySet = desc.getPrimaryKeyColSet();
                if (primaryKeySet != null) {
                    boolean allKeysExsits = true;
                    for (Map.Entry<String, String> entry : param.entrySet()) {
                        String key = entry.getKey();
                        if (!primaryKeySet.contains(key)) {
                            allKeysExsits = false;
                            throw new ApplicationException(UME_EMS_MSG_201, new Object[] { desc.getEntCfg().getEntId()});
                        }
                    }
                    if (allKeysExsits) {
                        selectParam = primaryKeySet;
                    }
                }
            } else {
                selectParam = param.keySet();
            }

            for (String key : selectParam) {
                EmColDescDto colCfg = desc.getColCfg(key);
                if (colCfg != null) {
                    if (paramConditions.length() == 0) {
                        paramConditions.append(" WHERE ");
                    } else {
                        paramConditions.append(" AND ");
                    }
                    paramConditions.append(colCfg.getColId());
                    paramConditions.append("=");
                    paramConditions.append("{");
                    paramConditions.append(key);
                    paramConditions.append("}");
                }
            }

            sql.append(paramConditions);

            if (param.containsKey("theGroupByCondition")) {
                String theGroupByCondition = param.get("theGroupByCondition");
                sql.append(" GROUP BY ");
                sql.append(theGroupByCondition);
            }
        }
        return sql.toString();
    }

    /**
     * @return the defaultQueryStartRowNum
     */
    public String getDefaultQueryStartRowNum() {
        return defaultQueryStartRowNum;
    }

    /**
     * @param defaultQueryStartRowNum the defaultQueryStartRowNum to set
     */
    public void setDefaultQueryStartRowNum(
            String defaultQueryStartRowNum) {
        this.defaultQueryStartRowNum = defaultQueryStartRowNum;
    }

    /**
     * @return the defaultQueryMaxLimit
     */
    public String getDefaultQueryMaxLimit() {
        return defaultQueryMaxLimit;
    }

    /**
     * @param defaultQueryMaxLimit the defaultQueryMaxLimit to set
     */
    public void setDefaultQueryMaxLimit(
            String defaultQueryMaxLimit) {
        this.defaultQueryMaxLimit = defaultQueryMaxLimit;
    }

    /**
     * @return the columnCreateUser
     */
    public String getColumnCreateUser() {
        return columnCreateUser;
    }

    /**
     * @param columnCreateUser the columnCreateUser to set
     */
    public void setColumnCreateUser(
            String columnCreateUser) {
        this.columnCreateUser = columnCreateUser;
    }

    /**
     * @return the columnCreateTime
     */
    public String getColumnCreateTime() {
        return columnCreateTime;
    }

    /**
     * @param columnCreateTime the columnCreateTime to set
     */
    public void setColumnCreateTime(
            String columnCreateTime) {
        this.columnCreateTime = columnCreateTime;
    }

    /**
     * @return the columnLastUpdateUser
     */
    public String getColumnLastUpdateUser() {
        return columnLastUpdateUser;
    }

    /**
     * @param columnLastUpdateUser the columnLastUpdateUser to set
     */
    public void setColumnLastUpdateUser(
            String columnLastUpdateUser) {
        this.columnLastUpdateUser = columnLastUpdateUser;
    }

    /**
     * @return the columnLastUpdateTime
     */
    public String getColumnLastUpdateTime() {
        return columnLastUpdateTime;
    }

    /**
     * @param columnLastUpdateTime the columnLastUpdateTime to set
     */
    public void setColumnLastUpdateTime(
            String columnLastUpdateTime) {
        this.columnLastUpdateTime = columnLastUpdateTime;
    }
}
