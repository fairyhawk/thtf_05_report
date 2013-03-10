/**
 * ClassName  SellContractDetailDaoImplTest
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.daoimpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.com.thtf.cms.report.dao.RSellContractDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSellContractDetailDto;
import cn.com.thtf.cms.report.entity.RSellContractDetailEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

/**
 * 
 * @author liuqg
 */

public class SellContractDetailDaoImplTest extends BaseTestNG implements NeedDBUnit {

    private RSellContractDetailDao dao = null;
    /** DB连接 */
    private  IDatabaseConnection conn;
    /* private static IDataSet backupDataSet; */
    private CommonService commonService;
    /** 表 */
    private static final String TABLE_RSellContractDdtail = "r_Sell_Contract_Detail";
    /** 显示的列 */
    private String fieldRows[] = { "sell_contract_id as sell_contract_id",
            "product_contract_code as product_contract_code",
            "company_contract_code as company_contract_code",
            "status_name as status_name", "money as money","customer_name as customer_name",
            "product_code as product_code", "product_name as product_name",
            "product_type as product_type", "product_unit as product_unit",
            "count as count", "price as price", "sell_money as sell_money",
            "product_limit_price as product_limit_price",
            "send_goods_money as send_goods_money",
            "fact_send_goods_money as fact_send_goods_money",
            "send_goods_count as send_goods_count",
            "fact_send_goods_count as fact_send_goods_count",
            "prepare_goods as prepare_goods", "stock_goods_count as stock_goods_count",
            "appoint_money as appoint_money",
            "in_transit_appoint_money as in_transit_appoint_money",
            "sell_invoice_money as sell_invoice_money",
            "back_contract_money as back_contract_money",
            "back_contract_count as back_contract_count", "back_money as back_money",
            "back_count as back_count", "fact_sell_count as fact_sell_count",
            "contract_not_implemented_count as contract_not_implemented_count" };

