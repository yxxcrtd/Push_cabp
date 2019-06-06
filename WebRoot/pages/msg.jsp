<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Msg.Prompt.Title"/></title>
<link href="${ctx}/css/pub.css" rel="stylesheet" type="text/css" />
</head>

<body>
<c:if test="${form.isSuccess!=null&&form.isSuccess=='true'}">
	<script language="javascript">
      	var value='${form.returnValue}';
      	var val=value.split(",");
 		window.returnValue = val;
    </script>
</c:if>
<c:if test="${form.msg!=null}">
	<script language="javascript">
		alert('${form.msg}');
		window.close();
	</script>
</c:if>
<div class="debug">
${form.isSuccess }
${form.msg}<hr/>
${form.returnValue}<hr/>
</div>
</body>
</html>
