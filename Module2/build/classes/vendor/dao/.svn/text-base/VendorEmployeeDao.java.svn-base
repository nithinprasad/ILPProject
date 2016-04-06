package vendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import vendor.bean.User;
import vendor.bean.VendorEmployee;
import vendor.bean.VendorProduct;
import vendor.connection.DBConnection;
import vendor.sql.InsertDao;

public class VendorEmployeeDao {
	/**
	 * @author mrunali
	 * @classname VendorEmployeeDao
	 * @creationDate 17 july 2015
	 **/
	private static final Logger logger = Logger
			.getLogger(VendorEmployeeDao.class);

	public String addVendorEmployee(VendorEmployee vEmployee, User user)
			throws SQLException {
		/**
		 * For adding new vendor employee
		 * 
		 * @param vEmployee
		 * @param user
		 * @return string
		 * @throws SQLException
		 */

		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		/*
		 * 
		 * Adding Vendor Employee
		 */
		InsertDao insertDao = new InsertDao();
		PreparedStatement preparedStatement = connection
				.prepareStatement(insertDao.insertEmployee);

		preparedStatement.setString(1, vEmployee.getEmployeeId());
		System.out.println(vEmployee.getEmployeeId());
		preparedStatement.setString(2, vEmployee.getEmployeeName());
		System.out.println(vEmployee.getEmployeeName());
		preparedStatement.setString(3, vEmployee.getVendorNumber());
		System.out.println(vEmployee.getVendorNumber());

		/*
		 * 
		 * Adding User
		 */

		PreparedStatement preparedStatement2 = connection
				.prepareStatement(insertDao.insertLogin);
		preparedStatement2.setString(1, user.getUserId());
		preparedStatement2.setString(2, user.getPassword());
		preparedStatement2.setString(3, user.getAccountType());
		preparedStatement2.setString(4, user.getStatus());

		try {
			preparedStatement.executeUpdate();
			logger.debug("vendor employee added");
			try {
				
				preparedStatement2.executeUpdate();
				logger.debug("user added");
				return "Sucess";
			} catch (Exception userException) {

				logger.error("user not added", userException);
				return "Failed";
			}

		} catch (Exception e) {
			logger.error("vendor employee not added", e);
			return "Failed";

		} finally {
			dbConnection.closeConnection(connection);
			dbConnection.closePreparedStatement(preparedStatement);
			dbConnection.closePreparedStatement(preparedStatement2);
		}
	}

	public ArrayList<VendorEmployee> viewEmployee(String employeeId)
			throws SQLException {
		/**
		 * retrieving details of particular employee
		 * 
		 * @param employeeId
		 * @return string
		 * @throws SQLException
		 */

		DBConnection dbConnection = new DBConnection();

		Connection connection = dbConnection.getConnection();
		ArrayList employeelist = new ArrayList();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from EMPLOYEE WHERE EMP_ID=?");
		preparedStatement.setString(1, employeeId);
		try {
			
			ResultSet rs = preparedStatement.executeQuery();
			logger.debug("details of particular employee retrieved");
			while (rs.next()) {
				String employeeid = rs.getString(1);
				String employeeName = rs.getString(2);
				String vendorNumber = rs.getString(3);
				VendorEmployee vEmployee = new VendorEmployee(employeeid,
						employeeName, vendorNumber);
				vEmployee.setEmployeeId(employeeid);
				vEmployee.setEmployeeName(employeeName);
				vEmployee.setVendorNumber(vendorNumber);

				employeelist.add(vEmployee);
			}
		} catch (Exception e) {
			logger.error("employee does not exist", e);
		} finally {
			dbConnection.closeConnection(connection);
			dbConnection.closePreparedStatement(preparedStatement);

		}
		return employeelist;

	}

	public void deleteEmployee(String employeeId) throws SQLException {
		/**
		 * deleting a particular employee
		 * 
		 * @param vEmployee
		 * @param user
		 * @return string
		 * @throws SQLException
		 */

		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from EMPLOYEE where EmployeeId = ?");
		try {
			
			preparedStatement.executeUpdate();
			logger.debug("particular employee deleted");
		} catch (Exception e) {
			logger.error("employee does not exist", e);
		} finally {
			dbConnection.closeConnection(connection);
			dbConnection.closePreparedStatement(preparedStatement);

		}
	}

