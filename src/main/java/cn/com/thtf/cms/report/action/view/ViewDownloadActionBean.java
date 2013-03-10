/**
 * ClassName  ViewDownloadActionBean
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-1-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.view;

import lombok.Getter;
import lombok.Setter;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.shiy.common.cws.action.BaseActionBean;
import cn.shiy.common.cws.security.Secure;

/**
 * ViewDownloadActionBean
 * 
 * @author Lubo
 */
@Secure
public class ViewDownloadActionBean extends BaseActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(ViewDownloadActionBean.class);

    @Setter
    @Getter
    private String nodeId;

    /**
     * toDownload
     * 
     * @return
     */
    @DefaultHandler
    public Resolution toDownload() {
        log.debug("toDownload,nodeId:{}", nodeId);
        return new ForwardResolution("/jsp/report/download.jsp");
    }

}
