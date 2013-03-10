<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>库存数量明细</title>
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
	 <c:if test="${requestScope.role ==12 || requestScope.role ==13 || requestScope.role ==19 || requestScope.role ==20}">
		<c:set var="specialIs12" value="special:true,"></c:set>
	</c:if>
	<script type="text/javascript"> 
		var windowId;

		var colNames = new Array('库房名称','产品分类名称', '产品系列名称', '产品编码','产品名称',
				'规格型号','单位','库存总数',	'库存单价(不含税)','库存金额');
		var colModel = new Array(
				{name:'stockroom_name',index:'stockroom_name',defaultReport:true, width:150, align:"left"},
				{name:'product_type_name',index:'product_type_name', defaultReport:true,width:90, align:"left"},
				{name:'product_serie_name',index:'product_serie_name', defaultReport:true,width:100, align:"left"}, 
				{name:'product_code',index:'product_code', width:100,defaultReport:true, align:"left"}, 
				{name:'product_name',index:'product_name',defaultReport:true, width:150, align:"left"},

				{name:'product_type',index:'product_type', width:150, align:"left"},
				{name:'product_unit',index:'product_unit', defaultReport:true,width:50, align:"left"},
				{name:'stockroom_count',index:'stockroom_count', defaultReport:true,width:70, align:"right"}, 
				{name:'stockroom_price',index:'stockroom_price', width:120, align:"right",formatter:'currency',${specialIs12} formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
				{name:'stockroom_money',index:'stockroom_money', width:100, align:"right",formatter:'currency',${specialIs12} formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}});
		var colSum = new Array(
				{name:'productCountSum',tableName:'库存总数合计:'},
				{name:'stockroomMoneySum',tableName:'库存金额合计:',${specialIs12} format:"money"}
				);
		$(document).ready(function(){ 
			$("#stockProductDetail").jqgridOfReport({
				url: "/report/data/StockProductDetail.action?getStockProductDetail=", 
				colNames: colNames,
				colModel:colModel, 
				rowNum:10, 
				rowList:[10,20,30,40], 
				pager: 'stockProductDetailBottom', 
				sortname: 'stockroom_name', 
				sortorder: "desc", 
				caption:"库存数量明细",
				reportSum:{
					sumUrl:"/report/data/StockProductDetail.action?getStockProductDetailSum=",
					colSum:colSum
					}
			});

			window.onbeforeunload=function(){ 
				if(windowId!=undefined){
					windowId.close();
				}
			}


			$("#btndownload").click(function(){ 
				windowId =  window.open('/report/view/ViewDownload.action?nodeId=1187','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
			});
		});
	</script>
</head>
<body>
<form>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="ye_header_left" width="16px" title=""><div class="ellipsis_div" style="width:16px;">&nbsp;</div></td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 报表管理 &gt;&gt; 库存数量明细</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><br/>
			<div class="mo_wp">
			<div class="mo_con" >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%">库房名称</td>
					<td class="td_02" width="30%"><input type="text" name="param.stockroomName"></td>
					<td class="td_01" width="20%">产品分类名称</td>
					<td class="td_02" width="30%"><input type="text" name="param.productTypeName" ></td>
				</tr>
				<tr>
					<td class="td_01" width="20%">产品系列名称</td>
					<td class="td_02" width="30%"><input type="text" name="param.productSerieName"></td>
					<td class="td_01" width="20%">产品编码</td>
					<td class="td_02" width="30%"><input type="text" name="param.productCode" ></td>
				</tr>
				<tr>
					<td class="td_01" width="20%">产品名称</td>
					<td class="td_02" width="30%"><input type="text"  name="param.productName"></td>
					<td class="td_01" width="20%">规格型号</td>
					<td class="td_02" width="30%"><input type="text" name="param.productType" ></td>
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
		<table id="stockProductDetail"></table>
		<div id="stockProductDetailBottom"></div>
		<div id="sumBottom"></div>
	</div>
</form>
</body>
</html>