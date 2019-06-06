<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Ftp.List.Title"/></title>
<daxtech-tag:CssTag/>
<daxtech-tag:JsTag/>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script> 
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
		
		

		function query(){
			document.getElementById("page").value="0";
			document.getElementById("form").submit();
		}
		
		 $(function() {
	           $("#checkAll").click(function() {
	                $('input[name="subBox"]').attr("checked",this.checked);
	               
	            });
	            var $subBox = $("input[name='subBox']");
	            $subBox.click(function(){
	            	
	                $("#checkAll").attr("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false);
	            });
	        });
		 
		 
		 //返回选中的数据id数组
		 	function getselecedVal(){
		 		var arr_v = new Array(); 
		 		$("input[name='subBox']:checked").each(function(){
		 		     arr_v.push($(this).val());
		 		});
		 		document.getElementById("arr_select").value=arr_v;
		 		return arr_v;
		 	}
	
		
		function delObj(id){
			if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.delete'/>")){
				document.getElementById("form").action="delete";
				document.getElementById("id").value=id;
				document.getElementById("page").value=document.getElementById("txtPage").value-1;
				document.getElementById("form").submit();
			}
		}
	
	
	 	function delALL(id){
		 	var arr_select = getselecedVal();
				if(arr_select.length<=0){
					alert("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.order.xuanze'/>");
					return false;
				}else{
					if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.delete'/>")){
						document.getElementById("form").action="userDel";
						document.getElementById("id").value=id;
						document.getElementById("page").value=document.getElementById("txtPage").value-1;
						document.getElementById("form").submit();
					}
				}
			}  
	 	
	 	
	 
</script>
</head>
<body>

<form:form action="manager" method="post" commandName="form" id="form">
<!-- 0代表自己的配置  1代表目标配置 -->
<form:hidden path="flag" id="flag" />
<form:hidden path="arr_select" id="arr_select" />
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
       	 <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.lianxi.ren"/> :&nbsp;</th>
       	 <td width="18%">
         	<form:input path="contacts" cssStyle="width:98%" id="contacts"/>
         </td>
         
       
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
         
          </tr>
         
         
    </table>
    </div> 
	
<div class="bodyBg">
	<div class="module">
	
	  <div style="margin:5px 0px;">
		   <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry"/>" class="devil_button" onclick="query()"/>
		   <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.delete.pl"/>" class="devil_button" onclick="delALL()"/>
	  </div>
     	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="devil_table">
     		<thead>
     		<tr>
		        <th width="5%" align="center"><input type="checkbox" id="checkAll"/></th>
		        <th width="5%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.lianxi.ren"/></th>
		        <th width="15%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.email.fasong.date"/></th>
		        <th width="12%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.email.fasong.youxiang"/></th>
		        <th width="16%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Operating"/></th>
		    </tr>
		    </thead>
		    <tbody>
		   	<c:forEach items="${list}" var="e" varStatus="index">
		   
		   	   <tr  class="<c:if test="${index.index%2 == 1}">odd</c:if><c:if test="${index.index%2 == 0}">eve</c:if>">
		   	   	<td align="center"><input name="subBox" type="checkbox" value="${e.id}"/></td>
		        <td align="center">${e.contacts}</td>
		        <td align="center">${e.sendDateStr}</td>
		        <td align="center">${e.email}</td>
		        <td>
		        	<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Delete"/>" class="devil_button" onclick="delObj('${e.id}')"/>
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
</form:form>
</body>
</html>

