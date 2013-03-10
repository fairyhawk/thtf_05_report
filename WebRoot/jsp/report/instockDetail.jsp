<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>入库单明细</title>
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
<c:if test="${requestScope.role ==13}">
	<c:set var="specialIs13" value="special:true,"></c:set>
</c:if>		 
	<script type="text/javascript"> 
		var windowId;
		var colNames = new Array('合同流水号', '产品合同号', '公司合同号', '产品分类名称', '入库单号', '库房名称', '供货商名称',
                '入库总金额', '申请日期', '入库日期', '人员名称', '入库单状态', '产品编码', '产品名称', '规格型号', '单位',
                '采购单价', '入库数', '入库金额', '返厂数', '返厂金额', '实际入库数量', '实际入库金额', '指定金额',
                '未付款金额', '厂家发货日期', '要求付款日期', '收票金额');
		var colModel = new Array(
				{name:'buy_contract_id',index:'buy_contract_id',defaultReport:true, width:90, align:"left"},
				{name:'product_contract_code',index:'product_contract_code', defaultReport:true,width:90, align:"left"},
				{name:'company_contract_code',index:'company_contract_code', defaultReport:true,width:120, align:"left"}, 
				{name:'product_type_name',index:'product_type_name', width:90,defaultReport:true, align:"left"}, 
				{name:'instock_id',index:'instock_id',defaultReport:true, width:90, align:"left"},
				{name:'stockroom_name',index:'stockroom_name', width:200, align:"left"},
				{name:'supplier_name',index:'supplier_name', width:180, align:"left"},
				{name:'instock_totalmoney',index:'instock_totalmoney', ${specialIs13} width:70, align:"right", formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}}, 
				{name:'date',index:'date', width:70, align:"left"},
				{name:'stock_date',index:'stock_date', width:70, align:"left"},
				{name:'user_name',index:'user_name', width:70, align:"left"},
				{name:'status_name',index:'status_name', width:70, align:"left"},
				{name:'product_code',index:'product_code',defaultReport:true,  width:100, align:"left"},
				{name:'product_name',index:'product_name',defaultReport:true,  width:100, align:"left"},
				{name:'product_type',index:'product_type', width:120, align:"left"},
				{name:'product_unit',index:'product_unit', width:40, align:"left"},
				{name:'buy_price',index:'buy_price', ${specialIs13} width:60, align:"right", formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
				{name:'instock_count',index:'instock_count', width:60, defaultReport:true, align:"right"},
				{name:'instock_money',index:'instock_money',${specialIs13} width:80, defaultReport:true, align:"right", formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
				{name:'back_count',index:'back_count', width:60, align:"right"},
				{name:'back_money',index:'back_money',${specialIs13} width:60, align:"right", formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
				{name:'stock_count_rel',index:'stock_count_rel', width:80, align:"right"},
				{name:'stock_money_rel',index:'stock_money_rel',${specialIs13} width:80, align:"right", formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
				{name:'appoint_money',index:'appoint_money',${specialIs13} width:80, align:"right", formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
				{name:'non_payment_money',index:'non_payment_money',${specialIs13} width:80, align:"right", formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}},
				{name:'factory_send_date',index:'factory_send_date', width:80, align:"left"},
				{name:'payment_date',index:'payment_date', width:80, align:"left"},
				{name:'receive_invoice_money',index:'receive_invoice_money',${specialIs13} width:80, align:"right" ,formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: ""}});
		var colSum = new Array(
				//{name:'instockTotalmoneySum',${specialIs13}tableName:'入库总金额合计:'},
				{name:'instockMoneySum',${specialIs13}tableName:'入库金额合计:', format:"money"},
				{name:'backMoneySum',${specialIs13}tableName:'返厂金额合计:', format:"money"},
				{name:'stockMoneyRelSum',${specialIs13}tableName:'实际入库金额合计:', format:"money"},
				{name:'appointMoneySum',${specialIs13}tableName:'指定金额合计:', format:"money"},
				{name:'nonPaymentMoneySum',${specialIs13}tableName:'未付款金额合计:', format:"money"},
				{name:'receiveInvoiceMoneySum',${specialIs13}tableName:'收票金额合计:', format:"money"});
		$(document).ready(function(){ 
			$("#instockDetail").jqgridOfReport({
				url: "/report/data/InstockDetail.action?getInstockDetail", 
				colNames: colNames,
				colModel: colModel, 
				rowNum:10, 
				rowList:[10,20,30,40], 
				pager: 'instockDetailBottom', 
				sortname: 'buy_contract_id', 
				sortorder: "desc", 
				caption:"入库单明细",
				reportSum:{
					sumUrl:"/report/data/InstockDetail.action?getInstockDetailSum",
					colSum:colSum
					},
				datepicker:["stockDateBegin","stockDateEnd","paymentDateBegin","paymentDateEnd"]
			});
			
			
			window.onbeforeunload=function(){ 
				if(windowId!=undefined){
					windowId.close();
				}
			}


			$("#btndownload").click(function(){ 
				windowId =  window.open('/report/view/ViewDownload.action?nodeId=1153','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
			});
			

		});
	</script>
</head>
<body>
<form>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="ye_header_left" width="16px" title=""><div class="ellipsis_div" style="width:16px;">&nbsp;</div></td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 报表管理 &gt;&gt; 入库单明细</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><br/>
			<div class="mo_wp">
			<div class="mo_con" >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%">合同流水号</td>
					<td class="td_02" width="30%"><input type="text" name="param.buyContractId"></td>
					<td class="td_01" width="20%">产品合同号</td>
					<td class="td_02" width="30%"><input type="text" name="param.productContractCode" ></td>
				</tr>
				<tr>
					<td class="td_01" width="20%">公司合同号</td>
					<td class="td_02" width="30%"><input type="text" name="param.companyContractCode"></td>
					<td class="td_01" width="20%">产品分类名称</td>
					<td class="td_02" width="30%"><input type="text" name="param.productTypeName" ></td>
				</tr>
				<tr>
					<td class="td_01" width="20%">入库单号</td>
					<td class="td_02" width="30%"><input type="text"  name="param.instockId"></td>
					<td class="td_01" width="20%">库房名称</td>
					<td class="td_02" width="30%"><input type="text"  name="param.stockroomName"></td>
				</tr>
				<tr>
					<td class="td_01" width="20%">供货商名称</td>
					<td class="td_02" width="30%"><input type="text"  name="param.supplierName"></td>
					<!-- <td class="td_01" width="20%"> 人员名称:</td>
					<td class="td_02" width="30%"><input type="text"  name="param.userName"></td>  -->
					<td class="td_01" width="20%">产品名称</td>
					<td class="td_02" width="30%"><input type="text"  name="param.productName"></td>
				</tr>
				<tr>
					<td class="td_01" width="20%">入库单状态</td>
					<td class="td_02" width="30%">
					<select name="param.statusName"  style="width:130px;">
						<option value="">--请选择--</option>
						<option>未提交</option>
						<option>产品总监待评审</option>
						<option>产品总监未通过</option>
						<option>待核对</option>
						<option>核对未通过</option>
						<option>入库成功</option>
					</select>
					</td>
					<td class="td_01" width="20%">产品编码</td>
					<td class="td_02" width="30%"><input type="text"  name="param.productCode"></td>
				</tr>
				<!-- <tr>
					<td class="td_01" width="20%">产品名称</td>
					<td class="td_02" width="30%"><input type="text"  name="param.productName"></td>
					<td class="td_01" width="20%"></td>
					<td class="td_02" width="30%"></td>
				</tr> -->
				 <tr>
	                <td class="td_01" width="20%">规格型号</td>	
	                <td class="td_02" width="30%">
	                	<input type="text" name="param.productType">
					</td>
					<td class="td_01" width="20%"></td>
					<td class="td_02" width="30%"></td>
                </tr> 
                <tr>
	                <td class="td_01">入库起始日期</td>	
	                <td class="td_02">
	                	<input type="text" name="param.stockDateBegin" id="stockDateBegin" readonly="true">
					</td>
					<td class="td_01">入库终止日期</td>
	                <td class="td_02">
	                	<input type="text" name="param.stockDateEnd" id="stockDateEnd" readonly="true">
	                </td>
                </tr>
               <!-- <tr>
	                <td class="td_01">厂家发货起始日期</td>	
	                <td class="td_02">
	                	<input type="text" name="param.factorySendDateBegin" id="factorySendDateBegin" date="true">
					</td>
					<td class="td_01">厂家发货终止日期</td>
	                <td class="td_02">
	                	<input type="text" name="param.factorySendDateEnd" id="factorySendDateEnd" date="true">
	                </td>
                </tr>-->
                <tr>
	                <td class="td_01">要求付款起始日期</td>	
	                <td class="td_02">
	                	<input type="text" name="param.paymentDateBegin" id="paymentDateBegin" readonly="true">
					</td>
					<td class="td_01">要求付款终止日期</td>
	                <td class="td_02">
	                	<input type="text" name="param.paymentDateEnd" id="paymentDateEnd" readonly="true">
	                </td>
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
		<table id="instockDetail"></table>
		<div id="instockDetailBottom"></div>
		<div id="sumBottom"></div>
	</div>
</form>
</body>
</html>