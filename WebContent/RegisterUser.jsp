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
		
		 </nav>
	</div>
	</header>
	<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

	<!-- <h1>Housing Network</h1> -->


	<!-- Main -->
	<section id="main">
	<div class="inner">
		<header class="major special">
		<h1>SignUp Page</h1>
		<p>SignUp to view profile and rental listings.</p>
		<h3>${requestScope.error}</h3>
		</header>
		<a href="#" class="image fit"><img src="images/IMG01.jpg" alt="" /></a>

		<c:choose>
			<c:when test="${pageContext.session['new']}">
				<c:redirect url="Login" />
			</c:when>
			<c:when test="${!pageContext.session['new']}">

				<h1>Sign Up</h1>
				<form method='post' action='RegisterUser'>
					<table style='width: 100%'>
						<tr>
							<td>Username</td>
							<td><input rows='4' columns='2' name='name'></input></td>
						</tr>
						<!-- To capture user contact details -->
						<tr>
							<td>Email ID</td>
							<td><input rows='4' columns='2' name='email'></input></td>
						</tr>

						<!-- To capture user password  -->
						<tr>
							<td>Password</td>
							<td><input rows='4' columns='2' name='password'></input></td>
						</tr>
						<!-- To match with the above password -->
						<tr>
							<td>Confirmation Password</td>
							<td><input rows='4' columns='2' name='cpassword'></input></td>
						</tr>
						<!-- To capture user contact details -->
						<tr>
							<td>Contact Number</td>
							<td><input rows='4' columns='2' name='contactNum'></input></td>
						</tr>
						<!-- To capture user role in the system -->
						<tr>
							<td>Here as a:</td>
							<td><select name="role">
									<option value="manager">Owner</option>
									<option value="prospect">Seeker</option>

							</select></td>
						</tr>

					</table>
					<input type='reset' value='clear' /> <a class='btn'></a> <input
						type='submit' name='logout' value='submit'></input>
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