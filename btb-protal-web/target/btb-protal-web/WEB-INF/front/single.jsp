<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Single</title>
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
	//图片轮播插件
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,		//自动播放 
      	nav: true,	//是否显示左翻右翻箭头
      	speed: 500,	//持续毫秒
        namespace: "callbacks",	//修改默认容器名称
        pager: true,	//是否显示页码
      });
    });
	
  </script>
<script src="${pageContext.request.contextPath}/js/easyResponsiveTabs.js" type="text/javascript"></script>
		<script type="text/javascript">
		    $(document).ready(function () {
		        $('#horizontalTab').easyResponsiveTabs({
		            type: 'default', //Types: default, vertical, accordion           
		            width: 'auto', //auto or any width like 600px
		            fit: true   // 100% fit in a container
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
</script>
<script type="text/javascript">
	function addInventory(){
		var url = "${pageContext.request.contextPath}/inventory/add.action" ;
		$.post(url,$('#addInventoryForm').serialize(),function(data){
			if(data.status == 200){
				if(window.confirm("添加成功，现在进入订货清单？")){
					window.location.href = "${pageContext.request.contextPath}/inventory/show.html"
				};
			}else if(data.status == 400){
				alert(data.msg);
			}
			
			
		},"json");
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
			<a href="${pageContext.request.contextPath}/index.html"><img src="${pageContext.request.contextPath}/images/logo.png" class="img-responsive" alt=""/> </a>
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
<!--initiate accordion-->
		<script type="text/javascript">
			$(function() {
			    var menu_ul = $('.menu > li > ul'),
			           menu_a  = $('.menu > li > a');
			    menu_ul.hide();
			    menu_a.click(function(e) {
			        e.preventDefault();
			        if(!$(this).hasClass('active')) {
			            menu_a.removeClass('active');
			            menu_ul.filter(':visible').slideUp('normal');
			            $(this).addClass('active').next().stop(true,true).slideDown('normal');
			        } else {
			            $(this).removeClass('active');
			            $(this).next().stop(true,true).slideUp('normal');
			        }
			    });
			
			});
		</script>

<div class="single">
<div class="container">
<div class="content span_1_of_2">				
		
		
		
		<!-- 大图3轮播 -->
		<div class="grid images_3_of_2">
			<div class="callbacks_container"> <!-- callbacks_container -->
			  	<ul class="rslides-item" id="slider">
					<c:forEach items="${item.images}" var="pic">
					<li>
						<img src="${pic}" class="img-responsive" alt="">
					</li>
					</c:forEach>
				</ul>
	  		</div>
  		</div>
					
					
					
		<div class="desc span_3_of_2">
					<h3>${item.title}</h3>
				<div class="desc">
	                <span class="brand">品牌:&emsp;&emsp;&emsp;&emsp;&emsp;<a href="#">${item.brand}</a></span><br>
	                <span class="brand">大小：&emsp;&emsp;&emsp;&emsp;${item.size}</span><br>
	                <span class="brand">建议零售价：&emsp;&nbsp;${item.retail/100}</span><br>
	                <span class="brand">剩余库存：&emsp;&emsp;${item.num}</span><br>
                	<div class="padd-stock">
                		<div class="extra-wrap"> 
                			<span class="prod-stock-2"></span>
                			<div class="prod-stock">${item.promote==1?"促销":""}</div>
                		</div>
                	</div>
           		<div class="price">
        			<span class="text">售价:</span>
                	<span class="price-old">$100.00</span><span class="price-new">${item.price/100}</span> 
                    <span class="price-tax"><!-- Ex Tax: $90.00 --></span><br>
<!--                         <span class="points"><small>Price in reward points: 400</small></span><br> -->
                </div>
				<div class="single-cart">
			        <div class="prod-row">
			          	<div class="cart-top">
							<form onsubmit="return false" id="addInventoryForm">
							<div class="cart-top-padd">
			                	<label>购买件数:</label>
			                	<input type="text" name="quantity" size="2" value="1">
			                	<input type="hidden" name="itemId" size="2" value="${item.id}">
			             	</div>
						  	
								<input type="submit" onclick="addInventory()" value="加入清单" class="button6">
						  	</form>
			          	</div>
        			</div>
        		</div>
          </div>
	</div>
	<div class="clearfix"> </div>
		<div class="sap_tabs">	
		     <div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
				  <ul class="resp-tabs-list">
				  	  <li class="resp-tab-item resp-tab-active" aria-controls="tab_item-0" role="tab"><span>商品详情</span></li>
					  <li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span>规格</span></li>
					  <li class="resp-tab-item" aria-controls="tab_item-2" role="tab"><span>评价</span></li>
					  <div class="clear"></div>
				  </ul>				  	 
					<div class="resp-tabs-container">
					    <h2 class="resp-accordion" role="tab" aria-controls="tab_item-0"><span class="resp-arrow"></span> Description</h2>
					    <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
							<div class="facts">
							  ${itemDesc.description}          
					        </div>
					</div>	
					     <h2 class="resp-accordion" role="tab" aria-controls="tab_item-1"><span class="resp-arrow"></span>Information</h2><div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
							<div class="facts">
							  <ul class="tab_list">
							    <li>${item.specification} </li>
							  </ul>           
					        </div>
					     	</div>	
					     <h2 class="resp-accordion" role="tab" aria-controls="tab_item-2"><span class="resp-arrow"></span>Reviews</h2><div class="tab-1 resp-tab-content" aria-labelledby="tab_item-2">
					 <ul class="tab_list">
					<li data-content="television" class="selected">
						<div class="comments-top-top">
							<div class="top-comment-left">
								<img class="img-responsive" src="images/co.png" alt="">
							</div>
							<div class="top-comment-right">
								<h6><a href="#">Hendri</a> - September 3, 2014</h6>
								<ul class="star-footer">
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
								</ul>
								<p>Wow nice!</p>
							</div>
								<div class="clearfix"> </div>
							<a class="add-re" href="#">ADD REVIEW</a>
						</div>
					</li>
							  </ul>      
					     </div>	
					</div>
			      </div>
			 </div>
		</div>	
		<div class="clearfix"> 
			</div><div class="row shop_box-top">
		</div>
				
</div>
</div>	
<div class="foot-top">
	
		
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
			<p>Copyright &copy; 2018.Company name All rights reserved. <a href="" target="_blank" title=""></a> <a href="" title="" target="_blank">.</a></p>
	</div>
</div>
<a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 0;"></span> <span id="toTopHover" style="opacity: 0;"> </span></a>
</body>
</html>