/**
 * ClassName  CommonServiceImpl
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.dao.CommonDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.PromptQueryDto;
import cn.com.thtf.cms.report.dto.UserAreaMappingDto;
import cn.com.thtf.cms.report.dto.UserAreaProductDto;
import cn.com.thtf.cms.report.dto.UserProductDto;
import cn.com.thtf.cms.report.dto.UserStockroomProductDto;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.util.CryptUtil;

/**
 * CommonServiceImpl
 * 
 * @author hanrb
 */
public class CommonServiceImpl implements CommonService {

    /** log */
    private static final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);
    @Setter
    @Getter
    private CommonDao commonDao;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.service.CommonService#getUserFileParam(cn.com.
     * thtf.cms.report.entity.UserEntity, boolean)
     */
    public String getUserFileParam(UserEntity user, boolean itself) {
        StringBuffer result = new StringBuffer();

        int roldId = user.getRoleId();
        try {

            /* 销售总监、信用专员、采购专员、产品总监、采购主管 */
            if (roldId == 5 || roldId == 6 || roldId == 8 || roldId == 10 || roldId == 11) {
                if (itself) {
                    // 产品总监采购合同时只查看自己负责的
                    result.append("user_").append(CryptUtil.MD5(user.getId()));
                } else {
                    List<UserProductDto> resultList = commonDao.getUserProduct(user
                            .getUserid());
                    if (resultList.size() > 0) {
                        result.append("up_");

                        StringBuffer sb = new StringBuffer();
                        for (UserProductDto userProductDto : resultList) {
                            sb.append("[");
                            sb.append(userProductDto.getProductTypeId());
                            sb.append("]");
                        }
                        result.append(CryptUtil.MD5(sb.toString()));
                    }
                }
            } else if (roldId == 19 || roldId == 20) {
                /* 大区经理、区域经理 */
                List<UserAreaMappingDto> resultList = commonDao.getUserAreaMapping(user
                        .getUserid());
                if (resultList.size() > 0) {
                    result.append("uam_");

                    StringBuffer sb = new StringBuffer();
                    for (UserAreaMappingDto userAreaMappingDto : resultList) {
                        sb.append("[");
                        sb.append(userAreaMappingDto.getUserAreaId());
                        sb.append("]");
                    }
                    result.append(CryptUtil.MD5(sb.toString()));
                }
            } else if (roldId == 12) {
                /* 库房管理员 */
                List<UserStockroomProductDto> resultList = getUserStockroomProductList(user
                        .getUserid());
                if (resultList.size() > 0) {
                    result.append("usp_");

                    StringBuffer sb = new StringBuffer();
                    for (UserStockroomProductDto userStockroomProductDto : resultList) {
                        sb.append("[");
                        sb.append(userStockroomProductDto.getStockroomId());
                        sb.append("&");
                        sb.append(userStockroomProductDto.getProductTypeId());
                        sb.append("]");
                    }
                    result.append(CryptUtil.MD5(sb.toString()));
                }
            } else if (roldId == 3) {
                /* 销售助理 */
                List<UserAreaProductDto> resultList = getUserAreaProductList(user
                        .getUserid());
                if (resultList.size() > 0) {
                    result.append("uap_");

                    StringBuffer sb = new StringBuffer();
                    for (UserAreaProductDto userAreaProductDto : resultList) {
                        sb.append("[");
                        sb.append(userAreaProductDto.getUserAreaId());
                        sb.append("&");
                        sb.append(userAreaProductDto.getProductTypeId());
                        sb.append("]");
                    }
                    result.append(CryptUtil.MD5(sb.toString()));
                }
            } else if (roldId == 9) {
                /* 区域总监 */
                List<UserAreaProductDto> resultList = getUserProductList(user.getUserid());
                if (resultList.size() > 0) {
                    result.append("ap_");

                    StringBuffer sb = new StringBuffer();
                    sb.append("[");
                    sb.append(resultList.get(0).getUserAreaId());
                    sb.append("]");
                    for (UserAreaProductDto userAreaProductDto : resultList) {
                        sb.append("[");
                        sb.append(userAreaProductDto.getProductTypeId());
                        sb.append("]");
                    }
                    result.append(CryptUtil.MD5(sb.toString()));
                }
            } else if (roldId == 4) {
                /* 销售经理 */
                result.append("user_").append(CryptUtil.MD5(user.getId()));
            } else {
                result.append("all");
            }

        } catch (NoSuchAlgorithmException e) {
            log.error("MD5加密错误:", e);
        } catch (UnsupportedEncodingException e) {
            log.error("MD5加密错误:", e);
        }
        return result.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.service.CommonService#getUserFileParam(cn.com.
     * thtf.cms.report.entity.UserEntity,
     * cn.com.thtf.cms.report.dto.BaseQueryData)
     */
    public boolean getUserFileParam(UserEntity user, BaseQueryData<?> queryParam) {
        boolean result = true;

        int roldId = user.getRoleId();

        /* 销售总监、信用专员、采购专员、产品总监、采购主管 */
        if (roldId == 5 || roldId == 6 || roldId == 8 || roldId == 10 || roldId == 11) {
            List<UserProductDto> resultList = commonDao.getUserProduct(user.getUserid());
            if (resultList.size() > 0) {
                queryParam.setUserProductList(resultList);
            } else {
                result = false;
            }

            if (roldId == 10) {
                // 产品总监采购合同时只查看自己负责的
                queryParam.setUserId(user.getUserid());
                result = true;
            }
        } else if (roldId == 19 || roldId == 20) {
            /* 大区经理、区域经理 */
            List<UserAreaMappingDto> resultList = commonDao.getUserAreaMapping(user
                    .getUserid());
            if (resultList.size() > 0) {
                queryParam.setUserAreaMappingList(resultList);
            } else {
                result = false;
            }
        } else if (roldId == 12) {
            /* 库房管理员 */
            List<UserStockroomProductDto> resultList = getUserStockroomProductList(user
                    .getUserid());
            if (resultList.size() > 0) {
                queryParam.setUserStockroomProductList(resultList);
            } else {
                result = false;
            }
        } else if (roldId == 3) {
            /* 销售助理 */
            List<UserAreaProductDto> resultList = getUserAreaProductList(user.getUserid());
            if (resultList.size() > 0) {
                queryParam.setUserAreaProductList(resultList);
            } else {
                result = false;
            }
        } else if (roldId == 9) {
            /* 区域总监 */
            List<UserAreaProductDto> resultList = getUserProductList(user.getUserid());
            if (resultList.size() > 0) {
                queryParam.setUserAreaProductList(resultList);
            } else {
                result = false;
            }
        } else if (roldId == 4) {
            /* 销售经理 */
            queryParam.setUserId(user.getUserid());
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.service.BaseCommonService#getUserAreaProductList
     * (java.lang.String)
     */
    @Override
    public List<UserAreaProductDto> getUserAreaProductList(String userId) {
        return commonDao.getUserAreaProductList(userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.service.BaseCommonService#userAreaProductList(
     * java.lang.String)
     */
    @Override
    public List<UserAreaProductDto> getUserProductList(String userId) {
        return commonDao.getUserProductList(userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.service.BaseCommonService#getUserStockroomProductList
     * (java.lang.String)
     */
    @Override
    public List<UserStockroomProductDto> getUserStockroomProductList(String userId) {
        return commonDao.getUserStockroomProductList(userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.service.BasePromptService#getProptData()
     */
    @Override
    public List<Object> getPromptCustomerData(PromptQueryDto promptQueryDto) {
        return commonDao.getPromptCustomerData(promptQueryDto);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.cms.report.service.BasePromptService#getPromptSupplierData()
     */
    @Override
    public List<Object> getPromptSupplierData(PromptQueryDto promptQueryDto) {
        return commonDao.getPromptSupplierData(promptQueryDto);
    }

}
