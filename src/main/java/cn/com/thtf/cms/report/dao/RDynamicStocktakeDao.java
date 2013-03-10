package cn.com.thtf.cms.report.dao;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.entity.RDynamicStocktakeEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * 动态盘点DAO
 * 
 * @author HanHaiyun
 * 
 */
public interface RDynamicStocktakeDao extends GenericDao<RDynamicStocktakeEntity, String> {
    /**
     * 获取动态盘点数据
     * 
     * @param queryParam
     * @return
     */
    public List<RDynamicStocktakeEntity> getStocktakeList(
            BaseQueryData<RDynamicStocktakeEntity> queryParam);
}
