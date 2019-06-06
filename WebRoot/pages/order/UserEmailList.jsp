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
	 			//alert($(this).val());
	 		     arr_v.push($(this).val());
	 		});
	 		return arr_v;
	 	}
		function sendEmails(){
			var arrUser_select = getselecedVal();
			if(arrUser_select.length<=0){
				alert("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.order.xuanze'/>");
				return false;       
			}else{
				document.getElementById("page").value="0";
				$("#form").submit();
			}
		}
		
		
</script>
</head>
<body>
<form:form action="sendEmails" method="post" commandName="form" id="form">
<!-- 0代表自己的配置  1代表目标配置 -->
<c:if test="${form.msg!=null&&form.msg != ''}">
<script language="javascript">
		alert('${form.msg}');
	</script>
</c:if>
<div class="bodyDiv bodyNew" style="position:relative;">
	<div class="pos">
	
	
<div class="bodyBg">
	<div class="module">
	 <div style="margin:5px 0px;">
  </div>
  <div style="margin:5px 0px;">
  </div>
     	<table width="95%" border="0" cellpadding="0" cellspacing="1" class="devil_table">
     		<thead>
     		<tr>
     			<th width="5%" align="center"><input type=checkbox id="checkAll""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.email.quanxuan"/></th>
		        <th width="8%" align="center">
		        	<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.SerialNumber"/>
		        </th>
		        <th width="10%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.lianxi.ren"/></th>
		        <th width="14%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.lianxi.email"/></th>
		       
		       
		    </tr>
		    </thead>
		    <tbody>
		    <c:if test="${fn:length(list)!=0}">
			   	<c:forEach items="${list}" var="oo" varStatus="index">
			   	   <tr  class="<c:if test="${index.index%2 == 1}">odd</c:if><c:if test="${index.index%2 == 0}">eve</c:if>">
			   	   	<td align="center">　<input name="subBox" type="checkbox" value="${oo.id}"></td>
			   	   	<td align="center">${(form.curpage*form.pageCount)+index.index+1}</td>
			   	   
			        <td align="center">${oo.contacts}</td>
			        <td align="center">${oo.email}</td>
			        </tr>
			   	</c:forEach>
	   		</c:if>
			<c:if test="${fn:length(list)==0}">
				<tr>
					<td align="center" colspan="4"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.email.alert"/></td>
				</tr>
			</c:if>
		    </tbody>
      	</table>
		<table width="95%" border="0" cellpadding="0" cellspacing="0">
      		<tr height="40">
		   		<td height="25">
		   			<ingenta-tag:SplitTag page="${form.curpage}" pageCount="${form.pageCount}" count="${form.count}" formName="form" showNum="10" i18n="${sessionScope.lang}"/>
		   		</td>
		   	</tr>
      	</table>
      	      <div class="control-group span3">
					<label class="control-label" for="form-field-2"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.email.bianji.qu"/>：</label>
					<div class="controls">
					<textarea name="emailContent" id="emailContent" "class='input', style='width: 450px; height: 100px;'"></textarea>
					</div>
					</div>
					<div style="text-align: center;">
					
					<!-- <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();"/> -->
					<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.PushOrder.order.queding"/>" class="devil_button" onclick="sendEmails()"/>&nbsp;&nbsp;
					<input type="button" name="close" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Close"/>" class="devil_button" onclick="window.close();"/>
					 
					</div>   
      	         
      	<% String str=request.getParameter("arr_select");%>
		<input name="booksId" type="hidden" value=<%=str%> size=200/>
      	</div>
</div>
</div>
</div>
<form:hidden path="id"/>
</form:form>
</body>
</html>

