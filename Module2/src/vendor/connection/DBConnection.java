package vendor.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import vendor.dao.BillDao;

public class DBConnection {
	Connection con = null;
	private static final Logger logger = Logger.getLogger(DBConnection.class);

	public Connection getConnection() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String dbUrl = "jdbc:oracle:thin:@intvmoradb02:1521:ORAJAVADB";

		// CONNECTION SETUP
		String dbUserName = "PJ02DEV_TJA18";
		String dbPassword = "tcstvm";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			

			return con;
		} catch (Exception e) {
			logger.error("Problem occured while connecting to database", e);

		}
		return con;

	}

	public void closeConnection(Connection connection) throws SQLException {

		if (connection != null)
			try {

				connection.close();

			} catch (Exception e) {

				logger.error("Problem occured while closing connection", e);

			}

	}

	public void closePreparedStatement(PreparedStatement pst)
			throws SQLException {

		if (pst != null)

			try {

				pst.close();

			} catch (Exception e) {

				logger.error(
						"Problem occured while closing prepared statement", e);

			}

	}

}
