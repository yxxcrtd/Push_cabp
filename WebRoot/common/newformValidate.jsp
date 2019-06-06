<link rel="stylesheet" href="${ctx}/css/validationEngine.jquery.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/css/template.css" type="text/css"/>
<script src="${ctx}/script/jquery-1.8.2.min.js" type="text/javascript">
</script>
<script src="${ctx}/script/languages/jquery.validationEngine-${sessionScope.lang}.js" type="text/javascript" charset="utf-8">
</script>
<script src="${ctx}/script/jquery.validationEngine.js" type="text/javascript" charset="utf-8">
</script>
<script type="text/javascript">
	jQuery(document).ready(function(){
	// binds form submission and fields to the validation engine
		jQuery("#form").validationEngine();
	});
</script>
