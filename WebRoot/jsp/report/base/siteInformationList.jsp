<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>站内信浏览</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script src="${ctx}/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<style type="text/css">
.msgBox{font-size:13px;line-height:16px} 
#msg tr{height:22px}
.subject{text-align:center;font-size:14px;line-height:18px;font-weight:600}
.msgText{padding:0 10px 0 25px;word-wrap:break-word; word-break:break-all;overflow:hidden;}
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
<script type="text/javascript">
	$(document).ready(function(){
		if($("#table")){
			$("#table2 tr:even").addClass("treven");
			$("#table2 tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		}
		if($("#table3")){
			$("#table3 tr:even").addClass("treven");
			$("#table3 tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		}
		$("#msg tr[id!=trHead]").click(function(){
			//$("#msgBoxScoll").css("border","1px solid");
				showMeno(this,"#msgBoxScoll");	
		}).mousemove(function(){
				$(this).css("cursor","pointer");

		}).mouseout(function(){
				$(this).css("cursor","");
		});
	});
	function showMeno(obj,msgbox){
			var meno=$(obj).children("td:eq(3)").html();
			var con="<div class='subject'> "+$(obj).children("td:eq(0)").text()+"</div><br><div class='msgText'>"+meno+"</div><br>";
			if($(obj).attr("type")=="history"){$("#msgInCircleHistory").html(con);}else{$("#msgInCircleScoll").html(con);}
	}
	
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center" style="font-size:12px"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 站内信管理 &gt;&gt; 站内信查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
    <div id="msg">
      <div class="div_tiao" style="font-size:13px;width:433px;float:left;"> &nbsp;&nbsp;滚动信息 </div>
      <span class="div_tiao" style="font-size:13px;width:440px;margin-left:5px;float:left;"> &nbsp;&nbsp;信息内容 </span>
      <table width="430" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table3" style="clear:both;float:left;">
			<tr id="trHead">
				<th nowrap="nowrap" width="180" >标题</th>
				<th nowrap="nowrap" width="100" >通知时间</th>
				<th nowrap="nowrap" width="150">附件</th>
			</tr>
			<c:forEach items="${informationScollList }" var="msgList">
				<tr>
					<td index="0" title="${msgList.subject }">&nbsp;<div class="ellipsis_div" style="width:170px;">${msgList.subject }</div></td>
					<td index="1">&nbsp;${msgList.datetime }</td>
					<td index="2">&nbsp;<a href="${ctx}/data/base/SiteInformation.action?downLoad=&param.id=${msgList.id }">${msgList.filename }</a></td>
					<td style="display:none">${msgList.text}</td>
				</tr>
			</c:forEach>
		</table>&nbsp;
		<div style="width:465px;overflow:hidden;position:absolute;left:448px;top:75px">
			<div  id="msgBoxScoll" style="width:435px;margin-left:13px;overflow-y:auto" class="msgBox"> 
					<div id="msgInCircleScoll" >
					<c:if test="${!empty clcikMsg}">
						<div class='subject'>
							<c:out value="${clcikMsg.subject }"></c:out>
						</div><br>
						<div class="msgText">
							<script type="text/javascript">document.write("${clcikMsg.text }")</script>
						</div>
					</div>
					</c:if>
			</div> 
		</div>
		<br/><br/>
	 <div class="div_tiao" style="font-size:13px;clear:both;width:433px"> &nbsp;&nbsp;历史信息 </div>
   	    <table width="430" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table2" style="float:left">
			<tr id="trHead">
				<th nowrap="nowrap" width="180" >标题</th>
				<th nowrap="nowrap" width="100" >通知时间</th>
				<th nowrap="nowrap" width="150">附件</th>
			</tr>
			<c:forEach items="${informationHistoryList }" var="msgHistoryList">
				<tr>
					<td index="0">&nbsp;${msgHistoryList.subject }</td>
					<td index="1">&nbsp;${msgHistoryList.datetime }</td>
					<td index="2">&nbsp;<a href="${ctx}/data/base/SiteInformation.action?downLoad=&param.id=${msgHistoryList.id }">${msgHistoryList.filename }</a></td>
					<td style="display:none">${msgHistoryList.text}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
   	  <br/> 
    </td>
    <td >&nbsp;</td>
  </tr>
</table>
<br>
<div style="text-align:left;padding-left:40px"><%@ include file="/jsp/common/page.jsp"%></div>
</body>
</html>