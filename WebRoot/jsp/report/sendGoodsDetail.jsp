<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发货单产品明细</title>
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
<c:if test="${requestScope.role ==3 || requestScope.role ==4 || requestScope.role ==6 || requestScope.role ==7 || requestScope.role ==9 || requestScope.role ==19}">
	<c:set var="specialIs3" value="special:true,"></c:set>
</c:if>
<c:if test="${requestScope.role ==13}">
	<c:set var="specialIs13" value="special:true,"></c:set>
</c:if>
<script type="text/javascript">
var windowId;
var colNames = new Array('销售合同流水号','产品合同号', '公司合同号', '发货单号',//CWS_SESSION_ACL
		'发货单状态','发货单类型',
		'产品分类名称','客户名称','要求发货日期','实际发货日期',
		'要求到账日期','库房名称','产品名称','产品编码','规格型号',
		'单位','单价(含税)','发货/备货数量','实际发货数量','实际发货金额',
		'发货金额','退货数量','退货金额','成本单价(不含税)','发货成本(不含税)',
		'发货利润(不含税)','发货毛利率','指定金额','在途指定金额','发货未回款金额',
		'人员名称 ','开票金额','申请日期','特殊说明','人员区域',
		'含税成本','含税利润','客户省','市','客户区域','项目名称','货物接收单位名称');
