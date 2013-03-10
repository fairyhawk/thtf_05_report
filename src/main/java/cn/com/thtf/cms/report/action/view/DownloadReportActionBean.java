package cn.com.thtf.cms.report.action.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.Validate;

import cn.com.thtf.cms.report.constant.Constants;
import cn.com.thtf.cms.report.dto.DownLoadFileInfo;
import cn.com.thtf.cms.report.util.FileFilterUtil;
import cn.shiy.common.cws.action.BaseActionBean;
import cn.shiy.common.cws.security.CommonRole;
import cn.shiy.common.cws.security.Secure;

@Secure
public class DownloadReportActionBean extends BaseActionBean {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Setter
    @Getter
    private DownLoadFileInfo downLoadFileInfo = new DownLoadFileInfo();

    @Setter
    @Getter
    @Validate(required = true, on = { "downLoad" })
    private String reportId;

    @Setter
    @Getter
    @Validate(required = true, on = { "downLoad" })
    private String week;

    /**
     * 显示下载文件列表
     * 
     * @return
     * @throws Exception
     */
    @DefaultHandler
    public Resolution getFileList() throws Exception {

        List<CommonRole> roleList = this.getACL().getRoleList();

        boolean hasRoleMessageAdmin = false;

        for (CommonRole role : roleList) {
            if (role.getRoleInt() == 2||role.getRoleInt() == 16||role.getRoleInt() == 17) {
                hasRoleMessageAdmin = true;
                break;
            }
        }
        if (!hasRoleMessageAdmin) {
            this.addErrorMsg("非权限访问");
            return new ForwardResolution("/");
        }

        downLoadFileInfo = getFileListInfo();

        this.getContext().getRequest().setAttribute("downLoadFileInfo", downLoadFileInfo);

        return new ForwardResolution("/jsp/report/reportdownload.jsp");
    }

    /**
     * 获得文件夹符合条件的文件
     * 
     * @return DownLoadFileInfo
     */
    private DownLoadFileInfo getFileListInfo() {

        DownLoadFileInfo downLoadFileInfo = new DownLoadFileInfo();
        Map<String, List<String>> filemap = new HashMap<String, List<String>>();

        // 获取目录下所有的文件，按文件名分组存放
        File fileDirTmp = new File(Constants.CSVFILEPATH);
        // 文件名
        String tmpfileNameIndex = "";
        // 匹配的正则
        String regex = "\\.[1234567]\\.end";
        File[] files = null;
        // 销售合同汇总表
        tmpfileNameIndex = Constants.CSVFILE_SELLCONTRACT;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("001", getFileWeekInfoList(files));
        // 销售合同产品明细表
        tmpfileNameIndex = Constants.CSVFILE_SELLCONTRACTDETAIL;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("002", getFileWeekInfoList(files));
        // 退货合同明细表
        tmpfileNameIndex = Constants.CSVFILE_SELLBACKCONTRACTDETAIL;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("003", getFileWeekInfoList(files));
        // 发货单产品明细
        tmpfileNameIndex = Constants.CSVFILE_SENDGOODS;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("004", getFileWeekInfoList(files));
        // 退货明细表
        tmpfileNameIndex = Constants.CSVFILE_SELLBACKGOODSDETAIL;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("005", getFileWeekInfoList(files));
        // 回款表
        tmpfileNameIndex = Constants.CSVFILE_MRETURN;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("006", getFileWeekInfoList(files));
        // 退款明细表
        tmpfileNameIndex = Constants.CSVFILE_REFUND;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("007", getFileWeekInfoList(files));
        // 开票汇总
        tmpfileNameIndex = Constants.CSVFILE_SELLINVOICE;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("008", getFileWeekInfoList(files));
        // 退票明细表
        tmpfileNameIndex = Constants.CSVFILE_SELLINVOICEDETAIL;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("009", getFileWeekInfoList(files));
        // 发货单汇总表
        tmpfileNameIndex = Constants.CSVFILE_RSENDGOODSSUMMARY;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("010", getFileWeekInfoList(files));
        // 库存数量明细
        tmpfileNameIndex = Constants.CSVFILE_STOCKPRODUCTDETAIL;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("011", getFileWeekInfoList(files));
        // 运费明细表
        tmpfileNameIndex = Constants.CSVFILE_TRANSPORTFAREDETAIL;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("012", getFileWeekInfoList(files));
        // 运费分摊
        tmpfileNameIndex = Constants.CSVFILE_SHARING;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("013", getFileWeekInfoList(files));
        // 动态盘点表
        tmpfileNameIndex = Constants.CSVFILE_DYNAMIC_STOCKTAKE;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("014", getFileWeekInfoList(files));
        // 采购合同汇总
        tmpfileNameIndex = Constants.CSVFILE_BUYCONTRACT;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("015", getFileWeekInfoList(files));
        // 采购合同明细表
        tmpfileNameIndex = Constants.CSVFILE_BUYCONTRACTDETAIL;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("016", getFileWeekInfoList(files));
        // 入库单明细
        tmpfileNameIndex = Constants.CSVFILE_INVOICEDETAIL;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("017", getFileWeekInfoList(files));
        // 采购付款汇总
        tmpfileNameIndex = Constants.CSVFILE_BUYPAYMENT;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("018", getFileWeekInfoList(files));
        // 采购合同收票
        tmpfileNameIndex = Constants.CSVFILE_RECEIVEINVOICE;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("019", getFileWeekInfoList(files));
        // 客户信用明细
        tmpfileNameIndex = Constants.CSVFILE_CUSTOMERCREDIT;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("020", getFileWeekInfoList(files));
        // 客户销售经理对应
        tmpfileNameIndex = Constants.CSVFILE_CUSTOMERSELLMAGMAP;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("021", getFileWeekInfoList(files));
        // 采购付款明细
        tmpfileNameIndex = Constants.CSVFILE_BUYPAYMENTDETAIL;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("022", getFileWeekInfoList(files));
        // 操作记录
        tmpfileNameIndex = Constants.CSVFILE_BUSILOG;
        files = fileDirTmp.listFiles(new FileFilterUtil(tmpfileNameIndex + regex));
        filemap.put("023", getFileWeekInfoList(files));

        downLoadFileInfo.setFilemap(filemap);

        return downLoadFileInfo;
    }

