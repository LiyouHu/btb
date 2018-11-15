package com.btb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.Item;
import com.btb.pojo.ItemDesc;
import com.btb.service.ItemService;

@Controller
public class ItemController {
	@Autowired 
	private ItemService itemService;
	
	@RequestMapping("/findItemById")
	@ResponseBody
	public Item findItemById(long id) {
		return itemService.findItemById(id);
	}
	
	
	@RequestMapping("/item/save")
	@ResponseBody
	public BtbResult save(Item item,String desc) {
		itemService.insertItem(item,desc);
		return BtbResult.ok();
	}
	//@RequiresRoles(value="xxx")
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult list(int page,int rows) {
		return itemService.findItemList(page,rows);
	}
	
	
	@RequestMapping("/item/query/desc/{itemId}")
	@ResponseBody
	public BtbResult findItemDesc(@PathVariable long itemId) {
		ItemDesc itemDesc = itemService.findItemDesc(itemId);
		return BtbResult.ok(itemDesc);
	}
	
	@RequestMapping("/item/update")
	@ResponseBody
	public BtbResult update(Item item,String desc) {
		BtbResult btbResult = itemService.updateItem(item,desc);
		return btbResult;
	}
	
	@RequestMapping("/item/delete")
	@ResponseBody
	public BtbResult delete(Long[] ids) {
		return itemService.deleteItem(ids);
		
	}
	
	//商品的下架
	@RequestMapping("/item/instock")
	@ResponseBody
	public BtbResult instock(Long[] ids) {
		return itemService.updateInstock(ids);
	}
	
	//商品上架
	@RequestMapping("/item/reshelf")
	@ResponseBody
	public BtbResult reshelf(Long[] ids) {
		return itemService.updateReshelf(ids);
	}
	
}
