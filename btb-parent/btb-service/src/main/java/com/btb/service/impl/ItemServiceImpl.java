package com.btb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.common.pojo.SearchResult;
import com.btb.common.utils.IDUtils;
import com.btb.mapper.ItemDescMapper;
import com.btb.mapper.ItemMapper;
import com.btb.pojo.Item;
import com.btb.pojo.ItemDesc;
import com.btb.pojo.ItemExample;
import com.btb.pojo.ItemExample.Criteria;
import com.btb.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Override
	public Item findItemById(Long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public void insertItem(Item item, String desc) {
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte)1);	//“1”正常 “2”删除
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		//添加商品详情
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setId(itemId);
		itemDesc.setDescription(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insertSelective(itemDesc);
		
	}

	@Override
	public EasyUIDataGridResult findItemList(int page, int rows) {
		//使用pageHelper进行页面的分页,需要在分页查询之前进行设置
		PageHelper.startPage(page, rows);
		ItemExample example = new ItemExample();
		//1、取页面结果集rows
		List<Item> list = itemMapper.selectByExample(example);
		//2、取页面总的记录数total
		long total = new PageInfo<>(list).getTotal();
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	
	
	@Override
	public ItemDesc findItemDesc(Long id) {
		return itemDescMapper.selectByPrimaryKey(id);
	}

	@Override
	public BtbResult updateItem(Item item, String desc) {
		try {
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
			ItemDesc itemDesc = new ItemDesc();
			itemDesc.setId(item.getId());
			itemDesc.setDescription(desc);
			itemDesc.setUpdated(new Date());
			itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		}catch(Exception e) {
			e.printStackTrace();
			return BtbResult.build(400, "商品修改失败！");
		}
		return BtbResult.ok();
	}

	@Override
	public BtbResult updateInstock(Long[] ids) {
		try {
			Item item =  new Item();
			item.setStatus((byte)2);
			for (Long id : ids) {
				item.setId(id);
				itemMapper.updateByPrimaryKeySelective(item);
			}
			return BtbResult.ok();
		}catch(Exception e) {
			e.printStackTrace();
			return BtbResult.build(400, "商品下架失败！");
		}
	}

	@Override
	public BtbResult updateReshelf(Long[] ids) {
		try {
			Item item =  new Item();
			item.setStatus((byte)1);
			for (Long id : ids) {
				item.setId(id);
				itemMapper.updateByPrimaryKeySelective(item);
			}
			return BtbResult.ok();
		}catch(Exception e) {
			e.printStackTrace();
			return BtbResult.build(400, "商品上架失败！");
		}
	}

	@Override
	public BtbResult deleteItem(Long[] ids) {
		try {
			for (Long id : ids) {
				itemMapper.deleteByPrimaryKey(id);
				itemDescMapper.deleteByPrimaryKey(id);
			}
			return BtbResult.ok();
		}catch(Exception e) {
			e.printStackTrace();
			return BtbResult.build(400, "商品删除失败！");
		}
	}
	
	/**
	 * 按条件查询商品
	 */
	@Override
	public List<Item> findItemListByPromote(int promote) {
		ItemExample example = new ItemExample();
		Criteria criteria = example.createCriteria();
		//查询没有下架的商品，1正常，2下架
		criteria.andStatusEqualTo((byte)1);
		//添加查询条件 1：促销商品 ；0：普通
		criteria.andPromoteEqualTo((byte)promote);	
		List<Item> itemList = itemMapper.selectByExample(example);
		return itemList;
	}
	
	/**
	 * 按条件分页查询商品
	 */
	@Override
	public SearchResult findItemList(int page, int rows, String queryKey) {
		//使用PageHellper插件进行分页处理
		//设置页码和每页显示的记录数
		PageHelper.startPage(page, rows);
		ItemExample example = new ItemExample();
		Criteria criteria = example.createCriteria();
		//1正常 ;2下架;
		criteria.andStatusEqualTo((byte)1);
		criteria.andTitleLike("%"+queryKey+"%");
		Criteria criteria2 = example.or();
		criteria2.andStatusEqualTo((byte)1);
		criteria2.andBrandLike("%"+queryKey+"%");
		List<Item> itemList = itemMapper.selectByExample(example);
		//使用分页插件的PageInfo取得页数信息
		long total = new PageInfo<>(itemList).getTotal();
		SearchResult searchResult = new SearchResult();
		//设置总的页数
		long totalPages = total/rows + (total%rows>0?1:0); 
		searchResult.setTotalPages(totalPages);	
		//设置当前页的结果集
		searchResult.setItemlist(itemList);
		//总的记录数
		searchResult.setRecoredCount((long)rows);
		return searchResult;
	}

	@Override
	public SearchResult findItemListByCid(int page, int SEARCH_ROWS, long cid) {
		PageHelper.startPage(page, SEARCH_ROWS);
		ItemExample example = new ItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cid);
		List<Item> itemList = itemMapper.selectByExample(example);
		PageInfo<Item> pageInfo = new PageInfo<>(itemList);
		//总的记录数
		long total = pageInfo.getTotal();
		SearchResult searchResult = new SearchResult();
		//总的页数
		long totalPages = total/SEARCH_ROWS + (total%SEARCH_ROWS>0?1:0);  
		searchResult.setItemlist(itemList);
		searchResult.setTotalPages(totalPages);
		searchResult.setRecoredCount(total);
		return searchResult;
	}

}
