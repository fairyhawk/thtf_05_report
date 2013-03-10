package cn.com.thtf.cms.report.serviceimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.entity.RDynamicStocktakeEntity;
import cn.com.thtf.cms.report.service.RDynamicStocktakeService;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

public class DynamicStocktakeServiceImpl extends BaseTestNG implements NeedDBUnit {

    public RDynamicStocktakeService rDynamicStocktakeService = null;
    public IDatabaseConnection conn;
    private static final String tableName = "r_dynamic_stocktake";
    private String[] fields = { "stock_name as stockName", "product_code as productCode",
            "product_name as productName", "product_type as productType",
            "product_unit as productUnit", "stockroom_count as stockroomCount",
            "stock_num_sum as stockNumSum", "short_name as shortName",
            "stockroom_id as stockroomId", "product_id as productId" };

    @BeforeClass
    public void setUp() {
        rDynamicStocktakeService = Container.getBean("rDynamicStocktakeService",
                RDynamicStocktakeService.class);
        try {
            conn = new DatabaseConnection(Container.getBean("sqlMapClient",
                    SqlMapClient.class).getDataSource().getConnection());

            QueryDataSet queryData = new QueryDataSet(conn, false);
            queryData.addTable(tableName);

            DatabaseOperation.CLEAN_INSERT.execute(conn, getDataSet());
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化、无条件检索
     */
    @Test
    public void dynamicStocktakeDaoImplTest() {
        BaseQueryData<RDynamicStocktakeEntity> queryParam = new BaseQueryData<RDynamicStocktakeEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("stock_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fields);
        queryParam.setQuery(new RDynamicStocktakeEntity());
        testData(queryParam);
    }

    /**
     * 单列检索 支持模糊查询
     */
    @Test
    public void dynamicStocktakeDaoImplTest001() {
        RDynamicStocktakeEntity entity = new RDynamicStocktakeEntity();
        entity.setStockName("同方网络北京(产品)正常库");
        BaseQueryData<RDynamicStocktakeEntity> queryParam = new BaseQueryData<RDynamicStocktakeEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("stock_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fields);
        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 多列检索 支持模糊查询
     */
    @Test
    public void dynamicStocktakeDaoImplTest002() {
        RDynamicStocktakeEntity entity = new RDynamicStocktakeEntity();
        entity.setStockName("同方网络北京(产品)正常库");
        entity.setProductName("金属理线器");
        entity.setProductCode("03.004.0026");
        BaseQueryData<RDynamicStocktakeEntity> queryParam = new BaseQueryData<RDynamicStocktakeEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("stock_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fields);
        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 所有列检索 支持模糊查询
     */
    @Test
    public void dynamicStocktakeDaoImplTest003() {
        RDynamicStocktakeEntity entity = new RDynamicStocktakeEntity();
        entity.setStockName("同方网络北京(产品)正常库");
        entity.setProductName("金属理线器");
        entity.setProductCode("03.004.0026");
        entity.setProductType("CP200L");
        entity.setShortName("北京");
        entity.setProductUnit("个");
        BaseQueryData<RDynamicStocktakeEntity> queryParam = new BaseQueryData<RDynamicStocktakeEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("stock_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fields);
        queryParam.setQuery(entity);
        testData(queryParam);
    }

    @SuppressWarnings("unchecked")
    private void testData(BaseQueryData<RDynamicStocktakeEntity> queryParam) {
        rDynamicStocktakeService.getStocktakeList(queryParam);
        List<RDynamicStocktakeEntity> entitys = (List<RDynamicStocktakeEntity>) queryParam
                .getDataList();
        try {
            ITable table = getDataSet().getTable(tableName);
            for (int i = 0; i < entitys.size(); i++) {
                RDynamicStocktakeEntity entity = new RDynamicStocktakeEntity();
                entity.setProductCode((String) table.getValue(i, "product_code"));
                entity
                        .setProductId(new Integer((String) table
                                .getValue(i, "product_id")));
                entity.setProductName((String) table.getValue(i, "product_name"));
                entity.setProductType((String) table.getValue(i, "product_type"));
                entity.setProductUnit((String) table.getValue(i, "product_unit"));
                entity.setShortName((String) table.getValue(i, "short_name"));
                entity.setStockName((String) table.getValue(i, "stock_name"));
                entity.setStockNumSum(new Integer((String) table.getValue(i,
                        "stock_num_sum")));
                entity.setStockroomCount(new Integer((String) table.getValue(i,
                        "stockroom_count")));
                entity.setStockroomId(new Integer((String) table.getValue(i,
                        "stockroom_id")));
                Assert.assertEquals(entity, entitys.get(i));
            }
        } catch (DataSetException e) {
            e.printStackTrace();
        }
    }

    private IDataSet getDataSet() {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        IDataSet dataSet = null;
        try {
            dataSet = builder.build(Container.getApplicationContext().getResource(
                    "classpath:dbunit/rDynamicStocktake.xml").getInputStream());
        } catch (DataSetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    @Override
    public String getBackupFileName() {
        return "rDynamicStocktake.xml";
    }

    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_dynamic_stocktake" };
    }

    @Override
    public String[] getDataFileName() {
        return new String[] { "rDynamicStocktake.xml" };
    }

}
