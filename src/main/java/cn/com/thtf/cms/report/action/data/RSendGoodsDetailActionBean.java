/**
 * ClassName  RSendGoodsDetailActionBean
 *
 * History
 * Create User: hanrb
 * Create Date: 2010-12-14
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.data;

import java.math.RoundingMode;
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
import cn.com.thtf.cms.report.dto.RSendGoodsDetailDto;
import cn.com.thtf.cms.report.entity.RSendGoodsDetailEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.service.RSendGoodsDetailService;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;

/**
 * 
 * @author hanrb
 */
@Secure
public class RSendGoodsDetailActionBean extends BasePageActionBean {

    /** log */
    private final Logger log = LoggerFactory.getLogger(getClass());
    /** 注入 */
    private RSendGoodsDetailService sendGoodsDetailImpl = (RSendGoodsDetailService) getBean(
            "sendGoodsDetailService", null);
    /** common */
    private CommonService baseCommonService = (CommonService) getBean("commonService",
            null);
    @Setter
    @Getter
    /** 检索条件 */
    @ValidateNestedProperties({
            @Validate(field = "sellContractId", trim = true, on = "getDateList"),
            @Validate(field = "sendGoodsId", trim = true, on = "getDateList"),
            @Validate(field = "productContractCode", trim = true, on = "getDateList"),
            @Validate(field = "companyContractCode", trim = true, on = "getDateList"),
            @Validate(field = "customerName", trim = true, on = "getDateList"),
            @Validate(field = "productName", trim = true, on = "getDateList"),
            @Validate(field = "productCode", trim = true, on = "getDateList"),
            @Validate(field = "productType", trim = true, on = "getDateList"),
            @Validate(field = "productTypeName", trim = true, on = "getDateList"),
            @Validate(field = "ontractProName", trim = true, on = "getDateList"),
            @Validate(field = "sendGoodsTypeName", trim = true, on = "getDateList"),
            @Validate(field = "sendGoodsStatusName", trim = true, on = "getDateList") })
    private RSendGoodsDetailDto param = new RSendGoodsDetailDto();
    public static String fieldRows[] = { "sell_contract_id as sellContractId",
            "product_contract_code as productContractCode",
            "company_contract_code as companyContractCode",
            "send_goods_id as sendGoodsId",
            "send_goods_status_name as sendGoodsStatusName",
            "send_goods_type_name as sendGoodsTypeName",
            "product_type_name as productTypeName", "customer_name as customerName",
            "request_date as requestDate", "send_date as sendDate",
            "request_reach_date as requestReachDate", "stockroom_name as stockroomName",
            "product_name as productName", "product_code as productCode",
            "product_type as productType", "product_unit as productUnit",
            "product_money_tax as productMoneyTax", "detail_count as detailCount",
            "detail_count_rel as detailCountRel", "detail_money_rel as detailMoneyRel",
            "detail_money as detailMoney", "back_count as backCount",
            "back_money as backMoney", "detail_price as detailPrice",
            "product_money_cost as productMoneyCost",
            "product_money_profit as productMoneyProfit",
            "product_money_profitrate as productMoneyProfitrate",
            "appoint_money as appointMoney", "appoint_money_otw as appointMoneyOtw",
            "appoint_money_ext as appointMoneyExt", "user_name as userName",
            "make_invoice_money as makeInvoiceMoney", "datetime", "text",
            "user_area_name as userAreaName",
            "product_money_cost_tax as productMoneyCostTax",
            "product_money_profit_tax as productMoneyProfitTax",
            "customer_province as customerProvince", "customer_city as customerCity",
            "customer_area as customerArea", "contract_Pro_Name as contractProName",
            "ca_name as caName" };

