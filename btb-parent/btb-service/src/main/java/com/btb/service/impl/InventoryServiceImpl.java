package com.btb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.btb.common.pojo.BtbResult;
import com.btb.common.utils.JsonUtils;
import com.btb.jedis.JedisClient;
import com.btb.mapper.ItemMapper;
import com.btb.pojo.Item;
import com.btb.pojo.User;
import com.btb.service.InventoryService;
@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INVENTORY}")	//订货清单
	private String INVENTORY;
	@Value("${INVENTORY_EXPIRE}")//保存在redis中的时间
	private Integer INVENTORY_EXPIRE;	
	
	@Override
	public BtbResult addItemtoRedis(User loginUser, Long itemId,int quantity, List<Item> inventoryList) {
		Long uid = loginUser.getUid();	//当前的登录用户id
		//1.将cookie中的商品同步到redis中
		for (Item cookieItem : inventoryList) {
			//保存之前需要判断redis中是否已经存在这个商品
			boolean exists = jedisClient.exists(INVENTORY+":"+uid+":"+cookieItem.getId());
			if(exists) {	//存在
				//取出这个商品
				String redisString = jedisClient.get(INVENTORY+":"+uid+":"+cookieItem.getId());
				Item redisItem = JsonUtils.jsonToPojo(redisString, Item.class);
				//修改清单中的数量为cookie中的数量+redis原有的数量
				redisItem.setNum(redisItem.getNum()+cookieItem.getNum());
				//将修改数量的item添加到item转为json
				String redisValue = JsonUtils.objectToJson(redisItem);
				jedisClient.set("INVENTORY:"+uid+":"+redisItem.getId(),redisValue);
				jedisClient.expire("INVENTORY:"+uid+":"+redisItem.getId(), INVENTORY_EXPIRE);
				
			}else {	//redis中没有这个商品，直接转为json 添加到redis中
				String cookieValue = JsonUtils.objectToJson(cookieItem);
				jedisClient.set("INVENTORY:"+uid+":"+cookieItem.getId(),cookieValue);
				jedisClient.expire("INVENTORY:"+uid+":"+cookieItem.getId(), INVENTORY_EXPIRE);
			}
		}
		//2判断需要添加的itemId在redis中是否存在
		boolean b = jedisClient.exists(INVENTORY+":"+uid+":"+itemId);
		Item newItem = null ;
		if(!b) { //不存在
			//2根据itemId查询商品信息，
			newItem = itemMapper.selectByPrimaryKey(itemId);
			if(null == newItem) {
				return BtbResult.build(400, "商品信息不存在！");
			}
			String image = newItem.getImage();
			//显示的图片为商品的第一张上传图片
			if(StringUtils.isNotBlank(image)) {
				image = image.split(",")[0];
				newItem.setImage(image);
			}
			newItem.setNum((long)quantity);	//使用商品的库存保存商品的购买数量
		}else {
			String redisString = jedisClient.get(INVENTORY+":"+uid+":"+itemId);
			Item redisItem = JsonUtils.jsonToPojo(redisString, Item.class);
			//修改数量为添加的数量+redis原有的数量
			redisItem.setNum(quantity+redisItem.getNum());
			newItem = redisItem;
		}
		//将修改数量的item添加到item转为json
		String itemJson = JsonUtils.objectToJson(newItem);
		//key形式INVENTORY:2:153425325235
		jedisClient.set(INVENTORY+":"+uid+":"+itemId, itemJson);
		jedisClient.expire(INVENTORY+":"+uid+":"+itemId, INVENTORY_EXPIRE);
		return BtbResult.ok("商品添加成功！");
	}
	@Override
	public List<Item> getItemByRedis(User loginUser) {
		Long uid = loginUser.getUid();
		Set<String> keys = jedisClient.keys("INVENTORY:"+uid+":*");
		List<Item> list = new ArrayList<Item>();
		for (String key : keys) {
			String itemJson = jedisClient.get(key);
			list.add(JsonUtils.jsonToPojo(itemJson, Item.class));
		}
		return list;
	}
	
	@Override
	public BtbResult updateInventoryToRedis(long userId,long itemId, int quantity) {
		//判断需要添加的itemId在redis中是否存在
		boolean b = jedisClient.exists(INVENTORY+":"+userId+":"+itemId);
		Item item = null ;
		if(!b) { //不存在
			return BtbResult.build(400, "商品不存在购物车中!");
		}
		String redisString = jedisClient.get(INVENTORY+":"+userId+":"+itemId);
		item = JsonUtils.jsonToPojo(redisString, Item.class);
		//修改数量为添加的数量+redis原有的数量
		item.setNum(quantity+item.getNum());
		//将修改数量的item添加到item转为json
		String itemJson = JsonUtils.objectToJson(item);
		//key形式INVENTORY:2:153425325235
		jedisClient.set(INVENTORY+":"+userId+":"+itemId, itemJson);
		jedisClient.expire(INVENTORY+":"+userId+":"+itemId, INVENTORY_EXPIRE);
		return BtbResult.ok("购买数量修改成功！");
	}
	@Override
	public BtbResult deleteItemById(Long itemId,Long userId) {
		boolean exists = jedisClient.exists(INVENTORY+":"+userId+":"+itemId);
		if(exists) {	//商品在购物车存在
			Long expire = jedisClient.expire(INVENTORY+":"+userId+":"+itemId, 0);
			if(expire>0) {
				return BtbResult.ok();
			}
		}
		return BtbResult.build(400, "商品删除失败！");
	}
	
}
