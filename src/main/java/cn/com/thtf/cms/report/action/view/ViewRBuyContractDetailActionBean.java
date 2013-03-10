package cn.com.thtf.cms.report.action.view;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.shiy.common.cws.action.BaseActionBean;

public class ViewRBuyContractDetailActionBean extends BaseActionBean {
    private static final Logger log = LoggerFactory
            .getLogger(ViewRBuyContractDetailActionBean.class);

    public Resolution toBuyContractList() {
        log.info("页面跳转：采购合同明细表");
        return new ForwardResolution("/jsp/report/buyContractDetail.jsp");
    }
}
