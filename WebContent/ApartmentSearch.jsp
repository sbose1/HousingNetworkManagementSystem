<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Housing Network</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
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
		<h1>LookUp Rental Units</h1>
		<p>Lookup to view rentals listed</p>
		<h3>${requestScope.error}</h3>
		</header>
		<a href="#" class="image fit"><img src="images/IMG03.jpg" alt="" /></a>
		<!-- To display Login Window -->
		<h4>Please Enter LookUp Details</h4>

		<form method='post' action="LookUpApartment">
			Maximum Occupants*: <input type='text' name='maxOcc' /><br>
			Number of Bedrooms*: <input type='text' name='bedRoomCount' /><br>
			Number of Bathrooms*: <input type='text' name='bathRoomCount' /><br>
			Maximum Rent* <input type='text' name='maxRent' /><br> <input
				type='reset' value='clear'></input> <a class='btn' href='submit'>

				<input class='btn' type='submit' value='submit'>
			</a><br>

		</form>
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