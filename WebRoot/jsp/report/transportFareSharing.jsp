<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>运费分摊</title>
	<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/ui.jqgrid.css" />
	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/ui/jquery-ui-i18n.js" type="text/javascript"></script>
	<script src="${ctx}/js/jqgrid/js/jquery-ui-1.8.2.custom.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script src="${ctx}/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/jqgrid/js/jquery.jqgridOfReport.js" type="text/javascript"></script>
	<script src="${ctx}/js/jqgrid/js/jquery.fmatter.js" type="text/javascript"></script>
	<script type="text/javascript"> 
		var windowId;
		var colNames = new Array('装箱单号','发货单号', '产品分类名称', '物流公司名称','客户名称',
				'发货费用','发货日期','单据类型','发货均摊费用','人员部门名称','人员姓名','是否确认','确认时间');
		var colModel = new Array(
				{name:'box_id',index:'box_id',defaultReport:true, width:90, align:"left"},
				{name:'send_goods_id',index:'send_goods_id', defaultReport:true,width:90, align:"left"},
				{name:'name',index:'name', defaultReport:true,width:90, align:"left"}, 
				{name:'logistics_name',index:'logistics_name', width:150,defaultReport:true, align:"left"}, 
				{name:'customer_name',index:'customer_name',defaultReport:true, width:170, align:"left"},
				{name:'money',index:'money', defaultReport:true,width:90, align:"right", formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
				{name:'send_date',index:'send_date', defaultReport:true,width:70, align:"left"},
				{name:'send_goods_type',index:'send_goods_type', defaultReport:true,width:50, align:"left"}, 
				{name:'send_agv_money',index:'send_agv_money', defaultReport:true, width:90, align:"right" , formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
				{name:'user_dept_name',index:'user_dept_name', width:80, align:"left"},
				{name:'user_name',index:'user_name', width:60, align:"left"},
				{name:'tbc_flag_name',index:'tbc_flag_name', width:70, align:"left"},
				{name:'tbc_date',index:'tbc_date', width:90, align:"left"});
		var colSum = new Array(
				{name:'moneySum',tableName:'发货费用合计:', format:"money"},
				{name:'sendAgvMoneySum',tableName:'均摊费用合计:', format:"money"}
				);
		$(document).ready(function(){ 
			$("#transportFareSharing").jqgridOfReport({
				url: "/report/data/TransportFareSharing.action?getTransportFareSharing=", 
				colNames: colNames,
				colModel: colModel, 
				rowNum:10, 
				rowList:[10,20,30,40], 
				pager: 'transportFareSharingBottom', 
				sortname: 'box_id', 
				sortorder: "desc", 
				caption:"运费分摊",
				reportSum:{
					sumUrl:"/report/data/TransportFareSharing.action?getTransportFareSharingSum=",
					colSum:colSum
					},
				datepicker:["sendBeginDate","sendEndDate"]
			});
			
			window.onbeforeunload=function(){ 
				if(windowId!=undefined){
					windowId.close();
				}
			}


			$("#btndownload").click(function(){ 
				windowId =  window.open('/report/view/ViewDownload.action?nodeId=1171','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
			});
			
			
		
		});
		
		

	</script>
</head>
<body>
<form>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="ye_header_left" width="16px" title=""><div class="ellipsis_div" style="width:16px;">&nbsp;</div></td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 报表管理 &gt;&gt; 运费分摊</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><br/>
			<div class="mo_wp">
			<div class="mo_con" >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%">装箱单号</td>
					<td class="td_02" width="30%"><input type="text" name="param.boxId"></td>
					<td class="td_01" width="20%">发货单号</td>
					<td class="td_02" width="30%"><input type="text" name="param.sendGoodsId" ></td>
				</tr>
				<tr>
					<td class="td_01" width="20%">产品分类名称</td>
					<td class="td_02" width="30%"><input type="text" name="param.name"></td>
					<td class="td_01" width="20%">物流公司名称</td>
					<td class="td_02" width="30%"><input type="text" name="param.logisticsName" ></td>
				</tr>
				<tr>
					<td class="td_01" width="20%">客户名称</td>
					<td class="td_02" width="30%"><input type="text"  name="param.customerName"></td>
					<td class="td_01" width="20%">人员姓名</td>
					<td class="td_02" width="30%"><input type="text"  name="param.userName"></td>
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
					<td class="td_01" width="20%">单据类型</td>
					<td class="td_02" width="30%"><input type="text"  name="param.sendGoodsType"></td>
					<td class="td_01" width="20%">人员部门名称</td>
					<td class="td_02" width="30%"><input type="text"  name="param.userDeptName"></td>
				</tr> 
				<tr>
					<td colspan="4" class="td_02" style="text-align:center">
						<a href="#" change="true" style="margin-right:60px"><img src="${ctx}/images/btnChangeField.gif" /></a>
						<a href="#" find="true" style="margin-right:60px"><img src="${ctx}/images/btn_JianSuo.gif" /></a>
						<a href="#" style="margin-right:60px"><img src="${ctx}/images/btnXiaZai.gif" id="btndownload"/></a>
					</td>
				</tr>
			</table> 
		</td>
		<td>&nbsp;</td>
	  </tr>
	</table> 
	<br/> 
	<div style="margin-left:15px;margin-right:auto;">
		<table id="transportFareSharing"></table>
		<div id="transportFareSharingBottom"></div>
		<div id="sumBottom"></div>
	</div>
</form>
</body>
</html>