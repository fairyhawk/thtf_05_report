/**
 * ClassName  ViewSellContractDetailActionBean
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-4
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
 * 
 * @author liuqg
 */
@Secure
public class ViewSellContractDetailActionBean extends BaseActionBean {
    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(ViewSellContractDetailActionBean.class);

    /**
     * 跳转到销售合同产品页面
     * 
     * @return
     */
    @DefaultHandler
    public Resolution roRSellContractDetail() {
        log.debug("销售合同产品明细查看页面");
        UserEntity user = (UserEntity) getACL().getUser();
        this.getContext().getRequest().setAttribute("roleId", user.getRoleId());
        return new ForwardResolution("/jsp/report/sellContractDetail.jsp");
    }

}
