package vendor.service;

import java.sql.SQLException;
import java.util.ArrayList;

import vendor.bean.Shipment;
import vendor.bean.Truck;
import vendor.dao.TruckDao;


public class TruckService {
	
	/**
	 * @author 971204
	 * @param truck
	 * @param vendorId
	 * @return String
	 * @throws SQLException
	 */
	
	public String addTruck(Truck truck) throws SQLException{
		
		TruckDao truckDao = new TruckDao();
		return truckDao.addTruck(truck);
		
	}

	public ArrayList<Truck> viewTruck(String vendorId) throws SQLException {
		TruckDao truckDao = new TruckDao();
		System.out.println("Service"+vendorId);
		return truckDao.viewTruck(vendorId) ;
	}
public String removeTruck(String truckRegNo) throws SQLException{
		
		TruckDao truckDao = new TruckDao();
		return truckDao.removeTruck(truckRegNo);
		
	}

public Truck viewTruckTruckId(String truckId) throws SQLException {
	// TODO Auto-generated method stub
	TruckDao truckDao = new TruckDao();
	return truckDao.viewTruckTruckId(truckId);
}

public void updateTruck(String truckId, int yearOfReg) throws SQLException {
	// TODO Auto-generated method stub
	TruckDao truckDao = new TruckDao();
	truckDao.updateTruck(truckId,yearOfReg);
}
public ArrayList<Shipment> viewTrucKBillNo(String truckId) throws SQLException {
	TruckDao truckDao = new TruckDao();
	return truckDao.viewTrucKBillNo(truckId);
}


}
