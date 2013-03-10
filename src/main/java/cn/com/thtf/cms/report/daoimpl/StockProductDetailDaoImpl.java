/**
 * ClassName  StockProductDetailDaoImpl
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-11-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.daoimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.StockProductDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.StockProductDetailDto;
import cn.com.thtf.cms.report.entity.StockProductDetailEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * StockProductDetailDaoImpl
 * 
 * @author Lubo
 */
public class StockProductDetailDaoImpl extends
        GenericDaoImpl<StockProductDetailEntity, String> implements StockProductDetailDao {

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.StockProductDetailDao#getStockProductDetail
     * (cn.com.thtf.cms.report.dto.BaseQueryData)
     */
    @SuppressWarnings("unchecked")
    public List<StockProductDetailEntity> getStockProductDetail(
            BaseQueryData<StockProductDetailEntity> queryParam) {
        return getSqlMapClientTemplate().queryForList(
                "stockProductDetail.getStockProductDetail", queryParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.StockProductDetailDao#getStockProductDetailSumVal
     * (cn.com.thtf.cms.report.entity.StockProductDetailEntity)
     */
    public StockProductDetailDto getStockProductDetailSumVal(
            BaseQueryData<StockProductDetailEntity> queryParam) {
        return (StockProductDetailDto) getSqlMapClientTemplate().queryForObject(
                "stockProductDetail.getStockProductDetailSum", queryParam);
    }

}
