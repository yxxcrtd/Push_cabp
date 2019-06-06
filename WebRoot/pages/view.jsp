<%@ page language="java" pageEncoding="UTF-8"%>
<%-- <%@ include file="/common/taglibs.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="/WEB-INF/taglib/zhima-taglib.tld" prefix="zhima-tag"%>
<%@ taglib uri="/WEB-INF/taglib/daxtech-taglib.tld" prefix="daxtech-tag"%>
<%@ taglib uri="/WEB-INF/taglib/ingenta-taglib.tld" prefix="ingenta-tag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CNPIEC eReading</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${ctx}/css/css/common.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${ctx}/css/css/header.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${ctx}/css/css/index.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${ctx}/css/css/subColumn.css" />
<%-- <link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/footer.css" /> --%>

<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/flexpaper/js/flexpaper.js"></script>
<script type="text/javascript"
	src="${ctx}/flexpaper/js/flexpaper_handlers.js"></script>
<script src="${ctx}/js/menu.js" type="text/javascript"></script>
<!--[if lt IE 8]>
	   <style type="text/css">
	   li a {display:inline-block;}
	   li a {display:block;}
	   </style>
    <![endif]-->
<script type="text/javascript">
	var config = {
		config : {
			SwfFile : '${ctx}/pages/view/form/page?id=${form.id}',
			key : '$659bda0e09811de23c6',
			Scale : 0.6,
			ZoomTransition : 'easeOut',
			ZoomTime : 0.5,
			ZoomInterval : 0.2,
			FitPageOnLoad : true,
			FitWidthOnLoad : true,
			FullScreenAsMaxWindow : false,
			ProgressiveLoading : true,
			MinZoomSize : 0.2,
			MaxZoomSize : 5,
			SearchMatchAll : false,
			InitViewMode : 'Portrait',
			RenderingOrder : 'flash,html',
			ViewModeToolsVisible : true,
			ZoomToolsVisible : true,
			NavToolsVisible : true,
			SearchToolsVisible : true,
			SearchString : '',
//			PrintVisible : false,
			//PrintToolsVisible : false, PrintEnabled : false,
			localeChain : 'zh_CN',
			//	DocSizeQueryService : '53',
			jsDirectory : '${ctx}/flexpaper/js/',

		}
	};

	$(document).ready(function(){
		config.config.SearchString = $('#wordName').val();
		$('#documentViewer').FlexPaperViewer(config);
	});
	
</script>
</head>
<body>
	<div class="previewContainer" style="width: 100%">
		<div class="clear"></div>
		<!--定义 左边分类列表 开始-->
		<!-- 
        <div class="leftMenu">
          <div class="hideMenu"><a href="#">Menu</a></div>
          <ul id="menu">
            <li>
              <a href="#"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.Label.Search"/></a>
              <ul>
                <li><input type="text" id="searchView"/><a href="#" onclick="aaaa()"><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Search"/></a></li>
                <div id="result">
                </div>
              </ul>
            </li>
            <c:if test="${sessionScope.mainUser!=null }">
            <li>
              <a href="#"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.Label.Record"/></a>
              <ul>
                <li>
                <a id="label1" href="#">
                	<c:if test="${form.record!=null&&form.record.pages.number>0 }"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.lable.lable1"/>${form.record.pages.number}</c:if>
                    <c:if test="${form.record==null||form.record.pages.number<=0 }"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.lable.nolable"/></c:if>
                </a>
                </li>
                <li><input type="button" name="save" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.lable.button"/>" class="devil_button" onclick="addLabels()"/></li>
              </ul>
            </li>
             <li>
              <a href="#"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.Label.Notes"/></a>
              <ul>
                <li>
                	<input type="hidden" name="isNote" id="isNote" value="${form.notes!=null?form.notes.id:'' }"/>
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1">
                    		<tr>
                    			<td><textarea name="content" id="content" style="width: 130px;height: 160px;"><c:if test="${form.notes!=null }">${form.notes.noteContent }</c:if></textarea></td>
                    		</tr>
                    		<tr>
                    			<td>
                    			<input type="button" name="save" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.note.button"/>" class="devil_button" onclick="addNotes()"/>
                    			</td>
                    		</tr>
                    	</table>
				</li>
              </ul>
            </li>
             <li>
              <a href="#"><ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.Label.Copy"/></a>
              <ul>
              	<li>
              	<a href="#">
              	<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.copy.Prompt"/>${form.readCount }
              	</a>
              	<a id="copyCount" href="#">
              	<ingenta-tag:LanguageTag sessionKey="lang" key="Content.View.now.copy.page"/>：<c:if test="${sessionScope.copyCount==null }">0</c:if><c:if test="${sessionScope.copyCount!=null }">${sessionScope.copyCount }</c:if>
              	</a>
              	</li>
              	<li>
              	<input type="button" name="save" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Pages.view.copy.Button"/>" class="devil_button" onclick="copy()"/>
              	<input type="button" name="download" id="downLoad" value="<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.DownLoad"/>" class="devil_button" onclick="download()" disabled="disabled"/>
              	<input type="hidden" id="dir"/>
              	</li>
              </ul>
            </li>
            </c:if>
          </ul>
          <div class="clear"></div>
        </div>
         -->
		<!--定义 左边分类列表 结束-->

		<!--定义 右侧图书详细信息列表 开始-->
		<!-- 分页隐藏页数 -->
