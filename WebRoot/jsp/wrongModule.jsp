<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>错误</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/jquery-ui-1.8.custom.min.js"></script>
<style type="text/css">
<!--
html,body{
    margin:0px;
	height:100%
	font-family:"宋体", Basemic, "Arial Narrow";
	font-size:12px;
	background-color:#ffffff; 
}
.ffnn-corner{
	font-size:12px;
	background-color:#ffffff; 
	position:absolute;
    width:401px;
	height:180px;
	left:50%;
    top:50%; 
	margin-left:-200px;   
    margin-top:-90px;}
-->
</style>
 
 

</head>
<body>
	<div class="ffnn-corner">
		<div style="background-image:url(${ctx}/images/wrong_tou.jpg); background-repeat:no-repeat; width:401px; height:32px"></div>
		<div style="border:1px solid #cccccc;height:100px"><br/>
			<div style="float:left;padding-left:20px"><img src="${ctx}/images/wrong_1.jpg" width="37" height="37" /></div>
			<div style="float:left;padding:12px 0px 0px 20px;font-weight:bold;">
				<div id="content"> <ul id="displayArea"> 
				<c:forEach items="${actionBean.errorMsg}" var="errList" varStatus="status">
					<font color="red">${errList }</font>
				</c:forEach>
				</ul> 
				 </div>
			</div>
		</div>
		
		<div style="background-color:#f9f9f9;border-bottom:1px solid #cccccc;border-left:1px solid #cccccc;border-right:1px solid #cccccc;line-height:30px;text-align:center">同方股份有限公司</div>
	</div>
</body>
</html>
