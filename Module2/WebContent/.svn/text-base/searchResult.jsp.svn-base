<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="vendor.bean.Truck"%>
<%@page import="vendor.service.TruckService"%>
<%@page import="vendor.bean.Appointment"%>
<%@page import="vendor.bean.PurchaseOrder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vendor.service.BillService"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendor Home</title>
</head>

<body>
<%
		HttpSession sessionCheck=request.getSession(true);  
		String vendorIdCheck = (String)sessionCheck.getAttribute("vendorId");
        if(vendorIdCheck==null){  
        	 out.print("Please login first");  
             request.getRequestDispatcher("Home_Vendor.jsp").forward(request, response);   
        }  
        
        %> 
<div>
<%@ include file="header.jsp" %>
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

if("searchPO".equalsIgnoreCase(request.getParameter("action")))
{
	%>
   <ul class="nav nav-pills">
  <li role="presentation" class="active"><a href="vendorlogin.jsp" >Bill Management</a></a></li>
  <li role="presentation"><a href="truckreg.jsp">Truck Management</a></li>
  <li role="presentation"><a href="employee.jsp" >Employee Management</a></li>
  <li role="presentation"><a href="productManagement.jsp" >Product Management</a></li>
  
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
 <!-- 
   <li role="presentation" class="pull-right"><a href="logoutUser.jsp" >Logout</a></li>
</ul> -->
	
	<%
	
	System.out.println(request.getParameter("searchPO"));
	String searchPO=request.getParameter("searchPO");
	searchPO=searchPO.toUpperCase();
	
	BillService billService=new BillService();
	ArrayList<PurchaseOrder> arrayList=new ArrayList<PurchaseOrder>();
	arrayList=billService.searchPO(searchPO, vendorIdCheck);
	if(arrayList.size()>0)
	{
		int count=0;
		 %>
		
		<form action="searchResult.jsp" method="post" class="navbar-form navbar-right">
		 <div class="form-group">
		<input type="search" class="form-control" name="searchPO" placeholder="Enter PO Number" required>
		</div>
		<input type="hidden" name="action" value="searchPO">
		<button type="submit" class="btn btn-info  ">Search</button>
 
		 </form>
		 	   
	<div class="container">
	<div class="row">
      	 <div class="table-responsive">
  	   <table class="table table-bordered table-hover">
  	   <caption><h3>New Orders</h3></caption> 
  	      <thead>
  	        <tr>
  	          <th  style="text-align:center">#</th>
  	           <th  style="text-align:center">PO Number</th>
  	          <th  style="text-align:center">Date</th>
  	          <th  style="text-align:center">Name of Retailer</th>
  	          <th  style="text-align:center">Date of Delivery</th>
  	          <th  style="text-align:center">Order By</th>
  	          <th></th>
  	        </tr>
  	      </thead>
  	      <tbody id="myTable">
     <%
  	   for(PurchaseOrder purchaseOrder:arrayList)
         {%>
      	   <tr align="center">
      	   		<td><%=++count %></td>
      	   		<td><%=purchaseOrder.getPoNo() %></td>
      	   		<td><%=purchaseOrder.getOrderingDate() %></td>
      	   		<td><%=purchaseOrder.getRetailerName() %></td>
      	   		<td><%=purchaseOrder.getExpectedDate() %></td>
      	   		<td><%=purchaseOrder.getOrderBy() %></td>
      	 
      	   			<form action="BillServlet" method="post">
      	   			<input type="hidden" name="poNumber" value=<%=purchaseOrder.getPoNo() %>>
      	   			<input type="hidden" name="action" value="viewPO">
      	   			<td><button type="submit" class="btn btn-info  btn-xs">View More</button></td>
      	   			
      	   			
      	   			</form>
      	   		
      	   		
      	   		
      	   </tr>
      	   
         <%}
     %>
     </tbody>
     </table>
     <div class="col-md-12 text-center">
      <ul class="pagination pagination-xs pager" id="myPager"></ul>
      </div>
     </div>
     </div>
     </div>
     <center>
		
		<a  class="btn btn-warning" href="vendorlogin.jsp" role="button"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Go Back</a>
	
	</center>
     
    <%     
         
         
	}
	else
	{
		%>
		<center>
		<div class="page-header">
  			<h1>Sorry <small> No Matches Found</small></h1>
  			<a  class="btn btn-warning" href="vendorlogin.jsp" role="button"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Go Back</a>
		</div>
		</center>
		<%
	}
	
	
	
	/*RequestDispatcher rd = request.getRequestDispatcher("vendorlogin.jsp");
	response.sendRedirect("vendorlogin.jsp");*/
}




