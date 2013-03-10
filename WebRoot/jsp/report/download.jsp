<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>下载</title>
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
	<script type="text/javascript"> 
		var intervalNum;
		var subtype = 0;

		$(document).ready(function(){
			intervalNum = window.setInterval(checkStatus,5000); 

			$("#btndownload").click(function(){ 
				  window.location ='/report/data/RealtimeDownload.action?downLoad=&nodeId=${actionBean.nodeId}';
			});

			$("#btnRetry").click(function(){ 
				$("#btnRetryTable").hide();
				$("#msgTable").text("正在发送下载请求...");
				subtype = 1;
				intervalNum = window.setInterval(checkStatus,5000); 
				
			});
		});

		//获取服务器返回结果
		function checkStatus(){
			$.ajax({
				type: "post",
				async: false,
				url: "/report/data/RealtimeDownload.action",
				data: "&checkDownLoadStatus=&nodeId=${actionBean.nodeId}&type="+subtype+"&date="+Date(),
				success: waitResult
			}); 
		} 
		
		//获取服务器返回结果
		function waitResult(msg){
			subtype = 0;
			if(msg == 0){
				window.clearInterval(intervalNum);
				$("#msgTable").text("文件已生成,点击下载！");
				$("#btnDownloadTable").show();
				$("#btnRetryTable").hide();
			}else if(msg == 1){ 
				$("#msgTable").text("文件转换中,请稍候！");
				$("#btnRetryTable").hide();
			}else if(msg == 2){
				$("#msgTable").text("系统繁忙,点击重试！");

				$("#btnRetryTable").show();
				window.clearInterval(intervalNum);
			}else if(msg == 8){  
				$("#msgTable").text("没有数据,无法下载！");
				window.clearInterval(intervalNum);
			}else if(msg == 9){  
				$("#msgTable").text("权限不足,无法下载！");
				window.clearInterval(intervalNum);
			}else{ 
				$("#msgTable").text("发生错误,无法下载！");
				
				$("#btnRetryTable").show();
				window.clearInterval(intervalNum);
			}
		} 
		
	</script>
</head>
<body>
<form>
<div class="ffnn-corner">
	<div style="background-image:url(${ctx}/images/wrong_tou.jpg); background-repeat:no-repeat; width:401px; height:32px"></div>
	<div style="border:1px solid #cccccc;height:110px"><br/>
		<div style="float:left;padding:2px 0 0 20px"><img src="${ctx}/images/wrong_1.jpg" width="37" height="37" /></div>
		<tt id="msgTable" style="font-size:13px;margin:0px 0px 0px 40px;font-weight:bold;float:left">正在发送下载请求...</tt>
			<div id="btnDownloadTable" style="display: none;margin-top:-2px;margin-left:260px"><img src="${ctx}/images/btnXiaZai.gif" id="btndownload"/></div>
			<div id="btnRetryTable" style="display: none;margin-top:-2px;margin-left:260px"><img src="${ctx}/images/btnChongShi.gif" id="btnRetry"/></div>
		<div style="line-height:20px;">
			<div style="clear:both;text-align:center;margin-top:25px">请不要使用下载工具下载,不要过于频繁点击下载!</div>
			<div style="text-align:center">下载中发生错误或不能下载,请联系管理员!</div>
		</div>
	</div>
</div>
</body>
</html>