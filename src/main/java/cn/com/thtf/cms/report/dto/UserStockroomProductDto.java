package cn.com.thtf.cms.report.dto;


public class UserStockroomProductDto {

    private static final long serialVersionUID = 7582905224465604962L;

    private Integer id;

    private String userId;

    private Integer stockroomId;

    private Integer productTypeId;

    private String productTypeName;
    
    private String stockroomName;
    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getStockroomName() {
        return stockroomName;
    }

    public void setStockroomName(String stockroomName) {
        this.stockroomName = stockroomName;
    }

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

    public Integer getStockroomId() {
        return stockroomId;
    }

    public void setStockroomId(Integer stockroomId) {
        this.stockroomId = stockroomId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
}
