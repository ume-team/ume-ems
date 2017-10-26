
package org.umeframework.ems.entity;

import java.io.Serializable;
import org.umeframework.dora.validation.constraints.Size;
import org.umeframework.dora.type.ColumnDesc;
import org.umeframework.dora.validation.constraints.TextFormat;
import org.umeframework.dora.validation.constraints.NotEmpty;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.umeframework.dora.type.TableDesc;
import javax.persistence.Id;
import org.umeframework.dora.bean.BeanUtil;
import org.umeframework.dora.service.TableEntity;

/**
 * Entity class map to table "EM实体属性配置表"
 *
 * @author ume-team
 */
@Entity
@Table(name="EM_TBL_COL_CFG")
@TableDesc(label="EM实体属性配置表")
public class EmTblColCfgDto extends TableEntity implements Serializable {
   /**
    * Default serial version code
    */
    private static final long serialVersionUID = 1L;

   /**
    * 属性所属实体ID 
    */
    @NotEmpty
    @Size(max=32)
    @Id
    @TextFormat(TextFormat.Category.AlphaNumeric)
    @ColumnDesc(index=1, type="VARCHAR", label="属性所属实体ID")
    @Column(name="ENT_ID", nullable=false, length=32)
    private String entId;

   /**
    * 属性ID 
    */
    @NotEmpty
    @Size(max=32)
    @Id
    @TextFormat(TextFormat.Category.AlphaNumeric)
    @ColumnDesc(index=2, type="VARCHAR", label="属性ID")
    @Column(name="COL_ID", nullable=false, length=32)
    private String colId;

   /**
    * 属性名称 
    */
    @Size(max=64)
    @ColumnDesc(index=3, type="VARCHAR", label="属性名称")
    @Column(name="COL_NAME", nullable=true, length=64)
    private String colName;

   /**
    * 属性子类型（扩展类型） 
    * 扩展类型备用
    */
    @Size(max=32)
    @ColumnDesc(index=4, type="VARCHAR", label="属性子类型（扩展类型）")
    @Column(name="DATA_SUB_TYPE", nullable=true, length=32)
    private String dataSubType;

   /**
    * 属性是否非空 
    * 1: 非空 0:可空 不用来存储，查询时仅作为服务端DTO使用
    */
    @Size(max=1)
    @ColumnDesc(index=5, type="INT", label="属性是否非空")
    @Column(name="NOT_NULL", nullable=true, length=1)
    private Integer notNull;

   /**
    * 属性默认值 
    * 不用来存储，查询时仅作为服务端DTO使用
    */
    @Size(max=128)
    @ColumnDesc(index=6, type="VARCHAR", label="属性默认值")
    @Column(name="DEFAULT_VALUE", nullable=true, length=128)
    private String defaultValue;

   /**
    * 属性最小长度 
    */
    @Size(max=32)
    @ColumnDesc(index=7, type="INT", label="属性最小长度")
    @Column(name="DATA_LENGTH_MIN", nullable=true, length=32)
    private Integer dataLengthMin;

   /**
    * 属性最大长度 
    */
    @Size(max=32)
    @ColumnDesc(index=8, type="INT", label="属性最大长度")
    @Column(name="DATA_LENGTH_MAX", nullable=true, length=32)
    private Integer dataLengthMax;

   /**
    * 属性最小值 
    */
    @Size(max=128)
    @ColumnDesc(index=9, type="VARCHAR", label="属性最小值")
    @Column(name="DATA_RANGE_MIN", nullable=true, length=128)
    private String dataRangeMin;

   /**
    * 属性最大值 
    */
    @Size(max=128)
    @ColumnDesc(index=10, type="VARCHAR", label="属性最大值")
    @Column(name="DATA_RANGE_MAX", nullable=true, length=128)
    private String dataRangeMax;

   /**
    * 属性制约类型 
    * 0:Default 1:CodeConfig 2:SQL
    */
    @Size(max=2)
    @ColumnDesc(index=11, type="INT", label="属性制约类型")
    @Column(name="CONSTRAINT_TYPE", nullable=true, length=2)
    private Integer constraintType;

