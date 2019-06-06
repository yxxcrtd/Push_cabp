<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${ctx}/css/screen.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/body.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/jquery-ui-1.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/picnic.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/registration.css" rel="stylesheet" type="text/css" charset="utf-8"/>
    <title><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Error.Prompt.Title"/></title>
  </head>
  
  <body>
    <div class="ui-dialog-content ui-widget-content" style="display: block; width: auto; min-height: 0px; " id="recommendForm">
    	<fieldset style="WIDTH:400px;HEIGHT:100px" align="center">
		   	<legend>
		   		<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.Error.Prompt.Body"/>（Error）
		   	</legend>
		   	<br/>
		   	<div align="center">${message}</div>
	   	</fieldset>	
    </div>
    <br/>
    <br/>
  </body>
</html>
