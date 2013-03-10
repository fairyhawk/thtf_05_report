package cn.com.thtf.cms.report.daoimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.TransportFareSharingDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.TransportFareSharingDto;
import cn.com.thtf.cms.report.entity.TransportFareSharingEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * TransportFareSharingDaoImpl
 * 
 * @author zhangzx
 */
public class TransportFareSharingDaoImpl extends
        GenericDaoImpl<TransportFareSharingDto, String> implements TransportFareSharingDao {


    @SuppressWarnings("unchecked")
    @Override
    public List<TransportFareSharingEntity> getTransportFareSharing(
            BaseQueryData<TransportFareSharingDto> queryParam) {
        return getSqlMapClientTemplate().queryForList(
                "transportFareSharing.getTransportFareSharing", queryParam);
    }

    @Override
    public TransportFareSharingDto getTransportFareSharingSumVal(
            BaseQueryData<TransportFareSharingDto> queryParam) {
        return (TransportFareSharingDto) getSqlMapClientTemplate().queryForObject(
                "transportFareSharing.getTransportFareSharingSum", queryParam);
    }

}
