/**
 * ClassName  PageBaseActionBean
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-12-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.data;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import cn.shiy.common.cws.action.BaseActionBean;

/**
 * PageBaseActionBean
 * 
 * @author Lubo
 */

public class BasePageActionBean extends BaseActionBean {

    /** 排序的列 */
    @Setter
    @Getter
    private String sidx;
    /** 升序、降序 */
    @Setter
    @Getter
    private String sord;
    /** 共多少条 */
    @Setter
    @Getter
    private int rows;
    /** 每页多少条 */
    @Setter
    private int page;
    /** 节点 */
    @Setter
    @Getter
    private String fields = null;

    /**
     * getPage
     * 
     * @return
     */
    public int getPage() {
        if (page < 1) {
            return 1;
        } else {
            return page;
        }
    }

    /**
     * getRequest
     */
    public HttpServletRequest getRequest() {
        return this.getContext().getRequest();
    }

}
