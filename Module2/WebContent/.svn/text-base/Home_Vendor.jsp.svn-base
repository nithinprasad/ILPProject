<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home_Vendor</title>
</head>

<body>

<%@include file="header.jsp" %>
 


<center><h3>Welcome!!! Enter the Credentials</h3><br>

</center>
<div class="col-md-10">
 
<form class="form-horizontal" method = "post" action = "LoginController">
<div class="container">
  <div class="row">
    <div class="col-md-10">
    <div class="col-md-7"><span class="pull-right">Note:<font color = "red" >*</font> fields are mandatory</span></div>
  </div>
</div>
</div>
  <div class="form-group required">
 
    <label for="userId" class="col-sm-4 control-label">User Id <span class="glyphicon glyphicon-user" aria-hidden="true"></span></label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="userId" name="userId" required placeholder="User Id" minlength = "4">
      
    </div>
  </div>
  
  
  
  
  <div class="form-group required">
    <label for="password" class="col-sm-4 control-label">Password <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span></label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="password" name="password" required placeholder="Password" minlength = "5">
    </div>
  </div>
  
  
  
   
  
  
  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-6">
    <input type = "hidden" name = "action" value = "loginUser">
      <button type="submit" class="btn btn-primary">Sign in</button>
       <a class="btn btn-primary" href="RegisterVendor.jsp" role="button">Sign Up</a>
     	 <a class="btn btn-warning" href="employeeLogin.jsp" role="button">EMPLOYEE LOGIN</a>
      
    </div>
   
  </div>
  
  

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


</form>
</div>

<%@include file="footer.jsp" %>
</body>
</html>