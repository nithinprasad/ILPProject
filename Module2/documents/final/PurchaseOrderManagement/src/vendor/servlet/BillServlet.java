package vendor.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import vendor.bean.Appointment;
import vendor.bean.BillDetails;
import vendor.bean.POProduct;
import vendor.bean.Product;
import vendor.bean.PurchaseOrder;
import vendor.bean.Shipment;
import vendor.bean.VendorBill;
import vendor.bean.VendorProduct;
import vendor.bean.poVendor;
import vendor.service.BillService;
import vendor.service.VendorProductService;

/**
 * Servlet implementation class BillServlet
 */
public class BillServlet extends HttpServlet {
	private static final Logger logger=Logger.getLogger(BillServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillServlet() {
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
		
		if("viewPO".equalsIgnoreCase(request.getParameter("action")))
		{
			String poNumber=request.getParameter("poNumber");
			System.out.println("PO id Clicked ="+poNumber);
			BillService billService=new BillService();
			try {
				logger.debug("displaying purchase order details");
				PurchaseOrder purchaseOrder=billService.viewPONUmber(poNumber);
				request.setAttribute("purchaseOrder",purchaseOrder);
				poVendor poVendor=billService.viewPOVendor(poNumber);
				request.setAttribute("poVendor",poVendor);
				ArrayList<POProduct> poProduct=billService.viewPOProduct(poNumber);
				request.setAttribute("poProduct", poProduct);
				for(POProduct poProduct2:poProduct)
				{
					System.out.println("Quantity"+poProduct2.getProductId()+" "+poProduct2.getQuantityNo());
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("no order found",e);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("billDetails.jsp");
			rd.forward(request, response);
		}
		
		PurchaseOrder editPurchase=(PurchaseOrder)request.getAttribute("purchaseOrder");
		poVendor editPoVendor=(poVendor)request.getAttribute("poVendor");
		ArrayList<POProduct> editPOProducts=(ArrayList<POProduct>)request.getAttribute("poProduct");
		
		
		if("generateBill".equalsIgnoreCase(request.getParameter("action")))
		{
			java.util.Enumeration enu = request.getParameterNames();
	         while(enu.hasMoreElements()){
	             String paramName = (String)enu.nextElement();
	             System.out.println("PARAM: "
	                       + paramName
	                       + ": "
	                      + request.getParameter(paramName));
	         }
			
			
			/*for(POProduct product:editPOProducts){
				VendorProductService vendorProductService=new VendorProductService();
  				Product productView;
				try {
					productView = vendorProductService.viewProduct(product.getProductId());
					VendorProduct vProduct = new VendorProduct();
					double amount = productView.getUnitPrice()*vProduct.getVat()*0.01*vProduct.getServiceCharge()*0.01*;
					System.out.println(amount);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			}*/
	         
			BillService billService=new BillService();
			HttpSession httpSession=request.getSession(true);
			String vendorId=(String)httpSession.getAttribute("vendorId");
			String poNumber=request.getParameter("poNumber");
			String truckNumber=request.getParameter("truckNumber");
			System.out.println("PONUMBER"+poNumber);
			int count=Integer.valueOf(request.getParameter("count"));
			logger.debug("generating bill for purchase order");
			double  totalAmount=0;
			double totalQuantity=0;
			int flagToCheck=0;
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
			String dateOFDelivery=request.getParameter("dateOfDelivery");
			java.util.Date date = null;
			try {
				logger.debug("converting string to date");
				date = simpleDateFormat.parse(dateOFDelivery);
					 
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				logger.error("parse Exception",e1);
			}
		
			java.sql.Date dateSql = new java.sql.Date(date.getTime());	
			String orderBy=request.getParameter("orderBy");
			for(int index=0;index<count;index++)
			{
				String productId=request.getParameter("productArray["+(index)+"]");
				
				VendorProductService productService=new VendorProductService();
				
				try {
					logger.debug("calculating total amount for generating bill");
					Product product=productService.viewProduct(productId);
					double unitPrice=product.getUnitPrice();
					
					VendorProduct vendorProduct=productService.viewVendorProductOnProductIdAndVendorId(productId,vendorId);
					
					int vat=vendorProduct.getVat();
					
					double serviceCharge=vendorProduct.getServiceCharge();
					
					int quantity=Integer.valueOf(request.getParameter(productId));
					
					int orderQuantity=Integer.valueOf(request.getParameter("quantityArray["+(index)+"]"));
					
					System.out.println(productId+" "+unitPrice+" "+vat+" "+serviceCharge+" "+quantity+" "+orderQuantity);
				    
					double amount=(unitPrice+(unitPrice*vat*0.01)+(unitPrice*serviceCharge*0.01))*quantity;
				    System.out.println("_________________________"+amount+"_______________________");
					billService.updateQuantity(poNumber, productId, quantity);
				    
					billService.checkPOStatus(poNumber);
				    
					if(quantity<orderQuantity)
				    	flagToCheck=1;
				    
					totalAmount= totalAmount+amount;
					totalQuantity+=quantity;
				    
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error("bill not generated",e);
				}
			}
			String typeOfDelivery;
			if(flagToCheck==0)
			{
				typeOfDelivery="FULL";
			}
			else
				typeOfDelivery="PARTIAL";
			
			//BillDetails billDetails=new BillDetails(billNo, poNo, totalAmount, quantity);
			try {
				logger.debug("bill details added successfully!!");
				String billNo=billService.addBillDetails(poNumber,totalAmount,totalQuantity);
				
				VendorBill vendorBill=new VendorBill(poNumber, vendorId, billNo, typeOfDelivery, "OPEN", "NO","TRUE", dateSql, orderBy);
				
				billService.addVendorBill(vendorBill);
				
				Appointment appointment=new Appointment(billNo, orderBy, vendorId, dateSql, dateSql, "PENDING");
				billService.fixAppointment(appointment);
				Shipment shipment=new Shipment(billNo, poNumber, truckNumber);
				
				billService.addShipment(shipment);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("bill not found",e);
			}
			
			System.out.println("total amount"+totalAmount);
			RequestDispatcher rd = request.getRequestDispatcher("vendorlogin.jsp");
			response.sendRedirect("vendorlogin.jsp");
			
		}
		
		if("readyForAppointment".equalsIgnoreCase(request.getParameter("action")))
		{
			BillService billService=new BillService();
			String billNo=request.getParameter("billNo");
			try {
				billService.readyForShipment(billNo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Error in making ready For shipment For the" +billNo);
			}
			RequestDispatcher rd = request.getRequestDispatcher("vendorlogin.jsp");
			response.sendRedirect("vendorlogin.jsp");
		}
		
		
		
		
		
	}	
	
	}
	
	
