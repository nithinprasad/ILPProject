<%@page import="vendor.bean.Shipment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="vendor.bean.Truck,vendor.service.TruckService"%>
<%@page import="java.util.ArrayList"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Truck Management</title>
</head>
<script type="text/javascript" >

function confirmation() {
    var x;
    if (confirm("Are You Sure You Want To Delete Truck?") == true) {
        x = "Truck Deleted";
    } else {
        x = "Truck not Deleted";
    }
   
}
</script>
<body>
<div>
<%@ include file="header.jsp" %>
</div>
<%
		HttpSession sessionCheck=request.getSession(true);  
		String vendorIdCheck = (String)sessionCheck.getAttribute("vendorId");
        if(vendorIdCheck==null){  
        	 out.print("Please login first");  
             request.getRequestDispatcher("Home_Vendor.jsp").forward(request, response);   
        }  
        
        %>        
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

<ul class="nav nav-pills">
  <li role="presentation"><a href="vendorlogin.jsp" >Bill Management</a></a></li>
  <li role="presentation"  class="active"><a href="truckreg.jsp">Truck Management</a></li>
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
<!-- 
 *
 * For every Truck management
 *@ author aditi choubey
 *
 -->
<!--
 *
 *
 Button trigger modal 
 *
 *
 -->
 <br><br><br><br>
 <center>
	<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addTruckModal">
	ADD TRUCK
	</button>
	<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#viewTruckModal">
	VIEW TRUCK
	</button>
	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteTruckModal">
	DELETE TRUCK
	</button>
	 <br><br><br><br>
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
Truck truckView=(Truck)request.getAttribute("truck");
ArrayList<Shipment> arrayList=new ArrayList<Shipment>();
arrayList=(ArrayList<Shipment>)request.getAttribute("truckBill");
if((truckView!=null))
	{%>
	<form class="form-horizontal " method="post" action="TruckController" name="addTruck">
		<div class="form-group required">
		<label for="number" class="col-sm-4 control-label">Truck Registration No:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" name="truckId" id="number" required value=<%=truckView.getTruckNo() %> readonly="readonly">
			</div>
		</div>
		<div class="form-group required">
		<label for="year" class="col-sm-4 control-label">Year Of Registration:</label>
		<div class="col-sm-6">
			<input type="number" name="year" pattern="[0-9]{4}" title="Format : for eg:2014" max="2015" min="2000" class="form-control" id="year" value=<%=truckView.getYear() %> required>
		</div>
		</div>
		
<br><br>

<div class="form-group">
<div class="col-sm-offset-4 col-sm-6">
<button type="submit" class="btn btn-success">Update</button>

</div>
</div>


<input type = "hidden" name = "action" value = "updateTruck">
</form> 
	<%}

if((arrayList!=null)&&(arrayList.size()>0))
{
	int count=0;
	%>

	<div class="container">
	<div class="row">
      	 <div class="table-responsive">
	<table class="table table-hover table-bordered">
	<caption><h3>Shipment Details</h3></caption>
	<thead>
	<th style="text-align:center">#</th>
	<th style="text-align:center">PO Number</th>
	<th style="text-align:center">Bill Number</th>
	</thead>
	<tbody id="myTable">
		<%
		for(Shipment shipment:arrayList)
		{
			%>
			<tr align="center">
			<td><%=++count %> </td>
			<td><%=shipment.getPoNumber() %> </td>
			<td><%=shipment.getBillNo() %> </td>
			</tr>
			<%
		}
		
		
		%>
	
	</tbody>
	
	</table>
	<div class="col-md-12 text-center">
      <ul class="pagination pagination-lg pager" id="myPager"></ul>
      </div>
	</div>
	</div>
	</div>
	<%


}
	 %>


<!--
 *
 *
 Button trigger modal 
 *
 *
 -->
<!-- Modal -->



<!--
 *
 *
Add Truck modal 
 *
 *
 -->
