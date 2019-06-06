<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<base target="_self"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Template.Edit.Title" /></title>
		<daxtech-tag:CssTag />
		<script>
		var sss = '<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.TargetTemplate"/>';
	    //Jquery Ajax传值联动
	    function getUser(type) {
	        $.ajax({
	            url:"<%=request.getContextPath()%>/pages/rtemplate/form/getType",
	            dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
	            type:"post", //传参方式，get 或post
	            data:{type:type //传过去的参数，格式为 变量名：变量值
	                  },
	            success: function(text) {
	            	$("#targetTemplateId").empty();
	            	$("#targetTemplateId").append("<option value=''>"+ sss +"</option>");
	            	for(var i in text) {
	            		$("#targetTemplateId").append("<option value='"+ text[i].id +"'>"+ text[i].name +"</option>");
	            	}
	            }
	        });
	    }
		</script>
	</head>
	<body>
	<c:if test="${form.msg != null && form.msg != ''}">
		<script language="javascript">
			alert('${form.msg}');
		</script>
	</c:if>
		<fieldset>
			<legend><c:choose><c:when test="${empty form.id}">新增</c:when><c:otherwise>修改</c:otherwise></c:choose></legend>
			<form:form id="form" commandName="form" action="save" method="post" name="templateNodeMapping">
				<p style="margin-left:0px;">
					<span class="red">*</span>
					<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Must" />
				</p>
				<div class="book-module">
				<!-- 数据调用选取 -->
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="tab02">
                        <tr>
                            <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.UserTemplate"/>：</th>
                            <td width="80%" align="left">
                            <!-- userTemplateId获取传值 -->
        	                    <form:select path="obj.userTemplateId.id" id="userTemplateId" data-validation-engine="validate[required]">
									<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.UserTemplate"/></form:option>
									<c:forEach items="${userTemplateList}" var="s">
									    <form:option value="${s.id}" id="${s.id}" onclick="getUser('${s.type}')">${s.name}</form:option>
									</c:forEach>
								</form:select>
                            </td>
                       </tr>
                       <tr>
                            <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.TargetTemplate"/>：</th>
                            <td width="80%" align="left">
                            <!-- targetTemplateId获取传值 -->
        	                    <form:select path="obj.targetTemplateId.id" id="targetTemplateId" data-validation-engine="validate[required]">
									<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Select.TargetTemplate"/></form:option>
									<!-- 判断是修改还是新增 -->
									<c:if test="${targetTemplateList != null}">
										<c:forEach items="${targetTemplateList}" var="t">
										    <form:option value="${t.id}" id="${t.id}">${t.name}</form:option>
	 									</c:forEach>									
									</c:if>

								</form:select>
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
				<input type="hidden" name="id" value="${form.id}" />
			</form:form>
		</fieldset>
		<daxtech-tag:JsTag />
		<%@ include file="/common/newformValidate.jsp"%>
		<script type="text/javascript">
		function apply() {
			if ("" == $("#objflag").val()) {
				$("#objflag").val($("#flag").val());
			} else {
				$("#flag").val($("#objflag").val());
			}
			$("#form").submit();
		}
		</script>
	</body>
</html>