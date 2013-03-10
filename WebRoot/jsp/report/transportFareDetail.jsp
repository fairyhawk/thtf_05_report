<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>运费明细表</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/ui.jqgrid.css" />
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="${ctx}/js/ui/jquery-ui-i18n.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/jquery-ui-1.8.2.custom.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/jquery.fmatter.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/jquery.jqgridOfReport.js" type="text/javascript"></script>

</head>
<script type="text/javascript">
var windowId;
var colNames = new Array('物流公司名称','发货日期', '装箱单号', '运单号',
		'库房名称','产品分类名称 ','客户名称','货物接收单位名称',
		'发货地址','要求货运方式名称','实际货运方式名称','装箱件数','到货日期',
		'签收日期','回执确认日期','人员名称','发货费用','装箱单状态',
		'是否确认','回执确认时间','新建日期'
		);
var colModel = new Array(
		{name:'logisticsName',index:'logistics_name',defaultReport:true, width:90},
        {name:'sendDate',index:'send_date', defaultReport:true,width:90},
	    {name:'boxId',index:'box_id', defaultReport:true,width:100}, 
	    {name:'boxNo',index:'box_no', width:90,defaultReport:true},
	    
	    {name:'stockroomName',index:'stockroom_name',defaultReport:true, width:80},
	    {name:'productTypeName',index:'product_type_name', width:80,defaultReport:true},
	    {name:'customerName',index:'customer_name', width:80},
	    {name:'companyName',index:'company_name', defaultReport:true,width:100},
        
	    {name:'address',index:'address', width:100},
	    {name:'requestWay',index:'request_way', width:80, defaultReport:true},
	    {name:'realityWay',index:'reality_way', width:80, defaultReport:true},
	    {name:'count',index:'count', width:60,align:"right", defaultReport:true},
	    {name:'arrivalDate',index:'arrival_date', width:80},
	    
	    {name:'signoffDate',index:'signoff_date', width:80},
        {name:'confirmDate',index:'confirm_date', width:100},
	    {name:'userName',index:'user_name', width:100}, 
	    {name:'money',index:'money', width:100, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
	    {name:'statusName',index:'status_name', width:80},
	    
	    {name:'tbcFlagName',index:'tbc_flag_name', width:80},
	    {name:'tbcDate',index:'tbc_date', width:80},
	    {name:'date',index:'date', width:80}
)
$(document).ready(function(){
	$("#transportFareDetail").jqgridOfReport({
		url: "/report/data/RTransportFareDetail.action?getDateList=",
		colNames: colNames,
		colModel:colModel,
		pager: 'transportFareDetailBottom',
		sortname: 'boxId',
		caption:"运费明细",
		reportSum:{
			sumUrl:"/report/data/RTransportFareDetail.action?getDateSumList=",
			colSum:[
				{name:'money',tableName:'发货费用:',format:"money"},
				{name:'count',tableName:'装箱件数合计:'}
			        ]
			},
		datepicker:["tbcEndDate","tbcBeginDate"]
	});
	window.onbeforeunload=function(){ 
		if(windowId!=undefined){
			windowId.close();
		}
	}
	$("#btndownload").click(function(){ 
		windowId =  window.open('/report/view/ViewDownload.action?nodeId=1172','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
	});
});
</script>
<body>
<form>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 报表管理 &gt;&gt; 运费明细</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">物流公司名称</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.logisticsName">
                </td>
                <td class="td_01" width="20%">装箱单号</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.boxId" >
                </td>
              </tr>
              <tr>
                <td class="td_01">运单号</td>
                <td class="td_02">
                	<input type="text" name="param.boxNo">
                </td>
				<td class="td_01">库房名称</td>
                <td class="td_02">
                	<input type="text" name="param.stockroomName" >
                </td>
              </tr>
              <tr>
                <td class="td_01" width="20%">客户名称</td>
                <td class="td_02" width="30%">
                	<input type="text"  name="param.customerName" id="customerName">
                </td>
                <td class="td_01" width="20%">发货地址</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.address">
                </td>
              </tr>
              <tr>
                <td class="td_01">确认起始日期</td>	
                <td class="td_02">
                	<input type="text" name="param.tbcBeginDate" id="tbcBeginDate" readonly="true">
				</td>
				<td class="td_01">确认终止日期</td>
                <td class="td_02">
                	<input type="text" name="param.tbcEndDate" id="tbcEndDate" readonly="true">
                </td>
              </tr>
              <tr>
                <td class="td_01">产品分类名称</td>
                <td class="td_02">
                	<input type="text"  name="param.productTypeName">
				</td>
				<td class="td_01">装箱单状态	</td>
                <td class="td_02">
                	<select name="param.statusName">
                		<option value="">---请选择---</option>
                		<option value="装箱中">装箱中</option>
                		<option value="在途">在途</option>
                		<option value="到达">到达</option>
                		<option value="签收">签收</option>
                		<option value="回执确认">回执确认</option>
                		<option value="已付款">已付款</option>
                	</select>
                </td>
              </tr>
              <tr>
                <td colspan="4" class="td_02" style="text-align:center">
                	<a href="#" change="true" style="margin-right:60px"><img src="${ctx}/images/btnChangeField.gif" /></a>
                	<a href="#" find="true" style="margin-right:60px"><img src="${ctx}/images/btn_JianSuo.gif" /></a>
                	<a href="#" ><img src="${ctx}/images/btnXiaZai.gif" id="btndownload"/></a>
				</td>
              </tr>
            </table>
          </div>
        </div>
    </td>
    <td>&nbsp;</td>
  </tr>
  </table>
</form>
<br/>
<div style="padding-left:16px;margin-right:auto;">
		<table id="transportFareDetail"></table>
		<div id="transportFareDetailBottom"></div>	
</div>		
</body>
</html>