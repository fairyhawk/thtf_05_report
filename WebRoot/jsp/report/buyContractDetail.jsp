<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>采购合同产品明细</title>
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

</head>
<script type="text/javascript">
var windowId;
var colNames = new Array("合同流水号", "产品合同号", "公司合同号", "供货商名称", "产品分类名称", "产品编码",
                "产品名称", "规格型号", "单位", "采购数", "采购单价", "采购金额", "入库金额", "入库数量", "指定金额",
                "收票金额", "退货合同金额", "退货合同数量", "返厂金额", "返厂数量", "实际入库数量", "实际入库金额", "未入库数量",
                "特殊说明", "实际合同数","要求供货日期");
var colModel = new Array(
		{name:'buyContractId',index:'buy_contract_id',defaultReport:true, width:90},
        {name:'productContractCode',index:'product_contract_code', defaultReport:true,width:90},
	    {name:'companyContractCode',index:'company_contract_code', defaultReport:true,width:90}, 
	    {name:'supplierName',index:'supplier_name', width:120,defaultReport:true, align:"left"},
	    
	    {name:'productTypeName',index:'product_type_name',defaultReport:true, width:80},
	    {name:'productCode',index:'product_code', width:80,defaultReport:true},
	    {name:'productName',index:'product_name', width:80,defaultReport:true},
	    {name:'productType',index:'product_type', defaultReport:true,width:110},
        
	    {name:'productUnit',index:'product_unit', width:50,defaultReport:true},
	    {name:'buyContractDetailCount',index:'buy_contract_detail_count', width:50, align:"right"}, 
	    {name:'buyContractPrice',index:'buy_contract_price', width:80, defaultReport:true, align:"right",formatter:'currency',
formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 5, prefix: ""}},
	    {name:'buyContractMoney',index:'buy_contract_money', width:80, defaultReport:true, align:"right",formatter:'currency',
formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 5, prefix: ""}},
	    {name:'inStockMoney',index:'in_stock_money', width:80, defaultReport:true, align:"right",formatter:'currency',
formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 5, prefix: ""}},
	    
	    {name:'inStockCount',index:'in_stock_count', width:50, align:"right"},
	    {name:'appointMoney',index:'appoint_money', width:80, align:"right", defaultReport:true,formatter:'currency',
formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 5, prefix: ""}},
	    {name:'invoiceMoney',index:'invoice_money', width:80, align:"right", defaultReport:true,formatter:'currency',
formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 5, prefix: ""}},
        {name:'buyBackContractMoney',index:'buy_back_contract_money', width:100, align:"right", defaultReport:true,formatter:'currency',
formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 5, prefix: ""}},
	    {name:'buyBackContractCount',index:'buy_back_contract_count', width:100, align:"right"}, 
	    
	    {name:'buyBackGoodsMoney',index:'buy_back_goods_money', width:100, align:"right", defaultReport:true,formatter:'currency',
formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 5, prefix: ""}},
	    {name:'buyBackGoodsCount',index:'buy_back_goods_count', width:80, align:"right"},
	    {name:'factInStockCount',index:'fact_in_stock_count', width:80, align:"right"},
	    {name:'factInStockMoney',index:'fact_in_stock_money', width:80, align:"right", defaultReport:true,formatter:'currency',
formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 5, prefix: ""}},
	    {name:'notInStockCount',index:'not_in_stock_count', width:80, align:"right"},
	    
	    {name:'text',index:'text', width:80, align:"right"},
	    {name:'factContractCount',index:'fact_contract_count', width:100, align:"right"},
		{name:'requestDate',index:'request_date', width:100, align:"right", defaultReport:true}
	   )
