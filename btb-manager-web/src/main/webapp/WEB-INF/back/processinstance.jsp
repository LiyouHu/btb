<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在运行流程实例列表</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js//jquery-easyui-1.4.1/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js//jquery-easyui-1.4.1/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js//jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js//jquery-easyui-1.4.1//ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("#grid").datagrid({
			rownumbers : true,
			striped : true,
			singleSelect: true,
			nowrap:false,
			fit:true
		});
	});
	//查看业务数据
	function doShowData(id){
		window.open('${pageContext.request.contextPath}/processInstance/findData?id='+id,'图片','modal=yes,width=800,height=400,resizable=no,scrollbars=no');
	}
</script>
<script type="text/javascript">
	function showPng(id){
		window.open('${pageContext.request.contextPath}/processInstance/showPng?id='+id+'','图片','modal=yes,width=1200,height=600,resizable=no,scrollbars=no');
	}
</script>	
</head>
<body class="easyui-layout">
   <div region="center">
   	  <table id="grid" class="easyui-datagrid">
   	  	<thead>
  			<tr>
  				<th data-options="field:'id'" width="60">实例编号</th>
  				<th data-options="field:'name'" width="150">流程定义编号</th>
  				<th data-options="field:'activity'" width="150">运行到哪个任务</th>
  				<th data-options="field:'viewRuntime'" width="500">业务数据</th>
  				<th data-options="field:'viewpng'" width="200">查看流程图</th>
  			</tr>
  		</thead>
  		<tbody>
  			<c:if test="${list != null}">
  				<c:forEach items="${list}" var="processInstance">
  				<tr>
  					<td>${processInstance.id }</td>
  					<td>${processInstance.processDefinitionId }</td>
  					<td>${processInstance.activityId }</td>
  					<td>
  						<a onclick="doShowData(${processInstance.id})" class="easyui-linkbutton" data-options="iconCls:'icon-search'">
  							查看业务数据
  						</a>
  					</td>
  					<td>
  						<a onclick="showPng('${processInstance.id}');" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查看流程图</a>
  					</td>
  				</tr>
  				</c:forEach>
  			</c:if>
  		</tbody>
   	  </table>
   </div>
</body>
</html>