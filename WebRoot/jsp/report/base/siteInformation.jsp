<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>站内信管理</title> 
	<style type="text/css">
	 
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		.rowselected {
			background-color: #868686;
		}
	 
	</style>
	<script language="JavaScript"> 
		$(document).ready(function(){ 
			if($("#table")){
				$("#table tr:even").addClass("treven");
				$("#table tr").each(function(i){
					$(this).mouseover(function(){
						$(this).addClass("over");
					});
					$(this).mouseout(function(){
						$(this).removeClass("over");
					});
					$(this).click( function(){
					  if( $(this).hasClass("rowselected") ){
						$(this).removeClass("rowselected");
					  }else{
						$(this).addClass("rowselected");
					  }
					});
				});
			} 

			$('#JianSuo').click(function(){  
				$('#frmConList').submit();
			});

			if("${actionBean.param.filename}"==100) {
				$('#checkbox1').attr('checked',true);
			}
		}); 

		function deleteSite(id){
			if(confirm("是否确认删除？")){
				window.location = '${ctx}/data/base/SiteInformation.action?deleteSiteInfo&siteInformationId='+id;
			}
		} 
	</script> 
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 基础信息管理 &gt;&gt; 站内信管理</td>
		<td class="ye_header_right" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><br/>
		<div class="mo_wp">
		<div style="display: ; " class="mo_con" > 
			<s:form beanclass="cn.com.thtf.cms.report.action.data.base.SiteInformationActionBean" id="frmConList">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3"> 
					<tr>
						<td class="td_01">标题</td>
						<td class="td_02"><s:text name="param.subject" style="width:240px;" maxlength="20"/></td>
						<td class="td_01">内容</td>
						<td class="td_02"><s:text name="param.text" style="width:240px;" maxlength="100"/></td>
					</tr>  
					<tr>
                  		<td class="td_01">是否包含附件</td>
                  		<td class="td_02"><s:checkbox  id="checkbox1" name="param.filename" value="100"/></td>
                  		<td class="td_01">&nbsp;</td>
                  		<td class="td_02">&nbsp;</td>
                  	</tr>
					<tr>
						<td colspan="4" align="center" style="height:30px;"> 
							<s:image id="JianSuo" name="siteInformationList" src="/images/btn_JianSuo.gif" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<div class="mo_title" onclick="fMainListToggle(this)">
			<div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
		</div>
		</div>
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td align="center">
		<br/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
			<tr> 
				<th nowrap="nowrap" width="100px" height="18">标题</th>
				<th nowrap="nowrap" width="80px">通知时间</th>
				<th nowrap="nowrap" width="220px">内容</th>
				<th nowrap="nowrap" width="80px">是否滚动</th>
				<th nowrap="nowrap" width="100px">附件</th> 
				<th nowrap="nowrap" width="100px">操作</th>
			</tr>
			<c:forEach var="site" items="${siteList}">     
				<tr> 
					<td nowrap="nowrap" height="18">${site.subject}</td>
					<td nowrap="nowrap">${site.datetime}</td>
					<td nowrap="nowrap" title="${site.text}">
						<div class="ellipsis_div" style="width:96px;">${site.text}&nbsp;</div>
					</td>
					<td nowrap="nowrap">
						<c:if test="${site.rollFlag==0}">滚动</c:if>
						<c:if test="${site.rollFlag==1}">不滚动</c:if>
					</td>
					<td nowrap="nowrap" title="点击下载">
						<a href="${ctx}/data/base/SiteInformation.action?downLoad=&param.id=${site.id}">${site.filename}</a>
					</td>
					<td nowrap="nowrap"><a href="javascript:window.location ='${ctx}/data/base/SiteInformation.action?viewSiteInfo&siteInformationId=${site.id}'">查看</a>&nbsp;<a href="javascript:window.location ='${ctx}/data/base/SiteInformation.action?updateSiteInfoInit&siteInformationId=${site.id}'">修改</a>&nbsp;<a href="javascript:deleteSite(${site.id});">删除</a></td>
				</tr> 
			</c:forEach> 
		</table>
		<br/>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
			<tr>
				<td width="100px">
					<a href="javascript:window.location ='${ctx}/data/base/SiteInformation.action?addSiteInfoInit'">
						<img src="${ctx}/images/btnAdd.gif" />
					</a>
				</td>
				<td align="right">&nbsp;<%@ include file="/jsp/common/page.jsp"%></td>
			</tr>
        </table>   
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</body>
</html>
