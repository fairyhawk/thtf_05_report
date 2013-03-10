/**
 * ClassName  RSellContractDetailDaoImpl
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.daoimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.RSellContractDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSellContractDetailDto;
import cn.com.thtf.cms.report.entity.RSellContractDetailEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * 
 * @author liuqg
 */

public class RSellContractDetailDaoImpl extends
        GenericDaoImpl<RSellContractDetailDto, String> implements RSellContractDetailDao {

    /**
     * 获取销售合同产品明细数据
     * 
     * @param queryParam
     * @return
     */

    @SuppressWarnings("unchecked")
    @Override
    public List<RSellContractDetailEntity> queryRecords(
            BaseQueryData<RSellContractDetailDto> queryParam) {
        return getSqlMapClientTemplate().queryForList(
                "sellContractDetail.getSellContractDetail", queryParam);
    }

    /**
     * 获取销售合同产品明细数据合计值
     * 
     * @param queryParam
     * @return
     */
    @Override
    public RSellContractDetailEntity getRSellContractDetaiSumVal(
            BaseQueryData<RSellContractDetailDto> queryParam) {
        return (RSellContractDetailEntity) getSqlMapClientTemplate().queryForObject(
                "sellContractDetail.getSellContractDetailSumVal", queryParam);
    }

}
