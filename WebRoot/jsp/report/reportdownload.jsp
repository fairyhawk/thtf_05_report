<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报表下载</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript">
//-----Map类型-------------
function Map() {  
    /** 存放键的数组(遍历用到) */  
    this.keys = new Array();  
    /** 存放数据 */  
    this.data = new Object();  
      
    /** 
     * 放入一个键值对 
     * @param {String} key 
     * @param {Object} value 
     */  
    this.put = function(key, value) {  
        if(this.data[key] == null){  
            this.keys.push(key);  
        }  
        this.data[key] = value;  
    };  
      
    /** 
     * 获取某键对应的值 
     * @param {String} key 
     * @return {Object} value 
     */  
    this.get = function(key) {  
        return this.data[key];  
    };  
      
    /** 
     * 删除一个键值对 
     * @param {String} key 
     */  
    this.remove = function(key) {  
        this.keys.remove(key);  
        this.data[key] = null;  
    };  
      
    /** 
     * 遍历Map,执行处理函数 
     *  
     * @param {Function} 回调函数 function(key,value,index){..} 
     */  
    this.each = function(fn){  
        if(typeof fn != 'function'){  
            return;  
        }  
        var len = this.keys.length;  
        for(var i=0;i<len;i++){  
            var k = this.keys[i];  
            fn(k,this.data[k],i);  
        }  
    };  
      
    /** 
     * 获取键值数组(类似Java的entrySet()) 
     * @return 键值对象{key,value}的数组 
     */  
    this.entrys = function() {  
        var len = this.keys.length;  
        var entrys = new Array(len);  
        for (var i = 0; i < len; i++) {  
            entrys[i] = {  
                key : this.keys[i],  
                value : this.data[i]  
            };  
        }  
        return entrys;  
    };  
      
    /** 
     * 判断Map是否为空 
     */  
    this.isEmpty = function() {  
        return this.keys.length == 0;  
    };  
      
    /** 
     * 获取键值对数量 
     */  
    this.size = function(){  
        return this.keys.length;  
    };  
      
    /** 
     * 重写toString  
     */  
    this.toString = function(){  
        var s = "{";  
        for(var i=0;i<this.keys.length;i++,s+=','){  
            var k = this.keys[i];  
            s += k+"="+this.data[k];  
        }  
        s+="}";  
        return s;  
    };  
}  

//--------------------


   var pagemap = new Map();
	 
   <c:forEach  items ="${actionBean.downLoadFileInfo.filemap}" var="map"  >
    	var type = "${map.key}";
    	var a = new Array() 
        <c:forEach items="${map.value}" var="list">
         var value = "${list}";
         a.push(value);
    	</c:forEach>  
    	pagemap.put(type,a); 
    	
　　</c:forEach>



	function downloadDetail(filetype) {
		if ("001"==filetype) {
            typeNameChs = "销售合同汇总表下载";
        } else if ("002"==filetype) {
            typeNameChs = "销售合同产品明细表下载";
        } else if ("003"==filetype) {
            typeNameChs = "退货合同明细表下载";
        } else if ("004"==filetype) {
            typeNameChs = "发货单产品明细表下载";
        } else if ("005"==filetype) {
            typeNameChs = "退货产品明细表下载";
        } else if ("006"==filetype) {
            typeNameChs = "回款表下载";
        } else if ("007"==filetype) {
            typeNameChs = "退款明细表下载";
        } else if ("008"==filetype) {
            typeNameChs = "开票汇总下载";
        } else if ("009"==filetype) {
            typeNameChs = "退票产品明细表下载";
        } else if ("010"==filetype) {
            typeNameChs = "发货单汇总表下载";
        } else if ("011"==filetype) {
            typeNameChs = "库存数量明细下载";
        } else if ("012"==filetype) {
            typeNameChs = "运费明细表下载";
        } else if ("013"==filetype) {
            typeNameChs = "运费分摊下载";
        } else if ("014"==filetype) {
            typeNameChs = "动态盘点表下载";
        } else if ("015"==filetype) {
            typeNameChs = "采购合同汇总下载";
        } else if ("016"==filetype) {
            typeNameChs = "采购合同产品明细表下载";
        } else if ("017"==filetype) {
            typeNameChs = "入库单明细下载";
        } else if ("018"==filetype) {
            typeNameChs = "采购付款汇总下载";
        } else if ("019"==filetype) {
            typeNameChs = "采购合同收票下载";
        } else if ("020"==filetype) {
            typeNameChs = "客户信用明细下载";
        } else if ("021"==filetype) {
            typeNameChs = "客户销售经理对应下载";
        } else if ("022"==filetype) {
            typeNameChs = "采购付款明细下载";
        } else if ("023"==filetype) {
            typeNameChs = "操作记录下载";
        }
		$("#filelist tr[id!='bottom']").remove();
		updateFileList(filetype);
		$("#dialog").dialog({ 
	            resizable: false,
	            width:500,
	            title:typeNameChs
	    });
	    
	}
    
    function updateFileList(filetype){
    	var arr = pagemap.get(filetype);
    	var url="";
    	if(arr!=null&&arr!=""){
	    	for(var i=0; i<arr.length; i++){
	    	  url= "<s:url beanclass="cn.com.thtf.cms.report.action.view.DownloadReportActionBean" event="downLoad"/>"+"&reportId="+filetype+"&week="+ arr[i] ;
	    	  //url= '<s:url beanclass="cn.com.thtf.cms.report.action.view.DownloadReportActionBean" event="downLoad"><s:param name="reportId" value="'+FormatNum(String(filetype),3)+'"/><s:param name="week" value="'+arr[i]+'"/></s:url>';
	    	  $('#bottom').before('<tr name="oldFileList"><td class="dbsx_td"><a href="'+url+'">'+'星期'+weekName(arr[i])+'</a></td></tr>');
	    	}
	    	$('#bottom').before('<tr><td colspan="2" height="30px">&nbsp;</td></tr>'); 
    	}else{
    	
    	}
    }
   
    
    $(document).ready(function() {
        $("#dialog").hide();
       
    });
    
	
	function weekName(name){
		if ("1"==name) {
            name = "一";
        } else if ("2"==name) {
            name = "二";
        } else if ("3"==name) {
            name = "三";
        } else if ("4"==name) {
            name = "四";
        } else if ("5"==name) {
            name = "五";
        } else if ("6"==name) {
            name = "六";
        } else if ("7"==name) {
            name = "日";
        }
        return name;
	}
	

