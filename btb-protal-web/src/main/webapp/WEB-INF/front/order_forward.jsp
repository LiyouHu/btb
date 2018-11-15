<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/index.html">
<title>订单提示页</title>
</head>
<body>
	<h3>您的订单${orderMsg}，系统将在3秒后跳转到首页,如果没有跳转请点击<a href="${pageContext.request.contextPath}/index.html">这里</a>!</h3>
</body>
</html>