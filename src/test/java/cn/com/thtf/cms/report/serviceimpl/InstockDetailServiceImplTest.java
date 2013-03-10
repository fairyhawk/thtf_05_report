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
import cn.com.thtf.cms.report.dto.InstockDetailDto;
import cn.com.thtf.cms.report.service.InstockDetailService;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author zhangzx
 */

public class InstockDetailServiceImplTest extends BaseTestNG implements NeedDBUnit {

    private InstockDetailService service = null;
    /** DB连接 */
    private static IDatabaseConnection conn;
    /* private static IDataSet backupDataSet; */
    /** 表 */
    private static final String TABLE_RINSTOCKDETAIL = "r_instock_detail";
    /** 显示的列 */
    private String fieldRows[] = { "buy_contract_id as buyContractId",
            "product_contract_code as productContractCode",
            "company_contract_code as companyContractCode",
            "product_type_name as productTypeName", 
            "instock_id as instockId",
            "stockroom_name as stockroomName", 
            "supplier_name as supplierName",
            "instock_totalmoney as instockTotalmoney", 
            "date",
            "stock_date as stockDate",
            "user_name as userName",
            "status_name as statusName",
            "product_code as productCode",
            "product_name as productName",
            "product_type as productType",
            "product_unit as productUnit",
            "buy_price as buyPrice",
            "instock_count as instockCount",
            "instock_money as instockMoney",
            "back_count as backCount",
            "back_money as backMoney",
            "stock_count_rel as stockCountRel",
            "stock_money_rel as stockMoneyRel",
            "appoint_money as appointMoney",
            "non_payment_money as nonPaymentMoney",
            "factory_send_date as factorySendDate",
            "payment_date as paymentDate",
            "receive_invoice_money as receiveInvoiceMoney" };

    @BeforeClass
    public void beforeClass() {
        try {
            service = Container.getBean("instockDetailService",
                    InstockDetailService.class);
            conn = new DatabaseConnection(Container
                    .getBean("sqlMapClient", SqlMapClient.class).getDataSource()
                    .getConnection());
            /* 获得需要备份表 */
            QueryDataSet tmpDataSet = new QueryDataSet(conn, false);
            tmpDataSet.addTable(TABLE_RINSTOCKDETAIL);

            /*
             * backupDataSet = new DefaultDataSet( new ITable[] {
             * tmpDataSet.getTable(TABLE_RSellContractDdtail) });
             */

            /* 从XML读取数据 */
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            IDataSet expectDataSet = builder.build(Container.getApplicationContext()
                    .getResource("classpath:dbunit/rtransportFareSharing.xml")
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
    /*
     * 测试返回行数
     */
    @Test
    public void testSellContractdetail001() {
        InstockDetailDto param = new InstockDetailDto();
        BaseQueryData<InstockDetailDto> queryParam = new BaseQueryData<InstockDetailDto>();

        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("buy_contract_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 显示的列
        queryParam.setQuery(param);
        service.getInstockDetail(queryParam);
        service.getInstockDetailSumVal(queryParam);
        Assert.assertEquals(queryParam.getDataList().size(), 4);

    }

    /*
     * 备份数据表的xml文件名
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupFileName()
     */
    @Override
    public String getBackupFileName() {
        return "rinstockDetail.xml";
    }

    /*
     * 导入的测试数据文件
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getDataFileName()
     */
    @Override
    public String[] getDataFileName() {
        return new String[] { "rinstockDetail.xml" };
    }

    /*
     * 备份的数据表名
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupTableName()
     */
    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_instock_detail" };
    }
}
