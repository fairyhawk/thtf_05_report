package cn.com.thtf.cms.report.action.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.BaseResultData;
import cn.com.thtf.cms.report.dto.TransportFareSharingDto;
import cn.com.thtf.cms.report.entity.TransportFareSharingEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.service.TransportFareSharingService;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;

/**
 * 运费分摊
 * 
 * @author zhangzx
 */
@Secure
public class TransportFareSharingActionBean extends BasePageActionBean {

    /** log */
    private static final Logger log = LoggerFactory
            .getLogger(TransportFareSharingActionBean.class);
    /** TransportFareSharingService */
    private TransportFareSharingService service;
    /** common */
    private CommonService commonService;

    @Setter
    @Getter
    /** 检索条件 */
    @ValidateNestedProperties( {
            @Validate(field = "boxId", trim = true, on = "getTransportFareSharing"),
            @Validate(field = "sendGoodsId", trim = true, on = "getTransportFareSharing"),
            @Validate(field = "name", trim = true, on = "getTransportFareSharing"),
            @Validate(field = "logisticsName", trim = true, on = "getTransportFareSharing"),
            @Validate(field = "customerName", trim = true, on = "getTransportFareSharing"),
            @Validate(field = "sendGoodsType", trim = true, on = "getTransportFareSharing"),
            @Validate(field = "userName", trim = true, on = "getTransportFareSharing"),
            @Validate(field = "userDeptName", trim = true, on = "getTransportFareSharing") })
    private TransportFareSharingDto param = new TransportFareSharingDto();
    /** 显示的列 */
    public static final String fieldRows[] = { "box_id as boxId", "send_goods_id as sendGoodsId",
            "name as name", "logistics_name as logisticsName",
            "customer_name as customerName", "round(money,2) as money",
            "send_date as sendDate", "send_goods_type as sendGoodsType",
            "send_agv_money as sendAgvMoney", "user_dept_name as userDeptName",
            "user_name as userName", "tbc_flag_name as tbcFlagName",
            "tbc_date as tbcDate" };

    /**
     * 检索运费分摊数据
     * 
     * @return
     */
    public Resolution getTransportFareSharing() {
        log.debug("检索运费分摊数据");
        /* 登录user类 */
        UserEntity user = (UserEntity) getACL().getUser();

        /* 封装查询参数 */
        BaseQueryData<TransportFareSharingDto> queryParam = new BaseQueryData<TransportFareSharingDto>();
        queryParam.setPage(this.getPage());
        queryParam.setRows(this.getRows());
        queryParam.setSord(this.getSord());
        queryParam.setSidx(this.getSidx());
        queryParam.setStar((this.getPage() - 1) * this.getRows());
        queryParam.setFieldsParam(this.getFields(), fieldRows);// 显示的列
        queryParam.setQuery(param);// 查询条件
        
        commonService = getBean("commonService", CommonService.class);
        boolean checkResult = commonService.getUserFileParam(user, queryParam);
        if (!checkResult) {
            return new JSONResolution("");
        } 
        service = getBean("transportFareSharingService",TransportFareSharingService.class);
        service.getTransportFareSharingDetail(queryParam);

        /* 判断错误 */
        if (this.hasError()) {
            log.error("检索发生错误!");
        }

        /* 封装返回结果 */
        BaseResultData result = new BaseResultData(queryParam.getPage(), queryParam
                .getRecords(), queryParam.getTotal());

        @SuppressWarnings("unchecked")
        List<TransportFareSharingEntity> dateList = (List<TransportFareSharingEntity>) queryParam
                .getDataList();
        for (TransportFareSharingEntity transportFareSharingEntity : dateList) {
            /* 页面数据,需与页面定义顺序一致 */
            result.addCells(transportFareSharingEntity.getBoxId(),
                    transportFareSharingEntity.getSendGoodsId(),
                    transportFareSharingEntity.getName(), 
                    transportFareSharingEntity.getLogisticsName(),
                    transportFareSharingEntity.getCustomerName(), 
                    transportFareSharingEntity.getMoney(),
                    transportFareSharingEntity.getSendDate(),
                    transportFareSharingEntity.getSendGoodsType(), 
                    transportFareSharingEntity.getSendAgvMoney(), 
                    transportFareSharingEntity.getUserDeptName(), 
                    transportFareSharingEntity.getUserName(),
                    transportFareSharingEntity.getTbcFlagName(),
                    transportFareSharingEntity.getTbcDate());
        }

        return new JSONResolution(result);
    }

    /**
     * 检索运费分摊数据合计值
     * 
     * @return
     */
    public Resolution getTransportFareSharingSum() {
        log.debug("检索运费分摊数据合计值");
        BaseQueryData<TransportFareSharingDto> queryParam = new BaseQueryData<TransportFareSharingDto>();
        
        queryParam.setQuery(param);// 查询条件
        
        service = getBean("transportFareSharingService",TransportFareSharingService.class);       

        UserEntity user = (UserEntity) getACL().getUser();
        
        commonService = getBean("commonService", CommonService.class);
        
        boolean checkResult = commonService.getUserFileParam(user, queryParam);
        if (!checkResult) {
            return new JSONResolution("");
        } 
        
        TransportFareSharingDto result = service.getTransportFareSharingSumVal(queryParam);

        /* 判断错误 */
        if (this.hasError()) {
            log.error("检索发生错误!");
        }
        return new JSONResolution(result);
    }
    

}
