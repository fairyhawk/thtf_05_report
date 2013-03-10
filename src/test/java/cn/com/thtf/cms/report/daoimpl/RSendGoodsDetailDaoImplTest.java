/**
 * ClassName  RSendGoodsDetailDaoImplTest
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-18
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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.com.thtf.cms.report.dao.RSendGoodsDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSendGoodsDetailDto;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author hanrb
 */

public class RSendGoodsDetailDaoImplTest extends BaseTestNG implements NeedDBUnit {

    /** StockProductDetailDao */
    private RSendGoodsDetailDao dao = null;
    /** DB连接 */
    private static IDatabaseConnection conn;
    /** 表 */
    private static final String TABLE = "r_send_goods_detail";
    /** 显示的列 */
    private String fieldRows[] = { "sell_contract_id as sellContractId",
            "product_contract_code as productContractCode",
            "company_contract_code as companyContractCode",
            "send_goods_id as sendGoodsId",
            "send_goods_status_name as sendGoodsStatusName",
            "send_goods_type_name as sendGoodsTypeName",
            "product_type_name as productTypeName", "customer_name as customerName",
            "request_date as requestDate", "send_date as sendDate",
            "request_reach_date as requestReachDate", "stockroom_name as stockroomName",
            "product_name as productName", "product_code as productCode",
            "product_type as productType", "product_unit as productUnit",
            "product_money_tax as productMoneyTax", "detail_count as detailCount",
            "detail_count_rel as detailCountRel", "detail_money_rel as detailMoneyRel",
            "detail_money as detailMoney", "back_count as backCount",
            "back_money as backMoney", "detail_price as detailPrice",
            "product_money_cost as productMoneyCost",
            "product_money_profit as productMoneyProfit",
            "product_money_profitrate as productMoneyProfitrate",
            "appoint_money as appointMoney", "appoint_money_otw as appointMoneyOtw",
            "appoint_money_ext as appointMoneyExt", "user_name as userName",
            "make_invoice_money as makeInvoiceMoney", "datetime", "text",
            "user_area_name as userAreaName",
            "product_money_cost_tax as productMoneyCostTax",
            "product_money_profit_tax as productMoneyProfitTax",
            "customer_province as customerProvince", "customer_city as customerCity",
            "customer_area as customerArea", "contract_Pro_Name as contractProName",
            "ca_name as caName" };

    /**
     * 初始化
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public void setUp() {
        try {
            /* 获得Dao */
            dao = Container.getBean("sendGoodsDetailDao", RSendGoodsDetailDao.class);
            /* 获得连接 */

            conn = new DatabaseConnection(Container
                    .getBean("sqlMapClient", SqlMapClient.class).getDataSource()
                    .getConnection());

            /* 获得需要备份表 */
            QueryDataSet tmpDataSet = new QueryDataSet(conn, false);
            tmpDataSet.addTable(TABLE);

