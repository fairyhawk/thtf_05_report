package cn.com.thtf.cms.report.test.dao;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import cn.com.thtf.cms.report.dao.UserDao;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.shiy.common.cws.utils.Container;

public class UserDaoTest extends BaseTestNG {

    private UserDao dao;

    @Override
    public void setUp() {
        dao = Container.getBean("userDao", UserDao.class);
    }

    @Test(groups = { "dao" })
    public void testGetUserById() {
        UserEntity user = dao.getUserById("123");
        Assert.assertEquals(user.getName(), "123");
    }

    @BeforeMethod
    public void beforeMethod() {
    }

    @AfterMethod
    public void afterMethod() {
    }

}
