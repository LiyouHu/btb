package com.btb.common.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
	private Long totalPages ;	//总页数
	private List<?> itemlist ;	//当前页的result
	private Long recoredCount ;		//每页的记录数
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	public List<?> getItemlist() {
		return itemlist;
	}
	public void setItemlist(List<?> itemlist) {
		this.itemlist = itemlist;
	}
	public Long getRecoredCount() {
		return recoredCount;
	}
	public void setRecoredCount(Long recoredCount) {
		this.recoredCount = recoredCount;
	} 
	
}
