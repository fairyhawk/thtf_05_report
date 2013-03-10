/**
 * ClassName  RTransportFareDetailActionBean
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-1-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.BaseResultData;
import cn.com.thtf.cms.report.dto.RTransportFareDetailDto;
import cn.com.thtf.cms.report.entity.TransportFareDetailEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.service.RTransportFareDetailService;
import cn.shiy.common.cws.action.JSONResolution;
import cn.shiy.common.cws.security.Secure;

/**
 * 
 * @author hanrb
 */
@Secure
public class RTransportFareDetailActionBean extends BasePageActionBean {

    /** log */
    private final Logger log = LoggerFactory.getLogger(getClass());
    /** 注入 */
    private RTransportFareDetailService transportFareDetailService = (RTransportFareDetailService) getBean(
            "transportFareDetailService", null);
    /** common */
    private CommonService baseCommonService = (CommonService) getBean("commonService",
            null);
    @Setter
    @Getter
    private RTransportFareDetailDto param = new RTransportFareDetailDto();
    public static String fieldRows[] = { "logistics_name as logisticsName",
            "send_date as sendDate", "box_id as boxId", "box_no as boxNo",
            "stockroom_name as stockroomName", "product_type_name as productTypeName",
            "customer_name as customerName", "company_name as companyName", "address",
            "request_way as requestWay", "reality_way as realityWay", "count",
            "arrival_date as arrivalDate", "signoff_date as signoffDate",
            "confirm_date as confirmDate", "user_name as userName", "money",
            "status_name as statusName", "tbc_flag_name as tbcFlagName",
            "tbc_date as tbcDate", "date" };

    public Resolution getDateList() {
        /* 登录user类 */
        UserEntity user = (UserEntity) getACL().getUser();
        BaseQueryData<RTransportFareDetailDto> queryParam = new BaseQueryData<RTransportFareDetailDto>();
        queryParam.setFieldsParam(this.getFields(), fieldRows);
        queryParam.setQuery(param);
        /** 数据 传ibatis */
        queryParam.setPage(this.getPage());
        queryParam.setRows(this.getRows());
        queryParam.setSord(this.getSord());
        queryParam.setSidx(this.getSidx());
        queryParam.setStar((this.getPage() - 1) * this.getRows());

        if (user.getRoleId() == 10) {
            queryParam.setUserId("");
        }
        boolean success = baseCommonService.getUserFileParam(user, queryParam);
        if (!success) {
            return new JSONResolution("");
        }
        transportFareDetailService.getTransportFareDetailList(queryParam);
        BaseResultData result = new BaseResultData(queryParam.getPage(),
                queryParam.getRecords(), queryParam.getTotal());
        @SuppressWarnings("unchecked")
        List<RTransportFareDetailDto> dateList = (List<RTransportFareDetailDto>) queryParam
                .getDataList();
        log.debug(String.valueOf(dateList.size()));
        for (RTransportFareDetailDto fare : dateList) {
            result.addCells(fare.getLogisticsName(), fare.getSendDate(), fare.getBoxId(),
                    fare.getBoxNo(), fare.getStockroomName(), fare.getProductTypeName(),
                    fare.getCustomerName(), fare.getCompanyName(), fare.getAddress(),
                    fare.getRequestWay(), fare.getRealityWay(),
                    String.valueOf(fare.getCount()), fare.getArrivalDate(),
                    fare.getSignoffDate(), fare.getConfirmDate(), fare.getUserName(),
                    fare.getMoney(), fare.getStatusName(), fare.getTbcFlagName(),
                    fare.getTbcDate(), fare.getDate());
        }
        return new JSONResolution(result);
    }

    public Resolution getDateSumList() {
        log.debug("检索合计值");
        UserEntity user = (UserEntity) getACL().getUser();
        BaseQueryData<RTransportFareDetailDto> queryParam = new BaseQueryData<RTransportFareDetailDto>();
        queryParam.setQuery(param);

        if (user.getRoleId() == 10) {
            queryParam.setUserId("");
        }
        boolean success = baseCommonService.getUserFileParam(user, queryParam);
        if (!success) {
            return new JSONResolution("");
        }
        TransportFareDetailEntity result = transportFareDetailService
                .getTransportFareDetailSum(queryParam);
        log.info(new JSONResolution(result).toJson());
        return new JSONResolution(result);
    }
}
