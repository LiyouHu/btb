package com.btb.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.OrderDetails;
import com.btb.pojo.Orders;
import com.btb.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/list")
	@ResponseBody	
	public EasyUIDataGridResult findOrderList(@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="10")int rows,@RequestParam(defaultValue="")String key){
		if(!"".equals(key)) {
			try {
				key = new String(key.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		EasyUIDataGridResult easyUIDataGridResult = orderService.findOrderList(page,rows,key);
		return easyUIDataGridResult;
	}
	
	/**
	 * 展示订单详情
	 */
	@RequestMapping("/order/orderdetails")
	public String orderDetails(Long oid,Model model) {
		Orders orders  = null ;
		List<OrderDetails> list = null ;
		if(oid != null) {
			orders = orderService.findOrderByOid(oid);
			list = orderService.findOrderDetailsListByOid(oid);
		}
		model.addAttribute("orders", orders);
		model.addAttribute("orderDetailsList", list);
		return "order_details";
	}
}
