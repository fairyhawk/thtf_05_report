/**
 * ClassName  StockProductDetailServiceImpl
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-12-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.serviceimpl;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
import cn.com.thtf.cms.report.dao.InstockDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.InstockDetailDto;
import cn.com.thtf.cms.report.entity.InstockDetailEntity;
import cn.com.thtf.cms.report.service.InstockDetailService;

/**
 * InstockDetailServiceImpl
 * 
 * @author zhangzx
 */
public class InstockDetailServiceImpl implements InstockDetailService {

    @Setter
    @Getter
    /** StockProductDetailDao */
    private InstockDetailDao dao;

    @Override
    public void getInstockDetail(BaseQueryData<InstockDetailDto> queryParam) {
        /* 检索数据 */
      List<InstockDetailEntity> resultList = dao.getInstockDetail(queryParam);
      long foundRows = dao.getFoundRows();
      /* 封装返回参数 */
      queryParam.setResultPage((int) foundRows);
      queryParam.setDataList(resultList);
    }

    @Override
    public InstockDetailDto getInstockDetailSumVal(BaseQueryData<InstockDetailDto> queryParam) {
        return dao.getInstockDetailSumVal(queryParam);
    }
}
