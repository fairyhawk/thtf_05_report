/**
 * ClassName  UserServiceImpl
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-09-02
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.serviceimpl;

import cn.com.thtf.cms.report.dao.UserDao;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.UserService;

/**
 * 
 * @author Shiy
 */

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserEntity getUserById(String userid) {
        return userDao.getUserById(userid);
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao
     *            the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
