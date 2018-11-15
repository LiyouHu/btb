<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
function submitForm(){
	if(!$('#itemeEditForm').form('validate')){
		$.messager.alert('提示','表单还未填写完成!');
		return ;
	}
	 //取商品价格，单位为“分”
	$("#itemeEditForm [name=price]").val(($("#itemeEditForm [name=priceView]").val()*100).toFixed(0));
	$("#itemeEditForm [name=retail]").val(($("#itemeEditForm [name=retailView]").val() *100).toFixed(0)); 
	 
/* 	$("#itemeEditForm [name=price]").val(eval($("#itemeEditForm [name=priceView]").val()) * 100);
	$("#itemeEditForm [name=retail]").val(eval($("#itemeEditForm [name=retailView]").val()) * 100); */
	itemEditEditor.sync();
	
	var paramJson = [];
	$("#itemeEditForm .params li").each(function(i,e){
		var trs = $(e).find("tr");
		var group = trs.eq(0).text();
		var ps = [];
		for(var i = 1;i<trs.length;i++){
			var tr = trs.eq(i);
			ps.push({
				"k" : $.trim(tr.find("td").eq(0).find("span").text()),
				"v" : $.trim(tr.find("input").val())
			});
		}
		paramJson.push({
			"group" : group,
			"params": ps
		});
	});
	paramJson = JSON.stringify(paramJson);
	
	$("#itemeEditForm [name=itemParams]").val(paramJson);
	
	$.post("${pageContext.request.contextPath}/item/update",$("#itemeEditForm").serialize(), function(data){
		if(data.status == 200){
			$.messager.alert('提示','修改商品成功!','info',function(){
				$("#itemEditWindow").window('close');
				$("#itemList").datagrid("reload");
			});
		}
	});
}
</script>
<!--

//-->
</script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemeEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	        <tr>
	            <td>商品类目:</td>
	            <td>
	            	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
	            	<input type="hidden" name="cid" style="width: 280px;"></input>	
	            </td>
	        </tr>
	        <tr>
	            <td>商品标题:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品大小:</td>
	            <td><input class="easyui-textbox" type="text" name="size" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>所属品牌:</td>
	            <td><input class="easyui-textbox" type="text" name="brand" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品价格:</td>
	            <td><input class="easyui-numberbox" type="text" name="priceView" data-options="min:1,max:99999999,precision:2,required:true" />
	            	<input type="hidden" name="price"/>
	            </td>
	        </tr>
	        <tr>
	            <td>商品规格:</td>
	            <td><input class="easyui-textbox" name="specification" data-options="multiline:true,validType:'length[0,250]'" style="height:60px;width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>库存数量:</td>
	            <td><input class="easyui-numberbox" type="text" name="num" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	         <tr>
	            <td>建议零售价:</td>
	            <td><input class="easyui-numberbox" type="text" name="retailView" data-options="min:1,max:99999999,precision:2,required:true" />
	            	<input type="hidden" name="retail"/>
	            </td>
	        </tr>
	        <tr>
	            <td>是否推广商品:</td>
	            <td><input type="radio" name="promote" value="0" /><span style="font-size:13px;color:#1E90FF">普通</span>
	           		<input type="radio" name="promote" value="1" /><span style="font-size:13px;color:#1E90FF">推广</span>
	            </td>
	        </tr>
	        <tr>
	            <td>商品图片:</td>
	            <td>
	            	<a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	                <input type="hidden" name="image"/>
	            </td>
	        </tr>
	        <tr>
	            <td>商品描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
	            </td>
	        </tr>
	       <!--  <tr class="params hide">
	        	<td>商品规格:</td>
	        	<td>
	        		
	        	</td>
	        </tr> -->
	    </table>
	  <!--   <input type="hidden" name="itemParams"/>
	    <input type="hidden" name="itemParamId"/> -->
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	var itemEditEditor ;
	$(function(){
		//实例化编辑器
		itemEditEditor = TAOTAO.createEditor("#itemeEditForm [name=desc]");
	});
	
	
</script>
