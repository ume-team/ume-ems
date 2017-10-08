/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.umeframework.dora.exception.ApplicationException;
import org.umeframework.dora.service.BaseDBComponent;
import org.umeframework.dora.transaction.TransactionRequired;
import org.umeframework.dora.util.DateUtil;
import org.umeframework.dora.util.StringUtil;
import org.umeframework.ems.common.ResourceManager;
import org.umeframework.ems.desc.EntityDescManager;
import org.umeframework.ems.entity.EmDsCfgDto;
import org.umeframework.ems.entity.EmResourceDto;
import org.umeframework.ems.entity.EmTblCfgDto;
import org.umeframework.ems.jdbc.DynaDaoManager;

import javax.annotation.Resource;

/**
 * System resource access implementation.<br>
 * 
 * @author Yue MA
 *
 */
@Service
public class ResourceManagerImpl extends BaseDBComponent implements ResourceManager {
    /**
     * EntityDESCManager instance
     */
    @Resource
    private EntityDescManager entityDESCManager;
    
    /**
     * DynaDaoManager instance
     */
    @Resource
    private DynaDaoManager dynaDaoManager;

    /**
     * Default max limit when import table list as resource
     */
    private int defaultImportMaxLimit = 1000;

    /*
     * (non-Javadoc)
     * 
     * @see org.umeframework.dora.service.em.sys.ResourceManager#importTables(java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Override
    public List<String> importTableResource(
            String dsId,
            String schema,
            String resGroup,
            String tableIdLike,
            Integer maxLimit) {

        if (StringUtil.isEmpty(resGroup)) {
            // use current time stamp as default group name
            java.util.Date sysDate = new java.util.Date(System.currentTimeMillis());
            resGroup = DateUtil.dateToString(sysDate, DateUtil.FORMAT.YYMMDDHHMMSS);
        }

        List<String> tblIdAndNameList = entityDESCManager.getTableListFromDictionary(dsId, schema, tableIdLike);
        List<String> msgList = new ArrayList<String>(tblIdAndNameList.size());

        // max process limit default set to 1000
        maxLimit = maxLimit == null ? defaultImportMaxLimit : maxLimit;
        int index = 0;
        for (String tblIdAndName : tblIdAndNameList) {
            if (index >= maxLimit) {
                // check max limit
                break;
            }
            String[] names = tblIdAndName.split(" ");
            String tblId = names[0];
            String tblName = names[1];

            EmResourceDto emRes = new EmResourceDto();
            emRes.setResId(tblId);
            emRes.setResName(tblName);
            emRes.setResIndex(index + 1);
            emRes.setResGroup(resGroup);
            emRes.setResType(0);

            EmTblCfgDto entEnt = new EmTblCfgDto();
            entEnt.setEntId(tblId);
            entEnt.setEntName(tblName);
            entEnt.setEntType(0);
            entEnt.setRefTblDatasource(schema + "." + tblId);
            entEnt.setEntLayout(null);
            entEnt.setRefTblDisableCols(null);
            entEnt.setRefTblHideCols(null);
            entEnt.setRefTblId(null);
            entEnt.setRefTblReadonlyCols(null);
            entEnt.setPreProcDisp(null);
            entEnt.setPreProcSave(null);
            entEnt.setPreProcValidate(null);
            entEnt.setCusEntityManager(null);

            index++;

            StringBuilder msg = new StringBuilder();
            try {
                int existRes = getDao().count(EmResourceDto.SQLID.COUNT, emRes);
                if (existRes == 0) {
                    int result = getDao().update(EmResourceDto.SQLID.INSERT, emRes);
                    if (result == 1) {
                        msg.append(result + " record insert into [EM_RES]. ");
                    }
                }
            } catch (Exception e) {
                msg.append("error when insert into [EM_RES]. ");
            }

            try {
                int existEnt = getDao().count(EmTblCfgDto.SQLID.COUNT, entEnt);
                if (existEnt == 0) {
                    int result = getDao().update(EmTblCfgDto.SQLID.INSERT, entEnt);
                    if (result == 1) {
                        msg.append(result + " record insert into [EM_ENT_CFG]. ");
                    }
                }
            } catch (Exception e) {
                msg.append("error when insert into [EM_ENT_CFG]. ");
            }
            if (msg.length() > 0) {
                msgList.add(tblId + " imported. " + msg.toString());
            }

        }
        return msgList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.umeframework.dora.service.em.sys.ResourceManager#removeGroupResource(java.lang.String)
     */
    @Override
    public List<String> removeGroupResource(
            String resGroup) {

        if (StringUtil.isEmpty(resGroup)) {
        	throw new ApplicationException("APMSG10006", new Object[] { resGroup});
        }
        EmResourceDto param = new EmResourceDto();
        param.setResGroup(resGroup);
        List<EmResourceDto> emResList = getDao().queryForObjectList(EmResourceDto.SQLID.SEARCH, param, EmResourceDto.class);
        List<String> msgList = new ArrayList<String>();
        for (EmResourceDto emRes : emResList) {
            EmTblCfgDto entEnt = new EmTblCfgDto();
            String resId = emRes.getResId();
            entEnt.setEntId(resId);
            int result = getDao().update(EmTblCfgDto.SQLID.DELETE, entEnt);

            StringBuilder msg = new StringBuilder();
            if (result > 0) {
                msg.append(result + " record(s) removed from [EM_ENT_CFG]. ");
            }
            result = getDao().update(EmResourceDto.SQLID.DELETE, emRes);
            if (result > 0) {
                msg.append(result + " record(s) removed from [EM_RES]. ");
            }
            if (msg.length() > 0) {
                msgList.add(resId + " removed. " + msg.toString());
            }
        }
        return msgList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.umeframework.dora.service.em.sys.ResourceManager#removeTableResource(java.lang.String, java.lang.String)
     */
    @Override
    public List<String> removeTableResource(
            String resGroup,
            String tableId) {

        if (StringUtil.isEmpty(resGroup) || StringUtil.isEmpty(tableId)) {
        	throw new ApplicationException("APMSG10006", new Object[] { "Resource Group and Table"});
        }
        EmResourceDto param = new EmResourceDto();
        param.setResId(tableId);
        param.setResGroup(resGroup);
        List<EmResourceDto> emResList = getDao().queryForObjectList(EmResourceDto.SQLID.SEARCH, param, EmResourceDto.class);
        List<String> msgList = new ArrayList<String>();
        for (EmResourceDto emRes : emResList) {
            EmTblCfgDto entEnt = new EmTblCfgDto();
            String resId = emRes.getResId();
            entEnt.setEntId(resId);
            int result = getDao().update(EmTblCfgDto.SQLID.DELETE, entEnt);

            StringBuilder msg = new StringBuilder();
            if (result > 0) {
                msg.append(result + " recoed(s) removed from [EM_ENT_CFG]. ");
            }
            result = getDao().update(EmResourceDto.SQLID.DELETE, emRes);
            if (result > 0) {
                msg.append(result + " recoed(s) removed from [EM_RES]. ");
            }
            if (msg.length() > 0) {
                msgList.add(resId + " removed. " + msg.toString());
            }
        }
        return msgList;
    }
    
    /**
     * @param host
     * @param port
     * @param dbname
     * @param username
     * @param password
     * @param dbtype
     * @return
     */
    @TransactionRequired
    public void addDataSource(String id, String host, String port, String dbname, String username, String password, String dbtype) {
    	String driverClass = null;
    	dbtype = dbtype != null ? dbtype.toLowerCase() : "mysql";
    	if ("mysql".equals(dbtype)) {
    		if (!dbname.contains("?")) {
    			dbname = dbname + "?characterEncoding=UTF-8";
    		}
    		driverClass = "com.mysql.jdbc.Driver";
    	} else if ("oracle".equals(dbtype)) {
    		driverClass = "oracle.jdbc.driver.OracleDriver";
    	} else if ("db2".equals(dbtype)) {
    		driverClass = "com.ibm.db2.jcc.DB2Driver";
    	} else {
    		throw new RuntimeException("Unsupport database type:" + dbtype);
    	}
    	StringBuilder url = new StringBuilder();
    	url.append("jdbc:");
    	url.append(dbtype);
    	url.append("://");
    	host = host.contains("^") ? host.replace('^', '.') : host;
    	url.append(host);
    	url.append(":");
    	url.append(port);
    	url.append("/");
    	url.append(dbname);
    	
    	EmDsCfgDto cfg = new EmDsCfgDto();
    	cfg.setId(id);
    	cfg.setDriverClass(driverClass);
    	cfg.setUrl(url.toString());
    	cfg.setUsername(username);
    	cfg.setPassword(password);
    	cfg.setInitialSize("1");
    	cfg.setMaxActive("2");
    	
    	dynaDaoManager.addDataSource(cfg);
    	
    	super.createMessage("Append new data source:" + cfg.getId());
    }

}
