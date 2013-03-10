/**
 * ClassName  SiteInformationDao
 *
 * History
 * Create User: zhangzx
 * Create Date: 2011-2-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dao.base;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.SiteInformationDto;
import cn.com.thtf.cms.report.dto.SiteInformationOfShowDto;
import cn.com.thtf.cms.report.entity.SiteInformationEntity;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * 
 * SiteInformationDao
 * 
 * @author zhangzx
 */
public interface SiteInformationDao extends GenericDao<SiteInformationEntity, String> {

    /**
     * 新增站内信
     * 
     * @param
     */
    public void addSiteInfo(SiteInformationEntity siteInfoEntity);

    /**
     * 修改站内信
     * 
     * @param
     */
    public int updateSiteInfo(SiteInformationEntity siteInfoEntity);

    /**
     * 获得站内信
     * 
     * @param
     */
    public SiteInformationEntity getSiteInfo(Integer id);
    
    /**
     * 删除站内信
     * 
     * @param
     */
    public int deleteSiteInfo(Integer id);

    /**
     * 获得站内信
     * 
     * @param queryParam
     * @return
     */
    public List<SiteInformationEntity> getSiteList(
            BaseQueryData<SiteInformationDto> queryParam);
    /**
     * 获得站内信标题
     * 
     * @param 
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
