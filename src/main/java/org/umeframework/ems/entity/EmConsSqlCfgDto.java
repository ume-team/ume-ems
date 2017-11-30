
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
 * Entity class map to table "EM 制约（SQL）配置表"
 *
 * @author ume-team
 */
@Entity
@Table(name="EM_CONS_SQL_CFG")
@TableDesc(label="EM 制约（SQL）配置表")
public class EmConsSqlCfgDto extends TableEntity implements Serializable {
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
    @ColumnDesc(index=(5 + 1), type="VARCHAR", label="createAuthor")
    @Column(name="CREATE_AUTHOR", nullable=true, length=32)
    private String createAuthor;
   /**
    * Create Datetime (default setting while insert)
    */
    @ColumnDesc(index=(5 + 2), type="TIMESTAMP", label="createDatetime")
    @Column(name="CREATE_DATETIME", nullable=true)
    private java.sql.Timestamp createDatetime;
   /**
    * Update Author (refresh on each update)
    */
    @ColumnDesc(index=(5 + 3), type="VARCHAR", label="updateAuthor")
    @Column(name="UPDATE_AUTHOR", nullable=true, length=32)
    private String updateAuthor;
   /**
    * Update Datetime (refresh on each update)
    */
    @ColumnDesc(index=(5 + 4), type="TIMESTAMP", label="updateDatetime")
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
            Object value = BeanUtil.getBeanProperty(this, name);
            BeanUtil.setBeanProperty(newInstance, name, value);
        }
        return newInstance;
    }
    
    /**
     * Constant declare: SQL ID in config file
     */
    public static class SQLID {
        public static final String INSERT = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_INSERT"; 
        public static final String UPDATE = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_UPDATE"; 
        public static final String SMART_UPDATE = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_SMART_UPDATE"; 
        public static final String DELETE = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_DELETE"; 
        public static final String DELETE_ALL = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_DELETE_ALL"; 
        public static final String FIND = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_FIND"; 
        public static final String FIND_FOR_UPDATE = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_FIND_FOR_UPDATE"; 
        public static final String FIND_LIST = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_FIND_LIST"; 
        public static final String FIND_LIST_LIKE = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_FIND_LIST_LIKE"; 
        public static final String FIND_LIST_MATCH = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_FIND_LIST_MATCH"; 
        public static final String COUNT = "org.umeframework.ems.entity.EM_CONS_SQL_CFG_COUNT";
    } 

    /**
     * Constant declare: entity property name.<br>
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
    }
    
    /**
     * Constant declare: column name map with bean property.<br>
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
     * Constant declare: table name.<br>
     */
    public static String TableName = "EM_CONS_SQL_CFG";

}
