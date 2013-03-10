/**
 * ClassName  UserEntity
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-9-2
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import cn.shiy.common.cws.security.CommonRole;
import cn.shiy.common.cws.security.CommonUser;

/**
 * 
 * @author Shiy
 */
@Data
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class UserEntity extends BaseEntity implements CommonUser, CommonRole {

    private static final long serialVersionUID = 20100902L;

    /** 登录名 */
    private String id;
    /** 用户名 */
    private String name;
    /** 密码 */
    private String password;
    /** 电话 */
    private String tel;
    /** 登录名 */
    private String mobile;

    /** 邮箱 */
    private String mail;
    /** msn */
    private String msn;
    /** QQ */
    private String qq;
    /** 上次登录时间 */
    private String datetime;
    /** 使用状态 */
    private String enable;

    /** 在线状态 */
    private Integer online;
    /** 职务编号 */
    private Integer roleId;
    /** 所属人员区域编号 */
    private Integer userAreaId;

    /** 所属人员部门编号 */
    private Integer userDeptId;
    /** 所属物流公司编号 */
    private Integer logisticsId;

    /**
     * @see cn.shiy.common.cws.security.CommonUser#getUserid()
     */
    @Override
    public String getUserid() {
        return id;
    }

    /**
     * @see cn.shiy.common.cws.security.CommonUser#getUsername()
     */
    @Override
    public String getUsername() {
        return this.name;
    }

    /**
     * @see cn.shiy.common.cws.security.CommonUser#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return "1".equals(this.enable);
    }

    /**
     * @see cn.shiy.common.cws.security.CommonRole#getRoleString()
     */
    @Override
    public String getRoleString() {
        return String.valueOf(this.roleId);
    }

    /**
     * @see cn.shiy.common.cws.security.CommonRole#getRoleInt()
     */
    @Override
    public int getRoleInt() {
        return this.roleId;
    }

    /**
     * @see cn.shiy.common.cws.security.CommonRole#getRole()
     */
    @Override
    public String getRole() {
        return String.valueOf(this.roleId);
    }

}
