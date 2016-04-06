package vendor.bean;

public class Shipment {
	private String billNo;
	private String poNumber;
	private String truckNo;
	public Shipment(String billNo, String poNumber, String truckNo) {
		super();
		this.billNo = billNo;
		this.poNumber = poNumber;
		this.truckNo = truckNo;
	}
	public Shipment() {
		// TODO Auto-generated constructor stub
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	

}