    /**
     * jsp页面初始化显示列表数据
     * 
     */
    public Resolution getDateList() {
        /* 登录user类 */
        UserEntity user = (UserEntity) getACL().getUser();
        BaseQueryData<RSendGoodsDetailDto> queryParam = new BaseQueryData<RSendGoodsDetailDto>();
        queryParam.setFieldsParam(this.getFields(), fieldRows);
        queryParam.setQuery(param);
        /** 数据 传ibatis */
        queryParam.setPage(this.getPage());
        queryParam.setRows(this.getRows());
        queryParam.setSord(this.getSord());
        queryParam.setSidx(this.getSidx());
        queryParam.setStar((this.getPage() - 1) * this.getRows());
        boolean success = baseCommonService.getUserFileParam(user, queryParam);
        if (!success) {
            return new JSONResolution("");
        }
        if (user.getRoleId() == 10) {
            queryParam.setUserId(null);
        }
        sendGoodsDetailImpl.getSendGoodsDetailList(queryParam);
        BaseResultData result = new BaseResultData(queryParam.getPage(),
                queryParam.getRecords(), queryParam.getTotal());

        @SuppressWarnings("unchecked")
        List<RSendGoodsDetailDto> dateList = (List<RSendGoodsDetailDto>) queryParam
                .getDataList();
        log.debug(String.valueOf(dateList.size()));
        for (RSendGoodsDetailDto send : dateList) {
            result.addCells(
                    send.getSellContractId(),
                    send.getProductContractCode(),
                    send.getCompanyContractCode(),
                    send.getSendGoodsId(),
                    send.getSendGoodsStatusName(),
                    send.getSendGoodsTypeName(),
                    send.getProductTypeName(),
                    send.getCustomerName(),
                    send.getRequestDate(),
                    send.getSendDate(),
                    send.getRequestReachDate(),
                    send.getStockroomName(),
                    send.getProductName(),
                    send.getProductCode(),
                    send.getProductType(),
                    send.getProductUnit(),
                    String.valueOf(send.getProductMoneyTax()),
                    String.valueOf(send.getDetailCount()),
                    String.valueOf(send.getDetailCountRel()),
                    send.getDetailMoneyRel() == null ? "0" : send.getDetailMoneyRel()
                            .setScale(2, RoundingMode.HALF_UP).toString(),
                    send.getDetailMoney() == null ? "0" : send.getDetailMoney()
                            .setScale(2, RoundingMode.HALF_UP).toString(),
                    String.valueOf(send.getBackCount()),
                    send.getBackMoney() == null ? "0" : send.getBackMoney()
                            .setScale(2, RoundingMode.HALF_UP).toString(), send
                            .getDetailPrice() == null ? "0" : send.getDetailPrice()
                            .setScale(2, RoundingMode.HALF_UP).toString(), send
                            .getProductMoneyCost() == null ? "0" : send
                            .getProductMoneyCost().setScale(2, RoundingMode.HALF_UP)
                            .toString(),
                    send.getProductMoneyProfit() == null ? "0" : send
                            .getProductMoneyProfit().setScale(2, RoundingMode.HALF_UP)
                            .toString(),
                    send.getProductMoneyProfitrate() == null ? "0" : send
                            .getProductMoneyProfitrate()
                            .setScale(2, RoundingMode.HALF_UP).toString(), send
                            .getAppointMoney() == null ? "0" : send.getAppointMoney()
                            .setScale(2, RoundingMode.HALF_UP).toString(), send
                            .getAppointMoneyOtw() == null ? "0" : send
                            .getAppointMoneyOtw().setScale(2, RoundingMode.HALF_UP)
                            .toString(), send.getAppointMoneyExt() == null ? "0" : send
                            .getAppointMoneyExt().setScale(2, RoundingMode.HALF_UP)
                            .toString(), send.getUserName(),
                    send.getMakeInvoiceMoney() == null ? "0" : send.getMakeInvoiceMoney()
                            .setScale(2, RoundingMode.HALF_UP).toString(), send
                            .getDatetime(), send.getText(), send.getUserAreaName(), send
                            .getProductMoneyCostTax() == null ? "0" : send
                            .getProductMoneyCostTax().setScale(2, RoundingMode.HALF_UP)
                            .toString(),
                    send.getProductMoneyProfitTax() == null ? "0" : send
                            .getProductMoneyProfitTax().setScale(2, RoundingMode.HALF_UP)
                            .toString(), send.getCustomerProvince(), send
                            .getCustomerCity(), send.getCustomerArea(), send
                            .getContractProName(), send.getCaName()

            );
        }
        return new JSONResolution(result);
    }

    /**
     * jsp页面合计值
     * 
     */
    public Resolution getDateSumList() {
        log.debug("检索合计值");
        /* 登录user类 */
        UserEntity user = (UserEntity) getACL().getUser();
        BaseQueryData<RSendGoodsDetailDto> queryParam = new BaseQueryData<RSendGoodsDetailDto>();
        queryParam.setQuery(param);
        boolean success = baseCommonService.getUserFileParam(user, queryParam);
        if (!success) {
            return new JSONResolution("");
        }
        if (user.getRoleId() == 10) {
            queryParam.setUserId(null);
        }
        RSendGoodsDetailEntity result = sendGoodsDetailImpl
                .getSendGoodsDetailSum(queryParam);
        log.info(new JSONResolution(result).toJson());
        return new JSONResolution(result);
    }

}
