package vendor.sql;

public class Selection {
	public static final String selectPoOfVendor="SELECT * FROM PO WHERE PO_NO IN(SELECT PO_NO FROM PO_VENDOR WHERE V_NO=?) AND STATUS=?";
	public static final String selectPoFromPo="SELECT * FROM PO WHERE PO_NO=?";
	public static final String selectPoVendorFromPo="SELECT * FROM PO_VENDOR WHERE PO_NO=?";
	public static final String selectPoProductFromPo="SELECT * FROM PO_PRODUCT WHERE PO_NO=?";
	public static final String selectQuantityFromPoProduct="SELECT QUANTITY FROM PO_PRODUCT WHERE PRODUCT_ID=? AND PO_NO=?";
	public static final String deleteFromPoProduct="DELETE FROM PO_PRODUCT WHERE PRODUCT_ID=? AND PO_NO=?";
	public static final String updatePoProduct="UPDATE PO_PRODUCT SET QUANTITY=? WHERE PRODUCT_ID=? AND PO_NO=?";
	public static final String updatePoStatus="UPDATE PO SET STATUS=? WHERE PO_NO=?";
}
