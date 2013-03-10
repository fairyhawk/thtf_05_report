/**
 * ClassName  ViewRTransportFareDetailActionBean
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.view;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.action.data.BasePageActionBean;
import cn.shiy.common.cws.security.Secure;

/**
 * 
 * @author hanrb
 */
@Secure
public class ViewRTransportFareDetailActionBean extends BasePageActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(ViewRTransportFareDetailActionBean.class);

    @DefaultHandler
    public Resolution toransportFareDetail() {
        log.debug("toransportFareDetail");
        return new ForwardResolution("/jsp/report/transportFareDetail.jsp");
    }

}