    @BeforeClass
    public void beforeClass() {
        try {
            dao = Container
                    .getBean("sellContractDetailDao", RSellContractDetailDao.class);
            conn = new DatabaseConnection(Container
                    .getBean("sqlMapClient", SqlMapClient.class).getDataSource()
                    .getConnection());
            commonService = Container.getBean("commonService", CommonService.class);
            /* 获得需要备份表 */
            QueryDataSet tmpDataSet = new QueryDataSet(conn, false);
            tmpDataSet.addTable(TABLE_RSellContractDdtail);

            /*
             * backupDataSet = new DefaultDataSet( new ITable[] {
             * tmpDataSet.getTable(TABLE_RSellContractDdtail) });
             */

            /* 从XML读取数据 */
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            IDataSet expectDataSet = builder.build(Container.getApplicationContext()
                    .getResource("classpath:dbunit/r_sell_contract_detail.xml")
                    .getInputStream());

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
     * 全部字段
     */
    @Test
    public void testSellContractdetail001() {
        RSellContractDetailDto param = new RSellContractDetailDto();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();

        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 28列
        queryParam.setQuery(param);
        queryParam.setPage(10);
        queryParam.setRows(3);
        queryParam.setSord("");
        queryParam.setSidx("sell_contract_id");
        queryParam.setStar(0);

        /*
         * 1 管理员 2 信息管理员 3 销售助理 4 销售经理 5 销售总监 6 信用专员 7 信用主管 8 采购专员 9 区域总监 10
         * 产品总监 11 采购主管 12 库房管理员 13 库房主管 14 物流管理员 15 法务专员 16 运营总监助理 17 运营总监 18
         * 总经理
         */
        /*
         * UserEntity user = new UserEntity(); user.setId("lishimei");
         * user.setRoleId(4);
         * 
         * commonService.getUserFileParam(user, queryParam);
         */
        List<RSellContractDetailEntity> resultList = dao.queryRecords(queryParam);

        IDataSet dataset;
        ITable tableXml;
        try {
            dataset = getDataSet();
            /* 获得XML中的数据 */
            tableXml = dataset.getTable(TABLE_RSellContractDdtail);
            for (int i = 0; i < resultList.size(); i++) {
                Assert.assertEquals(resultList.get(i).getSell_contract_id(),
                        (String) tableXml.getValue(i, "sell_contract_id"));
                Assert.assertEquals(resultList.get(i).getProduct_contract_code(),
                        (String) tableXml.getValue(i, "product_contract_code"));
                Assert.assertEquals(resultList.get(i).getCompany_contract_code(),
                        (String) tableXml.getValue(i, "company_contract_code"));
                Assert.assertEquals(resultList.get(i).getStatus_name(),
                        (String) tableXml.getValue(i, "status_name"));
                Assert.assertEquals(
                        resultList.get(i).getMoney().setScale(2, RoundingMode.HALF_UP),
                        new BigDecimal((String) tableXml.getValue(i, "money")).setScale(
                                2, RoundingMode.HALF_UP)

                );
                Assert.assertEquals(resultList.get(i).getProduct_code(),
                        (String) tableXml.getValue(i, "product_code"));
                Assert.assertEquals(resultList.get(i).getProduct_name(),
                        (String) tableXml.getValue(i, "product_name"));
                Assert.assertEquals(resultList.get(i).getProduct_type(),
                        (String) tableXml.getValue(i, "product_type"));
                Assert.assertEquals(resultList.get(i).getProduct_unit(),
                        (String) tableXml.getValue(i, "product_unit"));
                Assert.assertEquals(resultList.get(i).getCount(), new Integer(
                        (String) tableXml.getValue(i, "count")));
                Assert.assertEquals(resultList.get(i).getPrice(), new BigDecimal(
                        (String) tableXml.getValue(i, "price")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSell_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "sell_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getProduct_limit_price(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "product_limit_price")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i, "send_goods_money"))
                                .setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "fact_send_goods_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_count(), new Integer(
                        (String) tableXml.getValue(i, "send_goods_count")));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_count(),
                        new Integer((String) tableXml
                                .getValue(i, "fact_send_goods_count")));
                Assert.assertEquals(resultList.get(i).getPrepare_goods(), new BigDecimal(
                        (String) tableXml.getValue(i, "prepare_goods")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getStock_goods_count(),
                        new Integer((String) tableXml.getValue(i, "stock_goods_count")));
                Assert.assertEquals(resultList.get(i).getAppoint_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "appoint_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getIn_transit_appoint_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "in_transit_appoint_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getSell_invoice_money(),
                        new BigDecimal((String) tableXml
                                .getValue(i, "sell_invoice_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getBack_contract_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "back_contract_money")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_contract_count(),
                        new Integer((String) tableXml.getValue(i, "back_contract_count")));
                Assert.assertEquals(resultList.get(i).getBack_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "back_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_count(), new Integer(
                        (String) tableXml.getValue(i, "back_count")));
                Assert.assertEquals(resultList.get(i).getFact_sell_count(), new Integer(
                        (String) tableXml.getValue(i, "fact_sell_count")));
                Assert.assertEquals(
                        resultList.get(i).getContract_not_implemented_count(),
                        new Integer((String) tableXml.getValue(i,
                                "contract_not_implemented_count")));
                /*
                 * Assert.assertEquals("product_type_id:"+resultList.get(i).
                 * getProduct_type_id(), new Integer( (String)
                 * tableXml.getValue(i, "product_type_id")));
                 * Assert.assertEquals(resultList.get(i).getProduct_type_name(),
                 * (String) tableXml.getValue(i, "product_type_name"));
                 */

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 检索销售合同ID
     */
    @Test
    public void testSellContractdetail002() {
        RSellContractDetailDto param = new RSellContractDetailDto();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        String testSell_contract_id = "XS111111112";
        param.setSell_contract_id(testSell_contract_id);

        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 28列

        queryParam.setQuery(param);
        queryParam.setPage(10);
        queryParam.setRows(3);
        queryParam.setSord("");
        queryParam.setSidx("sell_contract_id");
        queryParam.setStar(0);

        /*
         * 1 管理员 2 信息管理员 3 销售助理 4 销售经理 5 销售总监 6 信用专员 7 信用主管 8 采购专员 9 区域总监 10
         * 产品总监 11 采购主管 12 库房管理员 13 库房主管 14 物流管理员 15 法务专员 16 运营总监助理 17 运营总监 18
         * 总经理
         */
        UserEntity user = new UserEntity();
        user.setId("lishimei");
        user.setRoleId(4);

        commonService.getUserFileParam(user, queryParam);
        List<RSellContractDetailEntity> resultList = dao.queryRecords(queryParam);

        IDataSet dataset;
        ITable tableXml;
        try {
            dataset = getDataSet();
            /* 获得XML中的数据 */
            tableXml = dataset.getTable(TABLE_RSellContractDdtail);
            for (int i = 0; i < resultList.size(); i++) {
                Assert.assertEquals(resultList.get(i).getSell_contract_id(),
                        (String) tableXml.getValue(i, "sell_contract_id"));
                Assert.assertEquals(resultList.get(i).getProduct_contract_code(),
                        (String) tableXml.getValue(i, "product_contract_code"));
                Assert.assertEquals(resultList.get(i).getCompany_contract_code(),
                        (String) tableXml.getValue(i, "company_contract_code"));
                Assert.assertEquals(resultList.get(i).getStatus_name(),
                        (String) tableXml.getValue(i, "status_name"));
                Assert.assertEquals(
                        resultList.get(i).getMoney().setScale(2, RoundingMode.HALF_UP),
                        new BigDecimal((String) tableXml.getValue(i, "money")).setScale(
                                2, RoundingMode.HALF_UP)

                );
                Assert.assertEquals(resultList.get(i).getProduct_code(),
                        (String) tableXml.getValue(i, "product_code"));
                Assert.assertEquals(resultList.get(i).getProduct_name(),
                        (String) tableXml.getValue(i, "product_name"));
                Assert.assertEquals(resultList.get(i).getProduct_type(),
                        (String) tableXml.getValue(i, "product_type"));
                Assert.assertEquals(resultList.get(i).getProduct_unit(),
                        (String) tableXml.getValue(i, "product_unit"));
                Assert.assertEquals(resultList.get(i).getCount(), new Integer(
                        (String) tableXml.getValue(i, "count")));
                Assert.assertEquals(resultList.get(i).getPrice(), new BigDecimal(
                        (String) tableXml.getValue(i, "price")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSell_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "sell_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getProduct_limit_price(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "product_limit_price")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i, "send_goods_money"))
                                .setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "fact_send_goods_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_count(), new Integer(
                        (String) tableXml.getValue(i, "send_goods_count")));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_count(),
                        new Integer((String) tableXml
                                .getValue(i, "fact_send_goods_count")));
                Assert.assertEquals(resultList.get(i).getPrepare_goods(), new BigDecimal(
                        (String) tableXml.getValue(i, "prepare_goods")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getStock_goods_count(),
                        new Integer((String) tableXml.getValue(i, "stock_goods_count")));
                Assert.assertEquals(resultList.get(i).getAppoint_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "appoint_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getIn_transit_appoint_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "in_transit_appoint_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getSell_invoice_money(),
                        new BigDecimal((String) tableXml
                                .getValue(i, "sell_invoice_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getBack_contract_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "back_contract_money")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_contract_count(),
                        new Integer((String) tableXml.getValue(i, "back_contract_count")));
                Assert.assertEquals(resultList.get(i).getBack_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "back_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_count(), new Integer(
                        (String) tableXml.getValue(i, "back_count")));
                Assert.assertEquals(resultList.get(i).getFact_sell_count(), new Integer(
                        (String) tableXml.getValue(i, "fact_sell_count")));
                Assert.assertEquals(
                        resultList.get(i).getContract_not_implemented_count(),
                        new Integer((String) tableXml.getValue(i,
                                "contract_not_implemented_count")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 检索产品合同号
     */
    @Test
    public void testSellContractdetail003() {
        RSellContractDetailDto param = new RSellContractDetailDto();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        String product_contract_code = "XS031000001";
        param.setProduct_contract_code(product_contract_code);

        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 28列

        queryParam.setQuery(param);
        queryParam.setPage(10);
        queryParam.setRows(3);
        queryParam.setSord("");
        queryParam.setSidx("sell_contract_id");
        queryParam.setStar(0);

        List<RSellContractDetailEntity> resultList = dao.queryRecords(queryParam);
        if (resultList == null || resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        IDataSet dataset;
        ITable tableXml;
        try {
            dataset = getDataSet();
            /* 获得XML中的数据 */
            tableXml = dataset.getTable(TABLE_RSellContractDdtail);
            for (int i = 0; i < resultList.size(); i++) {
                Assert.assertEquals(resultList.get(i).getSell_contract_id(),
                        (String) tableXml.getValue(i, "sell_contract_id"));
                Assert.assertEquals(resultList.get(i).getProduct_contract_code(),
                        (String) tableXml.getValue(i, "product_contract_code"));
                Assert.assertEquals(resultList.get(i).getCompany_contract_code(),
                        (String) tableXml.getValue(i, "company_contract_code"));
                Assert.assertEquals(resultList.get(i).getStatus_name(),
                        (String) tableXml.getValue(i, "status_name"));
                Assert.assertEquals(
                        resultList.get(i).getMoney().setScale(2, RoundingMode.HALF_UP),
                        new BigDecimal((String) tableXml.getValue(i, "money")).setScale(
                                2, RoundingMode.HALF_UP)

                );
                Assert.assertEquals(resultList.get(i).getProduct_code(),
                        (String) tableXml.getValue(i, "product_code"));
                Assert.assertEquals(resultList.get(i).getProduct_name(),
                        (String) tableXml.getValue(i, "product_name"));
                Assert.assertEquals(resultList.get(i).getProduct_type(),
                        (String) tableXml.getValue(i, "product_type"));
                Assert.assertEquals(resultList.get(i).getProduct_unit(),
                        (String) tableXml.getValue(i, "product_unit"));
                Assert.assertEquals(resultList.get(i).getCount(), new Integer(
                        (String) tableXml.getValue(i, "count")));
                Assert.assertEquals(resultList.get(i).getPrice(), new BigDecimal(
                        (String) tableXml.getValue(i, "price")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSell_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "sell_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getProduct_limit_price(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "product_limit_price")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i, "send_goods_money"))
                                .setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "fact_send_goods_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_count(), new Integer(
                        (String) tableXml.getValue(i, "send_goods_count")));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_count(),
                        new Integer((String) tableXml
                                .getValue(i, "fact_send_goods_count")));
                Assert.assertEquals(resultList.get(i).getPrepare_goods(), new BigDecimal(
                        (String) tableXml.getValue(i, "prepare_goods")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getStock_goods_count(),
                        new Integer((String) tableXml.getValue(i, "stock_goods_count")));
                Assert.assertEquals(resultList.get(i).getAppoint_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "appoint_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getIn_transit_appoint_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "in_transit_appoint_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getSell_invoice_money(),
                        new BigDecimal((String) tableXml
                                .getValue(i, "sell_invoice_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getBack_contract_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "back_contract_money")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_contract_count(),
                        new Integer((String) tableXml.getValue(i, "back_contract_count")));
                Assert.assertEquals(resultList.get(i).getBack_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "back_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_count(), new Integer(
                        (String) tableXml.getValue(i, "back_count")));
                Assert.assertEquals(resultList.get(i).getFact_sell_count(), new Integer(
                        (String) tableXml.getValue(i, "fact_sell_count")));
                Assert.assertEquals(
                        resultList.get(i).getContract_not_implemented_count(),
                        new Integer((String) tableXml.getValue(i,
                                "contract_not_implemented_count")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 检索 公司合同号 合同状态 产品名称 产品编码 规格型号
     */
    @Test
    public void testSellContractdetail004() {
        RSellContractDetailDto param = new RSellContractDetailDto();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();

        param.setProduct_contract_code("XS031000001");
        param.setCompany_contract_code("XS2010-13449-TW");
        param.setStatus("14");
        param.setProduct_name("超五类4对非屏蔽双绞线");
        param.setProduct_code("03.002.0001");
        param.setProduct_type("CC61004");

        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 28列

        queryParam.setQuery(param);
        queryParam.setPage(10);
        queryParam.setRows(3);
        queryParam.setSord("");
        queryParam.setSidx("sell_contract_id");
        queryParam.setStar(0);

        List<RSellContractDetailEntity> resultList = dao.queryRecords(queryParam);
        if (resultList == null || resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        IDataSet dataset;
        ITable tableXml;
        try {
            dataset = getDataSet();
            /* 获得XML中的数据 */
            tableXml = dataset.getTable(TABLE_RSellContractDdtail);
            for (int i = 0; i < resultList.size(); i++) {
                Assert.assertEquals(resultList.get(i).getSell_contract_id(),
                        (String) tableXml.getValue(i, "sell_contract_id"));
                Assert.assertEquals(resultList.get(i).getProduct_contract_code(),
                        (String) tableXml.getValue(i, "product_contract_code"));
                Assert.assertEquals(resultList.get(i).getCompany_contract_code(),
                        (String) tableXml.getValue(i, "company_contract_code"));
                Assert.assertEquals(resultList.get(i).getStatus_name(),
                        (String) tableXml.getValue(i, "status_name"));
                Assert.assertEquals(
                        resultList.get(i).getMoney().setScale(2, RoundingMode.HALF_UP),
                        new BigDecimal((String) tableXml.getValue(i, "money")).setScale(
                                2, RoundingMode.HALF_UP)

                );
                Assert.assertEquals(resultList.get(i).getProduct_code(),
                        (String) tableXml.getValue(i, "product_code"));
                Assert.assertEquals(resultList.get(i).getProduct_name(),
                        (String) tableXml.getValue(i, "product_name"));
                Assert.assertEquals(resultList.get(i).getProduct_type(),
                        (String) tableXml.getValue(i, "product_type"));
                Assert.assertEquals(resultList.get(i).getProduct_unit(),
                        (String) tableXml.getValue(i, "product_unit"));
                Assert.assertEquals(resultList.get(i).getCount(), new Integer(
                        (String) tableXml.getValue(i, "count")));
                Assert.assertEquals(resultList.get(i).getPrice(), new BigDecimal(
                        (String) tableXml.getValue(i, "price")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSell_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "sell_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getProduct_limit_price(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "product_limit_price")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i, "send_goods_money"))
                                .setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "fact_send_goods_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_count(), new Integer(
                        (String) tableXml.getValue(i, "send_goods_count")));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_count(),
                        new Integer((String) tableXml
                                .getValue(i, "fact_send_goods_count")));
                Assert.assertEquals(resultList.get(i).getPrepare_goods(), new BigDecimal(
                        (String) tableXml.getValue(i, "prepare_goods")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getStock_goods_count(),
                        new Integer((String) tableXml.getValue(i, "stock_goods_count")));
                Assert.assertEquals(resultList.get(i).getAppoint_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "appoint_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getIn_transit_appoint_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "in_transit_appoint_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getSell_invoice_money(),
                        new BigDecimal((String) tableXml
                                .getValue(i, "sell_invoice_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getBack_contract_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "back_contract_money")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_contract_count(),
                        new Integer((String) tableXml.getValue(i, "back_contract_count")));
                Assert.assertEquals(resultList.get(i).getBack_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "back_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_count(), new Integer(
                        (String) tableXml.getValue(i, "back_count")));
                Assert.assertEquals(resultList.get(i).getFact_sell_count(), new Integer(
                        (String) tableXml.getValue(i, "fact_sell_count")));
                Assert.assertEquals(
                        resultList.get(i).getContract_not_implemented_count(),
                        new Integer((String) tableXml.getValue(i,
                                "contract_not_implemented_count")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 检索 所有列
     */
    @Test
    public void testSellContractdetail005() {
        RSellContractDetailDto param = new RSellContractDetailDto();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        param.setSell_contract_id("XS111111112");
        param.setProduct_contract_code("XS031000001");
        param.setCompany_contract_code("XS2010-13449-TW");
        param.setStatus("14");
        param.setProduct_name("超五类4对非屏蔽双绞线");
        param.setProduct_code("03.002.0001");
        param.setProduct_type("CC61004");
        param.setProduct_type_name("布线产品");

        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 28列

        queryParam.setQuery(param);
        queryParam.setPage(10);
        queryParam.setRows(3);
        queryParam.setSord("");
        queryParam.setSidx("sell_contract_id");
        queryParam.setStar(0);

        List<RSellContractDetailEntity> resultList = dao.queryRecords(queryParam);
        if (resultList == null || resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        IDataSet dataset;
        ITable tableXml;
        try {
            dataset = getDataSet();
            /* 获得XML中的数据 */
            tableXml = dataset.getTable(TABLE_RSellContractDdtail);
            for (int i = 0; i < resultList.size(); i++) {
                Assert.assertEquals(resultList.get(i).getSell_contract_id(),
                        (String) tableXml.getValue(i, "sell_contract_id"));
                Assert.assertEquals(resultList.get(i).getProduct_contract_code(),
                        (String) tableXml.getValue(i, "product_contract_code"));
                Assert.assertEquals(resultList.get(i).getCompany_contract_code(),
                        (String) tableXml.getValue(i, "company_contract_code"));
                Assert.assertEquals(resultList.get(i).getStatus_name(),
                        (String) tableXml.getValue(i, "status_name"));
                Assert.assertEquals(
                        resultList.get(i).getMoney().setScale(2, RoundingMode.HALF_UP),
                        new BigDecimal((String) tableXml.getValue(i, "money")).setScale(
                                2, RoundingMode.HALF_UP)

                );
                Assert.assertEquals(resultList.get(i).getProduct_code(),
                        (String) tableXml.getValue(i, "product_code"));
                Assert.assertEquals(resultList.get(i).getProduct_name(),
                        (String) tableXml.getValue(i, "product_name"));
                Assert.assertEquals(resultList.get(i).getProduct_type(),
                        (String) tableXml.getValue(i, "product_type"));
                Assert.assertEquals(resultList.get(i).getProduct_unit(),
                        (String) tableXml.getValue(i, "product_unit"));
                Assert.assertEquals(resultList.get(i).getCount(), new Integer(
                        (String) tableXml.getValue(i, "count")));
                Assert.assertEquals(resultList.get(i).getPrice(), new BigDecimal(
                        (String) tableXml.getValue(i, "price")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSell_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "sell_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getProduct_limit_price(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "product_limit_price")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i, "send_goods_money"))
                                .setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "fact_send_goods_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getSend_goods_count(), new Integer(
                        (String) tableXml.getValue(i, "send_goods_count")));
                Assert.assertEquals(
                        resultList.get(i).getFact_send_goods_count(),
                        new Integer((String) tableXml
                                .getValue(i, "fact_send_goods_count")));
                Assert.assertEquals(resultList.get(i).getPrepare_goods(), new BigDecimal(
                        (String) tableXml.getValue(i, "prepare_goods")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getStock_goods_count(),
                        new Integer((String) tableXml.getValue(i, "stock_goods_count")));
                Assert.assertEquals(resultList.get(i).getAppoint_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "appoint_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getIn_transit_appoint_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "in_transit_appoint_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getSell_invoice_money(),
                        new BigDecimal((String) tableXml
                                .getValue(i, "sell_invoice_money")).setScale(2,
                                RoundingMode.HALF_UP));
                Assert.assertEquals(
                        resultList.get(i).getBack_contract_money(),
                        new BigDecimal((String) tableXml.getValue(i,
                                "back_contract_money")).setScale(2, RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_contract_count(),
                        new Integer((String) tableXml.getValue(i, "back_contract_count")));
                Assert.assertEquals(resultList.get(i).getBack_money(), new BigDecimal(
                        (String) tableXml.getValue(i, "back_money")).setScale(2,
                        RoundingMode.HALF_UP));
                Assert.assertEquals(resultList.get(i).getBack_count(), new Integer(
                        (String) tableXml.getValue(i, "back_count")));
                Assert.assertEquals(resultList.get(i).getFact_sell_count(), new Integer(
                        (String) tableXml.getValue(i, "fact_sell_count")));
                Assert.assertEquals(
                        resultList.get(i).getContract_not_implemented_count(),
                        new Integer((String) tableXml.getValue(i,
                                "contract_not_implemented_count")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 检索 合计值 所有列
     */
    @Test
    public void testSellContractdetail101() {
        RSellContractDetailDto param = new RSellContractDetailDto();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        param.setSell_contract_id("XS111111112");
        param.setProduct_contract_code("XS031000001");
        param.setCompany_contract_code("XS2010-13449-TW");
        param.setStatus("14");
        param.setProduct_name("超五类4对非屏蔽双绞线");
        param.setProduct_code("03.002.0001");
        param.setProduct_type("CC61004");
        param.setProduct_type_name("布线产品");
        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 28列
        queryParam.setQuery(param);
        queryParam.setPage(10);
        queryParam.setRows(3);
        queryParam.setSord("");
        queryParam.setSidx("sell_contract_id");
        queryParam.setStar(0);
        RSellContractDetailEntity entity = dao.getRSellContractDetaiSumVal(queryParam);
        if (entity == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(entity.getCount(), new Integer("60"));
        Assert.assertEquals(entity.getSell_money(), new BigDecimal("25200.00"));
    }

    /**
     * 检索 合计值 销售合同号
     */
    @Test
    public void testSellContractdetail102() {
        RSellContractDetailDto param = new RSellContractDetailDto();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        param.setSell_contract_id("XS111111112");
        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 28列
        queryParam.setQuery(param);
        queryParam.setPage(10);
        queryParam.setRows(3);
        queryParam.setSord("");
        queryParam.setSidx("sell_contract_id");
        queryParam.setStar(0);
        RSellContractDetailEntity entity = dao.getRSellContractDetaiSumVal(queryParam);
        if (entity == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(entity.getCount(), new Integer("121"));
        Assert.assertEquals(entity.getSell_money(), new BigDecimal("50400.00"));
    }

    /**
     * 检索 合计值 多项
     */
   // @Test
    public void testSellContractdetail103() {
        RSellContractDetailDto param = new RSellContractDetailDto();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        param.setCompany_contract_code("XS2010-13450-WL");
        param.setStatus("14");
        param.setProduct_name("配线架");
        param.setProduct_code("01.002.0029");
        param.setProduct_type("PMGS3-24/760062356");
        param.setProduct_type_name("康普产品");
        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 28列
        queryParam.setQuery(param);
        queryParam.setPage(10);
        queryParam.setRows(3);
        queryParam.setSord("");
        queryParam.setSidx("sell_contract_id");
        queryParam.setStar(0);
        RSellContractDetailEntity entity = dao.getRSellContractDetaiSumVal(queryParam);
        if (entity == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(entity.getCount(), new Integer("143"));
        Assert.assertEquals(entity.getSell_money(), new BigDecimal("1368648.00"));
    }

    /**
     * 检索 合计值 无数据
     */
    @Test
    public void testSellContractdetail104() {
        RSellContractDetailDto param = new RSellContractDetailDto();
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        param.setCompany_contract_code("XS2010-13450-WL3");
        param.setStatus("14");
        param.setProduct_name("配线架");
        param.setProduct_code("01.002.0029");
        param.setProduct_type("PMGS3-24/760062356");
        param.setProduct_type_name("康普产品2");
        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 28列
        queryParam.setQuery(param);
        queryParam.setPage(10);
        queryParam.setRows(3);
        queryParam.setSord("");
        queryParam.setSidx("sell_contract_id");
        queryParam.setStar(0);
        RSellContractDetailEntity entity = dao.getRSellContractDetaiSumVal(queryParam);
        Assert.assertTrue(entity.getCount() == null);

    }

    /*
     * @AfterClass public void afterClass() throws AmbiguousTableNameException,
     * DataSetException, DatabaseUnitException, SQLException { 测试结束执行 还原备份数据
     * DatabaseOperation.TRUNCATE_tableXml.execute(conn, new
     * DefaultDataSet(backupDataSet.getTable(TABLE_RSellContractDdtail)));
     * 
     * }
     */
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
                .getResource("classpath:dbunit/r_sell_contract_detail.xml")
                .getInputStream());
        return dataSet;
    }

    /*
     * 备份数据表的xml文件名
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupFileName()
     */
    @Override
    public String getBackupFileName() {
        return "r_sell_contract_detail.xml";
    }

    /*
     * 导入的测试数据文件
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getDataFileName()
     */
    @Override
    public String[] getDataFileName() {
        return new String[] { "r_sell_contract_detail.xml" };
    }

    /*
     * 备份的数据表名
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupTableName()
     */
    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_sell_contract_detail" };
    }

}
