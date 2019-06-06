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
		$("#form").submit();
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
    	<h3><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.xiugai.xinxi"/></h3>
    <div>
    <p style="margin-left:0px;"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Must"/></p>
    <div class="book-module">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="tab02">
      <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.order.bianhao"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.code"  id="code" cssStyle="width:60%"  data-validation-engine="validate[required]" />
        </td>
      </tr>
      <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.order.name"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.name"  id="name" cssStyle="width:60%"  data-validation-engine="validate[required]" />
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.jiesuan.price"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.settledPrice"  id="ip" cssStyle="width:60%" data-validation-engine="validate[custom[zhengshuo]]"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.order.shuliang"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.quantityStr"  id="quantityStr" cssStyle="width:60%" data-validation-engine="validate[required,custom[onlyNumber]]" />
        </td>
      </tr>
      <%-- <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.chuangjian.ren"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.createdby"  id="username" cssStyle="width:60%"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.genxin.ren"/> ：</th>
        <td width="60%" align="left">
        	<form:input path="obj.updatedby"  id="updatedby" cssStyle="width:60%" />
        </td>
      </tr> --%>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.xiaoshou.price"/> ：</th>
        <td width="60%" align="left">
        	<form:input path="obj.salePrice"  id="salePrice" cssStyle="width:60%" data-validation-engine="validate[custom[zhengshuo]]"/>
        </td>
      </tr>
     <%--  <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.CreateTime"/> ：</th>
        <td width="60%" align="left">
        	<form:input path="obj.createdonStr"  id="createdonStr" cssStyle="width:60%" data-validation-engine="validate[shijian]"/>
        </td>
      </tr> --%>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.goumairen.photo"/> ：</th>
        <td width="60%" align="left">
        	<form:input path="obj.purchaserPhone"  id="purchaserPhone" cssStyle="width:60%" />
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.lianxi.ren"/> ：</th>
        <td width="60%" align="left">
        	<form:input path="obj.contacts"  id="contacts" cssStyle="width:60%" />
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.lianxi.photo"/> ：</th>
        <td width="60%" align="left">
        	<form:input path="obj.phone"  id="phone" cssStyle="width:60%" />
        </td>
      </tr>
      <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.lianxi.email"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.email"  id="email" cssStyle="width:60%" data-validation-engine="validate[required]"/>
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.zhifu.leixing"/> ：</th>
        <td width="60%" align="left">
        	<form:input path="obj.paymentMethod"  id="password" cssStyle="width:60%" />
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.order.goumaiIp"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.ip"  id="ip" cssStyle="width:60%" />
        </td>
      </tr>
      <tr>
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Content.Table.Lable.Status"/>:&nbsp;</th>
         <td width="18%">
         <form:select path="obj.statuStr">
		 <form:option value="1" ></span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.dai.chuli"/></form:option>
		 <form:option value="2" ></span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.order.chulizhong"/></form:option>
		 <form:option value="3" ></span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.chuli.wencheng"/></form:option>
		 </form:select>
         </td>
      </tr>
      <%-- <tr>
        <th width="30%"><span class="red">*</span><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.gengxin.date"/> ：</th>
        <td width="60%" align="left">
        	<form:input path="obj.updatedonStr"  id="updatedonStr" cssStyle="width:60%" />
        </td>
      </tr> --%>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.goumai.ren"/> ：</th>
        <td width="60%" align="left">
        	<form:input path="obj.purchaser"  id="purchaser" cssStyle="width:60%" />
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.gouren.dizhi"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.purchaserAddress"  id="purchaserAddress" cssStyle="width:60%" />
        </td>
      </tr>
      <tr>
        <th width="30%"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.lianxiren.dizhi"/>：</th>
        <td width="60%" align="left">
        	<form:input path="obj.address"  id="address" cssStyle="width:60%" />
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
