<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> --%>
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
	src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript">
	
	function doView(){
		$.messager.prompt('查询提示', '请输入查询条件:',
			function(key){
				if (key == null){
					key = "" ;
				}
				// 雇员信息表格
				$('#grid').datagrid( {
					iconCls : 'icon-forward',
					fit : true,
					border : false,
					rownumbers : true,
					striped : true,
					pageList: [3,5,10,30,50,100],
					pagination : true,
					toolbar : toolbar,
					url : "${pageContext.request.contextPath}/user/list?key="+key,
					idField : 'uid',
					columns : columns
					
				});  
			}); 
		
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	} ];
	// 定义列
	var columns = [ [{
		field : 'uid',
		checkbox : true
	},{
		field : 'username',
		title : '名称',
		width : 60,
		align : 'center'
	}, {
		field : 'cellphone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'address',
		title : '收货地址',
		width : 180,
		align : 'center'
	},{ 
		field : 'email',
		title : '电子邮件',
		width : 120,
		align : 'center',
	},{
		field : 'created',
		title : '雇佣日期',
		width : 120,
		align : 'center',
		formatter:TT.formatDateTime
	}, {
		field : 'updated',
		title : '更新日期',
		width : 120,
		align : 'center',
		formatter:TT.formatDateTime
	}] ];
	
	$(function(){
		// 雇员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [3,5,10,30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/user/list",
			idField : 'uid',
			columns : columns
			//onDblClickRow : doDblClickRow	//指定双击取派员行的事件函数
		});
		
	});
	$(function(){
		//扩展自定义校验规则
		var reg = /^1[3|4|5|6|7|8|9][0-9]{9}$/;
		$.extend($.fn.validatebox.defaults.rules, { 
			phoneNumber: { 
				validator: function(value,param){ 
				return reg.test(value); 
			}, 
			message: '手机号输入不正确！' 
			} 
		});
	})
	
</script>
</head>
<body class="easyui-layout" ">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
</body>
</html>	