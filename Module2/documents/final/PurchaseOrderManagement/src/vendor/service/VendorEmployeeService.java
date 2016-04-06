package vendor.service;

import java.sql.SQLException;
import java.util.ArrayList;

import vendor.bean.User;
import vendor.bean.VendorEmployee;
import vendor.bean.VendorProduct;
import vendor.dao.VendorEmployeeDao;
import vendor.dao.VendorProductDao;

public class VendorEmployeeService {
	/**
	 * 
	 * @author nithin
	 *@classname  VendorEmployeeService
	 *@creationDate 19 july 2015
	 */
	public ArrayList<VendorEmployee> viewVendorEmployee(String vendorId) throws SQLException
	{
		/**
		 * viewVendorProduct
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorEmployeeDao employeeDao=new VendorEmployeeDao();
		return employeeDao.viewVendorEmployee(vendorId);
	}
	public User viewVendorEmployeeFromLogin(String loginId) throws SQLException
	{
		/**
		 * viewVendorProduct
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorEmployeeDao employeeDao=new VendorEmployeeDao();
		return employeeDao.viewVendorEmployeeFromLogin(loginId);
	}
	public void deleteVendorEmployee(String vendorId, String employeeId) throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * viewVendorProduct
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorEmployeeDao employeeDao=new VendorEmployeeDao();
		employeeDao.deleteVendorEmployee(vendorId,employeeId);
		
	}
	public VendorEmployee viewVendorEmployeeFromID(String employeeId) throws SQLException {
		/**
		 * viewVendorProduct
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorEmployeeDao employeeDao=new VendorEmployeeDao();
		return employeeDao.viewVendorEmployeeFromID(employeeId);
		
	}
	public void updateVendorEmployee(String employeeId, String employeeName,
			String password) throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * viewVendorProduct
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorEmployeeDao employeeDao=new VendorEmployeeDao();
		employeeDao.updateVendorEmployee(employeeId,employeeName,password);
	}
}
