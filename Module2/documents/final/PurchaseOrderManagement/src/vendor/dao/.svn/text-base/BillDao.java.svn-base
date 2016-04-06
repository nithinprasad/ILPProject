package vendor.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import vendor.bean.Appointment;
import vendor.bean.POProduct;
import vendor.bean.PurchaseOrder;
import vendor.bean.Shipment;
import vendor.bean.VendorBill;
import vendor.bean.poVendor;
import vendor.connection.DBConnection;
import vendor.sql.Selection;

public class BillDao {
	/**
	 * @author sweadha
	 * @throws SQLException
	 * @classname BillDao
	 * @creationDate 20 july 2015
	 */

	private static final Logger logger = Logger.getLogger(BillDao.class);

	public ArrayList<PurchaseOrder> viewPO(String vendorId) throws SQLException {
		/**
		 * For viewing the details of purchase order corresponding to particular
		 * vendor
		 * 
		 * @param vendorId
		 * @return ArrayList
		 * @throws SQLException
		 */

		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		
		PreparedStatement preparedStatement = connection
				.prepareStatement(Selection.selectPoOfVendor);
		logger.debug("For fetching the details of purchase order corresponding to particular vendor"+vendorId);
		preparedStatement.setString(1, vendorId);
		preparedStatement.setString(2, "APPROVED");
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
		while (resultSet.next()) {
			String poNo = resultSet.getString(1);
			Date orderingDate = resultSet.getDate(2);
			String retailerName = resultSet.getString(3);
			Date expectedDate = resultSet.getDate(4);
			String orderBy = resultSet.getString(5);
			PurchaseOrder purchaseOrder = new PurchaseOrder(poNo, orderingDate,
					retailerName, expectedDate, orderBy);
			purchaseOrders.add(purchaseOrder);
		}
		return purchaseOrders;

	}

