package com.btb.protal.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.btb.pojo.Item;
import com.btb.pojo.ItemCategory;
import com.btb.service.ItemCategoryService;
import com.btb.service.ItemService;

@Controller
public class PageController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemCategoryService itemCategoryService;
	@RequestMapping("/index")
	public String index(Model model) {
		//查询所有促销商品 
		//1促销；2普通
		List<Item> itemList = itemService.findItemListByPromote(1);
		//查询分类列表 1：类别正常
		List<ItemCategory> categroyList = itemCategoryService.findItemCategoryList(1);
		//分类列表
		model.addAttribute("categroyList", categroyList);
		//商品列表
		model.addAttribute("itemList", itemList);
		return "index" ;
	}
	
	@RequestMapping("/login.html")
	public String showLoginPage(HttpServletRequest request,@RequestParam(name="callback",defaultValue="")String url
			,@RequestParam(name="msg",defaultValue="")String msg) {
		try {
		//	msg = URLDecoder.decode(msg,"utf-8");
			msg = new String(msg.getBytes("ISO8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("callback", url);
		request.setAttribute("msg", msg);
		//响应login.jsp视图
		return "login" ;
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		//跳转到jsp的页面
		return page;
	}
	
}
