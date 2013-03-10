package cn.com.thtf.cms.report.service;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.entity.RDynamicStocktakeEntity;

/**
 * 动态盘点服务接口
 * 
 * @author HanHaiyun
 * 
 */
public interface RDynamicStocktakeService {
    public void getStocktakeList(BaseQueryData<RDynamicStocktakeEntity> queryParam);
}
