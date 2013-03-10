package cn.com.thtf.cms.report.daoimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.InstockDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.InstockDetailDto;
import cn.com.thtf.cms.report.entity.InstockDetailEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * InstockDetailDaoImpl
 * 
 * @author zhangzx
 */
public class InstockDetailDaoImpl extends
        GenericDaoImpl<InstockDetailDto, String> implements InstockDetailDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<InstockDetailEntity> getInstockDetail(
            BaseQueryData<InstockDetailDto> queryParam) {
        return getSqlMapClientTemplate().queryForList(
              "instockDetail.getInstockDetail", queryParam);
    }

    @Override
    public InstockDetailDto getInstockDetailSumVal(BaseQueryData<InstockDetailDto> queryParam) {
        return (InstockDetailDto) getSqlMapClientTemplate().queryForObject(
              "instockDetail.getInstockDetailSum", queryParam);
    }

}
