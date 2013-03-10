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
import cn.com.thtf.cms.report.dto.TransportFareSharingDto;
import cn.com.thtf.cms.report.service.TransportFareSharingService;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author zhangzx
 */

public class TransportFareSharingServiceImplTest extends BaseTestNG implements NeedDBUnit {

    private TransportFareSharingService service = null;
    /** DB连接 */
    private static IDatabaseConnection conn;
    /* private static IDataSet backupDataSet; */
    /** 表 */
    private static final String TABLE_RTRANSPORTFARESHARING = "r_transport_fare_sharing";
    /** 显示的列 */
    private String fieldRows[] = { "box_id as boxId", "send_goods_id as sendGoodsId",
            "name as name", "logistics_name as logisticsName",
            "customer_name as customerName", "round(money,2) as money",
            "send_date as sendDate", "send_goods_type as sendGoodsType",
            "send_agv_money as sendAgvMoney", "user_dept_name as userDeptName",
            "user_name as userName", "tbc_flag_name as tbcFlagName",
            "tbc_date as tbcDate" };

    @BeforeClass
    public void beforeClass() {
        try {
            service = Container.getBean("transportFareSharingService",
                    TransportFareSharingService.class);
            conn = new DatabaseConnection(Container
                    .getBean("sqlMapClient", SqlMapClient.class).getDataSource()
                    .getConnection());
            /* 获得需要备份表 */
            QueryDataSet tmpDataSet = new QueryDataSet(conn, false);
            tmpDataSet.addTable(TABLE_RTRANSPORTFARESHARING);

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
        TransportFareSharingDto param = new TransportFareSharingDto();
        BaseQueryData<TransportFareSharingDto> queryParam = new BaseQueryData<TransportFareSharingDto>();

        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("box_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111111", fieldRows);// 显示的列
        queryParam.setQuery(param);
        service.getTransportFareSharingDetail(queryParam);
        service.getTransportFareSharingSumVal(queryParam);
        Assert.assertEquals(queryParam.getDataList().size(), 4);

    }

    /*
     * 备份数据表的xml文件名
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupFileName()
     */
    @Override
    public String getBackupFileName() {
        return "rtransportFareSharing.xml";
    }

    /*
     * 导入的测试数据文件
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getDataFileName()
     */
    @Override
    public String[] getDataFileName() {
        return new String[] { "rtransportFareSharing.xml" };
    }

    /*
     * 备份的数据表名
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupTableName()
     */
    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_transport_fare_sharing" };
    }
}
