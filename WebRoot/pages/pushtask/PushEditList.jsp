<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!-- FtpConfigList.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.List.Title"/></title>
<daxtech-tag:CssTag/>
<daxtech-tag:JsTag/>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript">
		window.onload=  function(){
			
		};
		function query(){
			document.getElementById("page").value="0";
			document.getElementById("form").submit();
		}
</script>
</head>
<body>
<form:form action="states" method="post" commandName="form" id="form">
<!-- 0代表自己的配置  1代表目标配置 -->
<c:if test="${form.msg!=null&&form.msg != ''}">
<script language="javascript">
		alert('${form.msg}');
	</script>
</c:if>
<div class="bodyDiv bodyNew" style="position:relative;">
	<div class="pos">
	
	<div class="book-module" style="margin:5px 0px;">
	<table width="95%" border="0" cellpadding="0" cellspacing="1" class="tab02">
       <tr>
       	 <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.user.wenjianming"/>：</th>
         <td width="18%">
         	<form:input path="fileName" cssStyle="width:22%" id="fileName"/>
         </td>
         
          <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.ftp.wenjiamolu"/>：</th>
         <td  width="18%">
         	<form:input path="ftpFileDir" cssStyle="width:22%" id="ftpFileDir"/>
         </td>
        
         </tr>
         
         <tr>
          <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.kaishi"/>：</th>
         <td width="18%">
          <form:input id="ftpsDateon" type="text" path="ftpsDateon"/> 
     		<img onclick="WdatePicker({el:$dp.$('ftpsDateon')})" src="${ctx}/My97DatePicker/skin/datePicker.gif" _fcksavedurl="${ctx}/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"> 
         </td>
         
         
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.jianshu"/>：</th>
         <td width="18%">
         	<form:input id="ftpsDateto" type="text"  path="ftpsDateto"/> 
     		<img onclick="WdatePicker({el:$dp.$('ftpsDateto')})" src="${ctx}/My97DatePicker/skin/datePicker.gif" _fcksavedurl="${ctx}/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"> 
         
         </td>
       	
       </tr>
       
      
    </table>
    </div>
	
<div class="bodyBg">
	<div class="module">
	   <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry"/>" class="devil_button" onclick="query()"/>
  <div style="margin:5px 0px;">
  </div>
     	<table width="95%" border="0" cellpadding="0" cellspacing="1" class="devil_table">
     		<thead>
     		<tr>
		        <th width="8%" align="center">
		        	<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.SerialNumber"/>
		        </th>
		        <th width="6%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.user.wenjianming"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.ftp.wenjiamolu"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.ftpxia.kaishidian"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.ftpxia.jieshudian"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.chaifen.ksdian"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.chaifen.jieshudian"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.zuizhong.zhuangt"/></th>
		        
		    </tr>
		    </thead>
		    <tbody>
		   	<c:forEach items="${list}" var="p" varStatus="index">
		   	   <tr  class="<c:if test="${index.index%2 == 1}">odd</c:if><c:if test="${index.index%2 == 0}">eve</c:if>">
		   	   	<td align="center">${(form.curpage*form.pageCount)+index.index+1}</td>
		        <td align="center">${p.fileName}</td>
		        <td align="center">${p.ftpFileDir}</td>
		        <td align="center">${p.ftpsDate}</td>
		        <td align="center">${p.ftpeDate}</td>
		        <td align="center">${p.caisDate}</td>
		        <td align="center">${p.caieDate}</td>
		        <td align="center">
		        <c:if test="${p.puSta == '1'}"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.yi.jiexi"/></c:if>
		       	<c:if test="${p.puSta == '2'}"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.yi.chaifen"/></c:if>
		       	<c:if test="${p.puSta == '3'}"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushEdit.yi.xiazai"/></c:if>
		        </td>
		        </tr>
		        
		        
		      <%--   
		        <td align="center">
		       	<c:if test="${b.cunZai == '0'}">未新建</c:if>
		       	<c:if test="${b.cunZai == '1'}">已新建</c:if>
		        </td> --%>
		        
		        
		   	</c:forEach>
		    </tbody>
      	</table>
		<table width="95%" border="0" cellpadding="0" cellspacing="0">
      		<tr height="40">
		   		<td height="25">
		   			<ingenta-tag:SplitTag page="${form.curpage}" pageCount="${form.pageCount}" count="${form.count}" formName="form" showNum="10" i18n="${sessionScope.lang}"/>
		   		</td>
		   	</tr>
      	</table>
      	</div>
</div>
</div>
</div>
<form:hidden path="id"/>
</form:form>
</body>
</html>

