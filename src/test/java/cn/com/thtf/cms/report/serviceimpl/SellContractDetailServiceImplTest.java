/**
 * ClassName  SellContractDetailServiceImplTest
 *
 * History
 * Create User: liuqg
 * Create Date: 2011-1-19
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
import cn.com.thtf.cms.report.dto.RSellContractDetailDto;
import cn.com.thtf.cms.report.service.RSellContractDetailService;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author liuqg
 */

public class SellContractDetailServiceImplTest extends BaseTestNG implements NeedDBUnit {

    private RSellContractDetailService service = null;
    /** DB连接 */
    private static IDatabaseConnection conn;
    /* private static IDataSet backupDataSet; */
    /** 表 */
    private static final String TABLE_RSellContractDdtail = "r_Sell_Contract_Detail";
    /** 显示的列 */
    private String fieldRows[] = { "sell_contract_id as sell_contract_id",
            "product_contract_code as product_contract_code",
            "company_contract_code as company_contract_code",
            "status_name as status_name", "money as money",
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
            service = Container.getBean("sellContractDetailService",
                    RSellContractDetailService.class);
            conn = new DatabaseConnection(Container
                    .getBean("sqlMapClient", SqlMapClient.class).getDataSource()
                    .getConnection());
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
        service.getRSellContractDetail(queryParam);
        service.getRSellContractDetailSumVal(queryParam);
        Assert.assertEquals(queryParam.getDataList().size(), 3);

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
