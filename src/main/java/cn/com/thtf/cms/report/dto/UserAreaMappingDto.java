/**
 * ClassName  UserAreaMapping
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-1-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * UserAreaMappingDto
 * 
 * @author Lubo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAreaMappingDto extends BaseDto {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 用户ID */
    private String userId;
    /** 区域ID */
    private Integer userAreaId;
    /** 区域名称 */
    private String userAreaName;

}
