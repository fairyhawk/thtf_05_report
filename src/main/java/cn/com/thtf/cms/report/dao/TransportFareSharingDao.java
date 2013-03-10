package cn.com.thtf.cms.report.dao;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.TransportFareSharingDto;
import cn.com.thtf.cms.report.entity.TransportFareSharingEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * TransportFareSharingDao
 * 
 * @author zhangzx
 */
public interface TransportFareSharingDao extends
        GenericDao<TransportFareSharingDto, String> {

    /**
     * 获取运费分摊明细数据
     * 
     * @param queryParam
     * @return
     */
    public List<TransportFareSharingEntity> getTransportFareSharing(
            BaseQueryData<TransportFareSharingDto> queryParam);

    /**
     * 获取运费分摊明细列表合计值
     * 
     * @param queryParam
     * @return
     */
    public TransportFareSharingDto getTransportFareSharingSumVal(
            BaseQueryData<TransportFareSharingDto> queryParam);
}