<!-- 	<input type="hidden" name="prev" id="prev" value="${form.curpage-1 }" />
		<input type="hidden" name="next" id="next" value="${form.curpage+1 }" />
		<input type="hidden" name="curr" id="curr" value="${form.curpage }" />
 -->
		<input type="hidden" name="curPageId" id="curPageId" value="${form.page.id }" />
		<input type="hidden" name="wordName" id="wordName" value="${form.wordName }" />
		<!-- 分页隐藏页数END -->
		<div class="rightContent">
			<!-- 
			<div class="previewPage">
				<span>Pages</span>
				<input type="text" name="value1" id="a1" value="${form.curpage }" />
				<a href="#"	onclick="go(true,'goto',$('#a1').val())">GO</a>
				<img src="${ctx}/images/space.gif" width="75" height="1" />
				<ul>
					<li>
						<a href="#" onclick="go(false,'first','1')">
							<img src="${ctx}/images/icon-first.gif" />
						</a>
					</li>
					<c:if test="${form.curpage==1}">
						<li>
							<a id="p1" href="#">
								<img src="${ctx}/images/icon-prev.gif" />
							</a>
						</li>
					</c:if>
					<c:if test="${form.curpage!=1}">
						<li>
							<a href="#" onclick="go(false,'prev','')">
								<img src="${ctx}/images/icon-prev.gif" />
							</a>
						</li>
					</c:if>
					<li id="pages1">${form.curpage } of ${form.count}</li>
					<c:if test="${form.curpage==form.count}">
						<li>
							<a href="#">
								<img src="${ctx}/images/icon-next.gif" />
							</a>
						</li>
					</c:if>
					<c:if test="${form.curpage!=form.count}">
						<li>
							<a href="#" onclick="go(false,'next','')">
								<img src="${ctx}/images/icon-next.gif" />
							</a>
						</li>
					</c:if>
					<li>
						<a href="#" onclick="go(false,'last','${form.count}')">
							<img src="${ctx}/images/icon-last.gif" />
						</a>
					</li>
				</ul>

			</div>
			 -->

			<div class="previewContent" style="width: 100%">
				<div class="preview" id="documentViewer"
					style="width: 100%; margin: auto"></div>
					<!-- 
				<script type="text/javascript">
					$('#documentViewer').FlexPaperViewer(config);
				</script>
				 -->
			</div>

			<!-- 
			<div class="previewPage">
				<span>Pages</span>
				<input type="text" name="value1" id="a2" value="${form.curpage }" />
				<a href="#" onclick="go(true,'goto',$('#a2').val())">GO</a>
				<img src="${ctx}/images/space.gif" width="75" height="1" />
				<ul>
					<li>
						<a href="#" onclick="go(false,'first','1')">
							<img src="${ctx}/images/icon-first.gif" />
						</a>
					</li>
					<c:if test="${form.curpage==1}">
						<li>
							<a id="p2" href="#">
								<img src="${ctx}/images/icon-prev.gif" />
							</a>
						</li>
					</c:if>
					<c:if test="${form.curpage!=1}">
						<li>
							<a href="#" onclick="go(false,'prev','')">
								<img src="${ctx}/images/icon-prev.gif" />
							</a>
						</li>
					</c:if>
					<li id="pages2">${form.curpage } of ${form.count}</li>
					<c:if test="${form.curpage==form.count}">
						<li>
							<a href="#">
								<img src="${ctx}/images/icon-next.gif" />
							</a>
						</li>
					</c:if>
					<c:if test="${form.curpage!=form.count}">
						<li>
							<a href="#" onclick="go(false,'next','')">
								<img src="${ctx}/images/icon-next.gif" />
							</a>
						</li>
					</c:if>
					<li>
						<a href="#" onclick="go(false,'last','${form.count}')">
							<img src="${ctx}/images/icon-last.gif" />
						</a>
					</li>
				</ul>

			</div>
			 -->
		</div>

		<!--定义 右侧图书详细信息列表 结束-->
	</div>
</body>
</html>
