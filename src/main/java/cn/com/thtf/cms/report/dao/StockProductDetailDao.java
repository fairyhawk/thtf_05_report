/**
 * ClassName  StockProductDetailDao
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-11-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dao;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.StockProductDetailDto;
import cn.com.thtf.cms.report.entity.StockProductDetailEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * StockProductDetailDao
 * 
 * @author Lubo
 */
public interface StockProductDetailDao extends
        GenericDao<StockProductDetailEntity, String> {

    /**
     * 获取库存产品明细数据
     * 
     * @param queryParam
     * @return
     */
    public List<StockProductDetailEntity> getStockProductDetail(
            BaseQueryData<StockProductDetailEntity> queryParam);

    /**
     * 获取库存产品明细列表合计值
     * 
     * @param queryParam
     * @return
     */
    public StockProductDetailDto getStockProductDetailSumVal(
            BaseQueryData<StockProductDetailEntity> queryParam);
}
