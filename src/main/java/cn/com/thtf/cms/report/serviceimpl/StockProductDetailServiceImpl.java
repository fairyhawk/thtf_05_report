/**
 * ClassName  StockProductDetailServiceImpl
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-12-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.serviceimpl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import cn.com.thtf.cms.report.dao.StockProductDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.StockProductDetailDto;
import cn.com.thtf.cms.report.entity.StockProductDetailEntity;
import cn.com.thtf.cms.report.service.StockProductDetailService;

/**
 * StockProductDetailServiceImpl
 * 
 * @author Lubo
 */
public class StockProductDetailServiceImpl implements StockProductDetailService {

    @Setter
    @Getter
    /** StockProductDetailDao */
    private StockProductDetailDao dao;

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.service.StockProductDetailService#
     * getStockProductDetail(cn.com.thtf.cms.report.dto.BaseQueryData)
     */
    public void getStockProductDetail(BaseQueryData<StockProductDetailEntity> queryParam) {
        /* 检索数据 */
        List<StockProductDetailEntity> resultList = dao.getStockProductDetail(queryParam);
        long foundRows = dao.getFoundRows();
        /* 封装返回参数 */
        queryParam.setResultPage((int) foundRows);
        queryParam.setDataList(resultList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.service.StockProductDetailService#
     * getStockProductDetailSumVal
     * (cn.com.thtf.cms.report.entity.StockProductDetailEntity)
     */
    public StockProductDetailDto getStockProductDetailSumVal(
            BaseQueryData<StockProductDetailEntity> queryParam) {
        return dao.getStockProductDetailSumVal(queryParam);
    }

}
