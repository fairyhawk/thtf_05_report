package cn.com.thtf.cms.report.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 运费明细
 * 
 * @author zhangzx
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TransportFareDetailEntity {

    private String logisticsName;

    private String sendDate;

    private String boxId;

    private String boxNo;

    private String stockroomName;

    private String productTypeName;

    private String customerName;

    private String companyName;

    private String address;
    
    private Integer requestWayNum;

    private String requestWay;
    
    private Integer realityWayNum;

    private String realityWay;

    private Integer count;

    private String arrivalDate;

    private String signoffDate;

    private String confirmDate;

    private String userName;

    private String money;

    private Integer status;

    private String statusName;
    
    private String date;
    
    private Integer tbcFlag;
    
    private String tbcFlagName;
    
    private String tbcDate;

}
