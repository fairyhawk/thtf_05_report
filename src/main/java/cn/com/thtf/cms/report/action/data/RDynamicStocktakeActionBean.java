package cn.com.thtf.cms.report.action.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.BaseResultData;
import cn.com.thtf.cms.report.entity.RDynamicStocktakeEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.service.RDynamicStocktakeService;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * 动态盘点管理
 * 
 * @author HanHaiyun
 * 
 */
@Secure 
public class RDynamicStocktakeActionBean extends BasePageActionBean {
    private static final Logger log = LoggerFactory
            .getLogger(RDynamicStocktakeActionBean.class);
    private RDynamicStocktakeService rDynamicStocktakeService;
    /** CommonService */
    private CommonService commonService = null;
    @Setter
    @Getter
    /***/
    @ValidateNestedProperties( {
            @Validate(field = "stockName", trim = true, on = "getStocktakeList"),
            @Validate(field = "productCode", trim = true, on = "getStocktakeList"),
            @Validate(field = "productName", trim = true, on = "getStocktakeList"),
            @Validate(field = "productType", trim = true, on = "getStocktakeList"),
            @Validate(field = "productUnit", trim = true, on = "getStocktakeList"),
            @Validate(field = "shortName", trim = true, on = "getStocktakeList") })
    private RDynamicStocktakeEntity param = new RDynamicStocktakeEntity();
    public static final String[] fields = { "stock_name as stockName", "product_code as productCode",
            "product_name as productName", "product_type as productType",
            "product_unit as productUnit", "stockroom_count as stockroomCount",
            "stock_num_sum as stockNumSum", "short_name as shortName" };

    /**
     * 检索动态盘点表数据
     * 
     * @return 动态盘点数据
     */
    @SuppressWarnings("unchecked")
    public Resolution getStocktakeList() {
        log.debug("检索动态盘点数据");
        UserEntity user = (UserEntity) getACL().getUser();
        /* 封装查询参数 */
        BaseQueryData<RDynamicStocktakeEntity> queryParam = new BaseQueryData<RDynamicStocktakeEntity>();
        queryParam.setPage(this.getPage());
        queryParam.setRows(this.getRows());
        queryParam.setSord(this.getSord());
        queryParam.setSidx(this.getSidx());
        queryParam.setStar((this.getPage() - 1) * this.getRows());
        queryParam.setFieldsParam(this.getFields(), fields);// 显示的列
        queryParam.setQuery(param);// 查询条件

        commonService = getBean("commonService", CommonService.class);
        boolean checkResult = commonService.getUserFileParam(user, queryParam);
        if (!checkResult) {
            return new JSONResolution("");
        }

        rDynamicStocktakeService = getBean("rDynamicStocktakeService",
                RDynamicStocktakeService.class);
        rDynamicStocktakeService.getStocktakeList(queryParam);
        if (this.hasError()) {
            log.debug("检索动态盘点数据发生错误！");
        }
        BaseResultData result = new BaseResultData(queryParam.getPage(), queryParam
                .getRecords(), queryParam.getTotal());
        List<RDynamicStocktakeEntity> entitys = (List<RDynamicStocktakeEntity>) queryParam
                .getDataList();
        for (RDynamicStocktakeEntity entity : entitys) {
            result.addCells(entity.getStockName(), entity.getProductCode(), entity
                    .getProductName(), entity.getProductType(), entity.getProductUnit(),
                    String.valueOf(entity.getStockroomCount()), String.valueOf(entity
                            .getStockNumSum()), entity.getShortName());
        }
        return new JSONResolution(result);
    }
}
