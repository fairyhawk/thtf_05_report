/**
 * ClassName  BackEndLoginActionBeanTest
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-10-26
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.view;

import javax.servlet.http.HttpSession;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sourceforge.stripes.mock.MockRoundtrip;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.UserService;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedJMock;
import cn.com.thtf.cms.report.util.ReflectUtil;
import cn.shiy.common.cws.security.CommonACL;
import cn.shiy.common.cws.utils.Container;

/**
 * 
 * @author Shiy
 */
public class BackEndLoginActionBeanTest extends BaseTestNG implements NeedJMock {

    private Mockery context;

    private UserService service;

    private ApplicationContext mockContext;

    private ApplicationContext orgContext;

    private final UserEntity user = new UserEntity();

    private String token;

    @SuppressWarnings("unchecked")
    @Override
    public void setUp() {
        orgContext = Container.getApplicationContext();
        service = context.mock(UserService.class);
        mockContext = context.mock(ApplicationContext.class);
        context.checking(new Expectations() {
            {
                (allowing(mockContext)).getBean("userService", UserService.class);
                will(returnValue(service));
                (allowing(mockContext)).getBean(with(any(String.class)),
                        with(any(Class.class)));
                will(new CustomAction("original getBean") {
                    @Override
                    public Object invoke(Invocation invocation) throws Throwable {
                        return orgContext.getBean((String) invocation.getParameter(0),
                                (Class<?>) invocation.getParameter(1));
                    }
                });
            }
        });
        Container contaner = new Container();
        ReflectUtil.setFieldValue(contaner, "applicationContext",
                ApplicationContext.class, mockContext);
    }

    @Test(groups = { "action" })
    public void testSaveLoginToken() throws Exception {

        user.setId("123");
        user.setName("测试用户");
        context.checking(new Expectations() {
            {
                (allowing(service)).getUserById("123");
                will(returnValue(user));
            }
        });
        MockRoundtrip trip = new MockRoundtrip(this.getServletContext(),
                BackEndLoginActionBean.class);
        trip.setParameter("userid", "123");
        trip.execute();

        String key = new String(trip.getResponse().getOutputBytes(), "UTF-8");
        token = key;
        Assert.assertNotNull(key);
        System.out.println(key);

        Cache loginTokenCache = Container.getBean("loginTokenCache", Cache.class);
        Element elmt = loginTokenCache.get(key);

        UserEntity cachedUser = (UserEntity) elmt.getObjectValue();
        System.out.println(elmt.getTimeToLive());
        Assert.assertEquals(user, cachedUser);
    }

    /**
     * 测试LoginActionBean的hiddenLogin,正常登录
     * 
     * @throws Exception
     */
    @Test(groups = { "action" }, dependsOnMethods = { "testSaveLoginToken" })
    public void testHiddenLoginSuccess() throws Exception {
        MockRoundtrip trip = new MockRoundtrip(this.getServletContext(),
                LoginActionBean.class);
        trip.setParameter("token", token);
        trip.execute("hiddenLogin");
        HttpSession session = trip.getRequest().getSession();
        CommonACL acl = (CommonACL) session.getAttribute("CWS_SESSION_ACL");
        Assert.assertNotNull(acl);
        Assert.assertEquals(user, acl.getUser());
        Assert.assertEquals("\"done\"", trip.getOutputString());
    }

    /**
     * 测试LoginActionBean的hiddenLogin,错误登录
     * 
     * @throws Exception
     */
    @Test(groups = { "action" })
    public void testHiddenLoginFailed() throws Exception {
        MockRoundtrip trip = new MockRoundtrip(this.getServletContext(),
                LoginActionBean.class);
        trip.setParameter("token", "123");
        trip.execute("hiddenLogin");

        Assert.assertEquals("", trip.getOutputString());
    }

    /**
     * @see cn.com.thtf.cms.report.test.NeedJMock#setMockery(org.jmock.Mockery)
     */
    @Override
    public void setMockery(Mockery context) {
        this.context = context;
    }

}