   /**
    * 属性制约ID 
    * CONSTRAINT_TYPE=1时映射到EM_CODE_CFG.CODE_ID
    */
    @Size(max=32)
    @ColumnDesc(index=12, type="VARCHAR", label="属性制约ID")
    @Column(name="CONSTRAINT_REF", nullable=true, length=32)
    private String constraintRef;

   /**
    * 属性显示类别 
    * 0:Default 1:Radio 2:Dropdown 3:Check 4:Link 51:Image(base64) 52:image(url) 6:Textarea
    */
    @Size(max=3)
    @ColumnDesc(index=13, type="INT", label="属性显示类别")
    @Column(name="DISP_TYPE", nullable=true, length=3)
    private Integer dispType;

   /**
    * 显示格式 
    * 日期格式
    */
    @Size(max=128)
    @ColumnDesc(index=14, type="VARCHAR", label="显示格式")
    @Column(name="DISP_FORMAT", nullable=true, length=128)
    private String dispFormat;

   /**
    * 属性编辑格式 
    * 编辑（校验）格式
    */
    @Size(max=128)
    @ColumnDesc(index=15, type="VARCHAR", label="属性编辑格式")
    @Column(name="EDIT_FORMAT", nullable=true, length=128)
    private String editFormat;

   /**
    * 属性自定义显示前处理 
    * 实现PreHandler的类，由dataConvertor.properties定义
    */
    @Size(max=128)
    @ColumnDesc(index=16, type="VARCHAR", label="属性自定义显示前处理")
    @Column(name="PRE_PROC_DISP", nullable=true, length=128)
    private String preProcDisp;

   /**
    * 属性自定义保存前处理 
    * 实现PreHandler的类，由dataConvertor.properties定义
    */
    @Size(max=128)
    @ColumnDesc(index=17, type="VARCHAR", label="属性自定义保存前处理")
    @Column(name="PRE_PROC_SAVE", nullable=true, length=128)
    private String preProcSave;

   /**
    * Create Author (default setting while insert)
    */
    @ColumnDesc(index=(17 + 1), type="VARCHAR", label="createAuthor")
    @Column(name="CREATE_AUTHOR", nullable=true, length=32)
    private String createAuthor;
   /**
    * Create Datetime (default setting while insert)
    */
    @ColumnDesc(index=(17 + 2), type="TIMESTAMP", label="createDatetime")
    @Column(name="CREATE_DATETIME", nullable=true)
    private java.sql.Timestamp createDatetime;
   /**
    * Update Author (refresh on each update)
    */
    @ColumnDesc(index=(17 + 3), type="VARCHAR", label="updateAuthor")
    @Column(name="UPDATE_AUTHOR", nullable=true, length=32)
    private String updateAuthor;
   /**
    * Update Datetime (refresh on each update)
    */
    @ColumnDesc(index=(17 + 4), type="TIMESTAMP", label="updateDatetime")
    @Column(name="UPDATE_DATETIME", nullable=true)
    private java.sql.Timestamp updateDatetime;

    /**
     *　Get the "属性所属实体ID"
     */
    public String getEntId() {
        return this.entId;
    }
    /**
     *　Set the "属性所属实体ID"
     */
    public void setEntId(
            String entId) {
        this.entId = entId;
    }

    /**
     *　Get the "属性ID"
     */
    public String getColId() {
        return this.colId;
    }
    /**
     *　Set the "属性ID"
     */
    public void setColId(
            String colId) {
        this.colId = colId;
    }

    /**
     *　Get the "属性名称"
     */
    public String getColName() {
        return this.colName;
    }
    /**
     *　Set the "属性名称"
     */
    public void setColName(
            String colName) {
        this.colName = colName;
    }

    /**
     *　Get the "属性子类型（扩展类型）"
     */
    public String getDataSubType() {
        return this.dataSubType;
    }
    /**
     *　Set the "属性子类型（扩展类型）"
     */
    public void setDataSubType(
            String dataSubType) {
        this.dataSubType = dataSubType;
    }

