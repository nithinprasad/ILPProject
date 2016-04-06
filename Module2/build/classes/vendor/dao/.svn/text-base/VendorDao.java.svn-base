package vendor.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import vendor.bean.PurchaseOrder;
import vendor.bean.Truck;
import vendor.bean.User;
import vendor.bean.Vendor;
import vendor.bean.VendorProduct;
import vendor.connection.DBConnection;
import vendor.sql.InsertDao;

public class VendorDao {
	/**
	 * For adding new vendor,fetching purchase order details corresponding to a particular vendor,adding product to a vendor
	 * @author mrunali
	 * @classname VendorDao
	 * @creationDate 17 july 2015
	 */
	private static final Logger logger = Logger.getLogger(VendorDao.class);
	public String addVendor(Vendor vendor, User user) throws SQLException {
		/**
		 * For adding new vendor into VENDOR table
		 * @param vendor
		 * @param user
		 * @return
		 * @throws SQLException
		 */
		String vNo = null;
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		/*
		 * 
		 * Adding Vendor
		 */
		
		PreparedStatement preparedStatement2 = connection
				.prepareStatement("INSERT INTO LOGIN VALUES(?,?,?,?)");
		preparedStatement2.setString(1, user.getUserId());
		preparedStatement2.setString(2, user.getPassword());
		preparedStatement2.setString(3, user.getAccountType());
		preparedStatement2.setString(4, user.getStatus());

		try {
			
			preparedStatement2.executeUpdate();
			logger.debug("inserting vendor details in login table");
			
		} catch (Exception userException) {
			logger.error("problem occured while inserting vendor in login table",userException);
			return "Failed";

		}

		PreparedStatement ps = connection
				.prepareStatement("select 'VR'||VENDOR_SEQ.nextval FROM dual");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {

			vNo = rs.getString(1);
			System.out.println(vNo);
		}
       
		PreparedStatement preparedStatement;
		preparedStatement = connection
				.prepareStatement("INSERT INTO VENDOR VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		 logger.debug("inserting vendor details");
		preparedStatement.setString(1, vNo);
		preparedStatement.setString(2, vendor.getVendorName());
		preparedStatement.setDouble(3, vendor.getPhnNum1());
		preparedStatement.setDouble(4, vendor.getPhnNum2());
		preparedStatement.setString(5, vendor.getAddress1());
		preparedStatement.setString(6, vendor.getAddress2());
		preparedStatement.setString(7, vendor.getPincode());
		preparedStatement.setString(8, vendor.getCity());
		preparedStatement.setString(9, vendor.getState());
		preparedStatement.setString(10, vendor.getLandmark());
		preparedStatement.setString(11, vendor.getDirToStore());
		preparedStatement.setString(12, vendor.getStatus());
		preparedStatement.setString(13, user.getUserId());

		/*
		 * 
		 * Adding User
		 */

		try {
			
			preparedStatement.executeUpdate();
			logger.debug("vendor is registered");

			return vNo;
		} catch (Exception e) {
		 logger.error("vendor not registered");
			return "Failed";
		}

		finally {
			dbConnection.closeConnection(connection);
		}
	}

	/*
	 * public Vendor viewVendor(int v_no) throws SQLException { DBConnection
	 * dbConnection=new DBConnection(); Connection
	 * connection=dbConnection.getConnection(); Vendor v=new Vendor();
	 * PreparedStatement preparedStatement=connection.prepareStatement(
	 * "SELECT * FROM VENDOR WHERE V_NO=?"); preparedStatement.setInt(1,v_no);
	 * ResultSet resultSet=preparedStatement.executeQuery();
	 * while(resultSet.next()) { String vendorName=resultSet.getString(2);
	 * Double phnNum1=resultSet.getDouble(3); Double
	 * phnNum2=resultSet.getDouble(4); String address=resultSet.getString(5);
	 * String dirToStore=resultSet.getString(6);
	 * 
	 * 
	 * v.setVendorName(vendorName); v.setPhnNum1(phnNum1);
	 * v.setPhnNum2(phnNum2); v.setAddress(address);
	 * v.setDirToStore(dirToStore);
	 * 
	 * } dbConnection.closeConnection(connection); return v;
	 * 
	 * } public void addUser(User u) throws SQLException{ DBConnection
	 * dbConnection=new DBConnection(); Connection
	 * connection=dbConnection.getConnection(); PreparedStatement
	 * preparedStatement; preparedStatement =
	 * connection.prepareStatement("INSERT INTO LOGIN VALUES(?,?,?,?)");
	 * preparedStatement.executeUpdate();
	 * 
	 * dbConnection.closeConnection(connection); }
	 */

	/*
	 * 
	 * 
	 * Fetching Purchase Order of a particular Vendor Which is logged on
	 * 
	 * Nithin Prasad
	 */

	public ArrayList<PurchaseOrder> fetchPO(String vendorId)
			throws SQLException {
		{
			/**
			 * retrieving the details of purchase order from PO table using PO_NO from PO_VENDOR table 
			 * @param vendorId
			 * @return
			 * @throws SQLException
			 */
			DBConnection dbConnection = new DBConnection();
			PreparedStatement preparedStatement = null;
			Connection connection = dbConnection.getConnection();
			ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
			try {
				
				preparedStatement = connection
						.prepareStatement("SELECT * FROM PO WHERE PO_NO IN(SELECT PO_NO FROM PO_VENDOR WHERE V_NO=?)");
				logger.debug("retrieving the details of purchase order from PO table using PO_NO from PO_VENDOR table ");
				preparedStatement.setString(1, vendorId);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					String poNo = resultSet.getString(1);
					Date orderingDate = resultSet.getDate(2);
					String retailerName = resultSet.getString(3);
					Date expectedDate = resultSet.getDate(4);
					String orderBy=resultSet.getString(5);
					PurchaseOrder purchaseOrder = new PurchaseOrder(poNo,orderingDate, retailerName, expectedDate,orderBy);
					purchaseOrders.add(purchaseOrder);
				}
			} catch (Exception e) {
				logger.error("No order available for particular vendor",e);

			} finally {
				dbConnection.closeConnection(connection);
				dbConnection.closePreparedStatement(preparedStatement);

			}
			return purchaseOrders;
		}
	}

