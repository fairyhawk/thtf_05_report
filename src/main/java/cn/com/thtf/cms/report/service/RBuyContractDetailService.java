package cn.com.thtf.cms.report.service;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RBuyContractDetailDto;
import cn.com.thtf.cms.report.entity.RBuyContractDetailEntity;

/**
 * 采购合同明细服务接口
 * 
 * @author HanHaiyun
 * 
 */
public interface RBuyContractDetailService {

    /**
     * 检索采购合同明细数据
     * 
     * @param queryParam
     * @return
     */
    public void getBuyContractDetailList(
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
