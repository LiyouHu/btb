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
	function doAdd(){
		//alert("增加...");
		$('#addEmpGroupWindow').window("open");
	}
	
	function doDelete(){
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert("系统提示","请选择要删除的记录");
		}else{
			//选中了记录
			var array = new Array() ;
			for(var i=0;i<rows.length;i++){
				
				array.push(rows[i].gid);
			}
			var ids = array.join(",");
			$.messager.confirm('确认','确定删除ID为 '+ids+' 的组吗？',function(r){
				if(r){
					window.location.href = "${pageContext.request.contextPath}/empGroup/delete/"+ids;
				}
			});
		}
		
	}
	
	//工具栏
	var toolbar = [{
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	},
	
/* 	<shiro:hasPermission name="EmpGroup"> */
		{
			id : 'button-delete',
			text : '删除',
			iconCls : 'icon-cancel',
			handler : doDelete
		}
	];
/* 	</shiro:hasPermission> */
	// 定义列
	var columns = [ [{
		field : 'gid',
		checkbox : true
	},{
		field : 'gname',
		title : '组名称',
		width : 80,
		align : 'center'
	},{
		field : 'code',
		title : '组代码',
		width : 80,
		align : 'center'
	},{
		field : 'description',
		title : '组描述',
		width : 200,
		align : 'center'
	},{
		field : 'groupEmps',
		title : '所有组员',
		width : 500,
		align : 'center',
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
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/empGroup/listDesc",
			idField : 'gid',
			columns : columns,
			onDblClickRow : doDblClickRow	//指定双击取派员行的事件函数
		});
		
		// 添加雇员窗口
		$('#addEmpGroupWindow').window({
	        title: '添加组员',
	        width: 400,
	        modal: true,	//遮罩效果
	        shadow: true,	//阴影效果
	        closed: true,	//关闭状态
	        height: 400,	
	        resizable:false	//改变大小
	    });
		// 修改雇员窗口
		$('#editEmpGroupWindow').window({
	        title: '修改雇员',
	        width: 400,
	        modal: true,	//遮罩效果
	        shadow: true,	//阴影效果
	        closed: true,	//关闭状态
	        height: 400,	
	        resizable:false	//改变大小
	    });
		
	});
	//双取派员事件处理函数
	function doDblClickRow(rowIndex, rowData){
		$('#editEmpGroupWindow').window('open');
		$('#editEmpGroupWindow').form('load',rowData);
	}
	function addEmpGroup(){
		var b = $('#addEmpGroupForm').form('validate');
		if(b){
			$('#addEmpGroupForm').submit();
		}
	}
	function editEmpGroup(){
		var b = $('#editEmpGroupForm').form('validate');
		if(b){
			$('#editEmpGroupForm').submit();
		}
	}
	
</script>

	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对雇员进行添加或者修改" id="addEmpGroupWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="javascript:addEmpGroup()" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addEmpGroupForm" method="post" action="${pageContext.request.contextPath}/empGroup/add">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">雇员组信息</td>
					</tr>
					<tr>
						<td>组名称</td>
						<td><input type="text" name="gname" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>组编码</td>
						<td><input type="text" name="code" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>组描述</td>
						<td><input type="text" name="description" class="easyui-validatebox" required="true"/></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	
	<div class="easyui-window" title="对雇员进行修改" id="editEmpGroupWindow" collapsible="false" 
	minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-edit" href="javascript:editEmpGroup()" class="easyui-linkbutton" plain="true" >修改</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editEmpGroupForm" method="post" action="${pageContext.request.contextPath}/empGroup/update">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">组信息
						<input type="hidden" name="gid" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					<tr>
						<td>组名称</td>
						<td><input type="text" name="gname" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>组编码</td>
						<td><input type="text" name="code" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>组描述</td>
						<td><input type="text" name="description" class="easyui-validatebox" required="true"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>	