/**
 * ClassName  RTransportFareDetailServiceImpl
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.serviceimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.RTransportFareDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RTransportFareDetailDto;
import cn.com.thtf.cms.report.entity.TransportFareDetailEntity;
import cn.com.thtf.cms.report.service.RTransportFareDetailService;

/**
 * 
 * @author hanrb
 */

public class RTransportFareDetailServiceImpl implements RTransportFareDetailService {
    private RTransportFareDetailDao transportFareDetailDao;

    public RTransportFareDetailDao getTransportFareDetailDao() {
        return transportFareDetailDao;
    }

    public void setTransportFareDetailDao(RTransportFareDetailDao transportFareDetailDao) {
        this.transportFareDetailDao = transportFareDetailDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.service.RTransportFareDetailService#
     * getTransportFareDetailList(cn.com.thtf.cms.report.dto.BaseQueryData)
     */
    @Override
    public void getTransportFareDetailList(
            BaseQueryData<RTransportFareDetailDto> queryParam) {
        List<RTransportFareDetailDto> resultList = transportFareDetailDao
                .queryRecords(queryParam);
        long foundRows = transportFareDetailDao.getFoundRows();
        queryParam.setResultPage((int) foundRows);
        queryParam.setDataList(resultList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.service.RTransportFareDetailService#
     * getTransportFareDetailSum
     * (cn.com.thtf.cms.report.dto.RTransportFareDetailDto)
     */
    @Override
    public TransportFareDetailEntity getTransportFareDetailSum(
            BaseQueryData<RTransportFareDetailDto> queryParam) {
        return transportFareDetailDao.getTransportFareDetailSum(queryParam);
    }

}
