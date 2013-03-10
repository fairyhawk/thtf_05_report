/**
 * ClassName  RealtimeDownloadActionBean
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-1-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.data;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import lombok.Getter;
import lombok.Setter;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.Validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.constant.Constants;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.util.DownloadController.OPERATION_STATUS;
import cn.com.thtf.cms.report.util.DownloadController;
import cn.com.thtf.cms.report.util.DownloadParam;
import cn.com.thtf.cms.report.util.DownloadResult;
import cn.shiy.common.cws.action.BaseActionBean;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;

/**
 * 实时下载
 * 
 * @author Lubo
 */
@Secure
public class RealtimeDownloadActionBean extends BaseActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(RealtimeDownloadActionBean.class);
    /** CommonService */
    private CommonService commonService = null;

    @Setter
    @Getter
    @Validate(required = true, on = { "checkDownLoadStatus", "downLoad" })
    private String nodeId;
    @Setter
    @Getter
    @Validate(required = true, on = { "checkDownLoadStatus" })
    private String type;

    /**
     * 文件下载
     * 
     * @return
     */
    public Resolution downLoad() {
        UserEntity user = (UserEntity) getACL().getUser();
        String fileName = Constants.CSVTMPFILEPATH + getFileName(user);

        log.debug("下载,User:{},NodeId:{},FileName:{}", new Object[] { user.getId(),
                nodeId, fileName });

        FileInputStream inputStream = null;
        StreamingResolution resolution = null;
        try {
            inputStream = new FileInputStream(new File(fileName));
            resolution = new StreamingResolution("application/zip", inputStream);
            resolution.setFilename(URLEncoder.encode(getCsvZhName(), "UTF-8"));
        } catch (Exception e) {
            log.error("下载失败!User:{},NodeId:{},Exception:{}", new Object[] { user.getId(),
                    nodeId, e });
            this.addErrorMsg("下载时发生错误,请联系管理员！");
            return new ForwardResolution("/jsp/wrongModule.jsp");
        }
        return resolution;
    }

    /**
     * 文件下载
     * 
     * @return
     */
    public Resolution checkDownLoadStatus() {
        UserEntity user = (UserEntity) getACL().getUser();
        String ip = this.getContext().getRequest().getRemoteAddr();

        /* log */
        log.debug("下载申请,User:{},NodeId:{},IP:{},Type:{}", new Object[] { user.getId(),
                nodeId, ip, type });

        /* 权限校验 */
        if (checkRoleNode(nodeId, user.getRoleId())) {
            String fileName = getFileName(user);

            if (fileName != null) {
                DownloadParam param = new DownloadParam();
                // param.setIp(ip);
                param.setUser(user);
                param.setNodeId(nodeId);
                param.setNodeKey(fileName);
                param.setType(OPERATION_STATUS.APP);
                param.setRequestType(type);

                DownloadResult appResult = DownloadController.getInstance().app(param);
                if (appResult.isResult()) {
                    log.debug("下载申请：可下载,User:{},NodeId:{},FileName:{}", new Object[] {
                            user.getId(), nodeId, fileName });
                    return new JSONResolution("0");
                } else {
                    log.debug(
                            "下载申请：其它,User:{},NodeId:{},FileName:{},Status:{}",
                            new Object[] { user.getId(), nodeId, fileName,
                                    appResult.getResultType() });
                    return new JSONResolution(appResult.getResultType());
                }
            } else {
                log.debug("下载申请：无下载内容,User:{},NodeId:{},", new Object[] { user.getId(),
                        nodeId });
                return new JSONResolution("8");
            }
        } else {
            log.debug("下载申请：权限错误,User:{},NodeId:{},",
                    new Object[] { user.getId(), nodeId });
            return new JSONResolution("9");
        }
    }

    /**
     * getFileName
     * 
     * @param user
     * @return
     */
    private String getFileName(UserEntity user) {
        commonService = getBean("commonService", CommonService.class);
        boolean itself = false;
        if ("1152".equals(nodeId) && user.getRoleId() == 10) {
            itself = true;
        }

        /* 获取文件名称 */
        String result = commonService.getUserFileParam(user, itself);

        if (result != null && !"".equals(result)) {
            StringBuffer sb = new StringBuffer();
            sb.append(getCsvName(nodeId).replaceAll(".csv", ""));
            sb.append("_");
            sb.append(result);
            sb.append(".csv.zip");
            return sb.toString();
        } else {
            return null;
        }
    }

    /**
     * checkRoleNode
     * 
     * @param nodeId
     * @param roleId
     * @return
     */
    private boolean checkRoleNode(String nodeId, int roleId) {
        if (roleId == 2 || roleId == 10 || roleId == 16 || roleId == 17) {
            return true;
        } else if ((roleId == 3 || roleId == 6 || roleId == 7 || roleId == 9)
                && ("1141".equals(nodeId) || "1142".equals(nodeId))) {
            return true;
        } else if (roleId == 5
                && ("1141".equals(nodeId) || "1142".equals(nodeId) || "1171"
                        .equals(nodeId))) {
            return true;
        } else if (roleId == 8 && ("1152".equals(nodeId) || "1153".equals(nodeId))) {
            return true;
        } else if (roleId == 11
                && ("1152".equals(nodeId) || "1153".equals(nodeId) || "1187"
                        .equals(nodeId))) {
            return true;
        } else if (roleId == 12 && ("1187".equals(nodeId) || "1191".equals(nodeId))) {
            return true;
        } else if (roleId == 13 && (!"1141".equals(nodeId) && !"1152".equals(nodeId))) {
            return true;
        } else if (roleId == 18 && (!"1172".equals(nodeId) && !"1191".equals(nodeId))) {
            return true;
        } else if ((roleId == 19 || roleId == 20)
                && ("1141".equals(nodeId) || "1142".equals(nodeId)
                        || "1187".equals(nodeId) || "1171".equals(nodeId))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getCsvName
     * 
     * @param nodeId
     * @return
     */
    private String getCsvName(String nodeId) {
        if ("1141".equals(nodeId)) {
            return Constants.CSVFILE_SELLCONTRACTDETAIL;
        } else if ("1142".equals(nodeId)) {
            return Constants.CSVFILE_SENDGOODS;
        } else if ("1152".equals(nodeId)) {
            return Constants.CSVFILE_BUYCONTRACTDETAIL;
        } else if ("1153".equals(nodeId)) {
            return Constants.CSVFILE_INVOICEDETAIL;
        } else if ("1187".equals(nodeId)) {
            return Constants.CSVFILE_STOCKPRODUCTDETAIL;
        } else if ("1171".equals(nodeId)) {
            return Constants.CSVFILE_SHARING;
        } else if ("1172".equals(nodeId)) {
            return Constants.CSVFILE_TRANSPORTFAREDETAIL;
        } else if ("1191".equals(nodeId)) {
            return Constants.CSVFILE_DYNAMIC_STOCKTAKE;
        } else {
            return null;
        }
    }

    /**
     * 保存时显示的中文化文件名称
     * 
     * @return
     */
    private String getCsvZhName() {
        StringBuffer sb = new StringBuffer();
        if ("1141".equals(nodeId)) {
            sb.append(Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SELLCONTRACTDETAIL));
        } else if ("1142".equals(nodeId)) {
            sb.append(Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SENDGOODS));
        } else if ("1152".equals(nodeId)) {
            sb.append(Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_BUYCONTRACTDETAIL));
        } else if ("1153".equals(nodeId)) {
            sb.append(Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_INVOICEDETAIL));
        } else if ("1187".equals(nodeId)) {
            sb.append(Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_STOCKPRODUCTDETAIL));
        } else if ("1171".equals(nodeId)) {
            sb.append(Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_SHARING));
        } else if ("1172".equals(nodeId)) {
            sb.append(Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_TRANSPORTFAREDETAIL));
        } else if ("1191".equals(nodeId)) {
            sb.append(Constants.CSVFILENAME_ZH.get(Constants.CSVFILE_DYNAMIC_STOCKTAKE));
        } else {
            sb.append("未知");
        }
        return sb.append(".zip").toString();
    }
}
