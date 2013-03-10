package cn.com.thtf.cms.report.dao;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RBuyContractDetailDto;
import cn.com.thtf.cms.report.entity.RBuyContractDetailEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * 采购合同明细DAO接口
 * 
 * @author HanHaiyun
 * 
 */
public interface RBuyContractDetailDao extends
        GenericDao<RBuyContractDetailEntity, String> {

    /**
     * 检索采购合同明细数据
     * 
     * @param queryParam
     * @return
     */
    public List<RBuyContractDetailEntity> getBuyContractDetailList(
            BaseQueryData<RBuyContractDetailEntity> queryParam);

    /**
     * 获取采购合同明细各种金额的合计值
     * 
     * @param queryParam
     * @return
     */
    public RBuyContractDetailDto getBuyContractDeatilSum(
            BaseQueryData<RBuyContractDetailEntity> queryParam);
}
