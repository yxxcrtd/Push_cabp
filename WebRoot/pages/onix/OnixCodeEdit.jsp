<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<base target="_self"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Edit.Title" /></title>
		<daxtech-tag:CssTag />
	</head>
	<body>
		<fieldset>
			<legend><c:choose><c:when test="${empty form.id}">添加</c:when><c:otherwise>修改</c:otherwise></c:choose></legend>
			<form id="form" commandName="form" action="save" method="post">
				<p style="margin-left:0px;">
					<span class="red">*</span>
					<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Must" />
				</p>
				<div class="book-module">
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="tab02">
						<tr>
							<th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.CodeList"/></th>
							<td width="60%" align="left">
								<select name="obj.codeList">
									<c:forEach var="i" begin="1" end="220" step="1">
										<option value="${i}" <c:if test="${form.obj.codeList == i}">selected="selected"</c:if>>${i}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.OnixCode.Table.Lable.CodeValue"/></th>
							<td width="60%" align="left"><input type="text" class="required" name="obj.codeValue" value="${form.obj.codeValue}" style="width: 350px;" /></td>
						</tr>
						<tr>
							<th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.OnixCode.Table.Lable.Description"/></th>
							<td width="60%" align="left"><input type="text" class="required" name="obj.description" value="${form.obj.description}" style="width: 350px;" /></td>
						</tr>
						<tr>
							<th width="20%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.OnixCode.Table.Lable.Notes"/></th>
							<td width="60%" align="left"><textarea name="obj.notes" maxlength="512" cols="44">${form.obj.notes}</textarea></td>
						</tr>
						<tr>
							<th width="20%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.OnixCode.Table.Lable.Comment"/></th>
							<td width="60%" align="left"><textarea name="obj.comment" maxlength="512" cols="44">${form.obj.comment}</textarea></td>
						</tr>
					</table>
				</div>
				<div align="center" class="mtp10">
					<input type="button" name="save" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Save"/>" class="devil_button" onclick="apply();"/>
					<input type="button" name="close" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Close"/>" class="devil_button" onclick="window.close();"/>
				</div>
				<div class="mtp10"></div>
				<input type="hidden" name="id" value="${form.id}" />
			</form>
		</fieldset>
		<daxtech-tag:JsTag />
		<script type="text/javascript" src="${ctx}/script/jquery.validate.js"></script>
		<script type="text/javascript">
		<!--
		$(function() {
			$("#form").validate();
		});
		function apply() {
			if ("" == $("#objflag").val()) {
				$("#objflag").val($("#flag").val());
			} else {
				$("#flag").val($("#objflag").val());
			}
			$("#form").submit();
		}
		//-->
		</script>
	</body>
</html>
