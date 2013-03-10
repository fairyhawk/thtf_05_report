package cn.com.thtf.cms.report.service;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.InstockDetailDto;

/**
 * InstockDetailService
 * 
 * @author zhangzx
 */
public interface InstockDetailService {

    /**
     * 获取入库明细列表
     * 
     * @param queryParam
     */
    public void getInstockDetail(BaseQueryData<InstockDetailDto> queryParam);

    /**
     * 获取入库明细列表合计值
     * 
     * @param queryParam
     * @return
     */
    public InstockDetailDto getInstockDetailSumVal(BaseQueryData<InstockDetailDto> queryParam);
}
