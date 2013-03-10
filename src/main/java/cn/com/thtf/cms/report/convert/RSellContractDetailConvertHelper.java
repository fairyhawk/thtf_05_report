/**
 * ClassName  RSellContractDetailHelper
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-10-26
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.convert;

import java.math.RoundingMode;

import org.springframework.dao.DataAccessException;

import cn.com.thtf.cms.report.entity.RSellContractDetailEntity;

/**
 * 
 * @author liuqg
 */

public class RSellContractDetailConvertHelper implements ConvertHelper {

    /**
     * @see cn.com.thtf.egov.cms.batch.convert.ConvertHelper#getTilte()
     */
    @Override
    public String[] getTilte() {
        return new String[] { "合同流水号", "产品合同号", "公司合同号", "合同状态", "人员区域", "人员名称", "客户名称",
                "项目名称", "合同总额", "产品编码", "产品名称", "规格型号", "单位", "销售数", "销售单价", "销售金额",
                "限价", "发货金额", "实际发货金额", "发货数量", "实际发货数量", "备货金额", "备货数量", "指定金额",
                "在途指定金额", "开票金额", "退货合同金额", "退货合同数量", "退货金额", "退货数量", "实际销售数量", "合同未执行数量" };
    }

    /**
     * @see cn.com.thtf.egov.cms.batch.convert.ConvertHelper#getRow(Object)
     */
    @SuppressWarnings("serial")
    @Override
    public String[] getRow(Object obj) {
        if (!(obj instanceof RSellContractDetailEntity)) {
            throw new DataAccessException(
                    "row data must be RSellcontractDetailEntity type") {
            };
        }
        RSellContractDetailEntity sellcontractDetail = (RSellContractDetailEntity) obj;
        String[] row = new String[32];
        int i = 0;
        row[i++] = sellcontractDetail.getSell_contract_id();// 合同流水号
        row[i++] = sellcontractDetail.getProduct_contract_code();// 产品合同号
        row[i++] = sellcontractDetail.getCompany_contract_code();// 公司合同号
        row[i++] = sellcontractDetail.getStatus_name();// 合同状态名称
        row[i++] = sellcontractDetail.getUser_area_name();// 人员区域
        row[i++] = sellcontractDetail.getUser_name();// 人员名称
        row[i++] = sellcontractDetail.getCustomer_name();// 客户名称
        row[i++] = sellcontractDetail.getContract_pro_name();// 项目名称
        if (sellcontractDetail.getMoney() != null) {
            row[i++] = sellcontractDetail.getMoney().setScale(2, RoundingMode.HALF_UP)
                    .toString();// 合同总额
        } else {
            row[i++] = "";
        }
        row[i++] = sellcontractDetail.getProduct_code();// 产品编码
        row[i++] = sellcontractDetail.getProduct_name();// 产品名称
        row[i++] = sellcontractDetail.getProduct_type();// 规格型号
        row[i++] = sellcontractDetail.getProduct_unit();// 单位

        if (sellcontractDetail.getCount() != null) {
            row[i++] = sellcontractDetail.getCount().toString();// 销售数
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getPrice() != null) {
            row[i++] = sellcontractDetail.getPrice().setScale(2, RoundingMode.HALF_UP)
                    .toString();// 销售单价
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getSell_money() != null) {
            row[i++] = sellcontractDetail.getSell_money()
                    .setScale(2, RoundingMode.HALF_UP).toString();// 销售金额
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getProduct_limit_price() != null) {
            row[i++] = sellcontractDetail.getProduct_limit_price()
                    .setScale(2, RoundingMode.HALF_UP).toString();// 限价
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getSend_goods_money() != null) {
            row[i++] = sellcontractDetail.getSend_goods_money()
                    .setScale(2, RoundingMode.HALF_UP).toString();// 发货金额
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getFact_send_goods_money() != null) {
            row[i++] = sellcontractDetail.getFact_send_goods_money()
                    .setScale(2, RoundingMode.HALF_UP).toString();// 实际发货金额
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getSend_goods_count() != null) {
            row[i++] = sellcontractDetail.getSend_goods_count().toString();// 发货数量
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getFact_send_goods_count() != null) {
            row[i++] = sellcontractDetail.getFact_send_goods_count().toString();// 实际发货数量
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getPrepare_goods() != null) {
            row[i++] = sellcontractDetail.getPrepare_goods().toString();// 备货金额
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getStock_goods_count() != null) {
            row[i++] = sellcontractDetail.getStock_goods_count().toString();// 备货数量
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getAppoint_money() != null) {
            row[i++] = sellcontractDetail.getAppoint_money()
                    .setScale(2, RoundingMode.HALF_UP).toString();// 指定金额
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getIn_transit_appoint_money() != null) {
            row[i++] = sellcontractDetail.getIn_transit_appoint_money()
                    .setScale(2, RoundingMode.HALF_UP).toString();// 在途指定金额

        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getSell_invoice_money() != null) {
            row[i++] = sellcontractDetail.getSell_invoice_money()
                    .setScale(2, RoundingMode.HALF_UP).toString();// 开票金额
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getBack_contract_money() != null) {
            row[i++] = sellcontractDetail.getBack_contract_money()
                    .setScale(2, RoundingMode.HALF_UP).toString();// 退货合同金额
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getBack_contract_count() != null) {
            row[i++] = sellcontractDetail.getBack_contract_count().toString();// 退货合同数量
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getBack_money() != null) {
            row[i++] = sellcontractDetail.getBack_money()
                    .setScale(2, RoundingMode.HALF_UP).toString();// 退货金额
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getBack_count() != null) {
            row[i++] = sellcontractDetail.getBack_count().toString();// 退货数量
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getFact_sell_count() != null) {
            row[i++] = sellcontractDetail.getFact_sell_count().toString();// 实际销售数量
        } else {
            row[i++] = "";
        }
        if (sellcontractDetail.getContract_not_implemented_count() != null) {
            row[i++] = sellcontractDetail.getContract_not_implemented_count().toString();// 合同未执行数量
        } else {
            row[i++] = "";
        }

        return row;
    }

}
