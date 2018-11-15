<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程定义列表</title>
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
	$(function(){
		$("#grid").datagrid({
			striped : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			toolbar : [
				{
					id : 'deploy',
					text : '发布新流程',
					iconCls : 'icon-add',
					handler : function(){
						location.href = "${pageContext.request.contextPath}/processdefinition_deploy";
					}
				}
			]
		});
	});
	function del(id){
		window.location.href = "${pageContext.request.contextPath}/processDefinition/delete?id="+id;
	}
	
</script>	
</head>
<body class="easyui-layout">
  <div region="center" >
  	<table id="grid" class="easyui-datagrid">  
  		<thead>
  			<tr>
  				<th data-options="field:'id'" width="120">流程编号</th>
  				<th data-options="field:'name'" width="200">流程名称</th>
  				<th data-options="field:'key'" width="100">流程key</th>
  				<th data-options="field:'version'" width="80">版本号</th>
  				<th data-options="field:'viewpng'" width="200">查看流程图</th>
  			</tr>
  		</thead>
  		<tbody>
  				<c:if test="${list != null}">
  					<!-- 在循环过程中 ，将  processDefinition 对象，同时放入 root 和 map 中-->
				<c:forEach items="${list}" var="processDefinition">
  				<tr>
  					<td>
  						<%-- 
  						<s:property value="id"/> <!-- 从root找 -->
  						<s:property value="#processDefinition.id"/> <!-- 从map找 -->
  						 --%>
  						 ${processDefinition.id}
  					</td>
  					<td>${processDefinition.name}</td>
  					<td>${processDefinition.key}</td>
  					<td>${processDefinition.version}</td>
  					<td>
  						<a onclick="window.open('${pageContext.request.contextPath}/processDefinition/showPng?id=${processDefinition.id}','图片','modal=yes,width=800,height=500,resizable=no,scrollbars=no')"
  						class="easyui-linkbutton" data-options="iconCls:'icon-search'">
  							查看流程图
  						</a>
  						
  						<a onclick="del('${processDefinition.id}')"
  						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">
  							删除流程图
  						</a>
  					</td>
  				</tr>
  				</c:forEach>
  				</c:if>
  		</tbody>
  	</table>
  </div>
  <script type="text/javascript">
 	 var deltag = '${deltag}';
  	$(function(){
		if(deltag == '1'){
			$.messager.alert('删除提示','流程定义图已经在使用无法删除！','warning');
		}
	})
  </script>
</body>
</html>