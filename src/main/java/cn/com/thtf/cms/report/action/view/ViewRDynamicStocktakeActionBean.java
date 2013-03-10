package cn.com.thtf.cms.report.action.view;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.shiy.common.cws.action.BaseActionBean;

/**
 * 动态盘点 页面跳转
 * 
 * @author HanHaiyun
 * 
 */
public class ViewRDynamicStocktakeActionBean extends BaseActionBean {
    private static final Logger log = LoggerFactory
            .getLogger(ViewRDynamicStocktakeActionBean.class);

    public Resolution toStocktakeList() {
        log.info("页面跳转：动态盘点表");
        return new ForwardResolution("/jsp/report/dynamicStocktake.jsp");
    }
}
