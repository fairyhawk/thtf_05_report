/**
 * ClassName  RSendGoodsDetailRole13ConvertHelper
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-24
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.convert;

import org.springframework.dao.DataAccessException;

import cn.com.thtf.cms.report.dto.RSendGoodsDetailDto;

/**
 * 
 * @author hanrb
 */

public class RSendGoodsDetailRole13ConvertHelper implements ConvertHelper {

    @Override
    public String[] getTilte() {
        // TODO Auto-generated method stub
        return new String[] { "合同流水号", "产品合同号", "公司合同号", "发货单号", "发货单状态", "发货单类型",
                "产品分类名称", "客户区域", "省", "市", "客户名称", "要求发货日期", "实际发货日期", "要求到账日期",
                "库房名称","产品编码", "产品名称", "规格型号", "单位", "发货/备货数量", "实际发货数量",   
                "项目名称", "人员区域","人员名称", "特殊说明","货物接收单位名称","申请日期" };
    }

    @SuppressWarnings("serial")
    @Override
    public String[] getRow(Object obj) {
        if (!(obj instanceof RSendGoodsDetailDto)) {
            throw new DataAccessException("row data must be RSendGoodsDetailDto type") {
            };
        }
        RSendGoodsDetailDto sendGoods = (RSendGoodsDetailDto) obj;
        String[] row = new String[42];
        row[0] = sendGoods.getSellContractId();
        row[1] = sendGoods.getProductContractCode();
        row[2] = sendGoods.getCompanyContractCode();
        row[3] = sendGoods.getSendGoodsId();
        row[4] = sendGoods.getSendGoodsStatusName();
        row[5] = sendGoods.getSendGoodsTypeName();
        row[6] = sendGoods.getProductTypeName();
        row[7] = sendGoods.getCustomerArea();
        row[8] = sendGoods.getCustomerProvince();
        row[9] = sendGoods.getCustomerCity();
        row[10] = sendGoods.getCustomerName();
        row[11] = sendGoods.getRequestDate();
        row[12] = sendGoods.getSendDate();
        row[13] = sendGoods.getRequestReachDate();
        row[14] = sendGoods.getStockroomName();
        row[15] = sendGoods.getProductCode();
        row[16] = sendGoods.getProductName();
        row[17] = sendGoods.getProductType();
        row[18] = sendGoods.getProductUnit();
        row[19] = sendGoods.getDetailCount() == null ? "" : sendGoods.getDetailCount()
                .toString();
        row[20] = sendGoods.getBackCount()==null?"":sendGoods.getBackCount().toString();
        row[21] = sendGoods.getContractProName();
        row[22] = sendGoods.getUserAreaName();
        row[23] = sendGoods.getUserName();
        row[24] = sendGoods.getText();
        row[25] = sendGoods.getCaName();
        row[26] = sendGoods.getDatetime();
        return row;
    }
}
