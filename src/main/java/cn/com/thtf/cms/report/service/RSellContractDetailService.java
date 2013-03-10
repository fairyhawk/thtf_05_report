/**
 * ClassName  RSellContractDetailService
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.service;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSellContractDetailDto;
import cn.com.thtf.cms.report.entity.RSellContractDetailEntity;

/**
 * 
 * @author liuqg
 */

public interface RSellContractDetailService {
    /**
     * 获取销售合同产品明细
     * 
     * @param queryParam
     */
    public void getRSellContractDetail(BaseQueryData<RSellContractDetailDto> queryParam);

    /**
     * 获取销售合同产品明细合计值
     * 
     * @param queryParam
     * @return
     */
    public RSellContractDetailEntity getRSellContractDetailSumVal(
            BaseQueryData<RSellContractDetailDto> queryParam);
}
