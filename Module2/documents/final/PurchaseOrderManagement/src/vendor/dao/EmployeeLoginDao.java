package vendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import vendor.connection.DBConnection;

public class EmployeeLoginDao {
	/**
	 * @author heena
	 * @Class EmployeeLoginDao
	 * @CreationDate 18 July,2015
	 */

	private static final Logger logger = Logger
			.getLogger(EmployeeLoginDao.class);

	public String getLogin(String userId, String password) throws SQLException {
		/**
		 * retrieving the details of employee using USER_ID and PASSWORD
		 * 
		 * @param userId
		 * @param password
		 * @return String
		 * @throws SQLException
		 */

		/*
		 * 
		 * @Login Employee
		 */

		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			
			System.out.println(userId + " " + password);
			ps = connection
					.prepareStatement("select * from LOGIN where USER_ID = ? AND PASSWORD = ? AND ACCOUNT_TYPE = ?");
			logger.debug("retrieving the details of employee using USER_ID and PASSWORD"+userId);
			ps.setString(1, userId);
			ps.setString(2, password);
			ps.setString(3, "Vendor Employeer");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				PreparedStatement ps1 = connection
						.prepareStatement("select VENDOR_ID FROM EMPLOYEE WHERE EMP_ID = ?");
				logger.debug("retrieving vendorId from employee table for particular employeeId");
				ps1.setString(1, userId);
				ResultSet resultSet = ps1.executeQuery();
				if (resultSet.next()) {
					return resultSet.getString("VENDOR_ID");
				}
			} else {
				return "Failed";
			}
		} catch (SQLException e) {

			logger.error("employee not found", e);

		} finally {
			dbConnection.closeConnection(connection);
			dbConnection.closePreparedStatement(ps);
		}

		return "Failed";

	}

	// user1.setPassword(pass);
	// user1.setUserId(userIdd);
	// if(pass.equals(password) && userIdd.equals(userId)){

	// status = true;
	// }

}
