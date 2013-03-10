/**
 * ClassName  CmsACL
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-9-2
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.security;

import java.util.ArrayList;
import java.util.List;

import cn.com.thtf.cms.report.entity.UserEntity;
import cn.shiy.common.cws.security.CommonACL;
import cn.shiy.common.cws.security.CommonRole;
import cn.shiy.common.cws.security.CommonUser;

/**
 * 
 * @author Shiy
 */

public class CmsACL implements CommonACL {

    private static final long serialVersionUID = 667382275416139526L;

    private UserEntity user;

    private List<CommonRole> roleList;

    /**
     * @see cn.shiy.common.cws.security.CommonACL#getUser()
     */
    @Override
    public CommonUser getUser() {
        return user;
    }

    /**
     * @see cn.shiy.common.cws.security.CommonACL#getRoleList()
     */
    @Override
    public List<CommonRole> getRoleList() {
        return roleList;
    }

    /**
     * @see cn.shiy.common.cws.security.CommonACL#hasRole(cn.shiy.common.cws.security.CommonRole)
     */
    @Override
    public boolean hasRole(CommonRole role) {
        if (user.getRoleId() == role.getRoleInt()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
        roleList = new ArrayList<CommonRole>(1);
        roleList.add(user);
    }

}
