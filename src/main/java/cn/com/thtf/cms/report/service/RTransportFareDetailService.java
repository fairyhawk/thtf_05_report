/**
 * ClassName  RTransportFareDetailService
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.service;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RTransportFareDetailDto;
import cn.com.thtf.cms.report.entity.TransportFareDetailEntity;

/**
 * 
 * @author hanrb
 */

public interface RTransportFareDetailService {

    /**
     * 获取运费明细列表数据
     * 
     * @param queryParam
     */
    public void getTransportFareDetailList(
            BaseQueryData<RTransportFareDetailDto> queryParam);

    /**
     * 获取运费明细总和数据
     * 
     * @param param
     */
    public TransportFareDetailEntity getTransportFareDetailSum(
            BaseQueryData<RTransportFareDetailDto> queryParam);

}
