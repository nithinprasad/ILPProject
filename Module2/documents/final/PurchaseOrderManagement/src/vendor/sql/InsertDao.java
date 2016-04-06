package vendor.sql;

public class InsertDao {
	
	public static final String insertLogin="INSERT INTO LOGIN VALUES(?,?,?,?)";
	public static final String insertProduct="INSERT INTO PRODUCT VALUES(?,?,?)";
	public static final String insertStore="INSERT INTO STORE VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String insertStoreProduct="INSERT INTO STORE_PRODUCT VALUES(?,?,?,?)";
	public static final String insertDc="INSERT INTO DC VALUES(?,?,?,?,?,?,?,?)";
	public static final String insertDcStore="INSERT INTO DC_STORE VALUES(?,?)";
	public static final String insertDcProduct="INSERT INTO  DC_PRODUCT VALUES(?,?,?,?)";
	public static final String insertPo="INSERT INTO PO VALUES(?,?,?,?,?)"; 
	public static final String insertPoVendor="INSERT INTO PO_VENDOR  VALUES(?,?,?,?,?)";
	public static final String insertPoProduct="INSERT INTO PO_PRODUCT VALUES(?,?,?)";
	public static final String insertVendor="INSERT INTO VENDOR VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String insertVendorProduct="INSERT INTO VENDOR_PRODUCT VALUES(?,?,?,?)";
	public static final String insertVendorBill="INSERT INTO VENDOR_BILL VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String insertBillDetails="INSERT INTO BILL_DETAILS VALUES(?,?,?,?)";
	public static final String insertEmployee="INSERT INTO EMPLOYEE VALUES(?,?,?)";
	public static final String insertTruck="INSERT INTO TRUCK VALUES(?,?,?)";
	public static final String insertShipment="INSERT INTO SHIPMENT VALUES(?,?,?)";
	public static final String insertDeliveryDc="INSERT INTO DELIVERY_DC VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String insertDeliveryStore="INSERT INTO DELIVERY_STORE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	public static final String insertDefect="INSERT INTO DEFECT VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String insertRelProDefect="INSERT INTO REL_PRO_DEFECT VALUES(?,?,?,?)";
	public static final String insertAppointment="INSERT INTO APPOINTMENT VALUES(?,?,?,?,?)";
	public static final String insertStoreItemmanagement="INSERT INTO STORE_ITEM_MANAGEMENT VALUES(?,?,?,?,?,?)";
	public static final String insertRegistration="INSERT INTO REGISTRATION VALUES(?,?,?)";
}
