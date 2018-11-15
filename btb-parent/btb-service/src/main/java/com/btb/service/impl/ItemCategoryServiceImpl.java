package com.btb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUITreeNode;
import com.btb.mapper.ItemCategoryMapper;
import com.btb.pojo.ItemCategory;
import com.btb.pojo.ItemCategoryExample;
import com.btb.pojo.ItemCategoryExample.Criteria;
import com.btb.service.ItemCategoryService;
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
	@Autowired
	private ItemCategoryMapper itemCategoryMapper;
	@Override
	public List<EasyUITreeNode> findItemCategroyList(Long parentId) {
		ItemCategoryExample example = new ItemCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<ItemCategory> categroyList = itemCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> list = new ArrayList<EasyUITreeNode>();
		for (ItemCategory itemCategory : categroyList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(itemCategory.getCid());
			node.setText(itemCategory.getName());
			//设置节点是否为父节点，1父节点，父节点为显示文件夹闭合状态 “closed” 0为子节点设置为展开状态“open”
			node.setState(itemCategory.getIsParent()==1?"closed":"open");
			list.add(node);
		}
		return list;
	}
	@Override
	public BtbResult insertItemCategory(ItemCategory itemCategory) {
		//1是父类目；0 否
		itemCategory.setIsParent((byte)0);	
		itemCategory.setStatus((byte)1);	//分类状态1正常
		itemCategory.setTreeindex(0L);		//排序索引默认为从0开始
		itemCategory.setCreated(new Date());
		itemCategory.setUpdated(new Date());
		int i = itemCategoryMapper.insert(itemCategory);
		//查询父节点
		ItemCategory parent = new ItemCategory();
		parent.setCid(itemCategory.getParentId());
		parent.setIsParent((byte)1);
		parent.setUpdated(new Date());
		int j = itemCategoryMapper.updateByPrimaryKeySelective(parent);
		if(i>0&&j>0) {
			return BtbResult.ok();
		}
		return BtbResult.build(400, "添加节点失败！");
	}
	@Override
	public BtbResult updateItemCategory(long id, String name) {
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setCid(id);
		itemCategory.setName(name);
		itemCategory.setUpdated(new Date());
		int i = itemCategoryMapper.updateByPrimaryKeySelective(itemCategory);
		if(i>0) {
			return BtbResult.ok();
		}
		return BtbResult.build(400, "商品分类更新失败！");
	}
	@Override
	public BtbResult deleteItemCategory(Long id) {
		//第一种、删除的为子节点，且该节点有至少一个姊妹节点
		//第二种、删除的为子节点，该节点没有姊妹节点，需要修改父节点为子节点
		//第三种、删除的为父节点，需要删除自己和所有子节点
		try {
			//查询出该节点
			ItemCategory node = itemCategoryMapper.selectByPrimaryKey(id);
			//判断此节点是否为父节点
			if(node.getIsParent()==1) {	//是父节点
				//需要删除所有的子节点
				Long cid = node.getCid();
				ItemCategoryExample example = new ItemCategoryExample();
				Criteria criteria = example.createCriteria();
				criteria.andParentIdEqualTo(cid);;
				//查询所有以本节点id为父id的子节点
				List<ItemCategory> sonList = itemCategoryMapper.selectByExample(example);
				for (ItemCategory son : sonList) {
					//删除儿子节点之前需要判断每一个儿子节点是否为父节点
					if(son.getIsParent()==1) {	//儿子节点也是父节点
						//使用递归调用本方法
						this.deleteItemCategory(son.getCid());
					}
				}
				//删除掉所有的儿子节点
				itemCategoryMapper.deleteByExample(example);
			}
			Long parentId = node.getParentId();	//此节点的父节点id
			ItemCategoryExample example = new ItemCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			List<ItemCategory> cateList = itemCategoryMapper.selectByExample(example);
			if(cateList.size()==1) {	//如果该节点是子节点且此节点没有姊妹节点
				//需要修改父节点为子节点
				ItemCategory parentNode = new ItemCategory();
				parentNode.setCid(node.getParentId());
				parentNode.setIsParent((byte)0);	//修改父节点为子节点
				parentNode.setUpdated(new Date());
				itemCategoryMapper.updateByPrimaryKeySelective(parentNode);	//更新父节点
			}
			//删除的节点至少有一个姊妹节点，删除自己即可
			itemCategoryMapper.deleteByPrimaryKey(id);
			return BtbResult.ok();
		}catch(Exception e) {
			e.printStackTrace();
			return BtbResult.build(400, "删除节点失败！");
		}
	}
	
	/**
	 * 查询所有的子类别
	 */
	@Override
	public List<ItemCategory> findItemCategoryList(int categoryStatus) {
		ItemCategoryExample example = new ItemCategoryExample();
		//按照treeindex排序
		example.setOrderByClause("treeindex");
		Criteria criteria = example.createCriteria();
		//1：正常；2下架
		criteria.andStatusEqualTo((byte)categoryStatus);
		//1是父类目 0不是
		criteria.andIsParentEqualTo((byte)0);
		List<ItemCategory> categoryList = itemCategoryMapper.selectByExample(example);
		return categoryList;
	}

}
