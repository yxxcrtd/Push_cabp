<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
<head>
<!-- FtpdirConfigEdit.jsp -->
<daxtech-tag:CssTag/>
<daxtech-tag:JsTag/>
<base target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.List.Title"/></title>
<script type="text/javascript">
function apply(){
	$("#form").submit();
}
</script>
<link href="../css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form:form id="form" commandName="form" action="editSubmit">
<form:hidden path="flag" id="flag" />
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
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.Ftpname"/>：</th>
        <td width="60%" align="left">
        	<list>
	        	<form:select path="obj.ftpConfigure.id" id="fptid" data-validation-engine="validate[required]" cssStyle="width:60%" >
					<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/></form:option>
	        		<c:forEach items="${ftpmap}" var="s">
						<form:option value="${s.key}">${s.value}</form:option>
					</c:forEach>
	        	</form:select>
        	</list>
        </td>
      </tr>
     
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.PackName"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.dirName" data-validation-engine="validate[required]" id="dirName"  cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.Dir"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.ftpdir" data-validation-engine="validate[required]" id="ftpdir"  cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="20%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.Table.Lable.Description"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.description" data-validation-engine="validate[required]" id="description" cssStyle="width:60%"/>
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
