package vendor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import vendor.bean.Product;
import vendor.bean.PurchaseOrder;
import vendor.bean.VendorProduct;
import vendor.service.VendorProductService;
import vendor.service.VendorService;

/**
 * Servlet implementation class ProductServelet
 */
public class ProductServelet extends HttpServlet {
	
	/**
	 * @author 971204
	 * @Class ProductServelet
	 * @CreationDate 17 July, 2015
	 */
	
	
	
	private static final Logger logger=Logger.getLogger(ProductServelet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("addProduct".equalsIgnoreCase(request.getParameter("action")))
		{
			
					HttpSession httpSession=request.getSession(true);
					String vendorId = (String)httpSession.getAttribute("vendorId");
			        String productId = request.getParameter("productId");
			        int vat = Integer.valueOf(request.getParameter("vatCharges"));
			        int serviceCharge = Integer.valueOf(request.getParameter("serviceCharge"));
			       
			       VendorProduct vendorProduct = new VendorProduct(vendorId, productId, vat, serviceCharge);
			       VendorService vendorService = new VendorService();
			       String message=null;
			       try {
					message=vendorService.addProductToVendor(vendorProduct);
					logger.debug("product added successfully");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error("sorry product not added!!",e);
				}
			       if(message.equalsIgnoreCase("Failed"))
				   {
					   
					  
					   RequestDispatcher rd = request.getRequestDispatcher("productManagement.jsp");
						PrintWriter printWriter=response.getWriter();
						String Error="<div class=\"alert alert-danger alert-dismissable\"> ";
						Error+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
						Error+=" aria-hidden=\"true\">";
						Error+="&times";
						Error+="</button>";
						Error+="Product is already added or invalid product";
						Error+="</div>";
						request.setAttribute("productMessage",Error);
						printWriter.println(Error);
						rd.forward(request, response);
						
				   }
				   else
				   {
					   
					   System.out.println("inside sucess page");
					   RequestDispatcher rd = request.getRequestDispatcher("productManagement.jsp");
						PrintWriter printWriter=response.getWriter();
						String Error="<div class=\"alert alert-success alert-dismissable\"> ";
						Error+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
						Error+=" aria-hidden=\"true\">";
						Error+="&times";
						Error+="</button>";
						Error+="Product added sucessfully";
						Error+="</div>";
						request.setAttribute("productMessage",Error);
						printWriter.println(Error);
						rd.forward(request, response);
						
				   }
		}
		if("editProduct".equalsIgnoreCase(request.getParameter("action")))
		{
			String productId=request.getParameter("productId");
			VendorProductService vendorProductService=new VendorProductService();
			try {
				Product product=vendorProductService.viewProduct(productId);
				
				VendorProduct vendorProduct=vendorProductService.viewVendorProductOnProductId(productId);
				RequestDispatcher rd = request.getRequestDispatcher("productManagement.jsp");
				request.setAttribute("editProduct", product);
				request.setAttribute("editVendorProduct", vendorProduct);
				rd.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if("updateProduct".equalsIgnoreCase(request.getParameter("action")))
		{
			HttpSession httpSession=request.getSession(true);
			String vendorId = (String)httpSession.getAttribute("vendorId");
	        String productId = request.getParameter("productId");
	        int vat = Integer.valueOf(request.getParameter("vatCharges"));
	        int serviceCharge = Integer.valueOf(request.getParameter("serviceCharge"));
	       
	       VendorProduct vendorProduct = new VendorProduct(vendorId, productId, vat, serviceCharge);
	       VendorProductService vendorService = new VendorProductService();
	       try {
			vendorService.updateVendorProduct(vendorProduct);
			logger.debug("product is updated");
			RequestDispatcher rd = request.getRequestDispatcher("productManagement.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("product is not updated",e);
		}
			
		}
		
		
		if("deleteProduct".equalsIgnoreCase(request.getParameter("action")))
		{
			String productId=request.getParameter("productId");
			
			VendorProductService vendorProductService=new VendorProductService();
			try {
				
				HttpSession httpSession=request.getSession(true);
				Product product=vendorProductService.viewProduct(productId);
				String vendorId = (String)httpSession.getAttribute("vendorId");
				String status=vendorProductService.deleteVendorProductOnProductId(vendorId,productId);
				if(status.equalsIgnoreCase("failed")){
					RequestDispatcher rd = request.getRequestDispatcher("productManagement.jsp");
					PrintWriter printWriter=response.getWriter();
					String Error="<div class=\"alert alert-danger alert-dismissable\"> ";
					Error+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
					Error+=" aria-hidden=\"true\">";
					Error+="&times";
					Error+="</button>";
					Error+="Product with product id :"+productId+ " and name :"+product.getProductName() +" cannot be deleted as it is placed in purchase order";
					Error+="</div>";
					request.setAttribute("error",Error);
					printWriter.println(Error);
					rd.forward(request, response);
				}
				else{
					 RequestDispatcher rd = request.getRequestDispatcher("productManagement.jsp");
					 
					 PrintWriter printWriter = response.getWriter();
					 
					 String message="<div class=\"alert alert-success alert-dismissable\"> ";
						message+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
						message+=" aria-hidden=\"true\">";
						message+="&times";
						message+="</button>";
						message+="Product with product id :"+productId+ " and name :"+product.getProductName() +" is successfully removed";
						message+="</div>";
						request.setAttribute("message",message);
						printWriter.println(message);
						rd.forward(request, response);
					
				logger.debug("product deleted");
				
				rd.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("product not deleted",e);
			}
			
		}
		
		
		
	}

}
