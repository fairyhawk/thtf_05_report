/**
 * ClassName  RSendGoodsDetailServerImpl
 *
 * History
 * Create User: hanrb
 * Create Date: 2010-12-14
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.daoimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.RSendGoodsDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSendGoodsDetailDto;
import cn.com.thtf.cms.report.entity.RSendGoodsDetailEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * 
 * @author hanrb
 */

public class RSendGoodsDetailDaoImpl extends GenericDaoImpl<RSendGoodsDetailDto, String>
        implements RSendGoodsDetailDao {
    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.RSendGoodsDetailDao#queryRecords(java.lang
     * .String, java.lang.Object, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RSendGoodsDetailDto> queryRecords(
            BaseQueryData<RSendGoodsDetailDto> queryParam) {
        return getSqlMapClientTemplate().queryForList(
                "sendGoodsDetail.getSendGoodsDetail", queryParam);
    }

    public RSendGoodsDetailEntity getSendGoodsDetailSum(BaseQueryData<RSendGoodsDetailDto> queryParam) {
        return (RSendGoodsDetailEntity) getSqlMapClientTemplate().queryForObject(
                "sendGoodsDetail.getSendGoodsDetailSum", queryParam);
    }
}
