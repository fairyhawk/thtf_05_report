/**
 * ClassName  SiteInformationDaoImpl
 *
 * History
 * Create User: zhangzx
 * Create Date: 2011-2-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.daoimpl.base;

import java.util.List;

import cn.com.thtf.cms.report.dao.base.SiteInformationDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.SiteInformationDto;
import cn.com.thtf.cms.report.dto.SiteInformationOfShowDto;
import cn.com.thtf.cms.report.entity.SiteInformationEntity;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * 
 * SiteInformationDaoImpl
 * 
 * @author zhangzx
 */
public class SiteInformationDaoImpl extends GenericDaoImpl<SiteInformationEntity, String>
        implements SiteInformationDao {

    @Override
    public void addSiteInfo(SiteInformationEntity siteInfoEntity) {
        getSqlMapClientTemplate().insert("siteInformation.addSiteInfoById", siteInfoEntity);
    }

    @Override
    public SiteInformationEntity getSiteInfo(Integer id) {
        return (SiteInformationEntity) getSqlMapClientTemplate().queryForObject(
                "siteInformation.selectSiteInfoById", id);
    }

    @Override
    public int updateSiteInfo(SiteInformationEntity siteInfoEntity) {
        return getSqlMapClientTemplate().update("siteInformation.updateSiteInfoById",
                siteInfoEntity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.base.SiteInformationDao#getSiteList(cn.com
     * .thtf.cms.report.dto.BaseQueryData)
     */
    @SuppressWarnings("unchecked")
    public List<SiteInformationEntity> getSiteList(
            BaseQueryData<SiteInformationDto> queryParam) {
        return getSqlMapClientTemplate().queryForList(
                "siteInformation.getSiteInformationList", queryParam);
    }
    @Override
    public int deleteSiteInfo(Integer id) {
        return getSqlMapClientTemplate().delete("siteInformation.deleteSiteInfoById", id);
    }
    /* (non-Javadoc)
     * @see cn.com.thtf.cms.report.dao.base.SiteInformationDao#getSubject()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SiteInformationEntity> getSubject() {
        return getSqlMapClientTemplate().queryForList("siteInformation.getSubject");
    }

    /* (non-Javadoc)
     * @see cn.com.thtf.cms.report.dao.base.SiteInformationDao#getSiteInfoList(cn.com.thtf.cms.report.dto.BaseQueryData)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SiteInformationOfShowDto> getSiteInfoScollList(
            BaseQueryData<SiteInformationDto> queryParam) {
        return getSqlMapClientTemplate().queryForList(
                "siteInformation.getSiteInfoScollList", queryParam);
    }
    @SuppressWarnings("unchecked")
    public List<SiteInformationOfShowDto> getSiteInfoHistoryList(BaseQueryData<SiteInformationDto> queryParam){
        return getSqlMapClientTemplate().queryForList(
                "siteInformation.getSiteInfoHistoryList", queryParam);
        
    }
}
