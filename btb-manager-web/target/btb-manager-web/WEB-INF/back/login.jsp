<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一款基于JQuery做的动画背景后台管理登录模板 - </title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fun.base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function checkUserName(){
		var name = $("#name").val();
		if(name == "" || name == null){
			$("#errormsg").text("用户名不能为空！");
			return false;
		}else{
			$("#errormsg").text("");
			return true;
		}

	}
	function checkPassword(){
		var password = $("#password").val();
		if(password == "" || password == null){
			$("#errormsg").text("密码输入不合法！");
			return false;
		}else{
			$("#errormsg").text("");
			return true;
		}
	}
	function checkCaptcha(){
		var captcha = $("#captcha").val();
		if(captcha == "" || captcha == null){
			$("#errormsg").text("请输入验证码！");
			return false;
		}else{
			$("#errormsg").text("");
			return true;
		}
	}
	function checkForm(){
		return checkUserName()&&checkPassword()&&checkCaptcha();
	}
</script>
</head>
<body>


<div class="login">
    <div class="box png">
		<div class="logo png"></div>
		<div class="input">
			<div class="log">
				<form action="${pageContext.request.contextPath}/emp/login" method="post" onsubmit="return checkForm()">			
				<div class="name">
					<label>用户名</label><input type="text" id="name" name="name" class="text" id="value_1" placeholder="用户名" tabindex="1" onblur="checkUserName()">
				</div>
				<div class="pwd">
					<label>密　码</label><input type="password" id="password" name="password" class="text" id="value_2" placeholder="密码" tabindex="2" onblur="checkPassword()">
				</div>
				<div class="captcha">
					<label>验证码</label><input type="text" class="captText" name="captcha" id="captcha" maxlength="4" size="4" placeholder="验证码" tabindex="2" onblur="checkCaptcha()">
					<img src="${pageContext.request.contextPath}/captcha;" class="captchaImg" id="captchaImg" 
					onclick="javascript:document.getElementById('captchaImg').src='${pageContext.request.contextPath}/captcha?'+Math.random();"/>
					<br />					
					
				</div>
				<div>
					<input type="submit" class="submit" tabindex="3" value="登录">
					<div class="check"></div>
					<span id="errormsg" style="color:red;float:right">${errormsg}</span>
				</div>
				</form>		
				<div class="tip"></div>
			</div>
		</div>
	</div>
    <div class="air-balloon ab-1 png"></div>
	<div class="air-balloon ab-2 png"></div>
    <div class="footer"></div>
</div>



<!--[if IE 6]>
<script src="js/DD_belatedPNG.js" type="text/javascript"></script>
<script>DD_belatedPNG.fix('.png')</script>
<![endif]-->
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>
</body>
</html>