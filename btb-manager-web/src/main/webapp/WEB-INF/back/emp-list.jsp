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
		$('#addEmpWindow').window("open");
	}
	
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
					url : "${pageContext.request.contextPath}/emp/list?key="+key,
					idField : 'eid',
					columns : columns,
					onDblClickRow : doDblClickRow	//指定双击取派员行的事件函数
				});  
			}); 
		
	}
	
	function doDelete(){
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert("系统提示","请选择要删除的记录");
		}else{
			//选中了记录
			var array = new Array() ;
			for(var i=0;i<rows.length;i++){
				
				array.push(rows[i].eid);
			}
			var ids = array.join(",");
			$.messager.confirm('确认','确定删除ID为 '+ids+' 的雇员吗？',function(r){
				if(r){
					window.location.href = "${pageContext.request.contextPath}/emp/delete/"+ids;
				}
			});
		}
		
	}
	
	function doRestore(){
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert("系统提示","请选择要还原的记录");
		}else{
			//选中了记录
			var array = new Array() ;
			for(var i=0;i<rows.length;i++){
				array.push(rows[i].eid);
			}
			var ids = array.join(",");
			$.messager.confirm('确认','确定还原ID为 '+ids+' 的雇员吗？',function(r){
				if(r){
					window.location.href = "${pageContext.request.contextPath}/emp/restore/"+ids;
				}
			});
		}
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	},
	
/* 	<shiro:hasPermission name="emp"> */
		{
			id : 'button-delete',
			text : '作废',
			iconCls : 'icon-cancel',
			handler : doDelete
		},
