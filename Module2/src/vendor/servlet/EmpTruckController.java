package vendor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import vendor.bean.Shipment;
import vendor.bean.Truck;
import vendor.service.TruckService;

/**
 * Servlet implementation class EmpTruckController
 */
public class EmpTruckController extends HttpServlet {
	private static final Logger logger=Logger.getLogger(EmpTruckController.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpTruckController() {
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
		String action = request.getParameter("action");
		if("addTruck".equals(action)){
			
			String truckRegNo = request.getParameter("number");
			int yearOfReg = Integer.parseInt(request.getParameter("year"));
			
			HttpSession httpSession=request.getSession(true);
			String vId = (String)httpSession.getAttribute("vendorId");
			System.out.println(truckRegNo+" "+yearOfReg);
			System.out.println(vId);
			Truck truck = new Truck();
			truck.setTruckNo(truckRegNo);
			truck.setYear(yearOfReg);
			truck.setOwner(vId);
			
			TruckService truckService = new TruckService();
			String status = "Failed";
			try {
				
				 status = truckService.addTruck(truck);
				
				 if(status.equalsIgnoreCase("Failed")){
					 logger.debug("truck not added");
					 
					 RequestDispatcher rd = request.getRequestDispatcher("employeeLogin.jsp");
					 rd.include(request, response);
					 PrintWriter printWriter = response.getWriter();
					 
					 String message="<div class=\"alert alert-danger alert-dismissable\"> ";
					 message+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
					 message+=" aria-hidden=\"true\">";
					 message+="&times";
					 message+="</button>";
					 message+="Error ! Please use different credentials!!!";
					 message+="</div>";
									request.setAttribute("message",message);
									printWriter.println(message);
									rd.forward(request, response);
									
					 
				 }
				 else 
				 { logger.debug("truck added successfully");
					 RequestDispatcher rd = request.getRequestDispatcher("truckmgmt.jsp");
					 rd.include(request, response);
					 PrintWriter printWriter = response.getWriter();
					 
					 String message="<div class=\"alert alert-success alert-dismissable\"> ";
						message+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
						message+=" aria-hidden=\"true\">";
						message+="&times";
						message+="</button>";
						message+="Truck registration is successful";
						message+="</div>";
						request.setAttribute("message",message);
						printWriter.println(message);
						rd.forward(request, response);
					 
				 }
			}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
		}
	
				 
				 
			/*	 else if("removeTruck".equals(action)){
					 
						
						String truckRegNo = request.getParameter("number");
						System.out.println("-----------------------------"+truckRegNo);
						HttpSession httpSession=request.getSession(true);
						String vId = (String)httpSession.getAttribute("vendorId");
						
						
						TruckService truckService = new TruckService();
						
						try { 
						       
							
							String status = truckService.removeTruck(truckRegNo);
							 if(status.equalsIgnoreCase("failed")){
								 
								 RequestDispatcher rd = request.getRequestDispatcher("truckmgmt.jsp");
								 
								 PrintWriter printWriter = response.getWriter();
								 
								 String message="<div class=\"alert alert-danger alert-dismissable\"> ";
								 message+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
								 message+=" aria-hidden=\"true\">";
								 message+="&times";
								 message+="</button>";
								 message+="truck cannot be deleted as TruckNo :"+truckRegNo+"is under shipment";
								 message+="</div>";
								 request.setAttribute("message",message);
								 printWriter.println(message);
							     rd.forward(request, response);
												
								 
							 }
							 else 
							 {
								 RequestDispatcher rd = request.getRequestDispatcher("truckmgmt.jsp");
								 
								 PrintWriter printWriter = response.getWriter();
								 logger.debug("truck is removed");
								 String message="<div class=\"alert alert-success alert-dismissable\"> ";
									message+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
									message+=" aria-hidden=\"true\">";
									message+="&times";
									message+="</button>";
									message+="Truck is successfully deleted";
									message+="</div>";
									request.setAttribute("message",message);
									printWriter.println(message);
									rd.forward(request, response);
								 
							 }
	
						}
				 catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
		}
		*/
		if("viewTruck".equals(action))
		{
			
			String truckId=request.getParameter("truckId");
			System.out.println("++++++++++++++++++++++++++++++++++++++++");
			System.out.println(truckId);
			System.out.println("++++++++++++++++++++++++++++++++++++++++");
			TruckService truckService=new TruckService();
			Truck truck=null;
			try {
				logger.debug("displaying the details of truck!!");
				truck= truckService.viewTruckTruckId(truckId);
				ArrayList<Shipment>arrayList=truckService.viewTrucKBillNo(truckId);
				request.setAttribute("truck", truck);
				request.setAttribute("truckBill", arrayList);
				RequestDispatcher rd = request.getRequestDispatcher("truckmgmt.jsp");
				rd.forward(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("no truck available",e);
			}
		
		}
		if("updateTruck".equals(action))
		{
			
			System.out.println("inside loop");
			String truckId=request.getParameter("truckId");
			int yearOfReg = Integer.parseInt(request.getParameter("year"));
			System.out.println("++++++++++++++++++++++++++++++++++++++++");
			System.out.println(truckId);
			System.out.println("++++++++++++++++++++++++++++++++++++++++");
			TruckService truckService=new TruckService();
			Truck truck=null;
			try {
				logger.debug("truck updated successfully!!");
				truckService.updateTruck(truckId,yearOfReg);
				RequestDispatcher rd = request.getRequestDispatcher("truckmgmt.jsp");
				rd.forward(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("no truck updated",e);
			}
		
		}
		if("deleteTruck".equals(action))
		{
			
			String truckId=request.getParameter("truckId");
			HttpSession httpSession=request.getSession(true);
			String vId = (String)httpSession.getAttribute("vendorId");
			TruckService truckService=new TruckService();
			
			try {
				String status=truckService.removeTruck(truckId);
				
				
				
				if(status.equalsIgnoreCase("failed")){
					logger.debug(" particular truck removed");
					RequestDispatcher rd = request.getRequestDispatcher("truckmgmt.jsp");
					PrintWriter printWriter=response.getWriter();
					String Error="<div class=\"alert alert-danger alert-dismissable\"> ";
					Error+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
					Error+=" aria-hidden=\"true\">";
					Error+="&times";
					Error+="</button>";
					Error+="Truck with registration number :"+truckId+" cannot be deleted as it is under shipment";
					Error+="</div>";
					request.setAttribute("error",Error);
					printWriter.println(Error);
					rd.forward(request, response);
				}
					 
	
				 else 
				 {
					 RequestDispatcher rd = request.getRequestDispatcher("truckmgmt.jsp");
					 
					 PrintWriter printWriter = response.getWriter();
					 
					 String Error="<div class=\"alert alert-success alert-dismissable\"> ";
					 Error+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "; 
					 Error+=" aria-hidden=\"true\">";
					 Error+="&times";
					 Error+="</button>";
					 Error+="Truck with registration number :"+truckId+" is successfully removed";
					 Error+="</div>";
						request.setAttribute("error",Error);
						printWriter.println(Error);
						rd.forward(request, response);
					 
			} 
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("truck not removed!!",e);
			}
		
		}
	}


	}


