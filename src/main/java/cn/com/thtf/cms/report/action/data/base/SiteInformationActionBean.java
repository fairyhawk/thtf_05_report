/**
 * ClassName  SiteInformationActionBean
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-2-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.data.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.action.data.BasePageActionBean;
import cn.com.thtf.cms.report.constant.Constants;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.BaseResultData;
import cn.com.thtf.cms.report.dto.SiteInformationDto;
import cn.com.thtf.cms.report.dto.SiteInformationOfShowDto;
import cn.com.thtf.cms.report.dto.SiteInformationSubjectDto;
import cn.com.thtf.cms.report.entity.SiteInformationEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.base.SiteInformationService;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;

/**
 * SiteInformationActionBean
 * 
 * @author Lubo
 */
@Secure
public class SiteInformationActionBean extends BasePageActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(SiteInformationActionBean.class);

    /** SiteInformationService */
    private SiteInformationService service = null;
    @Setter
    @Getter
    @ValidateNestedProperties({
            @Validate(field = "subject", trim = true, required = true, on = {
                    "addSiteInfo", "updateSiteInfo" }),
            @Validate(field = "text", trim = true, required = true, on = { "addSiteInfo",
                    "updateSiteInfo" }) })
    private SiteInformationEntity siteInfomationEntity = new SiteInformationEntity();
    @Setter
    @Getter
    @Validate(required = true, on = { "viewSiteInfo", "updateSiteInfoInit",
            "deleteSiteInfo" })
    private Integer siteInformationId;
    @Setter
    @Getter
    private FileBean uploadFile;
    @Setter
    @Getter
    private long uploadFileSize;
    @Setter
    @Getter
    private String msgId;// msgId
    @Setter
    @Getter
    /** 检索条件 */
    @ValidateNestedProperties({ @Validate(field = "subject", trim = true),
            @Validate(field = "text", trim = true) })
    private SiteInformationDto param = new SiteInformationDto();

    /**
     * siteInformationList
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @DefaultHandler
    public Resolution siteInformationList() {
        log.debug("siteInformationList");

        /* 封装查询参数 */
        BaseQueryData<SiteInformationDto> queryParam = new BaseQueryData<SiteInformationDto>();
        queryParam.setPage(this.getPage());
        queryParam.setRows(Constants.PAGESIZE);
        queryParam.setStar((this.getPage() - 1) * Constants.PAGESIZE);
        queryParam.setQuery(param);// 查询条件

        service = getBean("siteInformationService", SiteInformationService.class);
        service.getSiteList(queryParam);

        /* 判断错误 */
        if (this.hasError()) {
            log.error("检索发生错误!");
        }

        /* 封装返回结果 */
        BaseResultData result = new BaseResultData(queryParam.getPage(),
                queryParam.getRecords(), queryParam.getTotal());
        result.setUrl("/data/base/SiteInformation.action?siteInformationList=");
        result.setQuery("param.subject", param.getSubject());
        result.setQuery("param.text", param.getText());
        result.setQuery("param.filename", param.getFilename());

        getRequest().setAttribute("siteList",
                (List<SiteInformationEntity>) queryParam.getDataList());
        getRequest().setAttribute("page", result);

        return new ForwardResolution("/jsp/report/base/siteInformation.jsp");
    }

    /**
     * 新建站内信
     * 
     * @return
     * @author zhangzx
     */
    public Resolution addSiteInfo() {
        log.debug("siteInfoAdd");

        UserEntity user = (UserEntity) getACL().getUser();

        siteInfomationEntity.setUserId(user.getId());

        if (siteInfomationEntity.getRollFlag() == null) {
            siteInfomationEntity.setRollFlag(1);
        }
        service = getBean("siteInformationService", SiteInformationService.class);

        if (uploadFile != null) {
            // 上传文件、获得新文件名
            if (!upload()) {
                return new ForwardResolution("/jsp/wrongModule.jsp");
            }
        }
        boolean result = service.insertSiteInfo(siteInfomationEntity);

        if (!result) {
            log.error("新建站内信失败!User:{}", user.getId());
            this.addErrorMsg("新建站内信发生错误,请联系管理员！");
            return new ForwardResolution("/jsp/wrongModule.jsp");
        }

        return new ForwardResolution(SiteInformationActionBean.class,
                "siteInformationList");
    }

    /**
     * 查看站内信
     * 
     * @return
     * @author zhangzx
     */
    public Resolution viewSiteInfo() {
        log.debug("siteInfoView");

        service = getBean("siteInformationService", SiteInformationService.class);

        siteInfomationEntity = service.getSiteInfo(siteInformationId);

        String siteText = siteInfomationEntity.getText();

        if (siteText != null) {
            siteText = siteText.replaceAll("\r\n", "<br>");
            siteText = siteText.replaceAll("\r", "<br>");
            siteText = siteText.replaceAll("\n", "<br>");
            siteText = siteText.replaceAll(" ", "&nbsp;");
            siteInfomationEntity.setText(siteText);
        }

        return new ForwardResolution("/jsp/report/base/siteInfoView.jsp");
    }

    /**
     * 删除站内信
     * 
     * @return
     * @author zhangzx
     */
    public Resolution deleteSiteInfo() {
        log.debug("siteInfoDelete");

        UserEntity user = (UserEntity) getACL().getUser();

        service = getBean("siteInformationService", SiteInformationService.class);

        SiteInformationEntity siteInfo = service.getSiteInfo(siteInformationId);

        boolean result = service.deleteSiteInfo(siteInformationId);

        if (!result) {
            log.error("删除站内信失败!User:{}", new Object[] { user.getId(), });
            this.addErrorMsg("删除站内信发生错误,请联系管理员！");
            return new ForwardResolution("/jsp/wrongModule.jsp");
        }

        String filePath = Constants.ACCESSORYFILEPATH + siteInfo.getFilepath();

        if (siteInfo.getFilepath() != null) {
            // 删除文件
            FileUtils.deleteQuietly(new File(filePath));
        }

        return new ForwardResolution(SiteInformationActionBean.class,
                "siteInformationList");
    }

    /**
     * 修改站内信
     * 
     * @return
     * @author zhangzx
     */
    public Resolution updateSiteInfo() {
        log.debug("siteInfoUpdate");

        boolean result = false;

        UserEntity user = (UserEntity) getACL().getUser();

        siteInfomationEntity.setUserId(user.getId());

        if (siteInfomationEntity.getRollFlag() == null) {
            siteInfomationEntity.setRollFlag(1);
        }
        service = getBean("siteInformationService", SiteInformationService.class);
        // 更新前的entity
        SiteInformationEntity siteInfo = service
                .getSiteInfo(siteInfomationEntity.getId());

        if (uploadFile != null) {
            // 旧的文件路径
            String oldFilePath = Constants.ACCESSORYFILEPATH
                    + service.getSiteInfo(siteInfomationEntity.getId()).getFilepath();

            // 上传文件、获得新文件名
            if (upload()) {
                // 删除旧的文件
                if (oldFilePath != null) {
                    FileUtils.deleteQuietly(new File(oldFilePath));
                }
            } else {
                return new ForwardResolution("/jsp/wrongModule.jsp");
            }
        } else {
            siteInfomationEntity.setFilename(siteInfo.getFilename());
            siteInfomationEntity.setFilepath(siteInfo.getFilepath());
        }
        result = service.updateSiteInfo(siteInfomationEntity);

        if (!result) {
            log.error("修改站内信失败!User:{}", user.getId());
            this.addErrorMsg("修改站内信发生错误,请联系管理员！");
            return new ForwardResolution("/jsp/wrongModule.jsp");
        }

        return new ForwardResolution(SiteInformationActionBean.class,
                "siteInformationList");
    }

    /**
     * 新建初始化
     * 
     * @return
     * @author zhangzx
     */
    public Resolution addSiteInfoInit() {
        log.debug("toaddSiteInfo");

        return new ForwardResolution("/jsp/report/base/siteInfoAdd.jsp");
    }

    /**
     * 修改初始化
     * 
     * @return
     * @author zhangzx
     */
    public Resolution updateSiteInfoInit() {
        log.debug("toupdateSiteInfo");

        service = getBean("siteInformationService", SiteInformationService.class);

        siteInfomationEntity = service.getSiteInfo(siteInformationId);

        return new ForwardResolution("/jsp/report/base/siteInfoUpdate.jsp");
    }

    /**
     * 上传附件
     * 
     * @return
     * @author zhangzx
     */
    private boolean upload() {

        UserEntity user = (UserEntity) getACL().getUser();

        log.debug(
                "上传,User:{},Upload File:{},File size:{},File Content type:{}",
                new Object[] { user.getId(), uploadFile.getFileName(),
                        uploadFile.getSize(), uploadFile.getContentType() });

        // 文件大小限制
        if (uploadFile.getSize() > Integer.parseInt(Constants.MAX_POST_SIZE)) {
            log.error("User:{},上传文件失败:" + Constants.MAX_POST_SIZE_ERR_MSG, user.getId());
            this.addErrorMsg("上传文件失败:" + Constants.MAX_POST_SIZE_ERR_MSG);
            return false;
        }

        // 文件类型限制
        String filetype[] = Constants.FILETYPE;
        // 检查文件类型是否符合
        boolean isType = false;

        for (int i = 0; i < filetype.length; i++) {
            if (uploadFile.getFileName().toLowerCase().endsWith(filetype[i])) {
                isType = true;
                break;
            }
        }

        if (isType) {
            String fname = uploadFile.getFileName();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

            String formatDate = user.getId() + "_" + sdf.format(new Date());
            // 新的文件名
            fname = formatDate + fname.substring(fname.lastIndexOf("."));
            // 完整路径
            String filePath = Constants.ACCESSORYFILEPATH + fname; // 完整路径

            File myFileDir = new File(Constants.ACCESSORYFILEPATH);
            // 如果目录不存在 新建目录
            if (!myFileDir.exists()) {
                boolean isSuccess = myFileDir.mkdir();
                if (!isSuccess) {
                    log.error("文件目录" + Constants.ACCESSORYFILEPATH + "建立失败");
                    this.addErrorMsg("文件目录" + Constants.ACCESSORYFILEPATH + "建立失败");
                    return false;
                }
            }

            try {
                // 保存文件
                uploadFile.save(new File(filePath));
            } catch (IOException e) {
                log.error("上传附件错误:", e);
                this.addErrorMsg("上传附件错误,请联系管理员！");
                return false;
            }
            // 更新文件名称 路径
            siteInfomationEntity.setFilename(uploadFile.getFileName());

            siteInfomationEntity.setFilepath(fname);

        } else {
            this.addErrorMsg("上传附件错误,不可上传的类型！");
            log.error("上传附件错误，不可上传的类型！");
            return false;
        }
        return true;
    }

    /**
     * 文件下载
     * 
     * @return
     */
    public Resolution downLoad() {
        UserEntity user = (UserEntity) getACL().getUser();
        log.error("附件下载:User{},Id{}", new Object[] { user.getId(), param.getId() });

        service = getBean("siteInformationService", SiteInformationService.class);
        siteInfomationEntity = service.getSiteInfo(param.getId());

        FileInputStream inputStream = null;
        StreamingResolution resolution = null;
        try {
            inputStream = new FileInputStream(new File(Constants.ACCESSORYFILEPATH
                    + siteInfomationEntity.getFilepath()));
            resolution = new StreamingResolution("application/zip", inputStream);
            resolution.setFilename(URLEncoder.encode(siteInfomationEntity.getFilename(),
                    "UTF-8"));
        } catch (Exception e) {
            log.error("下载失败!User:{},Id:{},Exception:{}", new Object[] { user.getId(),
                    param.getId(), e });
            this.addErrorMsg("下载时发生错误,请联系管理员！");
            return new ForwardResolution("/jsp/wrongModule.jsp");
        }
        return resolution;
    }

    /**
     * cms head.jsp 获取站内信滚动标题
     * 
     */
    public Resolution getSubject() {
        service = getBean("siteInformationService", SiteInformationService.class);
        List<SiteInformationEntity> msgsList = service.getSubject();
        SiteInformationSubjectDto subject = new SiteInformationSubjectDto();
        List<String> msgList = new ArrayList<String>();
        List<Integer> msgId = new ArrayList<Integer>();
        for (SiteInformationEntity msgs : msgsList) {
            // StringBuffer msgBuf=new StringBuffer();
            // msgBuf.append("[");
            // msgBuf.append(msgs.getSubject());
            // msgBuf.append("] ");
            // msgBuf.append(msgs.getText());
            // String msg =msgBuf.toString();
            String msg = msgs.getSubject();
//            if (msg != null) {
//                if (msg.length() > 12) {
//                    msg = msg.substring(0, 12) + "...";
//                } else {
//                    msg = msg.substring(0, msg.length());
//                }
//            }
            msgList.add(msg);
            msgId.add(msgs.getId());
        }

        subject.setMsgs(msgList);
        subject.setId(msgId);
        return new JSONResolution(subject);
    }

    public Resolution showSiteInformationList() {
        service = getBean("siteInformationService", SiteInformationService.class);

        BaseQueryData<SiteInformationDto> queryParam = new BaseQueryData<SiteInformationDto>();
        queryParam.setPage(this.getPage());
        queryParam.setRows(Constants.PAGESIZE);
        queryParam.setStar((this.getPage() - 1) * Constants.PAGESIZE);
        // queryParam.setQuery(param);// 查询条件

        List<SiteInformationOfShowDto> informationScollList = service
                .getSiteInfoScollList(queryParam);
        List<SiteInformationOfShowDto> informationHistoryList = service
                .getSiteInfoHistoryList(queryParam);
        getRequest().setAttribute("informationScollList", informationScollList);
        getRequest().setAttribute("informationHistoryList", informationHistoryList);
        SiteInformationOfShowDto clcikMsg = new SiteInformationOfShowDto();
        if (msgId != null) {
            for (SiteInformationOfShowDto msg : informationScollList) {
                if (msg.getId() == Integer.parseInt(msgId)) {
                    clcikMsg = msg;
                }
            }
        }
        // if (informationScollList.size() > 0) {
        // firstMsg = informationScollList.get(0);
        // } else if (informationHistoryList.size() > 0) {
        // firstMsg = informationHistoryList.get(0);
        // }
        /* 封装返回结果 */
        BaseResultData result = new BaseResultData(queryParam.getPage(),
                queryParam.getRecords(), queryParam.getTotal());
        result.setUrl("/data/base/SiteInformation.action?showSiteInformationList=&cut=1");
        getRequest().setAttribute("page", result);
        getRequest().setAttribute("clcikMsg", clcikMsg);
        return new ForwardResolution("/jsp/report/base/siteInformationList.jsp");
    }
}
