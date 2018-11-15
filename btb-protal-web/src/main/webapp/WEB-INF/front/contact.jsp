<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Contact</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary JavaScript plugins) -->
<script type='text/javascript' src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<!-- Custom Theme files -->
<link href="${pageContext.request.contextPath}/css/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Medical_Equipment Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- start menu -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/menu_jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/simpleCart.min.js"> </script>
<script src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
<script>
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,
      	nav: true,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });
    });
	
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/move-top.js"></script>
       <script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
				});
			});
		</script>
		<script type="text/javascript">
		$(document).ready(function() {
				/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
				*/
		$().UItoTop({ easingType: 'easeOutQuart' });
});
		
function goItem(itemId){
	window.location.href = "${pageContext.request.contextPath}/item/"+itemId+'.html?rand='+Math.random() ;
}
</script>
<style type="text/css">
	.custStyle{
		background:transparent;
		border:0px;
		color:red;
	}

</style>

</head>
<body>
<!-- header -->
<div class="header_bg">
<div class="container">
	<div class="header">
	<div class="head-t">
		<div class="logo">
			<a href="index.html"><img src="${pageContext.request.contextPath}/images/logo.png" class="img-responsive" alt=""/> </a>
		</div>
		<!-- start header_right -->
		<div class="header_right">
			<div class="rgt-bottom">
				<li><a href="${pageContext.request.contextPath}/index.html">首页</a></li>
				<li><a href="${pageContext.request.contextPath}/inventory/show.html">订货清单</a></li>
				<li><a href="http://58.49.234.128:8090">后台管理系统</a></li>
				<li a="" href="#">
					<div class="drop-down">
							<select class="d-arrow">
								<option onclick="login();">${loginUser.username!=null?loginUser.username:"登录"}</option>
								<option onclick="logout();">注销</option>
								<option >修改密码</option>
							</select>
					</div>
				</li>
				<li a="" href="#">
					<div class="drop-down">
							<select class="d-arrow">
								<option value="">中文</option>
								<option value="">English</option>
							</select>
					</div>
				</li>				
			<div class="clearfix"> </div>
		</div>
		<div class="se-ca">
			<!-- 商品搜索 -->
			<div class="search">
				<form action="${pageContext.request.contextPath}/search.html">
					<input name="q" type="text" value="" placeholder="搜索商品...">
					<input type="submit" value="">
				</form>
			</div>
			<!-- 购物车 -->
			<div class="cart box_1">
					<a href="${pageContext.request.contextPath}/inventory/show.html">
						<h3>订货清单   <span class="simpleCart_total"></span>  <img src="images/bag.png" alt=""></h3>
					</a>	
					<p><a href="javascript:;" class="simpleCart_empty">(empty card)</a></p>
					<div class="clearfix"> </div>
				</div>
			<div class="clearfix"> </div>
		</div>
		</div>
			<div class="clearfix"> </div>
	</div>	
	
		<!-- start header menu -->
		<div class="header-top">
			<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      	<li class="active"><a href="${pageContext.request.contextPath}/search.html?page=1" class="">全部</a></li>
      	<c:if test="${categroyList != null}">
      	<c:forEach items="${categroyList}" var="categroy" begin="0" end="4">
        <li class="active"><a href="${pageContext.request.contextPath}/item/category.html?page=1&cid=${categroy.cid}">${categroy.name} </a></li>
        </c:forEach>
        </c:if>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">其他 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <c:if test="${categroyList != null}">
	      	<c:forEach items="${categroyList}" var="categroy" begin="5">
	       		<li><a href="${pageContext.request.contextPath}/item/category.html?page=1&cid=${categroy.cid}">${categroy.name} </a></li>
	        </c:forEach>
	        </c:if>
          </ul>
        </li>
      </ul>
      
    
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
		</div>
		<!-- start header menu -->
	</div>