	public PurchaseOrder viewPONUmber(String poNumber) throws SQLException {
		/**
		 * For viewing the details of purchase order corresponding to a
		 * particular poNumber
		 * 
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement(Selection.selectPoFromPo);
		logger.debug("fetching details of purchase order corresponding to a particular poNumber" +poNumber);
		preparedStatement.setString(1, poNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String poNo = resultSet.getString(1);
			Date orderingDate = resultSet.getDate(2);
			String retailerName = resultSet.getString(3);
			Date expectedDate = resultSet.getDate(4);
			String orderBy = resultSet.getString(5);
			PurchaseOrder purchaseOrder = new PurchaseOrder(poNo, orderingDate,
					retailerName, expectedDate, orderBy);
			return purchaseOrder;
		}
		return null;
	}

	public poVendor viewPOVendor(String poNumber) throws SQLException {
		/**
		 * For retrieving the details of purchase order using poNumber
		 * corresponding to particular vendorNumber
		 * 
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement(Selection.selectPoVendorFromPo);
		logger.debug("retrieving the details of purchase order using poNumber corresponding to particular vendorNumber"+poNumber);
		preparedStatement.setString(1, poNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String vNo = resultSet.getString(1);
			String poNo = resultSet.getString(2);
			String directToStore = resultSet.getString(3);
			String address1 = resultSet.getString(4);
			String address2 = resultSet.getString(5);
			String pincode = resultSet.getString(6);
			String city = resultSet.getString(7);
			String state = resultSet.getString(8);
			String landmark = resultSet.getString(9);
			poVendor poVendor = new poVendor(poNo, vNo, directToStore,
					address1, address2, pincode, city, state, landmark);
			return poVendor;
		}
		return null;

	}

	public ArrayList<POProduct> viewPOProduct(String poNumber)
			throws SQLException {
		/**
		 * For viewing the details of all products corresponding to poNumber
		 * 
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement(Selection.selectPoProductFromPo);
		logger.debug("fetching details of all products corresponding to particular poNumber"+poNumber);
		preparedStatement.setString(1, poNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<POProduct> poProducts = new ArrayList<POProduct>();
		while (resultSet.next()) {
			String poNo = resultSet.getString(1);
			String productId = resultSet.getString(2);
			Double quantity = resultSet.getDouble(3);
			POProduct poProduct = new POProduct(poNo, productId, quantity);
			poProducts.add(poProduct);
		}
		return poProducts;
	}

	public String updateQuantity(String poNumber, String productId, int quantity)
			throws SQLException {
		/**
		 * For updating the quantity corresponding to poNumber and particular productId
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		int newQuantity = 0;
		String status = "updated";
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement(Selection.selectQuantityFromPoProduct);
		logger.debug("fetching quantity from PO_PRODUCT corresponding to particular poNumber and productId"+poNumber+" "+productId);
		preparedStatement.setString(1, productId);
		preparedStatement.setString(2, poNumber);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			newQuantity = resultSet.getInt("QUANTITY");
		}
		newQuantity = newQuantity - quantity;
		System.out.println("New" + newQuantity);

		if (newQuantity == 0) {
			
			PreparedStatement preparedStatement2 = connection
					.prepareStatement(Selection.deleteFromPoProduct);
			logger.debug("deleting PO_PRODUCT when quantity is 0"+poNumber+" "+productId);
			preparedStatement2.setString(1, productId);
			preparedStatement2.setString(2, poNumber);
			preparedStatement2.executeUpdate();
			return "unavailable stock";
		} else {
			
			PreparedStatement preparedStatement1 = connection
					.prepareStatement(Selection.updatePoProduct);
			logger.debug("updating quantity of PO_PRODUCT"+productId+"Quantity "+quantity+"PO Number"+poNumber);
			preparedStatement1.setInt(1, newQuantity);
			preparedStatement1.setString(2, productId);
			preparedStatement1.setString(3, poNumber);
			preparedStatement1.executeUpdate();
			status = "updated";
		}
		return status;

	}

	public void checkPOStatus(String poNumber) throws SQLException {
		
		/**
		 * For retrieving the details of purchase order using poNumber
		 * corresponding to particular vendorNumber
		 * 
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement(Selection.selectPoProductFromPo);
		logger.debug("retrieving the details of purchase order using poNumber"+poNumber);
		preparedStatement.setString(1, poNumber);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

		} else {
			
			PreparedStatement preparedStatement2 = connection
					.prepareStatement(Selection.updatePoStatus);
			logger.debug("updating status CLOSED of PO for particular PO_NO"+poNumber);
			preparedStatement2.setString(1, "CLOSED");
			preparedStatement2.setString(2, poNumber);
			preparedStatement2.execute();
		}

	}

	

	public String addBillDetails(String poNumber, double totalAmount,
			double totalQuantity) throws SQLException {
		/**
		 * For retrieving the details of purchase order using poNumber
		 * corresponding to particular vendorNumber
		 * 
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		String billNo = "";
		
		PreparedStatement ps = connection
				.prepareStatement("select 'BILL'||BILL_SEQ.nextval FROM dual");
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {

			billNo = rs.getString(1);
			System.out.println(billNo);
			logger.debug("generating bill no"+billNo);
		}
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO BILL_DETAILS VALUES(?,?,?,?)");
		logger.debug("inserting values into Bill_Details"+billNo);
		preparedStatement.setString(1, billNo);
		preparedStatement.setString(2, poNumber);
		preparedStatement.setDouble(3, totalAmount);
		preparedStatement.setDouble(4, totalQuantity);
		preparedStatement.execute();
		return billNo;

	}

	public void addVendorBill(VendorBill vendorBill) throws SQLException {
		/**
		 * For inserting values in vendor bill
		 * corresponding to particular vendorNumber
		 * 
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
	
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement ps = connection
				.prepareStatement("INSERT INTO VENDOR_BILL VALUES(?,?,?,?,?,?,?,?,?)");
		logger.debug("inserting values in VENDOR_BILL"+vendorBill.getBillNo());
		ps.setString(1, vendorBill.getPoNo());
		ps.setString(2, vendorBill.getVendorNo());
		ps.setString(3, vendorBill.getBillNo());
		ps.setString(4, vendorBill.getTypeOfDelivery());
		ps.setString(5, vendorBill.getStatus());
		ps.setString(6, vendorBill.getReadyForShipment());
		ps.setString(7, vendorBill.getDirectToStore());
		ps.setDate(8, vendorBill.getDateOFDelivery());
		ps.setString(9, vendorBill.getOrderBy());
		ps.execute();

	}

	public void fixAppointment(Appointment appointment) throws SQLException {
		/**
		 * For inserting appointment details
		 * corresponding to particular vendorNumber
		 * 
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		logger.debug("inserting values in Appointment table Bill No"+appointment.getPoNo());
		PreparedStatement ps = connection
				.prepareStatement("INSERT INTO APPOINTMENT VALUES(?,?,?,?,?,?)");

		ps.setString(1, appointment.getPoNo());
		ps.setString(2, appointment.getDcNo());
		ps.setString(3, appointment.getvNo());
		ps.setDate(4, appointment.getExpectedDate());
		ps.setDate(5, appointment.getFixedDate());
		ps.setString(6, appointment.getStatus());
		ps.execute();

	}

	public void addShipment(Shipment shipment) throws SQLException {
		/**
		 * inserting shipment details
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		logger.debug("inserting values in SHIPMENT table "+shipment.getBillNo());
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO SHIPMENT VALUES(?,?,?)");
		preparedStatement.setString(1, shipment.getBillNo());
		preparedStatement.setString(2, shipment.getPoNumber());
		preparedStatement.setString(3, shipment.getTruckNo());
		preparedStatement.execute();
	}

	public ArrayList<Appointment> viewAppoinment(String vendorId)
			throws SQLException {
		/**
		 * fetching appointment details of particular vendor
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		logger.debug("fetching appointment details of particular vendor"+vendorId);
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM APPOINTMENT WHERE V_NO=? AND BILL_NO IN(SELECT BILL_NO FROM VENDOR_BILL WHERE READY_FOR_SHIPMENT='NO') ORDER BY STATUS ,BILL_NO ASC");
		preparedStatement.setString(1, vendorId);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		while (resultSet.next()) {
			String poNo = resultSet.getString(1);
			String dcNo = resultSet.getString(2);
			String vNo = resultSet.getString(3);
			Date expectedDate = resultSet.getDate(4);
			Date fixedDate = resultSet.getDate(5);
			String status = resultSet.getString(6);
			Appointment appointment = new Appointment(poNo, dcNo, vNo,
					expectedDate, fixedDate, status);
			appointments.add(appointment);
		}
		return appointments;
	}

	public long viewVendorPin(String vendorId) throws SQLException {
		/**
		 *fetching pincode of particular vendor
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		long pincode = 0;
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT PINCODE FROM VENDOR WHERE V_NO = ?");
		
		preparedStatement.setString(1, vendorId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			pincode = resultSet.getLong("PINCODE");
			logger.debug("fetching pincode of particular vendor"+pincode);
		}
		
		return pincode;
	}

	public long viewDeliveryPin(String billNo) throws SQLException {
		/**
		 *fetching pincode corresponding to poNumber and billNo
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		
		long pincode = 0;
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT PINCODE FROM PO_VENDOR WHERE PO_NO IN(SELECT PO_NO FROM BILL_DETAILS WHERE BILL_NO = ?)");
		
		preparedStatement.setString(1, billNo);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			pincode = resultSet.getLong("PINCODE");
			logger.debug("fetching pincode corresponding to poNumber and billNo"+billNo);
		}

		return pincode;
	}

	public void readyForShipment(String billNo) throws SQLException {
		// TODO Auto-generated method stub
		/**
		 *fetching pincode corresponding to poNumber and billNo
		 * @param poNumber
		 * @return
		 * @throws SQLException
		 */
		
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("UPDATE VENDOR_BILL SET READY_FOR_SHIPMENT='YES' WHERE BILL_NO=?");
		preparedStatement.setString(1,billNo);
		preparedStatement.execute();
		logger.debug("Ready For shipment flag is set to YES"+billNo);
	}
}