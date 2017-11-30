
package org.umeframework.ems.entity;

import java.io.Serializable;
import org.umeframework.dora.validation.constraints.Size;
import org.umeframework.dora.type.ColumnDesc;
import org.umeframework.dora.validation.constraints.NotEmpty;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.umeframework.dora.type.TableDesc;
import javax.persistence.Id;
import org.umeframework.dora.bean.BeanUtil;
import org.umeframework.dora.service.TableEntity;

/**
 * Entity class map to table "EM 编码配置表"
 *
 * @author ume-team
 */
@Entity
@Table(name="EM_CONS_CODE_CFG")
@TableDesc(label="EM 编码配置表")
public class EmConsCodeCfgDto extends TableEntity implements Serializable {
   /**
    * Default serial version code
    */
    private static final long serialVersionUID = 1L;

   /**
    * 类别编号 
    */
    @NotEmpty
    @Size(max=32)
    @Id
    @ColumnDesc(index=1, type="VARCHAR", label="类别编号")
    @Column(name="CONS_ID", nullable=false, length=32)
    private String consId;

   /**
    * 序号 
    */
    @NotEmpty
    @Size(max=8)
    @Id
    @ColumnDesc(index=2, type="INT", label="序号")
    @Column(name="CONS_SEQ", nullable=false, length=8)
    private Integer consSeq;

   /**
    * 编码名称 
    */
    @Size(max=32)
    @ColumnDesc(index=3, type="VARCHAR", label="编码名称")
    @Column(name="CONS_NAME", nullable=true, length=32)
    private String consName;

   /**
    * 编码描述 
    */
    @Size(max=64)
    @ColumnDesc(index=4, type="VARCHAR", label="编码描述")
    @Column(name="CONS_DESC", nullable=true, length=64)
    private String consDesc;

   /**
    * 表示值 
    */
    @Size(max=256)
    @ColumnDesc(index=5, type="VARCHAR", label="表示值")
    @Column(name="DISP_VALUE", nullable=true, length=256)
    private String dispValue;

   /**
    * 存贮值 
    */
    @Size(max=256)
    @ColumnDesc(index=6, type="VARCHAR", label="存贮值")
    @Column(name="STORE_VALUE", nullable=true, length=256)
    private String storeValue;

   /**
    * 存贮类型 
    * 0:String 1:int 2:Date 3:Boolean
    */
    @Size(max=2)
    @ColumnDesc(index=7, type="INT", label="存贮类型")
    @Column(name="STORE_TYPE", nullable=true, length=2)
    private Integer storeType;

   /**
    * Create Author (default setting while insert)
    */
    @ColumnDesc(index=(7 + 1), type="VARCHAR", label="createAuthor")
    @Column(name="CREATE_AUTHOR", nullable=true, length=32)
    private String createAuthor;
   /**
    * Create Datetime (default setting while insert)
    */
    @ColumnDesc(index=(7 + 2), type="TIMESTAMP", label="createDatetime")
    @Column(name="CREATE_DATETIME", nullable=true)
    private java.sql.Timestamp createDatetime;
   /**
    * Update Author (refresh on each update)
    */
    @ColumnDesc(index=(7 + 3), type="VARCHAR", label="updateAuthor")
    @Column(name="UPDATE_AUTHOR", nullable=true, length=32)
    private String updateAuthor;
   /**
    * Update Datetime (refresh on each update)
    */
    @ColumnDesc(index=(7 + 4), type="TIMESTAMP", label="updateDatetime")
    @Column(name="UPDATE_DATETIME", nullable=true)
    private java.sql.Timestamp updateDatetime;

    /**
     *　Get the "类别编号"
     */
    public String getConsId() {
        return this.consId;
    }
    /**
     *　Set the "类别编号"
     */
    public void setConsId(
            String consId) {
        this.consId = consId;
    }

    /**
     *　Get the "序号"
     */
    public Integer getConsSeq() {
        return this.consSeq;
    }
    /**
     *　Set the "序号"
     */
    public void setConsSeq(
            Integer consSeq) {
        this.consSeq = consSeq;
    }

