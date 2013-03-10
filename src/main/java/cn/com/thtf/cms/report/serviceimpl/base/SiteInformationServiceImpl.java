/**
 * ClassName  SiteInformationServiceImpl
 *
 * History
 * Create User: zhangzx
 * Create Date: 2011-2-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.serviceimpl.base;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.dao.base.SiteInformationDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.SiteInformationDto;
import cn.com.thtf.cms.report.dto.SiteInformationOfShowDto;
import cn.com.thtf.cms.report.entity.SiteInformationEntity;
import cn.com.thtf.cms.report.service.base.SiteInformationService;
import cn.com.thtf.cms.report.serviceimpl.CommonServiceImpl;

/**
 * SiteInformationServiceImpl
 * 
 * @author zhangzx
 */
public class SiteInformationServiceImpl implements SiteInformationService {

    /** log */
    private static final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);
    @Setter
    @Getter

    private SiteInformationDao siteInfoDao;

    @Override
    public boolean insertSiteInfo(SiteInformationEntity siteInfoEntity) {
        try {
            siteInfoDao.addSiteInfo(siteInfoEntity);
            return true;
        } catch (Exception e) {
            log.error("新建站内信失败", e);
            return false;
        }
    }

    @Override
    public SiteInformationEntity getSiteInfo(Integer id) {
        SiteInformationEntity siteInformationEntity = null;
        try {
            siteInformationEntity = siteInfoDao.getSiteInfo(id);
        } catch (Exception e) {
            log.error("获取站内信错误:", e);
        }
        return siteInformationEntity;
    }

    @Override
    public boolean updateSiteInfo(SiteInformationEntity siteInfoEntity) {
        try {
            int count = siteInfoDao.updateSiteInfo(siteInfoEntity);
            if (count != 1) {
                return false;
            }
        } catch (Exception e) {
            log.error("修改站内信错误", e);
            return false;
        }
        return true;
    }
    

    @Override
    public boolean deleteSiteInfo(Integer id) {
        try {
            int count = siteInfoDao.deleteSiteInfo(id);
            if (count != 1) {
                return false;
            }
        } catch (Exception e) {
            log.error("删除站内信错误", e);
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.service.base.SiteInformationService#getSiteList
     * (cn.com.thtf.cms.report.dto.BaseQueryData)
     */
    public void getSiteList(BaseQueryData<SiteInformationDto> queryParam) {
        /* 检索数据 */
        List<SiteInformationEntity> resultList = siteInfoDao.getSiteList(queryParam);
        long foundRows = siteInfoDao.getFoundRows();
        /* 封装返回参数 */
        queryParam.setResultPage((int) foundRows);
        queryParam.setDataList(resultList);
    }

    /* (non-Javadoc)
     * @see cn.com.thtf.cms.report.service.base.SiteInformationService#getSubject()
     */
    @Override
    public List<SiteInformationEntity> getSubject() {
        return siteInfoDao.getSubject();
    }

    /* (non-Javadoc)
     * @see cn.com.thtf.cms.report.service.base.SiteInformationService#getSiteInfoScollList(cn.com.thtf.cms.report.dto.BaseQueryData)
     */
    @Override
    public List<SiteInformationOfShowDto> getSiteInfoScollList(
            BaseQueryData<SiteInformationDto> queryParam) {
        List<SiteInformationOfShowDto> resultList = siteInfoDao.getSiteInfoScollList(queryParam);
        long foundRows = siteInfoDao.getFoundRows();
        queryParam.setResultPage((int) foundRows);
        queryParam.setDataList(resultList);
        return resultList;
    }

    /* (non-Javadoc)
     * @see cn.com.thtf.cms.report.service.base.SiteInformationService#getSiteInfoHistoryList(cn.com.thtf.cms.report.dto.BaseQueryData)
     */
    @Override
    public List<SiteInformationOfShowDto> getSiteInfoHistoryList(
            BaseQueryData<SiteInformationDto> queryParam) {
        List<SiteInformationOfShowDto> resultList = siteInfoDao.getSiteInfoHistoryList(queryParam);
        long foundRows = siteInfoDao.getFoundRows();
        queryParam.setResultPage((int) foundRows);
        queryParam.setDataList(resultList);
        return resultList;
    }
}
