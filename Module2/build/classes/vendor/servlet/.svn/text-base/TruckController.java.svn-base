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
 * Servlet implementation class TruckController
 */
public class TruckController extends HttpServlet {
	/**
	 * @author 971204
	 * @Class TruckController
	 * @CreationDate 22 July, 2015
	 */
	
	
	
	private static final Logger logger=Logger.getLogger(TruckController.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TruckController() {
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
		
		String action = request.getParameter("action");
		if("addTruck".equals(action)){
			
			String truckRegNo = request.getParameter("number");
			int yearOfReg = Integer.parseInt(request.getParameter("year"));
			
			HttpSession httpSession=request.getSession(true);
			String vId = (String)httpSession.getAttribute("vendorId");
			System.out.println(truckRegNo+" "+yearOfReg);
			Truck truck = new Truck();
			truck.setTruckNo(truckRegNo);
			truck.setYear(yearOfReg);
			truck.setOwner(vId);
			
			TruckService truckService = new TruckService();
			String status = "failed";
			try {
				
				 status = truckService.addTruck(truck);
				 logger.debug("truck added successfully");
				 if(status.equalsIgnoreCase("failed")){
					 
					 RequestDispatcher rd = request.getRequestDispatcher("truckreg.jsp");
					 logger.debug("truck not added please try again!!");
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
				 {
					 RequestDispatcher rd = request.getRequestDispatcher("truckreg.jsp");
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
						
						logger.error("truck couldn't be added!!",e);
					}
			
		}
	
		if("viewTruck".equals(action))
		{
			
			String truckId=request.getParameter("truckId");
			System.out.println(truckId);
			TruckService truckService=new TruckService();
			Truck truck=null;
			try {
				truck= truckService.viewTruckTruckId(truckId);
				logger.debug("display details of truck"+truckId);
				ArrayList<Shipment>arrayList=truckService.viewTrucKBillNo(truckId);
				request.setAttribute("truck", truck);
				request.setAttribute("truckBill", arrayList);
				RequestDispatcher rd = request.getRequestDispatcher("truckreg.jsp");
				rd.forward(request,response);
			} catch (SQLException e) {
				logger.error("no details available for the truck!",e);
			}
		
		}
		if("updateTruck".equals(action))
		{
			
			String truckId=request.getParameter("truckId");
			int yearOfReg = Integer.parseInt(request.getParameter("year"));
	        System.out.println(truckId);
			TruckService truckService=new TruckService();
			Truck truck=null;
			try {
				truckService.updateTruck(truckId,yearOfReg);
				logger.debug("update the details of truck");
				RequestDispatcher rd = request.getRequestDispatcher("truckreg.jsp");
				rd.forward(request,response);
			} catch (SQLException e) {
				logger.error("truck not updated!",e);
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
					logger.debug(" particular truck not removed");
					RequestDispatcher rd = request.getRequestDispatcher("truckreg.jsp");
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
					 RequestDispatcher rd = request.getRequestDispatcher("truckreg.jsp");
					 
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
				logger.error("truck not removed!",e);
			}
		
		}

	}
}

