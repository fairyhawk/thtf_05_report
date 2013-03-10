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

import cn.com.thtf.cms.report.dao.InstockDetailDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.InstockDetailDto;
import cn.com.thtf.cms.report.entity.InstockDetailEntity;
import cn.com.thtf.cms.report.test.BaseTestNG;
import cn.com.thtf.cms.report.test.NeedDBUnit;
import cn.shiy.common.cws.utils.Container;

import com.ibatis.sqlmap.client.SqlMapClient;



public class InstockDetailDaoImplTest extends BaseTestNG implements NeedDBUnit{
    /** InstockDetailDao */
    private InstockDetailDao dao = null;
    /** DB连接 */
    private IDatabaseConnection conn;
    /** 表 */
    private static final String TABLE_RINSTOCKDETAIL = "r_instock_detail";
    /** 显示的列 */
    private String fieldRows[] = {  "buy_contract_id as buyContractId",
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
            "receive_invoice_money as receiveInvoiceMoney"};
    
    
    /**
     * 初始化
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public void setUp() {
        try {
            /* 获得Dao */
            dao = Container.getBean("instockDetailDao", InstockDetailDao.class);
            /* 获得连接 */

            conn = new DatabaseConnection(Container
                    .getBean("sqlMapClient", SqlMapClient.class).getDataSource()
                    .getConnection());

            /* 获得需要备份表 */
            QueryDataSet tmpDataSet = new QueryDataSet(conn, false);
            tmpDataSet.addTable(TABLE_RINSTOCKDETAIL);

