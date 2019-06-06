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
		if ($("#orderNo").val() == "") {
			alert("<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.zhuang.hao"/>");
			return false;
		}else if ($("#title").val() == "") {
			alert("<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.tushu.name"/>！");
			return false;
		}else if ($("#isbn").val() == "") {
			alert("<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.book.isbn"/>");
			return false;
		}else if ($("#publisher").val() == "") {
			alert("<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.tushu.publisher"/>");
			return false;
		}
		else{
			$("#form").submit();
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
<form:form id="form" commandName="form" action="updateSubmit" enctype="multipart/form-data">

<div class="bodyDiv" >
    <div class="bodyBg">
    	<h3>修改信息</h3>
    <div>
    <p style="margin-left:0px;"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Must"/></p>
    <div class="book-module">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="tab02">
      <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.info.Msg"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.orderNo"  id="orderNo" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.biaoti"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.title"  id="title" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.isbn"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.isbn"  id="isbn" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.chubanshe"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.publisher"  id="publisher" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.huiliandz"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.url"  id="url" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.author"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.author"  id="author" cssStyle="width:60%"/>
        </td>
      </tr>
     <%--  <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.chuban.date"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.publishDateStr"  id="publishDateStr" cssStyle="width:60%"/>
        </td>
      </tr> --%>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.dingjia"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.price"  id="price" cssStyle="width:60%" data-validation-engine="validate[custom[zhengshuo]]" />
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.online.price"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.onPrice"  id="onPrice" cssStyle="width:60%"  data-validation-engine="validate[custom[zhengshuo]]"  />
        </td>
      </tr>
       <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.lixian.price"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.offPrice"  id="offPrice" cssStyle="width:60%" data-validation-engine="validate[custom[zhengshuo]]" />
        </td>
      </tr>
       <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.banci"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.edition"  id="edition" cssStyle="width:60%"/>
        </td>
      </tr>
       
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.zhuangzhen"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.frame"  id="frame" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.kaiben"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.format"  id="format" cssStyle="width:60%"/>
        </td>
      </tr>
       <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.leibie"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.category"  id="category" cssStyle="width:60%"/>
        </td>
      </tr>
       <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.chuban.dizhi"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.location"  id="location" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.yeshu"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.pageStr"  id="pageStr" cssStyle="width:60%" data-validation-engine="validate[custom[onlyNumber]]" />
        </td>
      </tr>
      
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.yinzhang"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.sheet"  id="sheet" cssStyle="width:60%" data-validation-engine="validate[custom[zhengshuo]]" />
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.fenmian"/>：</th>
        <td width="60%" align="left">
        	<c:if test="${form.obj.cover!=null}">
        	<img  src="/upload/${form.obj.cover}" id="cover"   cssStyle="width:30%" width="100" />
        	</c:if>
        	<input type="file" id="upLoad" class="span6" name="upLoadPhoto" onblur="WordInfo();"/>
        	<form:hidden path="obj.cover"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.jianjie"/>：</th>
        <td width="60%" align="left">
        	<form:textarea path="obj.introduction" id="introduction" cssStyle="width:60%"/>
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
	<form:hidden path="obj.id"/>
</form:form>
</body>
</html>
<%@ include file="/common/newformValidate.jsp"%>
