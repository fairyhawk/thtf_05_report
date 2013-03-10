package cn.com.thtf.cms.report.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class RBuyContractDetailDto extends BaseDto{
    /** 采购金额 总和*/
    private BigDecimal sumBuyContractMoney;
    /** 入库金额 总和*/
    private BigDecimal sumInStockMoney;
    /** 指定金额 总和*/
    private BigDecimal sumAppointMoney;
    /** 收票金额 总和*/
    private BigDecimal sumInvoiceMoney;
    /** 退货合同金额 总和*/
    private BigDecimal sumBuyBackContractMoney;
    /** 返厂金额 总和*/
    private BigDecimal sumBuyBackGoodsMoney;
    /** 实际入库金额 */
    private BigDecimal sumFactInStockMoney;
}
