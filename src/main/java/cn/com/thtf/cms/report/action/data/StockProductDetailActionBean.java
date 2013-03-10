/**
 * ClassName  库存产品明细
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-11-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.BaseResultData;
import cn.com.thtf.cms.report.dto.StockProductDetailDto;
import cn.com.thtf.cms.report.entity.StockProductDetailEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.service.StockProductDetailService;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;

/**
 * 库存产品明细
 * 
 * @author Lubo
 */
@Secure
public class StockProductDetailActionBean extends BasePageActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(StockProductDetailActionBean.class);
    /** StockProductDetailService */
    private StockProductDetailService service = null;
    /** CommonService */
    private CommonService commonService = null;

    @Setter
    @Getter
    /** 检索条件 */
    @ValidateNestedProperties({
            @Validate(field = "stockroomName", trim = true, on = "getStockProductDetail"),
            @Validate(field = "productTypeName", trim = true, on = "getStockProductDetail"),
            @Validate(field = "productSerieName", trim = true, on = "getStockProductDetail"),
            @Validate(field = "productCode", trim = true, on = "getStockProductDetail"),
            @Validate(field = "productName", trim = true, on = "getStockProductDetail"),
            @Validate(field = "productType", trim = true, on = "getStockProductDetail"),
            @Validate(field = "productUnit", trim = true, on = "getStockProductDetail") })
    private StockProductDetailEntity param = new StockProductDetailEntity();
    /** 显示的列 */
    public static final String fieldRows[] = { "stockroom_name as stockroomName",
            "product_type_name as productTypeName",
            "product_serie_name as productSerieName", "product_code as productCode",
            "product_name as productName", "product_type as productType",
            "product_unit as productUnit", "ifnull(stockroom_count,0) as stockroomCount",
            "ifnull(round(stockroom_price,2),0) as stockroomPrice",
            "ifnull(round(stockroom_money,2),0) as stockroomMoney" };

    /**
     * 检索库存数据
     * 
     * @return
     */
    public Resolution getStockProductDetail() {
        log.debug("检索库存数据");

        /* 封装查询参数 */
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(this.getPage());
        queryParam.setRows(this.getRows());
        queryParam.setSord(this.getSord());
        queryParam.setSidx(this.getSidx());
        queryParam.setStar((this.getPage() - 1) * this.getRows());
        queryParam.setFieldsParam(this.getFields(), fieldRows);// 显示的列
        queryParam.setQuery(param);// 查询条件

        UserEntity user = (UserEntity) getACL().getUser();
        commonService = getBean("commonService", CommonService.class);
        boolean checkResult = commonService.getUserFileParam(user, queryParam);
        if (!checkResult) {
            return new JSONResolution("");
        }

        service = getBean("stockProductDetailService", StockProductDetailService.class);
        service.getStockProductDetail(queryParam);

        /* 判断错误 */
        if (this.hasError()) {
            log.error("检索发生错误!");
        }

        /* 封装返回结果 */
        BaseResultData result = new BaseResultData(queryParam.getPage(),
                queryParam.getRecords(), queryParam.getTotal());

        @SuppressWarnings("unchecked")
        List<StockProductDetailEntity> dateList = (List<StockProductDetailEntity>) queryParam
                .getDataList();
        for (StockProductDetailEntity stockProductDetailEntity : dateList) {
            /* 页面数据,需与页面定义顺序一致 */
            result.addCells(stockProductDetailEntity.getStockroomName(),
                    stockProductDetailEntity.getProductTypeName(),
                    stockProductDetailEntity.getProductSerieName(),
                    stockProductDetailEntity.getProductCode(),
                    stockProductDetailEntity.getProductName(),
                    stockProductDetailEntity.getProductType(),
                    stockProductDetailEntity.getProductUnit(),
                    String.valueOf(stockProductDetailEntity.getStockroomCount()),
                    String.valueOf(stockProductDetailEntity.getStockroomPrice()),
                    String.valueOf(stockProductDetailEntity.getStockroomMoney()));
        }

        return new JSONResolution(result);
    }

    /**
     * 检索库存数据合计值
     * 
     * @return
     */
    public Resolution getStockProductDetailSum() {
        log.debug("检索库存数据合计值");

        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setQuery(param);// 查询条件

        UserEntity user = (UserEntity) getACL().getUser();
        commonService = getBean("commonService", CommonService.class);
        boolean checkResult = commonService.getUserFileParam(user, queryParam);
        if (!checkResult) {
            return new JSONResolution("");
        }

        service = getBean("stockProductDetailService", StockProductDetailService.class);
        StockProductDetailDto result = service.getStockProductDetailSumVal(queryParam);

        /* 判断错误 */
        if (this.hasError()) {
            log.error("检索发生错误!");
        }
        return new JSONResolution(result);
    }

}