    /**
     * 文件下载
     * 
     * @return
     */
    public Resolution downLoad() {

        List<CommonRole> roleList = this.getACL().getRoleList();

        boolean hasRoleMessageAdmin = false;

        for (CommonRole role : roleList) {
            if (role.getRoleInt() == 2||role.getRoleInt() == 16||role.getRoleInt() == 17) {
                hasRoleMessageAdmin = true;
                break;
            }
        }
        if (!hasRoleMessageAdmin) {
            this.addErrorMsg("非权限访问");
            return new ForwardResolution("/");
        }

        String fileName = Constants.CSVFILEPATH + getDownLoadFileName(reportId, week);
        FileInputStream inputStream = null;
        StreamingResolution resolution = null;
        try {
            inputStream = new FileInputStream(new File(fileName));
            resolution = new StreamingResolution("application/zip", inputStream);
            resolution.setFilename(URLEncoder.encode(getChsName(reportId, week),
                    "UTF-8"));
        } catch (Exception e) {
            log.error("download error!" + e);
            this.addErrorMsg("下载信息错误");
            return new ForwardResolution("/jsp/wrongModule.jsp");
        }

        return resolution;

    }

    /**
     * 获得文件名带.zip
     * 
     * @param reportId
     * @param WeekId
     * @return
     */
    private String getDownLoadFileName(String reportId, String WeekId) {

        String nameEnd = "." + WeekId + ".zip";

        if ("001".equals(reportId)) {
            return Constants.CSVFILE_SELLCONTRACT + nameEnd;// 销售合同汇总表
        }
        if ("002".equals(reportId)) {
            return Constants.CSVFILE_SELLCONTRACTDETAIL + nameEnd;// 销售合同产品明细表
        }

        if ("003".equals(reportId)) {
            return Constants.CSVFILE_SELLBACKCONTRACTDETAIL + nameEnd;// 退货合同明细表
        }
        if ("004".equals(reportId)) {
            return Constants.CSVFILE_SENDGOODS + nameEnd;// 发货单产品明细
        }
        if ("005".equals(reportId)) {
            return Constants.CSVFILE_SELLBACKGOODSDETAIL + nameEnd;// 退货明细表
        }

        if ("006".equals(reportId)) {
            return Constants.CSVFILE_MRETURN + nameEnd;// 回款表
        }
        if ("007".equals(reportId)) {
            return Constants.CSVFILE_REFUND + nameEnd;// 退款明细表
        }
        if ("008".equals(reportId)) {
            return Constants.CSVFILE_SELLINVOICE + nameEnd;// 开票汇总
        }
        if ("009".equals(reportId)) {
            return Constants.CSVFILE_SELLINVOICEDETAIL + nameEnd;// 退票明细表
        }
        if ("010".equals(reportId)) {
            return Constants.CSVFILE_RSENDGOODSSUMMARY + nameEnd;// 发货单汇总表
        }
        if ("011".equals(reportId)) {
            return Constants.CSVFILE_STOCKPRODUCTDETAIL + nameEnd;// 库存数量明细
        }
        if ("012".equals(reportId)) {
            return Constants.CSVFILE_TRANSPORTFAREDETAIL + nameEnd;// 运费明细表
        }
        if ("013".equals(reportId)) {
            return Constants.CSVFILE_SHARING + nameEnd;// 运费分摊
        }
        if ("014".equals(reportId)) {
            return Constants.CSVFILE_DYNAMIC_STOCKTAKE + nameEnd;// 动态盘点表
        }
        if ("015".equals(reportId)) {
            return Constants.CSVFILE_BUYCONTRACT + nameEnd;// 采购合同汇总
        }
        if ("016".equals(reportId)) {
            return Constants.CSVFILE_BUYCONTRACTDETAIL + nameEnd;// 采购合同明细表
        }
        if ("017".equals(reportId)) {
            return Constants.CSVFILE_INVOICEDETAIL + nameEnd;// 入库单明细
        }
        if ("018".equals(reportId)) {
            return Constants.CSVFILE_BUYPAYMENT + nameEnd;// 采购付款汇总
        }
        if ("019".equals(reportId)) {
            return Constants.CSVFILE_RECEIVEINVOICE + nameEnd;// 采购合同收票
        }
        if ("020".equals(reportId)) {
            return Constants.CSVFILE_CUSTOMERCREDIT + nameEnd;// 客户信用明细
        }
        if ("021".equals(reportId)) {
            return Constants.CSVFILE_CUSTOMERSELLMAGMAP + nameEnd;// 客户销售经理对应
        }
        if ("022".equals(reportId)) {
            return Constants.CSVFILE_BUYPAYMENTDETAIL + nameEnd;// 采购付款明细
        }
        if ("023".equals(reportId)) {
            return Constants.CSVFILE_BUSILOG + nameEnd;// 操作明细
        }

        return "";

    }

