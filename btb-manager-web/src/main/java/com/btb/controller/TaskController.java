package com.btb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btb.pojo.Emp;
import com.btb.pojo.OrderDetails;
import com.btb.pojo.Orders;
import com.btb.service.OrderService;

/**
 * @ClassName: TaskController 
 * @Description: 任务管理
 * @author: huliyou
 * @date: 2018年11月4日 下午5:51:00
 */
@Controller
public class TaskController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private OrderService orderService ;
	
	@RequestMapping("/task/groupTaskList")
	public String findGroupTaskList(HttpServletRequest request) {
		Emp loginUser = (Emp)request.getSession().getAttribute("loginUser");
		TaskQuery taskQuery = taskService.createTaskQuery();
		String candidateUser = loginUser.getEid().toString();
		//使用用户id过滤
		taskQuery.taskCandidateUser(candidateUser);
		List<Task> list = taskQuery.list();
		request.setAttribute("list", list);
		return "grouptask" ;
	}
	
	@RequestMapping("/task/findData")
	//查看业务数据
	public String findData(String id,Model model) {
		String taskId = id ;
		//根据任务id获取流程变量
		Map<String, Object> variables = taskService.getVariables(taskId);
		Object obj = variables.get("业务数据");
		if(obj == null) {
			return "processinstance_data" ;
		}
		//如果这是一个订单业务
		if(obj instanceof Orders) {
			Orders orders = (Orders)obj;
			return "redirect:/order/orderdetails?oid="+orders.getOid();
		}
		//返回一个通用的流程数据展示页
		model.addAttribute("processInstanceData", obj.toString());
		return "processinstance_data" ;
	}
	
	/**
	 * 办理组任务
	 */
	@RequestMapping("/task/takeTask")
	public String takeGroupTask(String taskId,HttpServletRequest request) {
		Emp loginUser = (Emp)request.getSession().getAttribute("loginUser");
		String userId = loginUser.getEid().toString();
		if(StringUtils.isNotBlank(taskId) && StringUtils.isNotBlank(userId)) {
			//拾取组任务
			taskService.claim(taskId, userId);
		}
		return "forward:/task/groupTaskList";
	}
	
	@RequestMapping("/task/personalTask")
	public String findPersonalTask(HttpServletRequest request) {
		TaskQuery taskQuery = taskService.createTaskQuery();
		Emp loginUser = (Emp)request.getSession().getAttribute("loginUser");
		String assignee = loginUser.getEid().toString();
		taskQuery.taskAssignee(assignee);
		List<Task> list = taskQuery.list();
		request.setAttribute("list", list);
		return "personaltask";
	}
	
	@RequestMapping("/task/checkOrder")
	public String checkOrder(String taskId,Model model) {
		Map<String, Object> variables = taskService.getVariables(taskId);
		Object obj = variables.get("业务数据");
		Orders orders  = null ;
		List<OrderDetails> list = null ;
		if(obj instanceof Orders) {
			orders = (Orders)obj;
		}
		if(orders.getOid() != null) {
			orders = orderService.findOrderByOid(orders.getOid());
			list = orderService.findOrderDetailsListByOid(orders.getOid());
		}
		model.addAttribute("orders", orders);
		model.addAttribute("orderDetailsList", list);
		model.addAttribute("taskId", taskId);
		return "check" ;
	}
	/**
	 * 检查订单结果提交
	 */
	@RequestMapping("/task/checkOrderResult")
	public String checkOrderResult(String taskId,String check) {
		if(StringUtils.isBlank(taskId)&&StringUtils.isBlank(check)) {
			return "redirect:/task/personalTask" ;
		}
		//办理任务之前先取得业务数据
		Map<String, Object> variables = taskService.getVariables(taskId);
		Object obj = variables.get("业务数据");
		Orders orders  = null ;
		if(obj instanceof Orders) {
			orders = (Orders)obj;
		}
		//没有通过验证
		if(!"true".equals(check)&&orders.getOid() != null) {
			//关闭订单
			orderService.updateNotPassOrders(taskId,orders.getOid());
		}else {	//通过检测
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("check", true);
			taskService.complete(taskId, map);
		}
		return "redirect:/task/personalTask" ;
	}
	
	/**
	 * 	办理收集货物的业务
	 */
	@RequestMapping("/task/collectItem")
	public String collectItem(String taskId,HttpServletRequest request) {
		Object obj = taskService.getVariable(taskId, "业务数据");
		Orders orders = null ;
		if(obj instanceof Orders) {
			orders = (Orders) obj;
		}
		Emp loginUser = (Emp)request.getSession().getAttribute("loginUser");
		//办理任务
		orderService.updateOrdersCollect(orders.getOid(),loginUser.getEid(),taskId);
		return "redirect:/task/personalTask" ;
	}

	/**
	 * 	办理配送货物的业务
	 */
	@RequestMapping("/task/itemTransfer")
	public String itemTransfer(String taskId,HttpServletRequest request) {
		Object obj = taskService.getVariable(taskId, "业务数据");
		Orders orders = null ;
		if(obj instanceof Orders) {
			orders = (Orders) obj;
		}
		Emp loginUser = (Emp)request.getSession().getAttribute("loginUser");
		//办理任务
		orderService.updateOrdersTransfer(orders.getOid(),loginUser.getEid(),taskId);
		return "redirect:/task/personalTask" ;
	}
	
	/**
	 * 办理用户签收的任务
	 */
	@RequestMapping("/task/userReceive")
	public String userReceive(String taskId) {
		Object obj = taskService.getVariable(taskId, "业务数据");
		Orders orders = null ;
		if(obj instanceof Orders) {
			orders = (Orders) obj;
		}
		//办理任务
		orderService.updateOrdersReceive(orders.getOid(),taskId);
		return "redirect:/task/personalTask" ;
	}
}
