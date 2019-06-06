<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>功能列表</title>
	</head>
	
	<body>
		<ul>
			<li>
				<a href="<%=basePath%>pages/ftpConfig/form/manager?&flag=0" target="_blank">源FTP</a>
			</li>
			<li>
				<a href="<%=basePath%>pages/ftpdirConfig/form/manager?&flag=0" target="_blank">源FTP目录</a>
			</li>
			<li>
				<a href="<%=basePath%>pages/ftpConfig/form/manager?&flag=1" target="_blank">目标FTP</a>
			</li>
			<li>
				<a href="<%=basePath%>pages/ftpdirConfig/form/manager?&flag=1" target="_blank">目标FTP目录</a>
			</li>
		</ul>
		
		<ul>
			<li>
				<a href="<%=basePath%>pages/onix/form/manager" target="_blank">Onix管理</a>
			</li>
			<li>
				<a href="<%=basePath%>pages/onixCodeList/form/manager" target="_blank">Onix CodeList 管理</a>
			</li>
		</ul>
		
		<ul>
			<li>
				<a href="<%=basePath%>pages/loadConfig/form/threadcfg" target="_blank">系统配置</a>
			</li>
		</ul>
		
		<ul>
			<li>
				<a href="<%=basePath%>pages/userTemplate/form/manager" target="_blank">模板管理</a>
			</li>
			<li>
				<a href="<%=basePath%>pages/templateMapping/form/manager" target="_blank">模板映射</a>
			</li>
			<li>
				<a href="<%=basePath%>pages/templateNodeMapping/form/manager" target="_blank">模板节点映射</a>
			</li>
		</ul>
		
		<ul>
			<li>
				<a href="<%=basePath%>pages/push/form/manager" target="_blank">推送</a>
			</li>
		</ul>
		
		<ul>
			<li>
				<a href="<%=basePath%>pages/pushTask/form/manager" target="_blank">推送历史</a>
			</li>
		</ul>
		
	</body>
</html>
