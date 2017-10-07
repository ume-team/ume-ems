package org.umeframework.ems.entity;

import java.io.Serializable;

import javax.persistence.Table;

import org.umeframework.dora.service.TableEntity;
import org.umeframework.dora.type.ColumnDesc;
import org.umeframework.dora.type.TableDesc;
import org.umeframework.dora.validation.constraints.NotEmpty;
import org.umeframework.dora.validation.constraints.Size;
import org.umeframework.dora.validation.constraints.TextFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Data transfer object mapping to table "EM实体展示配置表"
 *
 * @author DORA generator
 */
@Entity
@Table(name="EM_ENTITY_CFG")
@TableDesc(label="EM实体展示配置表")
public class EmEntityCfgDto extends TableEntity implements Serializable, Comparable<EmEntityCfgDto> {
   /**
    * Default serial version code
    */
    private static final long serialVersionUID = 1L;

   /**
    * 实体ID 
    */
    @NotEmpty
    @Size(max=64)
    @Id
    @TextFormat(TextFormat.Category.AlphaNumeric)
    @ColumnDesc(index=1, type="VARCHAR", label="实体ID")
    @Column(name="ID", nullable=false, length=64)
    private String id;

   /**
    * 实体Config 
    */
    @ColumnDesc(index=2, type="TEXT", label="实体Config")
    @Column(name="CONTENT", nullable=true)
    private String content;

   /**
    * Create Author (default setting while insert)
    */
    @Size(min=32, max=32)
    @ColumnDesc(index=(2 + 1), type="VARCHAR", label="Create Auther")
    @Column(name="CREATE_AUTHER", nullable=true, length=32)
    private String createAuthor;
   /**
    * Create Datetime (default setting while insert)
    */
    @ColumnDesc(index=(2 + 2), type="TIMESTAMP", label="Create Datetime")
    @Column(name="CREATE_DATETIME", nullable=true)
    private java.sql.Timestamp createDatetime;
   /**
    * Update Author (refresh on each update)
    */
    @Size(min=32, max=32)
    @ColumnDesc(index=(2 + 3), type="VARCHAR", label="Update Auther")
    @Column(name="UPDATE_AUTHER", nullable=true, length=32)
    private String updateAuthor;
   /**
    * Update Datetime (refresh on each update)
    */
    @ColumnDesc(index=(2 + 4), type="TIMESTAMP", label="Update Datetime")
    @Column(name="UPDATE_DATETIME", nullable=true)
    private java.sql.Timestamp updateDatetime;

    /**
     *　Get the "实体ID"
     */
    public String getId() {
        return this.id;
    }
    /**
     *　Set the "实体ID"
     */
    public void setId(
            String id) {
        this.id = id;
    }

    /**
     *　Get the "实体Config"
     */
    public String getContent() {
        return this.content;
    }
    /**
     *　Set the "实体Config"
     */
    public void setContent(
            String content) {
        this.content = content;
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
            EmEntityCfgDto targetObj) {
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
    public EmEntityCfgDto copyFrom(
            Property... selectProperties) {
        if (selectProperties == null) {
            return null;
        }
        EmEntityCfgDto newInstance = new EmEntityCfgDto();
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
        public static final String INSERT = "TBL.EM_ENTITY_CFG_INSERT"; 
        public static final String UPDATE = "TBL.EM_ENTITY_CFG_UPDATE"; 
        public static final String SMART_UPDATE = "TBL.EM_ENTITY_CFG_SMART_UPDATE"; 
        public static final String DELETE = "TBL.EM_ENTITY_CFG_DELETE"; 
        public static final String FIND = "TBL.EM_ENTITY_CFG_FIND"; 
        public static final String FIND_FOR_UPDATE = "TBL.EM_ENTITY_CFG_FIND_FOR_UPDATE"; 
        public static final String SEARCH = "TBL.EM_ENTITY_CFG_SEARCH"; 
        public static final String LIKE_SEARCH = "TBL.EM_ENTITY_CFG_LIKE_SEARCH"; 
        public static final String DYNA_SEARCH = "TBL.EM_ENTITY_CFG_DYNA_SEARCH"; 
        public static final String COUNT = "TBL.EM_ENTITY_CFG_COUNT";
    } 

    /**
     * Enumerate all bean properties
     */
    public static class Property {
        public static final String id = "id";
        public static final String content = "content";
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
        public static final String ID = "ID";
        public static final String CONTENT = "CONTENT";
        public static final String CREATE_AUTHOR = "CREATE_AUTHOR";
        public static final String CREATE_DATETIME = "CREATE_DATETIME";
        public static final String UPDATE_AUTHOR = "UPDATE_AUTHOR";
        public static final String UPDATE_DATETIME = "UPDATE_DATETIME";
    }
    /**
     * Enumerate table name
     */
    public static class TableName {
        public static final String EM_ENTITY_CFG = "EM_ENTITY_CFG";
    }

}
