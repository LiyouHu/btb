package com.btb.service;

import java.util.List;

import com.btb.common.pojo.BtbResult;
import com.btb.pojo.Item;
import com.btb.pojo.User;

public interface InventoryService {
	
	BtbResult addItemtoRedis(User loginUser,Long itemId,int quantity, List<Item> cookieList);

	List<Item> getItemByRedis(User loginUser);

	BtbResult updateInventoryToRedis(long userId,long itemId, int quantity);

	BtbResult deleteItemById(Long itemId,Long userId);
}
