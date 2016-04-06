<%@page import="vendor.bean.Product"%>
<%@page import="vendor.bean.VendorProduct"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vendor.service.VendorProductService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ProductManagement</title>
</head>
<body>
	<div>
		<%@ include file="header.jsp"%>
		<script type="text/javascript">
	
$.fn.pageMe = function(opts){
    var $this = this,
        defaults = {
            perPage: 7,
            showPrevNext: false,
            hidePageNumbers: false
        },
        settings = $.extend(defaults, opts);
    
    var listElement = $this;
    var perPage = settings.perPage; 
    var children = listElement.children();
    var pager = $('.pager');
    
    if (typeof settings.childSelector!="undefined") {
        children = listElement.find(settings.childSelector);
    }
    
    if (typeof settings.pagerSelector!="undefined") {
        pager = $(settings.pagerSelector);
    }
    
    var numItems = children.size();
    var numPages = Math.ceil(numItems/perPage);

    pager.data("curr",0);
    
    if (settings.showPrevNext){
        $('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
    }
    
    var curr = 0;
    while(numPages > curr && (settings.hidePageNumbers==false)){
        $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
        curr++;
    }
    
    if (settings.showPrevNext){
        $('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
    }
    
    pager.find('.page_link:first').addClass('active');
    pager.find('.prev_link').hide();
    if (numPages<=1) {
        pager.find('.next_link').hide();
    }
  	pager.children().eq(1).addClass("active");
    
    children.hide();
    children.slice(0, perPage).show();
    
    pager.find('li .page_link').click(function(){
        var clickedPage = $(this).html().valueOf()-1;
        goTo(clickedPage,perPage);
        return false;
    });
    pager.find('li .prev_link').click(function(){
        previous();
        return false;
    });
    pager.find('li .next_link').click(function(){
        next();
        return false;
    });
    
    function previous(){
        var goToPage = parseInt(pager.data("curr")) - 1;
        goTo(goToPage);
    }
     
    function next(){
        goToPage = parseInt(pager.data("curr")) + 1;
        goTo(goToPage);
    }
    
    function goTo(page){
        var startAt = page * perPage,
            endOn = startAt + perPage;
        
        children.css('display','none').slice(startAt, endOn).show();
        
        if (page>=1) {
            pager.find('.prev_link').show();
        }
        else {
            pager.find('.prev_link').hide();
        }
        
        if (page<(numPages-1)) {
            pager.find('.next_link').show();
        }
        else {
            pager.find('.next_link').hide();
        }
        
        pager.data("curr",page);
      	pager.children().removeClass("active");
        pager.children().eq(page+1).addClass("active");
    
    }
};

$(document).ready(function(){
    
  $('#myTable').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:5});
    
});
	
$(document).ready(function(){
    
	  $('#myTable2').pageMe({pagerSelector:'#myPager2',showPrevNext:true,hidePageNumbers:false,perPage:5});
	    
	});	
</script>
		
	</div>
	<%
		HttpSession sessionCheck=request.getSession(true);  
		String vendorId = (String)sessionCheck.getAttribute("vendorId");
        if(vendorId==null){  
        	 out.print("Please login first");   			 
        	 
             request.getRequestDispatcher("Home_Vendor.jsp").forward(request, response);   
        }  
        
        %>
	<ul class="nav nav-pills">
		<li role="presentation"><a href="vendorlogin.jsp">Bill
				Management</a></a></li>
		<li role="presentation"><a href="truckreg.jsp">Truck
				Management</a></li>
		<li role="presentation"><a href="employee.jsp">Employee
				Management</a></li>

		<li role="presentation" class="active"><a
			href="productManagement.jsp">Product Management</a></li>

		<li role="presentation" class="pull-right">
 <div class="dropdown" >
  <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown">Vendor id-<%=vendorId %>(Vendor Manager)
  <span class="caret"></span></button>
  <ul class="dropdown-menu">
    <li><a href="logoutUser.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>Logout</a></li>
    
  </ul>
