<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建站内信</title>
<script type="text/javascript">

var allow = new Array(".txt", ".pdf", ".xml", ".rtf", ".doc",
        ".docx", ".xls", ".xlsx", ".rar"); 

function validate(){

	var ext = $("#upfile").val().substring($("#upfile").val().lastIndexOf('.'));
	
	var filename=$("#upfile").val().replace(/.*(\/|\\)/, "");
	//alert(filename);
	
 	if($.trim($("#subject").val()).length==0){
 		alert("标题不能为空");
 		return false;
 	}
 	
 	if ($.trim($("#text").val()).length==0){
		alert("内容不能为空");
		return false;
	}
	
	if ($.trim($("#subject").val()).length>20){
		alert("标题过长");
		return false;
	}
	
	if ($.trim($("#text").val()).length>200){
		alert("内容过长");
		return false;
	}
	if($.trim($("#upfile").val()).length>0){
		if($.inArray(ext, allow) == -1) { 
	    	alert('不可上传的文件类型!'); 
	    	return false;
		} 
 	}
 	if(filename.length>20){
 		alert("附件名称过长");
		return false;
 	}
 	return true;
}
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <s:form beanclass="cn.com.thtf.cms.report.action.data.base.SiteInformationActionBean">
	<tr>
		<td class="ye_header_left">&nbsp;</td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp;当前位置： 基础信息管理  &gt;&gt; 新建站内信</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		
		
		<td >&nbsp;</td>
		<td><br /> 
			<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			
        	<tr>
            	<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;标题</td>
              <td class="td_02" ><s:text maxlength="20" id="subject" name="siteInfomationEntity.subject" style="width:200px;"/></td>
            </tr>
            <tr>
            	<td class="td_01">&nbsp;是否滚动</td>
              <td class="td_02"> <s:checkbox  name="siteInfomationEntity.rollFlag" value="0"/> </td>
            </tr>
            <tr>
            	<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;内容</td>
              	<td class="td_02"><s:textarea name="siteInfomationEntity.text" id="text" cols="40" rows="7"/></td>
            </tr>
            <tr>
            	<td class="td_01">上传附件</td>
                <td class="td_02"><s:file name="uploadFile" id="upfile"/></td>
            </tr>
            
            
        	</table>
        
         <table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
		  <tr>
            <td height="50px" valign="bottom" align="center"><!--   <s:submit name="addSiteInfo" value="提交"/>-->
              <a href="${ctx}/view/base/ViewSiteInformation.action" ><img src="${ctx}/images/btnBack.gif" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <s:image name="addSiteInfo" src="/images/btnSubmit.gif" onclick="return validate();"/> 
            </td>
          </tr>
         </table>
	</tr>
</s:form>
</table>
</body>
</html>