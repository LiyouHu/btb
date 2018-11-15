<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	//查看业务数据
	function doShowData(id){
		window.open('${pageContext.request.contextPath}/task/findData?id='+id,'图片','modal=yes,width=800,height=400,resizable=no,scrollbars=no');
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table class="easyui-datagrid" fit="true" nowrap="false">
			<thead>
				<tr>
					<th data-options="field:'id',width:120">任务编号</th>
					<th data-options="field:'name',width:120">任务名称</th>
					<th data-options="field:'data',width:520">业务数据</th>
					<th data-options="field:'pick',width:120">办理任务</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="task">
					<tr>
						<td>${task.id}</td>
						<td>${task.name}</td>
						<td>
							<a onclick="doShowData(${task.id})" class="easyui-linkbutton" data-options="iconCls:'icon-search'">
  								查看业务数据
  							</a>
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/task/${task.taskDefinitionKey}?taskId=${task.id}"  class="easyui-linkbutton">办理任务
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>