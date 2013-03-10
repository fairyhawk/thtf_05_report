package cn.com.thtf.cms.report.serviceimpl;

import java.io.IOException;
import java.math.BigDecimal;
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
import cn.com.thtf.cms.report.dto.RBuyContractDetailDto;
import cn.com.thtf.cms.report.entity.RBuyContractDetailEntity;
import cn.com.thtf.cms.report.service.RBuyContractDetailService;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

/**
 * 采购合同明细Service测试
 * 
 * @author HanHaiyun
 * 
 */
public class BuyContractDetailServiceImplTest extends BaseTestNG implements NeedDBUnit {

    private RBuyContractDetailService rBuyContractDetailService = null;
    private IDatabaseConnection conn;
    private static final String tableName = "r_buy_contract_detail";
    private String[] fields = { "buy_contract_id           AS buyContractId",
            "product_contract_code       AS productContractCode",
            "company_contract_code       AS companyContractCode",
            "supplier_name               AS supplierName",
            "product_type_name           AS productTypeName",
            "product_code                AS productCode",
            "product_name                AS productName",
            "product_type                AS productType",
            "product_unit                AS productUnit",
            "buy_contract_detail_count   AS buyContractDetailCount",
            "buy_contract_price          AS buyContractPrice",
            "buy_contract_money          AS buyContractMoney",
            "in_stock_money              AS inStockMoney",
            "in_stock_count              AS inStockCount",
            "appoint_money               AS appointMoney",
            "invoice_money               AS invoiceMoney",
            "buy_back_contract_money     AS buyBackContractMoney",
            "buy_back_contract_count     AS buyBackContractCount",
            "buy_back_goods_money        AS buyBackGoodsMoney",
            "buy_back_goods_count        AS buyBackGoodsCount",
            "fact_in_stock_count         AS factInStockCount",
            "fact_in_stock_money         AS factInStockMoney",
            "not_in_stock_count          AS notInStockCount", "text", "date",
            "fact_contract_count         AS factContractCount",
            "request_date                AS requestDate"};