</div>
 </li>
	</ul>

	<!-- 
 *
 * For every Product management
 *@ author nithin prasad
 *
 -->
	<!--
 *
 *
 Button trigger modal 
 *
 *
 -->
	<br>
	<br>
	<br>
	<br>
	<center>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#addProductModal">ADD PRODUCT</button>
		<button type="button" class="btn btn-warning" data-toggle="modal"
			data-target="#viewProductModal">VIEW PRODUCT</button>
		<button type="button" class="btn btn-danger" data-toggle="modal"
			data-target="#editProductModal">DELETE PRODUCT</button>
		<br> <br> <br> <br>
	</center>

	<%
String error=(String)request.getAttribute("error");
if(error!=null)
{
	%>
	<%=error%>
	<%
}
%>
	<%
String message=(String)request.getAttribute("message");
if(message!=null)
{
	%>
	<%=message%>
	<%
}
%>
	<!-- 
*
* View Product Displaying page
*
*
-->

	<%

Product editProduct=(Product)request.getAttribute("editProduct");
VendorProduct editVendorProduct=(VendorProduct)request.getAttribute("editVendorProduct");
if((editProduct!=null)&&(editVendorProduct!=null))
	{
		%>


	<form class="form-horizontal " method="post" action="ProductServelet"
		name="addVendor">
		<div class="form-group required">
			<label for="name" class="col-sm-4 control-label">Product Id</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="productId" id="name"
					required value=<%=editProduct.getProductId() %> readonly="readonly">
			</div>
		</div>
		<div class="form-group required">
			<label for="userName" class="col-sm-4 control-label">Product
				Name:</label>
			<div class="col-sm-6">
				<input type="text" name="productName" class="form-control"
					id="productName" value=<%=editProduct.getProductName() %>
					readonly="readonly" required>
			</div>
		</div>
		<div class="form-group required">
			<label for="password" class="col-sm-4 control-label">Unit
				Price</label>
			<div class="col-sm-6 ">
				<input type="number" class="form-control" name="unitPrice"
					id="password" value=<%=editProduct.getUnitPrice() %>
					readonly="readonly" required>
			</div>
		</div>


		<div class="form-group required">
			<label class="col-sm-4 control-label">Vat Charges </label> <label>
				<div class="radio-inline">
					<label class="radio-inline"> <input type="radio"
						name="vatCharges" id="directToStore" value="1">1
					</label> <label class="radio-inline"> <input type="radio"
						name="vatCharges" checked="checked" id="directToStore" value="2">2
					</label> <label class="radio-inline"> <input type="radio"
						name="vatCharges" id="directToStore" value="3">3
					</label> <label class="radio-inline"> <input type="radio"
						name="vatCharges" id="directToStore" value="4">4
					</label> <label class="radio-inline"> <input type="radio"
						name="vatCharges" id="directToStore" value="5">5
					</label>
				</div>
			</label>
		</div>
		<div class="form-group required">
			<label for="password" class="col-sm-4 control-label">Service
				Charge</label>
			<div class="col-sm-6 ">
				<input type="number" class="form-control" name="serviceCharge"
					id="password" value=<%=(int)editVendorProduct.getServiceCharge() %>
					required>
			</div>
		</div>
		<br> <br>

		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-6">
				<button type="submit" class="btn btn-success">Update</button>

			</div>
		</div>


		<input type="hidden" name="action" value="updateProduct">
	</form>

	<%
	}

%>


	<!-- 
*
*View Product Displaying page
*
*
-->


	<!--

====================================================================================================================
_________________________________________________________________________________________________________________
  -->


	<!--
 *
 *
