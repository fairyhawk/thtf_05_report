/**
 * ClassName  RTransportFareDetailDaoImplTest
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-21
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

import cn.com.thtf.cms.report.dao.RTransportFareDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RTransportFareDetailDto;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author hanrb
 */

public class RTransportFareDetailDaoImplTest extends BaseTestNG implements NeedDBUnit {

    /** StockProductDetailDao */
    private RTransportFareDetailDao dao = null;
    /** DB连接 */
    private IDatabaseConnection conn;
    /** 表 */
    private static final String TABLE = "r_transport_fare_detail";
    /** 显示的列 */
    private String fieldRows[] = { "logistics_name as logisticsName",
            "send_date as sendDate", "box_id as boxId", "box_no as boxNo",
            "stockroom_name as stockroomName", "product_type_name as productTypeName",
            "customer_name as customerName", "company_name as companyName", "address",
            "request_way as requestWay", "reality_way as realityWay", "count",
            "arrival_date as arrivalDate", "signoff_date as signoffDate",
            "confirm_date as confirmDate", "user_name as userName", "money",
            "status_name as statusName", "tbc_flag_name as tbcFlagName",
            "tbc_date as tbcDate", "date" };

    /**
     * 初始化
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public void setUp() {
        try {
            /* 获得Dao */
            dao = Container.getBean("transportFareDetailDao",
                    RTransportFareDetailDao.class);
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
                    .getResource("classpath:dbunit/r_transport_fare_detail.xml")
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
    public void testTransportFareDetail001() throws Exception {
        RTransportFareDetailDto param = new RTransportFareDetailDto();
        BaseQueryData<RTransportFareDetailDto> queryParam = new BaseQueryData<RTransportFareDetailDto>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("box_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("111111111111111111111", fieldRows);// 显示的列
        queryParam.setQuery(param);// 查询条件
        List<RTransportFareDetailDto> resultList = (List<RTransportFareDetailDto>) dao
                .queryRecords(queryParam);

        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getLogisticsName(),
                    (String) table.getValue(i, "logistics_name"));
            Assert.assertEquals(resultList.get(i).getSendDate(),
                    (String) table.getValue(i, "send_date"));
            Assert.assertEquals(resultList.get(i).getBoxId(),
                    (String) table.getValue(i, "box_id"));
            Assert.assertEquals(resultList.get(i).getBoxNo(),
                    (String) table.getValue(i, "box_no"));
            Assert.assertEquals(resultList.get(i).getStockroomName(),
                    (String) table.getValue(i, "stockroom_name"));
            Assert.assertEquals(resultList.get(i).getProductTypeName(),
                    (String) table.getValue(i, "product_type_name"));
            Assert.assertEquals(resultList.get(i).getCustomerName(),
                    (String) table.getValue(i, "customer_name"));
            Assert.assertEquals(resultList.get(i).getCompanyName(),
                    (String) table.getValue(i, "company_name"));
            Assert.assertEquals(resultList.get(i).getAddress(),
                    (String) table.getValue(i, "address"));
            Assert.assertEquals(resultList.get(i).getRequestWay(),
                    (String) table.getValue(i, "request_way"));
            Assert.assertEquals(resultList.get(i).getRealityWay(),
                    (String) table.getValue(i, "reality_way"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getCount()),
                    (String) table.getValue(i, "count"));
            Assert.assertEquals(resultList.get(i).getArrivalDate(),
                    (String) table.getValue(i, "arrival_date"));
            Assert.assertEquals(resultList.get(i).getSignoffDate(),
                    (String) table.getValue(i, "signoff_date"));
            Assert.assertEquals(resultList.get(i).getConfirmDate(),
                    (String) table.getValue(i, "confirm_date"));
            Assert.assertEquals(resultList.get(i).getUserName(),
                    (String) table.getValue(i, "user_name"));
            Assert.assertEquals(String.valueOf(resultList.get(i).getMoney()),
                    (String) table.getValue(i, "money"));
            Assert.assertEquals(resultList.get(i).getStatusName(),
                    (String) table.getValue(i, "status_name"));
            Assert.assertEquals(resultList.get(i).getTbcFlagName(),
                    (String) table.getValue(i, "tbc_flag_name"));
            Assert.assertEquals(resultList.get(i).getTbcDate(),
                    (String) table.getValue(i, "tbc_date"));
            Assert.assertEquals(resultList.get(i).getDate(),
                    (String) table.getValue(i, "date"));
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
                .getResource("classpath:dbunit/r_transport_fare_detail.xml")
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
        return "r_transport_fare_detail.xml";
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getDataFileName()
     */
    @Override
    public String[] getDataFileName() {
        return new String[] { "r_transport_fare_detail.xml" };
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.cms.report.test.NeedDBUnit#getBackupTableName()
     */
    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_transport_fare_detail" };
    }
}
