<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!-- userTemplateEdit.jsp -->
<daxtech-tag:CssTag/>
<daxtech-tag:JsTag/>
<base target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.List.Title"/></title>
<script type="text/javascript">
function apply(){
	$("#form").submit();
}

function change(){
	var flag = $("input[name='obj.flag']:checked").val();
	//0 代表用户模板 1代表目标模板 目标模板不绑定ftp服务器
	if(flag==1){
		$("#ftptrid").hide();
	}else{
		$("#ftptrid").show();
	}
}

</script>
</head>
<body>
<fieldset>
<form:form id="form" commandName="form" action="editSubmit" enctype="multipart/form-data">
<div class="bodyDiv" >
    <div class="bodyBg">
    <c:if test="${form.id==null||form.id=='0'||form.id==''}">
    	<h3><ingenta-tag:LanguageTag sessionKey="lang" key="message_zh_CN.properties"/></h3>
    </c:if>
    <c:if test="${form.id!=null&&form.id!='0'&&form.id!=''}">
    	<h3><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Edit.Header.Modify"/></h3>
    </c:if>
    <div>
    <p style="margin-left:0px;"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Must"/></p>
    <div class="book-module">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="tab02">
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Category"/>：</th>
      	<td>
        	 <c:if test="${form.obj.flag==0}">
	        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.UserTemplate"/>
	        	</c:if>
        	<c:if test="${form.obj.flag==1}">
        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.TargetTemplate"/>
        	</c:if>
      	</td>
      </tr>
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.Name"/>：</th>
        <td width="60%" align="left">
       		<form:input path="obj.name" id="name" data-validation-engine="validate[required]"/>
        </td>
      </tr>
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Type"/>：</th>
        <td width="60%" align="left">
        	 <c:if test="${form.obj.type==0}">
        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.content.type.EBook"/>
	        	</c:if>
	        	<c:if test="${form.obj.type==1}">
	        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.content.type.Periodical"/>
        	</c:if>
        </td>
      </tr>
       <c:if test="${form.obj.flag==0}">
	      <tr id="ftptrid">
	        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.ServerId"/>：</th>
	        <td width="60%" align="left">
	        	<c:out value="${form.obj.ftpName}"></c:out>
	        </td>
	      </tr>
      <tr class="hiddentr">
      	<!--数据元素  -->
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.splitNode"/>：</th>
      	<td>
      		<form:input path="obj.splitNode" id="splitNode" data-validation-engine="validate[required]"/>
      	</td>
      </tr>
      <tr class="hiddentr">
      <!-- 公共元素 -->
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.commonNode"/>：</th>
        <td width="60%" align="left">
       		<form:input path="obj.commonNode" id="commonNode" data-validation-engine="validate[required]"/>
        </td>
      </tr>
      <tr class="hiddentr">
      <!-- isbn -->
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.isbn"/>：</th>
        <td width="60%" align="left">
       		<form:input path="obj.isbn" id="isbn" data-validation-engine="validate[required]"/>
        </td>
      </tr>
      <tr class="hiddentr">
      <!-- 书籍名称 -->
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.bookName"/>：</th>
        <td width="60%" align="left">
       		<form:input path="obj.bookName" id="bookName" data-validation-engine="validate[required]"/>
        </td>
      </tr>
      <tr class="hiddentr">
      <!-- 出版社 -->
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.publisher"/>：</th>
        <td width="60%" align="left">
       		<form:input path="obj.publisher" id="publisher" data-validation-engine="validate[required]"/>
        </td>
      </tr>
      <tr class="hiddentr">
      	<!-- issn/isbn 元素 -->
      	<th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.bookCodeNode"/>：</th>
        <td width="60%" align="left">
       		<form:input path="obj.bookCodeNode" id="bookCodeNode" data-validation-engine="validate[required]"/>
        </td>
      </tr>
   	  <!-- 根元素 -->
      <!-- 
      <tr class="hiddentr">
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.rootNode"/>：</th>
        <td width="60%" align="left">
       		<form:input path="obj.rootNode" id="rootNode" data-validation-engine="validate[required]"/>
        </td>
      </tr>
       -->
       <tr class="hiddentr">
      	<!--验证tld -->
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.checkTLD"/>：</th>
        <td width="60%" align="left">
       		<form:input path="obj.checkTLD" id="checkTLD" data-validation-engine="validate[required]"/>
        </td>
      </c:if>
      
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Upload"/>：</th>
        <td width="60%" align="left">
        	<c:out value="${form.obj.originalName}"></c:out>
        </td>
      </tr>
    </table>
    </div>
    <div align="center" class="mtp10">
    	<input type="button" name="save" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Save"/>" class="devil_button" onclick="apply()"/>
    	<input type="button" name="close" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Close"/>" class="devil_button" onclick="window.close();"/>
	</div>
    <div class="mtp10"></div>
    </div>
  </div>    
</div>
	<form:hidden path="id"/>
	<form:hidden path="obj.flag"/>
</form:form>
</fieldset>
<%@ include file="/common/newformValidate.jsp"%>
</body>
</html>