if("searchAppointment".equalsIgnoreCase(request.getParameter("action")))
{
	%>
	   <ul class="nav nav-pills">
  <li role="presentation" class="active"><a href="vendorlogin.jsp" >Bill Management</a></a></li>
  <li role="presentation"><a href="truckreg.jsp">Truck Management</a></li>
  <li role="presentation"><a href="employee.jsp" >Employee Management</a></li>
  <li role="presentation"><a href="productManagement.jsp" >Product Management</a></li>
  
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
 <!-- 
   <li role="presentation" class="pull-right"><a href="logoutUser.jsp" >Logout</a></li>
</ul> -->
		<%
		String searchBillNo=request.getParameter("searchAppointment");
		searchBillNo=searchBillNo.toUpperCase();
		BillService billService=new BillService();
		ArrayList<Appointment> appointments=new ArrayList<Appointment>();
		appointments=billService.searchAppoinment(searchBillNo,vendorIdCheck);
		if(appointments.size()>0)
		{
			%>
			
			<!-- ======================================================================================================== 
	*
	*new orders received from retailer
	*
	by nithin
	*
	-->

	 
	 <form action="searchResult.jsp" method="post" class="navbar-form navbar-right">
	 <div class="form-group">
	<input type="search" class="form-control" required name="searchAppointment" placeholder="Enter Bill Number">
	</div>
	<input type="hidden" name="action" value="searchAppointment">
	<button type="submit" class="btn btn-info  ">Search</button>
	 
	 </form>
	 
	<!-- ======================================================================================================== 
	*
	*new orders received from retailer
	*
	by nithin
	*
	--> 	   
	<div class="container">
	<div class="row">
      	 <div class="table-responsive">
			<table class="table table-hover table-bordered">
			<caption><h3>Appointment Details</h3></caption>
			<thead>
				<th style="text-align:center">#</th>
				<th  style="text-align:center">Bill Number</th>
				<th  style="text-align:center">Dc Number</th>
				<th  style="text-align:center">Date Of Delivery</th>
				<th  style="text-align:center">Status</th>
				<th style="text-align:center">Generate Bill</th>
				<th></th>
				<th></th>
			</thead>
			<tbody id="myTable2">
			<%
			int countTable=0;
			for(Appointment appointment:appointments)
			{
			%>
			<tr align="center">
				<td><%=++countTable %></td>
				<td><%=appointment.getPoNo() %></td>
				<td><%=appointment.getDcNo() %></td>
				<td><%=appointment.getFixedDate() %></td>
				<td><%=appointment.getStatus() %></td>
				<form action="billPrint.jsp" method="POST">
			<input type="hidden" name="billNo" value=<%=appointment.getPoNo() %> >
			<input type="hidden" name="action" value="printBill">
			<td><button type="submit" class="btn btn-info  btn-xs">Generate Bill</button></td>
			
			</form>
				<%
				if(appointment.getStatus().equalsIgnoreCase("PENDING"))
				{ %>
					<td><button type="button" class="btn btn-info btn-xs" disabled="disabled">Ready For Shipment</button></td>
				  <%
				}
				else
				{
				
				%>
				<form action="BillServlet" method="POST">
				<input type="hidden" name="billNo" value=<%=appointment.getPoNo() %> >
				<input type="hidden" name="action" value="readyForAppointment">
				<td><button type="submit" class="btn btn-info  btn-xs">Ready For Shipment</button></td>
				
				</form>
				<%} %>
				<form  target="_blank" method="post" action="mapView.jsp">
				<%
					 long source=billService.viewVendorPin(vendorIdCheck);
				
					long destination=billService.viewDeliveryPin(appointment.getPoNo());
				
					
				%>
				<input type="hidden" name="source" value=<%=source %>>
				<input type="hidden" name="destination" value=<%=destination %>> 
				<td><button type="submit" class="btn btn-info  btn-xs">View</button></td>
				</form>
				
				</tr>
			<%
				
			}
			%>
			
			</tbody>
			</table>
			<div class="col-md-12 text-center">
      <ul class="pagination pagination-xs pager" id="myPager2"></ul>
      </div>
      </div>
      </div>
      </div>
			<center>
			
	  			<a  class="btn btn-warning" href="vendorlogin.jsp" role="button"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Go Back</a>
			
			</center>
			<%
		}
		else
		{
			%>
			<center>
			<div class="page-header">
	  			<h1>Sorry <small> No Matches Found</small></h1>
	  			<a  class="btn btn-warning" href="vendorlogin.jsp" role="button"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Go Back</a>
			</div>
			</center>
			<%
		}
}



%>



</body>
</html>