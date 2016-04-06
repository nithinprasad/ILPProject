<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ include file="header.jsp"%>
</head>
<!Register Vendor>
<body onload="initialize()">

	<CENTER style="margin-right: 90px">
		<h3>VENDOR REGISTRATION FORM</h3>
		
	</CENTER>
	<br>
           <div class="container">
             <div class="row">
              <div class="col-md-5"><span class="pull-right">Note:<font color = "red">*</font> fields are mandatory</span></div>
              </div>
             </div>
	<div class="col-md-10">
		<form class="form-horizontal " method="post" action="VendorController"
			                                                                  name="addVendor">
			

			<!-- 
=================================================================================================================================================================
 -->
			<input class="field" id="street_number" type="hidden" name="street"
				disabled="true"></input> <input class="field" id="route"
				disabled="true" type="hidden"></input>
			</td> <input class="field" id="country" type="hidden" disabled="true"></input>
			</td>
			<!-- 
=================================================================================================================================================================
 -->

             
			<div class="form-group required">
				<label for="name" class="col-sm-4 control-label">Name:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="name" id="name"
						required placeholder="Enter your Name">
				</div>
			</div>
			<div class="form-group required">
				<label for="userName" class="col-sm-4 control-label">User
					Name:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="userName"
						id="userName" placeholder="Enter user name" required>
				</div>
			</div>
			<div class="form-group required">
				<label for="password" class="col-sm-4 control-label">Password:</label>
				<div class="col-sm-6 ">
					<input type="password" class="form-control" name="password"
						id="password" placeholder="enter password" minlength="6" required>
				</div>
			</div>
			<div class="form-group required">
				<label for="ph1" class="col-sm-4 control-label">Phone No. 1:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="ph1" id="ph1"
						placeholder="enter valid phone no" minlength="10" maxlength="10"
						required>
				</div>
			</div>
			<div class="form-group">
				<label for="ph2" class="col-sm-4 control-label">Phone No. 2:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="ph2" id="ph2"
						placeholder="enter valid phone no" minlength="10" maxlength="10">
				</div>
			</div>
			<div class="form-group required">
				<label for="address1" class="col-sm-4 control-label">Address
					Line 1:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="address1"
						id="address1" placeholder="enter address" required>
				</div>
			</div>
			<div class="form-group">
				<label for="address2" class="col-sm-4 control-label">Address
					Line 2:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="address2"
						id="address2" placeholder="enter address">
				</div>
			</div>
			<div class="form-group required">
				<label for="inputEmail3" class="col-sm-4 control-label">Search Place</label>
				<div class="col-sm-6">
					<input id="autocomplete" class="form-control"
						placeholder="Enter your place or pincode" onFocus="geolocate()" type="text" minlength = "6" maxlength="6" required></input>
				</div>
			</div>

			<div class="form-group required">
				<label for="pincode" class="col-sm-4 control-label">Pin
					Code:</label>
				<div class="col-sm-6">
					<input id="postal_code" class="form-control" name="pincode" required
						disabled="true"></input>
				</div>
			</div>

			<div class="form-group required">
				<label for="inputPassword3" class="col-sm-4 control-label">City</label>
				<div class="col-sm-6">
					<input class="form-control" id="locality" name="city" required
						disabled="true"></input>
				</div>
			</div>
			<div class="form-group required">
				<label for="inputPassword3" class="col-sm-4 control-label">State</label>
				<div class="col-sm-6">
					<input class="form-control" id="administrative_area_level_1" required
						name="state" disabled="true"></input>
				</div>
			</div>
			<div class="form-group">
				<label for="landmark" class="col-sm-4 control-label">Landmark:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="landmark" required
						id="landmark" placeholder="enter landmark">
				</div>
			</div>

			<div class="form-group required">
				<label for="directToStore" class="col-sm-4 control-label">Direct
					To Store: </label>

				<div class="radio-inline">
					<label class="radio-inline"> <input type="radio"
						name="directToStore" id="directToStore" value="TRUE" checked>Yes
					</label> <label class="radio-inline"> <input type="radio"
						name="directToStore" id="directToStore" value="FALSE">No
					</label>

				</div>


				<br>
				<br>
				<center style="margin-right: 100px">
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-6">
							<button type="submit" class="btn btn-primary">Register</button>
							<button type="reset" class="btn btn-danger">Clear</button>
							<a class="btn btn-success" href="Home_Vendor.jsp" role="button">Sign In</a>


							


						</div>
					</div>
				</center>
				<input type="hidden" name="action" value="addvendor">
		</form>

	</div>

	<%@include file="footer.jsp"%>
</body>
</html>