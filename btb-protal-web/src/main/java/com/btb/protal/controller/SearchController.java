package com.btb.protal.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.btb.common.pojo.SearchResult;
import com.btb.pojo.Item;
import com.btb.pojo.ItemCategory;
import com.btb.pojo.ItemDesc;
import com.btb.protal.pojo.SearchItem;
import com.btb.service.ItemCategoryService;
import com.btb.service.ItemService;

@Controller
public class SearchController {
	@Value("${SEARCH_ROWS}")
	private int SEARCH_ROWS;
	
	
	@Autowired
	private ItemService itemService ;
	@Autowired ItemCategoryService itemCategoryService;
	
	@RequestMapping("/search")
	public String getItemList(@RequestParam(defaultValue="1")int page,
			@RequestParam(name="q",defaultValue="")String queryKey,Model model) {
		try {
			queryKey = new String(queryKey.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SearchResult searchResult = itemService.findItemList(page, SEARCH_ROWS, queryKey);
		model.addAttribute("itemList", searchResult.getItemlist());
		model.addAttribute("totalPages", searchResult.getTotalPages());	//总的页数
		model.addAttribute("page", page);//	当前页
		model.addAttribute("queryKey", queryKey);
		//查询分类列表 1：类别正常
		List<ItemCategory> categroyList = itemCategoryService.findItemCategoryList(1);
		//分类列表
		model.addAttribute("categroyList", categroyList);
		return "sale" ;
	}
	
	@RequestMapping("/item/{itemId}")
	public String getItem(@PathVariable("itemId")long itemId,Model model) {
		Item item = itemService.findItemById(itemId);
		ItemDesc itemDesc = itemService.findItemDesc(itemId);
		SearchItem searchItem = new SearchItem(item);
		model.addAttribute("item", searchItem);
		model.addAttribute("itemDesc", itemDesc);
		//查询分类列表 1：类别正常
		List<ItemCategory> categroyList = itemCategoryService.findItemCategoryList(1);
		//分类列表
		model.addAttribute("categroyList", categroyList);
		return "single";
	}
	
	/**
	 * 根据商品分类查询
	 */
	@RequestMapping("/item/category")
	public String findItemListByCid(@RequestParam(defaultValue="1")int page,long cid,Model model) {
		/**
		 * page页码  ;SEARCH_ROWS 每页显示的条数 ;cid类别号
		 */
		SearchResult searchResult = itemService.findItemListByCid(page,SEARCH_ROWS,cid);
		model.addAttribute("itemList", searchResult.getItemlist());
		model.addAttribute("totalPages", searchResult.getTotalPages());
		model.addAttribute("page", page);
		model.addAttribute("cid", cid);
		//查询分类列表 1：类别正常
		List<ItemCategory> categroyList = itemCategoryService.findItemCategoryList(1);
		//分类列表
		model.addAttribute("categroyList", categroyList);
		return "sale" ;
	}
}
