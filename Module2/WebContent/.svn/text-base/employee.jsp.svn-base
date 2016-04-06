<%@page import="vendor.bean.User"%>
<%@page import="vendor.bean.VendorEmployee"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vendor.service.VendorEmployeeService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee details</title>
<%@ include file="header.jsp"%>
</head>
<script>
function confirmation() {
    var x;
    if (confirm("Are You Sure You Want To Delete Employee?") == true) {
        x = "Employee Deleted";
    } else {
        x = "Employee not Deleted";
    }
   
}
</script>
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
  <li role="presentation"  ><a href="truckreg.jsp">Truck Management</a></li>
  <li role="presentation" class="active"><a href="employee.jsp" >Employee Management</a></li>
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
<body>

	<!-- 
 *
 * For every Employee management
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

	<br>
	<br>
	<br>
	<br>
	<center>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#addEmployeeModal">ADD EMPLOYEE</button>
		<button type="button" class="btn btn-warning" data-toggle="modal"
			data-target="#viewEmployeeModal">VIEW EMPLOYEE</button>
		<button type="button" class="btn btn-danger" data-toggle="modal"
			data-target="#deleteEmployeeModal">DELETE EMPLOYEE</button>
		<br>
		<br>
		<br>
		<br>
	</center>
<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
	%>
	<%=message%>
	<%
		}
	%>

	<%
		String error = (String) request.getAttribute("error");
		if (error != null) {
	%>
	<%=error%>
	<%
		}
	%>

	<!-- 
*
* View Employee Displaying page
*
*
-->


	<%
		User editUser = (User) request.getAttribute("editLogin");
		VendorEmployee editVendorEmployee = (VendorEmployee) request
				.getAttribute("editVendorEmployee");
		if ((editUser != null) && (editVendorEmployee != null)) {
	%>

	<form class="form-horizontal" name="addEmployeeForm" method="post"
		action="VendorEmployeeController">
		<div class="form-group required">
			<label for="Employee Id" class="col-sm-4 control-label">User
				Id:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="userId" name="userId"
					placeholder="User Id" minlength="6" readonly="readonly"
					value=<%=editUser.getUserId()%> required>
			</div>
		</div>
		<div class="form-group required ">
			<label for="Employee Name" class="col-sm-4 control-label">Password:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="password"
					name="password" placeholder="Password"
					value=<%=editUser.getPassword()%> minlength="6" required>
			</div>
		</div>

		<div class="form-group required">
			<label for="Vendor Id" class="col-sm-4 control-label">Employee
				Name:</label>
			<div class="col-sm-6">
				<input class="form-control" id="employeename" name="employeeName"
					value=<%=editVendorEmployee.getEmployeeName()%> type="text"
					placeholder="Employee Name">
			</div>
		</div>
		<br>
		<center style="margin-right: 50px">
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-6">
					<button type="submit" class="btn btn-success">Update</button>
				</div>
			</div>
		</center>


		<input type="hidden" name="action" value="updateEmployee">

	</form>
	<%
		}
	%>
	</div>





	<!-- 
*
*View Employee Displaying page
*
*
-->


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
Add Employee modal 
 *
 *
 -->

	<div class="modal fade" id="addEmployeeModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add Employee</h4>
				</div>
				<div class="modal-body">


					<form class="form-horizontal" name="addEmployeeForm" method="post"
						action="VendorEmployeeController">
						<div class="col-md-10">
    					<div class="col-md-7"><span class="pull-right">Note:<font color = "red" >*</font> fields are mandatory</span></div>
  							</div>
						<div class="form-group required">
							<label for="Employee Id" class="col-sm-4 control-label">User
								Id:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="userId"
									name="userId" placeholder="User Id" minlength="6" required>
							</div>
						</div>
						<div class="form-group required ">
							<label for="Employee Name" class="col-sm-4 control-label">Password:</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Password" minlength="6" required>
							</div>
						</div>

						<div class="form-group required">
							<label for="Vendor Id" class="col-sm-4 control-label">Employee
								Name:</label>
							<div class="col-sm-6">
								<input class="form-control" id="employeename" required
									name="employeeName" type="text" placeholder="Employee Name">
							</div>
						</div>
						<br>
						<center style="margin-right: 50px">
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-6">
									<button type="submit" class="btn btn-success"><span class="Addglyphicon glyphicon-plus" aria-hidden="true"></span> Add</button>

									<button type="reset" class="btn btn-danger"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Reset</button>
								</div>
							</div>
						</center>


						<input type="hidden" name="action" value="addEmployee">

					</form>


				</div>
			</div>
		</div>
	</div>


	<!--
 *
 *
