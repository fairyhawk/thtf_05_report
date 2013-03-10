package cn.com.thtf.cms.report.daoimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.RDynamicStocktakeDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.entity.RDynamicStocktakeEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * 动态盘点实现
 * 
 * @author HanHaiyun
 * 
 */
public class RDynamicStocktakeDaoImpl extends
        GenericDaoImpl<RDynamicStocktakeEntity, String> implements RDynamicStocktakeDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<RDynamicStocktakeEntity> getStocktakeList(
            BaseQueryData<RDynamicStocktakeEntity> queryParam) {
        return this.getSqlMapClientTemplate().queryForList(
                "dynamicStocktake.getStocktakeList", queryParam);
    }

}
