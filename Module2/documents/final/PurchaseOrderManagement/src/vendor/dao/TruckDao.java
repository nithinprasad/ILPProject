package vendor.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vendor.sql.InsertDao;

import org.apache.log4j.Logger;

import vendor.bean.Shipment;
import vendor.bean.Truck;

import vendor.connection.DBConnection;

public class TruckDao {
	/**
	 * For adding,deleting,viewing all truck details as well as view and update truck by truck_id 
	 * @author 962769
	 *@classname  TruckDao
	 @creationDate 17 July, 2015
	 */ 
	private static final Logger logger = Logger.getLogger(TruckDao.class);

	public String addTruck(Truck truck) throws SQLException {
		/**
		 * For adding new truck 
		* @param year
	    * @param vendorId
	    * @return
	    * @throws SQLException
	 */
		/*
		 * 
		 * Adding truck
		 */
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		
		PreparedStatement preparedStatement;

		preparedStatement = connection
				.prepareStatement(InsertDao.insertTruck);
		preparedStatement.setString(1, truck.getTruckNo());
		preparedStatement.setString(2, truck.getOwner());
		preparedStatement.setInt(3, truck.getYear());
		
		System.out.println(truck.getTruckNo());
		
		System.out.println(truck.getOwner());

		System.out.println(truck.getYear());
		

		try {
			
			preparedStatement.executeUpdate();
			logger.debug("Adding new truck");
        	return "success"; 
		} catch (Exception e) {
			logger.error("Truck not Added",e);
			return "failed";
		}

		finally {
			dbConnection.closeConnection(connection);
			dbConnection.closePreparedStatement(preparedStatement);
		}
	}

	

	public String removeTruck(String tNo) throws SQLException {
		/**
		 * For removing truck using tNo and
		 * @param tNo
		 * @return
		 * @throws SQLException
		 */

		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement ps = connection
				.prepareStatement("DELETE FROM TRUCK WHERE T_NO=?");
		ps.setString(1, tNo);
		
		try {
			
			ps.executeUpdate();
			logger.debug("deleting particular truck");
			return "truck is successfully removed";
		} catch (Exception e) {
			logger.error("Truck not deleted",e);
			return "Failed";
		}

		finally {
			dbConnection.closeConnection(connection);
			dbConnection.closePreparedStatement(ps);
		}
	}

	/**
	 * 
	 * @returnArrayList<Truck>
	 * @throws SQLException
	 */

	public ArrayList<Truck> viewTruck(String vendorId) throws SQLException {
		/**
		 * Retrieving the details of truck corresponding to a particular vendor
		 * @param vendorId
		 * @return
		 * @throws SQLException
		 */

		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();

		ArrayList<Truck> truckList = new ArrayList<Truck>();
		PreparedStatement ps=null;
		try {
			
			 ps = connection
					.prepareStatement("select * from TRUCK where V_NO=?");
			 logger.debug("Retrieving the details of truck corresponding to a particular vendor");
			
			System.out.println(vendorId);
            ps.setString(1,vendorId);
            
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String truckNo = rs.getString("T_NO");
				String vendorNo = rs.getString("V_NO");
				int year = rs.getInt("YEAR");
				Truck truck = new Truck();
				truck.setTruckNo(truckNo);
				truck.setOwner(vendorNo);
				truck.setYear(year);
				truckList.add(truck);
			}
		} catch (SQLException e) {
			logger.error("No truck available for particular vendor",e);
		} finally {
			dbConnection.closeConnection(connection);
			dbConnection.closePreparedStatement(ps);
		}
		System.out.println("number of trucks matched:" + truckList.size());
		return truckList;

	}

	public Truck viewTruckTruckId(String truckId) throws SQLException {
		/**
		 * Retrieving the details of a particular truck using truckId 
		 * @param truckId
		 * @return
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
	
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM TRUCK WHERE T_NO=?");
		logger.debug("Retrieving the details of a particular truck using truckId ");
		
		preparedStatement.setString(1,truckId);
		
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			String truckNo = resultSet.getString("T_NO");
			String vendorNo = resultSet.getString("V_NO");
			int year = resultSet.getInt("YEAR");
			Truck truck = new Truck();
			truck.setTruckNo(truckNo);
			truck.setOwner(vendorNo);
			truck.setYear(year);
			return truck;

		}
		return null;
		
	}
	public ArrayList<Shipment> viewTrucKBillNo(String truckId) throws SQLException {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM SHIPMENT WHERE T_NO=? ORDER BY PO_NO");
		logger.debug("Retrieving the details of a particular truck and corresponding bill no using truckId "+truckId);
		Shipment shipment=new Shipment();
		
		preparedStatement.setString(1,truckId);
		ArrayList<Shipment> shipments=new ArrayList<Shipment>();
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			
			String billNo = resultSet.getString("BILL_NO");
			String poNumber = resultSet.getString("PO_NO");
			String truckNo = resultSet.getString("T_NO");
			
			Shipment shipment2=new Shipment(billNo, poNumber, truckNo);
			
			shipments.add(shipment2);

		}
		return shipments;
	}

	public void updateTruck(String truckId, int yearOfReg) throws SQLException {
		/**
		 * Updating year of registration of a truck using truckId
		 * @param vendorId
		 * @return
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
	
		PreparedStatement preparedStatement=connection.prepareStatement("UPDATE TRUCK SET YEAR=? WHERE T_NO=?");
		logger.debug("Updating year of registration of a truck using truckId");
		preparedStatement.setInt(1,yearOfReg);
		preparedStatement.setString(2,truckId);
		preparedStatement.execute();
		
	}

}
