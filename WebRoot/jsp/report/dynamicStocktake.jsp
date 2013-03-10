<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发货单明细报表</title>
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

</head>
<script type="text/javascript">
var windowId;
var colNames = new Array("库房名称", "产品编码", "产品名称", "规格型号", "单位", "库存数量","所在地库存汇总", "所在地"
                );
var colModel = new Array(
		{name:'stockName',index:'stock_name',defaultReport:true, width:250},
        {name:'productCode',index:'product_code', defaultReport:true,width:120},
	    {name:'productName',index:'product_name', defaultReport:true,width:100}, 
	    {name:'productType',index:'product_type', width:175,defaultReport:true, align:"left"},
	    
	    {name:'productUnit',index:'product_unit',defaultReport:true, width:80},
	    {name:'stockroomCount',index:'stockroom_count', width:80,defaultReport:true, align:"right"},
        
	    {name:'stockNumSum',index:'stock_num_sum', defaultReport:true,width:100, align:"right"},
	    {name:'shortName',index:'short_name', width:150, defaultReport:true}
	   )
$(document).ready(function(){
	$("#stocktake").jqgridOfReport({
		url: "/report/data/RDynamicStocktake.action?getStocktakeList=",//请求的链接，必配置项，jqgrid必配置项
		colNames: colNames,//上面定义的列标题，必配置项，jqgrid必配置项
		colModel:colModel, //上面定义的列值配置，必配置项，jqgrid必配置项
		pager: 'stocktakeBottom', //显示底部条数，可不用配置，jqgrid可不用配置
		sortname: 'stockName',  //排序的名字，必配置项，是jqgrid必配置项
		caption:"动态盘点表",//标题，可不用配置，jqgrid可不用配置配置项
		pgbuttons:true
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
				windowId =  window.open('/report/view/ViewDownload.action?nodeId=1191','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
			});
});
</script>
<body>
<form>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 报表管理 &gt;&gt; 动态盘点</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">库房名称</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.stockName">                </td>
                <td class="td_01" width="20%">产品编码</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.productCode" >                </td>
              </tr>
              <tr>
                <td class="td_01">产品名称</td>
                <td class="td_02">
                	<input type="text" name="param.productName">                </td>
				<td class="td_01">规格型号</td>
                <td class="td_02">
                	<input type="text" name="param.productType" >                </td>
              </tr>
              <tr>
                <td class="td_01" width="20%">单位</td>
                <td class="td_02" width="30%">
                	<input type="text"  name="param.productUnit" id="productUnit">                </td>
                <td class="td_01" width="20%">所在地</td>
                <td class="td_02" width="30%">
                	<input type="text" name="param.shortName" id="shortName">                </td>
              </tr>
              <tr>
                <td colspan="4" class="td_02" style="text-align:center">
                	<a href="#" change="true" style="margin-right:60px"><img src="${ctx}/images/btnChangeField.gif" /></a>
                	<a href="#" find="true" style="margin-right:60px"><img src="${ctx}/images/btn_JianSuo.gif" /></a>
					<a href="#" style="margin-right:60px"><img src="${ctx}/images/btnXiaZai.gif" id="btndownload"/></a>				</td>
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
		<table id="stocktake" with="100%"></table>
		<div id="stocktakeBottom"></div>	
</div>		
</body>
</html>