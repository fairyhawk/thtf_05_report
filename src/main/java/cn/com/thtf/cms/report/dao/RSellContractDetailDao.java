/**
 * ClassName  RsellContractDetailDao
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dao;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSellContractDetailDto;
import cn.com.thtf.cms.report.entity.RSellContractDetailEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * 销售合同产品明细
 * 
 * @author liuqg
 */

public interface RSellContractDetailDao extends
        GenericDao<RSellContractDetailDto, String> {
    /**
     * 获取销售合同产品明细数据
     * 
     * @param queryParam
     * @return
     */
    public List<RSellContractDetailEntity> queryRecords(
            BaseQueryData<RSellContractDetailDto> queryParam);

    /**
     * 获取销售合同产品明细数据合计值
     * 
     * @param queryParam
     * @return
     */

    public RSellContractDetailEntity getRSellContractDetaiSumVal(
            BaseQueryData<RSellContractDetailDto> queryParam);

}
