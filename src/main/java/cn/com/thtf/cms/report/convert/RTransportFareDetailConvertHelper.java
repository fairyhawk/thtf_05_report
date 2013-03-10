/**
 * ClassName  RSellBackConvertHelper
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-10-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.convert;

import org.springframework.dao.DataAccessException;

import cn.com.thtf.cms.report.dto.RTransportFareDetailDto;



public class RTransportFareDetailConvertHelper implements ConvertHelper {

    /**
     * @see cn.com.thtf.egov.cms.batch.convert.ConvertHelper#getTilte()
     */
    @Override
    public String[] getTilte() {
        return new String[] { "物流公司名称", "发货日期", "装箱单号", "运单号", "库房名称", "产品分类名称", "客户名称",
                "货物接收单位名称", "发货地址", "要求货运方式", "实际货运方式", "装箱件数", "到货日期", "签收日期", "回执确认日期",
                "人员名称", "发货费用", "装箱单状态","是否确认","确认时间","新建日期"};
    }

    /**
     * @see cn.com.thtf.egov.cms.batch.convert.ConvertHelper#getRow(Object)
     */
    @SuppressWarnings("serial")
    @Override
    public String[] getRow(Object obj) {
        if (!(obj instanceof RTransportFareDetailDto)) {
            throw new DataAccessException("row data must be RTransportFareDetailDto type") {
            };
        }
        RTransportFareDetailDto transportFareDetailEntity = (RTransportFareDetailDto) obj;
        String[] row = new String[21];
        row[0] = transportFareDetailEntity.getLogisticsName();
        row[1] = transportFareDetailEntity.getSendDate();
        row[2] = transportFareDetailEntity.getBoxId();
        row[3] = transportFareDetailEntity.getBoxNo();
        row[4] = transportFareDetailEntity.getStockroomName();
        row[5] = transportFareDetailEntity.getProductTypeName();
        row[6] = transportFareDetailEntity.getCustomerName();
        row[7] = transportFareDetailEntity.getCompanyName();
        row[8] = transportFareDetailEntity.getAddress();
        row[9] = transportFareDetailEntity.getRequestWay();
        row[10] = transportFareDetailEntity.getRealityWay();
        row[11] = String.valueOf(transportFareDetailEntity.getCount());
        row[12] = transportFareDetailEntity.getArrivalDate();
        row[13] = transportFareDetailEntity.getSignoffDate();
        row[14] = transportFareDetailEntity.getConfirmDate();
        row[15] = transportFareDetailEntity.getUserName();
        if (transportFareDetailEntity.getMoney() != null) {
            row[16] = transportFareDetailEntity.getMoney();
        } else {
            row[16] = "";
        }
        row[17] = transportFareDetailEntity.getStatusName();
        
        row[18] = transportFareDetailEntity.getTbcFlagName();
        
        row[19] = transportFareDetailEntity.getTbcDate();
        
        row[20] = transportFareDetailEntity.getDate();
        return row;
    }
}
