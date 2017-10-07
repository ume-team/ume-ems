package org.umeframework.ems.entity;

import java.io.Serializable;

import javax.persistence.Table;

import org.umeframework.dora.service.TableEntity;
import org.umeframework.dora.type.ColumnDesc;
import org.umeframework.dora.type.TableDesc;
import org.umeframework.dora.validation.constraints.NotEmpty;
import org.umeframework.dora.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Data transfer object mapping to table "EM 制约（SQL）配置表"
 *
 * @author DORA generator
 */
@Entity
@Table(name="EM_CONS_SQL_CFG")
@TableDesc(label="EM 制约（SQL）配置表")
public class EmConsSqlCfgDto extends TableEntity implements Serializable, Comparable<EmConsSqlCfgDto> {
   /**
    * Default serial version code
    */
    private static final long serialVersionUID = 1L;

   /**
    * 制约ID 
    */
    @NotEmpty
    @Size(max=32)
    @Id
    @ColumnDesc(index=1, type="VARCHAR", label="制约ID")
    @Column(name="CONS_ID", nullable=false, length=32)
    private String consId;

   /**
    * 制约名称 
    */
    @Size(max=32)
    @ColumnDesc(index=2, type="VARCHAR", label="制约名称")
    @Column(name="CONS_NAME", nullable=true, length=32)
    private String consName;

   /**
    * 制约描述 
    */
    @Size(max=64)
    @ColumnDesc(index=3, type="VARCHAR", label="制约描述")
    @Column(name="CONS_DESC", nullable=true, length=64)
    private String consDesc;

   /**
    * 制约SQLR 
    */
    @Size(max=1024)
    @ColumnDesc(index=4, type="VARCHAR", label="制约SQLR")
    @Column(name="CONS_SQLR", nullable=true, length=1024)
    private String consSqlr;

   /**
    * 制约SQLU 
    */
    @Size(max=1024)
    @ColumnDesc(index=5, type="VARCHAR", label="制约SQLU")
    @Column(name="CONS_SQLU", nullable=true, length=1024)
    private String consSqlu;

   /**
    * Create Author (default setting while insert)
    */
    @Size(min=32, max=32)
    @ColumnDesc(index=(5 + 1), type="VARCHAR", label="Create Auther")
    @Column(name="CREATE_AUTHER", nullable=true, length=32)
    private String createAuthor;
   /**
    * Create Datetime (default setting while insert)
    */
    @ColumnDesc(index=(5 + 2), type="TIMESTAMP", label="Create Datetime")
    @Column(name="CREATE_DATETIME", nullable=true)
    private java.sql.Timestamp createDatetime;
   /**
    * Update Author (refresh on each update)
    */
    @Size(min=32, max=32)
    @ColumnDesc(index=(5 + 3), type="VARCHAR", label="Update Auther")
    @Column(name="UPDATE_AUTHER", nullable=true, length=32)
    private String updateAuthor;
   /**
    * Update Datetime (refresh on each update)
    */
    @ColumnDesc(index=(5 + 4), type="TIMESTAMP", label="Update Datetime")
    @Column(name="UPDATE_DATETIME", nullable=true)
    private java.sql.Timestamp updateDatetime;

    /**
     *　Get the "制约ID"
     */
    public String getConsId() {
        return this.consId;
    }
    /**
     *　Set the "制约ID"
     */
    public void setConsId(
            String consId) {
        this.consId = consId;
    }

    /**
     *　Get the "制约名称"
     */
    public String getConsName() {
        return this.consName;
    }
    /**
     *　Set the "制约名称"
     */
    public void setConsName(
            String consName) {
        this.consName = consName;
    }

    /**
     *　Get the "制约描述"
     */
    public String getConsDesc() {
        return this.consDesc;
    }
    /**
     *　Set the "制约描述"
     */
    public void setConsDesc(
            String consDesc) {
        this.consDesc = consDesc;
    }

    /**
     *　Get the "制约SQLR"
     */
    public String getConsSqlr() {
        return this.consSqlr;
    }
    /**
     *　Set the "制约SQLR"
     */
    public void setConsSqlr(
            String consSqlr) {
        this.consSqlr = consSqlr;
    }

    /**
     *　Get the "制约SQLU"
     */
    public String getConsSqlu() {
        return this.consSqlu;
    }
    /**
     *　Set the "制约SQLU"
     */
    public void setConsSqlu(
            String consSqlu) {
        this.consSqlu = consSqlu;
    }

    /**
     * Get the "Create Auther"
     */
    public String getCreateAuthor() {
        return createAuthor;
    }
    /**
     * Set the "Create Auther"
     */
    public void setCreateAuthor(
            String createAuthor) {
        this.createAuthor = createAuthor;
    }

