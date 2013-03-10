/**
 * ClassName  SiteInformationService
 *
 * History
 * Create User: zhangzx
 * Create Date: 2011-2-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.service.base;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.SiteInformationDto;
import cn.com.thtf.cms.report.dto.SiteInformationOfShowDto;
import cn.com.thtf.cms.report.entity.SiteInformationEntity;

/**
 * 
 * SiteInformationService
 * 
 * @author zhangzx
 */
public interface SiteInformationService {

    /**
     * 新增站内信
     * 
     */
    public boolean insertSiteInfo(SiteInformationEntity siteInfomationEntity);

    /**
     * 修改站内信
     * 
     */
    public boolean updateSiteInfo(SiteInformationEntity siteInfoEntity);

    /**
     * 查看站内信
     * 
     */
    public SiteInformationEntity getSiteInfo(Integer id);
    
    /**
     * 删除站内信
     * 
     */
    public boolean deleteSiteInfo(Integer id);

    /**
     * 获得站内信列表
     * 
     * @param queryParam
     */
    public void getSiteList(BaseQueryData<SiteInformationDto> queryParam);
    /**
     * 获取滚动标题
     * 
     */
    public List<SiteInformationEntity> getSubject();
    /**
     * 获取站内信滚动列表
     */
    public List<SiteInformationOfShowDto> getSiteInfoScollList(BaseQueryData<SiteInformationDto> queryParam);
    /**
     * 获取站内信历史列表
     */
    public List<SiteInformationOfShowDto> getSiteInfoHistoryList(BaseQueryData<SiteInformationDto> queryParam);
}
