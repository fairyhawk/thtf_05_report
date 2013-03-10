/**
 * ClassName  StockProductDetailDaoImplTest
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-1-14
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.daoimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import cn.com.thtf.cms.report.dao.StockProductDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.StockProductDetailDto;
import cn.com.thtf.cms.report.entity.StockProductDetailEntity;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * StockProductDetailDaoImplTest
 * 
 * @author Lubo
 */
public class StockProductDetailDaoImplTest extends BaseTestNG implements NeedDBUnit {

    /** StockProductDetailDao */
    private StockProductDetailDao dao = null;
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
            /* 获得Dao */
            dao = Container.getBean("stockProductDetailDao", StockProductDetailDao.class);
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

    /**
     * Test method for {@link
     * cn.com.thtf.cms.report.dao.StockProductDetailDaoImpl#
     * getStockProductDetail
     * (cn.com.thtf.cms.report.dto.BaseQueryData<StockProductDetailEntity>)} .
     */
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

        List<StockProductDetailEntity> resultList = dao.getStockProductDetail(queryParam);

        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE_RSTOCKPRODUCTDETAIL);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getStockroomName(),
                    (String) table.getValue(i, "stockroom_name"));
            Assert.assertEquals(resultList.get(i).getProductTypeName(),
                    (String) table.getValue(i, "product_type_name"));
            Assert.assertEquals(resultList.get(i).getProductSerieName(),
                    (String) table.getValue(i, "product_serie_name"));
            Assert.assertEquals(resultList.get(i).getProductCode(),
                    (String) table.getValue(i, "product_code"));
            Assert.assertEquals(resultList.get(i).getProductName(),
                    (String) table.getValue(i, "product_name"));

            Assert.assertEquals(resultList.get(i).getProductType(),
                    (String) table.getValue(i, "product_type"));
            Assert.assertEquals(resultList.get(i).getProductUnit(),
                    (String) table.getValue(i, "product_unit"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomCount()),
                    (String) table.getValue(i, "stockroom_count"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomPrice()),
                    (String) table.getValue(i, "stockroom_price"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomMoney()),
                    (String) table.getValue(i, "stockroom_money"));
        }
    }

    /* 检索库房名称 */
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
        param.setStockroomName("1网络分销北京(商品)正常库");
        queryParam.setQuery(param);// 查询条件

        List<StockProductDetailEntity> resultList = dao.getStockProductDetail(queryParam);
        if (resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE_RSTOCKPRODUCTDETAIL);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getStockroomName(),
                    (String) table.getValue(i, "stockroom_name"));
            Assert.assertEquals(resultList.get(i).getProductTypeName(),
                    (String) table.getValue(i, "product_type_name"));
            Assert.assertEquals(resultList.get(i).getProductSerieName(),
                    (String) table.getValue(i, "product_serie_name"));
            Assert.assertEquals(resultList.get(i).getProductCode(),
                    (String) table.getValue(i, "product_code"));
            Assert.assertEquals(resultList.get(i).getProductName(),
                    (String) table.getValue(i, "product_name"));

            Assert.assertEquals(resultList.get(i).getProductType(),
                    (String) table.getValue(i, "product_type"));
            Assert.assertEquals(resultList.get(i).getProductUnit(),
                    (String) table.getValue(i, "product_unit"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomCount()),
                    (String) table.getValue(i, "stockroom_count"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomPrice()),
                    (String) table.getValue(i, "stockroom_price"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomMoney()),
                    (String) table.getValue(i, "stockroom_money"));
        }
    }

    /* 检索产品分类名称 */
    @Test
    public void testGetStockProductDetail003() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        param.setProductTypeName("康普产品");
        queryParam.setQuery(param);// 查询条件

        List<StockProductDetailEntity> resultList = dao.getStockProductDetail(queryParam);
        if (resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE_RSTOCKPRODUCTDETAIL);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getStockroomName(),
                    (String) table.getValue(i, "stockroom_name"));
            Assert.assertEquals(resultList.get(i).getProductTypeName(),
                    (String) table.getValue(i, "product_type_name"));
            Assert.assertEquals(resultList.get(i).getProductSerieName(),
                    (String) table.getValue(i, "product_serie_name"));
            Assert.assertEquals(resultList.get(i).getProductCode(),
                    (String) table.getValue(i, "product_code"));
            Assert.assertEquals(resultList.get(i).getProductName(),
                    (String) table.getValue(i, "product_name"));

            Assert.assertEquals(resultList.get(i).getProductType(),
                    (String) table.getValue(i, "product_type"));
            Assert.assertEquals(resultList.get(i).getProductUnit(),
                    (String) table.getValue(i, "product_unit"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomCount()),
                    (String) table.getValue(i, "stockroom_count"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomPrice()),
                    (String) table.getValue(i, "stockroom_price"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomMoney()),
                    (String) table.getValue(i, "stockroom_money"));
        }
    }

    /* 检索产品系列名称 */
    @Test
    public void testGetStockProductDetail004() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        param.setProductSerieName("工具");
        queryParam.setQuery(param);// 查询条件

        List<StockProductDetailEntity> resultList = dao.getStockProductDetail(queryParam);
        if (resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE_RSTOCKPRODUCTDETAIL);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getStockroomName(),
                    (String) table.getValue(i, "stockroom_name"));
            Assert.assertEquals(resultList.get(i).getProductTypeName(),
                    (String) table.getValue(i, "product_type_name"));
            Assert.assertEquals(resultList.get(i).getProductSerieName(),
                    (String) table.getValue(i, "product_serie_name"));
            Assert.assertEquals(resultList.get(i).getProductCode(),
                    (String) table.getValue(i, "product_code"));
            Assert.assertEquals(resultList.get(i).getProductName(),
                    (String) table.getValue(i, "product_name"));

            Assert.assertEquals(resultList.get(i).getProductType(),
                    (String) table.getValue(i, "product_type"));
            Assert.assertEquals(resultList.get(i).getProductUnit(),
                    (String) table.getValue(i, "product_unit"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomCount()),
                    (String) table.getValue(i, "stockroom_count"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomPrice()),
                    (String) table.getValue(i, "stockroom_price"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomMoney()),
                    (String) table.getValue(i, "stockroom_money"));
        }
    }

    /* 检索 产品编码 产品名称 规格型号 */
    @Test
    public void testGetStockProductDetail005() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        param.setProductCode("01.001.0001");
        param.setProductName("螺丝刀");
        param.setProductType("TF010001");
        queryParam.setQuery(param);// 查询条件

        List<StockProductDetailEntity> resultList = dao.getStockProductDetail(queryParam);
        if (resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE_RSTOCKPRODUCTDETAIL);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getStockroomName(),
                    (String) table.getValue(i, "stockroom_name"));
            Assert.assertEquals(resultList.get(i).getProductTypeName(),
                    (String) table.getValue(i, "product_type_name"));
            Assert.assertEquals(resultList.get(i).getProductSerieName(),
                    (String) table.getValue(i, "product_serie_name"));
            Assert.assertEquals(resultList.get(i).getProductCode(),
                    (String) table.getValue(i, "product_code"));
            Assert.assertEquals(resultList.get(i).getProductName(),
                    (String) table.getValue(i, "product_name"));

            Assert.assertEquals(resultList.get(i).getProductType(),
                    (String) table.getValue(i, "product_type"));
            Assert.assertEquals(resultList.get(i).getProductUnit(),
                    (String) table.getValue(i, "product_unit"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomCount()),
                    (String) table.getValue(i, "stockroom_count"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomPrice()),
                    (String) table.getValue(i, "stockroom_price"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomMoney()),
                    (String) table.getValue(i, "stockroom_money"));
        }
    }

    /* 检索 所有字段 */
    @Test
    public void testGetStockProductDetail006() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        param.setStockroomName("1网络分销北京(商品)正常库");
        param.setProductTypeName("康普产品");
        param.setProductSerieName("工具");
        param.setProductCode("01.001.0001");
        param.setProductName("螺丝刀");
        param.setProductType("TF010001");
        queryParam.setQuery(param);// 查询条件

        List<StockProductDetailEntity> resultList = dao.getStockProductDetail(queryParam);
        if (resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE_RSTOCKPRODUCTDETAIL);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getStockroomName(),
                    (String) table.getValue(i, "stockroom_name"));
            Assert.assertEquals(resultList.get(i).getProductTypeName(),
                    (String) table.getValue(i, "product_type_name"));
            Assert.assertEquals(resultList.get(i).getProductSerieName(),
                    (String) table.getValue(i, "product_serie_name"));
            Assert.assertEquals(resultList.get(i).getProductCode(),
                    (String) table.getValue(i, "product_code"));
            Assert.assertEquals(resultList.get(i).getProductName(),
                    (String) table.getValue(i, "product_name"));

            Assert.assertEquals(resultList.get(i).getProductType(),
                    (String) table.getValue(i, "product_type"));
            Assert.assertEquals(resultList.get(i).getProductUnit(),
                    (String) table.getValue(i, "product_unit"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomCount()),
                    (String) table.getValue(i, "stockroom_count"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomPrice()),
                    (String) table.getValue(i, "stockroom_price"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getStockroomMoney()),
                    (String) table.getValue(i, "stockroom_money"));
        }
    }

    /* 检索 合计值 所有列 */
    @Test
    public void testGetStockProductDetail007() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        param.setStockroomName("1网络分销北京(商品)正常库");
        param.setProductTypeName("康普产品");
        param.setProductSerieName("工具");
        param.setProductCode("01.001.0001");
        param.setProductName("螺丝刀");
        param.setProductType("TF010001");
        queryParam.setQuery(param);// 查询条件

        StockProductDetailDto detailEntity = dao.getStockProductDetailSumVal(queryParam);
        if (detailEntity == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(detailEntity.getProductCountSum(), "99");
        Assert.assertEquals(detailEntity.getStockroomMoneySum(), "10724.67");

    }

    /* 检索 合计值 产品分类 */
    @Test
    public void testGetStockProductDetail008() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        param.setProductTypeName("康普产品");
        queryParam.setQuery(param);// 查询条件

        StockProductDetailDto detailEntity = dao.getStockProductDetailSumVal(queryParam);
        if (detailEntity == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(detailEntity.getProductCountSum(), "142");
        Assert.assertEquals(detailEntity.getStockroomMoneySum(), "13003.67");

    }

    /* 检索 合计值 多列 */
    @Test
    public void testGetStockProductDetail009() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        param.setStockroomName("3网络分销上海(商品)正常库");
        param.setProductSerieName("饮品");
        param.setProductCode("01.002.0001");
        param.setProductName("蒜味咖啡");
        param.setProductType("TF020001");
        queryParam.setQuery(param);// 查询条件

        StockProductDetailDto detailEntity = dao.getStockProductDetailSumVal(queryParam);
        if (detailEntity == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(detailEntity.getProductCountSum(), "15623");
        Assert.assertEquals(detailEntity.getStockroomMoneySum(), "319490.35");

    }

    /* 检索 合计值 无数据 */
    @Test
    public void testGetStockProductDetail010() throws Exception {
        StockProductDetailEntity param = new StockProductDetailEntity();
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("stockroom_name");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111", fieldRows);// 显示的列
        param.setStockroomName("34网络分销上海(商品)正常库");
        param.setProductSerieName("饮品");
        param.setProductCode("01.002.0001");
        param.setProductName("蒜味咖啡");
        param.setProductType("TF020001");
        queryParam.setQuery(param);// 查询条件

        StockProductDetailDto detailEntity = dao.getStockProductDetailSumVal(queryParam);
        if (detailEntity == null) {
            Assert.assertTrue(true);
        }

    }

    /**
     * getDataSet
     * 
     * @return
     * @throws Exception
     */
    protected IDataSet getDataSet() throws Exception {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        IDataSet dataSet = builder
                .build(Container.getApplicationContext()
                        .getResource("classpath:dbunit/rstockProductDetail.xml")
                        .getInputStream());
        return dataSet;
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
