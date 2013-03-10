/**
 * ClassName  CommonDaoImpl
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.daoimpl;

import java.util.List;

import cn.com.thtf.cms.report.dao.CommonDao;
import cn.com.thtf.cms.report.dto.CommonDto;
import cn.com.thtf.cms.report.dto.PromptQueryDto;
import cn.com.thtf.cms.report.dto.UserAreaMappingDto;
import cn.com.thtf.cms.report.dto.UserAreaProductDto;
import cn.com.thtf.cms.report.dto.UserProductDto;
import cn.com.thtf.cms.report.dto.UserStockroomProductDto;
import cn.shiy.common.cws.dao.GenericDaoImpl;

/**
 * CommonDaoImpl
 * 
 * @author hanrb
 */
public class CommonDaoImpl extends GenericDaoImpl<CommonDto, String> implements CommonDao {

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.dao.BasePromptDao#getProptData()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getPromptCustomerData(PromptQueryDto promptQueryDto) {
        return getSqlMapClientTemplate().queryForList("common.getPromptCustomerData",
                promptQueryDto);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.CommonDao#getPromptSupplierData(cn.com.thtf
     * .cms.report.dto.PromptQueryDto)
     */
    @SuppressWarnings("unchecked")
    public List<Object> getPromptSupplierData(PromptQueryDto promptQueryDto) {
        return getSqlMapClientTemplate().queryForList("common.getPromptSupplierData",
                promptQueryDto);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.BaseCommonDao#getUserAreaProductList(java.
     * lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UserAreaProductDto> getUserAreaProductList(String userId) {

        return getSqlMapClientTemplate().queryForList(
                "common.getAreaIdAndProductTypeIdOfRoldIs3", userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.BaseCommonDao#userAreaProductList(java.lang
     * .String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UserAreaProductDto> getUserProductList(String userId) {
        return getSqlMapClientTemplate().queryForList(
                "common.getAreaIdAndProductTypeIdOfRoldIs9", userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.BaseCommonDao#getUserStockroomProductList(
     * java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UserStockroomProductDto> getUserStockroomProductList(String userId) {
        return getSqlMapClientTemplate().queryForList("common.getTreasuryManagerById",
                userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.CommonDao#getUserAreaMapping(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UserAreaMappingDto> getUserAreaMapping(String userId) {
        return getSqlMapClientTemplate()
                .queryForList("common.getUserAreaMapping", userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.dao.CommonDao#getUserProduct(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UserProductDto> getUserProduct(String userId) {
        return getSqlMapClientTemplate().queryForList("common.getUserProduct", userId);
    }
}
