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
		$(".hiddentr").hide();
		//$("#splitNodetr").hide();
	}else{
		$(".hiddentr").show();
		//$("#splitNodetr").show();
	}
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
      		<form:radiobuttons path="obj.flag" items="${form.templateCategoryMap}" name="radiuFlag" onclick="change();" delimiter="&nbsp;"/> 
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
        	<form:select path="obj.type" data-validation-engine="validate[required]">
        	<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/></form:option>
				<form:options items="${form.templateTypeMap}" />
			</form:select>
        </td>
      </tr>
        <tr class="hiddentr">
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.ServerId"/>：</th>
       	 <td width="60%" align="left">
	       		<list>
		        	<form:select path="ftpid" id="ftpid" data-validation-engine="validate[required]">
						<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/></form:option>
		        		<c:forEach items="${ftpmap}" var="s">
							<form:option value="${s.key}">${s.value}</form:option>
						</c:forEach>
		        	</form:select>
	        	</list>
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
       		<form:input path="obj.commonNode" id="commonNode" />
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
      </tr>
      
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Upload"/>：</th>
        <td width="60%" align="left">
        	<input type="file" name="upFile" id="upFile" data-validation-engine="validate[required]"/>
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
</form:form>
</fieldset>
<%@ include file="/common/newformValidate.jsp"%>
</body>
</html>
