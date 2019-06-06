<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/downMenu.js"></script>
<script type="text/javascript" src="${ctx}/js/calculate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.formatCurrency-1.4.0.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.colorbox.js"></script>
<script type="text/javascript" src="${ctx }/js/artDialog.source.js?skin=black"></script>
<script type="text/javascript" src="${ctx }/js/plugins/iframeTools.source.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/common.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/header_${sessionScope.lang}.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/index.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/subColumn.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/footer.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/order.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/favourite.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/highlight.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/columns.css" />

<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/new_en.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/list.css" />

<script type="text/javascript">
	$(function(){
          var signedstr='${sessionScope.logininfo}';
          var logostr='${sessionScope.logoinfo}';
          if(logostr!=null && logostr.length>1){
          	$("#institutionlogobox").html(logostr);
          	}
          if(signedstr!=null && signedstr.length>1){
          	$("#signedbox").html(signedstr);
			$("#loginbox").hide();
			$("#register").hide();
			$("#signoutlinkdiv").show();
			$("#management").show();
          }else{
          	$("#loginbox").show();
          	$("#register").show();
          	$("#signoutlinkdiv").hide();
          	$("#management").hide();
          }  
          var mul='${sessionScope.mainUserLevel}';
          if(mul!=null && "" != mul){
          	$("#signedlink").hide();
          	$("#management").show();
          	$("#role1").hide();
          	$("#role2").hide();
          	$("#role4").hide();
          	$("#role"+mul).show();
          	if(mul=="5"){
          		$("#role1").show();
          	}
          }else{
          	$("#register").show();
          	$("#signedlink").show();
          	$("#management").hide();
          }    
          
             
        });

	function login(){
		$.ajax({
  			type : "POST",  
			url: "${ctx}/pages/user/form/login",
			data: {
				uid:$("#loginuid").val(),
				pwd:$("#loginpwd").val(),
				rmb:$("#remember").attr("checked"),
				r_ : new Date().getTime()
			},
			success : function(data) {  
			    var s = data.split(":");			     
			    if(s[0]=="success"){
			    	<c:if test="${ctx!=''}">
			    		window.location.href="${ctx}";
			    	</c:if>
			    	<c:if test="${ctx==''}">
			    		window.location.href="${domain}";
			    	</c:if>
			    }else{
			    	art.dialog.tips(s[1],1,'error');
			    }
			    var mul='${sessionScope.mainUserLevel}';
		          if(mul!=null){
		          	$("#signedlink").hide();
		          }else{
		          	$("#signedlink").show();
		          }  
			},  
			error : function(data) {  
			    art.dialog.tips(data,1,'error');
			}  
		});
	}
	function loginout(){
		$.ajax({
  			type : "POST",  
			url: "${ctx}/pages/user/form/loginout",
			data: {r_ : new Date().getTime()},
			success : function(data) { 
			 	var s = data.split(":");
			 	if(s[0]=="error"){
			    	art.dialog.tips(s[1],1,'error');
			    }else{
			    	<c:if test="${ctx!=''}">
			    		location="${ctx}";
			    	</c:if>
			    	<c:if test="${ctx==''}">
			    		location="${domain}";
			    	</c:if>
			    }	
			},  
			error : function(data) {  
			    art.dialog.tips(data,1,'error');
			}  
		});
	}
	var ctx = '${ctx}';
	
	artDialog.tips = function (content,events,status,time) {
		var s  = '<div class="inline" style="padding: 0 1em;font-size: 12px;">';
		s += content ;
		s += '</div>';
		var icon="succeed";
		if(status=='error'){
			icon="warning";
		}
		var t =artDialog({
			id: 'Tips',
			title: "<ingenta-tag:LanguageTag sessionKey='lang' key='Global.Lable.Prompt'/>",
			icon: icon,
			cancel: false,
			fixed: true,
			lock: true
		})
		.content(s)
		.time(time || 1.5);
		if(events==1){
			setTimeout("window.location.reload(true)",1.5*1000);
		}
		return t;
	};
</script>