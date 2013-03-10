/**
 * ClassName  库存产品明细
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-11-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.view;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.entity.UserEntity;
import cn.shiy.common.cws.action.BaseActionBean;
import cn.shiy.common.cws.security.Secure;

/**
 * 库存产品明细 页面跳转
 * 
 * @author Lubo
 */
@Secure
public class ViewStockProductDetailActionBean extends BaseActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(ViewStockProductDetailActionBean.class);

    /**
     * toStockProductDetail
     * 
     * @return
     */
    @DefaultHandler
    public Resolution toStockProductDetail() {
        log.debug("toStockProductDetail");
        UserEntity user = (UserEntity) getACL().getUser();
        this.getContext().getRequest().setAttribute("role", user.getRoleId());
        return new ForwardResolution("/jsp/report/stockProductDetail.jsp");
    }

}
