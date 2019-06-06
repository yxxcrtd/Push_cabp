<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!-- FtpConfigEdt.jsp -->
<daxtech-tag:CssTag/>
<daxtech-tag:JsTag/>
<base target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.List.Title"/></title>
<script type="text/javascript">
	function apply(){
		if($("#objflag").val()==""){
			$("#objflag").val($("#flag").val());
		}else{
			$("#flag").val($("#objflag").val());
		}
		$("#form").submit();
	}
</script>
</head>
<body>
<form:form id="form" commandName="form" action="editSubmit">
<form:hidden path="flag" id="flag" />
<form:hidden path="obj.flag" id="objflag" />
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
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.Ident"/>：</th>
        <td width="60%" align="left">
	       	<form:input path="obj.code" data-validation-engine="validate[required,custom[onlyLetterSp]]" id="code" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.Name"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.name" data-validation-engine="validate[required]" id="name" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.IP"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.ip" data-validation-engine="validate[required,custom[ipv4]]" id="ip" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.Port"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.port" data-validation-engine="validate[required],custom[onlyNumberSp],max[65535]" id="port" cssStyle="width:60%"></form:input>
        </td>
      </tr>
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.User.Name"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.username" data-validation-engine="validate[required]" id="username" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="15%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.Password"/>：</th>
        <td width="85%" align="left">
        	<form:input path="obj.password" data-validation-engine="validate[required]" id="password" cssStyle="width:60%"/>
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
</body>
</html>
<%@ include file="/common/newformValidate.jsp"%>