Add Product modal 
 *
 *
 -->
	<script type="text/javascript">
		function autoFillProduct() {
			var productId = document.getElementById("addNewProduct").value;
			var form = $(document.createElement('form'));
			$(form).attr("action", "productManagement.jsp");
			$(form).attr("method", "POST");
			$(form).css("display", "none");

			var input_employee_name = $("<input>").attr("type", "hidden").attr(
					"name", "productIdTOView").val(productId);
			$(form).append($(input_employee_name));
			form.appendTo(document.body);
			$(form).submit();
			//window.location.replace("productManagement.jsp?productIdToView="+productId);
		}
	</script>

	<%
 String productIdTest=request.getParameter("productIdTOView");
 if(productIdTest!=null)
 {
	 
	 VendorProductService productServiceAutoFill=new VendorProductService();
	 Product productAuto=productServiceAutoFill.viewProduct(productIdTest);
	 
	 String printScript="<script type=\"text/javascript\">";
	 printScript += " $(window).load(function(){   $(\"#addProductModalAuto\").modal({keyboard:false,backdrop:\"static\"}); })";
	 printScript +=   "</script>";
	 out.print(printScript);
	 String productName="<script type=\"text/javascript\">"+"document.getElementById(\"productNameAuto\").value="+productAuto.getProductName()+"</script>";
	 out.print(productName);
	 %>
	<script type="text/javascript">
		document.getElementById("productName").value =
	<%=productAuto.getProductName()%>
		;
		document.getElementById("productPrice").value =
	<%=productAuto.getUnitPrice()%>
		;
	</script>












	<div class="modal fade" id="addProductModalAuto" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Product</h4>
				</div>
				<div class="col-md-10">
    <div class="col-md-7"><span class="pull-right">Note:<font color = "red" >*</font> fields are mandatory</span></div>
  </div>
				<div class="modal-body">
					<form class="form-horizontal " method="post"
						action="ProductServelet" name="addVendor">
						<div class="form-group required">
							<label for="name" class="col-sm-4 control-label">Product
								Id</label>
							<div class="col-sm-6">
								<select class="form-control" name="productIdDemo"
									id="addNewProduct" onchange="autoFillProduct()">
									<option>------</option>
									<%
      			
      			HttpSession session1=request.getSession(true);  
      		    vendorId = (String)session1.getAttribute("vendorId");
      				VendorProductService productService=new VendorProductService();
    				ArrayList<Product> products=new ArrayList<Product>();
    				products=productService.viewAllProducts(vendorId);
    				%>



									<%
      				for(Product product:products)
         			{%>
									<option id=<%=product.getProductId() %>
										value=<%=product.getProductId() %>><%=product.getProductId() %></option>
									<%}%>
								</select>
							</div>
						</div>
						<input type="hidden" value=<%=productAuto.getProductId()%>
							name="productId">
						<div class="form-group required">
							<label for="userName" class="col-sm-4 control-label">Product
								Name:</label>
							<div class="col-sm-6">
								<input type="text" name="productName" class="form-control"
									id="productNameAuto" value=<%=productAuto.getProductName()%>
									required>
							</div>
						</div>
						<div class="form-group required">
							<label for="password" class="col-sm-4 control-label">Unit
								Price (Rs.)</label>
							<div class="col-sm-6 ">
								<input type="number" class="form-control" name="unitPrice"
									id="productPrice" value=<%=productAuto.getUnitPrice()%>
									required>
							</div>
						</div>


						<div class="form-group required">
							<label class="col-sm-4 control-label">Vat Charges (in %)  </label> <label>
								<div class="radio-inline">
									<label class="radio-inline"> <input type="radio" required
										name="vatCharges" id="directToStore" value="1">1
									</label> <label class="radio-inline"> <input type="radio"
										name="vatCharges" id="directToStore" value="2">2
									</label> <label class="radio-inline"> <input type="radio"
										name="vatCharges" id="directToStore" value="3">3
									</label> <label class="radio-inline"> <input type="radio"
										name="vatCharges" id="directToStore" value="4">4
									</label> <label class="radio-inline"> <input type="radio"
										name="vatCharges" id="directToStore" value="5">5
									</label>
								</div>
							</label>
						</div>
						<div class="form-group required">
							<label for="password" class="col-sm-4 control-label">Service
								Charge (in %)</label>
							<div class="col-sm-6 ">
								<input type="number" class="form-control" name="serviceCharge"
									id="password" placeholder="Enter Service Charge In Percent"
									required>
							</div>
						</div>
						<br> <br>

						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-6">
								<button type="submit" class="btn btn-success"><span class="Addglyphicon glyphicon-plus" aria-hidden="true"></span> Add</button><br>
							

							</div>
						</div>


						<input type="hidden" name="action" value="addProduct">
					</form>

				</div>

			</div>

		</div>
	</div>
	</div>
	<!--
 *
 *
Add product modal 
 *
 *
 -->



	<%
 }
 
 %>

	<!--