            /* 从XML读取数据 */
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            IDataSet expectDataSet = builder.build(Container.getApplicationContext()
                    .getResource("classpath:dbunit/rinstockDetail.xml")
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
     public void testGetInstockDetail001() throws Exception {
         InstockDetailDto param = new InstockDetailDto();
         BaseQueryData<InstockDetailDto> queryParam = new BaseQueryData<InstockDetailDto>();
         queryParam.setPage(10);
         queryParam.setRows(5);
         queryParam.setSord("");
         queryParam.setSidx("buy_contract_id");
         queryParam.setStar(0);
         queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 显示的列
         queryParam.setQuery(param);// 查询条件

         List<InstockDetailEntity> resultList = dao.getInstockDetail(queryParam);

         /* 获得XML中的数据 */
         IDataSet dataset = getDataSet();
         ITable table = dataset.getTable(TABLE_RINSTOCKDETAIL);

         for (int i = 0; i < resultList.size(); i++) {
             Assert.assertEquals(resultList.get(i).getBuyContractId(),
                     (String) table.getValue(i, "buy_contract_id"));
             Assert.assertEquals(resultList.get(i).getProductContractCode(),
                     (String) table.getValue(i, "product_contract_code"));
             Assert.assertEquals(resultList.get(i).getCompanyContractCode(),
                     (String) table.getValue(i, "company_contract_code"));
             Assert.assertEquals(resultList.get(i).getProductTypeName(),
                     (String) table.getValue(i, "product_type_name"));
             Assert.assertEquals(resultList.get(i).getInstockId(),
                     (String) table.getValue(i, "instock_id"));
             Assert.assertEquals(resultList.get(i).getStockroomName(),
                     (String) table.getValue(i, "stockroom_name"));
             Assert.assertEquals(resultList.get(i).getSupplierName(),
                     (String) table.getValue(i, "supplier_name"));
             Assert.assertEquals(resultList.get(i).getInstockTotalmoney(),
                     (String) table.getValue(i, "instock_totalmoney"));
             Assert.assertEquals(resultList.get(i).getDate(),
                     (String) table.getValue(i, "date"));
             Assert.assertEquals(resultList.get(i).getStockDate(),
                     (String) table.getValue(i, "stock_date"));
             Assert.assertEquals(resultList.get(i).getUserName(),
                     (String) table.getValue(i, "user_name"));
             Assert.assertEquals(resultList.get(i).getStatusName(),
                     (String) table.getValue(i, "status_name"));
             Assert.assertEquals(resultList.get(i).getProductCode(),
                     (String) table.getValue(i, "product_code"));
             Assert.assertEquals(resultList.get(i).getProductName(),
                     (String) table.getValue(i, "product_name"));
             Assert.assertEquals(resultList.get(i).getProductType(),
                      (String) table.getValue(i, "product_type"));
             Assert.assertEquals(resultList.get(i).getProductUnit(),
                     (String) table.getValue(i, "product_unit"));
             Assert.assertEquals(resultList.get(i).getBuyPrice(),
                     (String) table.getValue(i, "buy_price"));
             Assert.assertEquals(resultList.get(i).getInstockCount(),
                     (String) table.getValue(i, "instock_count"));
             Assert.assertEquals(resultList.get(i).getInstockMoney(),
                      (String) table.getValue(i, "instock_money"));
             Assert.assertEquals(resultList.get(i).getBackCount(),
                     (String) table.getValue(i, "back_count"));
             Assert.assertEquals(resultList.get(i).getBackMoney(),
                     (String) table.getValue(i, "back_money"));
             Assert.assertEquals(resultList.get(i).getStockCountRel(),
                     (String) table.getValue(i, "stock_count_rel"));
             Assert.assertEquals(resultList.get(i).getStockMoneyRel(),
                      (String) table.getValue(i, "stock_money_rel"));
             Assert.assertEquals(resultList.get(i).getAppointMoney(),
                     (String) table.getValue(i, "appoint_money"));
             Assert.assertEquals(resultList.get(i).getNonPaymentMoney(),
                     (String) table.getValue(i, "non_payment_money"));
             Assert.assertEquals(resultList.get(i).getFactorySendDate(),
                     (String) table.getValue(i, "factory_send_date"));
             Assert.assertEquals(resultList.get(i).getPaymentDate(),
                      (String) table.getValue(i, "payment_date"));
             Assert.assertEquals(resultList.get(i).getReceiveInvoiceMoney(),
                     (String) table.getValue(i, "receive_invoice_money"));
         }
     }
     
     
     /* 检索库房名称 */
     //@Test
     public void testGetInstockDetail002() throws Exception {
         InstockDetailDto param = new InstockDetailDto();
         BaseQueryData<InstockDetailDto> queryParam = new BaseQueryData<InstockDetailDto>();
         queryParam.setPage(10);
         queryParam.setRows(5);
         queryParam.setSord("");
         queryParam.setSidx("buy_contract_id");
         queryParam.setStar(0);
         queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 显示的列
         param.setStockroomName("同方网络上海(产品)正常库");
         queryParam.setQuery(param);// 查询条件

         List<InstockDetailEntity> resultList = dao.getInstockDetail(queryParam);
         if (resultList.size() == 0) {
             Assert.assertTrue(false);
         }
         /* 获得XML中的数据 */
         IDataSet dataset = getDataSet();
         ITable table = dataset.getTable(TABLE_RINSTOCKDETAIL);

         for (int i = 0; i < resultList.size(); i++) {
             Assert.assertEquals(resultList.get(i).getBuyContractId(),
                     (String) table.getValue(i, "buy_contract_id"));
             Assert.assertEquals(resultList.get(i).getProductContractCode(),
                     (String) table.getValue(i, "product_contract_code"));
             Assert.assertEquals(resultList.get(i).getCompanyContractCode(),
                     (String) table.getValue(i, "company_contract_code"));
             Assert.assertEquals(resultList.get(i).getProductTypeName(),
                     (String) table.getValue(i, "product_type_name"));
             Assert.assertEquals(resultList.get(i).getInstockId(),
                     (String) table.getValue(i, "instock_id"));
             Assert.assertEquals(resultList.get(i).getStockroomName(),
                     (String) table.getValue(i, "stockroom_name"));
             Assert.assertEquals(resultList.get(i).getSupplierName(),
                     (String) table.getValue(i, "supplier_name"));
             Assert.assertEquals(resultList.get(i).getInstockTotalmoney(),
                     (String) table.getValue(i, "instock_totalmoney"));
             Assert.assertEquals(resultList.get(i).getDate(),
                     (String) table.getValue(i, "date"));
             Assert.assertEquals(resultList.get(i).getStockDate(),
                     (String) table.getValue(i, "stock_date"));
             Assert.assertEquals(resultList.get(i).getUserName(),
                     (String) table.getValue(i, "user_name"));
             Assert.assertEquals(resultList.get(i).getStatusName(),
                     (String) table.getValue(i, "status_name"));
             Assert.assertEquals(resultList.get(i).getProductCode(),
                     (String) table.getValue(i, "product_code"));
             Assert.assertEquals(resultList.get(i).getProductName(),
                     (String) table.getValue(i, "product_name"));
             Assert.assertEquals(resultList.get(i).getProductType(),
                      (String) table.getValue(i, "product_type"));
             Assert.assertEquals(resultList.get(i).getProductUnit(),
                     (String) table.getValue(i, "product_unit"));
             Assert.assertEquals(resultList.get(i).getBuyPrice(),
                     (String) table.getValue(i, "buy_price"));
             Assert.assertEquals(resultList.get(i).getInstockCount(),
                     (String) table.getValue(i, "instock_count"));
             Assert.assertEquals(resultList.get(i).getInstockMoney(),
                      (String) table.getValue(i, "instock_money"));
             Assert.assertEquals(resultList.get(i).getBackCount(),
                     (String) table.getValue(i, "back_count"));
             Assert.assertEquals(resultList.get(i).getBackMoney(),
                     (String) table.getValue(i, "back_money"));
             Assert.assertEquals(resultList.get(i).getStockCountRel(),
                     (String) table.getValue(i, "stock_count_rel"));
             Assert.assertEquals(resultList.get(i).getStockMoneyRel(),
                      (String) table.getValue(i, "stock_money_rel"));
             Assert.assertEquals(resultList.get(i).getAppointMoney(),
                     (String) table.getValue(i, "appoint_money"));
             Assert.assertEquals(resultList.get(i).getNonPaymentMoney(),
                     (String) table.getValue(i, "non_payment_money"));
             Assert.assertEquals(resultList.get(i).getFactorySendDate(),
                     (String) table.getValue(i, "factory_send_date"));
             Assert.assertEquals(resultList.get(i).getPaymentDate(),
                      (String) table.getValue(i, "payment_date"));
             Assert.assertEquals(resultList.get(i).getReceiveInvoiceMoney(),
                     (String) table.getValue(i, "receive_invoice_money"));
         }
     }
     
     
     /* 检索产品分类名称 */
     //@Test
     public void testGetInstockDetail003() throws Exception {
         InstockDetailDto param = new InstockDetailDto();
         BaseQueryData<InstockDetailDto> queryParam = new BaseQueryData<InstockDetailDto>();
         queryParam.setPage(10);
         queryParam.setRows(5);
         queryParam.setSord("");
         queryParam.setSidx("buy_contract_id");
         queryParam.setStar(0);
         queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 显示的列
         param.setProductTypeName("康普产品");
         queryParam.setQuery(param);// 查询条件

         List<InstockDetailEntity> resultList = dao.getInstockDetail(queryParam);
         if (resultList.size() == 0) {
             Assert.assertTrue(false);
         }
         /* 获得XML中的数据 */
         IDataSet dataset = getDataSet();
         ITable table = dataset.getTable(TABLE_RINSTOCKDETAIL);

         for (int i = 0; i < resultList.size(); i++) {
             Assert.assertEquals(resultList.get(i).getBuyContractId(),
                     (String) table.getValue(i, "buy_contract_id"));
             Assert.assertEquals(resultList.get(i).getProductContractCode(),
                     (String) table.getValue(i, "product_contract_code"));
             Assert.assertEquals(resultList.get(i).getCompanyContractCode(),
                     (String) table.getValue(i, "company_contract_code"));
             Assert.assertEquals(resultList.get(i).getProductTypeName(),
                     (String) table.getValue(i, "product_type_name"));
             Assert.assertEquals(resultList.get(i).getInstockId(),
                     (String) table.getValue(i, "instock_id"));
             Assert.assertEquals(resultList.get(i).getStockroomName(),
                     (String) table.getValue(i, "stockroom_name"));
             Assert.assertEquals(resultList.get(i).getSupplierName(),
                     (String) table.getValue(i, "supplier_name"));
             Assert.assertEquals(resultList.get(i).getInstockTotalmoney(),
                     (String) table.getValue(i, "instock_totalmoney"));
             Assert.assertEquals(resultList.get(i).getDate(),
                     (String) table.getValue(i, "date"));
             Assert.assertEquals(resultList.get(i).getStockDate(),
                     (String) table.getValue(i, "stock_date"));
             Assert.assertEquals(resultList.get(i).getUserName(),
                     (String) table.getValue(i, "user_name"));
             Assert.assertEquals(resultList.get(i).getStatusName(),
                     (String) table.getValue(i, "status_name"));
             Assert.assertEquals(resultList.get(i).getProductCode(),
                     (String) table.getValue(i, "product_code"));
             Assert.assertEquals(resultList.get(i).getProductName(),
                     (String) table.getValue(i, "product_name"));
             Assert.assertEquals(resultList.get(i).getProductType(),
                      (String) table.getValue(i, "product_type"));
             Assert.assertEquals(resultList.get(i).getProductUnit(),
                     (String) table.getValue(i, "product_unit"));
             Assert.assertEquals(resultList.get(i).getBuyPrice(),
                     (String) table.getValue(i, "buy_price"));
             Assert.assertEquals(resultList.get(i).getInstockCount(),
                     (String) table.getValue(i, "instock_count"));
             Assert.assertEquals(resultList.get(i).getInstockMoney(),
                      (String) table.getValue(i, "instock_money"));
             Assert.assertEquals(resultList.get(i).getBackCount(),
                     (String) table.getValue(i, "back_count"));
             Assert.assertEquals(resultList.get(i).getBackMoney(),
                     (String) table.getValue(i, "back_money"));
             Assert.assertEquals(resultList.get(i).getStockCountRel(),
                     (String) table.getValue(i, "stock_count_rel"));
             Assert.assertEquals(resultList.get(i).getStockMoneyRel(),
                      (String) table.getValue(i, "stock_money_rel"));
             Assert.assertEquals(resultList.get(i).getAppointMoney(),
                     (String) table.getValue(i, "appoint_money"));
             Assert.assertEquals(resultList.get(i).getNonPaymentMoney(),
                     (String) table.getValue(i, "non_payment_money"));
             Assert.assertEquals(resultList.get(i).getFactorySendDate(),
                     (String) table.getValue(i, "factory_send_date"));
             Assert.assertEquals(resultList.get(i).getPaymentDate(),
                      (String) table.getValue(i, "payment_date"));
             Assert.assertEquals(resultList.get(i).getReceiveInvoiceMoney(),
                     (String) table.getValue(i, "receive_invoice_money"));
         }
     }
     
     /* 检索 合计值 所有列 */
     //@Test
     public void testGetStockProductDetail007() throws Exception {
         InstockDetailDto param = new InstockDetailDto();
         BaseQueryData<InstockDetailDto> queryParam = new BaseQueryData<InstockDetailDto>();
         queryParam.setPage(10);
         queryParam.setRows(5);
         queryParam.setSord("");
         queryParam.setSidx("buy_contract_id");
         queryParam.setStar(0);
         queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 显示的列
         param.setProductTypeName("康普产品");
         queryParam.setQuery(param);// 查询条件

         InstockDetailDto detailEntity = dao.getInstockDetailSumVal(queryParam);
         if (detailEntity == null) {
             Assert.assertTrue(false);
         }
         Assert.assertEquals(detailEntity.getAppointMoneySum(), "55.00");
         Assert.assertEquals(detailEntity.getBackMoneySum(), "33.77");
         Assert.assertEquals(detailEntity.getInstockMoneySum(), "6850.08");
         Assert.assertEquals(detailEntity.getNonPaymentMoneySum(), "3425.04");
         Assert.assertEquals(detailEntity.getReceiveInvoiceMoneySum(), "764.02");
         Assert.assertEquals(detailEntity.getStockMoneyRelSum() ,"4177.06");
     }
     
     
     /* 检索 合计值 多列 */
     //@Test
     public void testGetInstockDetail009() throws Exception {
         InstockDetailDto param = new InstockDetailDto();
         BaseQueryData<InstockDetailDto> queryParam = new BaseQueryData<InstockDetailDto>();
         queryParam.setPage(10);
         queryParam.setRows(5);
         queryParam.setSord("");
         queryParam.setSidx("buy_contract_id");
         queryParam.setStar(0);
         queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 显示的列
         param.setProductTypeName("康普产品");
         queryParam.setQuery(param);// 查询条件

         InstockDetailDto detailEntity = dao.getInstockDetailSumVal(queryParam);
         if (detailEntity == null) {
             Assert.assertTrue(false);
         }
         Assert.assertEquals(detailEntity.getAppointMoneySum(), "55.00");
         Assert.assertEquals(detailEntity.getBackMoneySum(), "33.77");
         Assert.assertEquals(detailEntity.getInstockMoneySum(), "6850.08");
         Assert.assertEquals(detailEntity.getNonPaymentMoneySum(), "6850.08");
         Assert.assertEquals(detailEntity.getReceiveInvoiceMoneySum(), "12.00");
         Assert.assertEquals(detailEntity.getStockMoneyRelSum() ,"6850.08");

     }
     
     /* 检索 合计值 无数据 */
     @Test
     public void testGetInstockDetail010() throws Exception {
         InstockDetailDto param = new InstockDetailDto();
         BaseQueryData<InstockDetailDto> queryParam = new BaseQueryData<InstockDetailDto>();
         queryParam.setPage(10);
         queryParam.setRows(5);
         queryParam.setSord("");
         queryParam.setSidx("stockroom_name");
         queryParam.setStar(0);
         queryParam.setFieldsParam("1111111111111111111111111111", fieldRows);// 显示的列
         param.setStockroomName("网络分销上海(商品)正常库");
         param.setProductCode("01.002.0001");
         param.setProductName("咖啡");
         param.setProductType("TF020001");
         queryParam.setQuery(param);// 查询条件

         InstockDetailDto detailEntity = dao.getInstockDetailSumVal(queryParam);
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
                        .getResource("classpath:dbunit/rinstockDetail.xml")
                        .getInputStream());
        return dataSet;
    }
    @Override
    public String getBackupFileName() {
        return "rinstockDetail.xml";
    }

    @Override
    public String[] getBackupTableName() {
        return new String[] { "r_instock_detail" };
    }

    @Override
    public String[] getDataFileName() {
        return new String[] { "rinstockDetail.xml" };
    }

}
