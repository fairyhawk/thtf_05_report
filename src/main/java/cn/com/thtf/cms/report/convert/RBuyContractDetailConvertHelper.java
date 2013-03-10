package cn.com.thtf.cms.report.convert;

import java.math.RoundingMode;

import org.springframework.dao.DataAccessException;

import cn.com.thtf.cms.report.entity.RBuyContractDetailEntity;

/**
 * 采购合同产品明细转化辅助
 * 
 * @author HanHaiyun
 * 
 */
public class RBuyContractDetailConvertHelper implements ConvertHelper {

    @Override
    public String[] getTilte() {
        return new String[] { "合同流水号", "产品合同号", "公司合同号", "供货商名称", "产品分类名称", "产品编码",
                "产品名称", "规格型号", "单位", "采购数", "采购单价", "采购金额", "入库金额", "入库数量", "指定金额",
                "收票金额", "退货合同金额", "退货合同数量", "返厂金额", "返厂数量", "实际入库数量", "实际入库金额", "未入库数量",
                "特殊说明", "实际合同数", "要求供货日期" };
    }

    @SuppressWarnings("serial")
    @Override
    public String[] getRow(Object obj) {
        if (!(obj instanceof RBuyContractDetailEntity)) {
            throw new DataAccessException(
                    "row data must be RBuyContractDetailEntity type") {
            };
        }
        String[] str = new String[26];
        RBuyContractDetailEntity entity = (RBuyContractDetailEntity) obj;
        str[0] = entity.getBuyContractId();
        str[1] = entity.getProductContractCode();
        str[2] = entity.getCompanyContractCode();
        str[3] = entity.getSupplierName();
        str[4] = entity.getProductTypeName();
        str[5] = entity.getProductCode();
        str[6] = entity.getProductName();
        str[7] = entity.getProductType();
        str[8] = entity.getProductUnit();
        str[9] = entity.getBuyContractDetailCount() == null ? "0" : entity
                .getBuyContractDetailCount().toString();
        str[10] = entity.getBuyContractPrice() == null ? "" : entity
                .getBuyContractPrice().setScale(5, RoundingMode.HALF_UP).toString();
        str[11] = entity.getBuyContractMoney() == null ? "" : entity
                .getBuyContractMoney().setScale(5, RoundingMode.HALF_UP).toString();
        str[12] = entity.getInStockMoney() == null ? "" : entity.getInStockMoney()
                .setScale(5, RoundingMode.HALF_UP).toString();
        str[13] = entity.getInStockCount() == null ? "0" : entity.getInStockCount()
                .toString();
        str[14] = entity.getAppointMoney() == null ? "" : entity.getAppointMoney()
                .setScale(5, RoundingMode.HALF_UP).toString();
        str[15] = entity.getInvoiceMoney() == null ? "" : entity.getInvoiceMoney()
                .setScale(5, RoundingMode.HALF_UP).toString();
        str[16] = entity.getBuyBackContractMoney() == null ? "" : entity
                .getBuyBackContractMoney().setScale(5, RoundingMode.HALF_UP).toString();
        str[17] = entity.getBuyBackContractCount() == null ? "0" : entity
                .getBuyBackContractCount().toString();
        str[18] = entity.getBuyBackGoodsMoney() == null ? "" : entity
                .getBuyBackGoodsMoney().setScale(5, RoundingMode.HALF_UP).toString();
        str[19] = entity.getBuyBackGoodsCount() == null ? "0" : entity
                .getBuyBackGoodsCount().toString();
        str[20] = entity.getFactInStockCount() == null ? "0" : entity
                .getFactInStockCount().toString();
        str[21] = entity.getFactInStockMoney() == null ? "" : entity
                .getFactInStockMoney().setScale(5, RoundingMode.HALF_UP).toString();
        str[22] = entity.getNotInStockCount() == null ? "0" : entity.getNotInStockCount()
                .toString();
        str[23] = entity.getText();
        str[24] = entity.getFactContractCount() == null ? "0" : entity
                .getFactContractCount().toString();
        str[25] = entity.getRequestDate();
        return str;
    }

}
