package com.btb.common.pojo;

import java.util.List;

/**
 * 用于查询商品信息响应到页面的datagrid的json格式
 * @ClassName: SearchItemResult 
 * @Description: TODO
 * @author: huliyou
 * @date: 2018年9月25日 下午5:13:10
 */
public class EasyUIDataGridResult {
	//属性名称是根据easyui要求的json格式取的
	private Long total ;	//总记录数
	private List rows ;		//数据结果集
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}		
	
}
