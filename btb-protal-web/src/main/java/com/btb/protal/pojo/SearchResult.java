package com.btb.protal.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
	private Long totalPages ;
	private List<?> itemlist ;
	private Long recoredCount ;
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
