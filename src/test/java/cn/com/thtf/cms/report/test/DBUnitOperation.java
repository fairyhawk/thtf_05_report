/**
 * ClassName  DBUnitOperation
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-9-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import cn.shiy.common.cws.utils.Container;

/**
 * 
 * @author Shiy
 */

public class DBUnitOperation implements TestOperation {

    protected static final String BKPACKAGE = "/dbbkup/";

    /**
     * db连接
     */
    protected IDatabaseConnection connection;

    /**
     * 备份的dataset
     */
    protected IDataSet bkupDataSet;

    /**
     * 备份文件名
     */
    protected String backupFileName;

    /**
     * 备份表名
     */
    protected String[] backupTableName;

    /**
     * 测试数据文件名
     */
    protected String[] dataFileName;

    public DBUnitOperation(NeedDBUnit test) {
        backupFileName = test.getBackupFileName();
        backupTableName = test.getBackupTableName();
        dataFileName = test.getDataFileName();
    }

    /**
     * @throws SQLException
     * @throws DatabaseUnitException
     * @throws IOException
     * @see cn.com.thtf.cms.report.test.TestOperation#doSetUp()
     */
    @Override
    public void doSetUp() throws DatabaseUnitException, SQLException, IOException {
        connection = new DatabaseConnection(Container.getBean("dataSource",
                DataSource.class).getConnection());

        if (backupTableName == null || backupTableName.length == 0) {
            throw new NullPointerException("backupTableName must be set");
        }
        if (backupTableName == null || backupTableName.length == 0) {
            throw new NullPointerException("backupTableName must be set");
        }
        bkupTable();
        insertData();
    }

    /**
     * @throws SQLException
     * @throws DatabaseUnitException
     * @see cn.com.thtf.cms.report.test.TestOperation#doTearDown()
     */
    @Override
    public void doTearDown() throws DatabaseUnitException, SQLException {
        restoreTable();
        connection.close();
    }

    protected void insertData() throws IOException, DatabaseUnitException, SQLException {
        if (this.dataFileName != null && this.dataFileName.length > 0) {
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);

            for (String name : dataFileName) {
                String classPathName = "classpath:/dbunit/" + name;
                IDataSet expectDataSet = builder.build(Container.getApplicationContext()
                        .getResource(classPathName).getInputStream());
                DatabaseOperation.CLEAN_INSERT.execute(connection, expectDataSet);
            }

        }
    }

    protected void bkupTable() throws IOException, DataSetException {
        QueryDataSet tmpDataSet = new QueryDataSet(connection, false);

        ITable tables[] = new ITable[backupTableName.length];
        for (int i = 0; i < backupTableName.length; i++) {
            tmpDataSet.addTable(backupTableName[i]);
            tables[i] = tmpDataSet.getTable(backupTableName[i]);
        }
        bkupDataSet = new DefaultDataSet(tables);

        String bkDirName = Container.getApplicationContext().getResource(BKPACKAGE)
                .getFile().getAbsolutePath();
        OutputStream os = new FileOutputStream(bkDirName + File.separator
                + backupFileName);
        FlatXmlDataSet.write(bkupDataSet, os);
        os.close();
    }

    protected void restoreTable() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(connection, bkupDataSet);
    }
}
