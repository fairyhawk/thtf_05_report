/**
 * ClassName  NeedDBUnit
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-9-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.test;

/**
 * 需要使用dbunit时，实现此接口
 * 
 * @author Shiy
 */

public interface NeedDBUnit {

    /**
     * 备份数据表的xml文件名，如data.xml
     * 
     * @return
     */
    public String getBackupFileName();

    /**
     * 导入的测试数据文件，如{"user.xml","role.xml"}
     * 
     * @return
     */
    public String[] getDataFileName();

    /**
     * 备份的数据表名，如{"user","role"}
     * 
     * @return
     */
    public String[] getBackupTableName();
}
