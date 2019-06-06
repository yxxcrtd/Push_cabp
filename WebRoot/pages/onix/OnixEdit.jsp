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
							<th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.NodePath"/></th>
							<td width="60%" align="left"><input type="text" class="required" name="obj.nodePath" value="${form.obj.nodePath}" style="width: 350px;" /></td>
						</tr>
						<tr>
							<th width="20%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.ShortTag"/></th>
							<td width="60%" align="left"><input type="text" name="obj.shortTag" value="${form.obj.shortTag}" /></td>
						</tr>
						<tr>
							<th width="20%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.Cardinality"/></th>
							<td width="60%" align="left"><input type="text" name="obj.cardinality" value="${form.obj.cardinality}" /></td>
						</tr>
						<tr>
							<th width="20%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.CodeList"/></th>
							<td width="60%" align="left">
								<select name="obj.codeList">
									<option value="">选择CodeList&nbsp;</option>
									<c:forEach var="i" begin="1" end="220" step="1">
										<option value="${i}" <c:if test="${form.obj.codeList == i}">selected="selected"</c:if>>${i}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.IsNessary"/></th>
							<td width="60%" align="left">
								<input type="radio" name="obj.necessary" value="true" <c:if test="${form.obj.necessary}">checked="checked"</c:if> />必填
								<input type="radio" name="obj.necessary" value="false" <c:if test="${!form.obj.necessary}">checked="checked"</c:if> />选填
							</td>
						</tr>
						<tr>
							<th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.IsOnly"/></th>
							<td width="60%" align="left">
								<input type="radio" name="obj.only" value="true" <c:if test="${form.obj.only}">checked="checked"</c:if> />是
								<input type="radio" name="obj.only" value="false" <c:if test="${!form.obj.only}">checked="checked"</c:if> />否
							</td>
						</tr>
						<tr>
							<th width="20%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.Comment"/></th>
							<td width="60%" align="left"><textarea name="obj.comment" maxlength="512" cols="44">${form.obj.comment}</textarea></td>
						</tr>
						<tr>
							<th width="20%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.Length"/></th>
							<td width="60%" align="left"><input type="text" name="obj.length" value="${form.obj.length}" /></td>
						</tr>
						<tr>
							<th width="20%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Onix.Table.Lable.Example"/></th>
							<td width="60%" align="left"><input type="text" name="obj.example" value="${form.obj.example}" style="width: 350px;" /></td>
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
