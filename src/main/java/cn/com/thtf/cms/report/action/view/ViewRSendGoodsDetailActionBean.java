/**
 * ClassName  ViewRSendGoodsDetailActionBean
 *
 * History
 * Create User: hanrb
 * Create Date: 2010-12-27
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
 * 发货明细 页面跳转
 * 
 * @author hanrb
 */
@Secure
public class ViewRSendGoodsDetailActionBean extends BaseActionBean {
    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(ViewStockProductDetailActionBean.class);

    @DefaultHandler
    public Resolution toSendGoodsDetail() {
        log.debug("toSendGoodsDetail");
        UserEntity user = (UserEntity) getACL().getUser();
        this.getContext().getRequest().setAttribute("role", user.getRoleId());
        return new ForwardResolution("/jsp/report/sendGoodsDetail.jsp");
    }
}
