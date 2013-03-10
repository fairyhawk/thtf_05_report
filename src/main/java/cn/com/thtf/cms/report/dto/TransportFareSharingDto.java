package cn.com.thtf.cms.report.dto;


import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 运费分摊表 Entity r_transport_fare_Sharing
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TransportFareSharingDto extends BaseDto {
    
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
    /** 确认時間 */
    private String tbcDate;
    //开始日期
    private String sendBeginDate;
    //终止日期
    private String sendEndDate;
    /**发货费用合计*/
    private String moneySum;
    /** 均摊金额合计 **/
    private String sendAgvMoneySum;
    /** 用户id */
    private String userId;
    /** 权限id */
    private int roleId;
    /** 销售助理负责区域和产品分类  */
    private List<?> userAreaProductList;
   
}
