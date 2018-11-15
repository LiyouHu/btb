<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Sale</title>
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
<!-- 分页栏样式 -->
<style type="text/css">
	
	.pagediv{
		margin:-2em; 
		display:inline-block;
  		height:auto;
		float: right;
		height:100%;
		color: blue;
		
	}
	.pagefooter{
		margin: 0px;
		padding:25px 40px 40px 40px;
		height:auto;
	}
	
	.pagefooter li{
		list-style:none; 
		float: left;
		display: inline-block;

	}
	.pagefooter a{
		text-decoration:none;
		padding:5px 12px;
		margin:2px;
		border: 1px solid lightgray;
		color: #47B3F2;
	}
	.pagefooter a:hover{
		background: #03358F;
		color: #ffffff;
	}
	.pagefooter a.cur{
		background: #47B3F2;
		color: #ffffff;
	}
	.span_pageNum{
		margin:5px;
		color:#47B3F2;
		
	}
	.pageNumStyle{
		border:1px solid #47B3F2;
		background-color:#F2F9FE;
		width:25px;
	}
</style>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/move-top.js"></script>
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
	
	function goItem(itemId){
		window.location.href = "${pageContext.request.contextPath}/item/"+itemId+".html?rand="+Math.random() ;
	}
	function changeImage(id,images){
		document.getElementById(id).src = images.split(',')[0] ;
	}
	
	function changeBackground(id,images){
		var pic = images.split(',')[0];
		$('#'+id+'').css('background-image','url('+pic+')');
	}
	function getPage(page){	//跳转的页码
		//搜索条件
		var q = $("input[name='q']").val();
		//类别条件
		var cid = '${cid}' ;
		if(q != null && q != ""){
			//按搜索条件翻页			
			window.location.href = "${pageContext.request.contextPath}/search.html?page="+page+"&q="+q;
		}else if(cid != null && cid != ""){
			window.location.href = "${pageContext.request.contextPath}/item/category.html?page="+page+"&cid="+cid ;
		}else{
			window.location.href = "${pageContext.request.contextPath}/search.html?page="+page ;
		}
		
	}
	function goForwardPage(){
		
		var pageNum = $("input[name='pageNum']").val();
		if(/^\d+$/.test(pageNum)){
			if(pageNum>${totalPages}){
				getPage(${totalPages});
			}else if(pageNum<1){
				getPage(1);
			}else{
				getPage(pageNum);
			}
		}
		
	}
	function addInventory(id){
		var url = "${pageContext.request.contextPath}/inventory/add.action" ;
		$.post(url,{itemId:id,quantity:1},function(data){
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
					<input name="q" type="text" value="${queryKey}" placeholder="搜索商品...">
					<input type="submit" value="">
				</form>
			</div>
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
<div class="shop_top">
		<div class="container">
			<div class="row shop_box-top">
				<c:if test="${itemList != null}">
				<c:forEach items="${itemList}" var="item">
				
				<div class="col-md-3 shop_box">
					<img id="${item.id}" onclick="goItem('${item.id}')" src="" class="img-responsive" alt="" onerror="changeImage(this.id,'${item.image}')">
					<span class="new-box">
						<span class="new-label">New</span>
					</span>
					
					</a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5>${item.title}</h5>
							<div class="item_add"><span class="item_price"><span class="reducedfrom">￥：${item.price/100+10}</span>
								<span class="actual">￥：${item.price/100}</span></span></div>
							<div class="item_add"><span class="item_price"><a href="javascript:void(0);" onclick="addInventory('${item.id}');">加入清单</a></span></div>
						</div>
				</div>
				</c:forEach>
				</c:if>
			</div>
			
	   </div>
<div class="foot-top">
	<div class="pagediv">
		<ul class="pagefooter">
			<li><a href="${pageContext.request.contextPath}/index.html" class="">首页</a></li>
			<c:if test="${page-1>0}"><!-- 第2页开始才显示上一页 -->
			<li><a href="javascript:void(0)" onclick="getPage('${page-1}')">上一页</a></li>
			</c:if>
			<c:if test="${page-3>0}">
			<li><a href="javascript:void(0)" onclick="getPage('${page-3}')">${page-3}</a></li>
			</c:if>
			<c:if test="${page-2>0}">
			<li><a href="javascript:void(0)" onclick="getPage('${page-2}')">${page-2}</a></li>
			</c:if>
			<c:if test="${page-1>0}">
			<li><a href="javascript:void(0)" onclick="getPage('${page-1}')">${page-1}</a></li>
			</c:if>
			<li><a href="javascript:void(0)" onclick="getPage('${page}')" class="cur">${page}</a></li>
			<c:if test="${page+1<=totalPages}">
			<li><a href="javascript:void(0)" onclick="getPage('${page+1}')">${page+1}</a></li>
			</c:if>
			<c:if test="${page+2<=totalPages}">
			<li><a href="javascript:void(0)" onclick="getPage('${page+2}')">${page+2}</a></li>
			</c:if>
			<c:if test="${page+3<=totalPages}">
			<li><a href="javascript:void(0)" onclick="getPage('${page+3}')">${page+3}</a></li>
			</c:if>
			<c:if test="${page+4<=totalPages}">
			<li><a href="javascript:void(0)" onclick="getPage('${page+4}')">${page+4}</a></li>
			</c:if>
			<c:if test="${page+1<=totalPages}">
			<li><a href="javascript:void(0)" onclick="getPage('${page+1}')">下一页</a></li>
			</c:if>
			<li><a href="javascript:void(0)" onclick="getPage('${totalPages}')">尾页</a></li>
			<li><a href="javascript:void(0)"class="">共${totalPages}页</a></li>
			<li>
				<span class="span_pageNum">到第<input name="pageNum" class="pageNumStyle" type="text"/>页</span>
			</li>
			<li><a href="javascript:void(0)" onclick="goForwardPage()">确定</a></li>
		</ul>
	</div>
	<!-- 清楚所有的浮动，让子div的高度来决定父div的高度 -->
	<div class="clearfix"> </div>
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
			<p>Copyright &copy; 2018.Company name All rights reserved. <a href="" target="_blank" title=""></a> <a href="" title="" target="_blank"></a></p>
	</div>
</div>
<a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 0;"></span> <span id="toTopHover" style="opacity: 0;"> </span></a>
</body>
</html>