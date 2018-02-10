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

<!-- <title>Listed Apartment Unit/s</title> -->
</head>
<body>

	<!-- Header -->
	<header id="header">
	<div class="inner">
		<a href="index1.jsp" class="logo">Housing Network</a>

		<nav id="nav"> <a href="index.html">Home</a> 
		<a href='Logout'>Logout</a> </nav>
	</div>
	</header>
	<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>



	<!-- Main -->
	<section id="main">
	<div class="inner">
		<header class="major special">
		<h1>Owner Home</h1>
		<p>Welcome ${sessionScope.username}</p>
		<%-- <h3>${requestScope.error}</h3> --%>
		<h3>${requestScope.message}</h3>
		</header>
		<a href="#" class="image fit"><img src="images/IMG02.jpg" alt="" /></a>
		<!-- To display  Window -->
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
				<!-- <th>Edit</th>
				<th>Delete</th> -->
				<th>Delete</th>

			</tr>

			<c:forEach items="${listing}" var="unitDetail">
				<tr>
					<td><c:out value="${unitDetail.getApartmentID() }" /></td>
					<td><c:out value="${unitDetail.getType()}" /></td>
					<td><c:out value="${unitDetail.getFacility() }" /></td>
					<td><c:out value="${unitDetail.getMaxPeople()}" /></td>
					<td><c:out value="${unitDetail.getBedRoomCount()}" /></td>
					<td><c:out value="${unitDetail.getRent()}" /></td>
					<td><c:out value="${unitDetail.getDeposit()}" /></td>

					<td><c:out value="${unitDetail.getSociety()}" /></td>
					<td><c:out value="${unitDetail.getBuilding()}" /></td>

					<!-- <td><form action="edit" method="post">
							<input type="hidden" name="todo" value="todo"> <input
								type="submit" value="Edit">
						</form></td>

					<td><form action="delete" method="post">
							<input type="hidden" name="todo" value="todo"> <input
								type="submit" value="Delete">
						</form></td> -->
						<td><form action="DeleteApartment" method="post">
							<input type="hidden" name="unitID" value="${unitDetail.getApartmentID()}"> <input
								type="submit" value="Delete">
						</form></td>


				</tr>


			</c:forEach>


		</table>
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
