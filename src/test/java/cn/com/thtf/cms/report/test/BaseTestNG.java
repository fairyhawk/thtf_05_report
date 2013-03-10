/**
 * ClassName  BaseTestNG
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-9-2
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import lombok.Getter;

import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.mock.MockServletContext;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import cn.shiy.common.cws.controller.CWSFilter;

/**
 * 
 * @author Shiy
 */

public class BaseTestNG {

    private List<TestOperation> operations = new ArrayList<TestOperation>();

    @Getter
    private MockServletContext servletContext = null;
    
    @BeforeSuite(alwaysRun = true)
    public void initSuite() throws ServletException {
        servletContext = new MockServletContext("cmsReport");

        Map<String, String> filterParams = new HashMap<String, String>();
        filterParams.put("cws-spring-config-files",
                "classpath:/cn/com/thtf/cms/report/spring/beans.xml");
        filterParams.put("cws-debug", "true");
        filterParams.put("ActionResolver.Packages", "cn.com.thtf.cms.report.action");
        servletContext.addFilter(CWSFilter.class, "CWSFilter", filterParams);
        servletContext.setServlet(DispatcherServlet.class, "StripesDispatcher", null);
    }

    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        TestOperation operation = null;
        if (this instanceof NeedDBUnit) {
            operation = new DBUnitOperation((NeedDBUnit) this);
            operations.add(operation);
        }
        if (this instanceof NeedJMock) {
            operation = new JMockOperation((NeedJMock) this);
            operations.add(operation);
        }
        for (TestOperation testOperation : operations) {
            testOperation.doSetUp();
        }

        setUp();

    }

    @AfterClass
    public void tearDownClass() throws Exception {
        for (TestOperation testOperation : operations) {
            testOperation.doTearDown();
        }
        tearDown();
    }

    public void setUp() {

    }

    public void tearDown() {

    }

}