====================================================================================================================
_________________________________________________________________________________________________________________
  -->




	<div class="modal fade" id="addProductModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Product</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal " method="post"
						action="ProductServelet" name="addVendor">
						<div class="col-md-10">
    							<div class="col-md-7"><span class="pull-right">Note:<font color = "red" >*</font> fields are mandatory</span></div>
  						</div>
						<div class="form-group required">
							<label for="name" class="col-sm-4 control-label">Product
								Id</label>
							<div class="col-sm-6">
								<select class="form-control" name="productId" id="addNewProduct"
									onchange="autoFillProduct()" required>
									<option></option>
									<%
      			VendorProductService productService=new VendorProductService();
    			ArrayList<Product> products=new ArrayList<Product>();
    			products=productService.viewAllProducts(vendorId);
      			for(Product product:products)
         			{
         			Product product2=	productService.viewProduct(product.getProductId());
         			
         			%>
									<option value="<%=product.getProductId() %>"><%=product.getProductId() %>-<%=product2.getProductName() %></option>
									<%}%>
								</select>
							</div>
						</div>
						<div class="form-group required">
							<label for="userName" class="col-sm-4 control-label">Product
								Name:</label>
							<div class="col-sm-6">
								<input type="text" name="productName" class="form-control"
									id="productNameAuto" placeholder="Enter Product name" required>
							</div>
						</div>
						<div class="form-group required">
							<label for="password" class="col-sm-4 control-label">Unit
								Price(Rs.)</label>
							<div class="col-sm-6 ">
								<input type="number" class="form-control" name="unitPrice"
									id="productPrice" placeholder="Enter Unit Price" required>
							</div>
						</div>


						<div class="form-group required">
							<label class="col-sm-4 control-label">Vat Charges(in %) </label> <label>
								<div class="radio-inline">
									<label class="radio-inline"> <input type="radio"
										name="vatCharges" id="directToStore" value="1">1
									</label> <label class="radio-inline"> <input type="radio"
										name="vatCharges" id="directToStore" value="2">2
									</label> <label class="radio-inline"> <input type="radio"
										name="vatCharges" id="directToStore" value="3">3
									</label> <label class="radio-inline"> <input type="radio"
										name="vatCharges" id="directToStore" value="4">4
									</label> <label class="radio-inline"> <input type="radio"
										name="vatCharges" id="directToStore" value="5">5
									</label>
								</div>
							</label>
						</div>
						<div class="form-group required">
							<label for="password" class="col-sm-4 control-label">Service
								Charge(in %)</label>
							<div class="col-sm-6 ">
								<input type="number" class="form-control" name="serviceCharge"
									id="password" placeholder="Enter Service Charge In Percent"
									required>
							</div>
						</div>
						<br> <br>

						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-6">
								<button type="submit" class="btn btn-success"><span class="Addglyphicon glyphicon-plus" aria-hidden="true"></span> Add</button>

							</div>
						</div>


						<input type="hidden" name="action" value="addProduct">
					</form>

				</div>

			</div>

		</div>
	</div>
	</div>
	<!--
 *
 *
Add product modal 
 *
 *
 -->



	<!--
 *
 *
