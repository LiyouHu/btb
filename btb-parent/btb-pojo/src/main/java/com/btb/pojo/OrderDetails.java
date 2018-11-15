package com.btb.pojo;

import java.io.Serializable;

public class OrderDetails implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long odid;	//订单详细id
    
    private Long oid;	//订单id

    private Long itemid;	//商品id

    private Long itemnum;	//商品数目

    private Long price;		//商品单价

    private Long totalfee;	//商品总价格

    private String image;	//商品图片

    private String itemtitle; //商品标题

    public Long getOdid() {
        return odid;
    }

    public void setOdid(Long odid) {
        this.odid = odid;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public Long getItemnum() {
        return itemnum;
    }

    public void setItemnum(Long itemnum) {
        this.itemnum = itemnum;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(Long totalfee) {
        this.totalfee = totalfee;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getItemtitle() {
        return itemtitle;
    }

    public void setItemtitle(String itemtitle) {
        this.itemtitle = itemtitle == null ? null : itemtitle.trim();
    }
}