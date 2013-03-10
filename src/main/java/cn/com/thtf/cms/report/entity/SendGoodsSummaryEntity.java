/**
 * ClassName  SendGoodsSummaryEntity
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-11-08
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SendGoodsSummaryEntity
 * 
 * @author Lubo
 */
@Data
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class SendGoodsSummaryEntity extends BaseEntity {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 合同流水号 */
    private String sellContractId;
    /** 产品合同号 */
    private String productContractCode;
    /** 公司合同号 */
    private String companyContractCode;
    /** 发货单号 */
    private String sendGoodsId;
    /** 发货单状态 */
    private Integer sendGoodsStatus;

    /** 发货单状态名称 */
    private String sendGoodsStatusName;
    /** 产品分类名称 */
    private String productTypeName;
    /** 客户名称 */
    private String customerName;
    /** 客户区域 */
    private String customerArea;
    /** 客户省/直辖市 */
    private String customerProvince;

    /** 客户市/区 */
    private String customerCity;
    /** 申请日期 */
    private String date;
    /** 实际发货日期 */
    private String sendDate;
    /** 要求到账日期 r */
    private String equestReachDate;
    /** 库房名称 */
    private String stockroomName;

    /** 实际发货金额 */
    private BigDecimal sendGoodsMoneyRel;
    /** 发货金额 */
    private BigDecimal sendGoodsMoney;
    /** 退货金额 */
    private BigDecimal backCountMoney;
    /** 发货成本(不含税) */
    private BigDecimal detailMoneyCost;
    /** 发货利润(不含税) */
    private BigDecimal detailMoneyProfit;

    /** 发货毛利率 */
    private BigDecimal detailMoneyProfitrate;
    /** 指定金额 */
    private BigDecimal appointMoney;
    /** 在途指定金额 */
    private BigDecimal appointMoneyOtw;
    /** 欠款金额 */
    private BigDecimal oweMoney;
    /** 超期欠款 */
    private BigDecimal exceedOweMoney;

    /** 正常欠款 */
    private BigDecimal normalOweMoney;
    /** 不包含在途的欠款 */
    private BigDecimal excludeOtwOweMoney;
    /** 人员名称  */
    private String userName;
    /** 开票金额 */
    private BigDecimal makeInvoiceMoney;
    /** 超期回款金额 */
    private BigDecimal exceedReturnMoney;

    /** 未超期回款金额 */
    private BigDecimal normalReturnMoney;
    /** 信用类型 */
    private Integer creditTypeId;
    /** 信用类型名称 */
    private String creditTypeName;
    /** 项目名称 */
    private String projectName;
    /** 账期 */
    private Integer arterm;

    /** 信用额度 */
    private BigDecimal amount;
    /** 超期天数 */
    private Integer exceedDays;
    /** 宽限超期天数 */
    private Integer extendExceedDays;

}
