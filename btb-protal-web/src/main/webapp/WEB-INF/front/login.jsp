<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- font files  -->
<link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Nunito:400,300,700' rel='stylesheet' type='text/css'>
<!-- /font files  -->
<!-- css files -->
<link href="${pageContext.request.contextPath}/css/login_style.css" rel='stylesheet' type='text/css' media="all" />
<!-- /css files -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	function checkData(pro,type){	//data为校验的数据内容，type为校验的属性 
		//1：校验username是否存在 ，2：校验手机号是否已经被使用 ，3：校验email是否已经被使用，
		var url = "${pageContext.request.contextPath}/user/checkData.action" ;
		$.get(url,{property:pro,type:type},function(data){
			if(data.data == true){	//true 表示已经被使用
				if(type==1){
					$("#errorusername").css("display","block");
					$("#errorusername").val("用户名已经被使用！");
					$("#register").attr("disabled", true);
					$("#register").css("background-color", "#376D29");
				}else if(type == 2){
					$('#errorcellphone').css("display","block");
					$('#errorcellphone').val("手机号已经被使用！");
					$("#register").attr("disabled", true);
					$("#register").css("background-color", "#376D29");
				}else if(type == 3){
					$("#erroremail").css("display","block");	
					$("#erroremail").val("邮箱已经被使用！");
					$("#register").attr("disabled", true);
					$("#register").css("background-color", "#376D29");
				}
			}else{
				$("#register").attr("disabled", false); 
				$("#register").css("background-color", "#4C9A39");
			}
		},'json');
	}

	function changeForm(){
		$('.content2').css('display','block');
		$('.content1').css('display','none');
	}
	function changeForm2(){
		$('.content1').css('display','block');
		$('.content2').css('display','none');
	}

	function checkLoginUsername(){		
		var  username = $('#username').val();	
		if("" != username && username != null){			
			$("#errorloginusername").css("display","none");
			return true;		
		}
		$("#errorloginusername").css("display","block");
		$("#errorloginusername").val("用户名不能为空！");
		return false ;		
	}
	function checkLoginPassword(){	
		var password = $('#password').val();	
		if(/^\w{3,18}$/.test(password)){
			$("#errorloginuserpassword").css("display","none");
			return true ;
		}
		$("#errorloginuserpassword").css("display","block");
		$("#errorloginuserpassword").val("密码输入不合法！");
		return false ;
	}
	function checkUsername(){		
		var  username = $('#r_username').val();	
		if("" != username && username != null){		
			$("#errorusername").css("display","none");	
			checkData(username,1);
			return true;		
		}else{
			$("#errorusername").css("display","block");
			$("#errorusername").val("用户名不能为空！");
			return false ;
		}		
	}

	function checkPassword(){	
		var password = $('#r_password').val();	
		if(/^\w{3,18}$/.test(password)){
			$("#errorpassword").css("display","none");
			return true ;
		}
		$("#errorpassword").css("display","block");
		$("#errorpassword").val("密码输入不合法！");
		return false ;
	}

	function checkPsw(){
		var password = $('#r_password').val();
		var psw = $('#psw').val();
		if(psw == null || psw == ""){
			$("#errorpsw").css("display","block");
			$("#errorpsw").val("两次密码输入不一致！");
			return false;
		}else if(psw != password){
			$("#errorpsw").css("display","block");
			$("#errorpsw").val("两次密码输入不一致！");		
			return false;
		}
		$("#errorpsw").css("display","none");
		return true;
		

	}
	function checkCellphone(){
		var cellphone = $('#cellphone').val();
		if(/^0?(13|14|15|17|18|19)[0-9]{9}$/.test(cellphone)){
			$('#errorcellphone').css("display","none");
			checkData(cellphone,2);
			return true
		}else{
			$('#errorcellphone').css("display","block");
			$('#errorcellphone').val("手机格式不正确！");
			return false ;
		}
	}

	function checkEmail(){
		var email = $('#email').val();
		if(/^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}$/.test(email)){
			$("#erroremail").css("display","none");
			checkData(email,3);
			return true;
		}
		$("#erroremail").css("display","block");	
		$("#erroremail").val("邮箱格式不正确！");
		return false;
	}
	
	function checkAddress(){
		var address = $('#address').val();
		if("" != address && address != null){
			$('#erroraddress').css("display","none");
			return true ;
		}
		$('#erroraddress').css("display","block");
		$('#erroraddress').val("收货货地址不能为空！");
		return false ;
		
	}
	
	function checkSubmit(){
		var flag  = false;
		flag = checkPsw()&&checkEmail()&&checkUsername()&&checkAddress()&&checkCellphone();
		return flag ;
	}
	function registerFun(){
		if(checkSubmit()==false){
			return false ;
		}
		
		var url = "${pageContext.request.contextPath}/user/register.action";
		$.post(url,$('#registerForm').serialize(),function(data){
			if(data.data==true){
				alert("用户注册成功！");
				window.location.href = "${pageContext.request.contextPath}/login.html"
			}else{
				alert("用户注册失败！");
			}
		},"json");
	}
	
