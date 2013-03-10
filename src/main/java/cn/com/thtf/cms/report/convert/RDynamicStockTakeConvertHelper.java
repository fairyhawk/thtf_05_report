package cn.com.thtf.cms.report.convert;

import org.springframework.dao.DataAccessException;
import cn.com.thtf.cms.report.entity.RDynamicStocktakeEntity;
/**
 * 动态盘点表
 * 
 * @author HanHaiyun
 */

public class RDynamicStockTakeConvertHelper implements ConvertHelper {

    @Override
    public String[] getTilte() {
        return new String[] { "库房名称", "产品编码", "产品名称", "规格型号", "单位", "库存数量", "所在地",
                "所在地库存汇总" };

    }

    @SuppressWarnings("serial")
    @Override
    public String[] getRow(Object obj) {
        if (!(obj instanceof RDynamicStocktakeEntity)) {
            throw new DataAccessException("row data must be RDynamicStockTake type") {
            };
        }
        RDynamicStocktakeEntity dto = (RDynamicStocktakeEntity) obj;
        String[] row = new String[8];
        row[0] = dto.getStockName();
        row[1] = dto.getProductCode();
        row[2] = dto.getProductName();
        row[3] = dto.getProductType();
        row[4] = dto.getProductUnit();
        if (dto.getStockroomCount() == null) {
            row[5] = "";
        } else {
            row[5] = dto.getStockroomCount().toString();
        }
        row[6] = dto.getShortName();
        if (dto.getStockNumSum() == null) {
            row[7] = "";
        } else {
            row[7] = dto.getStockNumSum().toString();
        }

        return row;
    }

}
