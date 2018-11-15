<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品后台管理系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zTree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>

<script type="text/javascript">
	$(function(){
		var setting1 = {
			data: {
				simpleData: {
					enable: true //启动简单json数据描述节点数据
				}
			},
			//动态添加选项卡
			callback: {
				//绑定事件
				onClick : onClick
				/* onClick: function(a,b,treeNode){
					//判断中部区域tt是否有这个先选卡存在
					var b = $('#tt').tabs('exists',treeNode.name);
					if(!b){	//如果没有，就添加
						//取得json数据的page属性
						var page = treeNode.page ;
						if(page != undefined){	//父级目录没有page属性，不用打开	
							//添加选项卡
							$('#tt').tabs('add', {
								title:treeNode.name,
								content:'<iframe frameborder="0" width="100%" height="100%" src="'+page+'">',
								closable:true,
								iconCls:'icon-edit'
							})
						}
					}else{  //如果已经有了，选中这个选项卡
						$('#tt').tabs('select',treeNode.name);
						
					}
				} */
			}
		};//ztree相关属性
		var url = "${pageContext.request.contextPath}/function/showMenu" ;
		//发送ajax请求，取得json数据，构造ztree
		$.get(url,{},function(data){
			//创建ztree
			$.fn.zTree.init($("#baseMenu"),setting1,data);
		},'json');
		var url = "${pageContext.request.contextPath}/json/admin.json" ;
		//发送ajax请求，取得json数据，构造ztree
		$.get(url,{},function(data){
			//创建ztree
			$.fn.zTree.init($("#adminMenu"),setting1,data);
		},'json');
	});	
	// 退出登录
	function logoutFun() {
		$.messager
		.confirm('系统提示','您确定要退出本次登录吗?',function(isConfirm) {
			if (isConfirm) {
				location.href = '${pageContext.request.contextPath }/emp/logout';
			}
		});
	}
	// 修改密码
	function editPassword() {
		$('#editPwdWindow').window('open');
	}
</script>
<script type="text/javascript">
/* $(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
			
			
		}
	});
}); */
	function onClick(event, treeId, treeNode, clickFlag) {
		// 判断树菜单节点是否含有 page属性
		if (treeNode.page!=undefined && treeNode.page!= "") {
			var content = '<div style="width:100%;height:100%;overflow:hidden;">'
					+ '<iframe src="'
					+ treeNode.page
					+ '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>';
			if ($("#tt").tabs('exists', treeNode.name)) {// 判断tab是否存在
				$('#tt').tabs('select', treeNode.name); // 切换tab
				var tab = $('#tt').tabs('getSelected'); 
				$('#tt').tabs('update', {
				    tab: tab,
				    options: {
				        title: treeNode.name,
				        content: content
				    }
				});
			} else {
				// 开启一个新的tab页面
				$('#tt').tabs('add', {
					title : treeNode.name,
					content : content,
					closable : true
				});
			}
		}
	}
	
	/*验证密码两次输入是否一致*/
	function checkPassword(){
		var newPass = $("#txtNewPass").val();
		var RePass = $("#txtRePass").val();
		if(!/^[\w_-]{6,16}$/.test(newPass)){
			$("#errorPass").text("密码格式不合法！");
			return false ;
		}
		if(newPass == RePass){
			$("#errorPass").text("");
			return true ;
		}else{
			$("#errorPass").text("两次密码输入不一致！");
			return false ;
		}
	}
	
	function doSubmitEditPasswordForm(){
		alert(checkPassword())
		if(checkPassword()){
			var url = "${pageContext.request.contextPath}/emp/updatePassword" ;
			var pass = $("#editPasswordForm").serialize();
			$.post(url,pass,function(data){
				if(data.status == 200){
					$('#editPwdWindow').window('close');
					$.messager.alert("密码修改提示","密码修改成功！新密码将在下次登录生效","info");
				}else{
					$('#editPwdWindow').window('close');
					$.messager.alert("密码修改提示",data.msg,"warning");
				}
			},"json");
		}
	}
</script>
</head>
<body class="easyui-layout">
	<!-- 使用div指定区域 -->
	<div data-options="region:'north',border:false"
		style="height:80px;padding:10px;background:url('./images/header_bg.png') no-repeat left;">
		<div id="sessionInfoDiv"
			style="position: absolute;right: 5px;top:10px;">
			[<strong>${loginUser.ename}</strong>]，欢迎你！
		</div>
		<div style="position: absolute; right: 5px; bottom: 10px; ">
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
		</div>
		<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
			<div onclick="editPassword();">修改密码</div>
			<div onclick="showAbout();">联系管理员</div>
			<div class="menu-sep"></div>
			<div onclick="logoutFun();">退出系统</div>
		</div>
	</div>
	<div title="功能管理" data-options="region:'west'" style="width:200px">
		<!-- 折叠面板效果 fit:true 填充父容器 -->
		<div class="easyui-accordion" data-options="fit:true">
			<div title="基本功能">
				<!-- 展开树形菜单，使用标准json数据构造 -->
				<ul id="baseMenu" class="ztree"></ul>			
			</div>
			<div title="系统管理">
				<!-- 展开树形菜单，使用简单json数据构造 -->
				<ul id="adminMenu" class="ztree"></ul>
			</div> 
		</div>
		
	
		
	</div>
	<div data-options="region:'center'">
		<!-- 选项卡面板效果 fit:true 填充父容器 -->
		<div id="tt" class="easyui-tabs" data-options="fit:true"  >
			<!-- <div data-options="closable:true,iconCls:'icon-add'" title="面板一"></div>
			<div data-options="iconCls:'icon-edit'" title="面板二"></div>
			<div data-options="iconCls:'icon-help'" title="面版三"></div>  -->
		</div>
	</div>
	<div data-options="region:'south'" style="height:35px;background:url('./images/foot_bg.png') no-repeat left;"></div>
	<!--修改密码窗口-->
    <div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 160px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
              <form id="editPasswordForm"onsubmit="return false;"> 
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" name="password" type="Password" class="txt01 easyui-validatebox" 
                        	required="true" data-options="validType:'length[4,9]'"
                        /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" name="rePassword" type="Password" class="txt01 easyui-validatebox" 
                        	required="true" onBlur="checkPassword();" data-options="validType:'length[4,9]'"
                        /></td>
                    </tr>
                </table>
              </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
               	<span id="errorPass" style="color:red"></span>
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="doSubmitEditPasswordForm()">确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
</body>
</html>