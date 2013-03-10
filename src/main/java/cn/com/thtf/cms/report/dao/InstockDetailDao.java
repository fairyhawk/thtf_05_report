package cn.com.thtf.cms.report.dao;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.InstockDetailDto;
import cn.com.thtf.cms.report.entity.InstockDetailEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * InstockDetailDao
 * 
 * @author zhangzx
 */
public interface InstockDetailDao extends GenericDao<InstockDetailDto, String> {

    /**
     * 获取入库明细数据
     * 
     * @param queryParam
     * @return
     */
    public List<InstockDetailEntity> getInstockDetail(
            BaseQueryData<InstockDetailDto> queryParam);

    /**
     * 获取入库明细列表合计值
     * 
     * @param queryParam
     * @return
     */
    public InstockDetailDto getInstockDetailSumVal(BaseQueryData<InstockDetailDto> queryParam);
}
