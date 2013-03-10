package cn.com.thtf.cms.report.dto;


public class UserAreaProductDto {

    private static final long serialVersionUID = 5218956859792267451L;

    private Integer id;

    private String userId;

    private Integer userAreaId;

    private Integer productTypeId;

    private String productTypeName;
    
    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getUserAreaName() {
        return userAreaName;
    }

    public void setUserAreaName(String userAreaName) {
        this.userAreaName = userAreaName;
    }

    private String userAreaName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserAreaId() {
        return userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
}
