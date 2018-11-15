<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前流程图</title>
</head>
<body>
<!-- 1.获取到规则流程图 -->
<img id="img1" style="position: absolute;top: 0px;left: 0px;"
	 src=""/>
	<!--  ${pageContext.request.contextPath}/processInstanceAction_viewImage?deploymentId=${deploymentId}&imageName=${imageName} -->
<!-- 2.根据当前活动的坐标，动态绘制DIV -->
<div style="position: absolute;border:4px solid red;top:${y-4}px;left:${x-4}px;width:${width}px;height:${height}px;">
<!-- <div style="position: absolute;border:1px solid red;top:200px;left:200px;width:200px;height:200px;"> -->
</div>
<script type="text/javascript">
		var pngName = '${pngName}';
	//	imageName =  imageName.replace('\/','%5c'); 
	//	var imageName = encodeURIComponent('${imageName}'); 
		document.getElementById('img1').src="${pageContext.request.contextPath}/processInstance/viewImage?deploymentId=${deploymentId}&pngName="+pngName;
</script>
</body>
</html>