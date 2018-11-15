package com.btb.protal.pojo;

import java.util.ArrayList;
import java.util.List;

import com.btb.pojo.OrderDetails;

public class OrderDetailsCustom {

	private List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
	
	
	public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	public List<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}
	
}
