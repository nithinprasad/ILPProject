package vendor.service;

import java.sql.SQLException;
import java.util.ArrayList;

import vendor.bean.PurchaseOrder;
import vendor.bean.User;
import vendor.bean.Vendor;
import vendor.bean.VendorEmployee;
import vendor.bean.VendorProduct;
import vendor.dao.VendorDao;
import vendor.dao.VendorEmployeeDao;

public class VendorService {
	/**
	 * @author shweta
	 * @class name VendorService
	 * @createDate 17july2015
	 */
	public String addVendor(Vendor vendor, User user) throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * @param <vendor><object of class Vendor>
		 * @param <user><object of class User>
		 * @return <String><returns a message>
		 * @throws SQLException
		 * @see calling to Dao class function(addVendor)
		 */
		VendorDao vendorDao = new VendorDao();
		return vendorDao.addVendor(vendor, user);

	}

	public ArrayList<PurchaseOrder> fetchPO(String vendorId)
			throws SQLException {
		/**
		 * @param <String>< vendor id>
		 * @return <ArrayList><returns a list of orders>
		 * @throws SQLException
		 * @see calling to dao class function(fetchPO)
		 */

		VendorDao vendorDao = new VendorDao();
		return vendorDao.fetchPO(vendorId);

	}

	/*
	 * public Vendor viewVendor(int v_no) throws SQLException { VendorDao
	 * vDao=new VendorDao(); return vDao.viewVendor(v_no);
	 * 
	 * } public void addUser(User u) throws SQLException { VendorDao vDao=new
	 * VendorDao(); vDao.addUser(u); }
	 */
	public String addVendorEmployee(VendorEmployee vEmployee, User user)
			throws SQLException {

		/**
		 * @param <vEmployee><vEmployee is the object of VendorEmployee >
		 * @param <user><user is the object of User >
		 * @return <String><returns a message of add employee>
		 * @throws SQLException
		 * 
		 */

		VendorEmployeeDao vEmployeeDao = new VendorEmployeeDao();
		return vEmployeeDao.addVendorEmployee(vEmployee, user);
	}
	public ArrayList<VendorEmployee> viewEmployee(String employeeId) throws SQLException
	{
		VendorEmployeeDao vendoremployeedao = new VendorEmployeeDao();
		ArrayList<VendorEmployee> employeelist = new ArrayList<VendorEmployee>();
		employeelist = vendoremployeedao.viewEmployee(employeeId);
		return employeelist;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * Add Product to a particular vendor Nithin Prasad
	 */
	public String addProductToVendor(VendorProduct vendorProduct)
			throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * @param <vendorProduct><vendorProduct is the object of VendorProduct>
		 * @return <String><return a message>
		 * @throws SQLException
		 */
		VendorDao vendorDao = new VendorDao();
		return vendorDao.addProductToVendor(vendorProduct);
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * Add Product to a particular vendor Nithin Prasad
	 */

	public int addEmployee(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
}
