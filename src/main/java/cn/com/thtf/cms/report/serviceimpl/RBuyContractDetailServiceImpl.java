package cn.com.thtf.cms.report.serviceimpl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import cn.com.thtf.cms.report.dao.RBuyContractDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RBuyContractDetailDto;
import cn.com.thtf.cms.report.entity.RBuyContractDetailEntity;
import cn.com.thtf.cms.report.service.RBuyContractDetailService;

/**
 * 采购合同明细服务实现
 * 
 * @author HanHaiyun
 * 
 */
public class RBuyContractDetailServiceImpl implements RBuyContractDetailService {

    @Getter
    @Setter
    private RBuyContractDetailDao rBuyContractDetailDao;

    @Override
    public void getBuyContractDetailList(
            BaseQueryData<RBuyContractDetailEntity> queryParam) {
        List<RBuyContractDetailEntity> dataList = rBuyContractDetailDao
                .getBuyContractDetailList(queryParam);
        long foundrows = rBuyContractDetailDao.getFoundRows();
        queryParam.setResultPage((int) foundrows);
        queryParam.setDataList(dataList);
    }
    
    @Override
    public RBuyContractDetailDto getBuyContractDeatilSum(
            BaseQueryData<RBuyContractDetailEntity> queryParam) {
        // TODO Auto-generated method stub
        return rBuyContractDetailDao.getBuyContractDeatilSum(queryParam);
    }

}
