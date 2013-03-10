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

import cn.com.thtf.cms.report.dao.TransportFareSharingDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.TransportFareSharingDto;
import cn.com.thtf.cms.report.entity.TransportFareSharingEntity;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * TransportFareSharingDaoImplTest
 * 
 * @author zhangzx
 */
public class TransportFareSharingDaoImplTest extends BaseTestNG implements NeedDBUnit {

    /** TransportFareSharingDao */
    private TransportFareSharingDao dao = null;
    /** DB连接 */
    private IDatabaseConnection conn;
    /** 表 */
    private static final String TABLE_RTRANSPORTFARESHARING = "r_transport_fare_sharing";
    /** 显示的列 */
    private String fieldRows[] = {  "box_id as boxId", "send_goods_id as sendGoodsId",
            "name as name", "logistics_name as logisticsName",
            "customer_name as customerName", "round(money,2) as money",
            "send_date as sendDate", "send_goods_type as sendGoodsType",
            "send_agv_money as sendAgvMoney", "user_dept_name as userDeptName",
            "user_name as userName", "tbc_flag_name as tbcFlagName",
            "tbc_date as tbcDate" };

    /**
     * 初始化
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public void setUp() {
        try {
            /* 获得Dao */
            dao = Container.getBean("transportFareSharingDao", TransportFareSharingDao.class);
            /* 获得连接 */

            conn = new DatabaseConnection(Container
                    .getBean("sqlMapClient", SqlMapClient.class).getDataSource()
                    .getConnection());

            /* 获得需要备份表 */
            QueryDataSet tmpDataSet = new QueryDataSet(conn, false);
            tmpDataSet.addTable(TABLE_RTRANSPORTFARESHARING);

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

