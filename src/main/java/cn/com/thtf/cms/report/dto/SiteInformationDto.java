package cn.com.thtf.cms.report.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class SiteInformationDto extends BaseDto{
    /*编号*/
    private Integer id;
    /*标题*/
    private String subject;
    /*内容*/
    private String text;
    /*是否滚动*/
    private Integer rollFlag;
    /*发送者编号*/
    private String userId;
    /*新建时间*/
    private String datetime;
    /*修改时间*/
    private String updTime;
    /*附件名称*/
    private String filename;
    /*附件路径*/
    private String filepath;

}
