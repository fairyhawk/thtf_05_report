/**
 * ClassName  RSendGoodsDetailDao
 *
 * History
 * Create User: hanrb
 * Create Date: 2010-12-14
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dao;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSendGoodsDetailDto;
import cn.com.thtf.cms.report.entity.RSendGoodsDetailEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * 
 * @author hanrb
 */
public interface RSendGoodsDetailDao extends GenericDao<RSendGoodsDetailDto, String> {
    /**
     * 获取发货明细数据
     * 
     * @param queryParam
     * @return
     */
    public List<RSendGoodsDetailDto> queryRecords(
            BaseQueryData<RSendGoodsDetailDto> queryParam);

    /**
     * 获取发货明细数据合计值
     * 
     * @param queryParam
     * @return
     */
    public RSendGoodsDetailEntity getSendGoodsDetailSum(BaseQueryData<RSendGoodsDetailDto> queryParam);
}
