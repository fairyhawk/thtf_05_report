/**
 * ClassName  StockProductDetailService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-12-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.service;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.StockProductDetailDto;
import cn.com.thtf.cms.report.entity.StockProductDetailEntity;

/**
 * StockProductDetailService
 * 
 * @author Lubo
 */
public interface StockProductDetailService {

    /**
     * 获取库存产品明细列表
     * 
     * @param queryParam
     */
    public void getStockProductDetail(BaseQueryData<StockProductDetailEntity> queryParam);

    /**
     * 获取库存产品明细列表合计值
     * 
     * @param queryParam
     * @return
     */
    public StockProductDetailDto getStockProductDetailSumVal(
            BaseQueryData<StockProductDetailEntity> queryParam);
}
