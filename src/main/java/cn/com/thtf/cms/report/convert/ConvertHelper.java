package cn.com.thtf.cms.report.convert;
/**
 * ClassName  ConvertHelper
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-10-18
 * Update User:
 * Update Date:
 */


/**
 * CSV转换辅助接口，定义title及data行的生成方式
 * 
 * @author Shiy
 */

public interface ConvertHelper {

    /**
     * CSVTitle行
     * 
     * @return
     */
    public String[] getTilte();

    /**
     * 数据行转换方式
     * 
     * @param obj
     * @return
     */
    public String[] getRow(Object obj);

}
