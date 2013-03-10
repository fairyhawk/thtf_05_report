package cn.com.thtf.cms.report.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author zhangzx
 */
@Data
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class InstockDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /** 合同流水号  */
    private String buyContractId;
    /** 产品合同号  */
    private String productContractCode;
    /** 公司合同号  */
    private String companyContractCode;
    /** 产品分类ID */
    private int productTypeId;
    /** 产品分类 */
    private String productTypeName;
    /** 入库单号  */
    private String instockId;
    /** 库房名称 */
    private String stockroomName;
    /** 供货商名称 */
    private String supplierName;
    /** 入库总金额 */
    private String instockTotalmoney;
    /** 申请日期 */
    private String date;
    /** 入库日期 */
    private String stockDate;
    /** 人员名称 */
    private String userName;
    /** 入库单状态 */
    private String status;
    /** 入库单状态名称 */
    private String statusName;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 规格型号 */
    private String productType;
    /** 单位 */
    private String productUnit;
    /** 采购单价 */
    private String buyPrice;
    /** 入库数 */
    private String instockCount;
    /** 入库金额 */
    private String instockMoney;
    /** 返厂数 */
    private String backCount;
    /** 返厂金额 */
    private String backMoney;
    /** 实际入库数量 */
    private String stockCountRel;
    /** 实际入库金额 */
    private String stockMoneyRel;
    /** 指定金额 */
    private String appointMoney;
    /** 未付款金额 */
    private String nonPaymentMoney;
    /** 厂家发货日期 */
    private String factorySendDate;
    /** 要求付款日期 */
    private String paymentDate;
    /** 收票金额 */
    private String receiveInvoiceMoney;
    /** 特殊说明  */
    private String text;
    /** 申请起始日期 */
    private String dateBegin;
    /** 申请终止日期 */
    private String dateEnd;
    /** 入库起始日期 */
    private String stockDateBegin;
    /** 入库终止日期 */
    private String stockDateEnd;
    /** 厂家发货起始日期 */
    private String factorySendDateBegin;
    /** 厂家发货终止日期 */
    private String factorySendDateEnd;
    /** 要求付款起始日期 */
    private String paymentDateBegin;
    /** 要求付款终止日期 */
    private String paymentDateEnd;
}
