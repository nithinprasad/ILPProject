package vendor.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import vendor.bean.User;
import vendor.connection.DBConnection;
import vendor.servlet.LoginController;

public class LoginDao {
	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	private static final Logger logger = Logger.getLogger(LoginDao.class);

	public String getLogin(String userId, String password) throws SQLException {
		/**
		 * Retrieving the details of vendor using USER_ID and PASSWORD from
		 * LOGIN table and retrieving vendor number corresponding to vendor from
		 * VENDOR table
		 * 
		 * @author HEENA
		 * @classname LoginDao
		 * @creationDate 17 july 2015
		 **/

		boolean status = false;
		// User user1 = new User();
		String pass = null;
		String userIdd = null;
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement ps = null;
		/*
		 * 
		 * Adding Vendor
		 */
		try {
			
			ps = connection
					.prepareStatement("select * from LOGIN where USER_ID = ? AND PASSWORD = ?");
			logger.debug("Fetching userId and password from Login table");
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT V_NO FROM VENDOR WHERE VENDOR_MANAGER=? ");
				logger.debug("Fetching vendorId from VENDOR table with respect to vendor manager");
				preparedStatement.setString(1, userId);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					return resultSet.getString(1);
				}

			} else {
				return "Failed";
			}

			// user1.setPassword(pass);
			// user1.setUserId(userIdd);
			// if(pass.equals(password) && userIdd.equals(userId)){

			// status = true;
			// }

		} catch (SQLException e) {

			logger.error("vendor does not exist", e);
		} finally {
			dbConnection.closeConnection(connection);
			dbConnection.closePreparedStatement(ps);
		}

		return "Failed";
	}

}
