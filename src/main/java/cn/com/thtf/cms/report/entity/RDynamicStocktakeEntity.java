package cn.com.thtf.cms.report.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class RDynamicStocktakeEntity extends BaseEntity {
    /** 库房名称 */
    private String stockName;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 产品型号 */
    private String productType;
    /** 单位 */
    private String productUnit;
    /** 库存数量 */
    private Integer stockroomCount;
    /** 产品编号 */
    private Integer productId;
    /** 库房 */
    private Integer stockroomId;
    /** 所在地库存汇总 */
    private Integer stockNumSum;
    /** 所在地 */
    private String shortName;
}