</script>
 
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  
	<tr>
		<td class="ye_header_left">&nbsp;</td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp;当前位置： 报表管理 &gt;&gt; 报表下载</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td >&nbsp;</td>
		<td><br /> 
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
				<tr>
					<th nowrap="nowrap" > 销售类报表</th>
					<th nowrap="nowrap" > 采购类报表</th> 
					<th nowrap="nowrap">其他报表 </th>
				</tr>
				<tr>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('001')">销售合同汇总表</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('015')">采购合同汇总</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('011')">库存数量明细</a></td>
				</tr>
				<tr>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('002')">销售合同产品明细表</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('016')">采购合同产品明细表</a></td> 
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('012')">运费明细表</a></td>
				</tr>
				<tr>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('003')">退货合同明细表</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('017')">入库单明细</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('013')">运费分摊</a></td>
				</tr>
				<tr>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('004')">发货单产品明细表</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('018')">采购付款汇总</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('014')">动态盘点表</a></td>
				</tr>
				<tr>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('005')">退货产品明细表</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('022')">采购付款明细</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('020')">客户信用明细</a></td> 
				</tr>
				<tr>
					<td class="td_02" width="50%"><a href="javascript:void(0)" onclick="downloadDetail('006')">回款表</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('019')">采购合同收票</a></td>
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('021')">客户销售经理对应</a></td> 
				</tr>
				<tr>
					<td class="td_02" width="50%"><a href="javascript:void(0)" onclick="downloadDetail('007')">退款明细表</a></td>
					<td class="td_02"></td> 
					<td class="td_02"><a href="javascript:void(0)" onclick="downloadDetail('023')">操作记录</a></td>
				</tr>
				 
				<tr>
					<td class="td_02" width="50%"><a href="javascript:void(0)" onclick="downloadDetail('008')">开票汇总</a></td>
					<td class="td_02"></td> 
					<td class="td_02"></td>
				</tr> 
				<tr>
					<td class="td_02" width="50%"><a href="javascript:void(0)" onclick="downloadDetail('009')">退票产品明细表</a></td>
					<td class="td_02"></td> 
					<td class="td_02"></td> 
				</tr>
				
				<tr>
					<td class="td_02" width="50%"><a href="javascript:void(0)" onclick="downloadDetail('010')">发货单汇总表</td> 
					<td class="td_02"></td> 
					<td class="td_02"></td> 
				</tr>
			</table>
			     
			
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td height="50px" align="center" valign="bottom"><a href="javascript:history.go(-1)"><img src="${ctx}/images/btnBack.gif" /></a></td>
		<td></td>
	</tr>
</table>
<div id="dialog" >
    <table id="filelist" border="0" cellpadding="0" cellspacing="0" class="dbsx">
        <tr id="bottom"><td colspan="2"></td></tr>
      
    </table>
</div>
</body>
</html>