package cn.com.thtf.cms.report.action.data;

import java.math.RoundingMode;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.service.RBuyContractDetailService;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.Validate;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.BaseResultData;
import cn.com.thtf.cms.report.dto.RBuyContractDetailDto;
import cn.com.thtf.cms.report.entity.RBuyContractDetailEntity;
import cn.com.thtf.cms.report.entity.UserEntity;

/**
 * 采购合同明细管理
 * 
 * @author HanHaiyun
 * 
 */
@Secure
public class RBuyContractDetailActionBean extends BasePageActionBean {
    private static final Logger log = LoggerFactory
            .getLogger(RBuyContractDetailActionBean.class);
    private RBuyContractDetailService buyContractDetailService;
    /** CommonService */
    private CommonService commonService = null;
    @Getter
    @Setter
    @ValidateNestedProperties( {
            @Validate(field = "productContractCode", trim = true, on = "getBuyContractDetailList"),
            @Validate(field = "companyContractCode", trim = true, on = "getBuyContractDetailList"),
            @Validate(field = "productTypeName", trim = true, on = "getBuyContractDetailList"),
            @Validate(field = "supplierName", trim = true, on = "getBuyContractDetailList"),
            @Validate(field = "productCode", trim = true, on = "getBuyContractDetailList"),
            @Validate(field = "productName", trim = true, on = "getBuyContractDetailList"),
            @Validate(field = "productType", trim = true, on = "getBuyContractDetailList"),
            @Validate(field = "buyContractId", trim = true, on = "getBuyContractDetailList"),
            @Validate(field = "minRequestDate", trim = true, on = "getBuyContractDetailList"),
            @Validate(field = "maxRequestDate", trim = true, on = "getBuyContractDetailList") })
    private RBuyContractDetailEntity param = new RBuyContractDetailEntity();
    public static final String[] fields = { "buy_contract_id           AS buyContractId",
            "product_contract_code       AS productContractCode",
            "company_contract_code       AS companyContractCode",
            "supplier_name               AS supplierName",
            "product_type_name           AS productTypeName",
            "product_code                AS productCode",
            "product_name                AS productName",
            "product_type                AS productType",
            "product_unit                AS productUnit",
            "buy_contract_detail_count   AS buyContractDetailCount",
            "buy_contract_price          AS buyContractPrice",
            "buy_contract_money          AS buyContractMoney",
            "in_stock_money              AS inStockMoney",
            "in_stock_count              AS inStockCount",
            "appoint_money               AS appointMoney",
            "invoice_money               AS invoiceMoney",
            "buy_back_contract_money     AS buyBackContractMoney",
            "buy_back_contract_count     AS buyBackContractCount",
            "buy_back_goods_money        AS buyBackGoodsMoney",
            "buy_back_goods_count        AS buyBackGoodsCount",
            "fact_in_stock_count         AS factInStockCount",
            "fact_in_stock_money         AS factInStockMoney",
            "not_in_stock_count          AS notInStockCount", "text",
            "fact_contract_count         AS factContractCount",
            "request_date                AS requestDate" };

    /**
     * 检索采购合同明细管理 数据检索
     * 
     * @return 采购合同明细数据
     */
    @SuppressWarnings("unchecked")
    public Resolution getBuyContractDetailList() {
        log.debug("检索采购合同明细数据");
        buyContractDetailService = getBean("rBuyContractDetailService",
                RBuyContractDetailService.class);
        UserEntity user = (UserEntity) getACL().getUser();
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(getPage());
        queryParam.setRows(getRows());
        queryParam.setSidx(getSidx());
        queryParam.setSord(getSord());
        queryParam.setStar((getPage() - 1) * this.getRows());
        queryParam.setFieldsParam(this.getFields(), fields);
        queryParam.setQuery(param);
        commonService = getBean("commonService", CommonService.class);
        boolean checkResult = commonService.getUserFileParam(user, queryParam);
        if (!checkResult) {
            return new JSONResolution("");
        }

        buyContractDetailService.getBuyContractDetailList(queryParam);
        if (this.hasError()) {
            log.debug("检索采购合同明细数据发生错误！");
        }
        BaseResultData result = new BaseResultData(queryParam.getPage(), queryParam
                .getRecords(), queryParam.getTotal());
        List<RBuyContractDetailEntity> dataList = (List<RBuyContractDetailEntity>) queryParam
                .getDataList();
        for (RBuyContractDetailEntity entity : dataList) {
            result.addCells(entity.getBuyContractId(), entity.getProductContractCode(),
                    entity.getCompanyContractCode(), entity.getSupplierName(), entity
                            .getProductTypeName(), entity.getProductCode(), entity
                            .getProductName(), entity.getProductType(), entity
                            .getProductUnit(),
                    entity.getBuyContractDetailCount() == null ? "0" : entity
                            .getBuyContractDetailCount().toString(), entity
                            .getBuyContractPrice() == null ? "" : entity
                            .getBuyContractPrice().setScale(5, RoundingMode.HALF_UP)
                            .toString(), entity.getBuyContractMoney() == null ? ""
                            : entity.getBuyContractMoney().setScale(5,
                                    RoundingMode.HALF_UP).toString(), entity
                            .getInStockMoney() == null ? "" : entity.getInStockMoney()
                            .setScale(5, RoundingMode.HALF_UP).toString(), entity
                            .getInStockCount() == null ? "0" : entity.getInStockCount()
                            .toString(), entity.getAppointMoney() == null ? "" : entity
                            .getAppointMoney().setScale(5, RoundingMode.HALF_UP)
                            .toString(), entity.getInvoiceMoney() == null ? "" : entity
                            .getInvoiceMoney().setScale(5, RoundingMode.HALF_UP)
                            .toString(), entity.getBuyBackContractMoney() == null ? ""
                            : entity.getBuyBackContractMoney().setScale(5,
                                    RoundingMode.HALF_UP).toString(), entity
                            .getBuyBackContractCount() == null ? "0" : entity
                            .getBuyBackContractCount().toString(), entity
                            .getBuyBackGoodsMoney() == null ? "" : entity
                            .getBuyBackGoodsMoney().setScale(5, RoundingMode.HALF_UP)
                            .toString(), entity.getBuyBackGoodsCount() == null ? "0"
                            : entity.getBuyBackGoodsCount().toString(), entity
                            .getFactInStockCount() == null ? "0" : entity
                            .getFactInStockCount().toString(), entity
                            .getFactInStockMoney() == null ? "" : entity
                            .getFactInStockMoney().setScale(5, RoundingMode.HALF_UP)
                            .toString(), entity.getNotInStockCount() == null ? "0"
                            : entity.getNotInStockCount().toString(), entity.getText(),
                    entity.getFactContractCount() == null ? "0" : entity
                            .getFactContractCount().toString(),entity.getRequestDate());
        }
        return new JSONResolution(result);
    }

    /**
     * 采购合同明细管理 求各类金额总和
     * 
     * @return 各种金额总和
     */
    public Resolution getBuyContractDetailSum() {
        buyContractDetailService = getBean("rBuyContractDetailService",
                RBuyContractDetailService.class);
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setQuery(param);
        commonService = getBean("commonService", CommonService.class);
        UserEntity user = (UserEntity) getACL().getUser();
        boolean checkResult = commonService.getUserFileParam(user, queryParam);
        if (!checkResult) {
            return new JSONResolution("");
        }
        RBuyContractDetailDto rBuyContractDetailDto = buyContractDetailService
                .getBuyContractDeatilSum(queryParam);
        return new JSONResolution(rBuyContractDetailDto);
    }
}
