package com.btb.protal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btb.common.pojo.BtbResult;
import com.btb.pojo.OrderDetails;
import com.btb.pojo.Orders;
import com.btb.protal.pojo.OrderDetailsCustom;
import com.btb.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/order/save")
	public String saveOrder(OrderDetailsCustom orderDetailsCustom,Orders order,Model model) {
		List<OrderDetails> orderDetailsList = orderDetailsCustom.getOrderDetailsList();
		BtbResult btbResult = orderService.insertOrderDetails(orderDetailsList,order);
		if(btbResult == null || btbResult.getStatus()==400) {
			model.addAttribute("orderMsg", "提交失败！");
			return "order_forward";
		}
		model.addAttribute("orderMsg", "提交成功！");
		return "order_forward";
	}
	
	

}
