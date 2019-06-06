<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Template.List.Title" /></title>
		<daxtech-tag:CssTag />
		<daxtech-tag:JsTag />
	</head>

	<body>
		<c:if test="${form.msg != null && form.msg != ''}">
			<script language="javascript">
				alert('${form.msg}');
			</script>
		</c:if>
		<form:form action="manager" method="post" commandName="form" id="form">
			<div class="bodyDiv bodyNew" style="position: relative;">
				<div class="pos">
				<!-- 条件查询部分 -->
					<div class="book-module" style="margin:5px 0px;">
					<table width="99%" border="0" cellpadding="0" cellspacing="1" class="tab02">
                        <tr>
                            <th width="10%" align="right"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.UserTemplate" />：</th>
							<td width="35%">
                            <!-- userTemplateId获取传值 -->
        	                    <form:select path="userTemplateId.id" id="userTemplateId" onchange="getID(this)">
									<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.UserTemplate"/></form:option>
									<c:forEach items="${userTemplateIdList}" var="u">
									    <form:option value="${u.userTemplateId.id}" id="${u.userTemplateId.id}">${u.userTemplateId.name}</form:option>
									</c:forEach>
								</form:select>
                            </td>
                            <th width="10%" align="right"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.TargetTemplate"/>：</th>
							<td width="35%">
                            <!-- targetTemplateId获取传值 -->
        	                    <form:select path="targetTemplateId.id" id="targetTemplateId">
									<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.TargetTemplate"/></form:option>
										<c:forEach items="${list}" var="ta">
										    <form:option value="${ta.targetTemplateId.id}" id="${ta.targetTemplateId.id}">${ta.targetTemplateId.name}</form:option>
	 									</c:forEach>

								</form:select>
                            </td>
                            <td width="15%" align="center"><input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry" />" class="devil_button" onclick="query();"/></td>
                       </tr>      
					</table>
					</div>
					<!-- 查询 -->
					<div class="bodyBg">
						<div class="module">
							<div style="margin: 5px 0;">
								<input type="button" name="add" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.New" />" class="devil_button" onclick="addObj();"/>
							</div>
					<!-- 数据显示部分 -->		
							<table width="100%" border="0" cellpadding="0" cellspacing="1" class="devil_table">
								<thead>
									<tr>
										<th width="5%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.SerialNumber"/></th>
										<th width="10%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.UserTemplate"/></th>
										<th width="10%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.TargetTemplate"/></th>
										<th width="15%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Operating"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${RtemplateList}" var="t" varStatus="index">
										<tr class="<c:if test="${index.index%2 == 1}">odd</c:if><c:if test="${index.index%2 == 0}">eve</c:if>">
											<td align="center">${(form.curpage*form.pageCount)+index.index+1}</td>
											<td align="left">${t.userTemplateId.name}</td>
											<td align="left">${t.targetTemplateId.name}</td>
											<td align="center">
												<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Modify"/>" class="devil_button" onclick="modObj('${t.id}');"/>
												<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Delete"/>" class="devil_button" onclick="delObj('${t.id}');"/>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					<!-- 分页部分 -->		
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
		</form:form>
		<!-- 动作操作部分 -->
		<script type="text/javascript">
		// 弹出新增窗口
		function addObj() {
			var url = "<%=request.getContextPath()%>/pages/rtemplate/form/edit";
			var top = document.body.offsetHeigth / 2;
			var left = document.body.offsetWIdth / 2;
			var width = "600px";
			var height = "384px";
			openWindow(url, top, left, width, height);
		}
		// 弹出修改窗口
		function modObj(id) {
			var url = "<%=request.getContextPath()%>/pages/rtemplate/form/edit?id=" + id;
			var top = document.body.offsetHeigth / 2;
			var left = document.body.offsetWIdth / 2;
			var width = "600px";
			var height = "384px";
			openWindow(url, top, left, width, height);
		}
		// 删除
		function delObj(id) {
			if (window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='RTemplate.Info.Delete.Prompt'/>")) {
				document.getElementById("form").action = "delete";
				document.getElementById("id").value = id;
				document.getElementById("page").value = document.getElementById("txtPage").value - 1;
				document.getElementById("form").submit();
			}
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
		
		//Jquery Ajax传值联动获取目标模版
		var sss = '<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.TargetTemplate"/>';
	    function getID(obj) {
	        $.ajax({
	            url:"<%=request.getContextPath()%>/pages/rtemplate/form/getTarget",
	            dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
	            type:"post", //传参方式，get 或post
	            data:{
	            	id: obj.value
	            },
	            success: function(text) {
	            	$("#targetTemplateId").empty();
	            	$("#targetTemplateId").append("<option value=''>"+ sss +"</option>");
	            	for(var i in text) {
	            		$("#targetTemplateId").append("<option value='"+ text[i].targetTemplateId.id +"'>"+ text[i].targetTemplateId.name +"</option>");
	            	}
	            }
	        });
	    }
		</script>
	</body>
</html>
