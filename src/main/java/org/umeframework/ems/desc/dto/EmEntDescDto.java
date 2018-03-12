/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.desc.dto;

import java.io.Serializable;

/**
 * Data structure to contain table configuration items.
 * 
 * @author Yue MA
 *
 */
public class EmEntDescDto implements Serializable {
	/**
	* serial version UID
	*/
	private static final long serialVersionUID = 1L;

    /**
     * entity ID
     */
	private String entId;
    /**
     * entity name (comment define in database usually)
     */
	private String entName;
	/**
	* entity type
	*/
	private Integer entType;
	/**
	* entity edit display layout ID
	*/
	private String entLayout;
	/**
	* reference table ID
	*/
	private String refTblId;
	/**
	* reference table data source ID
	*/
	private String refTblDatasource;
	/**
	* reference table data read only columns (split by ",")
	*/
	private String refTblReadonlyCols;
    /**
    * reference table data hidden columns (split by ",")
    */
	private String refTblHideCols;
    /**
    * reference table data disable columns (split by ",")
    */
	private String refTblDisableCols;
    /**
     * entity pre-display process ID define in configuration
     */
	private String preProcDisp;
    /**
     * column pre-save process ID define in configuration
     */
	private String preProcSave;
	/**
	* entity customize validation instance list
	*/
	private String preProcValidate;
	/**
	* entity customize CRUD manager ID
	*/
	private String cusEntityManager;
    /**
     * @return the entId
     */
    public String getEntId() {
        return entId;
    }
    /**
     * @param entId the entId to set
     */
    public void setEntId(
            String entId) {
        this.entId = entId;
    }
    /**
     * @return the entName
     */
    public String getEntName() {
        return entName;
    }
    /**
     * @param entName the entName to set
     */
    public void setEntName(
            String entName) {
        this.entName = entName;
    }
    /**
     * @return the entType
     */
    public Integer getEntType() {
        return entType;
    }
    /**
     * @param entType the entType to set
     */
    public void setEntType(
            Integer entType) {
        this.entType = entType;
    }
    /**
     * @return the entLayout
     */
    public String getEntLayout() {
        return entLayout;
    }
    /**
     * @param entLayout the entLayout to set
     */
    public void setEntLayout(
            String entLayout) {
        this.entLayout = entLayout;
    }
    /**
     * @return the refTblId
     */
    public String getRefTblId() {
        return refTblId;
    }
    /**
     * @param refTblId the refTblId to set
     */
    public void setRefTblId(
            String refTblId) {
        this.refTblId = refTblId;
    }
    /**
     * @return the refTblDatasource
     */
    public String getRefTblDatasource() {
        return refTblDatasource;
    }
    /**
     * @param refTblDatasource the refTblDatasource to set
     */
    public void setRefTblDatasource(
            String refTblDatasource) {
        this.refTblDatasource = refTblDatasource;
    }
    /**
     * @return the refTblReadonlyCols
     */
    public String getRefTblReadonlyCols() {
        return refTblReadonlyCols;
    }
    /**
     * @param refTblReadonlyCols the refTblReadonlyCols to set
     */
    public void setRefTblReadonlyCols(
            String refTblReadonlyCols) {
        this.refTblReadonlyCols = refTblReadonlyCols;
    }
    /**
     * @return the refTblHideCols
     */
    public String getRefTblHideCols() {
        return refTblHideCols;
    }
    /**
     * @param refTblHideCols the refTblHideCols to set
     */
    public void setRefTblHideCols(
            String refTblHideCols) {
        this.refTblHideCols = refTblHideCols;
    }
    /**
     * @return the refTblDisableCols
     */
    public String getRefTblDisableCols() {
        return refTblDisableCols;
    }
    /**
     * @param refTblDisableCols the refTblDisableCols to set
     */
    public void setRefTblDisableCols(
            String refTblDisableCols) {
        this.refTblDisableCols = refTblDisableCols;
    }
    /**
     * @return the preProcDisp
     */
    public String getPreProcDisp() {
        return preProcDisp;
    }
    /**
     * @param preProcDisp the preProcDisp to set
     */
    public void setPreProcDisp(
            String preProcDisp) {
        this.preProcDisp = preProcDisp;
    }
    /**
     * @return the preProcSave
     */
    public String getPreProcSave() {
        return preProcSave;
    }
    /**
     * @param preProcSave the preProcSave to set
     */
    public void setPreProcSave(
            String preProcSave) {
        this.preProcSave = preProcSave;
    }
    /**
     * @return the preProcValidate
     */
    public String getPreProcValidate() {
        return preProcValidate;
    }
    /**
     * @param preProcValidate the preProcValidate to set
     */
    public void setPreProcValidate(
            String preProcValidate) {
        this.preProcValidate = preProcValidate;
    }
    /**
     * @return the cusEntityManager
     */
    public String getCusEntityManager() {
        return cusEntityManager;
    }
    /**
     * @param cusEntityManager the cusEntityManager to set
     */
    public void setCusEntityManager(
            String cusEntityManager) {
        this.cusEntityManager = cusEntityManager;
    }

}