	/*
	 * 
	 * 
	 * Fetching Purchase Order End Here
	 * 
	 * Nithin Prasad
	 */

	/*
	 * 
	 * 
	 * 
	 * 
	 * Add Product to a particular vendor Nithin Prasad
	 */
	public String addProductToVendor(VendorProduct vendorProduct)
			throws SQLException {
		/**
		 * For adding new product to a vendor 
		 * @param vendorProduct
		 * @return
		 * @throws SQLException
		 */
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		InsertDao insertDao = new InsertDao();

		PreparedStatement preparedStatement = connection
				.prepareStatement(insertDao.insertVendorProduct);
		preparedStatement.setString(1, vendorProduct.getV_NO());
		preparedStatement.setString(2, vendorProduct.getProducts());
		preparedStatement.setInt(3, vendorProduct.getVat());
		preparedStatement.setDouble(4, vendorProduct.getServiceCharge());
		try {
			logger.debug(" product added to particular vendor ");
			preparedStatement.execute();
			return "Sucess";
		} catch (Exception e) {
			logger.error("product not added to particular vendor",e);
			return "failed";
		} finally {
			dbConnection.closeConnection(connection);
			dbConnection.closePreparedStatement(preparedStatement);

		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * Add Product to a particular vendor Nithin Prasad
	 */
	/*public ArrayList<VendorProduct> ViewVendorProduct()
			throws ClassNotFoundException, SQLException {
		*//**
		 * For retrieving the details of all products under a particular vendor
		 * @return
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
		/**
		 * view dummy vendor product
		 *//*
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pst1 = null;
		ResultSet rs = null;
		DBConnection dbConnection = new DBConnection();
		ArrayList<VendorProduct> productlist = new ArrayList<VendorProduct>();
		System.out.println("ENTERING TRY BLOCK");

		try {
			con = dbConnection.getConnection();
			System.out.println("connection done");
			pst1 = con.prepareStatement("select * from VENDOR_PRODUCT_DUMMY");

			rs = pst1.executeQuery();
			while (rs.next()) {
				*//**
				 * V_NO VARCHAR2(20) primary key, PRODUCT_ID VARCHAR2(20), VAT
				 * VARCHAR2(20), SERVICE VARCHAR2(20)
				 **//*

				System.out.println("Entering while");
				String vendorNo = rs.getString(1);
				String productId = rs.getString(2);
				int VAT = Integer.parseInt(rs.getString(3));
				double SERVICE = Double.parseDouble(rs.getString(4));
				VendorProduct vp = new VendorProduct();
				vp.setV_NO(vendorNo);
				vp.setProducts(productId);
				vp.setVat(VAT);
				vp.setServiceCharge(SERVICE);

				productlist.add(vp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(con);
			dbConnection.closePreparedStatement(pst1);

		}
		System.out.println("No of books matched----" + productlist.size());
		return productlist;

	}
*/
}