<div class="modal fade" id="addTruckModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Add Truck</h4>
      </div>
      <div class="modal-body">
      	<div class="col-md-10">
    <div class="col-md-7"><span class="pull-right">Note:<font color = "red" >*</font> fields are mandatory</span></div>
  </div>
		<form class="form-horizontal " method="post" action="TruckController" name="addTruck">
  			<div class="form-group required">
    			<label for="number" class="col-sm-4 control-label">Truck Registration No:</label>
    			<div class="col-sm-6">
      			<input type="text" class="form-control" pattern="[A-Z]{2}-[0-9]{2}-[0-9]{4}" title="Format : for eg:MH-12-1212" name="number" id="number" required placeholder="Enter Registration No">
   				</div>
  			</div>
  			<div class="form-group required">
    			<label for="year" class="col-sm-4 control-label">Year Of Registration:</label>
    			<div class="col-sm-6">
      			<input type="number" name="year" pattern="[0-9]{4}" title="Format : for eg:2014" max="2015" min="2000" class="form-control" id="year" placeholder="yyyy" required>
    			</div>
  			</div>
  			
 <br><br>
  
  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-6">
      <button type="submit" class="btn btn-success"><span class="Addglyphicon glyphicon-plus" aria-hidden="true"></span> ADD</button>
      
    </div>
  </div>
  
 	
  <input type = "hidden" name = "action" value = "addTruck">
</form> 

</div>
      
      </div>
     
    </div>
  </div>

<!--
 *
 *
Add truck modal 
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

<div class="modal fade" id="viewTruckModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">View Truck Details</h4>
      </div>
      <div class="modal-body">
      	
		<form class="form-horizontal " method="post" action="TruckController" name="addTruck">
  			<div class="form-group required">
  			<center>
  			<label for="name" class="col-sm-4 control-label">Truck Number</label>
  				<div class="col-sm-6">
    			<%
    			TruckService truckServiceView=new TruckService();
 				HttpSession httpSessionView=request.getSession(true);
 				String vendorIdView=(String)httpSessionView.getAttribute("vendorId");
				ArrayList<Truck> truckListView=truckServiceView.viewTruck(vendorIdView);
				 %>
				 <select class="form-control" name="truckId">
      			<%
      			
      			for(Truck truck:truckListView)
         			{%>
	  				<option value="<%=truck.getTruckNo() %>"><%=truck.getTruckNo() %></option>
	  				<%}%>
				</select>
				</div>
			</center>	
  			</div>
  		
  			
 <br><br>
  
  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-6">
      <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"> VIEW</span></button>
      
    </div>
  </div>
  
 	 <input type = "hidden" name = "action" value = "viewTruck">
</form> 
 	
 

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


<div class="modal fade" id="deleteTruckModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Delete Truck</h4>
      </div>
      <div class="modal-body">
      
      <form class="form-horizontal " method="post" action="TruckController" name="addTruck">
       
  			<div class="form-group required">
  			<center>
  			<label for="name" class="col-sm-4 control-label">Truck Number</label>
  				<div class="col-sm-6">
    			<%
    			TruckService truckServiceDelete=new TruckService();
 				HttpSession httpSessionDelete=request.getSession(true);
 				String vendorIdDelete=(String)httpSessionDelete.getAttribute("vendorId");
				ArrayList<Truck> truckListDelete=truckServiceView.viewTruck(vendorIdDelete);
				 %>
				 <select class="form-control" name="truckId">
      			<%
      			
      			for(Truck truck:truckListDelete)
         			{%>
	  				<option value="<%=truck.getTruckNo() %>"><%=truck.getTruckNo() %></option>
	  				<%}%>
				</select>
				</div>
			</center>	
  			</div>
  		
  			
 <br><br>
  
  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-6">
      <button type="submit" class="btn btn-danger" onclick = "confirmation()"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
       <p id="demo"></p>
      
    </div>
  </div>
  
 	 <input type = "hidden" name = "action" value = "deleteTruck">
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
 TruckService truckService=new TruckService();
 HttpSession httpSession=request.getSession(true);
 String truckRegNo = request.getParameter("Number");
 String vendorId=(String)httpSession.getAttribute("vendorId");
