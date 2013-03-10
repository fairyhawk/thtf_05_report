/**
 * ClassName  UserServiceTest
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-9-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.test.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import cn.com.thtf.cms.report.dao.UserDao;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.UserService;
import cn.com.thtf.cms.report.serviceimpl.UserServiceImpl;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedJMock;

public class UserServiceTest extends BaseTestNG implements NeedJMock {

    private Mockery context;

    private UserDao userDao;

    private UserService service;

    @Override
    public void setUp() {
        service = new UserServiceImpl();
        userDao = context.mock(UserDao.class);
        ((UserServiceImpl) service).setUserDao(userDao);
    }

    @Test
    public void testGetUserById() {
        final UserEntity user = new UserEntity();
        user.setId("123");
        context.checking(new Expectations() {
            {
                (allowing(userDao)).getUserById("123");
                will(returnValue(user));
                (allowing(userDao)).getUserById("124");
                will(returnValue(null));
            }
        });
        assertEquals(service.getUserById("123"), user);
        assertNull(service.getUserById("124"));

    }

    /**
     * @see cn.com.thtf.cms.report.test.NeedJMock#setMockery(org.jmock.Mockery)
     */
    @Override
    public void setMockery(Mockery context) {
        this.context = context;
    }

}