Add Employee modal 
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

	<div class="modal fade" id="viewEmployeeModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">View EMployee</h4>
				</div>
				<div class="modal-body">


					<form class="form-horizontal " method="post"
						action="VendorEmployeeController" name="addVendor">
						<div class="form-group required">
							<label for="name" class="col-sm-4 control-label">Employee
								Id</label>
							<div class="col-sm-6">
								<select class="form-control" name="employeeId">
									<%
										HttpSession httpSession2 = request.getSession(true);
										String vendorId2 = (String) httpSession2.getAttribute("vendorId");
										VendorEmployeeService employeeServiceOption = new VendorEmployeeService();
										ArrayList<VendorEmployee> arrayListOption = new ArrayList<VendorEmployee>();
										arrayListOption = employeeServiceOption
												.viewVendorEmployee(vendorId2);
										for (VendorEmployee employee : arrayListOption) {
									%>
									<option value="<%=employee.getEmployeeId()%>"><%=employee.getEmployeeName()%>-<%=employee.getEmployeeId()%></option>
									<%
										}
									%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-6">
								<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"> View</button>

							</div>
						</div>


						<input type="hidden" name="action" value="editEmployee">
					</form>
					<!-- 
      *
      *
      *
      *-->
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


	<div class="modal fade" id="deleteEmployeeModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Delete Employee</h4>
				</div>
				<div class="modal-body">



					<form class="form-horizontal " method="post"
						action="VendorEmployeeController" name="addVendor">
						<div class="form-group required">
							<label for="name" class="col-sm-4 control-label">Employee</label>
							<div class="col-sm-6">
								<select class="form-control" name="employeeId">
									<%
										HttpSession httpSessionDelete = request.getSession(true);
										String vendorId3 = (String) httpSessionDelete
												.getAttribute("vendorId");
										VendorEmployeeService employeeServiceDelete = new VendorEmployeeService();
										ArrayList<VendorEmployee> vendorEmployees = new ArrayList<VendorEmployee>();
										vendorEmployees = employeeServiceDelete
												.viewVendorEmployee(vendorId3);
										out.print(vendorEmployees);
										for (VendorEmployee employee : vendorEmployees) {
									%>
									<option value="<%=employee.getEmployeeId()%>"><%=employee.getEmployeeId()%></option>
									<%
										}
									%>
								</select>
							</div>
						</div>
						
						
						
						
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-6">
								<button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure want to delete?');" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>

							</div>
						</div>


						<input type="hidden" name="action" value="deleteEmployee">
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
		HttpSession httpSession = request.getSession(true);
		String vendorId = (String) httpSession.getAttribute("vendorId");
		VendorEmployeeService employeeService = new VendorEmployeeService();
		ArrayList<VendorEmployee> arrayList = new ArrayList<VendorEmployee>();
		arrayList = employeeService.viewVendorEmployee(vendorId);
		int count = 0;
		if(arrayList.size()==0)
		{

			 %>
			 <center>
					<div class="page-header">
			  			<h1>Sorry <small> Nothing to show</small></h1>
			  		
					</div>
			</center>
			 
			 
			 <%
			
		}
		else 
		{
	%>
	
	<form action="employee.jsp" method="post" class="navbar-form navbar-right">
 	<div class="form-group">
	<input type="search" class="form-control" name="searchEmployee" placeholder="Enter Employee Id" required>
	</div>
	<input type="hidden" name="action" value="searchEmployee">
	<button type="submit" class="btn btn-info  "><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Search</button>
 
 	</form>
 	<%
 	
 	if("searchEmployee".equalsIgnoreCase(request.getParameter("action")))
	{
 		ArrayList<VendorEmployee> arrayListSearch = new ArrayList<VendorEmployee>();
 		String empId=request.getParameter("searchEmployee");
		arrayListSearch = employeeService.searchEmployee(empId, vendorId);
		
		if(arrayListSearch.size()==0)
		{
			%>
			 <center>
					<div class="page-header">
			  			<h1>Sorry <small> No Matches Found</small></h1>
			  			
					</div>
			</center>
			 
			 
			 <%
		}
		else
		{
			 %>
				<div class="container">
				<div class="row">
			      	 <div class="table-responsive">
					<table class="table table-bordered table-hover">
					<caption><h3>Search Result</h3></caption>
						<thead>
							<tr align="center">
								<th style="text-align:center">#</th>
								<th style="text-align:center">Employee Id</th>
								<th style="text-align:center">Employee Name</th>
								<th style="text-align:center">Account Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="myTable2">
							<%
								for (VendorEmployee employee : arrayListSearch) {
							%>
							<tr align="center">
								<td><%=++count%></td>
								<td><%=employee.getEmployeeId()%></td>
								<td><%=employee.getEmployeeName()%></td>

								<!-- 
			       	   		*
			       	   		*
			       	   		* Fetching Employee From Db
			       	   		*
			       	   		*-->

								<%
									VendorEmployeeService service = new VendorEmployeeService();
											User user = service.viewVendorEmployeeFromLogin(employee
													.getEmployeeId());
								%>
								<td><%=user.getStatus()%></td>
								<form class="form-horizontal " method="post"
									action="VendorEmployeeController">
									<input type="hidden" name="action" value="editEmployee"> 
									<input type="hidden" name="employeeId" id=<%=employee.getEmployeeId()%> value=<%=employee.getEmployeeId()%>>
									<td><button type="submit"
											class="btn btn-info btn-primary btn-xs">View More</button></td>
										
							</tr>
							</form>
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
					</div><%
		}
		
		
		
	}
 		 %>
	<div class="container">
	<div class="row">
      	 <div class="table-responsive">
		<table class="table table-bordered table-hover">
		<caption><h3>Employee Details</h3></caption>
			<thead>
				<tr align="center">
					<th style="text-align:center">#</th>
					<th style="text-align:center">Employee Id</th>
					<th style="text-align:center">Employee Name</th>
					<th style="text-align:center">Account Status</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="myTable">
				<%
					for (VendorEmployee employee : arrayList) {
				%>
				<tr align="center">
					<td><%=++count%></td>
					<td><%=employee.getEmployeeId()%></td>
					<td><%=employee.getEmployeeName()%></td>

					<!-- 
       	   		*
       	   		*
       	   		* Fetching Employee From Db
       	   		*
       	   		*-->

					<%
						VendorEmployeeService service = new VendorEmployeeService();
								User user = service.viewVendorEmployeeFromLogin(employee
										.getEmployeeId());
					%>
					<td><%=user.getStatus()%></td>
					<form class="form-horizontal " method="post"
						action="VendorEmployeeController">
						<input type="hidden" name="action" value="editEmployee"> 
						<input type="hidden" name="employeeId" id=<%=employee.getEmployeeId()%> value=<%=employee.getEmployeeId()%>>
						<td><button type="submit"
								class="btn btn-info btn-primary btn-xs">View More</button></td>
							
				</tr>
				</form>
				<%
					}
				%>


			</tbody>
		</table>
		<div class="col-md-12 text-center">
      <ul class="pagination pagination-xs pager" id="myPager"></ul>
      </div>
		</div>
		</div>
		</div>
		<%
			}
		%>










		<%@include file="footer.jsp"%>
</body>
</html>