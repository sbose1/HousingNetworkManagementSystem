
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

<!-- <title>Add Apartment Unit/s postal</title> -->
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
		<h3>${requestScope.error}</h3>
		<%-- <h3>${requestScope.error}</h3> --%>
		<h3>${requestScope.message}</h3>
		</header>
		<a href="#" class="image fit"><img src="images/IMG02.jpg" alt="" /></a>
		<!-- To display  Window -->
		
		
		<form method='post' action='AddApartment'>
		
	        <!-- Apartment ID*: <input type='text' name='aptID' /><br> -->
			Apartment Number*: <input type='text' name='aptNo' /><br>
			Maximum Occupants*: <input type='text' name='maxOcc' /><br>
			Number of Bedrooms*: <input type='text' name='bedRoomCount' /><br>
			Number of Bathrooms*: <input type='text' name='bathRoomCount' /><br>
			Type* <input type='text' name='type' /><br> 
			Society* <input type='text' name='society' /><br> 
			Building* <input type='text' name='building' /><br> 
			Rent(in USD)* <input type='text' name='rent' /><br> 
			Deposit Amount(in USD)*: <input type='text' name='deposit' /><br>
		
		
		
		
		

		<input type='reset' value='clear'></input> <a class='btn'
			href='/submit'></a> <input class='btn' type='submit' value='Add Rental'><br />
	</form>
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

