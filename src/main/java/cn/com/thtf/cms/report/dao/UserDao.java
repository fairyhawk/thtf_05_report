/**
 * ClassName  UserDao
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-09-02
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dao;

import cn.com.thtf.cms.report.entity.UserEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * 
 * @author Shiy
 */

public interface UserDao extends GenericDao<UserEntity, String> {

    public UserEntity getUserById(String id);
}
