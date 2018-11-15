<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<<html>
<head>
	<title></title>
	<meta charset="utf-8">
	<style type="text/css">
		table,th,td{
			border: 1px solid red;
		}
	</style>
	<script type="text/javascript" src="./js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
			
	
	
	
		function insert(){
			var url = "${pageContext.request.contextPath}/emp/list" ;
			var num = $('#num').val();
			$("tr").remove('.clas1');
			alert('s')
			$.get(url,{page:num,rows:15},function(data){
				var rows = data.rows;
				
				for(var i = 0 ;i<rows.length;i++){
					 var id = rows[i].eid;
					var ename = rows[i].ename ;
					var cellphone = rows[i].cellphone ;
					var cow =  "<tr class='clas1'><td>"+id+"</td><td>"+ename+"</td><td>"+cellphone+"</td></tr>"
					$("#table1").append(cow); 
				}
			},'json');
		}
	</script>
</head>
<body>
	<table id="table1" >
		<tr id="th1" >
			<td >1222</td>
			<td>222</td>
			<td>22222222</td>
		</tr>

	</table>
	<input type="button" value="添加" onclick="insert()"> 
</body>
</html>