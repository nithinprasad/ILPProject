<%@page import="vendor.bean.Appointment"%>
<%@page import="vendor.service.TruckService"%>
<%@page import="vendor.bean.Truck"%>
<%@page import="vendor.service.BillService"%>
<%@page import="vendor.service.VendorProductService"%>
<%@page import="vendor.bean.Product"%>
<%@page import="vendor.bean.POProduct"%>
<%@page import="vendor.bean.poVendor"%>
<%@page import="vendor.bean.PurchaseOrder"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendor Home</title>
</head>

<%
	HttpSession sessionCheck = request.getSession(true);
	String vendorIdCheck = (String) sessionCheck
			.getAttribute("vendorId");
	if (vendorIdCheck == null) {
		out.print("Please login first");
		request.getRequestDispatcher("Home_Vendor.jsp").forward(
				request, response);
	}
%>
<div>
	<%@ include file="header.jsp"%>
</div>
<ul class="nav nav-pills">
		<li role="presentation" class="active"><a href="vendorlogin.jsp">Bill
				Management</a></a></li>
		<li role="presentation"><a href="truckreg.jsp">Truck
				Management</a></li>
		<li role="presentation"><a href="employee.jsp">Employee
				Management</a></li>

		<li role="presentation" ><a
			href="productManagement.jsp">Product Management</a></li>

		<li role="presentation" class="pull-right">
 <div class="dropdown" >
  <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown">Vendor id-<%=vendorIdCheck %>(Vendor Manager)
  <span class="caret"></span></button>
  <ul class="dropdown-menu">
    <li><a href="logoutUser.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>Logout</a></li>
    
  </ul>
</div>
 </li>
	</ul>
<%
	String poNumber = request.getParameter("poNumber");
	System.out.println("PO id Clicked =" + poNumber);
	BillService billService = new BillService();
	PurchaseOrder purchaseOrder = billService.viewPONUmber(poNumber);
	request.setAttribute("purchaseOrder", purchaseOrder);
	poVendor poVendor = billService.viewPOVendor(poNumber);
	request.setAttribute("poVendor", poVendor);
	ArrayList<POProduct> poProduct = billService
			.viewPOProduct(poNumber);
	request.setAttribute("poProduct", poProduct);
	PurchaseOrder editPurchase = purchaseOrder;
	poVendor editPoVendor = poVendor;
	ArrayList<POProduct> editPOProducts = poProduct;
%>
<br>
<br>


<center><h3>Bill Details</h3></center>
<center>
<div class="col-xs-6 col-md-4">
</div>
<div class="col-xs-6 col-md-4">

<table class="table table-bordered table-hover">

<tbody>
	<thead>
		<tr>
			<th>Po Number:</th>
			<td><%=editPurchase.getPoNo()%></td>
		</tr>
		<tr>
			<th>Retailer Name:</th>
			<td><%=editPurchase.getRetailerName()%></td>
		</tr>
		<tr>
			<th>Ordering date:</th>
			<td><%=editPurchase.getOrderingDate()%></td>
		</tr>
		<tr>
			<th>Expected Date:</th>
			<td><%=editPurchase.getExpectedDate()%></td>
		</tr>
		<tr>
			<th>Direct To Store:</th>
			<td><%=editPoVendor.getDirectToStore()%></td>
		</tr>
		<tr>
			<th>Address Line 1:</th>
			<td><%=editPoVendor.getAddress1()%></td>
		</tr>
		<tr>
			<th>Address Line 2:</th>
			<td><%=editPoVendor.getAddress2()%></td>
		</tr>
		<tr>
			<th>City:</th>
			<td><%=editPoVendor.getCity()%></td>
		</tr>
		<tr>
			<th>State:</th>
			<td><%=editPoVendor.getState()%></td>
		</tr>
		<tr>
			<th>Pin Code:</th>
			<td><%=editPoVendor.getPincode()%></td>
		</tr>
	</thead>
	



</tbody>




</table>
</div>
</center>
<br>
<div class="col-md-8">

</div>
<div class="col-md-4">
</div>
 <div class="col-md-6 col-md-offset-3">

