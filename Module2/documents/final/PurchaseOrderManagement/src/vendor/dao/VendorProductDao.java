package vendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import vendor.bean.Product;
import vendor.bean.VendorProduct;
import vendor.connection.DBConnection;

/**
 * 
 * @author nithin
 * @classname VendorProductDao
 * @creationDate 18 july 2015
 */
public class VendorProductDao {
	private static final Logger logger = Logger.getLogger(VendorProductDao.class);
	
	
	public ArrayList<VendorProduct> viewVendorProduct(String vendorId)
			throws SQLException {
		/**
		 * 
		 * @see this function will return arraylist which contain the details of
		 *      vendor product.
		 * @param vendorId
		 * @return
		 * @throws SQLException
		 */
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		/*
		 * 
		 * 
		 * 
		 */
		ArrayList<VendorProduct> arrayList = new ArrayList<VendorProduct>();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM VENDOR_PRODUCT WHERE V_NO=?");
		logger.debug("fetching product of particular vendor");
		preparedStatement.setString(1, vendorId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String v_NO = resultSet.getString(1);
			String products = resultSet.getString(2);
			int vat = resultSet.getInt(3);
			double serviceCharge = resultSet.getDouble(4);
			VendorProduct vendorProduct = new VendorProduct(v_NO, products,
					vat, serviceCharge);
			arrayList.add(vendorProduct);
		}
		return arrayList;

	}
	
	

	public Product viewProduct(String productId) throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * 
		 * This function will return product details by taking productId as a
		 * parameter.
		 * 
		 * @param productId
		 * @return
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		/*
		 * 
		 * 
		 * 
		 */
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_ID=?");
		logger.debug("fetching product details corresponding to productId");
		preparedStatement.setString(1, productId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String productIdFromDB = resultSet.getString(1);
			String productName = resultSet.getString(2);
			Double unitPrice = resultSet.getDouble(3);
			Product product = new Product();
			product.setProductId(productIdFromDB);
			product.setProductName(productName);
			product.setUnitPrice(unitPrice);
			return product;
		}
		return null;
	}

	public VendorProduct viewVendorProductOnProductId(String productId)
			throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * this function will retrieve the details of vendorProduct by taking
		 * productId as parameter.
		 * 
		 * @param vendorId
		 * @return
		 * @throws SQLException
		 */
		// TODO Auto-generated method stub
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		/*
		 * 
		 * 
		 * 
		 */
       
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM VENDOR_PRODUCT WHERE PRODUCT_ID=?");
		 logger.debug("fetching details of vendor product for particular product Id");
		preparedStatement.setString(1, productId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {

			String v_NO = resultSet.getString(1);
			String products = resultSet.getString(2);
			int vat = resultSet.getInt(3);
			double serviceCharge = resultSet.getDouble(4);
			VendorProduct vendorProduct = new VendorProduct(v_NO, products,
					vat, serviceCharge);
			return vendorProduct;
		}
		return null;

	}
	
	public VendorProduct viewVendorProductOnProductIdAndVendorId(String productId,String vendorId)
			throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * this function will retrieve the details of vendorProduct by taking
		 * productId as parameter.
		 * 
		 * @param vendorId
		 * @return
		 * @throws SQLException
		 */
		// TODO Auto-generated method stub
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		/*
		 * 
		 * 
		 * 
		 */
      
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM VENDOR_PRODUCT WHERE PRODUCT_ID=? AND V_NO=?");
		 logger.debug("fetching details of vendor product corresponding to particular vendorId and productId");
		preparedStatement.setString(1, productId);
		preparedStatement.setString(2, vendorId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {

			String v_NO = resultSet.getString(1);
			String products = resultSet.getString(2);
			int vat = resultSet.getInt(3);
			double serviceCharge = resultSet.getDouble(4);
			VendorProduct vendorProduct = new VendorProduct(v_NO, products,
					vat, serviceCharge);
			return vendorProduct;
		}
		return null;

	}

	public void updateVendorProduct(VendorProduct vendorProduct)
			throws SQLException {

		/**
		 * this function will update the vendorProduct details
		 * 
		 * @param vendorProduct
		 * @return void
		 * @throws SQLException
		 */
        
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		System.out.println(vendorProduct.getProducts() + " "
				+ vendorProduct.getV_NO() + " " + vendorProduct.getVat() + " "
				+ vendorProduct.getServiceCharge());
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE VENDOR_PRODUCT SET VAT=?,SERVICE=? WHERE V_NO=? AND PRODUCT_ID=?");
		logger.debug("vendor product updated");
		preparedStatement.setInt(1, vendorProduct.getVat());
		preparedStatement.setDouble(2, vendorProduct.getServiceCharge());
		preparedStatement.setString(3, vendorProduct.getV_NO());
		preparedStatement.setString(4, vendorProduct.getProducts());
		try {
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void deleteVendorProductOnProductId(String vendorId, String productId)
			throws SQLException {
		/**
		 * this function will delete the vendorProduct details
		 * 
		 * @param vendorProduct
		 * @return void
		 * @throws SQLException
		 */

		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		System.out.println(vendorId);
		System.out.println(productId);
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM VENDOR_PRODUCT  WHERE V_NO=? AND PRODUCT_ID=?");
		logger.debug("vendor product deleted");
		preparedStatement.setString(1, vendorId);
		preparedStatement.setString(2, productId);
		preparedStatement.execute();

	}

	public ArrayList<Product> viewAllProducts(String vendorId)
			throws SQLException {
		/**
		 * this function will fetch products not added to particular vendor
		 * @param vendorProduct
		 * @return void
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		ArrayList<Product> arrayList = new ArrayList<Product>();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_ID NOT IN(SELECT PRODUCT_ID FROM VENDOR_PRODUCT WHERE V_NO=?)");
		logger.debug("fetching products not added to particular vendor");
		preparedStatement.setString(1, vendorId);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Product product = new Product();
			product.setProductId(resultSet.getString(1));
			arrayList.add(product);
		}
		return arrayList;
	}
	
	
	}
	

