<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js//jquery-easyui-1.4.1/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js//jquery-easyui-1.4.1/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js//jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js//jquery-easyui-1.4.1//ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<style type="text/css">
	.titleStyle{
		font-size:25px;
		text-align: center;
		color: olive;
		padding:5px;
	}
	.title2Style{
		font-size:21px;
		text-align: left;
		color: olive;
		padding:5px;
	}
</style>
</head>
<body class="easyui-layout">
  	 <div>
  		<div class="titleStyle">
  			<h1>订单信息</h1>
  		</div>
   	 	<table id="grid1" class="easyui-datagrid">
   	  	<thead>
  			<tr>
  				<th data-options="field:'oid'" width="60">订单编号</th>
  				<th data-options="field:'username'" width="70">收货人</th>
  				<th data-options="field:'cellphone'" width="100">联系电话</th>
  				<th data-options="field:'address'" width="100">收货地址</th>
  				<th data-options="field:'payment'" width="80">支付总金额</th>
  				<th data-options="field:'created',formatter:TT.formatDateTime" width="150">创建时间</th>
  				<th data-options="field:'remark'" width="250">备注</th>
  			</tr>
  		</thead>
  		<tbody>
  			<c:if test="${orders != null}">
  				<tr>
  					<td>${orders.oid}</td>
  					<td>${orders.username}</td>
  					<td>${orders.cellphone}</td>
  					<td>${orders.receiver}</td>
  					<td>${orders.payment/100}</td>
  					<td>${orders.createTime}</td>
  					<td>${orders.remark}</td>
  				</tr>
  			</c:if>
  		</tbody>
   	  </table>
   </div>
  <div >
 	<div class="title2Style"><h3>订单详细</h3></div>
   	  <table id="grid2" class="easyui-datagrid">
   	  	<thead>
  			<tr>
  				<th data-options="field:'itemid'" width="130">商品编号</th>
  				<th data-options="field:'itemtitle'" width="350">商品标题</th>
  				<th data-options="field:'price'" width="100">单价</th>
  				<th data-options="field:'itemnum'" width="100">数量</th>
  				<th data-options="field:'totalfee'" width="120">总价</th>
  			</tr>
  		</thead>
  		<tbody>
  			<c:if test="${orderDetailsList != null}">
  			<c:forEach items="${orderDetailsList}" var="orderDatails">
  				<tr>
  					<td>${orderDatails.itemid}</td>
  					<td>${orderDatails.itemtitle}</td>
  					<td>${orderDatails.price/100}</td>
  					<td>${orderDatails.itemnum}</td>
  					<td>${orderDatails.totalfee/100}</td>
  				</tr>
  			</c:forEach>
  			</c:if>
  		</tbody>
   	  </table>
   </div>
</body>
</html>