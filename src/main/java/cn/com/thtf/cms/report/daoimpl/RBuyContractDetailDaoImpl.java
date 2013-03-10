package cn.com.thtf.cms.report.daoimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.RBuyContractDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RBuyContractDetailDto;
import cn.com.thtf.cms.report.entity.RBuyContractDetailEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * 采购合同明细实现
 * 
 * @author HanHaiyun
 * 
 */
public class RBuyContractDetailDaoImpl extends
        GenericDaoImpl<RBuyContractDetailEntity, String> implements RBuyContractDetailDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<RBuyContractDetailEntity> getBuyContractDetailList(
            BaseQueryData<RBuyContractDetailEntity> queryParam) {
        // TODO Auto-generated method stub
        return this.getSqlTemplate().queryForList(
                "buyContractDetail.selBuyContractDetailList", queryParam);
    }

    @Override
    public RBuyContractDetailDto getBuyContractDeatilSum(
            BaseQueryData<RBuyContractDetailEntity> queryParam) {
        return (RBuyContractDetailDto) this.getSqlTemplate().queryForObject(
                "buyContractDetail.selBuyContractDetailListSum", queryParam);
    }

}
