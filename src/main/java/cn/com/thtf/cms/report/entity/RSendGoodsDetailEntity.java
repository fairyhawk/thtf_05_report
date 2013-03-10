package cn.com.thtf.cms.report.entity;


import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class RSendGoodsDetailEntity extends BaseEntity {
    // 合同流水号
    private String sellContractId;
    // 产品合同号
    private String productContractCode;
    // 公司合同号
    private String companyContractCode;
    // 发货单号
    private String sendGoodsId;
    // 发货单状态
    private Integer sendGoodsStatus;
    // 发货单状态名称
    private String sendGoodsStatusName;
    // 发货单类型
    private Integer sendGoodsType;
    // 发货单类型名称
    private String sendGoodsTypeName;
    // 产品分类名称
    private String productTypeName;
    // 客户名称
    private String customerName;
    // 要求发货日期
    private String requestDate;
    // 实际发货日期
    private String sendDate;
    // 要求到账日期
    private String requestReachDate;
    // 库房名称
    private String stockroomName;
    // 产品编码
    private String productCode;
    // 产品名称
    private String productName;
    // 规格型号
    private String productType;
    // 单位
    private String productUnit;
    // 单价（含税）
    private BigDecimal productMoneyTax;
    // 发货(或备货数量)
    private Integer detailCount;
    // 实际发货数量
    private Integer detailCountRel;
    // 实际发货金额
    private BigDecimal detailMoneyRel;
    // 发货金额
    private BigDecimal detailMoney;
    // 退货数量
    private Integer backCount;
    // 退货金额
    private BigDecimal backMoney;
    // 成本单价（不含税）
    private BigDecimal detailPrice;
    // 发货成本（不含税）
    private BigDecimal productMoneyCost;
    // 发货利润（不含税）
    private BigDecimal productMoneyProfit;
    // 发货毛利率
    private BigDecimal productMoneyProfitrate;
    // 指定金额
    private BigDecimal appointMoney;
    // 在途指定金额
    private BigDecimal appointMoneyOtw;
    // 发货未回款金额
    private BigDecimal appointMoneyExt;
    // 人员名称
    private String userName;
    // 开票金额
    private BigDecimal makeInvoiceMoney;
    // 发货单新建日期
    private String datetime;
    // 特殊说明
    private String text;
    // 人员区域
    private String userAreaName;
    //含税成本
    private BigDecimal productMoneyCostTax;
    //含税利润
    private BigDecimal productMoneyProfitTax;
    //客户省
    private String customerProvince;
    //市
    private String customerCity;
    //区域
    private String customerArea;
    //项目名称
    private String contractProName;
    //货物接收单位名称
    private String caName;
    
}
