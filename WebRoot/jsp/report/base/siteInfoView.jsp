<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看站内信</title>
<script type="text/javascript">
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  
	<tr>
		<td class="ye_header_left">&nbsp;</td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp;当前位置： 基础信息管理  &gt;&gt; 查看站内信</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td >&nbsp;</td>
		<td><br /> 
			<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        	<tr>
            	<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;标题</td>
              <td class="td_02" >${actionBean.siteInfomationEntity.subject}</td>
            </tr>
            <tr>
            	<td class="td_01">&nbsp;是否滚动</td>
              <td class="td_02">
				<c:if test="${actionBean.siteInfomationEntity.rollFlag==0}">是</c:if>
              	<c:if test="${actionBean.siteInfomationEntity.rollFlag==1}">否</c:if>
				</td>
            </tr>
            <tr>
            	<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;内容</td>
              	<td class="td_02">${actionBean.siteInfomationEntity.text}</td>
            </tr>
            <tr>
            	<td class="td_01">上传附件</td>
                <td class="td_02">${actionBean.siteInfomationEntity.filename} </td>
            </tr>
            
            
        </table>
        
         <table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
							
		  <tr>
            <td height="50px" valign="bottom" align="center"><a href="${ctx}/view/base/ViewSiteInformation.action" ><img src="${ctx}/images/btnBack.gif" /></a></td>
          </tr>
        </table>
		
			
		<td >&nbsp;</td>
	</tr>
</table>
</body>
</html>