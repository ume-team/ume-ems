
package org.umeframework.ems.uac.entity;

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
 * Entity class map to table "UME用户表"
 *
 * @author ume-team
 */
@Entity
@Table(name="UME_USER")
@TableDesc(label="UME用户表")
public class UmeUserDto extends TableEntity implements Serializable {
   /**
    * Default serial version code
    */
    private static final long serialVersionUID = 1L;

   /**
    * 用户识别ID 
    */
    @NotEmpty
    @Size(max=16)
    @Id
    @ColumnDesc(index=1, type="VARCHAR", label="用户识别ID")
    @Column(name="USER_ID", nullable=false, length=16)
    private String userId;

   /**
    * 用户名称 
    */
    @Size(max=64)
    @ColumnDesc(index=2, type="VARCHAR", label="用户名称")
    @Column(name="USER_NAME", nullable=true, length=64)
    private String userName;

   /**
    * 用户登录密码 
    */
    @Size(max=64)
    @ColumnDesc(index=3, type="VARCHAR", label="用户登录密码")
    @Column(name="USER_PASSWORD", nullable=true, length=64)
    private String userPassword;

   /**
    * 用户手机 
    */
    @Size(max=16)
    @TextFormat(TextFormat.Category.MobileNumber)
    @ColumnDesc(index=4, type="VARCHAR", label="用户手机")
    @Column(name="USER_MOBILE", nullable=true, length=16)
    private String userMobile;

   /**
    * 用户邮箱 
    */
    @Size(max=64)
    @TextFormat(TextFormat.Category.Email)
    @ColumnDesc(index=5, type="VARCHAR", label="用户邮箱")
    @Column(name="USER_EMAIL", nullable=true, length=64)
    private String userEmail;

   /**
    * 用户账户状态 
    * 1:正常 0:异常
    */
    @Size(max=2)
    @ColumnDesc(index=6, type="INT", label="用户账户状态")
    @Column(name="USER_STATUS", nullable=true, length=2)
    private Integer userStatus;

   /**
    * Create Author (default setting while insert)
    */
    @ColumnDesc(index=(6 + 1), type="VARCHAR", label="Create Auther")
    @Column(name="CREATE_AUTHER", nullable=true, length=32)
    private String createAuthor;
   /**
    * Create Datetime (default setting while insert)
    */
    @ColumnDesc(index=(6 + 2), type="TIMESTAMP", label="Create Datetime")
    @Column(name="CREATE_DATETIME", nullable=true)
    private java.sql.Timestamp createDatetime;
   /**
    * Update Author (refresh on each update)
    */
    @ColumnDesc(index=(6 + 3), type="VARCHAR", label="Update Auther")
    @Column(name="UPDATE_AUTHER", nullable=true, length=32)
    private String updateAuthor;
   /**
    * Update Datetime (refresh on each update)
    */
    @ColumnDesc(index=(6 + 4), type="TIMESTAMP", label="Update Datetime")
    @Column(name="UPDATE_DATETIME", nullable=true)
    private java.sql.Timestamp updateDatetime;

    /**
     *　Get the "用户识别ID"
     */
    public String getUserId() {
        return this.userId;
    }
    /**
     *　Set the "用户识别ID"
     */
    public void setUserId(
            String userId) {
        this.userId = userId;
    }

    /**
     *　Get the "用户名称"
     */
    public String getUserName() {
        return this.userName;
    }
    /**
     *　Set the "用户名称"
     */
    public void setUserName(
            String userName) {
        this.userName = userName;
    }

    /**
     *　Get the "用户登录密码"
     */
    public String getUserPassword() {
        return this.userPassword;
    }
    /**
     *　Set the "用户登录密码"
     */
    public void setUserPassword(
            String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     *　Get the "用户手机"
     */
    public String getUserMobile() {
        return this.userMobile;
    }
    /**
     *　Set the "用户手机"
     */
    public void setUserMobile(
            String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     *　Get the "用户邮箱"
     */
    public String getUserEmail() {
        return this.userEmail;
    }
    /**
     *　Set the "用户邮箱"
     */
    public void setUserEmail(
            String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     *　Get the "用户账户状态"
     */
    public Integer getUserStatus() {
        return this.userStatus;
    }
    /**
     *　Set the "用户账户状态"
     */
    public void setUserStatus(
            Integer userStatus) {
        this.userStatus = userStatus;
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
    public UmeUserDto copyFrom(
            Property... selectProperties) {
        if (selectProperties == null) {
            return null;
        }
        UmeUserDto newInstance = new UmeUserDto();
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
        public static final String INSERT = "org.umeframework.ems.uac.entity.UME_USER_INSERT"; 
        public static final String UPDATE = "org.umeframework.ems.uac.entity.UME_USER_UPDATE"; 
        public static final String SMART_UPDATE = "org.umeframework.ems.uac.entity.UME_USER_SMART_UPDATE"; 
        public static final String DELETE = "org.umeframework.ems.uac.entity.UME_USER_DELETE"; 
        public static final String FIND = "org.umeframework.ems.uac.entity.UME_USER_FIND"; 
        public static final String FIND_FOR_UPDATE = "org.umeframework.ems.uac.entity.UME_USER_FIND_FOR_UPDATE"; 
        public static final String SEARCH = "org.umeframework.ems.uac.entity.UME_USER_SEARCH"; 
        public static final String LIKE_SEARCH = "org.umeframework.ems.uac.entity.UME_USER_LIKE_SEARCH"; 
        public static final String DYNA_SEARCH = "org.umeframework.ems.uac.entity.UME_USER_DYNA_SEARCH"; 
        public static final String COUNT = "org.umeframework.ems.uac.entity.UME_USER_COUNT";
    } 

    /**
     * Constant declare: entity property name.<br>
     */
    public static class Property {
        public static final String userId = "userId";
        public static final String userName = "userName";
        public static final String userPassword = "userPassword";
        public static final String userMobile = "userMobile";
        public static final String userEmail = "userEmail";
        public static final String userStatus = "userStatus";
        public static final String createAuthor = "createAuthor";
        public static final String createDatetime = "createDatetime";
        public static final String updateAuthor = "updateAuthor";
        public static final String updateDatetime = "updateDatetime";
    }
    
    /**
     * Constant declare: column name map with bean property.<br>
     */
    public static class ColumnName {
        public static final String USER_ID = "USER_ID";
        public static final String USER_NAME = "USER_NAME";
        public static final String USER_PASSWORD = "USER_PASSWORD";
        public static final String USER_MOBILE = "USER_MOBILE";
        public static final String USER_EMAIL = "USER_EMAIL";
        public static final String USER_STATUS = "USER_STATUS";
        public static final String CREATE_AUTHOR = "CREATE_AUTHOR";
        public static final String CREATE_DATETIME = "CREATE_DATETIME";
        public static final String UPDATE_AUTHOR = "UPDATE_AUTHOR";
        public static final String UPDATE_DATETIME = "UPDATE_DATETIME";
    }
    /**
     * Constant declare: table name.<br>
     */
    public static String TableName = "UME_USER";

}