<h3>Product Details</h3>
		<table class="table table-bordered table-hover" >
		
			<thead>
				<th style="text-align:center">#</th>
				<%
					int count = 0;
				%>
				<th style="text-align:center">Product Id</th>
				<th style="text-align:center">Product Name</th>
				<th style="text-align:center">Unit Price</th>
				<th style="text-align:center">Order Quantity</th>
			</thead>

			<%
				for (POProduct product : editPOProducts) {
			%>
			<tbody align="center">
				<td><%=++count%></td>
				<td><%=product.getProductId()%></td>
				<%
					VendorProductService vendorProductService = new VendorProductService();
						Product productView = vendorProductService.viewProduct(product
								.getProductId());
				%>
				<td><%=productView.getProductName()%></td>
				<td><%=productView.getUnitPrice()%></td>
				<td><%=product.getQuantityNo()%></td>


			</tbody>



			<%
				}
			%>
		</table>
		</div>
		
		</center>
	</div>
	</div>

</form>

<div class="form-group">
	<label class="col-sm-5 control-label"></label>
	<div class="col-sm-7">

		<a class="btn btn-success" data-toggle="modal" data-target="#myModal"
			href="#" role="button">Process Bill</a>
	</div>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br>
<!--
Modal For Bill
  -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Bill</h4>
			</div>
			<div class="modal-body">
				<table class="table table-bordered table-hover" >
					<thead>
						<th>#</th>
						<%
							int countModal = 0;
						%>
						<th>Product Id</th>
						<th>Product Name</th>
						<th>Unit Price</th>
						<th>Order Quantity</th>
						<th>Quantity</th>
					</thead>

					<form method="post" action="BillServlet">
						<%
							String productArray, quantityArray;
							for (POProduct product : editPOProducts) {
								quantityArray = "quantityArray[" + (countModal) + "]";
								productArray = "productArray[" + (countModal) + "]";
								countModal = countModal + 1;
						%>
						<tbody>
							<td><%=countModal%></td>
							<td><%=product.getProductId()%></td>
							<%
								VendorProductService vendorProductService = new VendorProductService();
									Product productView = vendorProductService.viewProduct(product
											.getProductId());
							%>
							<td><%=productView.getProductName()%></td>
							<td><%=productView.getUnitPrice()%></td>
							<td><%=product.getQuantityNo()%></td>
							<td><input type="number" name=<%=product.getProductId()%>
								value=1 min="0" max=<%=product.getQuantityNo()%>></td>
							<input type="hidden" name=<%=quantityArray%>
								value=<%=(int) product.getQuantityNo()%>>
							<input type="hidden" name=<%=productArray%>
								value=<%=product.getProductId()%>>
						</tbody>



						<%
							}
						%>

						<input type="hidden" name="dateOfDelivery"
							value=<%=editPurchase.getExpectedDate()%>> <input
							type="hidden" name="orderBy" value=<%=editPurchase.getOrderBy()%>>
						<input type="hidden" name="poNumber"
							value=<%=editPurchase.getPoNo()%>> <input type="hidden"
							name="count" value=<%=countModal%>> <input type="hidden"
							name="action" value="generateBill">

						<%
							ArrayList<Truck> arrayList = new ArrayList<Truck>();
							TruckService truckService = new TruckService();
							arrayList = truckService.viewTruck(vendorIdCheck);
						%>
						<div class="form-group">
							<label class="col-sm-5 control-label">Truck Number</label>
							<div class="col-sm-7">
								<select name="truckNumber">

									<%
										for (Truck truck : arrayList) {
									%>
									<option value=<%=truck.getTruckNo()%>><%=truck.getTruckNo()%></option>
									<%
										}
									%>
								
							</div>
						</div>

						</select> <input class="btn btn-success" type="submit" value="Submit">
					</form>
				</table>
			</div>

		</div>
	</div>
</div>
<!--
Modal For Bill
  -->









<!--  ==================================================______________________________TRUCK________________________________============================================-->

<!--  ==================================================______________________________TRUCK________________________________============================================-->






<br>

</body>

<%@include file="footer.jsp"%>
</html>