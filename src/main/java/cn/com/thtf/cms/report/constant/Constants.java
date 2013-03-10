/**
 * ClassName  Constants
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-9-6
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.constant;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * Constants
 * 
 * @author Lubo
 */
public class Constants {

    /** 翻页,每页显示数据条数 */
    public static int PAGESIZE = 10;
    /**
     * 后台登录请求发送IP限定
     */
    public static String HIDDENLOGIN_IP = "127.0.0.1";
    /**
     * 一次取数据的条数
     */
    public static final long FETCHSIZE = 1000;
    /**
     * 同时生成csv文件数量
     */
    public static int TRANSCSV_MAXCOUNT = 1;
    /**
     * CSV文件保存目录
     */
    public static String CSVFILEPATH = null;
    public static String CSVTMPFILEPATH = null;
    /** 退款 */
    public static String CSVFILE_REFUND = null;
    /** 回款 */
    public static String CSVFILE_MRETURN = null;
    /** 发货单汇总 */
    public static String CSVFILE_RSENDGOODSSUMMARY = null;
    /**
     * 退货单产品明细CSV文件名称
     */
    public static String CSVFILE_SELLBACKGOODSDETAIL = null;
    // 退票产品明细
    public static String CSVFILE_SELLINVOICEDETAIL = null;
    // 采购合同收票表
    public static String CSVFILE_RECEIVEINVOICE = null;
    // 库存产品明细表
    public static String CSVFILE_STOCKPRODUCTDETAIL = null;
    // 客户销售经理对应
    public static String CSVFILE_CUSTOMERSELLMAGMAP = null;
    /** 运费分摊CSV文件名称 */
    public static String CSVFILE_SHARING = null;
    /** 开票汇总CSV文件名称 */
    public static String CSVFILE_SELLINVOICE = null;
    /** 入库明细CSV文件名称 */
    public static String CSVFILE_INVOICEDETAIL = null;
    /**
     * 付款汇总CSV文件名称
     */
    public static String CSVFILE_BUYPAYMENT = null;
    /**
     * 付款明细CSV文件名称
     */
    public static String CSVFILE_BUYPAYMENTDETAIL = null;
    /**
     * 运费明细CSV文件名称
     */
    public static String CSVFILE_TRANSPORTFAREDETAIL = null;

    /**
     * 发货单产品明细CSV文件名称
     */
    public static String CSVFILE_SENDGOODS = null;
    /**
     * 采购合同汇总CSV文件名称
     */
    public static String CSVFILE_BUYCONTRACT = null;
    /**
     * 销售合同汇总CSV文件名
     */

    public static String CSVFILE_SELLCONTRACT = null;

    /**
     * 采购合同产品明细CSV文件名称
     */
    public static String CSVFILE_BUYCONTRACTDETAIL = null;
    /**
     * 客户信用明细CSV文件名称
     */
    public static String CSVFILE_CUSTOMERCREDIT = null;

    /**
     * 销售合同产品明细
     **/
    public static String CSVFILE_SELLCONTRACTDETAIL = null;

    /**
     * 退货合同产品明细
     **/
    public static String CSVFILE_SELLBACKCONTRACTDETAIL = null;

    /** 动态盘点表 */
    public static String CSVFILE_DYNAMIC_STOCKTAKE = null;
    
    /**
     *  操作记录
     */
    public static String CSVFILE_BUSILOG =null;
    
    /**
     * 站内信附件保存目录
     */
    public static String ACCESSORYFILEPATH = null;
    
    /** 上传文件的 类型*/
    public final static String FILETYPE[] = { ".txt", ".pdf", ".xml", ".rtf", ".doc",
        ".docx", ".xls", ".xlsx", ".rar"};
    
    /** 上传文件大小 */
    public static String MAX_POST_SIZE = null;
    
    /** 上传文件过大导致失败的错误提示 */
    public static String MAX_POST_SIZE_ERR_MSG = null;

    /** 中文名称 */
    public static Map<String, String> CSVFILENAME_ZH = new HashMap<String, String>();

