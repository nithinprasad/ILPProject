<%@page import="vendor.bean.BillDetails"%>
<%@page import="vendor.bean.Shipment"%>
<%@page import="vendor.service.BillService"%>
<%@page import="vendor.bean.VendorBill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Details</title>
</head>
<%@ include file="header.jsp" %>
<body>

<%
	HttpSession sessionCheck = request.getSession(true);
	String vendorIdCheck = (String) sessionCheck
			.getAttribute("vendorId");
	String billNo=request.getParameter("billNo");

	
	if ((vendorIdCheck == null)||(billNo==null)) {
		out.print("Please login first");
		request.getRequestDispatcher("Home_Vendor.jsp").forward(
				request, response);
	}
BillService billService=new BillService();
VendorBill vendorBill=billService.getVendorBill(billNo);
Shipment shipment=billService.getShipment(billNo);
BillDetails billDetails=billService.getBillDetails(billNo);

%>

<script type="text/javascript">
function printBill()
{
	window.print();
	}

</script>




<div class="container">
		<div class="row">
      	 <div class="table-responsive">
		<table class="table table-hover table-bordered">
		
		<thead>
		<tr>
		<th>Memo </th>
		<th></th>
		</tr>
		</thead>
		<tbody> 
		<tr>
			<td>Bill Number</td>
			<td><%=billNo %></td>
		</tr>
		<tr>
			<td>Vendor Number</td>
			<td><%=vendorIdCheck%></td>
		</tr>
		<tr>
			<td>Purchase Order Number</td>
			<td><%=vendorBill.getPoNo() %></td>
		</tr>
		<tr>
			<td>Type Of Delivery</td>
			<td><%=vendorBill.getTypeOfDelivery() %></td>
		</tr>
		<tr>
			<td>Destination</td>
			<td><%=vendorBill.getOrderBy() %></td>
		</tr>
		<tr>
			<td>Delivery Date</td>
			<td><%=vendorBill.getDateOFDelivery() %></td>
		</tr>
		<tr>
			<td>Truck Number</td>
			<td><%=shipment.getTruckNo() %></td>
		</tr>
		<tr>
			<td>Total Quantity</td>
			<td><%=billDetails.getQuantity() %></td>
		</tr>
		<tr>
			<td>Total Amount</td>
			<td><%=billDetails.getTotalAmount() %></td>
		</tr>
		
		
		
		 </tbody>
		
		
		</table>
		</div>
		</div>
		<button type"button" class="btn btn-success" onclick="printBill()">Print</button>
		<center>
		
		<a  class="btn btn-warning" href="vendorlogin.jsp" role="button"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Go Back</a>
	
	</center>

<%@ include file="footer.jsp" %>
</body>
</html>