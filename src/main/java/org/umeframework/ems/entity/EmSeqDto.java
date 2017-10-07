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
 * Data transfer object mapping to table "采番表"
 *
 * @author DORA generator
 */
@Entity
@Table(name="EM_SEQ")
@TableDesc(label="采番表")
public class EmSeqDto extends TableEntity implements Serializable, Comparable<EmSeqDto> {
   /**
    * Default serial version code
    */
    private static final long serialVersionUID = 1L;

   /**
    * 采番名称 
    */
    @NotEmpty
    @Size(max=8)
    @Id
    @ColumnDesc(index=1, type="VARCHAR", label="采番名称")
    @Column(name="ITEM_NAME", nullable=false, length=8)
    private String itemName;

   /**
    * 最小值 
    */
    @NotEmpty
    @Size(max=16)
    @ColumnDesc(index=2, type="BIGINT", label="最小值")
    @Column(name="MIN_VALUE", nullable=false, length=16)
    private Long minValue;

   /**
    * 最大值 
    */
    @NotEmpty
    @Size(max=32)
    @ColumnDesc(index=3, type="BIGINT", label="最大值")
    @Column(name="MAX_VALUE", nullable=false, length=32)
    private Long maxValue;

   /**
    * 当前值 
    */
    @NotEmpty
    @Size(max=32)
    @ColumnDesc(index=4, type="BIGINT", label="当前值")
    @Column(name="CURRENT_INDEX", nullable=false, length=32)
    private Long currentIndex;

   /**
    * 增量 
    */
    @NotEmpty
    @Size(max=16)
    @ColumnDesc(index=5, type="BIGINT", label="增量")
    @Column(name="INCREMENT_VALUE", nullable=false, length=16)
    private Long incrementValue;

   /**
    * 长度 
    */
    @ColumnDesc(index=6, type="INT", label="长度")
    @Column(name="ITEM_LENGTH", nullable=true)
    private Integer itemLength;

   /**
    * 前缀 
    */
    @Size(max=64)
    @ColumnDesc(index=7, type="VARCHAR", label="前缀")
    @Column(name="PREFIX", nullable=true, length=64)
    private String prefix;

   /**
    * 后缀 
    */
    @Size(max=64)
    @ColumnDesc(index=8, type="VARCHAR", label="后缀")
    @Column(name="POSTFIX", nullable=true, length=64)
    private String postfix;

   /**
    * 是否可重复 
    */
    @Size(max=1)
    @ColumnDesc(index=9, type="VARCHAR", label="是否可重复")
    @Column(name="REPEATABLE", nullable=true, length=1)
    private String repeatable;

   /**
    * 格式化扩展 
    */
    @Size(max=64)
    @ColumnDesc(index=10, type="VARCHAR", label="格式化扩展")
    @Column(name="FORMATTER", nullable=true, length=64)
    private String formatter;

   /**
    * Create Author (default setting while insert)
    */
    @Size(min=32, max=32)
    @ColumnDesc(index=(10 + 1), type="VARCHAR", label="Create Auther")
    @Column(name="CREATE_AUTHER", nullable=true, length=32)
    private String createAuthor;
   /**
    * Create Datetime (default setting while insert)
    */
    @ColumnDesc(index=(10 + 2), type="TIMESTAMP", label="Create Datetime")
    @Column(name="CREATE_DATETIME", nullable=true)
    private java.sql.Timestamp createDatetime;
   /**
    * Update Author (refresh on each update)
    */
    @Size(min=32, max=32)
    @ColumnDesc(index=(10 + 3), type="VARCHAR", label="Update Auther")
    @Column(name="UPDATE_AUTHER", nullable=true, length=32)
    private String updateAuthor;
   /**
    * Update Datetime (refresh on each update)
    */
    @ColumnDesc(index=(10 + 4), type="TIMESTAMP", label="Update Datetime")
    @Column(name="UPDATE_DATETIME", nullable=true)
    private java.sql.Timestamp updateDatetime;

    /**
     *　Get the "采番名称"
     */
    public String getItemName() {
        return this.itemName;
    }
    /**
     *　Set the "采番名称"
     */
    public void setItemName(
            String itemName) {
        this.itemName = itemName;
    }

    /**
     *　Get the "最小值"
     */
    public Long getMinValue() {
        return this.minValue;
    }
    /**
     *　Set the "最小值"
     */
    public void setMinValue(
            Long minValue) {
        this.minValue = minValue;
    }