$(document).ready(function(){
	$("#buyContractDetail").jqgridOfReport({
		url: "/report/data/RBuyContractDetail.action?getBuyContractDetailList=",//请求的链接，必配置项，jqgrid必配置项
		colNames: colNames,//上面定义的列标题，必配置项，jqgrid必配置项
		colModel:colModel, //上面定义的列值配置，必配置项，jqgrid必配置项
		pager: 'buyContractDetailBottom', //显示底部条数，可不用配置，jqgrid可不用配置
		sortname: 'buyContractId',  //排序的名字，必配置项，是jqgrid必配置项
		caption:"采购合同产品明细",//标题，可不用配置，jqgrid可不用配置配置项
		
		reportSum:{
			sumUrl:"/report/data/RBuyContractDetail.action?getBuyContractDetailSum=",
			colSum:[
				{name:'sumBuyContractMoney',tableName:'采购总金额:',format:"money", decimalPlaces: 5},
				{name:'sumInStockMoney',tableName:'入库总金额:',format:"money", decimalPlaces: 5},
				{name:'sumAppointMoney',tableName:'指定总金额:',format:"money", decimalPlaces: 5},
				{name:'sumInvoiceMoney',tableName:'收票总金额:',format:"money", decimalPlaces: 5},
				{name:'sumBuyBackContractMoney',tableName:'退货合同总金额 :',format:"money", decimalPlaces: 5},
				{name:'sumBuyBackGoodsMoney',tableName:'返厂总金额:',format:"money", decimalPlaces: 5},
				{name:'sumFactInStockMoney',tableName:'实际入库总金额 :',format:"money", decimalPlaces: 5}
			        ]
			},
			datepicker:['minRequestDate','maxRequestDate']
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
				windowId =  window.open('/report/view/ViewDownload.action?nodeId=1152','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
			});
});
</script>
<body>
<form>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 报表管理 &gt;&gt; 采购合同产品明细</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">合同流水号</td>
                <td class="td_02" width="30%"><input type="text" name="param.buyContractId" id="param.buyContractId" /></td>
                <td class="td_01" width="20%">供货商名称</td>
                <td class="td_02" width="30%"><input type="text" name="param.supplierName" /></td>
              </tr>
              <tr>
                <td class="td_01">产品合同号</td>
                <td class="td_02"><input type="text" name="param.productContractCode" /></td>
				<td class="td_01">公司合同号</td>
                <td class="td_02"><input type="text" name="param.companyContractCode" /></td>
              </tr>
              <tr>
                <td class="td_01" width="20%">产品编码</td>
                <td class="td_02" width="30%">
                	<input type="text"  name="param.productCode" id="productCode">                </td>
                <td class="td_01" width="20%">产品名称</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.productName" id="productName">                </td>
              </tr>
              <tr>
                <td class="td_01">规格型号</td>
                <td class="td_02"><input type="text"  name="param.productType" id="param.productType" /></td>
                <td class="td_01">产品分类名称</td>
                <td class="td_02"><input type="text" name="param.productTypeName" /></td>
              </tr>
			  <tr>
                <td class="td_01">要求供货起始日期</td>	
                <td class="td_02">
                	<input type="text" name="param.minRequestDate" id="minRequestDate" readonly="true">
				</td>
				<td class="td_01">要求供货终止日期</td>
                <td class="td_02">
                	<input type="text" name="param.maxRequestDate" id="maxRequestDate" readonly="true">
                </td>
              </tr>
              <tr>
                <td colspan="4" class="td_02" style="text-align:center">
                	<a href="#" change="true" style="margin-right:60px"><img src="${ctx}/images/btnChangeField.gif" /></a>
                	<a href="#" find="true" style="margin-right:60px"><img src="${ctx}/images/btn_JianSuo.gif" /></a>				
					<a href="#" style="margin-right:60px"><img src="${ctx}/images/btnXiaZai.gif" id="btndownload"/></a></td>
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
<div style="padding-right:50px;padding-left:18px;margin-right:auto;">
		<table id="buyContractDetail"></table>
		<div id="buyContractDetailBottom"></div>	
</div>		
</body>
</html>