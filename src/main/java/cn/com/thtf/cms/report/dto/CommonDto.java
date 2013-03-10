/**
 * ClassName  CommonDto
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
 * CommonDto
 * 
 * @author Lubo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonDto extends BaseDto {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    // /** 用户ID */
    // private String userId;
    // /** 区域ID */
    // private Integer userAreaId;
    // /** 产品分类ID */
    // private Integer productTypeId;
    // /** 产品分类名称 */
    // private String productTypeName;
    // /** 库房ID */
    // private Integer stockroomId;
    // /** 库房名称 */
    // private String stockroomName;
}
