<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	$(function(){
		$("#grid").datagrid({
			toolbar : [
				{
					id : 'add',
					text : '添加权限',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/function_add';
					}
				}           
			],
			url : '${pageContext.request.contextPath}/function/list',
			columns : [[
			  {
				  field : 'id',
				  title : '编号',
				  width : 200
			  },
			  {
				  field : 'name',
				  title : '名称',
				  width : 200
			  },  
			  {
				  field : 'description',
				  title : '描述',
				  width : 200
			  },  
			  {
				  field : 'generatemenu',
				  title : '是否生成菜单',
				  width : 200,
				  formatter:function(data){
					  if(data == "1"){
						  return "是";
					  }else{
						  return "否";
					  }
					  
				  }
					  
				  
			  },  
			  {
				  field : 'zindex',
				  title : '优先级',
				  width : 200
			  },  
			  {
				  field : 'page',
				  title : '路径',
				  width : 200
			  }
			]],
			pageList: [5,10,30,50,100],
			pagination : true
		});
	});
</script>	
</head>
<body class="easyui-layout">
<div data-options="region:'center'">
	<table id="grid"></table>
</div>
</body>
</html>