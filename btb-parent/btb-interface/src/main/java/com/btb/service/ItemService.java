package com.btb.service;

import java.util.List;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.common.pojo.SearchResult;
import com.btb.pojo.Item;
import com.btb.pojo.ItemCategory;
import com.btb.pojo.ItemDesc;

public interface ItemService {
	Item findItemById(Long id);

	void insertItem(Item item, String desc);

	EasyUIDataGridResult findItemList(int page, int rows);
	
	ItemDesc findItemDesc(Long id);

	BtbResult updateItem(Item item, String desc);

	BtbResult updateInstock(Long[] ids);

	BtbResult updateReshelf(Long[] ids);

	BtbResult deleteItem(Long[] ids);

	List<Item> findItemListByPromote(int promote);
	
	SearchResult findItemList(int page,int rows,String queryKey);

	SearchResult findItemListByCid(int page, int SEARCH_ROWS, long cid);

}
