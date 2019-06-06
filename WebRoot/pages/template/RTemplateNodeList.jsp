<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.List.Title" /></title>
		<daxtech-tag:CssTag />
		<daxtech-tag:JsTag />
	</head>

	<body>
		<!-- 在列表中弹出操作后的提示信息 -->
		<c:if test="${form.msg != null && form.msg != ''}">
			<script language="javascript">
				alert('${form.msg}');
			</script>
		</c:if>

		<form:form action="manager" method="post" commandName="form" id="form" name="templateNodeMapping">
			<div class="bodyDiv bodyNew" style="position: relative;">
				<div class="pos">
					<!-- 下拉查询条件 -->
					<div class="book-module" style="margin:5px 0px;">
						<table width="99%" border="0" cellpadding="0" cellspacing="1" class="tab02">
							<tr>
								<th width="10%" align="right"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.UserTemplate" />：</th>
								<td width="35%">
									<select name="userTemplateId.id">
										<option value="" style="width: 280px;"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.UserTemplate" /></option>
										<c:forEach items="${userTemplateIdList}" var="s" varStatus="index">
											<option value="${s.userTemplateId.id}" onclick="getTargateTemplate('${s.userTemplateId.id}')" <c:if test="${form.userTemplateId.id == s.userTemplateId.id}">selected="selected"</c:if>>${s.userTemplateId.name}</option>
										</c:forEach>
									</select>
								</td>
								<th width="10%" align="right"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.TargetTemplate"/>：</th>
								<td width="35%">
									<select name="targetTemplateId.id" id="targetTemplateId">
										<option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.TargetTemplate" /></option>
										<c:forEach items="${targetTemplateList}" var="t" varStatus="index">
											<option value="${t.targetTemplateId.id}" <c:if test="${form.targetTemplateId.id == t.targetTemplateId.id}">selected="selected"</c:if>>${t.targetTemplateId.name}</option>
										</c:forEach>
									</select>
									<span id="target_loading" style="display: none;">
										<img src="<%=request.getContextPath()%>/images/Loading.gif" align="absmiddle" />
									</span>
								</td>
								<td width="10%" align="center"><input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry" />" class="devil_button" onclick="query();"/></td>
							</tr>
						</table>
					</div>
					
					<!-- ${fn:length(targetTemplateNodeList)} -->
					
					<!-- 数据展示部分 -->
					<div class="bodyBg">
						<div class="module">
							<table width="100%" border="0" cellpadding="0" cellspacing="1" class="devil_table">
								<thead>
									<tr>
										<th width="6%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.SerialNumber"/></th>
										<th width="42%" align="left"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.UserTemplateNode"/></th>
										<th width="52%" align="left"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.TargetTemplateNode"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${targetTemplateNodeList}" var="o" varStatus="index">
										<tr class="<c:if test="${index.index%2 == 1}">odd</c:if><c:if test="${index.index%2 == 0}">eve</c:if>">
											<td align="center">${(form.curpage*form.pageCount)+index.index+1}</td>
											<td align="left">
												<input type="hidden" class="tnv" value="${o.id}" />
												<select class="sel">
													<option value="" style="width: 300px;"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.UserTemplateNode" /></option>
													<c:forEach items="${userTemplateNodeList}" var="s" varStatus="index">
														<option value="${s.id}" <c:if test="${o.id == map[s.id]}">selected="selected"</c:if>>${s.nodePath}</option>
													</c:forEach>
												</select>
											</td>
											<td align="left">${o.nodePath}</td>
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
					<!-- 数据展示部分 -->
				</div>
			</div>
		</form:form>
		<script type="text/javascript">
		<!--
		var target_select = document.templateNodeMapping.targetTemplateId;
		var selectMsg = '<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.TargetTemplate"/>';
		$(function() {
			// 根据选择去映射
	 		$(".sel").live("change", function() {
	 			var This = this;
	 			var cur = $(This).siblings(".tnv").val();
	 			var chk = $(This).children("option:selected").val();
	 			$.getJSON("save.json", {"targetTemplateId" : "${form.targetTemplateId.id}", "userTemplateId" : "${form.userTemplateId.id}", "targetTemplateNodeId" : cur, "userTemplateNodeId" : chk}, function(data) {
	 				if ("success" == data) {
	 					
	 				} else {
	 					alert("因未知原因无法映射，请稍后重试！");
	 				}
	 			});
	 		});
		});
		
	    function getTargateTemplate(userTemplateId) {
	        $.ajax({
	            url:"<%=request.getContextPath()%>/pages/rtemplateNode/form/getTargetList",
	            dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
	            type:"post", //传参方式，get 或post
	            data:{userTemplateId:userTemplateId //传过去的参数，格式为 变量名：变量值
	                  },
	            success: function(data) {
					
	            	$("#targetTemplateId").empty();
	            	$("#targetTemplateId").append("<option value=''>"+ selectMsg +"</option>");
	            	for(var i in data) {
	            		var targetTemplate = data[i].targetTemplateId;
	            		$("#targetTemplateId").append("<option value='"+ targetTemplate.id +"'>"+ targetTemplate.name +"</option>");
	            	}
	            },
	            error: function() {     
	            	alert("异常！");     
	            }     

	        });
	    }
		
// 		根据选择不同的用户模板去加载相应的目标模板
		function change(select) {
			var id = select.options[select.selectedIndex].value;
			if (null == select.value || "" == select.value || 0 == select.value) {
				clearTarget(target_select);
				setDefault(target_select, '', '<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.TargetTemplate" />');
				return;
			}
			clearTarget(target_select);
			target_select.disabled = true;
			$("#target_loading").css("display", "");
			$.getJSON("getTargetList", { "id" : select.value }, function(data) {
				if ("success" == data.result) {
					$("#targetTemplateId").append("<option value=''><ingenta-tag:LanguageTag sessionKey='lang' key='Pages.UserTemplateMapping.Table.Select.TargetTemplate' /></option>");
					$.each(data.list, function(i, n) {
						$("#targetTemplateId").append("<option value='" + n.targetTemplateId.id + "'>" + n.targetTemplateId.name + "</option>");
					});
					$("#target_loading").css("display", "none");
					target_select.disabled = false;
				} else {
					alert("因未知原因无法获取数据，请稍后重试！");
					target_select.disabled = true;
				}
			});
		}
// 		将目标置为0
		function clearTarget(select) {
			select.options.length = 0;
		}
// 		重置目标下拉项
		function setDefault(select, value, text) {
		    if ("" == value) {
		    	select.options[select.options.length] = new Option(text, value, true, true);
		    } else {
		    	select.options[select.options.length] = new Option(text, value);
		    }
		}
		
// 		查询
		function query() {
			document.getElementById("page").value = "0";
			document.getElementById("form").submit();
		}

		//-->
		</script>
	</body>
</html>
