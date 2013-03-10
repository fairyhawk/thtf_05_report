package cn.com.thtf.cms.report.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class RBuyContractDetailEntity extends BaseEntity {
    /** 合同流水号 */
    private String buyContractId;
    /** 产品合同号 */
    private String productContractCode;
    /** 公司合同号 */
    private String companyContractCode;
    /** 供货商名称 */
    private String supplierName;
    /** 产品分类名称 */
    private String productTypeName;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 规格型号 */
    private String productType;
    /** 单位 */
    private String productUnit;
    /** 采购数 */
    private Integer buyContractDetailCount;
    /** 采购单价 */
    private BigDecimal buyContractPrice;
    /** 采购金额 */
    private BigDecimal buyContractMoney;
    /** 入库金额 */
    private BigDecimal inStockMoney;
    /** 入库数量 */
    private Integer inStockCount;
    /** 指定金额 */
    private BigDecimal appointMoney;
    /** 收票金额 */
    private BigDecimal invoiceMoney;
    /** 退货合同金额 */
    private BigDecimal buyBackContractMoney;
    /** 退货合同数量 */
    private Integer buyBackContractCount;
    /** 返厂金额 */
    private BigDecimal buyBackGoodsMoney;
    /** 返厂数量 */
    private Integer buyBackGoodsCount;
    /** 实际入库数量 */
    private Integer factInStockCount;
    /** 实际入库金额 */
    private BigDecimal factInStockMoney;
    /** 未入库数量 */
    private Integer notInStockCount;
    /** 新建日期*/
    private String date;
    /**特殊说明*/
    private String text;
    /**实际合同数*/
    private Integer factContractCount;
    /**要求供货日期*/
    private String requestDate;
    /**要求发货其实日期*/
    private String minRequestDate;
    /**要求发货终止日期*/
    private String maxRequestDate;
}
