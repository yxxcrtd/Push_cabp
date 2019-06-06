<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.SelectFTP" /></title>
		<daxtech-tag:CssTag />
	</head>
	
	<body>
		<p>&nbsp;</p>
		<c:if test="${form.msg != null && form.msg != ''}">
			<script language="javascript">
			<!--
				alert('${form.msg}');
			//-->
			</script>
		</c:if>
		<p>&nbsp;</p>
		<fieldset>
			<legend><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.SelectFTP" /></legend>
			<form id="form" commandName="form" action="save" method="post" name="pushSetForm">
				<div class="book-module">
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="tab02">
                        <tr>
                            <th width="45%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.Select.TargetTemplate" />：</th>
                            <td width="55%" align="left">
	                            <list>
	        	                    <select name="userTemplateId" id="utid" data-validation-engine="validate[required]">
										<option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.Select.TargetTemplate" /></option>
										<c:forEach items="${targetTemplateList}" var="t">
										    <option value="${t.targetTemplateId.id}" onclick="changeTargateTemptName('${t.targetTemplateId.name}');">${t.targetTemplateId.name}</option>
										</c:forEach>
									</select>
									<input type="hidden" name="targetTemplateName" id="targetTemplateName"/>
								</list>
                            </td>
                       </tr>
                       <tr>
                            <th width="45%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.Select.TargetFTP.DIR" />：</th>
                            <td width="55%" align="left">
	                            <list>
						        	<select name="targetTemplateId" id="ttid" data-validation-engine="validate[required]" onchange="change(this);">
										<option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.Select.TargetFTP" /></option>
						        		<c:forEach items="${ftpmap}" var="s">
											<option value="${s.key}">${s.value}</option>
										</c:forEach>
						        	</select>
						        	<select name="ftpdirid" id="targetId">
						        		<option value=''><ingenta-tag:LanguageTag sessionKey='lang' key='Pages.Push.Table.Lable.Select.TargetFTPDIR' /></option>
						        	</select>
									<span id="target_loading" style="display: none;">
										<img src="<%=request.getContextPath()%>/images/Loading.gif" align="absmiddle" />
									</span>									
								</list>
                            </td>
                       </tr>      
					</table>
				</div>
				<div align="center" class="mtp10">
					<input type="button" name="save" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Save"/>" class="devil_button" onclick="apply();"/>
					<input type="button" name="close" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Close"/>" class="devil_button" onclick="window.close();"/>
				</div>
				<div class="mtp10"></div>
				<input type="hidden" name="id" value="${form.id}" />
				<input type="hidden" name="utId" value="${utId}" />
				<input type="hidden" name="tsourceId" value="${tsourceId}" />
				<input type="hidden" name="type" value="${type}" />
			</form>
		</fieldset>
		<daxtech-tag:JsTag />
		<%@ include file="/common/newformValidate.jsp"%>
		<script type="text/javascript">
		<!--
		var ftpdir_select = document.pushSetForm.ftpdirid;
		$(function() {
			$("#form").validate();
		});
		
		// 根据FTP的ID获取FTP下的文件夹列表
		function change(select) {
			var ftpId = select.options[select.selectedIndex].value;
			if (null == ftpId || "" == ftpId || 0 == ftpId) {
				clearTarget(ftpdir_select);
				setDefault(ftpdir_select, '', '<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.TargetTemplate" />');
				return;
			}
			ftpdir_select.disabled = true;
			$("#target_loading").css("display", "");
			$.getJSON("getFtpDir", { "ftpId" : ftpId }, function(data) {
				$.each(data, function(i, n) {
					$("#targetId").append("<option value='" + n.id + "'>" + n.dirName + "</option>");
				});
				$("#target_loading").css("display", "none");
				ftpdir_select.disabled = false;
			});
		}
		// 将目标置为0
		function clearTarget(select) {
			select.options.length = 0;
		}
		// 重置目标下拉项
		function setDefault(select, value, text) {
		    if ("" == value) {
		    	select.options[select.options.length] = new Option(text, value, true, true);
		    } else {
		    	select.options[select.options.length] = new Option(text, value);
		    }
		}
		// 确定
		function apply() {
			// 表单提交
			$("#form").submit();
		}
		function changeTargateTemptName(targateTemplateName){
			$("#targetTemplateName").val(targateTemplateName);
		}
		//-->
		</script>
	</body>
</html>