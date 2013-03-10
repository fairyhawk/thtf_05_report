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
 * 入库明细 页面跳转
 * 
 * @author zhangzx
 */
@Secure
public class ViewInstockDetailActionBean extends BaseActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(ViewInstockDetailActionBean.class);

    /**
     * toInstockDetail
     * 
     * @return
     */
    @DefaultHandler
    public Resolution toInstockDetail() {
        log.debug("toInstockDetail");
        UserEntity user = (UserEntity) getACL().getUser();
        this.getContext().getRequest().setAttribute("role", user.getRoleId());
        return new ForwardResolution("/jsp/report/instockDetail.jsp");
    }

}
