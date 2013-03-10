/**
 * ClassName  ViewSiteInformationActionBean
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-2-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.view.base;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.shiy.common.cws.action.BaseActionBean;
import cn.shiy.common.cws.security.Secure;

/**
 * 站内信管理
 * 
 * @author Lubo
 */
@Secure
public class ViewSiteInformationActionBean extends BaseActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(ViewSiteInformationActionBean.class);

    /**
     * toSiteInformation
     * 
     * @return
     */
    @DefaultHandler
    public Resolution toSiteInformation() {
        log.debug("toSiteInformation");
        return new ForwardResolution("/data/base/SiteInformation.action?siteInformationList=");
    }
    public Resolution showSiteInformationList() {
        log.debug("showSiteInformationList");
        return new ForwardResolution("/data/base/SiteInformation.action?showSiteInformationList=");
    }
    public Resolution getSubject() {
        log.debug("getSubject");
        return new ForwardResolution("/data/base/SiteInformation.action?getSubject=");
    }
}
