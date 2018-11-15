<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Checkout</title>
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
<script src="${pageContext.request.contextPath}/js/simpleCart.min.js"> </script>
<script src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
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
    function goItem(itemId){
		window.location.href = "${pageContext.request.contextPath}/item/"+itemId+'.html?rand='+Math.random() ;
	}
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
</script>
<style type="text/css">
	.update_style{
		margin: 0px;
		padding:25px 40px 40px 40px;
		height:auto;
		text-decoration:none;
		padding:5px 12px;
		margin:2px;
		background:transparent;		
		border: 1px solid lightgray;
		color: #47B3F2;
	}
	.update_style:hover{
		background: #03358F;
		color: #ffffff;
	}
	.numStyle{
		border:1px solid #47B3F2;
		background-color:#F2F9FE;
		width:25px;
	}
</style>
<script type="text/javascript">
	function updateInventory(itemid,itemNum){
		var id = $("#"+itemid+"").val();
		var num = $("#"+itemNum+"").val();
		var url = "${pageContext.request.contextPath}/inventory/update.action"
		$.post(url,{itemId:id,quantity:num},function(data){
			if(data.status == 200){
				//alert("修改成功！");
			}else{
				alert(data.msg);
			}
		},'json')
	}
	function closeDiv(id){
		var url = "${pageContext.request.contextPath}/inventory/delete.action";
		$.post(url,{itemId:id},function(data){
			if(data.status == 200){
				$('.cart-header_'+id+'').fadeOut('slow', function(c){
					$('.cart-header_'+id+'').remove();
				});
			}else{
				alert('删除失败！');
			}
		},'json');	
	}
	function changeSumPrice(price,id){	//价格和数量input的id
		//var num = $('#'+numId+'').val() ;
		var totalPrice = $('#totalPrice').val();	//得到修改前总的价格
		var oldNum = $("#oldNum"+id+"").val();	//得到修改前被修改商品的数量
		var nowNum = $("#itemNum"+id+"").val();	//得到修改后的数量
		//最终价格=原总价 + 增加商品的总价
		var finalPrice = parseFloat(totalPrice)+parseFloat((nowNum-oldNum)*price) ;
		$("span[class^='showPrice']").text("￥: "+finalPrice+"");	//跟新总的价格
		$("#oldNum"+id+"").val(nowNum);	//更新商品数量
		$('#totalPrice').val(finalPrice);
	}
	function changePrice2(price,id){	//价格和数量input的id
		//var num = $('#'+numId+'').val() ;
		var totalPrice = $('#totalPrice').val();	//得到修改前总的价格
		var oldNum = $("#oldNum"+id+"").val();	//得到修改前被修改商品的数量
		//最终价格=原总价 + 增加商品的总价
		var finalPrice = parseFloat(totalPrice)-parseFloat(oldNum*price) ;
		$("span[class^='showPrice']").text("￥: "+finalPrice+"");	//跟新总的价格
		$('#totalPrice').val(finalPrice);
	}
	function login(){
		window.location.href = "${pageContext.request.contextPath}/login.html";
	}
	function logout(){
		var url = "${pageContext.request.contextPath}/user/logout.action";
		$.post(url,{},function(data){
			if(data.status == 200){
				alert("注销成功！");
				window.location.href = "${pageContext.request.contextPath}/index.html"
			}
		},'json'); 
	}
</script>
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
						<h3>订货清单   <span class="simpleCart_total"></span>  <img src="${pageContext.request.contextPath}/images/bag.png" alt=""></h3>
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
<div class="check">	 
<div class="container">
	
			 <div class="col-md-3 cart-total">
			 <a class="continue" href="${pageContext.request.contextPath}/index.html">继续采购</a>
			 <div class="price-details">
				 <h3>价格详细</h3>
				 <span>总价</span>
				 <span class="showPrice1">￥：${totalPrice/100}</span>
				 <input type="hidden" id="totalPrice" value="${totalPrice/100}">
				 <span>折扣</span>
				 <span class="total1">---</span>
				 <span>运费</span>
				 <span class="total1">0</span>
				 <div class="clearfix"></div>				 
			 </div>	
			 <ul class="total_price">
			   <li class="last_price"> <h4>总计</h4></li>	
			   <li class="last_price"><span class="showPrice2">￥：${totalPrice/100}</span></li>
			   <div class="clearfix"> </div>
			 </ul>
			
			 
			 <div class="clearfix"></div>
			 <a class="order" href="${pageContext.request.contextPath}/inventory/commit.action">结算订单</a>
			 <div class="total-item">
				 <h3>选项</h3>
				 <h4>优惠券</h4>
				 <a class="cpns" href="#">使用优惠券</a>
				 <p><a href="javascript:void(0);" onclick="login();">登录</a> to use accounts - linked coupons</p>
			 </div>
			</div>
		 <div class="col-md-9 cart-items">
			 <h1>&nbsp;货物清单(${inventoryList != null?inventoryList.size():0})</h1>
				
		<c:if test="${inventoryList != null}"> 
			<c:forEach items="${inventoryList}" var="item">
		
			 <div class="cart-header_${item.id}">
				 <div class="close_${item.id}" onclick="closeDiv(${item.id});changePrice2(${item.price/100},${item.id});"> </div>
				 <div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							 <img src="${item.image}" onclick="goItem(${item.id});" class="img-responsive" alt=""/>
						</div>
					   <div class="cart-item-info">
						<h3><a href="javascript:void(0);" onclick="goItem(${item.id});">${item.title}</a><span>价格: ${item.price/100}</span></h3>
						<ul class="qty">
							<li><p>大小 :${item.size} </p></li>
							
							<li><p>订购数量 : <input id="itemNum${item.id}" onblur="updateInventory('itemId${item.id}','itemNum${item.id}');changeSumPrice(${item.price/100},${item.id});" class="numStyle" type="text" value="${item.num}"/>
							<!-- 原来的数量 用来根新总价 --><input type="hidden" id="oldNum${item.id}" value="${item.num}"/>
							</p></li>
						</ul>	<!-- 用来记录商品的id 每个商品的id需要不一样 -->
								<input id="itemId${item.id}" type="hidden" value="${item.id}">
							 <div class="delivery">
							 <p>规格：${item.specification}&emsp;&emsp;</p>
							 <p>品牌:${item.brand}</p>
							<%-- <span> <input type="button" class="update_style" onclick="updateInventory('itemId${item.id}','itemNum${item.id}');changeSumPrice(${item.price/100},${item.id});" value="修改"></span> --%>
							 <div class="clearfix"></div>
				        </div>
					   </div>
					   <div class="clearfix"></div>
				  </div>
			 </div>
			
			 </c:forEach>
			</c:if>
		 </div>
			<div class="clearfix"> </div>
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
				<li><i class="phone"> </i>963172090</li>
				<li><a href="mailto:info@example.com"><i class="mail"> </i>963172090@qq.com</a></li>
			
		</div>
		<div class="clearfix"> </div>
			<p>Copyright &copy; 2015.Company name All rights reserved.<a href="" target="_blank" title=""></a> <a href="" title="" target="_blank"></a></p>
	</div>
</div>
<a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 0;"></span> <span id="toTopHover" style="opacity: 0;"> </span></a>
</body>
</html>