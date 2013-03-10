<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<script src="${ctx}/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/jquery-ui-1.8.2.custom.min.js" type="text/javascript"></script>
<script src="${ctx}/js/ui/jquery-ui-i18n.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jqgrid/js/jquery.jqgridOfReport.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/ui.jqgrid.css" />
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script>
	var jsCtx = '${ctx}';
</script>