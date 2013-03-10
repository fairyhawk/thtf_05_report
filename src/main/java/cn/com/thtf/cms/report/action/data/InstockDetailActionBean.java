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
import cn.com.thtf.cms.report.dto.InstockDetailDto;
import cn.com.thtf.cms.report.entity.InstockDetailEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.service.InstockDetailService;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;

/**
 * 入库明细
 * 
 * @author zhangzx
 */
@Secure
public class InstockDetailActionBean extends BasePageActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(InstockDetailActionBean.class);
    /** InstockDetailService */
    private InstockDetailService service = null;
    /** CommonService */
    private CommonService commonService = null;
    
    @Setter
    @Getter
    /** 检索条件 */
    @ValidateNestedProperties( {
            @Validate(field = "buyContractId", trim = true, on = "getInstockDetail"),
            @Validate(field = "productContractCode", trim = true, on = "getInstockDetail"),
            @Validate(field = "companyContractCode", trim = true, on = "getInstockDetail"),
            @Validate(field = "productTypeName", trim = true, on = "getInstockDetail"),
            @Validate(field = "instockId", trim = true, on = "getInstockDetail"),
            @Validate(field = "stockroomName", trim = true, on = "getInstockDetail"),
            @Validate(field = "supplierName", trim = true, on = "getInstockDetail"),
            @Validate(field = "userName", trim = true, on = "getInstockDetail"), 
            @Validate(field = "statusName", trim = true, on = "getInstockDetail"),
            @Validate(field = "productCode", trim = true, on = "getInstockDetail"),
            @Validate(field = "productType", trim = true, on = "getInstockDetail"),
            @Validate(field = "productName", trim = true, on = "getInstockDetail")
    })
    private InstockDetailDto param = new InstockDetailDto();
    /** 显示的列 */
    public static final String fieldRows[] = { "buy_contract_id as buyContractId",
            "product_contract_code as productContractCode",
            "company_contract_code as companyContractCode",
            "product_type_name as productTypeName", 
            "instock_id as instockId",
            "stockroom_name as stockroomName", 
            "supplier_name as supplierName",
            "instock_totalmoney as instockTotalmoney", 
            "date",
            "stock_date as stockDate",
            "user_name as userName",
            "status_name as statusName",
            "product_code as productCode",
            "product_name as productName",
            "product_type as productType",
            "product_unit as productUnit",
            "buy_price as buyPrice",
            "instock_count as instockCount",
            "instock_money as instockMoney",
            "back_count as backCount",
            "back_money as backMoney",
            "stock_count_rel as stockCountRel",
            "stock_money_rel as stockMoneyRel",
            "appoint_money as appointMoney",
            "non_payment_money as nonPaymentMoney",
            "factory_send_date as factorySendDate",
            "payment_date as paymentDate",
            "receive_invoice_money as receiveInvoiceMoney"
            };

    /**
     * 检索入库明细数据
     * 
     * @return
     */
    public Resolution getInstockDetail() {
        log.debug("检索入库明细数据");

        /* 登录user类 */
        UserEntity user = (UserEntity) getACL().getUser();
        /* 封装查询参数 */
        BaseQueryData<InstockDetailDto> queryParam = new BaseQueryData<InstockDetailDto>();
        queryParam.setResultPage(this.getPage());
        queryParam.setRows(this.getRows());
        queryParam.setSord(this.getSord());
        queryParam.setSidx(this.getSidx());
        queryParam.setStar((this.getPage() - 1) * this.getRows());
        queryParam.setFieldsParam(this.getFields(), fieldRows);// 显示的列
        queryParam.setQuery(param);// 查询条件

        commonService = getBean("commonService", CommonService.class);
        boolean checkResult = commonService.getUserFileParam(user, queryParam);
        if (!checkResult) {
            return new JSONResolution("");
        } 
        service = getBean("instockDetailService",InstockDetailService.class);
        service.getInstockDetail(queryParam);
        
        /* 判断错误 */
        if (this.hasError()) {
            log.error("检索发生错误!");
        }

        /* 封装返回结果 */
        BaseResultData result = new BaseResultData(queryParam.getPage(), queryParam
                .getRecords(), queryParam.getTotal());

        @SuppressWarnings("unchecked")
        List<InstockDetailEntity> dateList = (List<InstockDetailEntity>) queryParam
                .getDataList();
        for (InstockDetailEntity instockDetailEntity : dateList) {
            if("0.00".equals(instockDetailEntity.getBackMoney())){
                instockDetailEntity.setBackMoney("");
            }
            /* 页面数据,需与页面定义顺序一致 */
            result.addCells(instockDetailEntity.getBuyContractId(),
                            instockDetailEntity.getProductContractCode(),
                            instockDetailEntity.getCompanyContractCode(),
                            instockDetailEntity.getProductTypeName(),
                            instockDetailEntity.getInstockId(),
                            instockDetailEntity.getStockroomName(),
                            instockDetailEntity.getSupplierName(),
                            instockDetailEntity.getInstockTotalmoney(),
                            instockDetailEntity.getDate(),
                            instockDetailEntity.getStockDate(),
                            instockDetailEntity.getUserName(),
                            instockDetailEntity.getStatusName(),
                            instockDetailEntity.getProductCode(),
                            instockDetailEntity.getProductName(),
                            instockDetailEntity.getProductType(),
                            instockDetailEntity.getProductUnit(),
                            instockDetailEntity.getBuyPrice(),
                            instockDetailEntity.getInstockCount(),
                            instockDetailEntity.getInstockMoney(),
                            instockDetailEntity.getBackCount(),
                            instockDetailEntity.getBackMoney(),
                            instockDetailEntity.getStockCountRel(),
                            instockDetailEntity.getStockMoneyRel(),
                            instockDetailEntity.getAppointMoney(),
                            instockDetailEntity.getNonPaymentMoney(),
                            instockDetailEntity.getFactorySendDate(),
                            instockDetailEntity.getPaymentDate(),
                            instockDetailEntity.getReceiveInvoiceMoney()
                           );
        }
        log.info(new JSONResolution(result).toJson());
        return new JSONResolution(result);
    }

    private Object getRows() {
        return null;
    }

    /**
     * 检索入库明细数据合计值
     * 
     * @return
     */
    public Resolution getInstockDetailSum() {
        log.debug("检索入库明细数据合计值");
        
        BaseQueryData<InstockDetailDto> queryParam = new BaseQueryData<InstockDetailDto>();
        
        queryParam.setQuery(param);// 查询条件
        
        service = getBean("instockDetailService",InstockDetailService.class);        

        UserEntity user = (UserEntity) getACL().getUser();
        
        commonService = getBean("commonService", CommonService.class);
        
        boolean checkResult = commonService.getUserFileParam(user, queryParam);
        if (!checkResult) {
            return new JSONResolution("");
        } 
        InstockDetailDto result = service.getInstockDetailSumVal(queryParam);
        /* 判断错误 */
        if (this.hasError()) {
            log.error("检索发生错误!");
        }
        return new JSONResolution(result);
    }
    

}
