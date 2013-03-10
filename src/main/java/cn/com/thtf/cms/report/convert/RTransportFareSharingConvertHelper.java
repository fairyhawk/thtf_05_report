package cn.com.thtf.cms.report.convert;


import org.springframework.dao.DataAccessException;

import cn.com.thtf.cms.report.entity.TransportFareSharingEntity;


public class RTransportFareSharingConvertHelper implements ConvertHelper {

    @Override
    public String[] getTilte() {
        return new String[] { "装箱单号", "发货单号", "产品类型名称", "物流公司名称", "客户名称", "发货费用", "发货日期",
                "单据类型", "发货均摊费用", "人员部门名称", "人员姓名","是否确认","确认时间" };
    }

    @SuppressWarnings("serial")
    @Override
    public String[] getRow(Object obj) {
        if (!(obj instanceof TransportFareSharingEntity)) {
            throw new DataAccessException(
                    "row data must be TransportFareSharingEntity type") {
            };
        }
        TransportFareSharingEntity sharing = (TransportFareSharingEntity) obj;
        String[] row = new String[13];
        row[0] = sharing.getBoxId();
        row[1] = sharing.getSendGoodsId();
        row[2] = sharing.getName();
        row[3] = sharing.getLogisticsName();
        row[4] = sharing.getCustomerName();
        row[5] = sharing.getMoney();
        row[6] = sharing.getSendDate();
        row[7] = sharing.getSendGoodsType();
        row[8] = sharing.getSendAgvMoney();
        row[9] = sharing.getUserDeptName();
        row[10] = sharing.getUserName();
        row[11] = sharing.getTbcFlagName();
        row[12] = sharing.getTbcDate();
        return row;
    }

}
