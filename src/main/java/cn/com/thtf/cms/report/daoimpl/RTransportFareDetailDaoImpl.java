/**
 * ClassName  RTransportFareDetailDaoImpl
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.daoimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.RTransportFareDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RTransportFareDetailDto;
import cn.com.thtf.cms.report.entity.TransportFareDetailEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * 
 * @author hanrb
 */

public class RTransportFareDetailDaoImpl extends
        GenericDaoImpl<RTransportFareDetailDto, String> implements
        RTransportFareDetailDao {
    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.RTransportFareDetailDao#queryRecords(cn.com
     * .thtf.cms.report.dto.BaseQueryData)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RTransportFareDetailDto> queryRecords(
            BaseQueryData<RTransportFareDetailDto> queryParam) {
        return getSqlMapClientTemplate().queryForList(
                "transportFareDetail.getTransportFareDetail", queryParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.RTransportFareDetailDao#getSendGoodsDetailSum
     * (cn.com.thtf.cms.report.dto.RTransportFareDetailDto)
     */
    @Override
    public TransportFareDetailEntity getTransportFareDetailSum(
            BaseQueryData<RTransportFareDetailDto> queryParam) {
        return (TransportFareDetailEntity) getSqlMapClientTemplate().queryForObject(
                "transportFareDetail.getTransportFareDetailSum", queryParam);
    }

}
