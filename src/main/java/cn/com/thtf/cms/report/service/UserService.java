/**
 * ClassName  UserService
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-09-02
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.service;

import cn.com.thtf.cms.report.entity.UserEntity;

/**
 * 
 * @author Shiy
 */

public interface UserService {

    public UserEntity getUserById(String userid);
}
