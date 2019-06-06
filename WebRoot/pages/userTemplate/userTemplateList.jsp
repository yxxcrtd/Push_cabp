<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!-- userTemplateListList.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.List.Title"/></title>
<daxtech-tag:CssTag/>
<daxtech-tag:JsTag/>
<script type="text/javascript">
		window.onload=  function(){
		}
		function openWindow(url,top,left,width,height){
			var r="";

			if(url.indexOf("?")!=-1){
				url = url+ "&r_=" + Math.random();
			}else {
				url = url+ "?r_" + Math.random();
			}

			var returnval = window.showModalDialog(url,window,"dialogTop:"+top+";dialogLeft:"+left+";dialogWidth="+width+";dialogHeight="+height);
			//通过返回值判断  如果是undefined 说明是 关闭功能 则不执行查询列表功能
			if(returnval == undefined){
				return;
			}
			if(document.getElementById("txtPage")){
				document.getElementById("page").value = (document.getElementById("txtPage").value - 1);
			}else{
				document.getElementById("page").value = 0;
			}
			document.getElementById("form").submit();
		}
		function addObj(){
			var url="<%=request.getContextPath()%>/pages/userTemplate/form/add";
			var top=document.body.offsetHeigth/2;
			var left=document.body.offsetWIdth/2;
			var width="650px";
			var height="350px";
			openWindow(url,top,left,width,height);
		}
		function modObj(id){
			var url="<%=request.getContextPath()%>/pages/userTemplate/form/edit?eid="+id;
			var top=document.body.offsetHeigth/2;
			var left=document.body.offsetWIdth/2;
			var width="620px";
			var height="350px";
			openWindow(url,top,left,width,height);
		}
		
		function delObj(id, f){
			if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.Ftp.Prompt.Delete'/>")){//您确定删除选中的FTP配置信息?
				document.getElementById("form").action="delete";
				document.getElementById("id").value=id;
				document.getElementById("f").value = f;
				document.getElementById("page").value=document.getElementById("txtPage").value-1;
				document.getElementById("form").submit();
			}
		}
		
		function create(id){
			document.getElementById("form").action="create";
			document.getElementById("id").value=id;
			document.getElementById("page").value=document.getElementById("txtPage").value-1;
			document.getElementById("form").submit();
		}
		
		function query(){
			document.getElementById("page").value="0";
			document.getElementById("form").submit();
		}
		
		function conf(id){
			var url="<%=request.getContextPath()%>/pages/userTemplate/form/config?eid="+id;
			var top=document.body.offsetHeigth/2;
			var left=document.body.offsetWIdth/2;
			var width="600px";
			var height="350px";
			openWindow(url,top,left,width,height);
		}
</script>
<script>
</script> 
</head>
<body>
<form:form action="manager" method="post" commandName="form" id="form">
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
       	 <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Name"/>：</th>
         <td width="18%">
         	<form:input path="name" cssStyle="width:98%" id="name"/>
         	<form:input path="" cssStyle="display:none" id=""/>
         </td>
       	 <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Category"/>：</th>
         <td width="18%">
        	<form:select path="flag" cssStyle="width:120px">
	        	<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/></form:option>
				<c:forEach items="${form.templateCategoryMap}" var="c">
        			<form:option value ="${c.key}">${c.value}</form:option>
				</c:forEach>
			</form:select>
         </td>
         
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Type"/>：</th>
         <td width="18%">
        	<form:select path="type" cssStyle="width:120px">
	        	<form:option value=""><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/></form:option>
	        	<c:forEach items="${form.templateTypeMap}" var="c">
        			<form:option value ="${c.key}">${c.value}</form:option>
				</c:forEach>
			</form:select>
        </td>
         
       </tr>
    </table>
    </div>
	
<div class="bodyBg">
	<div class="module">
  <div style="margin:5px 0px;">
   <input type="button" name="add" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.New"/>" class="devil_button" onclick="addObj();"/>
   <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry"/>" class="devil_button" onclick="query()"/>
  </div>
     	<table width="95%" border="0" cellpadding="0" cellspacing="1" class="devil_table">
     		<thead>
     		<tr>
		        <th width="8%" align="center">
		        	<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.SerialNumber"/>
		        </th>
		        <th width="14%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Name"/></th>
		        <th width="14%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Category"/></th>
		        <th width="14%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Type"/></th>
		        <th width="14%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplate.Table.Lable.Time"/></th>
		        <th width="14%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Operating"/></th>
		    </tr>
		    </thead>
		    <tbody>
		   	<c:forEach items="${list}" var="p" varStatus="index">
		   	   <tr  class="<c:if test="${index.index%2 == 1}">odd</c:if><c:if test="${index.index%2 == 0}">eve</c:if>">
		   	   	<td align="center">${(form.curpage*form.pageCount)+index.index+1}</td>
		        <td>${p.name}</td>
		        <td align="center">
		        	 <c:if test="${p.flag==0}">
			        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.UserTemplate"/>
			        	</c:if>
		        	<c:if test="${p.flag==1}">
		        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserTemplateMapping.Table.Lable.TargetTemplate"/>
		        	</c:if>
		        </td>
		        <td align="center">
			       <c:if test="${p.type==0}">
		        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.content.type.EBook"/>
		        	</c:if>
		        	<c:if test="${p.type==1}">
		        		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.content.type.Periodical"/>
		        	</c:if>
		        </td>
		        <td align="center">${p.strTime}</td>
		        <td align="center">
		        	<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Modify"/>" class="devil_button" onclick="modObj('${p.id}')"/>
		        	<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Delete"/>" class="devil_button" onclick="delObj('${p.id}', '${p.flag}')"/>
		        	<!--  
		        	<input type="button" value="配置" class="devil_button" onclick="conf('${p.id}')"/>
		        	<input type="button" value="创建xml" class="devil_button" onclick="create('${p.id}')"/>
		        	-->
		        </td>
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
<form:hidden path="f"/>
</form:form>
</body>
</html>

