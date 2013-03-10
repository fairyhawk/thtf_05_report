/**
 * ClassName  CommonService
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.service;

import java.util.List;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.PromptQueryDto;
import cn.com.thtf.cms.report.dto.UserAreaProductDto;
import cn.com.thtf.cms.report.dto.UserStockroomProductDto;
import cn.com.thtf.cms.report.entity.UserEntity;

/**
 * CommonService
 * 
 * @author hanrb
 */
public interface CommonService {

    /**
     * 获得用户负责的区域产品分类等
     * 
     * @param user
     * @param itself
     * @return
     */
    public String getUserFileParam(UserEntity user, boolean itself);

    /**
     * 获得用户负责的区域产品分类等
     * 
     * @param user
     * @return
     */
    public boolean getUserFileParam(UserEntity user, BaseQueryData<?> queryParam);

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
     * 获取客户名字
     * 
     * @param queryParam
     */
    public List<Object> getPromptCustomerData(PromptQueryDto promptQueryDto);

    /**
     * 获取供货商名字
     * 
     * @param queryParam
     */
    public List<Object> getPromptSupplierData(PromptQueryDto promptQueryDto);
}