    public static void init() throws DocumentException {
        SAXReader reader = new SAXReader();
        InputStream input = Constants.class.getResourceAsStream("/config.xml");
        Document doc = reader.read(input);
        Node root = doc.getRootElement();

        HIDDENLOGIN_IP = root.selectSingleNode("//config/hiddenLogin/ip").getText();

        // 文件名获得
        CSVFILEPATH = root.selectSingleNode("//config/csvFilePath").getText();
        CSVTMPFILEPATH = root.selectSingleNode("//config/csvTmpFilePath").getText();
        TRANSCSV_MAXCOUNT = Integer.parseInt(root.selectSingleNode("//config/transCsvMaxCount").getText());
        //站内信附件保存目录
        ACCESSORYFILEPATH = root.selectSingleNode("//config/accessoryFilePath").getText();
        //上传文件大小
        MAX_POST_SIZE = root.selectSingleNode("//config/max_post_size").getText();
        //上传文件过大导致失败的错误提示
        MAX_POST_SIZE_ERR_MSG = root.selectSingleNode("//config/max_post_size_err_msg").getText();

        CSVFILE_REFUND = root.selectSingleNode("//config/csvFileName/refund/en")
                .getText();
        CSVFILE_MRETURN = root.selectSingleNode("//config/csvFileName/mreturn/en")
                .getText();
        CSVFILE_RSENDGOODSSUMMARY = root.selectSingleNode(
                "//config/csvFileName/rsendgoodsSummary/en").getText();
        CSVFILE_SELLINVOICEDETAIL = root.selectSingleNode(
                "//config/csvFileName/sellinvoicedetail/en").getText();
        CSVFILE_RECEIVEINVOICE = root.selectSingleNode(
                "//config/csvFileName/receiveInvoice/en").getText();

        CSVFILE_STOCKPRODUCTDETAIL = root.selectSingleNode(
                "//config/csvFileName/stockProductDetail/en").getText();
        CSVFILE_CUSTOMERSELLMAGMAP = root.selectSingleNode(
                "//config/csvFileName/customerSellMagMap/en").getText();
        CSVFILE_SHARING = root.selectSingleNode("//config/csvFileName/sharing/en")
                .getText();
        CSVFILE_SELLINVOICE = root
                .selectSingleNode("//config/csvFileName/sellInvoice/en").getText();
        CSVFILE_SENDGOODS = root.selectSingleNode("//config/csvFileName/sendgoods/en")
                .getText();

        CSVFILE_BUYCONTRACT = root
                .selectSingleNode("//config/csvFileName/buycontract/en").getText();
        CSVFILE_BUYCONTRACTDETAIL = root.selectSingleNode(
                "//config/csvFileName/buycontractdetail/en").getText();
        CSVFILE_CUSTOMERCREDIT = root.selectSingleNode(
                "//config/csvFileName/customercredit/en").getText();
        CSVFILE_SELLCONTRACT = root.selectSingleNode(
                "//config/csvFileName/sellcontract/en").getText();
        CSVFILE_SELLBACKGOODSDETAIL = root.selectSingleNode(
                "//config/csvFileName/sellbackgoodsdetail/en").getText();

        CSVFILE_BUYPAYMENT = root.selectSingleNode("//config/csvFileName/buypayment/en")
                .getText();
        CSVFILE_BUYPAYMENTDETAIL = root.selectSingleNode(
                "//config/csvFileName/buypaymentdetail/en").getText();
        CSVFILE_SELLCONTRACTDETAIL = root.selectSingleNode(
                "//config/csvFileName/rsellcontractdetail/en").getText();
        CSVFILE_INVOICEDETAIL = root.selectSingleNode(
                "//config/csvFileName/invoicedetail/en").getText();
        CSVFILE_TRANSPORTFAREDETAIL = root.selectSingleNode(
                "//config/csvFileName/rtransportfaredetail/en").getText();

        CSVFILE_SELLBACKCONTRACTDETAIL = root.selectSingleNode(
                "//config/csvFileName/rsellbackcontractdetail/en").getText();
        // 动态盘点表
        CSVFILE_DYNAMIC_STOCKTAKE = root.selectSingleNode(
                "//config/csvFileName/dynamicstocktake/en").getText();
        
        CSVFILE_BUSILOG = root.selectSingleNode("//config/csvFileName/busilog/en").getText();

        CSVFILENAME_ZH.put(CSVFILE_REFUND,
                root.selectSingleNode("//config/csvFileName/refund/zh").getText());
        CSVFILENAME_ZH.put(CSVFILE_MRETURN,
                root.selectSingleNode("//config/csvFileName/mreturn/zh").getText());
        CSVFILENAME_ZH.put(CSVFILE_RSENDGOODSSUMMARY,
                root.selectSingleNode("//config/csvFileName/rsendgoodsSummary/zh")
                        .getText());
        CSVFILENAME_ZH.put(CSVFILE_SELLINVOICEDETAIL,
                root.selectSingleNode("//config/csvFileName/sellinvoicedetail/zh")
                        .getText());
        CSVFILENAME_ZH
                .put(CSVFILE_RECEIVEINVOICE,
                        root.selectSingleNode("//config/csvFileName/receiveInvoice/zh")
                                .getText());

        CSVFILENAME_ZH.put(CSVFILE_STOCKPRODUCTDETAIL,
                root.selectSingleNode("//config/csvFileName/stockProductDetail/zh")
                        .getText());
        CSVFILENAME_ZH.put(CSVFILE_CUSTOMERSELLMAGMAP,
                root.selectSingleNode("//config/csvFileName/customerSellMagMap/zh")
                        .getText());
        CSVFILENAME_ZH.put(CSVFILE_SHARING,
                root.selectSingleNode("//config/csvFileName/sharing/zh").getText());
        CSVFILENAME_ZH.put(CSVFILE_SELLINVOICE,
                root.selectSingleNode("//config/csvFileName/sellInvoice/zh").getText());
        CSVFILENAME_ZH.put(CSVFILE_SENDGOODS,
                root.selectSingleNode("//config/csvFileName/sendgoods/zh").getText());

        CSVFILENAME_ZH.put(CSVFILE_BUYCONTRACT,
                root.selectSingleNode("//config/csvFileName/buycontract/zh").getText());
        CSVFILENAME_ZH.put(CSVFILE_BUYCONTRACTDETAIL,
                root.selectSingleNode("//config/csvFileName/buycontractdetail/zh")
                        .getText());
        CSVFILENAME_ZH
                .put(CSVFILE_CUSTOMERCREDIT,
                        root.selectSingleNode("//config/csvFileName/customercredit/zh")
                                .getText());
        CSVFILENAME_ZH.put(CSVFILE_SELLCONTRACT,
                root.selectSingleNode("//config/csvFileName/sellcontract/zh").getText());
        CSVFILENAME_ZH.put(CSVFILE_SELLBACKGOODSDETAIL,
                root.selectSingleNode("//config/csvFileName/sellbackgoodsdetail/zh")
                        .getText());

        CSVFILENAME_ZH.put(CSVFILE_BUYPAYMENT,
                root.selectSingleNode("//config/csvFileName/buypayment/zh").getText());
        CSVFILENAME_ZH.put(CSVFILE_BUYPAYMENTDETAIL,
                root.selectSingleNode("//config/csvFileName/buypaymentdetail/zh")
                        .getText());
        CSVFILENAME_ZH.put(CSVFILE_SELLCONTRACTDETAIL,
                root.selectSingleNode("//config/csvFileName/rsellcontractdetail/zh")
                        .getText());
        CSVFILENAME_ZH.put(CSVFILE_INVOICEDETAIL,
                root.selectSingleNode("//config/csvFileName/invoicedetail/zh").getText());
        CSVFILENAME_ZH.put(CSVFILE_TRANSPORTFAREDETAIL,
                root.selectSingleNode("//config/csvFileName/rtransportfaredetail/zh")
                        .getText());

        CSVFILENAME_ZH.put(CSVFILE_SELLBACKCONTRACTDETAIL,
                root.selectSingleNode("//config/csvFileName/rsellbackcontractdetail/zh")
                        .getText());
        CSVFILENAME_ZH.put(CSVFILE_DYNAMIC_STOCKTAKE,
                root.selectSingleNode("//config/csvFileName/dynamicstocktake/zh")
                        .getText());
        CSVFILENAME_ZH.put(CSVFILE_BUSILOG, root.selectSingleNode("//config/csvFileName/busilog/zh")
                        .getText());
    }
}
