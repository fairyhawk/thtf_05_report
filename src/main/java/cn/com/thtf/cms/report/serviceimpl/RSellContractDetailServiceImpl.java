/**
 * ClassName  RSellContractDetailServiceImpl
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.serviceimpl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import cn.com.thtf.cms.report.dao.RSellContractDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSellContractDetailDto;
import cn.com.thtf.cms.report.entity.RSellContractDetailEntity;
import cn.com.thtf.cms.report.service.RSellContractDetailService;

/**
 * 销售合同产品明细
 * 
 * @author liuqg
 */

public class RSellContractDetailServiceImpl implements RSellContractDetailService {

    @Getter
    @Setter
    private RSellContractDetailDao sellContractDetailDao;

    /**
     * 获取销售合同产品明细
     * 
     * @param queryParam
     */
    @Override
    public void getRSellContractDetail(BaseQueryData<RSellContractDetailDto> queryParam) {
        List<RSellContractDetailEntity> resultList = sellContractDetailDao
                .queryRecords(queryParam);
        long foundRows = sellContractDetailDao.getFoundRows();
        queryParam.setResultPage((int) foundRows);
        queryParam.setDataList(resultList);
    }

    /**
     * 获取销售合同产品明细合计值
     * 
     * @param queryParam
     * @return
     */
    @Override
    public RSellContractDetailEntity getRSellContractDetailSumVal(
            BaseQueryData<RSellContractDetailDto> queryParam) {
        return sellContractDetailDao.getRSellContractDetaiSumVal(queryParam);
    }

}
