package vendor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import vendor.bean.User;
import vendor.bean.Vendor;
import vendor.bean.VendorProduct;
import vendor.service.VendorService;


/**
 * Servlet implementation class VendorController
 */
public class VendorController extends HttpServlet {
	private static final Logger logger=Logger.getLogger(VendorController.class);
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
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
	
		String action=request.getParameter("action");
		if("addvendor".equals(action)) 
		{
			
			/*
			 * Vendor Form Creation
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			String accountType="Vendor Supervisor";
			String status="PENDING";
			User user=new User(userName, password, accountType, status);
			
			
			java.util.Enumeration enu = request.getParameterNames();
			         while(enu.hasMoreElements()){
			             String paramName = (String)enu.nextElement();
			             System.out.println("PARAM: "
			                       + paramName
			                       + ": "
			                      + request.getParameter(paramName));
			         }
			
			
			
			String vendorName=request.getParameter("name");	
			long phoneNumber1;
			long phoneNumber2;
			try
			{
				 phoneNumber1=Long.valueOf(request.getParameter("ph1"));
			}
			catch(NumberFormatException e)
			{
				phoneNumber1=0000000000;
			}
			try
			{
				 phoneNumber2=Long.valueOf(request.getParameter("ph2"));
			}
			catch(NumberFormatException e)
			{
				phoneNumber2=0000000000;
			}
			
			String address1=request.getParameter("address1");
			String address2=request.getParameter("address2");
			String pincode=request.getParameter("pincode");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			String landMark=request.getParameter("landmark");
			String dirToStore=request.getParameter("directToStore");
			String status1="PENDING";
			Vendor vendor=new Vendor(vendorName, phoneNumber1,  phoneNumber2, address1, address2, pincode, city, state, landMark, dirToStore, status1);
			/*
			 * 
			 * 
			 * 
			 * Login Box Creation
			 * 
			 * 
			 * 
			 */
			
			/*
			 * 
			 * 
			 * 
			 * 
			 * Service Creation
			 * 
			 * 
			 * 
			 */
			VendorService vendorService=new VendorService();
			String vendorId="Failed";
			try {
				vendorId = vendorService.addVendor(vendor,user);
			
				System.out.println("Returning and value is"+vendorId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(vendorId.equalsIgnoreCase("Failed"))
			{   logger.debug("registration failed");
				RequestDispatcher rd = request.getRequestDispatcher("Home_Vendor.jsp");
				PrintWriter printWriter=response.getWriter();
				String message="<div class=\"alert alert-danger alert-dismissable\"> ";
				message+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
				message+=" aria-hidden=\"true\">";
				message+="&times";
				message+="</button>";
				message+="Error ! Please use different user name";
				message+="</div>";
				request.setAttribute("message",message);
				printWriter.println(message);
				rd.forward(request, response);
			}
			else
			{   logger.debug("successful registration");
				RequestDispatcher rd = request.getRequestDispatcher("Home_Vendor.jsp");
				PrintWriter printWriter=response.getWriter();
				String message="<div class=\"alert alert-success alert-dismissable\"> ";
				message+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
				message+=" aria-hidden=\"true\">";
				message+="&times";
				message+="</button>";
				message+="Created Sucesfully please Login to continue your Regsiterd vendor id"+vendorId;
				message+="</div>";
				request.setAttribute("message",message);
				printWriter.println(message);
				rd.forward(request, response);
			}
			
		}
		/*if("ViewProduct".equals(action)) {
			ArrayList<VendorProduct> productlist=null;
			VendorService vendorService=new VendorService();
			try {
				productlist = vendorService.ViewVendorProduct();
				System.out.println("in the Controller");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("productlist", productlist);
			System.out.println("in the Controller");
			RequestDispatcher rd=request.getRequestDispatcher("DummyProductResult.jsp");
			rd.forward(request, response);
		}*/
		
		
	}
}
			
		
	
	