var colModel = new Array(
		{name:'sellContractId',index:'sell_contract_id',defaultReport:true, width:90,coordinate:"1,0"},
        {name:'productContractCode',index:'product_contract_code', defaultReport:true,width:85,coordinate:"1,1"},
	    {name:'companyContractCode',index:'company_contract_code', defaultReport:true,width:85,coordinate:"1,2"}, 
	    {name:'sendGoodsId',index:'send_goods_id', width:88,defaultReport:true, align:"left",coordinate:"1,3"},
	    
	    {name:'send_goods_status_name',index:'send_goods_status_name',defaultReport:true, width:80,coordinate:"2,0"},
	    {name:'send_goods_type_name',index:'send_goods_type_name',defaultReport:true, width:65,coordinate:"2,1"},
	    
	    {name:'productTypeName',index:'product_type_name',defaultReport:true, width:80,coordinate:"2,3"},
	    {name:'customerName',index:'customer_name', width:190,defaultReport:true,coordinate:"0,3"},
	    {name:'requestDate',index:'request_date', width:80,coordinate:"7,1"},
	    {name:'sendDate',index:'send_date', defaultReport:true,width:80,coordinate:"7,2"},
        
	    {name:'requestReachDate',index:'request_reach_date', width:80,coordinate:"7,3"},
	    {name:'stockroomName',index:'stockroom_name', width:160,coordinate:"2,2"}, 
	    {name:'productName',index:'product_name', width:100, defaultReport:true,coordinate:"3,1"},
	    {name:'productCode',index:'product_code', width:80, defaultReport:true,coordinate:"3,0"},
	    {name:'productType',index:'product_type', width:160, defaultReport:true,coordinate:"3,2"},
	    
	    {name:'productUnit',index:'product_unit', width:30,align:"center", defaultReport:true,coordinate:"3,3"},
	    {name:'productMoneyTax',index:'product_money_tax', width:70,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""},coordinate:"3,4"},
	    {name:'detailCount',index:'detail_count', width:85, align:"right",coordinate:"3,5"},
        {name:'detailCountRel',index:'detail_count_rel', width:80, align:"right",coordinate:"4,0"},
	    {name:'detailMoneyRel',index:'detail_money_rel', width:90,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""},coordinate:"4,1"}, 
	    
	    {name:'detailMoney',index:'detail_money', width:90,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"4,2"},
	    {name:'backCount',index:'back_count', width:60, align:"right",coordinate:"4,3"},
	    {name:'backMoney',index:'back_money', width:70,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"4,4"},
	    {name:'detailPrice',index:'detail_price',${specialIs3} width:100,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"6,3"},
	    {name:'productMoneyCost',index:'product_money_cost',${specialIs13} width:100,${specialIs3} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"6,4"},
	    
	    {name:'productMoneyProfit',index:'product_money_profit',${specialIs3} width:100,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"6,5"},
	    {name:'productMoneyProfitrate',index:'product_money_profitrate',${specialIs3} width:100,${specialIs13} align:"right",coordinate:"6,2"},
	    {name:'appointMoney',index:'appoint_money', width:100,${specialIs13}align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"5,0"}, 
	    {name:'appointMoneyOtw',index:'appoint_money_otw', width:100,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"5,1"},
	    {name:'appointMoneyExt',index:'appoint_money_ext', width:80,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"5,2"},
	    
	    {name:'userName',index:'user_name', width:60,coordinate:"1,5"},
	    {name:'makeInvoiceMoney',index:'make_invoice_money', width:80,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"5,3"},
	    {name:'datetime',index:'datetime', width:80,coordinate:"7,0"},
	    {name:'text',index:'text', width:120,coordinate:"7,5"},
	    {name:'user_area_name',index:'user_area_name', width:60,coordinate:"1,4"},
	    
	    {name:'productMoneyCostTax',index:'product_money_cost_tax',${specialIs3} width:80,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"6,0"},
	    {name:'productMoneyProfitTax',index:'product_money_profit_tax',${specialIs3} width:80,${specialIs13} align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"6,1"},
	    {name:'customerProvince',index:'customer_province', width:60,coordinate:"0,1"},
	    {name:'customerCity',index:'customer_city', width:60,coordinate:"0,2"},
	    {name:'customerArea',index:'customer_area', width:60,coordinate:"0,0"},
	    {name:'contractProName',index:'contract_Pro_Name', width:80, defaultReport:true,coordinate:"0,4"},
	    {name:'caName',index:'ca_name', width:140,coordinate:"7,4"}
)
$(document).ready(function(){
	$("#sendGoodsDetail").jqgridOfReport({
		url: "/report/data/RSendGoodsDetail.action?getDateList=",//请求的链接，必配置项，jqgrid必配置项
		colNames: colNames,//上面定义的列标题，必配置项，jqgrid必配置项
		colModel:colModel, 
		//上面定义的列值配置，必配置项，jqgrid必配置项;新加配置段1、special:true 特殊人员的显示，不用配置，配置后字段始终不显示；2、coordinate:"0,0",选择框按指定位置显示，不用配置
		pager: 'sendGoodsDetailBottom', //显示底部条数，可不用配置，jqgrid可不用配置
		sortname: 'sendGoodsId',  //排序的名字，必配置项，是jqgrid必配置项
		caption:"发货单产品明细",//标题，可不用配置，jqgrid可不用配置	
		dialogWidth:760,//选择框宽度，可不用配置，默认不选择，自定义，jqgrid无此配置项
		reportSum:{
			sumUrl:"/report/data/RSendGoodsDetail.action?getDateSumList=",
			colSum:[
				{name:'detailCountRel',tableName:'实际发货数量:'},
				{name:'detailMoneyRel',${specialIs13}tableName:'实际发货金额:',format:"money"},
				{name:'backCount',tableName:'退货数量:'},
				{name:'backMoney',${specialIs13}tableName:'退货金额:',format:"money"},
				{name:'appointMoney',${specialIs13}tableName:'指定金额:',format:"money"},
				{name:'appointMoneyOtw',${specialIs13}tableName:'在途指定金额:',format:"money"},
				{name:'appointMoneyExt',${specialIs13}tableName:'发货未回款金额:',format:"money"},
				{name:'makeInvoiceMoney',${specialIs13}tableName:'开票金额:',format:"money"}
			        ]
			},//显示合计值 ;可不用配置;自定义，jqgrid无此配置项
			datepicker:['sendEndDate','sendBeginDate',"requestReachEndDate","requestReachBeginDate"] //定义datepicker ；可不用配置 ，jqgrid可不用配置
		//rowNum:10, //每页显示数据条数，可不用配置 默认为10；是jqgrid必配置项
		//rowList:[10,20,30,40], //配置选择每页显示数据条数，可不用配置，默认[10,20,30,40]；是jqgrid必配置项
		//sortorder: "desc",  //升降序的配置，可不用配置，jqgrid可不用配置
		//showCheckBox:true	//显示复选框，默认不选择，自定义，jqgrid无此配置项
	});
	window.onbeforeunload=function(){ 
		if(windowId!=undefined){
			windowId.close();
		}
	}
	$("#btndownload").click(function(){ 
		windowId =  window.open('/report/view/ViewDownload.action?nodeId=1142','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
	});
});
</script>
<body>
<form>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 报表管理 &gt;&gt; 发货单产品明细</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">销售合同流水号</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.sellContractId">
                </td>
                <td class="td_01" width="20%">发货单号</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.sendGoodsId" >
                </td>
              </tr>
              <tr>
                <td class="td_01">产品合同号</td>
                <td class="td_02">
                	<input type="text" name="param.productContractCode">
                </td>
				<td class="td_01">公司合同号</td>
                <td class="td_02">
                	<input type="text" name="param.companyContractCode" >
                </td>
              </tr>
              <tr>
                <td class="td_01" width="20%">客户名称</td>
                <td class="td_02" width="30%">
                	<input type="text"  name="param.customerName" id="customerName">
                </td>
                <td class="td_01" width="20%">产品名称</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.productName" id="productName">
                </td>
              </tr>
              <tr>
                <td class="td_01">产品编码</td>
                <td class="td_02">
                	<input type="text"  name="param.productCode">
                </td>
                <td class="td_01">规格型号</td>
                <td class="td_02">
                	<input type="text" name="param.productType" >
                </td>
              </tr>
              <tr>
                <td class="td_01">发货起始日期</td>	
                <td class="td_02">
                	<input type="text" name="param.sendBeginDate" id="sendBeginDate" readonly="true">
				</td>
				<td class="td_01">发货终止日期</td>
                <td class="td_02">
                	<input type="text" name="param.sendEndDate" id="sendEndDate" readonly="true">
                </td>
              </tr>
              <tr>
                <td class="td_01">要求到账起始日期</td>	
                <td class="td_02">
                	<input type="text" name="param.requestReachBeginDate" id="requestReachBeginDate" readonly="true">
				</td>
				<td class="td_01">要求到账终止日期</td>
                <td class="td_02">
                	<input type="text" name="param.requestReachEndDate" id="requestReachEndDate" readonly="true">
                </td>
              </tr>
              <tr>
                <td class="td_01">产品分类名称</td>
                <td class="td_02">
                	<input type="text"  name="param.productTypeName">
				</td>
				<td class="td_01">项目名称</td>
                <td class="td_02">
                	<input type="text" name="param.contractProName" >
                </td>
              </tr>
              <tr>
                <td class="td_01">发货单类型</td>
                <td class="td_02">
                	<select name="param.sendGoodsTypeName">
                		<option value="">--请选择--</option>
                		<option value="发货">发货</option>
                		<option value="备货">备货</option>
                	</select>
				</td>
				<td class="td_01">发货单状态</td>
                <td class="td_02">
                	<select name="param.sendGoodsStatusName">
                		<option value="">---请选择---</option>
                		<option value="未提交">未提交</option>
                		<option value="待发货">待发货</option>
                		<option value="发货中">发货中</option>
                		<option value="发货异常">发货异常</option>
                		<option value="发货成功">发货成功</option>
                		<option value="销售总监待评审">销售总监待评审</option>
                		<option value="销售总监未通过">销售总监未通过</option>
                		<option value="备货成功">备货成功</option>
                		<option value="备货超期">备货超期</option>
                		<option value="区域总监待评审">区域总监待评审</option>
                		<option value="区域总监未通过">区域总监未通过</option>
                	</select>
                </td>
              </tr>
                            <tr>
                <td class="td_01">人员名称</td>
                <td class="td_02" colspan="3">
                	<input type="text"  name="param.userName">
				</td>
              </tr>
              <tr>
                <td colspan="4" class="td_02" style="text-align:center">
                	<a href="#" change="true" style="margin-right:60px"><img src="${ctx}/images/btnChangeField.gif" /></a>
                	<a href="#" find="true" style="margin-right:60px"><img src="${ctx}/images/btn_JianSuo.gif" /></a>
                	<c:if test="${requestScope.role !=4}">
						<a href="#" ><img src="${ctx}/images/btnXiaZai.gif" id="btndownload"/></a>
					</c:if>
                	
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
<div style="padding-left:15px;margin-right:auto;padding-bottom:15px;">
		<table id="sendGoodsDetail"></table>
		<div id="sendGoodsDetailBottom"></div>	
</div>		
</body>
</html>