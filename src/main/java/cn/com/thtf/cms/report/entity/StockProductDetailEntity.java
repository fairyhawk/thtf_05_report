/**
 * ClassName  StockProductDetailEntity
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * StockProductDetailEntity
 * 
 * @author Lubo
 */
@Data
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class StockProductDetailEntity extends BaseEntity {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 库房名称 */
    private String stockroomName;
    /** 产品分类名称 */
    private String productTypeName;
    /** 产品系列名称 */
    private String productSerieName;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;

    /** 规格型号 */
    private String productType;
    /** 单位 */
    private String productUnit;
    /** 库存总数 */
    private int stockroomCount;
    /** 库存单价 */
    private BigDecimal stockroomPrice;
    /** 库存金额 */
    private BigDecimal stockroomMoney;

}
