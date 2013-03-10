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
import cn.com.thtf.cms.report.dao.TransportFareSharingDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.TransportFareSharingDto;
import cn.com.thtf.cms.report.entity.TransportFareSharingEntity;
import cn.com.thtf.cms.report.service.TransportFareSharingService;

/**
 * TransportFareSharingServiceImpl
 * 
 * @author zhangzx
 */
public class TransportFareSharingServiceImpl implements TransportFareSharingService {

    @Setter
    @Getter
    /** StockProductDetailDao */
    private TransportFareSharingDao dao;

    @Override
    public void getTransportFareSharingDetail(BaseQueryData<TransportFareSharingDto> queryParam) {
        /* 检索数据 */
        List<TransportFareSharingEntity> resultList = dao.getTransportFareSharing(queryParam);
        long foundRows = dao.getFoundRows();
        /* 封装返回参数 */
        queryParam.setResultPage((int) foundRows);
        queryParam.setDataList(resultList);
    }

    @Override
    public TransportFareSharingDto getTransportFareSharingSumVal(
            BaseQueryData<TransportFareSharingDto> queryParam) {
        return dao.getTransportFareSharingSumVal(queryParam);
    }

}
