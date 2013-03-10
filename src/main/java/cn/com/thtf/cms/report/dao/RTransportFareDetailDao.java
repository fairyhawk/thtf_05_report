/**
 * ClassName  RTransportFareDetailDao
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dao;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RTransportFareDetailDto;
import cn.com.thtf.cms.report.entity.TransportFareDetailEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * 
 * @author hanrb
 */

public interface RTransportFareDetailDao extends
        GenericDao<RTransportFareDetailDto, String> {

    /**
     * 获取分摊明细数据
     * 
     * @param queryParam
     * @return
     */
    public List<RTransportFareDetailDto> queryRecords(
            BaseQueryData<RTransportFareDetailDto> queryParam);

    /**
     * 获取分摊明细数据合计值
     * 
     * @param queryParam
     * @return
     */
    public TransportFareDetailEntity getTransportFareDetailSum(
            BaseQueryData<RTransportFareDetailDto> queryParam);

}
