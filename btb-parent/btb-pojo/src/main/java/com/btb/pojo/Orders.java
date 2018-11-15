package com.btb.pojo;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long oid;			//订单id

    private Long payment;		//支付金额

    private Byte paymentType;	//支付类型

    private Date createTime;	//创建时间

    private Date updataTime;	//更新时间

    private Date paymentTime;	//支付时间
    
    private Date endTime;		//结束时间
    
    private Date closeTime;		//订单关闭时间

    private Long userid;		//订单用户id

    private Long storekeeperid;	//备货员id

    private Long empid;			//派送员id

    private String receiver;	//收货地址

    private String cellphone;	//联系电话

    private String remark;		//备注

    private String username;	//收货人名称

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public Byte getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Byte paymentType) {
        this.paymentType = paymentType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getStorekeeperid() {
        return storekeeperid;
    }

    public void setStorekeeperid(Long storekeeperid) {
        this.storekeeperid = storekeeperid;
    }

    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}