    /**
     * Get the "Create Datetime"
     */
    public java.sql.Timestamp getCreateDatetime() {
        return createDatetime;
    }
    /**
     * Set the "Create Datetime"
     */
    public void setCreateDatetime(
            java.sql.Timestamp createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * Get the "Update Auther"
     */
    public String getUpdateAuthor() {
        return updateAuthor;
    }
    /**
     * Set the "Update Auther"
     */
    public void setUpdateAuthor(
            String updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    /**
     * Get the "Update Datetime"
     */
    public java.sql.Timestamp getUpdateDatetime() {
        return updateDatetime;
    }
    /**
     * Set the "Update Datetime"
     */
    public void setUpdateDatetime(
            java.sql.Timestamp updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable compareTo(Object})
     */
    @Override
    public int compareTo(
            EmConsSqlCfgDto targetObj) {
        // default 0 no support sort
        return 0;
    }

    /**
     * Create bean instance copy with selected properties
     * 
     * @param selectProperties
     *            - properties which copy to new instance
     * @return
     */
    public EmConsSqlCfgDto copyFrom(
            Property... selectProperties) {
        if (selectProperties == null) {
            return null;
        }
        EmConsSqlCfgDto newInstance = new EmConsSqlCfgDto();
        for (Property property : selectProperties) {
            String name = property.toString();
            Object value = org.umeframework.dora.bean.BeanUtil.getBeanProperty(this, name);
            org.umeframework.dora.bean.BeanUtil.setBeanProperty(newInstance, name, value);
        }
        return newInstance;
    }
    
    /**
     * Enumerate all SQL ID define in SQL-MAP
     */
    public static class SQLID {
        public static final String INSERT = "TBL.EM_CONS_SQL_CFG_INSERT"; 
        public static final String UPDATE = "TBL.EM_CONS_SQL_CFG_UPDATE"; 
        public static final String SMART_UPDATE = "TBL.EM_CONS_SQL_CFG_SMART_UPDATE"; 
        public static final String DELETE = "TBL.EM_CONS_SQL_CFG_DELETE"; 
        public static final String FIND = "TBL.EM_CONS_SQL_CFG_FIND"; 
        public static final String FIND_FOR_UPDATE = "TBL.EM_CONS_SQL_CFG_FIND_FOR_UPDATE"; 
        public static final String SEARCH = "TBL.EM_CONS_SQL_CFG_SEARCH"; 
        public static final String LIKE_SEARCH = "TBL.EM_CONS_SQL_CFG_LIKE_SEARCH"; 
        public static final String DYNA_SEARCH = "TBL.EM_CONS_SQL_CFG_DYNA_SEARCH"; 
        public static final String COUNT = "TBL.EM_CONS_SQL_CFG_COUNT";
    } 

    /**
     * Enumerate all bean properties
     */
    public static class Property {
        public static final String consId = "consId";
        public static final String consName = "consName";
        public static final String consDesc = "consDesc";
        public static final String consSqlr = "consSqlr";
        public static final String consSqlu = "consSqlu";
        public static final String createAuthor = "createAuthor";
        public static final String createDatetime = "createDatetime";
        public static final String updateAuthor = "updateAuthor";
        public static final String updateDatetime = "updateDatetime";
        public static final String theSchema = "theSchema";
        public static final String theDivision = "theDivision";
        public static final String theOrderByCondition = "theOrderByCondition";
        public static final String theGroupByCondition = "theGroupByCondition";
        public static final String theSQLCondition = "theSQLCondition";
        public static final String theFetchSize = "theFetchSize";
        public static final String theFetchStart = "theFetchStart";   
    }
    
    /**
     * Enumerate all table column names which map to bean properties
     */
    public static class ColumnName {
        public static final String CONS_ID = "CONS_ID";
        public static final String CONS_NAME = "CONS_NAME";
        public static final String CONS_DESC = "CONS_DESC";
        public static final String CONS_SQLR = "CONS_SQLR";
        public static final String CONS_SQLU = "CONS_SQLU";
        public static final String CREATE_AUTHOR = "CREATE_AUTHOR";
        public static final String CREATE_DATETIME = "CREATE_DATETIME";
        public static final String UPDATE_AUTHOR = "UPDATE_AUTHOR";
        public static final String UPDATE_DATETIME = "UPDATE_DATETIME";
    }
    /**
     * Enumerate table name
     */
    public static class TableName {
        public static final String EM_CONS_SQL_CFG = "EM_CONS_SQL_CFG";
    }

}