View trigger modal 
 *
 *
 -->

	<div class="modal fade" id="viewProductModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Product</h4>
				</div>
				<div class="modal-body">
					<!-- 
  	Body 
  
  -->
					<%
	VendorProductService vendorProductService2=new VendorProductService();
 	HttpSession httpSession2=request.getSession(true);
 	String vendorId2=(String)httpSession2.getAttribute("vendorId");
	
 	ArrayList<VendorProduct>vendorProducts2=vendorProductService2.viewVendorProduct(vendorId2);
 	if(vendorProducts2==null)
 	{%>
					<form>
						<fieldset disabled>
							<div class="form-group">
								<label for="disabledTextInput">Disabled input</label> <input
									type="text" id="disabledTextInput" class="form-control"
									placeholder="Disabled input">
							</div>
							<div class="form-group">
								<label for="disabledSelect">Disabled select menu</label> <select
									id="disabledSelect" class="form-control">
									<option>Disabled select</option>
								</select>
							</div>
							<div class="checkbox">
								<label> <input type="checkbox"> Can't check this
								</label>
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</fieldset>
					</form>
					<%}
 	else
 	{ 
 		 
 	%>

					<form class="form-horizontal " method="post"
						action="ProductServelet" name="addVendor">
						<div class="form-group required">
							<label for="name" class="col-sm-4 control-label">Product
								Id</label>
							<div class="col-sm-6">
								<select class="form-control" name="productId">
									<%
      			for(VendorProduct purchaseOrder:vendorProducts2)
         			{%>
									<option value="<%=purchaseOrder.getProducts() %>"><%=purchaseOrder.getProducts() %></option>
									<%}%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-6">
								<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"> View</button>

							</div>
						</div>


						<input type="hidden" name="action" value="editProduct">
					</form>

					<%
 	}
	%>



					<!-- 
  	Body 
  -->


				</div>

			</div>
		</div>
	</div>

	<!--
 *
 *
VIEW trigger modal 
 *
 *
 -->



	<!--
 *
 *
DELETE modal 
 *
 *
 -->


	<div class="modal fade" id="editProductModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Delete Product</h4>
				</div>
				<div class="modal-body">


					<form class="form-horizontal " method="post"
						action="ProductServelet" name="addVendor">
						<div class="form-group required">
							<label for="name" class="col-sm-4 control-label">Product
								Id</label>
							<div class="col-sm-6">
								<select class="form-control" name="productId">
									<%
      			for(VendorProduct purchaseOrder:vendorProducts2)
         			{%>
									<option value="<%=purchaseOrder.getProducts() %>"><%=purchaseOrder.getProducts() %></option>
									<%}%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-6">
								<button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure want to delete?');"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>

							</div>
						</div>


						<input type="hidden" name="action" value="deleteProduct">
					</form>



				</div>

			</div>
		</div>
	</div>

	<!--
 *
 *
Delete trigger modal 
 *
 *
 -->

	<%
 VendorProductService vendorProductService=new VendorProductService();
 HttpSession httpSession=request.getSession(true);
 vendorId=(String)httpSession.getAttribute("vendorId");
