package vendor.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import vendor.bean.Appointment;
import vendor.bean.POProduct;
import vendor.bean.PurchaseOrder;
import vendor.bean.Shipment;
import vendor.bean.VendorBill;
import vendor.bean.poVendor;
import vendor.dao.BillDao;
import vendor.dao.LoginDao;

public class BillService {
	/**
	 * 
	 * @author swedha
	 * @throws SQLException 
	 *@classname  BillService
	 *@creationDate 20 july 2015
	 */
	
	public ArrayList<PurchaseOrder> viewPO(String vendorId) throws SQLException
	{
		/**
		 * viewPO
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		BillDao billDao=new BillDao();
		return billDao.viewPO(vendorId);
	}

	public PurchaseOrder viewPONUmber(String poNumber) throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * viewPO
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		BillDao billDao=new BillDao();
		return billDao.viewPONUmber(poNumber);
	}

	public poVendor viewPOVendor(String poNumber) throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * viewPO
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		BillDao billDao=new BillDao();
		return billDao.viewPOVendor(poNumber);
	}

	public ArrayList<POProduct> viewPOProduct(String poNumber) throws SQLException {
		// TODO Auto-generated method stub
		
		/**
		 * viewPO
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		BillDao billDao=new BillDao();
		return billDao.viewPOProduct(poNumber);
		
		
	}
	public String updateQuantity(String poNumber, String productId,int quantity) throws SQLException{
		/**
		 * viewPO
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		BillDao billDao=new BillDao();
		return billDao.updateQuantity(poNumber, productId, quantity);
	}

	public void checkPOStatus(String poNumber) throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * viewPO
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		BillDao billDao=new BillDao();
		billDao.checkPOStatus(poNumber);
	}

	public String addBillDetails(String poNumber, double totalAmount,
			double totalQuantity) throws SQLException {
		/**
		 * viewPO
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		BillDao billDao=new BillDao();
		return billDao.addBillDetails(poNumber,totalAmount,totalQuantity);
	}

	public void addVendorBill(VendorBill vendorBill) throws SQLException {
	
		// TODO Auto-generated method stub
		BillDao billDao=new BillDao();
		billDao.addVendorBill(vendorBill);
		
	}
	
	public void fixAppointment(Appointment appointment) throws SQLException{
		BillDao billDao=new BillDao();
		billDao.fixAppointment(appointment);
	}

	public void addShipment(Shipment shipment)throws SQLException {
		// TODO Auto-generated method stub
		BillDao billDao=new BillDao();
		billDao.addShipment(shipment);
		
	}
	public ArrayList<Appointment> viewAppoinment(String vendorId) throws SQLException
	{
		BillDao billDao=new BillDao();
		return billDao.viewAppoinment(vendorId);
	}
	
	public long viewVendorPin(String vendorId) throws SQLException
	{
		BillDao billDao=new BillDao();
		return billDao.viewVendorPin(vendorId);
	}
	public long viewDeliveryPin(String billNo) throws SQLException
	{
		BillDao billDao=new BillDao();
		return billDao.viewDeliveryPin(billNo);
	}

	public void readyForShipment(String billNo) throws SQLException {
		// TODO Auto-generated method stub
		BillDao billDao=new BillDao();
		billDao.readyForShipment(billNo);
		
	}
	
}
