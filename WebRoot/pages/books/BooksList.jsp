<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
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
			var flag=$("#flag").val();
			var url="<%=request.getContextPath()%>/pages/books/form/booksEdit?flag="+flag;
			var top=document.body.offsetHeigth/2;
			var left=document.body.offsetWIdth/2;
			var width="600px";
			var height="350px";
			openWindow(url,top,left,width,height);
		}
		
		function modObj(id){
			$("#objflag").val($("#flag").val());
			var url="<%=request.getContextPath()%>/pages/books/form/Update?booksid="+id;
			var top=document.body.offsetHeigth/2;
			var left=document.body.offsetWIdth/2;
			var width="600px";
			var height="750px";
			openWindow(url,top,left,width,height);
		}
		
		

		function query(){
			document.getElementById("page").value="0";
			document.getElementById("form").submit();
		}
		
		function xmlObj(id){
			if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.create.xml'/>")){
				document.getElementById("form").action="addxml";
				document.getElementById("id").value=id;
				document.getElementById("page").value=document.getElementById("txtPage").value-1;
				document.getElementById("form").submit();
			}
		}
		
		function delObj(id){
			if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.delete'/>")){
				document.getElementById("form").action="delete";
				document.getElementById("id").value=id;
				document.getElementById("page").value=document.getElementById("txtPage").value-1;
				document.getElementById("form").submit();
			}
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
	 
	 	function getEmails(){
	 	var arr_select = getselecedVal();
			if(arr_select.length<=0){
				alert("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.order.xuanze'/>");
				return false;
			}else{
				$("#objflag").val($("#flag").val());
				var url="<%=request.getContextPath()%>/pages/books/form/userEmailManagerUser?arr_select="+arr_select;
				var top=document.body.offsetHeigth/2;
				var left=document.body.offsetWIdth/2;
				var width="600px";
				var height="350px";
				openWindow(url,top,left,width,height);
			}
		}
	  	
	 	function delALL(id){
		 	var arr_select = getselecedVal();
				if(arr_select.length<=0){
					alert("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.order.xuanze'/>");
					return false;
				}else{
					if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.queding.delete'/>")){
						document.getElementById("form").action="userDel";
						document.getElementById("id").value=id;
						document.getElementById("page").value=document.getElementById("txtPage").value-1;
						document.getElementById("form").submit();
					}
				}
			}  
	 	
	 	
	 	function getXml(id){
		 	var arr_select = getselecedVal();
				if(arr_select.length<=0){
					alert("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.order.xuanze'/>");
					return false;
				}else{
					if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.create.xml'/>")){
						document.getElementById("form").action="userXml";
						document.getElementById("id").value=id;
						document.getElementById("page").value=document.getElementById("txtPage").value-1;
						document.getElementById("form").submit();
					}
				}
			}
	 	
	    // 弹出批量推送窗口
		function getBookId(id) {
			var arr_select = getselecedVal();
			if(arr_select.length<=0){
				alert("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.order.xuanze'/>");
				return false;
			}else{
				if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.Push.Table.Batch.Set.Books'/>")){
					var url = "<%=request.getContextPath()%>/pages/books/form/pushFtpList?arr_select=" + arr_select;
					var top = document.body.offsetHeigth / 2;
					var left = document.body.offsetWIdth / 2;
					var width = "600px";
					var height = "384px";
					openWindow(url, top, left, width, height);
				}
			}
		}
	 	
	 	function xmlScan(id){
				document.getElementById("form").action="userXmlScan";
				document.getElementById("id").value=id;
				document.getElementById("page").value=document.getElementById("txtPage").value-1;
				document.getElementById("form").submit();
		}
	 	
	 	
		function shareSina(){
			//取到选择的数据id
			if($("input[name='subBox']:checked").length<=0){
				alert("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.order.xuanze'/>");
				return false;
			}else{
				if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.fabu.sina'/>")){
					document.getElementById("form").action="shareSina";
					document.getElementById("page").value=document.getElementById("txtPage").value-1;
					document.getElementById("form").submit();
					
				}
			}
		}
		
		function restJson(){
			//取到选择的数据id
			if($("input[name='subBox']:checked").length<=0){
				alert("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.order.xuanze'/>");
				return false;
			}else{
				if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.xuan.tijiao'/>")){
					document.getElementById("form").action="restJson";
					document.getElementById("page").value=document.getElementById("txtPage").value-1;
					document.getElementById("form").submit();
					
				}
			}
		}
		
		function restJson(){
			//取到选择的数据id
			if($("input[name='subBox']:checked").length<=0){
				alert("<ingenta-tag:LanguageTag sessionKey='lang' key='Pages.PushOrder.order.xuanze'/>");
				return false;
			}else{
				var params = "?a=1";
				var checkeds = $("input[name='subBox']:checked");
				for (var i = 0; i < checkeds.length; i++) {
					params += "&" + checkeds[i].name + "=" + checkeds[i].value;
				}
				var url="<%=request.getContextPath()%>/pages/books/form/restJson"+params;
				var top=document.body.offsetHeigth/2;
				var left=document.body.offsetWIdth/2;
				var width="600px";
				var height="350px";
				openWindow(url,top,left,width,height);
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
       <%-- 
       	 <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.info.Msg"/>:&nbsp;</th>
       	 <td width="18%">
         	<form:input path="orderNo" cssStyle="width:98%" id="fileCode"/>
         </td>
        --%>
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.Name"/>:&nbsp;</th>
       	 <td width="18%">
         	<form:input path="title" cssStyle="width:98%" id="fileCode"/>
         </td>
         
         <%-- <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.fen.lei"/>:&nbsp;</th>
       	 <td width="18%">
         	<form:input path="category" cssStyle="width:98%" id="fileCode"/>
         </td> --%>
         
         
         <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.isbn"/>:&nbsp;</th>
          <td width="18%">
         	<form:input path="isbn" cssStyle="width:98%" id="fileCode"/>
         </td>
         <th width="14%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.xml"/>:&nbsp;</th>
         <td width="18%">
         <form:select path="cunZai" id="cunZai">
         <form:option value="" selected="select" ><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.select"/></form:option>
		 <form:option value="1" ><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.ok"/></form:option>
		 <form:option value="0" ><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.no"/></form:option>
		 </form:select>
         </td>
         <!-- <th width="8%" align="center"></th>
         <td width="18%"></td> -->
         <td width="15%" align="center"><input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry"/>" class="devil_button" onclick="query()"/></td>
       </tr>
    </table>
    </div> 
	
<div class="bodyBg">
	<div class="module">
	
	  <div style="margin:5px 0px;">
		   <input type="button" name="add" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.Excel.pldaoru"/>" class="devil_button" onclick="addObj()"/>
		   
 		  <%-- 邮件营销 --%>
		 <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.send.email"/>" class="devil_button" onclick="getEmails()"/>
		  <%-- 新浪微博
		  <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.send.sina"/>" class="devil_button" onclick="shareSina()"/>
		   --%>
		   <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.createXml.pl"/>" class="devil_button" onclick="getXml()"/>
		   <%-- 批量推送 --%> 
		   <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Push.Table.Lable.Batch.Set"/>" class="devil_button" onclick="getBookId()"/>
		   
		   <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.delete.pl"/>" class="devil_button" onclick="delALL()"/>
	  </div>
     	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="devil_table">
     		<thead>
     		<tr>
		        <th width="4%" align=""><input type="checkbox" id="checkAll"/></th>
		        <%-- <th width="5%" align=""><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.info.Msg"/></th> --%>
		        <th width="20%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.Name"/></th>
		        <th width="10%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.isbn"/></th>
		        <th width="15%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.author"/></th>
		        <%-- <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.fen.lei"/></th> --%>
		        <th width="6%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.dingjia"/></th>
		        <th width="5%" align="center" title="在线阅读价格"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.online.price"/></th>
		        <th width="5%" align="center" title="离线阅读价格"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.lixian.price"/></th>
		        <th width="8%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.chuban.date"/></th>
		        <%-- 是否生成xml--%>
		        <th width="5%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.xml"/></th>
		        <th width="12%" align="center"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.operation"/></th>
		    </tr>
		    </thead>
		    <tbody>
		   	<c:forEach items="${list}" var="b" varStatus="index">
		   
		   	   <tr  class="<c:if test="${index.index%2 == 1}">odd</c:if><c:if test="${index.index%2 == 0}">eve</c:if>">
		   	   	<td align="center"><input name="subBox" type="checkbox" value="${b.id}"/></td>
		   	   	<%-- 
		        <td align="center">${b.orderNo}</td>
		        --%>
		        <td align="center">${b.title}</td>
		        <td align="center">${b.isbn}</td>
		        <td align="center">${b.author}</td>
		        <%-- <td align="center">${b.category}</td> --%>
		        <td align="center">${b.price}</td>
		        <td align="center">${b.onPrice}</td>
		        <td align="center">${b.offPrice}</td>
		        <td align="center"><fmt:formatDate value="${b.publishDate}" pattern="yyyy-MM-dd"/></td>
		         <%-- 是否生成xml--%>
		        <td align="center">
		       	<c:if test="${b.cunZai == '0'}"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.no"/></c:if>
		       	<c:if test="${b.cunZai == '1'}"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.book.ok"/></c:if>
		        </td>
		        <td >
		        	<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Modify"/>" class="devil_button" onclick="modObj('${b.id}')"/>
		        	<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Delete"/>" class="devil_button" onclick="delObj('${b.id}')"/>
		        	<%-- <input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.createxml"/>" class="devil_button" onclick="xmlObj('${b.id}')"/>
		        	<c:if test="${b.cunZai == '1'}">
		        	<input type="button" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.UserDate.user.scanxml"/>" class="devil_button" onclick="xmlScan('${b.id}')"/> 
		        	</c:if>--%>
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
<form:hidden  path="id"/>
</form:form>
</body>
</html>

