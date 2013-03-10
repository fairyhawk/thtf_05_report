/**
 * ClassName  CommonDao
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dao;

import java.util.List;

import cn.com.thtf.cms.report.dto.CommonDto;
import cn.com.thtf.cms.report.dto.PromptQueryDto;
import cn.com.thtf.cms.report.dto.UserAreaMappingDto;
import cn.com.thtf.cms.report.dto.UserAreaProductDto;
import cn.com.thtf.cms.report.dto.UserProductDto;
import cn.com.thtf.cms.report.dto.UserStockroomProductDto;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * CommonDao
 * 
 * @author hanrb
 */
public interface CommonDao extends GenericDao<CommonDto, String> {

    /**
     * 获取文本框提示之客户名字
     * 
     * @param promptQueryDto
     */
    public List<Object> getPromptCustomerData(PromptQueryDto promptQueryDto);

    /**
     * 获取文本框提示之供货商
     * 
     * @param promptQueryDto
     */
    public List<Object> getPromptSupplierData(PromptQueryDto promptQueryDto);

    /**
     * 获取销售助理的区域和产品分类
     * 
     * @param userId
     */
    public List<UserAreaProductDto> getUserAreaProductList(String userId);

    /**
     * 获取区域总监的区域和产品分类
     * 
     * @param userId
     */
    public List<UserAreaProductDto> getUserProductList(String userId);

    /**
     * 获取库房管理员的产品分类和库房id
     * 
     * @param userId
     */
    public List<UserStockroomProductDto> getUserStockroomProductList(String userId);
    
    /**
     * 根据用户ID检索负责的区域
     * 
     * @param userId
     */
    public List<UserAreaMappingDto> getUserAreaMapping(String userId);
  
    /**
     * 根据用户ID检索负责的产品分类
     * 
     * @param userId
     */
    public List<UserProductDto> getUserProduct(String userId);
}
