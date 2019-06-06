<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<!-- LoadConfig_Adm.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Page.thread.setting"/></title>
<daxtech-tag:CssTag/>
<daxtech-tag:JsTag/>
<script type="text/javascript">	
	function submitObj(){
		if(window.confirm("<ingenta-tag:LanguageTag sessionKey='lang' key='Page.thread.ftp.prompt'/>")){
			document.getElementById("form").action="submit";
			document.getElementById("form").submit();
		}
	}
</script>
</head>
<body>
<form:form action="loadConfig" method="post" commandName="form" id="form">
<c:if test="${form.msg!=null&&form.msg != ''}">
<script language="javascript">
		alert('${form.msg}');
	</script>
</c:if>
<div class="bodyDiv bodyNew" style="position:relative;">
	<div class="pos">
<div class="bodyBg">
<div class="book-module">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tab02" align="center">
      
       <tr>
         <!-- 源数据自动下载线程  -->
         <th width="30%" align="center"><ingenta-tag:LanguageTag sessionKey='lang' key='Page.thread.Source.Download.label.name'/>：</th>
         <td width="15%">
        	<form:select path="sourceDataLoad" cssStyle="width:120px">
				<form:options items="${form.loadMap}" />
			</form:select>
         </td>
         <!-- 解析拆分源数据线程 -->
         <th width="30%" align="center"><ingenta-tag:LanguageTag sessionKey='lang' key='Page.thread.Source.Split.label.name'/>：</th>
         <td width="15%">
         	<form:select path="analysisDataLoad" cssStyle="width:120px">
				<form:options items="${form.loadMap}"/>
			</form:select>
         </td>
       </tr>
       
       
       <tr>
         <!-- 下载图书文件线程  -->
         <th width="30%" align="center"><ingenta-tag:LanguageTag sessionKey='lang' key='Page.thread.Datafile.Download.label.name'/>：</th>
         <td width="15%">
        	<form:select path="download" cssStyle="width:120px">
				<form:options items="${form.loadMap}"/>
			</form:select>
         </td>
         <!-- 模板转化线程 -->
         <!-- 
         <th width="30%" align="center"><ingenta-tag:LanguageTag sessionKey='lang' key='Page.thread.Datafile.Download.label.name'/>：</th>
         <td width="15%">
         	<form:select path="convertDataLoad" cssStyle="width:120px">
				<form:options items="${form.loadMap}"/>
			</form:select>
         </td>
       </tr>
        -->
         <!-- 图书推送线程 -->
         <th width="30%" align="center"><ingenta-tag:LanguageTag sessionKey='lang' key='Page.thread.Source.Push.label.name'/>：</th>
         <td width="15%">
         	<form:select path="pushLoad" cssStyle="width:120px">
				<form:options items="${form.loadMap}" />
			</form:select>
         </td>
       </tr>
       
    </table>
    </div>
</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab02" align="center">
    	<tr>
      		<td align="center" width="10%" rowspan="24">
		 	<input type="button" value="<ingenta-tag:LanguageTag sessionKey='lang' key='Page.thread.ftp.button.setting'/>" class="devil_button" onclick="submitObj()"/>
		 </td>
      </tr>
      </table>
</div>
</div>
<form:hidden path="id"/>
</form:form>
</body>
</html>

