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
					url : "${pageContext.request.contextPath}/order/list?key="+key,
					idField : 'eid',
					columns : columns,
					onDblClickRow : doDblClickRow	//指定双击取派员行的事件函数
				});  
			}); 
		
	}
	
	
	
	
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	},{
		id : 'button-view',	
		text : '查看订单详情',
		iconCls : 'icon-search',
		handler : showOrderDetails
	}
	
	];
	// 定义列
	var columns = [ [{
		field : 'oid',
		checkbox : true
	},{
		field : 'username',
		title : '收货人',
		width : 60,
		align : 'center'
	},{
		field : 'payment',
		title : '支付金额',
		width : 60,
		align : 'center',
		formatter:function(data,row,index){
			return data/100;
		}
	},{
		field : 'paymentType',
		title : '支付类型',
		width : 60,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "月底批结";
			}else if(data == "2"){
				return "货到支付";
			}
		}
	}, {
		field : 'cellphone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'receiver',
		title : '收货地址',
		width : 180,
		align : 'center'
	}, {
		field : 'storekeeperid',
		title : '备货员id',
		width : 50,
		align : 'center'
	}, {
		field : 'empid',
		title : '派送员id',
		width : 50,
		align : 'center'
	}, {
		field : 'status',
		title : '订单状态',
		width : 80,
		align : 'center',
		formatter : function(data,row, index){
			if(row.closeTime != null){
				return "<span style='color:red'>已取消</span>";			
			}
			if(row.endTime==null){
				return "<span style='color:red'>进行中</span>";
			}else {
				return "已结束";
			}
		}
	}, {
		field : 'remark',
		title : '备注',
		width : 200,
		align : 'center'
	},{
		field : 'createTime',
		title : '创建时间',
		width : 120,
		align : 'center',
		formatter:function(data,row,index){
			if(data != null){
				var now = new Date(data);
		    	return now.format("yyyy-MM-dd hh:mm:ss");
			}else{
				return "" ;
			}
		}
	}, {
		field : 'updataTime',
		title : '更新时间',
		width : 120,
		align : 'center',
		formatter:function(data,row, index){
			if(data != null){
				var now = new Date(data);
		    	return now.format("yyyy-MM-dd hh:mm:ss");
			}else{
				return "" ;
			}
		}
	}, {
		field : 'paymentTime',
		title : '支付时间',
		width : 120,
		align : 'center',
		formatter:function(data,row, index){
			if(data != null){
				var now = new Date(data);
		    	return now.format("yyyy-MM-dd hh:mm:ss");
			}else{
				return "" ;
			}
		}
	}, {
		field : 'endTime',
		title : '结束时间',
		width : 120,
		align : 'center',
		formatter:function(data,row, index){
			if(data != null){
				var now = new Date(data);
		    	return now.format("yyyy-MM-dd hh:mm:ss");
			}else{
				return "" ;
			}
		}
	}, {
		field : 'closeTime',
		title : '订单关闭时间',
		width : 120,
		align : 'center',
		formatter:function(data,row, index){
			if(data != null){
				var now = new Date(data);
		    	return now.format("yyyy-MM-dd hh:mm:ss");
			}else{
				return "" ;
			}
		}
	}
	
	] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
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
			url : "${pageContext.request.contextPath}/order/list",
			idField : 'oid',
			columns : columns,
			onDblClickRow : doDblClickRow	//指定双击取派员行的事件函数
		});
		
		
	});
	//双击事件处理函数
	function doDblClickRow(rowIndex, rowData){
		var oid = rowData.oid ;
		window.open('${pageContext.request.contextPath}/order/orderdetails?oid='+oid,'图片','modal=yes,width=800,height=400,resizable=no,scrollbars=no');
	}
	//双击事件处理函数
	function showOrderDetails(){
	 	var oid = $('#grid').datagrid('getSelected').oid;
		window.open('${pageContext.request.contextPath}/order/orderdetails?oid='+oid,'图片','modal=yes,width=800,height=400,resizable=no,scrollbars=no');
	}
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	
</body>
</html>	