package com.btb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.mapper.ItemMapper;
import com.btb.mapper.OrderDetailsMapper;
import com.btb.mapper.OrdersMapper;
import com.btb.pojo.Item;
import com.btb.pojo.OrderDetails;
import com.btb.pojo.OrderDetailsExample;
import com.btb.pojo.Orders;
import com.btb.pojo.OrdersExample;
import com.btb.pojo.OrdersExample.Criteria;
import com.btb.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private OrderDetailsMapper orderDetailsMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private TaskService taskService;
	
	/**
	 * 添加一个订单
	 */
	@Override
	public BtbResult insertOrderDetails(List<OrderDetails> orderDetailsList,Orders order) {
		order.setCreateTime(new Date());
		order.setUpdataTime(new Date());
		//订单更新的条数
		int i = ordersMapper.insert(order);
		Long oid = order.getOid();
		//订单详细插入的条数
		int s = 0,count = 0; 
		for (OrderDetails orderDetails : orderDetailsList) {
			//计算商品总价
			orderDetails.setOid(oid);
			orderDetails.setTotalfee(orderDetails.getItemnum()*orderDetails.getPrice());
			//更新商品的库存
			Item item = itemMapper.selectByPrimaryKey(orderDetails.getItemid());
			item.setNum(item.getNum()-orderDetails.getItemnum());
			itemMapper.updateByPrimaryKey(item);
			//
			s += orderDetailsMapper.insert(orderDetails);
			count++;
		}
		
		if(i==1&&s==count) {
			//订单添加成功，启动一个订单流程
			String processDefinitionKey = "order_process";
			String businessKey = oid.toString() ;	//业务主键 -- 通常为业务表主键值
			Map<String, Object> variables = new HashMap<String,Object>();
			variables.put("业务数据", order);
			runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
			return BtbResult.ok();
		}
		return BtbResult.build(400, "订单添加出错！");
	}

	@Override
	public EasyUIDataGridResult findOrderList(int page, int rows, String key) {
		//使用mybatis分页插件设置分页
		PageHelper.startPage(page, rows);	//设page码和每页显示的记录数
		OrdersExample example = new OrdersExample();
		if(key != null && !"".equals(key)) {	//添加查询条件
			Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(key);
			Criteria criteria2 = example.createCriteria();
			criteria2.andCellphoneEqualTo(key);
			example.or(criteria2);
		}
		List<Orders> list = ordersMapper.selectByExample(example);
		PageInfo<Orders> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		easyUIDataGridResult.setTotal(pageInfo.getTotal());
		easyUIDataGridResult.setRows(list);
		return easyUIDataGridResult;
	}

	@Override
	public Orders findOrderByOid(Long oid) {
		Orders orders = ordersMapper.selectByPrimaryKey(oid);
		return orders;
	}

	/**
	 * 根据订单oid查询订单详细
	 */
	@Override
	public List<OrderDetails> findOrderDetailsListByOid(Long oid) {
		//一个订单包含多个订单详细
		OrderDetailsExample example = new OrderDetailsExample();
		com.btb.pojo.OrderDetailsExample.Criteria criteria = example.createCriteria();
		criteria.andOidEqualTo(oid);
		List<OrderDetails> list = orderDetailsMapper.selectByExample(example);
		return list ;
	}

	/**
	 * 更新没有通过的商品订单
	 */
	@Override
	public void updateNotPassOrders(String taskId,Long oid) {
		Orders orders = ordersMapper.selectByPrimaryKey(oid);
		orders.setCloseTime(new Date());
		orders.setUpdataTime(new Date());
		ordersMapper.updateByPrimaryKeySelective(orders);
		//还原订单商品的库存
		List<OrderDetails> orderDetailsList = findOrderDetailsListByOid(oid);
		for (OrderDetails orderDetails : orderDetailsList) {
			Item item = itemMapper.selectByPrimaryKey(orderDetails.getItemid());
			item.setNum(item.getNum()-orderDetails.getItemnum());
			itemMapper.updateByPrimaryKeySelective(item);
		}
		Map<String, Object> variables = new HashMap<String, Object>();
		//网管流程变量check的值非true及不通过
		variables.put("check", false);
		//办理任务
		taskService.complete(taskId, variables);;
	}
	
	/**
	 * 更新订单货物收集流程
	 */
	@Override
	public void updateOrdersCollect(Long oid,Long eid,String taskId) {
		Orders orders = new Orders();
		orders.setOid(oid);
		orders.setStorekeeperid(eid);
		orders.setUpdataTime(new Date());
		ordersMapper.updateByPrimaryKeySelective(orders);
		taskService.complete(taskId);
	}
	/**
	 * 更新订单配送货物流程
	 */
	@Override
	public void updateOrdersTransfer(Long oid, Long eid, String taskId) {
		Orders orders = new Orders();
		orders.setOid(oid);
		//设置派送员id
		orders.setEmpid(eid);
		orders.setUpdataTime(new Date());
		ordersMapper.updateByPrimaryKeySelective(orders);
		taskService.complete(taskId);
	}
	/**
	 * 更新签收订单货物流程
	 */
	@Override
	public void updateOrdersReceive(Long oid, String taskId) {
		Orders orders = new Orders();
		orders.setOid(oid);
		orders.setUpdataTime(new Date());
		//设置结束时间
		orders.setEndTime(new Date());
		ordersMapper.updateByPrimaryKeySelective(orders);
		taskService.complete(taskId);
	}

	
}
