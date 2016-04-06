package vendor.service;

import java.sql.SQLException;
import java.util.ArrayList;

import vendor.bean.Product;
import vendor.bean.Truck;
import vendor.bean.VendorProduct;
import vendor.dao.TruckDao;
import vendor.dao.VendorProductDao;
/**
 * 
 * @author nithin
 *@classname  VendorProductService
 *@creationDate 18 july 2015
 */
public class VendorProductService {
	
	
	public ArrayList<VendorProduct> viewVendorProduct(String vendorId) throws SQLException
	{
		/**
		 * viewVendorProduct
		 * @param vendorId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorProductDao vendorProductDao=new VendorProductDao();
		return vendorProductDao.viewVendorProduct(vendorId);
	}
	
	
	public Product viewProduct(String productId) throws SQLException
	{
		/**
		 * viewProduct
		 * @param productId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorProductDao vendorProductDao=new VendorProductDao();
		return vendorProductDao.viewProduct(productId);
	}


	public VendorProduct viewVendorProductOnProductId(String productId) throws SQLException {
		/**
		 * viewVendorProductOnProductId
		 * @param productId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorProductDao vendorProductDao=new VendorProductDao();
		return vendorProductDao.viewVendorProductOnProductId(productId);
	}


	public void updateVendorProduct(VendorProduct vendorProduct) throws SQLException {
		/**
		 * updateVendorProduct
		 * @param VendorProduct vendorProduct
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorProductDao vendorProductDao=new VendorProductDao();
		vendorProductDao.updateVendorProduct(vendorProduct);
		
	}


	public String deleteVendorProductOnProductId(String vendorId, String productId) throws SQLException {
		/**
		 * deleteVendorProductOnProductId
		 * @param String vendorId
		 * @param String productId
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorProductDao vendorProductDao=new VendorProductDao();
		return vendorProductDao.deleteVendorProductOnProductId(vendorId,productId);
	}
	
	public ArrayList<Product> viewAllProducts(String venString) throws SQLException
	{
		/**
		 * viewAllProducts()
		 * @param 
		 * @param 
		 * @param 
		 * @return
		 * @throws SQLException
		 */
		VendorProductDao vendorProductDao=new VendorProductDao();
		return vendorProductDao.viewAllProducts(venString);
	}


	public VendorProduct viewVendorProductOnProductIdAndVendorId(
			String productId, String vendorId) throws SQLException {
		VendorProductDao vendorProductDao=new VendorProductDao();
		return vendorProductDao.viewVendorProductOnProductIdAndVendorId(productId, vendorId);
		
	}
	public ArrayList<VendorProduct> searchProduct(String prodId, String vendorId) throws SQLException {
		
	
		
		VendorProductDao vendorProductDao=new VendorProductDao();
		System.out.println("Service"+vendorId);
	 return	vendorProductDao.searchProduct(prodId.toUpperCase(),vendorId);
		
	}

	

}