/* 	</shiro:hasPermission>
	 */
	
	{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [{
		field : 'eid',
		checkbox : true
	},{
		field : 'ename',
		title : '姓名',
		width : 60,
		align : 'center'
	},{
		field : 'gender',
		title : '性别',
		width : 40,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "男";
			}else if(data == "0"){
				return "女";
			}
		}
	}, {
		field : 'cellphone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'address',
		title : '住址',
		width : 180,
		align : 'center'
	}, /* {	使用后端格式化的日期显示
		field : 'formatBirthday',
		title : '生日',
		width : 120,
		align : 'center',
	} , */{
		/* 前端自行格式化日期显示 */
		field : 'birthday',
	 /* 	hidden : true ,  */
		title : '生日',
		width : 120,
		align : 'center',
		formatter:TT.formatDateTime
	}, {
		field : 'salary',
		title : '基本工资',
		width : 80,
		align : 'center'
	}, {
		field : 'bonus',
		title : '提成工资',
		width : 80,
		align : 'center'
	}, {
		field : 'status',
		title : '雇员状态',
		width : 80,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "在职";
			}else if(data == "2"){
				return "<span style='color:red;'>离职</span>";
			}
		}
	}, {
		field : 'gid',
		hidden : true,
		title : '雇员组',
		width : 120,
		align : 'center'
	}, {
		field : 'groupName',
		title : '雇员组',
		width : 120,
		align : 'center'/*,
		 formatter:function(gid,row,index){
			var url = "${pageContext.request.contextPath}/empGroup/findEmpgroupById"
			var gname ;
			$.get(url,{group_id:gid},function(data){
				gname = data.gname;
				alert("1"+gname);
				return gname;
			},'json');
			alert("2"+gname);
		} */
	
	},{
		field : 'hiredate',
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
	}, {
		field : 'roles',
		title : '角色',
		width : 200,
		align : 'center',
		formatter: function(data,row, index){
			var roles = "";
			for(var i=0;i<data.length;i++){
				roles += data[i].name + "、 ";
			}
			return roles; 
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
			url : "${pageContext.request.contextPath}/emp/list",
			idField : 'eid',
			columns : columns,
			onDblClickRow : doDblClickRow	//指定双击取派员行的事件函数
		});
		
		// 添加雇员窗口
		$('#addEmpWindow').window({
	        title: '添加雇员',
	        width: 400,
	        modal: true,	//遮罩效果
	        shadow: true,	//阴影效果
	        closed: true,	//关闭状态
	        height: 500,	
	        resizable:false	//改变大小
	    });
		// 修改雇员窗口
		$('#editEmpWindow').window({
	        title: '修改雇员',
	        width: 400,
	        modal: true,	//遮罩效果
	        shadow: true,	//阴影效果
	        closed: true,	//关闭状态
	        height: 500,	
	        resizable:false	//改变大小
	    });
		
	});
	
	//双取派员事件处理函数
	function doDblClickRow(rowIndex, rowData){
		
		var temp = new Date(rowData.birthday);
		//使用common.js中的Date.prototype.format格式化
		rowData.birthday = temp.format("yyyy-MM-dd hh:mm:ss");
		//取得雇员角色的id
		//角色数据回显不使用自动回显
		if(rowData.roles.length>0){
			for(var i=0;i<rowData.roles.length;i++){
				var roleId = rowData.roles[i].id ;
				$('#'+roleId+'').attr("checked", true);
			}
		}else{
			$('input:checkbox').attr("checked", false);
		}
		
		
		$('#editEmpWindow').window('open');
		$('#editEmpWindow').form('load',rowData);
	}
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
	function addEmp(){
		var b = $('#addEmpForm').form('validate');
		if(b){
			$('#addEmpForm').submit();
		}
	}
	function editEmp(){
		var b = $('#editEmpForm').form('validate');
		if(b){
			$('#editEmpForm').submit();
		}
	}
	
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对雇员进行添加或者修改" id="addEmpWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="javascript:addEmp()" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addEmpForm" method="post" action="${pageContext.request.contextPath}/emp/add">
				<table class="table-edit" width="90%" align="center">
					<tr class="title">
						<td colspan="2">雇员信息</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="ename" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
				        <td >性别：</td>
				        <td style="text-align:left">
				            <span class="radioSpan">
				                <input type="radio" name="gender" value="1" checked/>男
				                <input type="radio" name="gender" value="0"/>女
				            </span>
				        </td>
				    </tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="cellphone" class="easyui-validatebox" required="true"
							 data-options="validType:'phoneNumber'"
						/></td>
					</tr>
					<tr>
						<td>住址</td>
						<td><input type="text" name="address" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>生日</td>
						<td><input id="birthday" name="birthday" type="text" class="easyui-datebox" required="required"></input></td>
					</tr>
					<tr>
						<td>基本工资</td>
						<td><input type="text" name="salary" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr style="border:2px,solid;">
		          		<td>选择雇员组:</td>
		          		<td>
		           		<%-- <input type="text" name="gid" required="true" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/empGroup/list',valueField:'gid',textField:'gname'"> --%>
			            	<input id="groupid" name="gid">
			        		<script type="text/javascript">
				        		$('#groupid').combobox({ 
				        			url:'${pageContext.request.contextPath}/empGroup/list', 
				        			valueField:'gid', 
				        			textField:'gname'
				        			
				        		});
			        		</script>
			           	</td>
			        </tr>
			        <tr>
		           		<td>选择角色:</td>
		           		<td id="checkbox">
			           		<script type="text/javascript">
			           			/* var url = "${pageContext.request.contextPath}/role/list";
			           			$.post(url,{},function(data){
			           				var rows = data.rows ;
				           			for(var i=0;i<rows.length;i++){
				           				var id = rows[i].id;
					           			var name = rows[i].name;
			           					$('#checkbox').append('<input type="checkbox" id="roleIds" name="roleIds" value="'+id+'"/>'+name);
				           			}
			           			},'json'); */
			           		</script>
			           	</td>
		       		</tr>
				</table>
				
			</form>
		</div>
	</div>
	
	
	<div class="easyui-window" title="对雇员进行修改" id="editEmpWindow" collapsible="false" 
	minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-edit" href="javascript:editEmp()" class="easyui-linkbutton" plain="true" >修改</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editEmpForm" method="post" action="${pageContext.request.contextPath}/emp/update">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">雇员信息
						<input type="hidden" name="eid" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="ename" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
				        <td >性别：</td>
				        <td style="text-align:left">
				            <span class="radioSpan">
				                <input type="radio" name="gender" value="1" checked/>男
				                <input type="radio" name="gender" value="0"/>女
				            </span>
				        </td>
				    </tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="cellphone" class="easyui-validatebox" required="true"
							 data-options="validType:'phoneNumber'"
						/></td>
					</tr>
					<tr>
						<td>住址</td>
						<td><input type="text" name="address" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>生日</td>
						<td><input id="formatBirthday" name="birthday" type="text" class="easyui-datebox"></input></td>
					</tr>
					<tr>
						<td>基本工资</td>
						<td><input type="text" name="salary" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
		          		<td >选择雇员组:</td>
		          		<td >
		           		<%-- <input type="text" name="gid" required="true" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/empGroup/list',valueField:'gid',textField:'gname'"> --%>
			            	<input id="editgroupid" name="gid">
			        		<script type="text/javascript">
				        		$('#editgroupid').combobox({ 
				        			url:'${pageContext.request.contextPath}/empGroup/list', 
				        			valueField:'gid', 
				        			textField:'gname'
				        			
				        		});
				      
			        		</script>
			           	</td>
			        </tr>
			        <tr>
		           		<td>修改角色:</td>
		           		<td id="checkbox2">
			           		<script type="text/javascript">
			           			var url = "${pageContext.request.contextPath}/role/list";
			           			$.post(url,{},function(data){
			           				var rows = data.rows ;
				           			for(var i=0;i<rows.length;i++){
				           				var id = rows[i].id;
					           			var name = rows[i].name;
			           					$('#checkbox').append('<input type="checkbox" id="roleIds" name="roleIds" value="'+id+'"/>'+name);
			           					$('#checkbox2').append('<input type="checkbox" id="'+id+'" name="roleIds" value="'+id+'"/>'+name);
				           			}
			           			},'json');
			           			
			           		</script>
			           	</td>
		       		</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>	