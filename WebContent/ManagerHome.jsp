<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Housing Network</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<!-- <title>ManagerHome</title> -->
</head>



<body>
	<!-- Header -->
	<header id="header">
	<div class="inner">
		<a href="index1.jsp" class="logo">Housing Network</a>

		<nav id="nav"> <a href="index.html">Home</a> 
		<!-- <a
			href="Logout.jsp">Logout</a>  -->
			<a href='Logout'>Logout</a>
			</nav>
	</div>
	</header>
	<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

	<!-- Main -->
	<section id="main">
	<div class="inner">
		<header class="major special">
		<h1>Owner Home</h1>
		<p>Welcome ${requestScope.username}</p>
		<%-- <h3>${requestScope.error}</h3> --%> </header>
		<a href="#" class="image fit"><img src="images/IMG02.jpg" alt="" /></a>
		<!-- To display  Window -->
		<h4>Please make a selection</h4>

		<c:choose>
			<c:when test="${pageContext.session['new']}">
				<c:redirect url="Login" />
			</c:when>
			<c:when test="${!pageContext.session['new']}">



                 <form action="AddApartment" method="GET">
					<input type="hidden" id="userName" name="userName"
						value="${requestScope.username}"> <input type="submit"
						value="Add Apartment">
				</form>

				<!-- <a href='AddApartment'>Add Apartment<br /></a> -->

				<form action="ViewApartment" method="GET">
					<input type="hidden" id="userName" name="userName"
						value="${requestScope.username}"> <input type="submit"
						value="View Rentals Posted">
				</form>







			</c:when>
		</c:choose>

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