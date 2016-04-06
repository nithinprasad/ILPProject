package vendor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import vendor.bean.User;
import vendor.bean.VendorEmployee;
import vendor.service.VendorEmployeeService;
import vendor.service.VendorProductService;
import vendor.service.VendorService;

/**
 * Servlet implementation class VendorEmployeeController
 */
public class VendorEmployeeController extends HttpServlet {
	/**
	 *@author 971204 
	 * @Class VendorEmployeeController
	 * @CreationDate 17 July, 2015
	 * 
	 */
	
	private static final Logger logger=Logger.getLogger(VendorEmployeeController.class);
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorEmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if("addEmployee".equalsIgnoreCase(action)){
			/*
			 * Vendor Employee Form Creation
			 * 
			 *  
			 */
			java.util.Enumeration params = request.getParameterNames();
			
			while(params.hasMoreElements()) {
				
				String paramName = (String) params.nextElement(); 
				String paramValue = request.getParameter(paramName);
				//System.out.println(paramName+" "+paramValue);
			}
			
			
			HttpSession httpSession=request.getSession(true);
			String vendorId = (String)httpSession.getAttribute("vendorId");
			
			
			String employeeId = request.getParameter("userId");
			String employeeName = request.getParameter("employeeName");
			String vendorNumber = vendorId;
			
			VendorEmployee vEmployee = new VendorEmployee(employeeId,employeeName,vendorNumber);
			/*
			 * 
			 * 
			 * 
			 * Login Box Creation
			 * 
			 * 
			 * 
			 */
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			String accountType = "Vendor Employeer";
		
			String status = "APPROVED";
			User user = new User(userId, password, accountType, status);
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
			VendorService vendorService = new VendorService();
			String message=null;
		    try {
		    	
		    	
				message=vendorService.addVendorEmployee(vEmployee,user);
				logger.debug("adding vendor employee");
				
			} catch (SQLException e) {
				
				logger.error("error occured while adding vendor employee",e);
				
			}
		    
		    System.out.println(message);
		    
		   if(message.equalsIgnoreCase("Failed"))
		   {
			   
			   System.out.println("inside failed page");
			   
			   RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
				PrintWriter printWriter=response.getWriter();
				String Error="<div class=\"alert alert-danger alert-dismissable\"> ";
				Error+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
				Error+=" aria-hidden=\"true\">";
				Error+="&times";
				Error+="</button>";
				Error+="Please Use Different User Name";
				Error+="</div>";
				request.setAttribute("error",Error);
				printWriter.println(Error);
				rd.forward(request, response);
				
		   }
		   else
		   {
			   
			   System.out.println("inside sucess page");
			   RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
				PrintWriter printWriter=response.getWriter();
				String Error="<div class=\"alert alert-success alert-dismissable\"> ";
				Error+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
				Error+=" aria-hidden=\"true\">";
				Error+="&times";
				Error+="</button>";
				Error+="Employee with employee Id: "+employeeId+" is successfully registered";
				Error+="</div>";
				request.setAttribute("error",Error);
				printWriter.println(Error);
				rd.forward(request, response);
				
		   }
		}
		
		
		
		
		
		
		
		
		
		action = request.getParameter("action");
		if("deleteEmployee".equalsIgnoreCase(action)){
			/*
			 * Vendor Employee Form Creation
			 * 
			 *  
			 */
			String status = null;
			System.out.println("Inside");
			java.util.Enumeration params = request.getParameterNames();
			
			while(params.hasMoreElements()) {
				
				String paramName = (String) params.nextElement(); 
				String paramValue = request.getParameter(paramName);
				System.out.println(paramName+" "+paramValue);
			}
			VendorEmployeeService employeeService=new VendorEmployeeService();
			HttpSession httpSession=request.getSession(true);
			String vendorId = (String)httpSession.getAttribute("vendorId");
			String employeeId=(String)request.getParameter("employeeId");
			System.out.println("EmpId"+employeeId);
			try {
				status = employeeService.deleteVendorEmployee(vendorId,employeeId);
				if(status.equalsIgnoreCase("Success")){
					 RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
					 logger.debug("employee deleted ");
					
					 PrintWriter printWriter = response.getWriter();
					 
					 String message="<div class=\"alert alert-success alert-dismissable\"> ";
					 message+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
					 message+=" aria-hidden=\"true\">";
					 message+="&times";
					 message+="</button>";
					 message+="Employee with employee Id: "+employeeId+"is deleted successfully";
					 message+="</div>";
									request.setAttribute("message",message);
									printWriter.println(message);
									rd.forward(request, response);
									
					 
				 }
				 else 
				 {
					 RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
					 rd.include(request, response);
					 PrintWriter printWriter = response.getWriter();
					 
					 String message="<div class=\"alert alert-danger alert-dismissable\"> ";
						message+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
						message+=" aria-hidden=\"true\">";
						message+="&times";
						message+="</button>";
						message+="Employee cannot be deleted";
						message+="</div>";
						request.setAttribute("message",message);
						printWriter.println(message);
						rd.forward(request, response);
					 
				 }
				
				logger.debug("deleting vendor employee");
				RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				logger.error("error occured while deleting employee",e);
			}
			
			
			
		}
		
		if("editEmployee".equalsIgnoreCase(action)){
			/*
			 * Vendor Employee Form Creation
			 * 
			 *  
			 */
			System.out.println("Inside");
			java.util.Enumeration params = request.getParameterNames();
			
			while(params.hasMoreElements()) {
				
				String paramName = (String) params.nextElement(); 
				String paramValue = request.getParameter(paramName);
				System.out.println(paramName+" "+paramValue);
			}
			VendorEmployeeService employeeService=new VendorEmployeeService();
			HttpSession httpSession=request.getSession(true);
			String vendorId = (String)httpSession.getAttribute("vendorId");
			String employeeId=(String)request.getParameter("employeeId");
			System.out.println("EmpId"+employeeId);
			User user=null;
			VendorEmployee vendorEmployee=null;
			try {
			 user=employeeService.viewVendorEmployeeFromLogin(employeeId);
			 logger.debug("viewing user details");
			} catch (SQLException e) {
				logger.error("user not found",e);
			}
			try {
				 vendorEmployee=employeeService.viewVendorEmployeeFromID(employeeId);
				 logger.debug("viewing particular employee details");
			} catch (SQLException e) {
				logger.error("employee details not found",e);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
			request.setAttribute("editLogin",user);
			request.setAttribute("editVendorEmployee", vendorEmployee);
			rd.forward(request, response);
			
			/*try {
				employeeService.deleteVendorEmployee(vendorId,employeeId);
				RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			
		}
		if("updateEmployee".equalsIgnoreCase(action)){
			/*
			 * Vendor Employee Form Creation
			 * 
			 *  
			 */
			System.out.println("Inside");
			java.util.Enumeration params = request.getParameterNames();
			
			while(params.hasMoreElements()) {
				
				String paramName = (String) params.nextElement(); 
				String paramValue = request.getParameter(paramName);
				System.out.println(paramName+" "+paramValue);
			}
			VendorEmployeeService employeeService=new VendorEmployeeService();
			HttpSession httpSession=request.getSession(true);
			String vendorId = (String)httpSession.getAttribute("vendorId");
			String employeeId=(String)request.getParameter("userId");
			String employeeName = request.getParameter("employeeName");
			String password = request.getParameter("password");
			try {
				employeeService.updateVendorEmployee(employeeId,employeeName,password);
				logger.debug("updating employee details");
			} catch (SQLException e) {
				
				logger.error("employee details not updated",e);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
			rd.forward(request, response);
			
			
		}
		
		
	}



		
	}