int i=0;
 ArrayList<Truck> truckList=truckService.viewTruck(vendorId);

 if(truckList.size()==0)
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
		 
	<form action="truckreg.jsp" method="post" class="navbar-form navbar-right">
 	<div class="form-group">
<input type="search" class="form-control" name="searchTruck" placeholder="Enter Truck Number" required>
</div>
<input type="hidden" name="action" value="searchTruck">
<button type="submit" class="btn btn-info  "><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Search</button>
 
 </form>
 
 <%
 
 if("searchTruck".equalsIgnoreCase(request.getParameter("action")))
 {
 	String truckId=request.getParameter("searchTruck");
 	ArrayList<Truck> searchTruckList=truckService.searchTruck(truckId,vendorId);
 	if(searchTruckList.size()>0)
 	{
 		%>
 		<div class="container">
 		<div class="row">
 	      	 <div class="table-responsive">
 		 <table class="table table-bordered table-hover">
 		 
 		 







 		 <caption><h3>Search Result</h3></caption>
 	     <thead>
 	       <tr>
 	         <th style="text-align:center">#</th>
 	          <th style="text-align:center">Truck Registration No</th>
 	         <th style="text-align:center">Year Of Registration</th>
 	         <th style="text-align:center">Owner Of Truck</th>
 	         <th></th>
 	       </tr>
 	     </thead>
 	     <tbody id="myTable">
 	      <%
 	     
 	   	   for(Truck truck:searchTruckList)
 	          {%>
 	         
 	       	   <tr align="center">
 	       	   		<td><%=++i %></td>
 	       	   		<td><%=truck.getTruckNo() %></td>
 	       	   		<td><%=truck.getYear() %></td>
 	       	   		<td><%=truck.getOwner() %></td>
 	       	   		<form class="form-horizontal " method="post" action="TruckController" name="addVendor">
 	      			 <input type = "hidden" name = "action" value = "viewTruck">
 	      			 <input type = "hidden" name="truckId" value =<%=truck.getTruckNo() %>>
 	      			 <td><button type="submit" class="btn btn-info btn-primary btn-xs">View More</button></td>
 	      			 </form>
 	       	   </tr>
 	       	   
 	         <%}
 	          
 	          
 	          
 	     
 	   %>
 	     
 	     </tbody>
 	   </table>
 	   <div class="col-md-12 text-center">
 	      <ul class="pagination pagination-lg pager" id="myPager"></ul>
 	      </div>
 	 </div>
 	 </div>
 	 </div>

 	<%}
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
	 
	 







	 <caption><h3>Truck Details</h3></caption>
     <thead>
       <tr>
         <th style="text-align:center">#</th>
          <th style="text-align:center">Truck Registration No</th>
         <th style="text-align:center">Year Of Registration</th>
         <th style="text-align:center">Owner Of Truck</th>
         <th></th>
       </tr>
     </thead>
     <tbody id="myTable2">
      <%
     
   	   for(Truck truck:truckList)
          {%>
         
       	   <tr align="center">
       	   		<td><%=++i %></td>
       	   		<td><%=truck.getTruckNo() %></td>
       	   		<td><%=truck.getYear() %></td>
       	   		<td><%=truck.getOwner() %></td>
       	   		<form class="form-horizontal " method="post" action="TruckController" name="addVendor">
      			 <input type = "hidden" name = "action" value = "viewTruck">
      			 <input type = "hidden" name="truckId" value =<%=truck.getTruckNo() %>>
      			 <td><button type="submit" class="btn btn-info btn-primary btn-xs">View More</button></td>
      			 </form>
       	   </tr>
       	   
         <%}
          
          
          
     
   }%>
     
     </tbody>
   </table>
   <div class="col-md-12 text-center">
      <ul class="pagination pagination-lg pager" id="myPager2"></ul>
      </div>
 </div>
 </div>
 </div>

 

 
<%@ include file="footer.jsp" %>
</body>
</html>