int i=0;
 ArrayList<VendorProduct>vendorProducts=vendorProductService.viewVendorProduct(vendorId);
 if(vendorProducts.size()==0)
 {
	 %>
	 <center>
			<div class="page-header">
	  			<h1>Sorry <small> Nothing to Show</small></h1>
	  			
			</div>
	</center>
	 
	 
	 <%
 }
 else
 {
		 %>
		 
	<form action="productManagement.jsp" method="post" class="navbar-form navbar-right">
 	<div class="form-group">
<input type="search" class="form-control" name="searchProduct" placeholder="Enter Product Number" required>
</div>
<input type="hidden" name="action" value="searchProduct">
<button type="submit" class="btn btn-info  "><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Search</button>
 
 </form>	 
<%

if("searchProduct".equalsIgnoreCase(request.getParameter("action")))
{
	String productId=request.getParameter("searchProduct");
	ArrayList<VendorProduct>vendorProductSearch=productService.searchProduct(productId,vendorId);
	if(vendorProductSearch.size()>0)
	{
		%>
		
		<div class="container">
	<div class="row">
      	 <div class="table-responsive">
		<table class="table table-bordered table-hover">
		<caption><h3>Search Product</h3></caption> 
			<thead align="center">
				<tr >
					<th  style="text-align:center">#</th>
					<th  style="text-align:center">Product Number</th>
					<th  style="text-align:center">Product Name</th>
					<th  style="text-align:center">Product Price(Rs.)</th>
					<th  style="text-align:center">Service Charge(in %)</th>
					<th  style="text-align:center">Vat(in %)</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="myTable2">
				<%
     
   	   for(VendorProduct purchaseOrder:vendorProductSearch)
          {%>
				<tr align="center">
					<td><%=++i %></td>
					<td><%=purchaseOrder.getProducts() %></td>
					<!-- 
       	   		*
       	   		*
       	   		* Fetching Product From Db
       	   		*
       	   		*-->
					<%
       	   			Product product=vendorProductService.viewProduct(purchaseOrder.getProducts());
       	   			
       	   		%>


					<!-- 
       	   		*
       	   		*
       	   		* Fetching Product From Db
       	   		*
       	   		*-->
					<td><%=product.getProductName() %></td>
					<td><%=(double)product.getUnitPrice() %></td>
					<td><%=(int)purchaseOrder.getServiceCharge() %></td>
					<td><%=(int)purchaseOrder.getVat() %></td>

					<form class="form-horizontal " method="post"
						action="ProductServelet" name="addVendor">
						<input type="hidden" name="action" value="editProduct"> <input
							type="hidden" name="productId" value=<%=product.getProductId() %>>
						<td><button type="submit"
								class="btn btn-info btn-primary btn-xs">View More</button></td>



					</form>
				</tr>

				<%}
          
		%>
		
		</tbody>
		</table>
		<div class="col-md-12 text-center">
      <ul class="pagination pagination-lg pager" id="myPager2"></ul>
      </div>
		</div>
		</div>
	</div>
		<% 
		
	
	}
	else
	{
		 %>
		 <center>
				<div class="page-header">
		  			<h1>Sorry <small> No Matches Found</small></h1>
		  			
				</div>
		</center>
		 
		 
		 <%
	}
	
	
	
	
}

%>		
	<div class="container">
	<div class="row">
      	 <div class="table-responsive">
		<table class="table table-bordered table-hover">
		<caption><h3>Product Details</h3></caption> 
			<thead align="center">
				<tr >
					<th  style="text-align:center">#</th>
					<th  style="text-align:center">Product Number</th>
					<th  style="text-align:center">Product Name</th>
					<th  style="text-align:center">Product Price(Rs.)</th>
					<th  style="text-align:center">Service Charge(in %)</th>
					<th  style="text-align:center">Vat(in %)</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="myTable">
				<%
     
   	   for(VendorProduct purchaseOrder:vendorProducts)
          {%>
				<tr align="center">
					<td><%=++i %></td>
					<td><%=purchaseOrder.getProducts() %></td>
					<!-- 
       	   		*
       	   		*
       	   		* Fetching Product From Db
       	   		*
       	   		*-->
					<%
       	   			Product product=vendorProductService.viewProduct(purchaseOrder.getProducts());
       	   			
       	   		%>


					<!-- 
       	   		*
       	   		*
       	   		* Fetching Product From Db
       	   		*
       	   		*-->
					<td><%=product.getProductName() %></td>
					<td><%=(double)product.getUnitPrice() %></td>
					<td><%=(int)purchaseOrder.getServiceCharge() %></td>
					<td><%=(int)purchaseOrder.getVat() %></td>

					<form class="form-horizontal " method="post"
						action="ProductServelet" name="addVendor">
						<input type="hidden" name="action" value="editProduct"> <input
							type="hidden" name="productId" value=<%=product.getProductId() %>>
						<td><button type="submit"
								class="btn btn-info btn-primary btn-xs">View More</button></td>



					</form>
				</tr>

				<%}
          
          
          
     
   }%>

			</tbody>
		</table>
		<div class="col-md-12 text-center">
      <ul class="pagination pagination-lg pager" id="myPager"></ul>
      </div>
		</div>
		</div>
	</div>

	<script type="text/javascript">document.getElementById('addNewProduct').value ="<%=request.getParameter("productIdTOView")%>";
	</script>



	<%@ include file="footer.jsp"%>
</body>
</html>