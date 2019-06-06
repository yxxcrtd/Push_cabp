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
<form:form action="manager" method="post" commandName="form" id="form">
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
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.kaishi"/>：</th>
         <td width="18%">
          <form:input id="createdon" type="text" path="createdon"/> 
     		<img onclick="WdatePicker({el:$dp.$('createdon')})" src="${ctx}/My97DatePicker/skin/datePicker.gif" _fcksavedurl="${ctx}/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"> 
         </td>
         
         
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.jianshu"/>：</th>
         <td width="18%">
         	<form:input id="createdto" type="text"  path="createdto"/> 
     		<img onclick="WdatePicker({el:$dp.$('createdto')})" src="${ctx}/My97DatePicker/skin/datePicker.gif" _fcksavedurl="${ctx}/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"> 
         </td>
         
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.book.name"/>：</th>
         <td width="18%">
         	<form:input path="bookName" cssStyle="width:98%" id="bookName"/>
         </td>
      
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.book.fenlei"/>：</th>
         
         <td width="18%">
         <form:select path="classify" id="classify">
         <form:option value="" selected="select" ><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.select"/></form:option>
		 <form:option value="0" ><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.books.tu.shu"/></form:option>
		 <form:option value="1" ><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.books.qi.kan"/></form:option>
		 </form:select>
         </td>
          </tr>
         
         
         
         <tr>
          <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.isbn"/>：</th>
         <td width="18%">
         	<form:input path="isbn" cssStyle="width:98%" id="isbn"/>
         </td>
       	 <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.wangzhan.name"/>：</th>
         <td width="18%">
         	<form:input path="targateServer" cssStyle="width:98%" id="targateServer"/>
         </td>
         
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Content.Table.Lable.Status"/>：</th>
         <td width="18%">
         	<list>
	        	<form:select path="status" id="status" cssStyle="width:98%" >
					<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/></form:option>
	        		<c:forEach items="${form.statusMap}" var="s">
						<form:option value="${s.key}">${s.value}</form:option>
					</c:forEach>
	        	</form:select>
        	</list>
         </td>
         
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.publisher"/>：</th>
         <td width="18%">
         	<form:input path="publisher" cssStyle="width:98%" id="publisher"/>
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
		        <th width="6%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.book.name"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.isbn"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.chubanshe"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.wangzhan.name"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Content.Table.Lable.Status"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.book.fenlei"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.dabao.date"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.tuisong.kaishi.date"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.tuisong.jieshu.date"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.tuisong.date"/></th>
		    </tr>
		    </thead>
		    <tbody>
		   	<c:forEach items="${list}" var="p" varStatus="index">
		   	   <tr  class="<c:if test="${index.index%2 == 1}">odd</c:if><c:if test="${index.index%2 == 0}">eve</c:if>">
		   	   	<td align="center">${(form.curpage*form.pageCount)+index.index+1}</td>
		   	   	
		        <td align="center"><span style="display: block; width: 150px; overflow: hidden; white-space: nowrap; -o-text-overflow: ellipsis; text-overflow: ellipsis;">${p.bookName}</td>
		        <td align="center"><span style="display: block; width: 150px; overflow: hidden; white-space: nowrap; -o-text-overflow: ellipsis; text-overflow: ellipsis;">${p.isbn}</td>
		        <td align="center">${p.publisher}</td>
		        
		        <td align="center">${p.targateServer}</td>
				<td align="center">
			        <c:choose>
			        	<c:when test="${p.status==1}">
			        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Pushtask.Data.operate.Pending"/>
			        	</c:when>
			        	<c:when test="${p.status==2}">
			        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Pushtask.Data.operate.Succeed"/>
			        	</c:when>
			        	<c:otherwise>
			        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Pushtask.Data.operate.Failed"/>
			        	</c:otherwise>
			        </c:choose>
		        </td>
		        <%-- <td align="center">${p.classify}</td> --%>
		        
		        
		        <td align="center">
		       	<c:if test="${p.classify == '0'}"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.tushu"/></c:if>
		       	<c:if test="${p.classify == '1'}"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.qikan"/></c:if>
		        </td>
		        
		        <td align="center">${p.dabaoDate}</td>
		        <td align="center">${p.tuisDate}</td>
		        <td align="center">${p.tuieDate}</td>
		        <td align="center">${p.createTimeStr}</td>
		        </tr>
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

