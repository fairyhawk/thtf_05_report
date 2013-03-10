package cn.com.thtf.cms.report.action.view;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.shiy.common.cws.action.BaseActionBean;
import cn.shiy.common.cws.security.Secure;

/**
 * 运费分摊 页面跳转
 * 
 * @author zhangzx
 */
@Secure
public class ViewTransportFareSharingActionBean extends BaseActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(ViewTransportFareSharingActionBean.class);

    /**
     * toTransportFareSharing
     * 
     * @return
     */
    @DefaultHandler
    public Resolution toTransportFareSharing() {
        log.debug("toTransportFareSharing");
        return new ForwardResolution("/jsp/report/transportFareSharing.jsp");
    }

}
