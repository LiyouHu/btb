package com.btb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUITreeNode;
import com.btb.pojo.ItemCategory;
import com.btb.service.ItemCategoryService;

/**
 * 商品分类管理
 * @ClassName: ItemCategoryController 
 * @Description: TODO
 * @author: huliyou
 * @date: 2018年9月22日 下午4:47:40
 */
@Controller
public class ItemCategoryController {
	@Autowired 
	private ItemCategoryService itemCategoryService;
	
	@RequestMapping("/itemCategory/list")
	@ResponseBody
	public List<EasyUITreeNode> list(@RequestParam(name="id",defaultValue="0") long parentId ) {
		
		List<EasyUITreeNode> list = itemCategoryService.findItemCategroyList(parentId);
		return list;
	} 
	
	@RequestMapping("/itemCategory/create")
	@ResponseBody
	public BtbResult create(ItemCategory itemCategory) {
		return itemCategoryService.insertItemCategory(itemCategory);
	}
	
	@RequestMapping("/itemCategory/update")
	@ResponseBody
	public BtbResult update(Long id,String name) {
		return itemCategoryService.updateItemCategory(id,name);
	}
	
	@RequestMapping("/itemCtegory/delete")
	@ResponseBody
	public BtbResult delete(Long id) {
		return itemCategoryService.deleteItemCategory(id);
	}
	
}
