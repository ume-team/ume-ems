/*
 * Copyright 2014-2017 UME Framework Group, Apache License Version 2.0
 */
package org.umeframework.ems.desc.dto;

import java.io.Serializable;

/**
 * Data structure to contain column configuration items.
 * 
 * @author Yue MA
 *
 */
public class EmColDescDto implements Serializable {
    /**
     * serial version UID
     */
    private static final long serialVersionUID = 1L;
    /**
     * entity ID
     */
    private String entId;
    /**
     * column ID
     */
    private String colId;
    /**
     * column name (comment define in database usually)
     */
    private String colName;
    /**
     * column type of JDBC
     */
    private Integer dataJdbcType;
    /**
     * column type of string
     */
    private String dataType;
    /**
     * column sub-type define in configuration
     */
    private String dataSubType;
    /**
     * column data length
     */
    private Integer dataLength;
    /**
     * column data precision
     */
    private Integer dataPrecision;
    /**
     * column data scale
     */
    private Integer dataScale;
    /**
     * column is primary key flag
     */
    private Integer pkFlag;
    /**
     * column is unique key flag
     */
    private Integer ukFlag;
    /**
     * column is not null flag
     */
    private Integer notNull;
    /**
     * column default value
     */
    private String defaultValue;
    /**
     * column min length define in configuration
     */
    private Integer dataLengthMin;
    /**
     * column max length define in configuration
     */
    private Integer dataLengthMax;
    /**
     * column min value define in configuration
     */
    private String dataRangeMin;
    /**
     * column max value define in configuration
     */
    private String dataRangeMax;
    /**
     * column constraint type define in configuration
     */
    private Integer constraintType;
    /**
     * column constraint reference ID define in configuration
     */
    private String constraintRef;
    /**
     * column display type define in configuration
     */
    private Integer dispType;
    /**
     * column display format define in configuration
     */
    private String dispFormat;
    /**
     * column edit format define in configuration
     */
    private String editFormat;
    /**
     * column pre-display process ID define in configuration
     */
    private String preProcDisp;
    /**
     * column pre-save process ID define in configuration
     */
    private String preProcSave;

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
     * @return the colId
     */
    public String getColId() {
        return colId;
    }


    /**
     * @param colId the colId to set
     */
    public void setColId(
            String colId) {
        this.colId = colId;
    }


    /**
     * @return the colName
     */
    public String getColName() {
        return colName;
    }


    /**
     * @param colName the colName to set
     */
    public void setColName(
            String colName) {
        this.colName = colName;
    }


    /**
     * @return the dataJdbcType
     */
    public Integer getDataJdbcType() {
        return dataJdbcType;
    }


    /**
     * @param dataJdbcType the dataJdbcType to set
     */
    public void setDataJdbcType(
            Integer dataJdbcType) {
        this.dataJdbcType = dataJdbcType;
    }


    /**
     * @return the dataType
     */
    public String getDataType() {
        return dataType;
    }


    /**
     * @param dataType the dataType to set
     */
    public void setDataType(
            String dataType) {
        this.dataType = dataType;
    }


    /**
     * @return the dataSubType
     */
    public String getDataSubType() {
        return dataSubType;
    }


    /**
     * @param dataSubType the dataSubType to set
     */
    public void setDataSubType(
            String dataSubType) {
        this.dataSubType = dataSubType;
    }


    /**
     * @return the dataLength
     */
    public Integer getDataLength() {
        return dataLength;
    }


    /**
     * @param dataLength the dataLength to set
     */
    public void setDataLength(
            Integer dataLength) {
        this.dataLength = dataLength;
    }


    /**
     * @return the dataPrecision
     */
    public Integer getDataPrecision() {
        return dataPrecision;
    }


    /**
     * @param dataPrecision the dataPrecision to set
     */
    public void setDataPrecision(
            Integer dataPrecision) {
        this.dataPrecision = dataPrecision;
    }


    /**
     * @return the dataScale
     */
    public Integer getDataScale() {
        return dataScale;
    }


    /**
     * @param dataScale the dataScale to set
     */
    public void setDataScale(
            Integer dataScale) {
        this.dataScale = dataScale;
    }


    /**
     * @return the pkFlag
     */
    public Integer getPkFlag() {
        return pkFlag;
    }


    /**
     * @param pkFlag the pkFlag to set
     */
    public void setPkFlag(
            Integer pkFlag) {
        this.pkFlag = pkFlag;
    }


    /**
     * @return the ukFlag
     */
    public Integer getUkFlag() {
        return ukFlag;
    }


    /**
     * @param ukFlag the ukFlag to set
     */
    public void setUkFlag(
            Integer ukFlag) {
        this.ukFlag = ukFlag;
    }


    /**
     * @return the notNull
     */
    public Integer getNotNull() {
        return notNull;
    }


    /**
     * @param notNull the notNull to set
     */
    public void setNotNull(
            Integer notNull) {
        this.notNull = notNull;
    }


    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }


    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(
            String defaultValue) {
        this.defaultValue = defaultValue;
    }


    /**
     * @return the dataLengthMin
     */
    public Integer getDataLengthMin() {
        return dataLengthMin;
    }


    /**
     * @param dataLengthMin the dataLengthMin to set
     */
    public void setDataLengthMin(
            Integer dataLengthMin) {
        this.dataLengthMin = dataLengthMin;
    }


    /**
     * @return the dataLengthMax
     */
    public Integer getDataLengthMax() {
        return dataLengthMax;
    }


    /**
     * @param dataLengthMax the dataLengthMax to set
     */
    public void setDataLengthMax(
            Integer dataLengthMax) {
        this.dataLengthMax = dataLengthMax;
    }


    /**
     * @return the dataRangeMin
     */
    public String getDataRangeMin() {
        return dataRangeMin;
    }


    /**
     * @param dataRangeMin the dataRangeMin to set
     */
    public void setDataRangeMin(
            String dataRangeMin) {
        this.dataRangeMin = dataRangeMin;
    }


    /**
     * @return the dataRangeMax
     */
    public String getDataRangeMax() {
        return dataRangeMax;
    }


    /**
     * @param dataRangeMax the dataRangeMax to set
     */
    public void setDataRangeMax(
            String dataRangeMax) {
        this.dataRangeMax = dataRangeMax;
    }


    /**
     * @return the constraintType
     */
    public Integer getConstraintType() {
        return constraintType;
    }


    /**
     * @param constraintType the constraintType to set
     */
    public void setConstraintType(
            Integer constraintType) {
        this.constraintType = constraintType;
    }


    /**
     * @return the constraintRef
     */
    public String getConstraintRef() {
        return constraintRef;
    }


    /**
     * @param constraintRef the constraintRef to set
     */
    public void setConstraintRef(
            String constraintRef) {
        this.constraintRef = constraintRef;
    }


    /**
     * @return the dispType
     */
    public Integer getDispType() {
        return dispType;
    }


    /**
     * @param dispType the dispType to set
     */
    public void setDispType(
            Integer dispType) {
        this.dispType = dispType;
    }


    /**
     * @return the dispFormat
     */
    public String getDispFormat() {
        return dispFormat;
    }


    /**
     * @param dispFormat the dispFormat to set
     */
    public void setDispFormat(
            String dispFormat) {
        this.dispFormat = dispFormat;
    }


    /**
     * @return the editFormat
     */
    public String getEditFormat() {
        return editFormat;
    }


    /**
     * @param editFormat the editFormat to set
     */
    public void setEditFormat(
            String editFormat) {
        this.editFormat = editFormat;
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

}
