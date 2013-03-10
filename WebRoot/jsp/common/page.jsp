<%@ page language="java" pageEncoding="UTF-8"%>

<c:if test="${page.records!=0}">
共有记录&nbsp;${page.records}&nbsp;条,共&nbsp;${page.total}&nbsp;页,当前第&nbsp;${page.page}&nbsp;页
 
<a href="${ctx}${page.pageFirst}">首页</a>

<c:if test="${page.page<2}">
	上一页
</c:if>
<c:if test="${page.page>1}">
	<a href="${ctx}${page.pageBefore}">上一页</a>
</c:if>

<c:if test="${page.page==page.total}">
	下一页
</c:if>
<c:if test="${page.page<page.total}">
	<a href="${ctx}${page.pageNext}">下一页</a>
</c:if>

<a href="${ctx}${page.pageLast}">尾页</a> 

</c:if>