</div>
</div>
<!-- start banner -->
<div class="main">
      <div class="contact_top">
		<div class="container">
			
			<form method="post" action="${pageContext.request.contextPath}/order/save.html">
			
			<div class="row">
				<div class="col-md-7 map">
				  <div class="map">
					 <h1>&nbsp;货物清单(${inventoryList != null?inventoryList.size():0})</h1>
			<c:if test="${inventoryList != null}"> 
			<c:forEach items="${inventoryList}" var="item" varStatus="itemIndex">
			 <div class="cart-header_${item.id}">
				 <div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							 <img src="${item.image}" onclick="goItem(${item.id});" class="img-responsive" alt=""/>
						 	 <input type="hidden" name="orderDetailsList[${itemIndex.index}].image" value="${item.image}"/>
						</div>
					   <div class="cart-item-info">
						<h3><a href="javascript:void(0);" onclick="goItem(${item.id});">${item.title}</a><span>价格: ${item.price/100}</span></h3>
						<input type="hidden" name="orderDetailsList[${itemIndex.index}].itemtitle" value="${item.title}"/>
						<ul class="qty">
							<li><p>大小 :${item.size} </p></li>
							<li><p>订购数量 : ${item.num}
							<input type="hidden" name="orderDetailsList[${itemIndex.index}].itemnum" value="${item.num}"/>
							<!-- 实际保存以分为单位 -->	  
				 			<input type="hidden" name="orderDetailsList[${itemIndex.index}].price" value="${item.price}"/>
							</p></li>
						</ul>	<!-- 用来记录商品的id 每个商品的id需要不一样 -->
						 		<input name="orderDetailsList[${itemIndex.index}].itemid" id="itemId${item.id}" type="hidden" value="${item.id}">
							 <div class="delivery">
							 <p>规格：${item.specification}&emsp;&emsp;</p>
							 <p>品牌:${item.brand}</p>
							<span> </span>
							 <div class="clearfix"></div>
				        </div>
					   </div>
					   <div class="clearfix"></div>
				  </div>
			 </div>
			
			 </c:forEach>
			</c:if>

				  </div>
				</div>
				<div class="col-md-5 tes">
					<p class="m_8" style="text-indent:2em;">Ly饮料采购平台致力于为私人商户提供饮料酒水等货物的出售，本品台用户仅面向公司附近的零售店铺，该系统由本人开发，采用的后端技术主要有Spring+SpringMVC+Mybatis+Shiro+Activiti+Redis；后台页面技术使用了Jquery EasyUI、zTree控件，ajax等 。如果您觉得这个Demo不错，可以联系我QQ963172090</p>
					<div class="address">
				                <p>商品总价：<input readonly class="custStyle" value="${totalPrice/100}"> 
				                <input name="payment" type="hidden" value="${totalPrice}"> 
				                </p>
				                <p>支付类型：
				                	<input name="paymentType" type="radio" value="1" checked>月底批结
					              	<input name="paymentType" type="radio" value="2">货到支付 
				                 	<input name="userid" type="hidden" value="${loginUser.uid}">
				                </p>
						   		<p>商品总件数：${itemCount}</p>
				   		<p>服务电话 : 18672768396</p>
				 	 	<p>服务邮箱 : <a href="mailto:info@example.com">963172090@qq.com</a></p>
				   </div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 contact">
				  
					<div class="to">
                     	<input type="text" name="username" class="text" value="${loginUser.username}" onblur="if (this.value == '') {this.value = '姓名';}">
			 			<input type="text" name="cellphone" class="text" value="${loginUser.cellphone}" onblur="if (this.value == '') {this.value = '手机号';}">
						<input type="text" name="receiver" class="text" value="${loginUser.address}" onblur="if (this.value == '') {this.value = '收货地址';}">
					</div>
					<div class="text">
	                   <textarea name="remark"  onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '备注';}">备注:</textarea>
	                   <div class="form-submit">
			           <input type="submit" id="submit" value="提交订单"><br>
			           </div>
	                </div>
	                <div class="clear"></div>
			     </div>
		    </div>
            </form>
            
	     </div>
	   </div>
	  </div>


<div class="footer">
	<div class="container">
		<div class="col-md-3 cust">
			<h4>CUSTOMER CARE</h4>
				<li><a href="#">Help Center</a></li>
				<li><a href="#">FAQ</a></li>
				<li><a href="buy.html">How To Buy</a></li>
				<li><a href="#">Delivery</a></li>
		</div>
		<div class="col-md-3 abt">
			<h4>ABOUT US</h4>
				<li><a href="#">Our Stories</a></li>
				<li><a href="#">Press</a></li>
				<li><a href="#">Career</a></li>
				<li><a href="contact.html">Contact</a></li>
		</div>
		<div class="col-md-3 myac">
			<h4>MY ACCOUNT</h4>
				<li><a href="register.html">Register</a></li>
				<li><a href="#">My Cart</a></li>
				<li><a href="#">Order History</a></li>
				<li><a href="buy.html">Payment</a></li>
		</div>
		<div class="col-md-3 our-st">
				<h4>OUR STORE</h4>
				<li><i class="add"> </i>lorem ipsum</li>
				<li><i class="phone"> </i>18672768396</li>
				<li><a href="mailto:info@example.com"><i class="mail"> </i>963172090@qq.com </a></li>
			
		</div>
		<div class="clearfix"> </div>
			<p>Copyright &copy; 2018.Company name All rights reserved<a href="#" target="_blank" title="#"></a> - Collect from <a href="#" title="" target="_blank"></a></p>
	</div>
</div>
<a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 0;"></span> <span id="toTopHover" style="opacity: 0;"> </span></a>
</body>
</html>