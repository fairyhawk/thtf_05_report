/**
 * ClassName  InstockDetailHelper
 *
 * History
 * Create User: hanrb
 * Create Date: 2010-11-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.convert;


import org.springframework.dao.DataAccessException;

import cn.com.thtf.cms.report.entity.InstockDetailEntity;


/**
 * 
 * @author hanrb
 */

public class RInstockDetailRole13ConvertHelper implements ConvertHelper {

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.egov.cms.batch.convert.ConvertHelper#getTilte()
     */
    @Override
    public String[] getTilte() {
        return new String[] { "合同流水号", "产品合同号", "公司合同号", "产品分类", "入库单号", "库房名称", "供货商名称",
                "申请日期", "入库日期", "人员名称", "入库单状态", "产品编码", "产品名称", "规格型号", "单位",
                 "入库数", "返厂数",  "实际入库数量", "厂家发货日期", "要求付款日期","特殊说明" };
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.batch.convert.ConvertHelper#getRow(java.lang.Object)
     */
    @SuppressWarnings("serial")
    @Override
    public String[] getRow(Object obj) {
        if (!(obj instanceof InstockDetailEntity)) {
            throw new DataAccessException("row data must be RSellInvoiceEntity type") {
            };
        }
        InstockDetailEntity instork = (InstockDetailEntity) obj;
        String[] row = new String[29];
        row[0] = instork.getBuyContractId();
        row[1] = instork.getProductContractCode();
        row[2] = instork.getCompanyContractCode();
        row[3] = instork.getProductTypeName();
        row[4] = instork.getInstockId();
        row[5] = instork.getStockroomName();
        row[6] = instork.getSupplierName();
        //row[7] = instork.getInstockTotalmoney();
        row[7] = instork.getDate();
        row[8] = instork.getStockDate();
        row[9] = instork.getUserName();
        row[10] = instork.getStatusName();
        row[11] = instork.getProductCode();
        row[12] = instork.getProductName();
        row[13] = instork.getProductType();
        row[14] = instork.getProductUnit();
        //row[16] = instork.getBuyPrice();
        row[15] = instork.getInstockCount();
        //row[18] = instork.getInstockMoney();
        row[16] = instork.getBackCount();
        //row[20] = instork.getBackMoney();
        row[17] = instork.getStockCountRel();
        //row[22] = instork.getStockMoneyRel();
        //row[23] = instork.getAppointMoney();
        //row[24] = instork.getNonPaymentMoney();
        row[18] = instork.getFactorySendDate();
        row[19] = instork.getPaymentDate();
        //row[27] = instork.getReceiveInvoiceMoney();
        row[20] = instork.getText();
        return row;
    }

}
