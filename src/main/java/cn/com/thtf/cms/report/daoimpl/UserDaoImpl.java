/**
 * ClassName  UserDaoImpl
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-09-02
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.daoimpl;

import cn.com.thtf.cms.report.dao.UserDao;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * 
 * @author Shiy
 */

public class UserDaoImpl extends GenericDaoImpl<UserEntity, String> implements UserDao {

    public UserEntity getUserById(String id) {
        return this.getObjectById("user.selectUserById", id);
    }
}
