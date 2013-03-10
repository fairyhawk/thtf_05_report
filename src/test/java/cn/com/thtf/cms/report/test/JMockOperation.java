/**
 * ClassName  JMockOperation
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-9-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.test;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

/**
 * 
 * @author Shiy
 */

public class JMockOperation implements TestOperation {

    public JMockOperation(NeedJMock test) {
        Mockery context = new Mockery();
        context.setImposteriser(ClassImposteriser.INSTANCE);
        test.setMockery(context);
    }

    /**
     * @see cn.com.thtf.cms.report.test.TestOperation#doSetUp()
     */
    @Override
    public void doSetUp() throws Exception {
    }

    /**
     * @see cn.com.thtf.cms.report.test.TestOperation#doTearDown()
     */
    @Override
    public void doTearDown() throws Exception {
    }

}
