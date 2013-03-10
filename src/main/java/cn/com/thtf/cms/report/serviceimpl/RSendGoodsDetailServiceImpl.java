/**
 * ClassName  RSendGoodsDetailServerImpl
 *
 * History
 * Create User: hanrb
 * Create Date: 2010-12-14
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.serviceimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.RSendGoodsDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSendGoodsDetailDto;
import cn.com.thtf.cms.report.entity.RSendGoodsDetailEntity;
import cn.com.thtf.cms.report.service.RSendGoodsDetailService;

/**
 * 
 * @author hanrb
 */

public class RSendGoodsDetailServiceImpl implements RSendGoodsDetailService{
    private RSendGoodsDetailDao sendGoodsDetailDao;


    public RSendGoodsDetailDao getSendGoodsDetailDao() {
        return sendGoodsDetailDao;
    }

    public void setSendGoodsDetailDao(RSendGoodsDetailDao sendGoodsDetailDao) {
        this.sendGoodsDetailDao = sendGoodsDetailDao;
    }


    /* (non-Javadoc)
     * @see cn.com.thtf.cms.report.service.RtransportFareSharingServer#getTransportFareSharingList(cn.com.thtf.cms.report.dto.RTransportFareSharingDto, int)
     */
    public void getSendGoodsDetailList(BaseQueryData<RSendGoodsDetailDto> queryParam) {
        List<RSendGoodsDetailDto> resultList = sendGoodsDetailDao.queryRecords(queryParam);
        long foundRows = sendGoodsDetailDao.getFoundRows();
        queryParam.setResultPage((int) foundRows);
        queryParam.setDataList(resultList);
    }
    public RSendGoodsDetailEntity getSendGoodsDetailSum(BaseQueryData<RSendGoodsDetailDto> queryParam) {
        return sendGoodsDetailDao.getSendGoodsDetailSum(queryParam);
    }

}
