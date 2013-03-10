/**
 * ClassName  CSVConvertUtil
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-10-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.convert;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import au.com.bytecode.opencsv.CSVWriter;
import cn.com.thtf.cms.report.constant.Constants;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.shiy.common.cws.dao.GenericDao;

/**
 * DB数据->CSV文件转换
 * 
 * @author Shiy
 */
public class CSVConvertUtil {

    /** CSV文件Encoding */
    public static final String CSVENCODING = "GBK";

    /**
     * transToCSV
     * 
     * @param filename
     * @param rows
     * @param helper
     * @throws IOException
     */
    public static void transToCSV(String filename, List<?> rows, ConvertHelper helper)
            throws IOException {
        CSVWriter writer = new CSVWriter(new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), CSVENCODING)));
        writer.writeNext(helper.getTilte());
        for (Object obj : rows) {
            writer.writeNext(helper.getRow(obj));
        }
        writer.close();
    }

    /**
     * 分页取数据转换CSV
     * 
     * @param filename
     *            CSV文件名，如："abc.csv"
     * @param helper
     *            转换辅助
     * @param dao
     *            数据操作dao
     * @param dataMethodName
     *            取数据方法名，参数必须为(Date fromDate,BaseQueryData<?> queryParam)
     * @param fromDate
     *            Date fromDate
     * @throws IOException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    public static void transToCSV(String filename, ConvertHelper helper,
            GenericDao<?, ?> dao, String dataMethodName, BaseQueryData<?> queryParam)
            throws IOException, SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException,
            InterruptedException {

        // /* 删除 */
        // String endFilename = getZipFileNameYe(filename);
        // FileUtils.deleteQuietly(new File(endFilename));

        Method method = dao.getClass().getMethod(dataMethodName, BaseQueryData.class);

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(
                getZipFileName(filename)));
        zipOutputStream.setEncoding(CSVENCODING);

        zipOutputStream.putNextEntry(new ZipEntry(getEntityName(filename)));

        CSVWriter writer = new CSVWriter(new BufferedWriter(new OutputStreamWriter(
                zipOutputStream, CSVENCODING)));
        writer.writeNext(helper.getTilte());

        long offset = 0;
        long rows = Constants.FETCHSIZE;
        long curRows = 0;
        long foundRows = rows;
        boolean isFirst = true;

        while (curRows < foundRows) {
            curRows += rows;
            queryParam.setRows((int) rows);
            queryParam.setStar((int) offset);

            List<?> list = (List<?>) method.invoke(dao, queryParam);
            if (list == null || list.size() == 0) {
                break;
            }
            if (isFirst) {
                foundRows = dao.getFoundRows();
                isFirst = false;
            }
            for (Object obj : list) {
                writer.writeNext(helper.getRow(obj));
            }
            offset = curRows;
            Thread.sleep(50);
        }

        writer.close();

        // /* 新建 */
        // new File(getZipFileNameYe(filename)).createNewFile();
        // FileUtils.touch(new File(endFilename));
    }

    /**
     * getZipFileName
     * 
     * @param filename
     * @return
     */
    private static String getZipFileName(String filename) {
        StringBuffer sb = new StringBuffer(Constants.CSVTMPFILEPATH);
        sb.append(filename);
        return sb.toString();
    }

    /**
     * getEntityName
     * 
     * @param filename
     * @return
     */
    private static String getEntityName(String filename) {
        StringBuffer sb = new StringBuffer();
        sb.append(Constants.CSVFILENAME_ZH.get(filename.substring(0,
                filename.indexOf("_"))
                + ".csv"));
        return sb.toString();
    }
}
