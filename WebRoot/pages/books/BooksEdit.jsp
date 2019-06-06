<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<base target="_self"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.List.Title" /></title>
		<daxtech-tag:CssTag />
	</head>
	<body>
	<c:if test="${form.msg != null && form.msg != ''}">
		<script language="javascript">
			alert('${form.msg}');
		</script>
	</c:if>
		<fieldset>
			<legend><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.shangchuan.excel"/></legend>
			<form:form id="form" commandName="form" action="save" method="post" enctype="multipart/form-data">
				<p style="margin-left:0px;">
					<span class="red">*</span>
					<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Must" />
				</p>
				<div class="book-module">
				<!-- 数据调用选取 -->
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="tab02">
                        <tr>
                            <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.shangchuan.excel"/>：</th>
                            <td width="80%" align="left">
        	                   <form:input type="file" path="txtFile" id="txtFile" data-validation-engine="validate[required],custom[exclfile]" /> 
                            </td>
                       </tr>      
					</table>
				</div>
				<!-- 动作操作 -->
				<div align="center" class="mtp10">
					<input type="button" name="save" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Save"/>" class="devil_button" onclick="apply();"/>
					<input type="button" name="close" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Close"/>" class="devil_button" onclick="window.close();"/>
				</div>
				<div class="mtp10"></div>
			</form:form>
		</fieldset>
		<daxtech-tag:JsTag />
		<%@ include file="/common/newformValidate.jsp"%>
		<script type="text/javascript">
		function apply() {
			$("#form").submit();
		}
		</script>
	</body>
</html>