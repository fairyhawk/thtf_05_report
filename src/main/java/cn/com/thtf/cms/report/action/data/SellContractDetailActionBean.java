/**
 * ClassName  SellContractDetailActionBean
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-4
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
import cn.com.thtf.cms.report.dto.RSellContractDetailDto;
import cn.com.thtf.cms.report.entity.RSellContractDetailEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.service.RSellContractDetailService;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;

/**
 * 销售合同产品明细
 * 
 * @author liuqg
 */
@Secure
public class SellContractDetailActionBean extends BasePageActionBean {

    private static final Logger log = LoggerFactory
            .getLogger(SellContractDetailActionBean.class);

    @Setter
    @Getter
    /**
     * 画面检索条件
     */@ValidateNestedProperties({
            @Validate(field = "sell_contract_id", trim = true, on = "getSellContractDetail"),
            @Validate(field = "product_name", trim = true, on = "getSellContractDetail"),
            @Validate(field = "product_contract_code", trim = true, on = "getSellContractDetail"),
            @Validate(field = "company_contract_code", trim = true, on = "getSellContractDetail"),
            @Validate(field = "product_type", trim = true, on = "getSellContractDetail"),
            @Validate(field = "product_code", trim = true, on = "getSellContractDetail"),
            @Validate(field = "customer_name", trim = true, on = "getSellContractDetail"),
            @Validate(field = "product_type_name", trim = true, on = "getSellContractDetail"),
            @Validate(field = "user_name", trim = true, on = "getSellContractDetail"),
            @Validate(field = "contract_pro_name", trim = true, on = "getSellContractDetail"),
            @Validate(field = "status", trim = true, on = "getSellContractDetail") })
    private RSellContractDetailDto param = new RSellContractDetailDto();

    /** SellContractDetailService */
    private RSellContractDetailService rSellContractDetailService = (RSellContractDetailService) getBean(
            "sellContractDetailService", RSellContractDetailService.class);

    /** commonService */
    private CommonService commonService = getBean("commonService", CommonService.class);

    public static final String fieldRows[] = { "sell_contract_id as sell_contract_id",
            "product_contract_code as product_contract_code",
            "company_contract_code as company_contract_code",
            "status_name as status_name", "user_area_name as user_area_name",
            "user_name as user_name", "customer_name as customer_name",
            "contract_pro_name as contract_pro_name", "money as money",
            "product_code as product_code", "product_name as product_name",
            "product_type as product_type", "product_unit as product_unit",
            "count as count", "price as price", "sell_money as sell_money",
            "product_limit_price as product_limit_price",
            "send_goods_money as send_goods_money",
            "fact_send_goods_money as fact_send_goods_money",
            "send_goods_count as send_goods_count",
            "fact_send_goods_count as fact_send_goods_count",
            "prepare_goods as prepare_goods", "stock_goods_count as stock_goods_count",
            "appoint_money as appoint_money",
            "in_transit_appoint_money as in_transit_appoint_money",
            "sell_invoice_money as sell_invoice_money",
            "back_contract_money as back_contract_money",
            "back_contract_count as back_contract_count", "back_money as back_money",
            "back_count as back_count", "fact_sell_count as fact_sell_count",
            "contract_not_implemented_count as contract_not_implemented_count" };

    public Resolution getSellContractDetail() {
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        queryParam.setFieldsParam(this.getFields(), fieldRows);
        queryParam.setQuery(param);
        /** 数据 传ibatis */
        queryParam.setPage(this.getPage());
        queryParam.setRows(this.getRows());
        queryParam.setSord(this.getSord());
        queryParam.setSidx(this.getSidx());
        queryParam.setStar((this.getPage() - 1) * this.getRows());
        /* 登录user类 */
        UserEntity user = (UserEntity) getACL().getUser();
        boolean success = commonService.getUserFileParam(user, queryParam);
        if (!success) {
            return new JSONResolution("");
        }
        /* 产品总监时将userid设置为空，只有销售经理时按userid检索 */
        if (user.getRoleId() == 10) {
            queryParam.setUserId(null);
        }
        rSellContractDetailService.getRSellContractDetail(queryParam);
        /* 判断错误 */
        if (this.hasError()) {
            log.error("检索销售合同产品明细错误!");
        }
        BaseResultData result = new BaseResultData(queryParam.getPage(),
                queryParam.getRecords(), queryParam.getTotal());
        @SuppressWarnings("unchecked")
        List<RSellContractDetailEntity> dateList = (List<RSellContractDetailEntity>) queryParam
                .getDataList();
        for (RSellContractDetailEntity sellcontract : dateList) {
            result.addCells(sellcontract.getSell_contract_id(),
                    sellcontract.getProduct_contract_code(),
                    sellcontract.getCompany_contract_code(),
                    sellcontract.getStatus_name(),
                    String.valueOf(sellcontract.getUser_area_name()),
                    String.valueOf(sellcontract.getUser_name()),
                    String.valueOf(sellcontract.getCustomer_name()),
                    String.valueOf(sellcontract.getContract_pro_name()),
                    String.valueOf(sellcontract.getMoney()),
                    sellcontract.getProduct_code(), sellcontract.getProduct_name(),
                    sellcontract.getProduct_type(), sellcontract.getProduct_unit(),
                    String.valueOf(sellcontract.getCount()),
                    String.valueOf(sellcontract.getPrice()),
                    String.valueOf(sellcontract.getSell_money()),
                    String.valueOf(sellcontract.getProduct_limit_price()),
                    String.valueOf(sellcontract.getSend_goods_money()),
                    String.valueOf(sellcontract.getFact_send_goods_money()),
                    String.valueOf(sellcontract.getSend_goods_count()),
                    String.valueOf(sellcontract.getFact_send_goods_count()),
                    String.valueOf(sellcontract.getPrepare_goods()),
                    String.valueOf(sellcontract.getStock_goods_count()),
                    String.valueOf(sellcontract.getAppoint_money()),
                    String.valueOf(sellcontract.getIn_transit_appoint_money()),
                    String.valueOf(sellcontract.getSell_invoice_money()),
                    String.valueOf(sellcontract.getBack_contract_money()),
                    String.valueOf(sellcontract.getBack_contract_count()),
                    String.valueOf(sellcontract.getBack_money()),
                    String.valueOf(sellcontract.getBack_count()),
                    String.valueOf(sellcontract.getFact_sell_count()),
                    String.valueOf(sellcontract.getContract_not_implemented_count())

            );
        }
        return new JSONResolution(result);
    }

    public Resolution getSellContractDetailSumVal() {
        log.debug("检索合计值:SellContractDetailSumVal");
        /* 登录user类 */
        UserEntity user = (UserEntity) getACL().getUser();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        queryParam.setQuery(param);
        boolean success = commonService.getUserFileParam(user, queryParam);

        if (!success) {
            return new JSONResolution("");
        }
        if (user.getRoleId() == 10) {
            queryParam.setUserId(null);
        }
        RSellContractDetailEntity result = rSellContractDetailService
                .getRSellContractDetailSumVal(queryParam);
        /* 判断错误 */
        if (this.hasError()) {
            log.error("检索销售合同产品明细合计值错误!");
        }
        return new JSONResolution(result);
    }

}