    /**
     *　Get the "属性是否非空"
     */
    public Integer getNotNull() {
        return this.notNull;
    }
    /**
     *　Set the "属性是否非空"
     */
    public void setNotNull(
            Integer notNull) {
        this.notNull = notNull;
    }

    /**
     *　Get the "属性默认值"
     */
    public String getDefaultValue() {
        return this.defaultValue;
    }
    /**
     *　Set the "属性默认值"
     */
    public void setDefaultValue(
            String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     *　Get the "属性最小长度"
     */
    public Integer getDataLengthMin() {
        return this.dataLengthMin;
    }
    /**
     *　Set the "属性最小长度"
     */
    public void setDataLengthMin(
            Integer dataLengthMin) {
        this.dataLengthMin = dataLengthMin;
    }

    /**
     *　Get the "属性最大长度"
     */
    public Integer getDataLengthMax() {
        return this.dataLengthMax;
    }
    /**
     *　Set the "属性最大长度"
     */
    public void setDataLengthMax(
            Integer dataLengthMax) {
        this.dataLengthMax = dataLengthMax;
    }

    /**
     *　Get the "属性最小值"
     */
    public String getDataRangeMin() {
        return this.dataRangeMin;
    }
    /**
     *　Set the "属性最小值"
     */
    public void setDataRangeMin(
            String dataRangeMin) {
        this.dataRangeMin = dataRangeMin;
    }

    /**
     *　Get the "属性最大值"
     */
    public String getDataRangeMax() {
        return this.dataRangeMax;
    }
    /**
     *　Set the "属性最大值"
     */
    public void setDataRangeMax(
            String dataRangeMax) {
        this.dataRangeMax = dataRangeMax;
    }

    /**
     *　Get the "属性制约类型"
     */
    public Integer getConstraintType() {
        return this.constraintType;
    }
    /**
     *　Set the "属性制约类型"
     */
    public void setConstraintType(
            Integer constraintType) {
        this.constraintType = constraintType;
    }

    /**
     *　Get the "属性制约ID"
     */
    public String getConstraintRef() {
        return this.constraintRef;
    }
    /**
     *　Set the "属性制约ID"
     */
    public void setConstraintRef(
            String constraintRef) {
        this.constraintRef = constraintRef;
    }

    /**
     *　Get the "属性显示类别"
     */
    public Integer getDispType() {
        return this.dispType;
    }
    /**
     *　Set the "属性显示类别"
     */
    public void setDispType(
            Integer dispType) {
        this.dispType = dispType;
    }

    /**
     *　Get the "显示格式"
     */
    public String getDispFormat() {
        return this.dispFormat;
    }
    /**
     *　Set the "显示格式"
     */
    public void setDispFormat(
            String dispFormat) {
        this.dispFormat = dispFormat;
    }

    /**
     *　Get the "属性编辑格式"
     */
    public String getEditFormat() {
        return this.editFormat;
    }
    /**
     *　Set the "属性编辑格式"
     */
    public void setEditFormat(
            String editFormat) {
        this.editFormat = editFormat;
    }

    /**
     *　Get the "属性自定义显示前处理"
     */
    public String getPreProcDisp() {
        return this.preProcDisp;
    }
    /**
     *　Set the "属性自定义显示前处理"
     */
    public void setPreProcDisp(
            String preProcDisp) {
        this.preProcDisp = preProcDisp;
    }

    /**
     *　Get the "属性自定义保存前处理"
     */
    public String getPreProcSave() {
        return this.preProcSave;
    }
    /**
     *　Set the "属性自定义保存前处理"
     */
    public void setPreProcSave(
            String preProcSave) {
        this.preProcSave = preProcSave;
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
    public EmTblColCfgDto copyFrom(
            Property... selectProperties) {
        if (selectProperties == null) {
            return null;
        }
        EmTblColCfgDto newInstance = new EmTblColCfgDto();
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
        public static final String INSERT = "org.umeframework.ems.entity.EM_TBL_COL_CFG_INSERT"; 
        public static final String UPDATE = "org.umeframework.ems.entity.EM_TBL_COL_CFG_UPDATE"; 
        public static final String SMART_UPDATE = "org.umeframework.ems.entity.EM_TBL_COL_CFG_SMART_UPDATE"; 
        public static final String DELETE = "org.umeframework.ems.entity.EM_TBL_COL_CFG_DELETE"; 
        public static final String FIND = "org.umeframework.ems.entity.EM_TBL_COL_CFG_FIND"; 
        public static final String FIND_FOR_UPDATE = "org.umeframework.ems.entity.EM_TBL_COL_CFG_FIND_FOR_UPDATE"; 
        public static final String SEARCH = "org.umeframework.ems.entity.EM_TBL_COL_CFG_SEARCH"; 
        public static final String LIKE_SEARCH = "org.umeframework.ems.entity.EM_TBL_COL_CFG_LIKE_SEARCH"; 
        public static final String DYNA_SEARCH = "org.umeframework.ems.entity.EM_TBL_COL_CFG_DYNA_SEARCH"; 
        public static final String COUNT = "org.umeframework.ems.entity.EM_TBL_COL_CFG_COUNT";
    } 

    /**
     * Constant declare: entity property name.<br>
     */
    public static class Property {
        public static final String entId = "entId";
        public static final String colId = "colId";
        public static final String colName = "colName";
        public static final String dataSubType = "dataSubType";
        public static final String notNull = "notNull";
        public static final String defaultValue = "defaultValue";
        public static final String dataLengthMin = "dataLengthMin";
        public static final String dataLengthMax = "dataLengthMax";
        public static final String dataRangeMin = "dataRangeMin";
        public static final String dataRangeMax = "dataRangeMax";
        public static final String constraintType = "constraintType";
        public static final String constraintRef = "constraintRef";
        public static final String dispType = "dispType";
        public static final String dispFormat = "dispFormat";
        public static final String editFormat = "editFormat";
        public static final String preProcDisp = "preProcDisp";
        public static final String preProcSave = "preProcSave";
        public static final String createAuthor = "createAuthor";
        public static final String createDatetime = "createDatetime";
        public static final String updateAuthor = "updateAuthor";
        public static final String updateDatetime = "updateDatetime";
    }
    
    /**
     * Constant declare: column name map with bean property.<br>
     */
    public static class ColumnName {
        public static final String ENT_ID = "ENT_ID";
        public static final String COL_ID = "COL_ID";
        public static final String COL_NAME = "COL_NAME";
        public static final String DATA_SUB_TYPE = "DATA_SUB_TYPE";
        public static final String NOT_NULL = "NOT_NULL";
        public static final String DEFAULT_VALUE = "DEFAULT_VALUE";
        public static final String DATA_LENGTH_MIN = "DATA_LENGTH_MIN";
        public static final String DATA_LENGTH_MAX = "DATA_LENGTH_MAX";
        public static final String DATA_RANGE_MIN = "DATA_RANGE_MIN";
        public static final String DATA_RANGE_MAX = "DATA_RANGE_MAX";
        public static final String CONSTRAINT_TYPE = "CONSTRAINT_TYPE";
        public static final String CONSTRAINT_REF = "CONSTRAINT_REF";
        public static final String DISP_TYPE = "DISP_TYPE";
        public static final String DISP_FORMAT = "DISP_FORMAT";
        public static final String EDIT_FORMAT = "EDIT_FORMAT";
        public static final String PRE_PROC_DISP = "PRE_PROC_DISP";
        public static final String PRE_PROC_SAVE = "PRE_PROC_SAVE";
        public static final String CREATE_AUTHOR = "CREATE_AUTHOR";
        public static final String CREATE_DATETIME = "CREATE_DATETIME";
        public static final String UPDATE_AUTHOR = "UPDATE_AUTHOR";
        public static final String UPDATE_DATETIME = "UPDATE_DATETIME";
    }
    /**
     * Constant declare: table name.<br>
     */
    public static String TableName = "EM_TBL_COL_CFG";

}