</script>
</head>

<body>
<h1>Ly饮料采购登录系统</h1>
<div class="log">
	<div class="content1">
		<h2>用户登录</h2>
		<form action="${pageContext.request.contextPath}/user/login.action" onsubmit="return checkLoginUsername()&&checkLoginPassword(); " method="post">
			<input type="text" id="username" name="username" value="用户名" onfocus="this.value = '';" onblur="checkLoginUsername();if(value == ''){value = '请输入用户名!'}">
			<!-- 回跳页面 -->
			<input type="hidden" name="callback" value="${callback}">
			<input id="errorloginusername" class="errrtextStyle">
			<input type="password"  id="password" name="password" value="password" onfocus="this.value = '';" onblur="checkLoginPassword();">
			<input id="errorloginuserpassword" class="errrtextStyle">
			<div class="clear"></div>
			<div class="button-row">
				<input type="submit" class="sign-in" value="登录">
				<input type="reset" class="reset" value="重置">
				<span style="color:red">${msg}</span>
				<div class="clear"></div>
			</div>
		</form>
		<input type="button" class="buttoncla" onclick="" value="忘记密码"/>
		<input type="button" class="buttoncla" onclick="changeForm();" value="用户注册"/>
	</div>
	<div class="content2">
		<h2>用户注册</h2>
		<form id="registerForm" onsubmit="return false" >
			<input type="text" id="r_username" name="username" value="用户名" onblur="checkUsername();if(value == ''){value = '请输入用户名!'}" onfocus="this.value = '';">
			<input id="errorusername" value="" class="errrtextStyle" />

			<input type="password" id="r_password" name="password" value="PASSWORD" onblur="checkPassword();" onfocus="this.value = '';">
			<input id="errorpassword" value="" class="errrtextStyle" />
			
			<input type="password" id="psw" name="psw" value="password" onblur="checkPsw();" onfocus="this.value = '';">
			<input id="errorpsw" value="" class="errrtextStyle" />

			<input type="tel" id="cellphone" name="cellphone" value="手机号" onblur="checkCellphone();if(value == ''){value = '请输入手机号！'}" onfocus="this.value = '';">
			<input id="errorcellphone" value="" class="errrtextStyle" />

			<input type="tel" id="address" name="address" value="收货地址" onblur="checkAddress();" onfocus="this.value = '';">
			<input id="erroraddress" value="" class="errrtextStyle" />

			<input type="email" id="email" name="email" value="邮箱" onblur="checkEmail();if(value == ''){value = '请输入邮箱!'}" onfocus="this.value = '';">
			<input id="erroremail" value="" class="errrtextStyle" />
			<br>
			<div class="clear"></div>
			<input type="reset" class="reset" value="重置">
			<input type="submit" onclick="registerFun()" id="register" class="register" value="注册">
		</form>
		<input type="button" class="buttonclb" onclick="changeForm2();" value="用户登录"/>
	</div>
	<div class="clear"></div>
</div>
<div class="footer">
	<p>Copyright &copy; 2018.Company name All rights .  <a href="" title="LY饮料采购" target="_blank">LY饮料采购</a></p>
</div>

</body>
</html>