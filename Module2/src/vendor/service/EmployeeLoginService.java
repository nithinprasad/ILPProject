package vendor.service;

import java.sql.SQLException;

import vendor.dao.EmployeeLoginDao;

public class EmployeeLoginService {
	
	
	/**
	 * 
	 * @param userId
	 * @param password
	 * @return String
	 * @throws SQLException
	 */
	
	public String getLogin(String userId, String password) throws SQLException{
		
		EmployeeLoginDao employeeLoginDao = new EmployeeLoginDao();
		return employeeLoginDao.getLogin(userId, password);
		
	}
	
	public String getLoginForSession(String userId, String password) throws SQLException{
		
		EmployeeLoginDao employeeLoginDao = new EmployeeLoginDao();
		return employeeLoginDao.getLoginForSession(userId, password);
		
	}

}
