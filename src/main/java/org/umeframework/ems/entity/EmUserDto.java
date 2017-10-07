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
 * Data transfer object mapping to table "EM用户表"
 *
 * @author DORA generator
 */
@Entity
@Table(name="EM_USER")
@TableDesc(label="EM用户表")
public class EmUserDto extends TableEntity implements Serializable, Comparable<EmUserDto> {
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
    * 用户备用手机 
    */
    @Size(max=16)
    @TextFormat(TextFormat.Category.MobileNumber)
    @ColumnDesc(index=5, type="VARCHAR", label="用户备用手机")
    @Column(name="USER_MOBILE_BAK", nullable=true, length=16)
    private String userMobileBak;

   /**
    * 用户身份证号 
    */
    @Size(max=32)
    @TextFormat(TextFormat.Category.AlphaNumeric)
    @ColumnDesc(index=6, type="VARCHAR", label="用户身份证号")
    @Column(name="USER_IDENTITY_CARD", nullable=true, length=32)
    private String userIdentityCard;

   /**
    * 用户驾驶证号 
    */
    @Size(max=32)
    @TextFormat(TextFormat.Category.AlphaNumeric)
    @ColumnDesc(index=7, type="VARCHAR", label="用户驾驶证号")
    @Column(name="USER_DRIVE_LICENSE", nullable=true, length=32)
    private String userDriveLicense;

   /**
    * 用户护照号 
    */
    @Size(max=32)
    @TextFormat(TextFormat.Category.AlphaNumeric)
    @ColumnDesc(index=8, type="VARCHAR", label="用户护照号")
    @Column(name="USER_PASSPORT", nullable=true, length=32)
    private String userPassport;

   /**
    * 用户地址 
    */
    @Size(max=256)
    @ColumnDesc(index=9, type="VARCHAR", label="用户地址")
    @Column(name="USER_ADDRESS", nullable=true, length=256)
    private String userAddress;

   /**
    * 用户邮箱 
    */
    @Size(max=64)
    @TextFormat(TextFormat.Category.Email)
    @ColumnDesc(index=10, type="VARCHAR", label="用户邮箱")
    @Column(name="USER_EMAIL", nullable=true, length=64)
    private String userEmail;

   /**
    * 用户备用邮箱 
    */
    @Size(max=64)
    @TextFormat(TextFormat.Category.Email)
    @ColumnDesc(index=11, type="VARCHAR", label="用户备用邮箱")
    @Column(name="USER_EMAIL_BAK", nullable=true, length=64)
    private String userEmailBak;

   /**
    * 用户等级 
    */
    @Size(max=8)
    @ColumnDesc(index=12, type="INT", label="用户等级")
    @Column(name="USER_LEVEL", nullable=true, length=8)
    private Integer userLevel;

   /**
    * 用户账户状态 
    * 1:正常 0:异常
    */
    @Size(max=2)
    @ColumnDesc(index=13, type="INT", label="用户账户状态")
    @Column(name="USER_STATUS", nullable=true, length=2)
    private Integer userStatus;

   /**
    * Create Author (default setting while insert)
    */
    @Size(min=32, max=32)
    @ColumnDesc(index=(13 + 1), type="VARCHAR", label="Create Auther")
    @Column(name="CREATE_AUTHER", nullable=true, length=32)
    private String createAuthor;
   /**
    * Create Datetime (default setting while insert)
    */
    @ColumnDesc(index=(13 + 2), type="TIMESTAMP", label="Create Datetime")
    @Column(name="CREATE_DATETIME", nullable=true)
    private java.sql.Timestamp createDatetime;
   /**
    * Update Author (refresh on each update)
    */
    @Size(min=32, max=32)
    @ColumnDesc(index=(13 + 3), type="VARCHAR", label="Update Auther")
    @Column(name="UPDATE_AUTHER", nullable=true, length=32)
    private String updateAuthor;
   /**
    * Update Datetime (refresh on each update)
    */
    @ColumnDesc(index=(13 + 4), type="TIMESTAMP", label="Update Datetime")
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
     *　Get the "用户备用手机"
     */
    public String getUserMobileBak() {
        return this.userMobileBak;
    }
    /**
     *　Set the "用户备用手机"
     */
    public void setUserMobileBak(
            String userMobileBak) {
        this.userMobileBak = userMobileBak;
    }

    /**
     *　Get the "用户身份证号"
     */
    public String getUserIdentityCard() {
        return this.userIdentityCard;
    }
    /**
     *　Set the "用户身份证号"
     */
    public void setUserIdentityCard(
            String userIdentityCard) {
        this.userIdentityCard = userIdentityCard;
    }

