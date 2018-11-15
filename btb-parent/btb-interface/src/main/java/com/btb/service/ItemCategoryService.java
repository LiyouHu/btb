package com.btb.service;

import java.util.List;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUITreeNode;
import com.btb.pojo.ItemCategory;

public interface ItemCategoryService {

	List<EasyUITreeNode> findItemCategroyList(Long parentId);

	BtbResult insertItemCategory(ItemCategory itemCategory);

	BtbResult updateItemCategory(long id, String name);

	BtbResult deleteItemCategory(Long id);

	List<ItemCategory> findItemCategoryList(int categoryStatus);

}
