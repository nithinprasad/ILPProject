package vendor.bean;

import java.sql.Date;

public class PurchaseOrder {
	
	private String poNo;
	private Date orderingDate;
	private String retailerName;
	private Date expectedDate;
	private String orderBy; 
	
	
	
	
	
	public PurchaseOrder(String poNo, Date orderingDate, String retailerName,
			Date expectedDate, String orderBy) {
		super();
		this.poNo = poNo;
		this.orderingDate = orderingDate;
		this.retailerName = retailerName;
		this.expectedDate = expectedDate;
		this.orderBy = orderBy;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
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
