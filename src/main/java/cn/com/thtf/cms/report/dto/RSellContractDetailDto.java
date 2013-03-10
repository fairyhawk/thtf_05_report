/**
 * ClassName  RSellContractDetailDto
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 销售合同产品明细检索
 * 
 * @author liuqg
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class RSellContractDetailDto extends BaseDto {
    private static final long serialVersionUID = 1L;
    private String sell_contract_id;// 合同流水号
    private String product_contract_code;// 产品合同号
    private String company_contract_code;// 公司合同号
    private String status;// 合同状态
    private String product_code;// 产品编码
    private String product_name;// 产品名称
    private String product_type;// 规格型号
    private String product_type_name;// 产品分类名称
    private String customer_name;// 客户名称
    private String user_name;
    private String contract_pro_name;

}
