<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ include file="header.jsp"%>
</head>
<body>
<center><h3>Welcome! Enter the Credentials</h3><br>
</center>

	<div class="col-md-10">
		<form class="form-horizontal" action="EmployeeLoginController" method="post">
		<div class="container">
  <div class="row">
    <div class="col-md-10">
    <div class="col-md-7"><span class="pull-right">Note:<font color = "red" >*</font> fields are mandatory</span></div>
  </div>
</div>
</div>
   
			<div class="form-group required">
		
				<label for="userId" class="col-sm-4 control-label">User Id</label>

				<div class="col-sm-6">
					<input type="text" class="form-control" id="userId" name="userId"
						placeholder="User Id" minlength="5" maxlength="15" required>
				</div>
			</div>
			<div class="form-group required">
				<label for="password" class="col-sm-4 control-label">Password</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="Password" required>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-6">
					<button type="submit" class="btn btn-primary">Sign in</button>
					<a class="btn btn-success" href="Home_Vendor.jsp" role="button">Home</a>
				</div>
			</div>
			<input type="hidden" name="action" value="employeeView">
			
<%
String error=(String)request.getAttribute("error");
if(error!=null)
{
	%>
	<%=error%>
		<%
}
%>
		</form>
		<%@include file="footer.jsp"%>



		<!--
**Pratik Nandankar**
** 
 -->
</body>
</html>