            /* 从XML读取数据 */
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            IDataSet expectDataSet = builder.build(Container.getApplicationContext()
                    .getResource("classpath:dbunit/rsendgoodsDetail.xml")
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
    public void testSendGoodsDetail001() throws Exception {
        RSendGoodsDetailDto param = new RSendGoodsDetailDto();
        BaseQueryData<RSendGoodsDetailDto> queryParam = new BaseQueryData<RSendGoodsDetailDto>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("send_Goods_Id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("11111111111111111111111111111111111111111", fieldRows);// 显示的列
        queryParam.setQuery(param);// 查询条件
        List<RSendGoodsDetailDto> resultList = (List<RSendGoodsDetailDto>) dao
                .queryRecords(queryParam);

        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getSellContractId(),
                    (String) table.getValue(i, "sell_contract_id"));
            Assert.assertEquals(resultList.get(i).getProductContractCode(),
                    (String) table.getValue(i, "product_contract_code"));
            Assert.assertEquals(resultList.get(i).getCompanyContractCode(),
                    (String) table.getValue(i, "company_contract_code"));
            Assert.assertEquals(resultList.get(i).getSendGoodsId(),
                    (String) table.getValue(i, "send_goods_id"));
            Assert.assertEquals(resultList.get(i).getSendGoodsStatusName(),
                    (String) table.getValue(i, "send_goods_status_name"));
            Assert.assertEquals(resultList.get(i).getSendGoodsTypeName(),
                    (String) table.getValue(i, "send_goods_type_name"));
            Assert.assertEquals(resultList.get(i).getProductTypeName(),
                    (String) table.getValue(i, "product_type_name"));
            Assert.assertEquals(resultList.get(i).getCustomerName(),
                    (String) table.getValue(i, "customer_name"));
            Assert.assertEquals(resultList.get(i).getRequestDate(),
                    (String) table.getValue(i, "request_date"));
            Assert.assertEquals(resultList.get(i).getSendDate(),
                    (String) table.getValue(i, "send_date"));
            Assert.assertEquals(resultList.get(i).getRequestReachDate(),
                    (String) table.getValue(i, "request_reach_date"));
            Assert.assertEquals(resultList.get(i).getStockroomName(),
                    (String) table.getValue(i, "stockroom_name"));
            Assert.assertEquals(resultList.get(i).getProductCode(),
                    (String) table.getValue(i, "product_code"));
            Assert.assertEquals(resultList.get(i).getProductName(),
                    (String) table.getValue(i, "product_name"));
            Assert.assertEquals(resultList.get(i).getProductType(),
                    (String) table.getValue(i, "product_type"));
            Assert.assertEquals(resultList.get(i).getProductUnit(),
                    (String) table.getValue(i, "product_unit"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getProductMoneyTax()),
                    (String) table.getValue(i, "product_money_tax"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getDetailCount()),
                    (String) table.getValue(i, "detail_count"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getDetailCountRel()),
                    (String) table.getValue(i, "detail_count_rel"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getDetailMoney()),
                    (String) table.getValue(i, "detail_money"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getBackMoney()),
                    (String) table.getValue(i, "back_money"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getBackCount()),
                    (String) table.getValue(i, "back_count"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getDetailPrice()),
                    (String) table.getValue(i, "detail_price"));
            Assert.assertEquals(
                    String.valueOf(resultList.get(i).getProductMoneyProfit()),
                    (String) table.getValue(i, "product_money_profit"));
            Assert.assertEquals(
                    String.valueOf(resultList.get(i).getProductMoneyProfitrate()),
                    (String) table.getValue(i, "product_money_profitrate"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getProductMoneyCost()),
                    (String) table.getValue(i, "product_money_cost"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getAppointMoneyOtw()),
                    (String) table.getValue(i, "appoint_money_otw"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getAppointMoneyExt()),
                    (String) table.getValue(i, "appoint_money_ext"));
            Assert.assertEquals(resultList.get(i).getDatetime(),
                    (String) table.getValue(i, "datetime"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getMakeInvoiceMoney()),
                    (String) table.getValue(i, "make_invoice_money"));
            Assert.assertEquals(resultList.get(i).getUserName(),
                    (String) table.getValue(i, "user_name"));
            Assert.assertEquals(resultList.get(i).getCustomerArea(),
                    (String) table.getValue(i, "customer_area"));
            Assert.assertEquals(resultList.get(i).getCustomerCity(),
                    (String) table.getValue(i, "customer_city"));
            Assert.assertEquals(resultList.get(i).getCustomerProvince(),
                    (String) table.getValue(i, "customer_province"));
            Assert.assertEquals(
                    String.valueOf(resultList.get(i).getProductMoneyProfitTax()),
                    (String) table.getValue(i, "product_money_profit_tax"));
            Assert.assertEquals(
                    String.valueOf(resultList.get(i).getProductMoneyCostTax()),
                    (String) table.getValue(i, "product_money_cost_tax"));
            Assert.assertEquals(resultList.get(i).getUserAreaName(),
                    (String) table.getValue(i, "user_area_name"));
            Assert.assertEquals(resultList.get(i).getText(),
                    (String) table.getValue(i, "text"));
            Assert.assertEquals(resultList.get(i).getContractProName(),
                    (String) table.getValue(i, "contract_pro_name"));
            // Assert.assertEquals(resultList.get(i).getCaName(),(String)
            // table.getValue(i, "contract_pro_name"));
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
        IDataSet dataSet = builder.build(Container.getApplicationContext()
                .getResource("classpath:dbunit/rsendgoodsDetail.xml").getInputStream());
        return dataSet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupFileName()
     */
    @Override
    public String getBackupFileName() {
        return "rsendgoodsDetail.xml";
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getDataFileName()
     */
    @Override
    public String[] getDataFileName() {
        return new String[] { "rsendgoodsDetail.xml" };
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupTableName()
     */
    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_send_goods_detail" };
    }
}
