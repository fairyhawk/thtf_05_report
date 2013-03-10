/**
 * ClassName  QueryDataBase
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-12-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import lombok.Data;

/**
 * QueryDataBase
 * 
 * @author Lubo
 * 
 */
@Data
public class BaseQueryData<T> implements Serializable {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 检索的列 */
    private List<String> fields;
    /** 排序的列 */
    private String sidx;
    /** 升序、降序 */
    private String sord;
    /** 共多少条 */
    private int rows;
    /** 第多少条 */
    private int star;
    /** 检索条件 */
    private T query;

    /** 每页多少条 */
    private int page;
    /** 总共条 */
    private int records;
    /** 总共多少页 */
    private int total;
    /** 检索结果 */
    private List<?> dataList;
    
    /** 用户id */
    private String userId; 
    /** 负责区域  */
    private List<UserAreaMappingDto> userAreaMappingList;
    /** 负责区域和产品分类  */
    private List<UserAreaProductDto> userAreaProductList;
    /** 负责产品分类  */
    private List<UserProductDto> userProductList;
    /** 负责产品分类和库房  */
    private List<UserStockroomProductDto> userStockroomProductList;
    
    /**
     * setFieldsParam
     * 
     * @param fieldsKey
     * @param fieldsArr
     */
    public void setFieldsParam(String fieldsKey, String[] fieldsArr) {
        if (fieldsKey != null && validateFields(fieldsKey.length(),fieldsKey)) {
            fields = new ArrayList<String>();
            for (int i = 0; i < fieldsKey.length(); i++) {
                if ("1".equals(fieldsKey.substring(i, (i + 1)))) {
                    fields.add(fieldsArr[i]);
                }
            }
        }
    }

    /**
     * setResultPage
     * 
     * @param foundRows
     */
    public void setResultPage(int foundRows) {
        setRecords(foundRows);
        setTotal((records - 1) / rows + 1);
    }
    /**
     * 设定返回结果
     * 
     * @param foundRows
     * @return boolean
     */
    private boolean  validateFields(int length,String fields) {
        return Pattern.compile("[0-1]{"+length+"}").matcher(fields).matches();
    }
    
}