    /**
     * 保存时显示的中文化文件名称
     * 
     * @param reportId
     * @return
     */
    private String getChsName(String reportId, String week) {
        
        String  endName="."+week+".zip";

        if ("001".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SELLCONTRACT)+endName;// 销售合同汇总表
        }
        if ("002".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SELLCONTRACTDETAIL)+endName;// 销售合同产品明细表
        }

        if ("003".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SELLBACKCONTRACTDETAIL)+endName;// 退货合同明细表
        }
        if ("004".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SENDGOODS)+endName;// 发货单产品明细
        }
        if ("005".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SELLBACKGOODSDETAIL)+endName;// 退货明细表
        }

        if ("006".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_MRETURN)+endName;// 回款表
        }
        if ("007".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_REFUND)+endName;// 退款明细表
        }
        if ("008".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SELLINVOICE)+endName;// 开票汇总
        }
        if ("009".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SELLINVOICEDETAIL)+endName;// 退票明细表
        }
        if ("010".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_RSENDGOODSSUMMARY)+endName;// 发货单汇总表
        }
        if ("011".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_STOCKPRODUCTDETAIL)+endName;// 库存数量明细
        }
        if ("012".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_TRANSPORTFAREDETAIL)+endName;// 运费明细表
        }
        if ("013".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SHARING)+endName;// 运费分摊
        }
        if ("014".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_DYNAMIC_STOCKTAKE)+endName;// 动态盘点表
        }
        if ("015".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_BUYCONTRACT)+endName;// 采购合同汇总
        }
        if ("016".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_BUYCONTRACTDETAIL)+endName;// 采购合同明细表
        }
        if ("017".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_INVOICEDETAIL)+endName;// 入库单明细
        }
        if ("018".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_BUYPAYMENT)+endName;// 采购付款汇总
        }
        if ("019".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_RECEIVEINVOICE)+endName;// 采购合同收票
        }
        if ("020".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_CUSTOMERCREDIT)+endName;// 客户信用明细
        }
        if ("021".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_CUSTOMERSELLMAGMAP)+endName;// 客户销售经理对应
        }
        if ("022".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_BUYPAYMENTDETAIL)+endName;// 采购付款明细
        }
        if ("023".equals(reportId)) {
            return Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_BUSILOG)+endName;// 操作记录
        }

        return "";

    }

    /**
     * 将周截取存放到list中
     * 
     * @param files
     * @return
     */
    public List<String> getFileWeekInfoList(File[] files) {

        List<String> list = new ArrayList<String>();
        if (files == null) {
            return list;
        }
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            int endIndex = fileName.indexOf(".end");
            // 截取周
            String tmpWeek = fileName.substring(endIndex - 1, endIndex);
            list.add(tmpWeek);
        }
        return list;

    }
}
