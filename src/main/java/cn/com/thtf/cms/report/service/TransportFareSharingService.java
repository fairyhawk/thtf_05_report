package cn.com.thtf.cms.report.service;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.TransportFareSharingDto;

/**
 * TransportFareSharingService
 * 
 * @author zhangzx
 */
public interface TransportFareSharingService {

    /**
     * 获取运费分摊明细列表
     * 
     * @param queryParam
     */
    public void getTransportFareSharingDetail(BaseQueryData<TransportFareSharingDto> queryParam);

    /**
     * 获取运费分摊明细列表合计值
     * 
     * @param queryParam
     * @return
     */
    public TransportFareSharingDto getTransportFareSharingSumVal(
            BaseQueryData<TransportFareSharingDto> queryParam);
}
