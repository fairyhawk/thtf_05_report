/**
 * ClassName  StockProductDetailServiceImplTest
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-1-14
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.serviceimpl;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.entity.StockProductDetailEntity;
import cn.com.thtf.cms.report.service.StockProductDetailService;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author Lubo
 */
public class StockProductDetailServiceImplTest extends BaseTestNG implements NeedDBUnit {
    private StockProductDetailService service = null;
    /** DB连接 */
    private static IDatabaseConnection conn;
    /** 表 */
    private static final String TABLE_RSTOCKPRODUCTDETAIL = "r_stock_product_detail";
    /** 显示的列 */
    private String fieldRows[] = { "stockroom_name as stockroomName",
            "product_type_name as productTypeName",
            "product_serie_name as productSerieName", "product_code as productCode",
            "product_name as productName", "product_type as productType",
            "product_unit as productUnit", "ifnull(stockroom_count,0) as stockroomCount",
            "ifnull(round(stockroom_price,2),0) as stockroomPrice",
            "ifnull(round(stockroom_money,2),0) as stockroomMoney" };

    /**
     * 初始化
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public void setUp() {
        try {
            /* 获得service */
            service = Container.getBean("stockProductDetailService",
                    StockProductDetailService.class);
            /* 获得连接 */

            conn = new DatabaseConnection(Container
                    .getBean("sqlMapClient", SqlMapClient.class).getDataSource()
                    .getConnection());

            /* 获得需要备份表 */
            QueryDataSet tmpDataSet = new QueryDataSet(conn, false);
            tmpDataSet.addTable(TABLE_RSTOCKPRODUCTDETAIL);

            /* 从XML读取数据 */
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            IDataSet expectDataSet = builder.build(Container.getApplicationContext()
                    .getResource("classpath:dbunit/rstockProductDetail.xml")
                    .getInputStream());

            // /* 去掉外键关联 */
            // conn.createQueryTable("", "SET FOREIGN_KEY_CHECKS=0");

            /* 插入数据库 */
            DatabaseOperation.CLEAN_INSERT.execute(conn, expectDataSet);

        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetStockProductDetail001() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        queryParam.setQuery(param);// 查询条件
        service.getStockProductDetail(queryParam);
        service.getStockProductDetailSumVal(queryParam);
    }

    /**
     * 测试返回行数
     * 
     * @throws Exception
     */
    @Test
    public void testGetStockProductDetail002() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        queryParam.setQuery(param);// 查询条件
        service.getStockProductDetail(queryParam);
        Assert.assertEquals(queryParam.getDataList().size(), 5);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupFileName()
     */
    @Override
    public String getBackupFileName() {
        return "rstockProductDetail.xml";
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getDataFileName()
     */
    @Override
    public String[] getDataFileName() {
        return new String[] { "rstockProductDetail.xml" };
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupTableName()
     */
    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_stock_product_detail" };
    }
}