	public ArrayList<VendorEmployee> viewVendorEmployee(String vendorId)
			throws SQLException {

		/**
		 * retrieving employee details of particular vendor
		 * 
		 * @param vEmployee
		 * @param user
		 * @return string
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		ArrayList<VendorEmployee> arrayList = new ArrayList<VendorEmployee>();
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM EMPLOYEE WHERE VENDOR_ID=?");
		logger.debug("fetching employee details of particular vendor");
		preparedStatement.setString(1, vendorId);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String employeeId = resultSet.getString(1);
			String employeeName = resultSet.getString(2);
			String vendorNumber = resultSet.getString(3);
			VendorEmployee employee = new VendorEmployee(employeeId,
					employeeName, vendorNumber);
			arrayList.add(employee);

		}
		return arrayList;
	}

	public User viewVendorEmployeeFromLogin(String loginId) throws SQLException {

		/**
		 * fetching details of particular user
		 * @param vEmployee
		 * @param user
		 * @return string
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM LOGIN WHERE USER_ID=?");
		logger.debug("fetching details of particular user");
		preparedStatement.setString(1, loginId);
		ResultSet resultSet = preparedStatement.executeQuery();
		User user = null;
		while (resultSet.next()) {
			String userId = resultSet.getString(1);
			String password = resultSet.getString(2);
			String accountType = resultSet.getString(3);
			String status = resultSet.getString(4);
			user = new User(userId, password, accountType, status);
		}
		return user;
	}

	public String deleteVendorEmployee(String vendorId, String employeeId)
			throws SQLException {
		// TODO Auto-generated method stub
		/**
		 *  deleting particular employee
		 * @param vEmployee
		 * @param user
		 * @return string
		 * @throws SQLException
		 */
		int status = 0;
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM EMPLOYEE WHERE EMP_ID=?");
		logger.debug("particular employee deleted");
		preparedStatement.setString(1, employeeId);
		PreparedStatement preparedStatement2 = connection
				.prepareStatement("DELETE FROM LOGIN WHERE USER_ID=?");
		preparedStatement2.setString(1, employeeId);
		status = preparedStatement.executeUpdate();
		preparedStatement2.execute();
		if(status>0){
			
			return "Success";
		}
		
		return "Failed";

	}

	public VendorEmployee viewVendorEmployeeFromID(String employeeId)
			throws SQLException {

		/**
		 * retrieving particular employee details
		 * 
		 * @param vEmployee
		 * @param user
		 * @return string
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMP_ID=?");
		logger.debug("retrieving particular employee details");
		preparedStatement.setString(1, employeeId);
		ResultSet resultSet = preparedStatement.executeQuery();
		VendorEmployee employee = null;
		while (resultSet.next()) {
			String employeeId2 = resultSet.getString(1);
			String employeeName = resultSet.getString(2);
			String vendorNumber = resultSet.getString(3);
			employee = new VendorEmployee(employeeId2, employeeName,
					vendorNumber);
		}
		return employee;
	}

	public void updateVendorEmployee(String employeeId, String employeeName,
			String password) throws SQLException {
		/**
		 * Updating particular employee details
		 * 
		 * @param vEmployee
		 * @param user
		 * @return string
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
	
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE LOGIN SET PASSWORD=? WHERE USER_ID=?");
		logger.debug("updating details of particular employee");
		PreparedStatement preparedStatement2 = connection
				.prepareStatement("UPDATE EMPLOYEE SET EMP_NAME=? WHERE EMP_ID=?");
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, employeeId);
		preparedStatement2.setString(1, employeeName);
		preparedStatement2.setString(2, employeeId);
		preparedStatement.execute();
		preparedStatement2.execute();

	}

	public ArrayList<VendorEmployee> searchEmployee(String empId,String vendorId) throws SQLException{
		// TODO Auto-generated method stub
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
	
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMP_ID LIKE ? AND VENDOR_ID=?");
		logger.debug("Retrieving the details of a particular employee search ");
		System.out.println("------------------------------------"+empId);
		preparedStatement.setString(1,"%"+empId+"%");
		preparedStatement.setString(2, vendorId);
		ResultSet resultSet=preparedStatement.executeQuery();
		ArrayList<VendorEmployee> employeeList = new ArrayList<VendorEmployee>();
		while(resultSet.next())
		{
			String empid= resultSet.getString(1);
			String empName = resultSet.getString(2);
			String Vno = resultSet.getString(3);
			
			VendorEmployee employee=new VendorEmployee();
			employee.setEmployeeId(empid);
		    employee.setEmployeeName(empName);
		    employee.setVendorNumber(Vno);
			employeeList.add(employee);

		}
		System.out.println("------------------------------------"+employeeList.size());
		return employeeList;
		
	}

}
