package com.btb.service;

import java.util.List;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.OrderDetails;
import com.btb.pojo.Orders;

public interface OrderService {

	BtbResult insertOrderDetails(List<OrderDetails> orderdetailsList,Orders order);

	EasyUIDataGridResult findOrderList(int page, int rows, String key);

	Orders findOrderByOid(Long oid);

	List<OrderDetails> findOrderDetailsListByOid(Long oid);

	void updateNotPassOrders(String taskId,Long oid);

	void updateOrdersCollect(Long oid,Long eid,String taskId);

	void updateOrdersTransfer(Long oid, Long eid, String taskId);

	void updateOrdersReceive(Long oid, String taskId);
	
}
