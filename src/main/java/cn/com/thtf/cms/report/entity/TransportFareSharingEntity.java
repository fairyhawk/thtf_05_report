package cn.com.thtf.cms.report.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 运费分摊表 Entity r_transport_fare_Sharing
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class TransportFareSharingEntity extends BaseEntity {
    
    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 装箱单号 */
    private String boxId;
    /** 发货单号 */
    private String sendGoodsId;
    /** 产品类型名称 */
    private String name;
    /** 物流公司名称 */
    private String logisticsName;
    /** 客户名称 */
    private String customerName;
    /** 发货费用 */
    private String money;
    /** 发货日期 */
    private String sendDate;
    //开始日期
    private String sendBeginDate;
    //终止日期
    private String sendEndDate;
    /** 单据类型 */
    private String sendGoodsType;
    /** 发货均摊费用 */
    private String sendAgvMoney;
    /** 人员部门名称 */
    private String userDeptName;
    /** 人员姓名 */
    private String userName;
    /** 确认标识 */
    private String tbcFlag;
    /** 确认标识名 */
    private String tbcFlagName;
    /** 确认标识名 */
    private String tbcDate;
   
}