   /**
    * 
    * @throws Exception
    */
    //@Test
    public void testGetTransportFareSharing001() throws Exception {
        TransportFareSharingDto param = new TransportFareSharingDto();
        BaseQueryData<TransportFareSharingDto> queryParam = new BaseQueryData<TransportFareSharingDto>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("box_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111111", fieldRows);// 显示的列
        queryParam.setQuery(param);// 查询条件

        List<TransportFareSharingEntity> resultList = dao.getTransportFareSharing(queryParam);

        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE_RTRANSPORTFARESHARING);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getBoxId(),
                    (String) table.getValue(i, "box_id"));
            Assert.assertEquals(resultList.get(i).getSendGoodsId(),
                    (String) table.getValue(i, "send_goods_id"));
            Assert.assertEquals(resultList.get(i).getName(),
                    (String) table.getValue(i, "name"));
            Assert.assertEquals(resultList.get(i).getLogisticsName(),
                    (String) table.getValue(i, "logistics_name"));
            Assert.assertEquals(resultList.get(i).getCustomerName(),
                    (String) table.getValue(i, "customer_name"));
            Assert.assertEquals(resultList.get(i).getMoney(),
                    (String) table.getValue(i, "money"));
            Assert.assertEquals(resultList.get(i).getSendDate(),
                    (String) table.getValue(i, "send_date"));
            Assert.assertEquals(resultList.get(i).getSendGoodsType(),
                    (String) table.getValue(i, "send_goods_type"));
            Assert.assertEquals(resultList.get(i).getSendAgvMoney(),
                    (String) table.getValue(i, "send_agv_money"));
            Assert.assertEquals(resultList.get(i).getUserDeptName(),
                    (String) table.getValue(i, "user_dept_name"));
            Assert.assertEquals(resultList.get(i).getUserName(),
                    (String) table.getValue(i, "user_name"));
            Assert.assertEquals(resultList.get(i).getTbcFlagName(),
                    (String) table.getValue(i, "tbc_flag_name"));
            Assert.assertEquals(resultList.get(i).getTbcDate(),
                    (String) table.getValue(i, "tbc_date"));
        }
    }
    
    
    /* 检索产品分类名称 */
    //@Test
    public void testGetTransportFareSharing002() throws Exception {
        TransportFareSharingDto param = new TransportFareSharingDto();
        BaseQueryData<TransportFareSharingDto> queryParam = new BaseQueryData<TransportFareSharingDto>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("box_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111111", fieldRows);// 显示的列
        param.setName("康普产品");
        queryParam.setQuery(param);// 查询条件

        List<TransportFareSharingEntity> resultList = dao.getTransportFareSharing(queryParam);
        if (resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE_RTRANSPORTFARESHARING);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getBoxId(),
                    (String) table.getValue(i, "box_id"));
            Assert.assertEquals(resultList.get(i).getSendGoodsId(),
                    (String) table.getValue(i, "send_goods_id"));
            Assert.assertEquals(resultList.get(i).getName(),
                    (String) table.getValue(i, "name"));
            Assert.assertEquals(resultList.get(i).getLogisticsName(),
                    (String) table.getValue(i, "logistics_name"));
            Assert.assertEquals(resultList.get(i).getCustomerName(),
                    (String) table.getValue(i, "customer_name"));
            Assert.assertEquals(resultList.get(i).getMoney(),
                    (String) table.getValue(i, "money"));
            Assert.assertEquals(resultList.get(i).getSendDate(),
                    (String) table.getValue(i, "send_date"));
            Assert.assertEquals(resultList.get(i).getSendGoodsType(),
                    (String) table.getValue(i, "send_goods_type"));
            Assert.assertEquals(resultList.get(i).getSendAgvMoney(),
                    (String) table.getValue(i, "send_agv_money"));
            Assert.assertEquals(resultList.get(i).getUserDeptName(),
                    (String) table.getValue(i, "user_dept_name"));
            Assert.assertEquals(resultList.get(i).getUserName(),
                    (String) table.getValue(i, "user_name"));
            Assert.assertEquals(resultList.get(i).getTbcFlagName(),
                    (String) table.getValue(i, "tbc_flag_name"));
            Assert.assertEquals(resultList.get(i).getTbcDate(),
                    (String) table.getValue(i, "tbc_date"));
        }
    }
    
    /* 检索 所有字段 */
    //@Test
    public void testGetTransportFareSharing006() throws Exception {
        TransportFareSharingDto param = new TransportFareSharingDto();
        BaseQueryData<TransportFareSharingDto> queryParam = new BaseQueryData<TransportFareSharingDto>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("box_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111111", fieldRows);// 显示的列
        param.setBoxId("ZX110105001");
        param.setSendGoodsId("FH101130043");
        param.setName("康普产品");
        param.setLogisticsName("上海信辉货运代理有限公司");
        param.setCustomerName("杭州茵鹏信息技术有限公司");
        param.setSendDate("2011-01-05");
        param.setSendGoodsType("发货单");
        param.setSendAgvMoney("12.00");
        param.setUserDeptName("运营管理");
        param.setUserName("李实美");
        param.setTbcFlagName("未确认");
        
        queryParam.setQuery(param);// 查询条件

        List<TransportFareSharingEntity> resultList = dao.getTransportFareSharing(queryParam);
        if (resultList.size() == 0) {
            Assert.assertTrue(false);
        }
        /* 获得XML中的数据 */
        IDataSet dataset = getDataSet();
        ITable table = dataset.getTable(TABLE_RTRANSPORTFARESHARING);

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(resultList.get(i).getBoxId(),
                    (String) table.getValue(i, "box_id"));
            Assert.assertEquals(resultList.get(i).getSendGoodsId(),
                    (String) table.getValue(i, "send_goods_id"));
            Assert.assertEquals(resultList.get(i).getName(),
                    (String) table.getValue(i, "name"));
            Assert.assertEquals(resultList.get(i).getLogisticsName(),
                    (String) table.getValue(i, "logistics_name"));
            Assert.assertEquals(resultList.get(i).getCustomerName(),
                    (String) table.getValue(i, "customer_name"));
            Assert.assertEquals(resultList.get(i).getMoney(),
                    (String) table.getValue(i, "money"));
            Assert.assertEquals(resultList.get(i).getSendDate(),
                    (String) table.getValue(i, "send_date"));
            Assert.assertEquals(resultList.get(i).getSendGoodsType(),
                    (String) table.getValue(i, "send_goods_type"));
            Assert.assertEquals(resultList.get(i).getSendAgvMoney(),
                    (String) table.getValue(i, "send_agv_money"));
            Assert.assertEquals(resultList.get(i).getUserDeptName(),
                    (String) table.getValue(i, "user_dept_name"));
            Assert.assertEquals(resultList.get(i).getUserName(),
                    (String) table.getValue(i, "user_name"));
            Assert.assertEquals(resultList.get(i).getTbcFlagName(),
                    (String) table.getValue(i, "tbc_flag_name"));
            Assert.assertEquals(resultList.get(i).getTbcDate(),
                    (String) table.getValue(i, "tbc_date"));
        }
    }
    
    /* 检索 合计值 所有列 */
    //@Test
    public void testGetTransportFareSharing007() throws Exception {
        TransportFareSharingDto param = new TransportFareSharingDto();
        BaseQueryData<TransportFareSharingDto> queryParam = new BaseQueryData<TransportFareSharingDto>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("box_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111111", fieldRows);// 显示的列
        param.setBoxId("ZX110105001");
        param.setSendGoodsId("FH101130043");
        param.setName("康普产品");
        param.setLogisticsName("上海信辉货运代理有限公司");
        param.setCustomerName("杭州茵鹏信息技术有限公司");
        param.setSendDate("2011-01-05");
        param.setSendGoodsType("发货单");
        param.setSendAgvMoney("12.00");
        param.setUserDeptName("运营管理");
        param.setUserName("李实美");
        param.setTbcFlagName("未确认");
        queryParam.setQuery(param);// 查询条件

        TransportFareSharingDto detailEntity = dao.getTransportFareSharingSumVal(queryParam);
        if (detailEntity == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(detailEntity.getMoneySum(), "12.00");
        Assert.assertEquals(detailEntity.getSendAgvMoneySum(), "12.00");

    }
    
    /* 检索 合计值 多列 */
    //@Test
    public void testGetTransportFareSharing009() throws Exception {
        TransportFareSharingDto param = new TransportFareSharingDto();
        BaseQueryData<TransportFareSharingDto> queryParam = new BaseQueryData<TransportFareSharingDto>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("box_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111111", fieldRows);// 显示的列
        param.setUserName("陈欢");
        queryParam.setQuery(param);

        TransportFareSharingDto detailEntity = dao.getTransportFareSharingSumVal(queryParam);
        if (detailEntity == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(detailEntity.getMoneySum(), "22.00");
        Assert.assertEquals(detailEntity.getSendAgvMoneySum(), "55.77");

    }
    
    
    /* 检索 合计值 无数据 */
    @Test
    public void testGetTransportFareSharing010() throws Exception {
        TransportFareSharingDto param = new TransportFareSharingDto();
        BaseQueryData<TransportFareSharingDto> queryParam = new BaseQueryData<TransportFareSharingDto>();
        queryParam.setPage(10);
        queryParam.setRows(5);
        queryParam.setSord("");
        queryParam.setSidx("box_id");
        queryParam.setStar(0);
        queryParam.setFieldsParam("1111111111111", fieldRows);// 显示的列
        param.setUserName("xxxx");
        queryParam.setQuery(param);// 查询条件

        TransportFareSharingDto detailEntity = dao.getTransportFareSharingSumVal(queryParam);
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
                        .getResource("classpath:dbunit/rtransportFareSharing.xml")
                        .getInputStream());
        return dataSet;
    }


    @Override
    public String getBackupFileName() {
        return "rtransportFareSharing.xml";
    }


    @Override
    public String[] getDataFileName() {
        return new String[] { "rtransportFareSharing.xml" };
    }


    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_transport_fare_sharing" };
    }
}