    @BeforeClass
    public void setUp() {
        rBuyContractDetailService = Container.getBean("rBuyContractDetailService",
                RBuyContractDetailService.class);
        try {
            // 获取连接
            conn = new DatabaseConnection(Container.getBean("sqlMapClient",
                    SqlMapClient.class).getDataSource().getConnection());
            // 备份数据
            QueryDataSet queryDataset = new QueryDataSet(conn, false);
            queryDataset.addTable(tableName);

            // 获取数据源 插入数据库
            DatabaseOperation.CLEAN_INSERT.execute(conn, getDataSet());
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBuyContractDaoImpl001() {
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);
        queryParam.setQuery(new RBuyContractDetailEntity());
        testData(queryParam);
    }

    /**
     * 根据采购合同编号检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl002() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setBuyContractId("CG100727001");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 根据产品合同号检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl003() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setProductContractCode("CG451000001");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 根据公司合同号检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl004() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setCompanyContractCode("CG2010-02983-WL");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 根据供货商名称检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl005() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setSupplierName("北京广源嘉adf信科技有限公司");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 根据产品名称检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl006() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setProductName("1061004CSL/18290192");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 根据产品编码检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl007() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setProductCode("01.001.0001");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 根据规格型号检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl008() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setProductType("线");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 根据产品类型名称检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl009() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setProductTypeName("康普产品");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 部分列组合检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl010() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setProductContractCode("CG451000001");
        entity.setBuyContractId("CG100727001");
        entity.setCompanyContractCode("CG2010-02983-WL");
        entity.setProductName("1061004CSL/18290192");
        entity.setSupplierName("北京广源嘉adf信科技有限公司");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 全部组合检索 支持模糊查询
     */
    @Test
    public void testBuyContractDaoImpl011() {
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setProductContractCode("CG451000001");
        entity.setBuyContractId("CG100727001");
        entity.setCompanyContractCode("CG2010-02983-WL");
        entity.setProductTypeName("康普产品");
        entity.setProductType("线");
        entity.setProductCode("01.001.0001");
        entity.setProductName("1061004CSL/18290192");
        entity.setSupplierName("北京广源嘉adf信科技有限公司");
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        testData(queryParam);
    }

    /**
     * 单列检索 支持模糊查询 检测各种金额和是否正确
     */
    @Test
    public void testBuyContractDaoImpl012() {
        String buyContractId = "CG100727001";
        BigDecimal sumBuyContractMoney = new BigDecimal("0");
        BigDecimal sumInStockMoney = new BigDecimal("0");
        BigDecimal sumAppointMoney = new BigDecimal("0");
        BigDecimal sumInvoiceMoney = new BigDecimal("0");
        BigDecimal sumBuyBackContractMoney = new BigDecimal("0");
        BigDecimal sumBuyBackGoodsMoney = new BigDecimal("0");
        BigDecimal sumFactInStockMoney = new BigDecimal("0");
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setBuyContractId(buyContractId);
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        RBuyContractDetailDto entitys = rBuyContractDetailService
                .getBuyContractDeatilSum(queryParam);
        try {
            ITable table = getDataSet().getTable(tableName);
            for (int i = 0; i < table.getRowCount(); i++) {
                if (((String) table.getValue(i, "buy_contract_id"))
                        .indexOf(buyContractId) >= 0) {
                    sumBuyContractMoney = sumBuyContractMoney.add(new BigDecimal(
                            (String) table.getValue(i, "buy_contract_money")));
                    sumInStockMoney = sumInStockMoney.add(new BigDecimal((String) table
                            .getValue(i, "in_stock_money")));
                    sumAppointMoney = sumAppointMoney.add(new BigDecimal((String) table
                            .getValue(i, "appoint_money")));
                    sumInvoiceMoney = sumInvoiceMoney.add(new BigDecimal((String) table
                            .getValue(i, "invoice_money")));
                    sumBuyBackContractMoney = sumBuyBackContractMoney.add(new BigDecimal(
                            (String) table.getValue(i, "buy_back_contract_money")));
                    sumBuyBackGoodsMoney = sumBuyBackGoodsMoney.add(new BigDecimal(
                            (String) table.getValue(i, "buy_back_goods_money")));
                    sumFactInStockMoney = sumFactInStockMoney.add(new BigDecimal(
                            (String) table.getValue(i, "fact_in_stock_money")));
                }
            }
            Assert.assertEquals(entitys.getSumAppointMoney(), sumAppointMoney);
            Assert.assertEquals(entitys.getSumBuyBackContractMoney(),
                    sumBuyBackContractMoney);
            Assert.assertEquals(entitys.getSumBuyBackGoodsMoney(), sumBuyBackGoodsMoney);
            Assert.assertEquals(entitys.getSumBuyContractMoney(), sumBuyContractMoney);
            Assert.assertEquals(entitys.getSumFactInStockMoney(), sumFactInStockMoney);
            Assert.assertEquals(entitys.getSumInStockMoney(), sumInStockMoney);
            Assert.assertEquals(entitys.getSumInvoiceMoney(), sumInvoiceMoney);

        } catch (DataSetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 多列检索 支持模糊查询 检测各种金额和是否正确
     */
    @Test
    public void testBuyContractDaoImpl013() {
        String buyContractId = "CG100727001";
        String companyContractCode = "CG2010-02983-WL";
        String productTypeName = "康普产品";
        BigDecimal sumBuyContractMoney = new BigDecimal("0");
        BigDecimal sumInStockMoney = new BigDecimal("0");
        BigDecimal sumAppointMoney = new BigDecimal("0");
        BigDecimal sumInvoiceMoney = new BigDecimal("0");
        BigDecimal sumBuyBackContractMoney = new BigDecimal("0");
        BigDecimal sumBuyBackGoodsMoney = new BigDecimal("0");
        BigDecimal sumFactInStockMoney = new BigDecimal("0");
        RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
        entity.setBuyContractId(buyContractId);
        entity.setCompanyContractCode(companyContractCode);
        entity.setProductTypeName(productTypeName);
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setPage(10);
        queryParam.setRows(10);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111111111", fields);

        queryParam.setQuery(entity);
        RBuyContractDetailDto entitys = rBuyContractDetailService
                .getBuyContractDeatilSum(queryParam);
        try {
            ITable table = getDataSet().getTable(tableName);
            for (int i = 0; i < table.getRowCount(); i++) {
                if (((String) table.getValue(i, "buy_contract_id"))
                        .indexOf(buyContractId) >= 0
                        && ((String) table.getValue(i, "product_type_name"))
                                .indexOf(productTypeName) >= 0
                        && ((String) table.getValue(i, "company_contract_code"))
                                .indexOf(companyContractCode) >= 0) {
                    sumBuyContractMoney = sumBuyContractMoney.add(new BigDecimal(
                            (String) table.getValue(i, "buy_contract_money")));
                    sumInStockMoney = sumInStockMoney.add(new BigDecimal((String) table
                            .getValue(i, "in_stock_money")));
                    sumAppointMoney = sumAppointMoney.add(new BigDecimal((String) table
                            .getValue(i, "appoint_money")));
                    sumInvoiceMoney = sumInvoiceMoney.add(new BigDecimal((String) table
                            .getValue(i, "invoice_money")));
                    sumBuyBackContractMoney = sumBuyBackContractMoney.add(new BigDecimal(
                            (String) table.getValue(i, "buy_back_contract_money")));
                    sumBuyBackGoodsMoney = sumBuyBackGoodsMoney.add(new BigDecimal(
                            (String) table.getValue(i, "buy_back_goods_money")));
                    sumFactInStockMoney = sumFactInStockMoney.add(new BigDecimal(
                            (String) table.getValue(i, "fact_in_stock_money")));
                }
            }
            Assert.assertEquals(entitys.getSumAppointMoney(), sumAppointMoney);
            Assert.assertEquals(entitys.getSumBuyBackContractMoney(),
                    sumBuyBackContractMoney);
            Assert.assertEquals(entitys.getSumBuyBackGoodsMoney(), sumBuyBackGoodsMoney);
            Assert.assertEquals(entitys.getSumBuyContractMoney(), sumBuyContractMoney);
            Assert.assertEquals(entitys.getSumFactInStockMoney(), sumFactInStockMoney);
            Assert.assertEquals(entitys.getSumInStockMoney(), sumInStockMoney);
            Assert.assertEquals(entitys.getSumInvoiceMoney(), sumInvoiceMoney);

        } catch (DataSetException e) {
            e.printStackTrace();
        }

    }

    // 获取xml文件中的数据
    private IDataSet getDataSet() {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        IDataSet dataSet = null;
        try {
            dataSet = builder.build(Container.getApplicationContext().getResource(
                    "classpath:dbunit/rBuyContractDetail.xml").getInputStream());
        } catch (DataSetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    @Override
    public String getBackupFileName() {
        return "rBuyContractDetail.xml";
    }

    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_buy_contract_detail" };

    }

    @Override
    public String[] getDataFileName() {
        return new String[] { "rBuyContractDetail.xml" };
    }

    @SuppressWarnings("unchecked")
    private void testData(BaseQueryData<RBuyContractDetailEntity> queryParam) {
        rBuyContractDetailService.getBuyContractDetailList(queryParam);
        List<RBuyContractDetailEntity> entitys = (List<RBuyContractDetailEntity>) queryParam
                .getDataList();
        try {
            ITable table = getDataSet().getTable(tableName);

            for (int i = 0; i < entitys.size(); i++) {
                RBuyContractDetailEntity entity = new RBuyContractDetailEntity();
                entity.setAppointMoney(new BigDecimal((String) table.getValue(i,
                        "appoint_money")));
                entity.setBuyBackContractCount(new Integer((String) table.getValue(i,
                        "buy_back_contract_count")));
                entity.setBuyBackContractMoney(new BigDecimal((String) table.getValue(i,
                        "buy_back_contract_money")));
                entity.setBuyBackGoodsCount(new Integer((String) table.getValue(i,
                        "buy_back_goods_count")));
                entity.setBuyBackGoodsMoney(new BigDecimal((String) table.getValue(i,
                        "buy_back_goods_money")));
                entity.setBuyContractDetailCount(new Integer((String) table.getValue(i,
                        "buy_contract_detail_count")));
                entity.setBuyContractId((String) table.getValue(i, "buy_contract_id"));
                entity.setBuyContractMoney(new BigDecimal((String) table.getValue(i,
                        "buy_contract_money")));
                entity.setBuyContractPrice(new BigDecimal((String) table.getValue(i,
                        "buy_contract_price")));
                entity.setCompanyContractCode((String) table.getValue(i,
                        "company_contract_code"));
                entity.setDate((String) table.getValue(i, "date"));
                entity.setFactInStockCount(new Integer((String) table.getValue(i,
                        "fact_in_stock_count")));
                entity.setFactInStockMoney(new BigDecimal((String) table.getValue(i,
                        "fact_in_stock_money")));
                entity.setInStockCount(new Integer((String) table.getValue(i,
                        "in_stock_count")));
                entity.setInStockMoney(new BigDecimal((String) table.getValue(i,
                        "in_stock_money")));
                entity.setInvoiceMoney(new BigDecimal((String) table.getValue(i,
                        "invoice_money")));
                entity.setNotInStockCount(new Integer((String) table.getValue(i,
                        "not_in_stock_count")));
                entity.setProductCode((String) table.getValue(i, "product_code"));
                entity.setProductContractCode((String) table.getValue(i,
                        "product_contract_code"));
                entity.setProductName((String) table.getValue(i, "product_name"));
                entity.setProductType((String) table.getValue(i, "product_type"));
                entity
                        .setProductTypeName((String) table.getValue(i,
                                "product_type_name"));
                entity.setProductUnit((String) table.getValue(i, "product_unit"));
                entity.setSupplierName((String) table.getValue(i, "supplier_name"));
                entity.setText((String) table.getValue(i, "text"));
                entity.setFactContractCount(new Integer((String) table.getValue(i,
                        "fact_contract_count")));
                entity.setRequestDate((String)table.getValue(i, "request_date"));
                Assert.assertEquals(entitys.get(i), entity);
            }
        } catch (DataSetException e) {
            e.printStackTrace();
        }
    }
}