    /**
     *　Get the "用户驾驶证号"
     */
    public String getUserDriveLicense() {
        return this.userDriveLicense;
    }
    /**
     *　Set the "用户驾驶证号"
     */
    public void setUserDriveLicense(
            String userDriveLicense) {
        this.userDriveLicense = userDriveLicense;
    }

    /**
     *　Get the "用户护照号"
     */
    public String getUserPassport() {
        return this.userPassport;
    }
    /**
     *　Set the "用户护照号"
     */
    public void setUserPassport(
            String userPassport) {
        this.userPassport = userPassport;
    }

    /**
     *　Get the "用户地址"
     */
    public String getUserAddress() {
        return this.userAddress;
    }
    /**
     *　Set the "用户地址"
     */
    public void setUserAddress(
            String userAddress) {
        this.userAddress = userAddress;
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
     *　Get the "用户备用邮箱"
     */
    public String getUserEmailBak() {
        return this.userEmailBak;
    }
    /**
     *　Set the "用户备用邮箱"
     */
    public void setUserEmailBak(
            String userEmailBak) {
        this.userEmailBak = userEmailBak;
    }

    /**
     *　Get the "用户等级"
     */
    public Integer getUserLevel() {
        return this.userLevel;
    }
    /**
     *　Set the "用户等级"
     */
    public void setUserLevel(
            Integer userLevel) {
        this.userLevel = userLevel;
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

    /* (non-Javadoc)
     * @see java.lang.Comparable compareTo(Object})
     */
    @Override
    public int compareTo(
            EmUserDto targetObj) {
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
    public EmUserDto copyFrom(
            Property... selectProperties) {
        if (selectProperties == null) {
            return null;
        }
        EmUserDto newInstance = new EmUserDto();
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
        public static final String INSERT = "TBL.EM_USER_INSERT"; 
        public static final String UPDATE = "TBL.EM_USER_UPDATE"; 
        public static final String SMART_UPDATE = "TBL.EM_USER_SMART_UPDATE"; 
        public static final String DELETE = "TBL.EM_USER_DELETE"; 
        public static final String FIND = "TBL.EM_USER_FIND"; 
        public static final String FIND_FOR_UPDATE = "TBL.EM_USER_FIND_FOR_UPDATE"; 
        public static final String SEARCH = "TBL.EM_USER_SEARCH"; 
        public static final String LIKE_SEARCH = "TBL.EM_USER_LIKE_SEARCH"; 
        public static final String DYNA_SEARCH = "TBL.EM_USER_DYNA_SEARCH"; 
        public static final String COUNT = "TBL.EM_USER_COUNT";
    } 

    /**
     * Enumerate all bean properties
     */
    public static class Property {
        public static final String userId = "userId";
        public static final String userName = "userName";
        public static final String userPassword = "userPassword";
        public static final String userMobile = "userMobile";
        public static final String userMobileBak = "userMobileBak";
        public static final String userIdentityCard = "userIdentityCard";
        public static final String userDriveLicense = "userDriveLicense";
        public static final String userPassport = "userPassport";
        public static final String userAddress = "userAddress";
        public static final String userEmail = "userEmail";
        public static final String userEmailBak = "userEmailBak";
        public static final String userLevel = "userLevel";
        public static final String userStatus = "userStatus";
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
        public static final String USER_ID = "USER_ID";
        public static final String USER_NAME = "USER_NAME";
        public static final String USER_PASSWORD = "USER_PASSWORD";
        public static final String USER_MOBILE = "USER_MOBILE";
        public static final String USER_MOBILE_BAK = "USER_MOBILE_BAK";
        public static final String USER_IDENTITY_CARD = "USER_IDENTITY_CARD";
        public static final String USER_DRIVE_LICENSE = "USER_DRIVE_LICENSE";
        public static final String USER_PASSPORT = "USER_PASSPORT";
        public static final String USER_ADDRESS = "USER_ADDRESS";
        public static final String USER_EMAIL = "USER_EMAIL";
        public static final String USER_EMAIL_BAK = "USER_EMAIL_BAK";
        public static final String USER_LEVEL = "USER_LEVEL";
        public static final String USER_STATUS = "USER_STATUS";
        public static final String CREATE_AUTHOR = "CREATE_AUTHOR";
        public static final String CREATE_DATETIME = "CREATE_DATETIME";
        public static final String UPDATE_AUTHOR = "UPDATE_AUTHOR";
        public static final String UPDATE_DATETIME = "UPDATE_DATETIME";
    }
    /**
     * Enumerate table name
     */
    public static class TableName {
        public static final String EM_USER = "EM_USER";
    }

}
