package cn.com.thtf.cms.report.convert;

import java.math.RoundingMode;

import org.springframework.dao.DataAccessException;

import cn.com.thtf.cms.report.dto.RSendGoodsDetailDto;

public class RSendGoodsDetailConvertHelper implements ConvertHelper {

    @Override
    public String[] getTilte() {
        // TODO Auto-generated method stub
        return new String[] { "合同流水号", "产品合同号", "公司合同号", "发货单号", "发货单状态", "发货单类型",
                "产品分类名称", "客户区域", "省", "市", "客户名称", "要求发货日期", "实际发货日期", "要求到账日期", "库房名称",
                "产品编码", "产品名称", "规格型号", "单位", "单价（含税）", "发货/备货数量", "实际发货数量", "实际发货金额",
                "发货金额", "退货数量", "退货金额", "成本单价（不含税）", "发货成本（不含税）", "含税成本", "发货利润（不含税）",
                "含税利润", "发货毛利率", "指定金额", "在途指定金额", "发货未回款金额", "开票金额", "项目名称", "人员区域",
                "人员名称", "特殊说明", "货物接收单位名称", "申请日期" };
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
        row[19] = sendGoods.getProductMoneyTax() == null ? "" : sendGoods
                .getProductMoneyTax().setScale(2, RoundingMode.HALF_UP).toString();
        row[20] = sendGoods.getDetailCount() == null ? "" : sendGoods.getDetailCount()
                .toString();
        row[21] = sendGoods.getDetailCountRel() == null ? "" : sendGoods
                .getDetailCountRel().toString();
        row[22] = sendGoods.getDetailMoneyRel() == null ? "" : sendGoods
                .getDetailMoneyRel().setScale(2, RoundingMode.HALF_UP).toString();
        row[23] = sendGoods.getDetailMoney() == null ? "" : sendGoods.getDetailMoney()
                .setScale(2, RoundingMode.HALF_UP).toString();
        row[24] = sendGoods.getBackCount() == null ? "" : sendGoods.getBackCount()
                .toString();
        row[25] = sendGoods.getBackMoney() == null ? "" : sendGoods.getBackMoney()
                .setScale(2, RoundingMode.HALF_UP).toString();
        row[26] = sendGoods.getDetailPrice() == null ? "" : sendGoods.getDetailPrice()
                .setScale(2, RoundingMode.HALF_UP).toString();
        row[27] = sendGoods.getProductMoneyCost() == null ? "" : sendGoods
                .getProductMoneyCost().setScale(2, RoundingMode.HALF_UP).toString();
        row[28] = sendGoods.getProductMoneyCostTax() == null ? "" : sendGoods
                .getProductMoneyCostTax().setScale(2, RoundingMode.HALF_UP).toString();
        row[29] = sendGoods.getProductMoneyProfit() == null ? "" : sendGoods
                .getProductMoneyProfit().setScale(2, RoundingMode.HALF_UP).toString();
        row[30] = sendGoods.getProductMoneyProfitTax() == null ? "" : sendGoods
                .getProductMoneyProfitTax().setScale(2, RoundingMode.HALF_UP).toString();
        row[31] = sendGoods.getProductMoneyProfitrate() == null ? "" : sendGoods
                .getProductMoneyProfitrate().setScale(2, RoundingMode.HALF_UP).toString();
        row[32] = sendGoods.getAppointMoney() == null ? "" : sendGoods.getAppointMoney()
                .setScale(2, RoundingMode.HALF_UP).toString();
        row[33] = sendGoods.getAppointMoneyOtw() == null ? "" : sendGoods
                .getAppointMoneyOtw().setScale(2, RoundingMode.HALF_UP).toString();
        row[34] = sendGoods.getAppointMoneyExt() == null ? "" : sendGoods
                .getAppointMoneyExt().setScale(2, RoundingMode.HALF_UP).toString();
        row[35] = sendGoods.getMakeInvoiceMoney() == null ? "" : sendGoods
                .getMakeInvoiceMoney().setScale(2, RoundingMode.HALF_UP).toString();
        row[36] = sendGoods.getContractProName();
        row[37] = sendGoods.getUserAreaName();
        row[38] = sendGoods.getUserName();
        row[39] = sendGoods.getText();
        row[40] = sendGoods.getCaName();
        row[41] = sendGoods.getDatetime();
        return row;
    }
}
