<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.List.Title" /></title>
		<daxtech-tag:CssTag />
		<daxtech-tag:JsTag />
	</head>

	<body>
		<c:if test="${form.msg != null && form.msg != ''}">
			<script language="javascript">
				alert('${form.msg}');
			</script>
		</c:if>
		<form action="manager" method="post" commandName="form" id="form">
			<div class="bodyDiv bodyNew" style="position: relative;">
				<div class="pos">
					<div class="book-module" style="margin:5px 0px;">
						<table width="99%" border="0" cellpadding="0" cellspacing="1" class="tab02">
							<tr>
								<td width="10%" align="right"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.FileName" />：</th>
								<td width="45%"><input type="text" style="width: 98%;" name="fileName" value="${form.fileName}" /></td>
								<td width="10%" align="center"><input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry" />" class="devil_button" onclick="query();"/></td>
							</tr>
						</table>
					</div>
					<div class="bodyBg">
						<div class="module">
							<table width="100%" border="0" cellpadding="0" cellspacing="1" class="devil_table">
								<thead>
									<tr>
										<th width="10%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.SerialNumber"/></th>
										<th width="60%" align="left"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.FileName" /></th>
										<th width="30%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.TargetFTP" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pushList}" var="p" varStatus="index">
										<tr class="<c:if test="${index.index%2 == 1}">odd</c:if><c:if test="${index.index%2 == 0}">eve</c:if>">
											<td align="center">${(form.curpage*form.pageCount)+index.index+1}</td>
											<td align="left">${p.fileName}</td>
											<td align="center">
												<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.SelectFTP" />" class="devil_button" onclick="selectFTP('${p.ftpcode}', '${p.id}','${p.type}');"/>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<table width="95%" border="0" cellpadding="0" cellspacing="0">
								<tr height="40">
									<td height="25">
										<ingenta-tag:SplitTag page="${form.curpage}" pageCount="${form.pageCount}" count="${form.count}" formName="form" showNum="10" i18n="${sessionScope.lang}" />
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="" />
		</form>
		<script type="text/javascript">
		// 弹出修改窗口
		function selectFTP(code, id,type) {
			var url = "<%=request.getContextPath()%>/pages/push/form/set?code=" + code + "&id=" + id+"&type="+type;
			var top = document.body.offsetHeigth / 2;
			var left = document.body.offsetWIdth / 2;
			var width = "630px";
			var height = "200px";
			openWindow(url, top, left, width, height);
		}
		function query() {
			document.getElementById("page").value = "0";
			document.getElementById("form").submit();
		}
		// 打开模态窗口
		function openWindow(url, top, left, width, height) {
			var r = "";
			if (url.indexOf("?") != -1) {
				url = url + "&r_=" + Math.random();
			} else {
				url = url+ "?r_" + Math.random();
			}
			var returnval = window.showModalDialog(url, window, "dialogTop:" + top + ";dialogLeft:" + left + ";dialogWidth=" + width + ";dialogHeight=" + height);
			// 通过返回值判断  如果是undefined 说明是 关闭功能 则不执行查询列表功能
			if (returnval == undefined) {
				return;
			}
			
			if (document.getElementById("txtPage")) {
				document.getElementById("page").value = (document.getElementById("txtPage").value - 1);
			} else {
				document.getElementById("page").value = 0;
			}
			// 表单提交
			document.getElementById("form").submit();
		}
		</script>
	</body>
</html>
