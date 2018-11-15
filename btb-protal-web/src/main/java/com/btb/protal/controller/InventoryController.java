package com.btb.protal.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.BtbResult;
import com.btb.common.utils.JsonUtils;
import com.btb.pojo.Item;
import com.btb.pojo.ItemCategory;
import com.btb.pojo.User;
import com.btb.service.InventoryService;
import com.btb.service.ItemCategoryService;
import com.btb.service.ItemService;

/**
 * @ClassName: InventoryController 
 * @Description:  存货清单管理
 * @author: huliyou
 * @date: 2018年10月20日 下午2:06:00
 */
@Controller
public class InventoryController{
	@Autowired 
	private ItemService itemService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired 
	private ItemCategoryService itemCategoryService;
	//商品信息保存在订货清单中的key
	@Value("${INVENTORY_KEY}")
	private String INVENTORY_KEY ;
	@Value("${COOKIE_EXPIRE}")
	private Integer COOKIE_EXPIRE;

	
	
	@RequestMapping("/inventory/add")
	@ResponseBody
	public BtbResult add(HttpServletRequest request,HttpServletResponse response,Long itemId,int quantity) {
		//检查数据合法性
		if(null == itemId && quantity >0) {
			return BtbResult.build(400, "商品数据信息不正确！");
		}
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		//取得cookie中的商品
		List<Item> cookieList = getInventoryList(request);	
		//如果用户是登录状态将商品信息保存在redis中
		if(loginUser != null) {
			try {
				return inventoryService.addItemtoRedis(loginUser, itemId, quantity,cookieList);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//不是登录状态
		boolean flag = false ;		//商品在清单中的标志位
		if(cookieList !=null && cookieList.size()>0) {	//从已有的信息中取
			for (Item cookieItem : cookieList) {
				//1、cookie中有需要添加的商品
				if(itemId.longValue() == cookieItem.getId()) {
					cookieItem.setNum(cookieItem.getNum()+quantity);	//叠加商品数量
					flag = true ;//商品在清单中存在
					break ;
				}
			}
		}
		//2、cookie中没有需要添加的商品
		if(!flag) {	//订货清单中没有此商品
			Item item = itemService.findItemById(itemId.longValue());//根据itemId查询商品信息，
			if(null == item) {
				return BtbResult.build(400, "商品信息不存在！");
			}
			String image = item.getImage();
			if(StringUtils.isNotBlank(image)) {
				image = image.split(",")[0];
				item.setImage(image);
			}
			item.setNum((long)quantity);	//使用商品的库存保存商品的购买数量
			cookieList.add(item);	//将商品信息添加到订货清单
		}
		if(cookieList.size()>0) {		//list有商品
			//将list转为json字符串保存到cookie中
			String json = JsonUtils.objectToJson(cookieList);
			this.saveCookie(response, INVENTORY_KEY, json, COOKIE_EXPIRE);
			return BtbResult.ok("商品已成功加入订货清单！");
			
		}
		return BtbResult.build(400, "商品加入失败！");		
	}
	
	
	public void saveCookie(HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxAge) {
		if(null != cookieName && !"".equals(cookieName)) {
			if(cookieValue == null ) {
				cookieValue = "" ;
 			}
			//将商品信息中的汉子进行编码
			try {
				cookieValue = URLEncoder.encode(cookieValue, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Cookie cookie = new Cookie(cookieName, cookieValue);
			if(cookie.getMaxAge()>0) {
				cookie.setMaxAge(cookieMaxAge);	
			}
			response.addCookie(cookie);
		}
	}
	
	public List<Item> getInventoryList(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();	//得到所有的cookie
		if("".equals(INVENTORY_KEY)) {
			return null;
		}
		String cookieValue = null ;
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(INVENTORY_KEY.equals(cookie.getName())) {
					cookieValue = cookie.getValue();
				}
			}
		}
		List<Item> list = null;
		if(cookieValue != null) {
			try {
				cookieValue = URLDecoder.decode(cookieValue, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotBlank(cookieValue)) {
			list = JsonUtils.jsonToList(cookieValue, Item.class);
		}else {
			list = new ArrayList<Item>();
		}
		return list;
	}
	
	@RequestMapping("/inventory/show")
	public String showInventory(HttpServletRequest request) {
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		List<Item> inventoryList = null ;
		if(loginUser != null) {
			inventoryList = inventoryService.getItemByRedis(loginUser);
		}else {
			inventoryList = this.getInventoryList(request);
		}
		long totalPrice = 0;
		for (Item item : inventoryList) {
			totalPrice += item.getNum()*item.getPrice();
		}
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("inventoryList", inventoryList);
		//查询分类列表 1：类别正常
		List<ItemCategory> categroyList = itemCategoryService.findItemCategoryList(1);
		//分类列表
		request.setAttribute("categroyList", categroyList);
		return "checkout" ;
	}
	
	@RequestMapping("/inventory/update")
	@ResponseBody
	public BtbResult update(Long itemId,int quantity,HttpServletRequest request,HttpServletResponse response) {
		if(itemId == null || quantity <= 0) {
			return BtbResult.build(400, "修改数据不合法！");
		}
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		//用户是登陆状态，修改redis中的商品
		if(loginUser != null) {	
			long uid = loginUser.getUid();
			return inventoryService.updateInventoryToRedis(uid,itemId,quantity);
		}
		//没有登录下修改订货清单
		List<Item> inventoryList = getInventoryList(request);
		if(inventoryList == null || inventoryList.size() == 0) {
			return BtbResult.build(400, "该商品不存在！");
		}
		for (Item item : inventoryList) {
			if(item.getId() == itemId.longValue()) {
				item.setNum((long)quantity);
				String json = JsonUtils.objectToJson(inventoryList);
				this.saveCookie(response, INVENTORY_KEY, json, COOKIE_EXPIRE);	//重新保存cookie
				break ;
			}
		}
		
		return BtbResult.ok();
	}
	@RequestMapping("/inventory/delete")
	@ResponseBody
	public BtbResult delete(Long itemId,HttpServletRequest request,HttpServletResponse response) {
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		//用户登录了,从redis中删除商品
		BtbResult btbResult = null ;
		if(loginUser != null) {
			btbResult = inventoryService.deleteItemById(itemId,loginUser.getUid());
		}
		//cookie中也需要删除
		List<Item> cookieList = getInventoryList(request);
		for (Item item : cookieList) {
			if(item.getId()==itemId.longValue()) {
				cookieList.remove(item);
				String json = JsonUtils.objectToJson(cookieList);
				this.saveCookie(response, INVENTORY_KEY, json, COOKIE_EXPIRE);	//重新保存cookie
				return BtbResult.ok();
			}
		}
		return btbResult;
	}
	
	@RequestMapping("/inventory/commit")
	public String commit(HttpServletRequest request) {
		List<Item> inventoryList = null;
		/*
		 * 1、进入结算前没有登录，清单存放在cookie中
		 * 	需要登录进入结算页 ，进入结算页 被拦截器 设置flag属性（cookie）属性
		 * 2 、进入结算前已经登录过，清单存放在redis中
		 * 	不用登录，拦截器此时不会设置拦截flag属性 
		 * ---有flag
		 * */
		
		//拦截登录 ，设置了一个flag
		if("cookie".equals(request.getSession().getAttribute("flag"))) {	//从cookie中结算清单
			//结算清单前没有登录，清单信息存放在cookie中	
			inventoryList = getInventoryList(request);
			//清掉标志位，说明登录了
			request.getSession().setAttribute("flag", null);	
		}else {	
			User loginUser = (User)request.getSession().getAttribute("loginUser");
			inventoryList = inventoryService.getItemByRedis(loginUser);
			
		}
		long totalPrice = 0;
		int itemCount = 0;
		for (Item item : inventoryList) {
			totalPrice += item.getNum()*item.getPrice();
			itemCount += item.getNum();
		}
		request.setAttribute("itemCount", itemCount);
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("inventoryList", inventoryList);
		//查询分类列表 1：类别正常
		List<ItemCategory> categroyList = itemCategoryService.findItemCategoryList(1);
		//分类列表
		request.setAttribute("categroyList", categroyList);
		return "contact" ;
	}
}
