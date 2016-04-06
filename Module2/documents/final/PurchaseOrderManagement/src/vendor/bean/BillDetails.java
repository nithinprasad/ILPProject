package vendor.bean;
/**
 * @author mrunali
 * @class name VendorService
 * @createDate 21july2015
 */
public class BillDetails {

	private String billNo;
	private String poNo;
	private double totalAmount;
	private double quantity;
	
	
	public BillDetails(String billNo, String poNo, double totalAmount,
			double quantity) {
		super();
		this.billNo = billNo;
		this.poNo = poNo;
		this.totalAmount = totalAmount;
		this.quantity = quantity;
	}

	public String getBillNo() {
		return billNo;
	}
	
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public String getPoNo() {
		return poNo;
	}
	
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
