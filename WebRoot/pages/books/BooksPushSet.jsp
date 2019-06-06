<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<base target="_self"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.List.Title" /></title>
		<daxtech-tag:CssTag />
		<script>
		//Jquery Ajax传值联动获取目标模版
		var sss = '<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.Select.TargetFTPDIR"/>';
	    function getFtpdirID(obj) {
	        $.ajax({
	            url:"<%=request.getContextPath()%>/pages/books/form/getFtpdir",
	            dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
	            type:"post", //传参方式，get 或post
	            data:{
	            	ftpId: obj.value
	            },
	            success: function(text) {
	            	$("#ftpdirId").empty();
	            	$("#ftpdirId").append("<option value=''>"+ sss +"</option>");
	            	for(var i in text) {
	            		$("#ftpdirId").append("<option value='"+ text[i].id +"'>"+ text[i].dirName +"</option>");
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
			<legend><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.SelectFTP"/></legend>
			<form:form id="form" commandName="form" action="booksSet" method="post">
				<p style="margin-left:20px;">
					<span class="red">*</span>
					<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Must" />
				</p>
				<div class="book-module">
				<!-- 数据调用选取 -->
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="tab02">
                        <tr>
                            <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.TargetFTP"/>：</th>
                            <td width="70%" align="left">
                                <!-- 获取目标Ftp -->
	   	                        <form:select path="ftpId" id="ftpId" onchange="getFtpdirID(this)" data-validation-engine="validate[required]">
									<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.Select.TargetFTP"/></form:option>
									<c:forEach items="${ftpConfigureList}" var="ftp">
				    					<form:option value="${ftp.id}" id="${ftp.id}">${ftp.name}</form:option>
							   		</c:forEach>
								</form:select>
                            </td>
                       </tr>
                       <tr>
                            <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.TargetFTPDIR"/>：</th>
                            <td width="70%" align="left">
        	                    <!-- 获取目标FTP文件夹 -->
					            <form:select path="ftpdirId" id="ftpdirId" data-validation-engine="validate[required]">
									<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.Select.TargetFTPDIR"/></form:option>
									<c:forEach items="${ftpdirlist}" var="dir">
									    <form:option value="${dir.id}" id="${dir.id}">${dir.dirName}</form:option>
									</c:forEach>
							
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
				<input type="hidden" name="arr_select" value="${form.arr_select}" />
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