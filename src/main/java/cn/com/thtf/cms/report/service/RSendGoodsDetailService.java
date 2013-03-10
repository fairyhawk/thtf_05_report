/**
 * ClassName  RSendGoodsDetailServer
 *
 * History
 * Create User: hanrb
 * Create Date: 2010-12-14
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.service;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSendGoodsDetailDto;
import cn.com.thtf.cms.report.entity.RSendGoodsDetailEntity;

/**
 * 
 * @author hanrb
 */

public interface RSendGoodsDetailService {
    /**
     * 获取发货明细列表数据
     * 
     * @param queryParam
     */
    public void getSendGoodsDetailList(BaseQueryData<RSendGoodsDetailDto> queryParam);
    /**
     * 获取发货明细总和数据
     * 
     * @param param
     */
    public RSendGoodsDetailEntity getSendGoodsDetailSum(BaseQueryData<RSendGoodsDetailDto> queryParam);
}
