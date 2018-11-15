package com.btb.protal.pojo;

import com.btb.pojo.Item;
/**
 * @ClassName: SearchItem 
 * @Description: 商品列表详细的包装类
 * @author: huliyou
 * @date: 2018年10月14日 下午2:11:56
 */
public class SearchItem extends Item{
	
	public SearchItem(Item item) {
		this.setId(item.getId());
	    this.setTitle(item.getTitle());
	    this.setCid(item.getCid());
	    this.setSize(item.getSize());
	    this.setBrand(item.getBrand());
	    this.setPrice(item.getPrice());
	    this.setSpecification(item.getSpecification());
	    this.setImage(item.getImage());
	    this.setNum(item.getNum());
	    this.setRetail(item.getRetail());
	    this.setStatus(item.getStatus());
	    this.setPromote(item.getPromote());
	}
	
	public String[] getImages() {
		if(this.getImage() !=  null && !"".equals(this.getImage())) {
			String[] images = this.getImage().split(",");
			return images ;
		}
		return null ;
	}
	
    
}