    /**
     *　Get the "编码名称"
     */
    public String getConsName() {
        return this.consName;
    }
    /**
     *　Set the "编码名称"
     */
    public void setConsName(
            String consName) {
        this.consName = consName;
    }

    /**
     *　Get the "编码描述"
     */
    public String getConsDesc() {
        return this.consDesc;
    }
    /**
     *　Set the "编码描述"
     */
    public void setConsDesc(
            String consDesc) {
        this.consDesc = consDesc;
    }

    /**
     *　Get the "表示值"
     */
    public String getDispValue() {
        return this.dispValue;
    }
    /**
     *　Set the "表示值"
     */
    public void setDispValue(
            String dispValue) {
        this.dispValue = dispValue;
    }

    /**
     *　Get the "存贮值"
     */
    public String getStoreValue() {
        return this.storeValue;
    }
    /**
     *　Set the "存贮值"
     */
    public void setStoreValue(
            String storeValue) {
        this.storeValue = storeValue;
    }

    /**
     *　Get the "存贮类型"
     */
    public Integer getStoreType() {
        return this.storeType;
    }
    /**
     *　Set the "存贮类型"
     */
    public void setStoreType(
            Integer storeType) {
        this.storeType = storeType;
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

    /**
     * Create bean instance copy with selected properties
     * 
     * @param selectProperties
     *            - properties which copy to new instance
     * @return
     */
    public EmConsCodeCfgDto copyFrom(
            Property... selectProperties) {
        if (selectProperties == null) {
            return null;
        }
        EmConsCodeCfgDto newInstance = new EmConsCodeCfgDto();
        for (Property property : selectProperties) {
            String name = property.toString();
            Object value = BeanUtil.getBeanProperty(this, name);
            BeanUtil.setBeanProperty(newInstance, name, value);
        }
        return newInstance;
    }
    
    /**
     * Constant declare: SQL ID in config file
     */
    public static class SQLID {
        public static final String INSERT = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_INSERT"; 
        public static final String UPDATE = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_UPDATE"; 
        public static final String SMART_UPDATE = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_SMART_UPDATE"; 
        public static final String DELETE = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_DELETE"; 
        public static final String DELETE_ALL = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_DELETE_ALL"; 
        public static final String FIND = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_FIND"; 
        public static final String FIND_FOR_UPDATE = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_FIND_FOR_UPDATE"; 
        public static final String FIND_LIST = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_FIND_LIST"; 
        public static final String FIND_LIST_LIKE = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_FIND_LIST_LIKE"; 
        public static final String FIND_LIST_MATCH = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_FIND_LIST_MATCH"; 
        public static final String COUNT = "org.umeframework.ems.entity.EM_CONS_CODE_CFG_COUNT";
    } 

    /**
     * Constant declare: entity property name.<br>
     */
    public static class Property {
        public static final String consId = "consId";
        public static final String consSeq = "consSeq";
        public static final String consName = "consName";
        public static final String consDesc = "consDesc";
        public static final String dispValue = "dispValue";
        public static final String storeValue = "storeValue";
        public static final String storeType = "storeType";
        public static final String createAuthor = "createAuthor";
        public static final String createDatetime = "createDatetime";
        public static final String updateAuthor = "updateAuthor";
        public static final String updateDatetime = "updateDatetime";
    }
    
    /**
     * Constant declare: column name map with bean property.<br>
     */
    public static class ColumnName {
        public static final String CONS_ID = "CONS_ID";
        public static final String CONS_SEQ = "CONS_SEQ";
        public static final String CONS_NAME = "CONS_NAME";
        public static final String CONS_DESC = "CONS_DESC";
        public static final String DISP_VALUE = "DISP_VALUE";
        public static final String STORE_VALUE = "STORE_VALUE";
        public static final String STORE_TYPE = "STORE_TYPE";
        public static final String CREATE_AUTHOR = "CREATE_AUTHOR";
        public static final String CREATE_DATETIME = "CREATE_DATETIME";
        public static final String UPDATE_AUTHOR = "UPDATE_AUTHOR";
        public static final String UPDATE_DATETIME = "UPDATE_DATETIME";
    }
    /**
     * Constant declare: table name.<br>
     */
    public static String TableName = "EM_CONS_CODE_CFG";

}
