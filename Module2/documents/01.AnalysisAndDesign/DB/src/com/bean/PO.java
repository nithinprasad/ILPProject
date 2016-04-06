package com.bean;

import java.sql.Date;

public class PO {
	private String poNo;
	private Date orderingDate;
	private String retailerName;
	private Date expectedDate;
	private String orderbyDCNo
	;
	public String getOrderbyDCNo() {
		return orderbyDCNo;
	}
	public void setOrderbyDCNo(String orderbyDCNo) {
		this.orderbyDCNo = orderbyDCNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public Date getOrderingDate() {
		return orderingDate;
	}
	public void setOrderingDate(Date orderingDate) {
		this.orderingDate = orderingDate;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	
	
	
}
