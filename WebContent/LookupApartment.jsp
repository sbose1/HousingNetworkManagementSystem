<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<%@page import="HousingNetwork.Model.RentalUnit"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Housing Network</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />

<!-- <title>LookUp Apartment</title> -->
</head>



<body>

	<!-- Header -->
	<header id="header">
	<div class="inner">
		<a href="index1.jsp" class="logo">Housing Network</a>

		<nav id="nav"> <a href="index.html">Home</a> 
		<a href='Logout'>Logout</a>
		 </nav>
	</div>
	</header>
	<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

	<!-- Main -->
	<section id="main">
	<div class="inner">
		<header class="major special">
		<h1>LookUp Apartment List</h1>
		<h3>Search Results for Rental listings</h3>
		<p>Welcome ${sessionScope.username}</p>
		<h3>${requestScope.message}</h3>
		</header>
		<a href="#" class="image fit"><img src="images/IMG02.jpg" alt="" /></a>
		<!-- To display  Window -->
		<h4>Search Results:</h4>

		<table border="1">
			<tr>
				<th>Apartment ID</th>
				<th>Apartment Type</th>
				<th>Facility</th>
				<th>Maximum People</th>
				<th>Number Of Bedrooms</th>
				<th>Rent</th>
				<th>Deposits</th>
				<th>Society/Location</th>
				<th>Building</th>
				<th>Connect to Owner</th>

			</tr>

			<c:forEach items="${listing}" var="unitDetails">
				<tr>
					<td><c:out value="${unitDetails.getApartmentID() }" /></td>
					<td><c:out value="${unitDetails.getType()}" /></td>
					<td><c:out value="${unitDetails.getFacility() }" /></td>
					<td><c:out value="${unitDetails.getMaxPeople()}" /></td>
					<td><c:out value="${unitDetails.getBedRoomCount()}" /></td>
					<td><c:out value="${unitDetails.getRent()}" /></td>
					<td><c:out value="${unitDetails.getDeposit()}" /></td>
					<td><c:out value="${unitDetails.getSociety()}" /></td>
					<td><c:out value="${unitDetails.getBuilding()}" /></td>



					<td><form action="ContactOwner" method="post">
							<input type="hidden" name="owner" value="${unitDetails.getOwner()}">
							<input type="hidden" name="customer" value="${sessionScope.username}">
							<input type="hidden" name="listingID" value="${unitDetails.getApartmentID()}">
							
							 <input
								type="submit" value="Contact Owner">
						</form></td>
				</tr>


			</c:forEach>


		</table>
	</div>
	</section>








	<!-- To display Contact details -->
	<!-- Footer -->
	<section id="footer">
	<div class="inner">
		<header>
		<h2>Get in Touch</h2>
		</header>
		<div class="field half first">
			<label for="name">Write Us To: housingforummail@gmail.com</label>

		</div>

		<div class="field half first">
			<label for="name">Call Us With: +1(980)-765 8896</label>

		</div>

	</div>
	</section>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>
</body>
</html>
