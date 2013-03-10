package cn.com.thtf.cms.report.serviceimpl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import cn.com.thtf.cms.report.dao.RDynamicStocktakeDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.entity.RDynamicStocktakeEntity;
import cn.com.thtf.cms.report.service.RDynamicStocktakeService;

/**
 * 动态盘点服务实现
 * 
 * @author HanHaiyun
 * 
 */
public class RDynamicStocktakeServiceImpl implements RDynamicStocktakeService {

@Getter
@Setter
    private RDynamicStocktakeDao rDynamicStocktakeDao;

    @Override
    public void getStocktakeList(BaseQueryData<RDynamicStocktakeEntity> queryParam) {
        List<RDynamicStocktakeEntity> resultList = rDynamicStocktakeDao
                .getStocktakeList(queryParam);
        long foundRows = rDynamicStocktakeDao.getFoundRows();
        queryParam.setResultPage((int) foundRows);
        queryParam.setDataList(resultList);
    }

}
