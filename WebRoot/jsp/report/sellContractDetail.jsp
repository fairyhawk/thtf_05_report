<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>销售合同产品明细</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${ctx}/css/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${ctx}/css/ui.jqgrid.css" />
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="${ctx}/js/ui/jquery-ui-i18n.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/jquery-ui-1.8.2.custom.min.js"
	type="text/javascript"></script> <script
	src="${ctx}/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/jquery.jqGrid.min.js"
	type="text/javascript"></script> <script
	src="${ctx}/js/jqgrid/js/jquery.jqgridOfReport.js"
	type="text/javascript"></script> <script
	src="${ctx}/js/jqgrid/js/jquery.fmatter.js" type="text/javascript"></script>
<script type="text/javascript"> 
		var windowId;
		var colNames = new Array(
				'合同流水号',
				'产品合同号',
				'公司合同号',
				'合同状态',
				'人员区域',
				'人员名称',
				'客户名称',
				'项目名称',
				'合同总额',
				'产品编码',
				'产品名称',
				'规格型号',
				'单位',
				'销售数',
				'销售单价',
				'销售金额',
				'限价',
				'发货金额',
				'实际发货金额',
				'发货数量',
				'实际发货数量',
				'备货金额',
				'备货数量',
				'指定金额',
				'在途指定金额',
				'开票金额',
				'退货合同金额',
				'退货合同数量',
				'退货金额',
				'退货数量',
				'实际销售数量',
				'合同未执行数量');
		var colModel = new Array( 
				{name:'sell_contract_id',index:'sell_contract_id',defaultReport:true, width:85, align:"left",coordinate:"0,0"},
				{name:'product_contract_code',index:'product_contract_code',defaultReport:true, width:85, align:"left",coordinate:"0,1"},
				{name:'company_contract_code',index:'company_contract_code',defaultReport:true, width:120, align:"left",coordinate:"0,2"},
				{name:'status_name',index:'status_name',defaultReport:true, width:95, align:"left",coordinate:"0,3"},
				{name:'user_area_name',index:'user_area_name',defaultReport:true, width:70, align:"left",coordinate:"0,4"},
				{name:'user_name',index:'user_name',defaultReport:true, width:70, align:"left",coordinate:"0,5"},
				{name:'customer_name',index:'customer_name',defaultReport:true, width:150, align:"left",coordinate:"1,0"},
				{name:'contract_pro_name',index:'contract_pro_name',defaultReport:true, width:100, align:"left",coordinate:"1,1"},
				{name:'money',index:'money',defaultReport:true, width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"1,2"},
				{name:'product_code',index:'product_code',defaultReport:true, width:80, align:"left",coordinate:"2,0"},
				{name:'product_name',index:'product_name',defaultReport:true, width:120, align:"left",coordinate:"2,1"},
				{name:'product_type',index:'product_type',defaultReport:true, width:155, align:"left",coordinate:"2,2"},
				{name:'product_unit',index:'product_unit',defaultReport:true, width:50, align:"left",coordinate:"2,3"},
				{name:'count',index:'count',defaultReport:true, width:80, align:"right",coordinate:"2,4"},
				{name:'price',index:'price',defaultReport:true, width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"2,5"},
				{name:'sell_money',index:'sell_money',defaultReport:true, width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"2,6"},
				{name:'product_limit_price',index:'product_limit_price', width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"2,7"},
				{name:'send_goods_money',index:'send_goods_money', width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"3,0"},
				{name:'fact_send_goods_money',index:'fact_send_goods_money', width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"3,1"},
				{name:'send_goods_count',index:'send_goods_count', width:80, align:"right",coordinate:"3,2"},
				{name:'fact_send_goods_count',index:'fact_send_goods_count', width:80, align:"right",coordinate:"3,3"},
				{name:'prepare_goods',index:'prepare_goods', width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"3,4"},
				{name:'stock_goods_count',index:'stock_goods_count', width:80, align:"right",coordinate:"3,5"},
				{name:'appoint_money',index:'appoint_money', width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"4,0"},
				{name:'in_transit_appoint_money',index:'in_transit_appoint_money', width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"4,1"},
				{name:'sell_invoice_money',index:'sell_invoice_money', width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"4,2"},
				{name:'back_contract_money',index:'back_contract_money', width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"1,3"},
				{name:'back_contract_count',index:'back_contract_count', width:80, align:"right",coordinate:"1,4"},
				{name:'back_money',index:'back_money', width:80, align:"right",formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: " "},coordinate:"3,6"},
				{name:'back_count',index:'back_count', width:80, align:"right",coordinate:"3,7"},
				{name:'fact_sell_count',index:'fact_sell_count', width:80, align:"right",coordinate:"1,5"},
				{name:'contract_not_implemented_count',index:'contract_not_implemented_count', width:80, align:"right",coordinate:"1,6"}
			);
		var colSum = new Array(
				{name:'sell_money',tableName:'销售金额:',format:"money"}, 
				{name:'count',tableName:'产品销售数量:'},
				{name:'fact_sell_count',tableName:'实际销售数量:'}
				);
		$(document).ready(function(){ 
			$("#sellContractDetail").jqgridOfReport({
				url: "/report/data/SellContractDetail.action?getSellContractDetail=", 
				colNames: colNames,
				colModel:colModel, 
				rowNum:10, 
				rowList:[10,20,30,40], 
				pager: 'sellContractDetailBottom', 
				sortname: 'sell_contract_id',
				sortorder: "desc", 
				caption:"销售合同产品明细",
				dialogWidth:810,
				reportSum:{
					sumUrl:"/report/data/SellContractDetail.action?getSellContractDetailSumVal=",
					colSum:colSum
					}
			
			});
			
			window.onbeforeunload=function(){ 
				if(windowId!=undefined){
					windowId.close();
				}
			}


			$("#btndownload").click(function(){ 
				windowId =  window.open('/report/view/ViewDownload.action?nodeId=1141','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
			});
			
		});
	</script>
</head>
<body>
<form>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="ye_header_left" width="16px" title="">
		<div class="ellipsis_div" style="width: 16px;">&nbsp;</div>
		</td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp;
		当前位置： 报表管理 &gt;&gt; 销售合同产品明细</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><br />
		<div class="mo_wp">
		<div class="mo_con">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="biao3">
			<tr>
				<td class="td_01" width="20%">合同流水号</td>
				<td class="td_02" width="30%"><input type="text"
					name="param.sell_contract_id" id="sell_contract_id"></td>
				<td class="td_01" width="20%">产品分类名称</td>
				<td class="td_02" width="30%"><input type="text"
					name="param.product_type_name" id="product_type_name"></td>
			</tr>
			<tr>
				<td class="td_01" width="20%">产品合同号</td>
				<td class="td_02" width="30%"><input type="text"
					name="param.product_contract_code" id="product_contract_code"></td>
				<td class="td_01" width="20%">公司合同号</td>
				<td class="td_02" width="30%"><input type="text"
					name="param.company_contract_code" id="company_contract_code"></td>
			</tr>
			<tr>
				<td class="td_01" width="20%">产品名称</td>
				<td class="td_02" width="30%"><input type="text"
					name="param.product_name" id="product_name"></td>
				<td class="td_01" width="20%">产品编码</td>
				<td class="td_02" width="30%"><input type="text"
					name="param.product_code" id="product_code"></td>
			</tr>
			<tr>
				<td class="td_01" width="100%">规格型号</td>
				<td class="td_02" width="100%"><input type="text"
					name="param.product_type" id="product_type"></td>
				<td class="td_01" width="20%">合同状态</td>
				<td class="td_02" width="30%"><select id="status"
					name="param.status" style="width: 132px">
					<option value="">--请选择--</option>
					<option value="1">未提交</option>
					<option value="2">产品总监待评审</option>
					<option value="3">产品总监未通过</option>
					<option value="4">法务专员待评审</option>
					<option value="5">法务专员未通过</option>
					<option value="16">区域总监待评审</option>
					<option value="17">区域总监未通过</option>
					<option value="6">销售总监待评审</option>
					<option value="7">销售总监未通过</option>
					<option value="8">运营助理待评审</option>
					<option value="9">运营助理未通过</option>
					<option value="10">运营总监待评审</option>
					<option value="11">运营总监未通过</option>
					<option value="12">待打印</option>
					<option value="13">待确认</option>
					<option value="14">合同生效</option>
					<option value="15">合同取消</option>

				</select></td>
			</tr>
			<tr>
				<td class="td_01" width="20%">人员名称</td>
				<td class="td_02" width="30%"><input type="text"
					name="param.user_name" id="user_name"></td>
				<td class="td_01" width="20%">客户名称</td>
				<td class="td_02" width="30%"><input type="text"
					name="param.customer_name" id="customer_name"></td>
			</tr>
			<tr>
				<td class="td_01" width="20%">项目名称</td>
				<td class="td_02" width="30%"><input type="text"
					name="param.contract_pro_name" id="contract_pro_name"></td>
				<td class="td_01" width="20%"></td>
				<td class="td_02" width="30%"></td>
			</tr>
			<tr>
				<td colspan="4" class="td_02" style="text-align: center" >
				<a href="#" change="true" style="margin-right: 60px"><img src="${ctx}/images/btnChangeField.gif" /></a> 
				<a href="#" find="true"  style="margin-right:60px"><img src="${ctx}/images/btn_JianSuo.gif" /></a>
				<c:if test="${roleId!=4 }"> 
					<a href="#" style="margin-right: 60px"><img src="${ctx}/images/btnXiaZai.gif" id="btndownload" /></a>
				</c:if>
				</td>
			</tr>

		</table>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
<br />
<div style="margin-left: 15px; margin-right: auto;">
<table id="sellContractDetail"></table>
<div id="sellContractDetailBottom"></div>
<div id="sumBottom"></div>
</div>
</form>
</body>
</html>