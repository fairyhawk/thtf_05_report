/**
 * ClassName  BasePromptActionBean
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-6
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.dto.PromptQueryDto;
import cn.com.thtf.cms.report.service.CommonService;
import cn.shiy.common.cws.action.BaseActionBean;
import cn.shiy.common.cws.action.JSONResolution;

/**
 * 提示框补全
 * 
 * @author hanrb
 */

public class BasePromptActionBean extends BaseActionBean {
    /* log */
    private final Logger log = LoggerFactory.getLogger(getClass());
    /* 注入 */
    @Setter
    @Getter
    private CommonService baseCommonService = (CommonService) getBean(
            "baseCommonService", null);
    @Setter
    @Getter
    private String customerName;// 查询的客户名称字段名
    @Setter
    @Getter
    private String stockroomName;// 查询的库房名称字段名
    @Setter
    @Getter
    PromptQueryDto promptQueryDto = new PromptQueryDto();

    public Resolution getPromptData() {
        List<Object> promptData = new ArrayList<Object>();
        /* 客户名提示查询 */
        if (customerName != null && "customer".equals(customerName)) {
            promptData = baseCommonService.getPromptCustomerData(promptQueryDto);
        }
        /* 库房名称提示查询 */
        if (stockroomName != null && "stockroom".equals(stockroomName)) {
            promptData = baseCommonService.getPromptSupplierData(promptQueryDto);
        }
        Map<String, List<Object>> prompt = new HashMap<String, List<Object>>();
        prompt.put("promptList", promptData);
        log.info(new JSONResolution(prompt).toJson());
        return new JSONResolution(prompt);
    }
}
