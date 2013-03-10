/**
 * ClassName  RSellContractDetailEntity
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 销售合同产品明细
 * 
 * @author liuqg
 */

@Data
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class RSellContractDetailEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String sell_contract_id;// 合同流水号
    private String product_contract_code;// 产品合同号
    private String company_contract_code;// 公司合同号
    private Integer status;// 合同状态
    private String status_name;// 合同状态名称
    private BigDecimal money;// 合同总额
    private String product_code;// 产品编码
    private String product_name;// 产品名称
    private String product_type;// 规格型号
    private String product_unit;// 单位
    private Integer count;// 销售数
    private BigDecimal price;// 销售单价
    private BigDecimal sell_money;// 销售金额
    private BigDecimal product_limit_price;// 限价
    private BigDecimal send_goods_money;// 发货金额
    private BigDecimal fact_send_goods_money;// 实际发货金额
    private Integer send_goods_count;// 发货数量
    private Integer fact_send_goods_count;// 实际发货数量
    private BigDecimal prepare_goods;// 备货金额
    private Integer stock_goods_count;// 备货数量
    private BigDecimal appoint_money;// 指定金额
    private BigDecimal in_transit_appoint_money;// 在途指定金额
    private BigDecimal sell_invoice_money;// 开票金额
    private BigDecimal back_contract_money;// 退货合同金额
    private Integer back_contract_count;// 退货合同数量
    private BigDecimal back_money;// 退货金额
    private Integer back_count;// 退货数量
    private Integer fact_sell_count;// 实际销售数量
    private Integer contract_not_implemented_count;// 合同未执行数量
    private Integer product_type_id;// 产品分类编码
    private String product_type_name;// 产品分类名称
    private String customer_name;// 客户名称
    private String user_area_name;// 人员区域
    private String user_name;// 人员名称
    private String contract_pro_name;// 项目名称

}