    /**
     *　Get the "最大值"
     */
    public Long getMaxValue() {
        return this.maxValue;
    }
    /**
     *　Set the "最大值"
     */
    public void setMaxValue(
            Long maxValue) {
        this.maxValue = maxValue;
    }

    /**
     *　Get the "当前值"
     */
    public Long getCurrentIndex() {
        return this.currentIndex;
    }
    /**
     *　Set the "当前值"
     */
    public void setCurrentIndex(
            Long currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     *　Get the "增量"
     */
    public Long getIncrementValue() {
        return this.incrementValue;
    }
    /**
     *　Set the "增量"
     */
    public void setIncrementValue(
            Long incrementValue) {
        this.incrementValue = incrementValue;
    }

    /**
     *　Get the "长度"
     */
    public Integer getItemLength() {
        return this.itemLength;
    }
    /**
     *　Set the "长度"
     */
    public void setItemLength(
            Integer itemLength) {
        this.itemLength = itemLength;
    }

    /**
     *　Get the "前缀"
     */
    public String getPrefix() {
        return this.prefix;
    }
    /**
     *　Set the "前缀"
     */
    public void setPrefix(
            String prefix) {
        this.prefix = prefix;
    }

    /**
     *　Get the "后缀"
     */
    public String getPostfix() {
        return this.postfix;
    }
    /**
     *　Set the "后缀"
     */
    public void setPostfix(
            String postfix) {
        this.postfix = postfix;
    }

    /**
     *　Get the "是否可重复"
     */
    public String getRepeatable() {
        return this.repeatable;
    }
    /**
     *　Set the "是否可重复"
     */
    public void setRepeatable(
            String repeatable) {
        this.repeatable = repeatable;
    }

    /**
     *　Get the "格式化扩展"
     */
    public String getFormatter() {
        return this.formatter;
    }
    /**
     *　Set the "格式化扩展"
     */
    public void setFormatter(
            String formatter) {
        this.formatter = formatter;
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
            EmSeqDto targetObj) {
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
    public EmSeqDto copyFrom(
            Property... selectProperties) {
        if (selectProperties == null) {
            return null;
        }
        EmSeqDto newInstance = new EmSeqDto();
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
        public static final String INSERT = "TBL.EM_SEQ_INSERT"; 
        public static final String UPDATE = "TBL.EM_SEQ_UPDATE"; 
        public static final String SMART_UPDATE = "TBL.EM_SEQ_SMART_UPDATE"; 
        public static final String DELETE = "TBL.EM_SEQ_DELETE"; 
        public static final String FIND = "TBL.EM_SEQ_FIND"; 
        public static final String FIND_FOR_UPDATE = "TBL.EM_SEQ_FIND_FOR_UPDATE"; 
        public static final String SEARCH = "TBL.EM_SEQ_SEARCH"; 
        public static final String LIKE_SEARCH = "TBL.EM_SEQ_LIKE_SEARCH"; 
        public static final String DYNA_SEARCH = "TBL.EM_SEQ_DYNA_SEARCH"; 
        public static final String COUNT = "TBL.EM_SEQ_COUNT";
    } 

    /**
     * Enumerate all bean properties
     */
    public static class Property {
        public static final String itemName = "itemName";
        public static final String minValue = "minValue";
        public static final String maxValue = "maxValue";
        public static final String currentIndex = "currentIndex";
        public static final String incrementValue = "incrementValue";
        public static final String itemLength = "itemLength";
        public static final String prefix = "prefix";
        public static final String postfix = "postfix";
        public static final String repeatable = "repeatable";
        public static final String formatter = "formatter";
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
        public static final String ITEM_NAME = "ITEM_NAME";
        public static final String MIN_VALUE = "MIN_VALUE";
        public static final String MAX_VALUE = "MAX_VALUE";
        public static final String CURRENT_INDEX = "CURRENT_INDEX";
        public static final String INCREMENT_VALUE = "INCREMENT_VALUE";
        public static final String ITEM_LENGTH = "ITEM_LENGTH";
        public static final String PREFIX = "PREFIX";
        public static final String POSTFIX = "POSTFIX";
        public static final String REPEATABLE = "REPEATABLE";
        public static final String FORMATTER = "FORMATTER";
        public static final String CREATE_AUTHOR = "CREATE_AUTHOR";
        public static final String CREATE_DATETIME = "CREATE_DATETIME";
        public static final String UPDATE_AUTHOR = "UPDATE_AUTHOR";
        public static final String UPDATE_DATETIME = "UPDATE_DATETIME";
    }
    /**
     * Enumerate table name
     */
    public static class TableName {
        public static final String EM_SEQ = "EM_SEQ";
    }

}
