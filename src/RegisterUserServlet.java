
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*@WebServlet("/HousingNetworkManagementSystem/RegisterResident")*/
@WebServlet(urlPatterns = "/RegisterUser")

public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterUserServlet() {
		super();
	}

	//RegisterUser Servlet doGet method to enable static page requests.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("RegisterUser.jsp").forward(request, response);
	}

	//RegisterUser Server doPost method to handle dynamic web page requests.
	//This method handles requests to register new user to the web application.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String conPassword = request.getParameter("cpassword");
		String role = request.getParameter("role");
		String contact_num = request.getParameter("contactNum");
		
String hashedPassword=null;
		
		//Hashing Password for match:
		try {
			hashedPassword=PasswordUtil.hashPassword(password);
			System.out.println(hashedPassword);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		ResultSet rs = null;
		Connection connection = null;
		try {

			/*String url = "jdbc:mysql://localhost:3306/UserDB";
			String usernameDB = "webapp";
			String passwordDB = "password";*/
			
			//AWS RDS db server connection:
			String url = "jdbc:mysql://userdb.ct8wwnb4oc22.us-east-2.rds.amazonaws.com:3306/userdb";
			String usernameDB = "root";
			String passwordDB = "Tanmoy*2";
			
			connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			
			//To check Email for requisite format.
			String emailREGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

			//To check all fields are furnished by the user.
			if (name.equals(null) || email.equals(null) || password.equals(null) || (contact_num.equals(null))) {
				request.setAttribute("error", "All field are mandatory!");
				request.getRequestDispatcher("RegisterUser.jsp").forward(request, response);
			}

			//Two fields password matching check.
			if (!password.equals(conPassword)) {

				request.setAttribute("error", "Passwords entered in both fields do not match !");
				request.getRequestDispatcher("RegisterUser.jsp").forward(request, response);

			}
			if (!email.matches(emailREGEX)) {
				request.setAttribute("error", "Enter valid email format !");
				request.getRequestDispatcher("RegisterUser.jsp").forward(request, response);
			}

			//Code to enforce Password strength:
			if (password == null || password.trim().isEmpty()) {
				request.setAttribute("error", "Password cannot be Empty !");
				request.getRequestDispatcher("RegisterUser.jsp").forward(request, response);
		    } else if (password.length() < 8) {
		    	request.setAttribute("error", "Password too short. Must be 8 characters long !");
				request.getRequestDispatcher("RegisterUser.jsp").forward(request, response);
		    }

			
			
			
			/*
			 * Integer contact_num = null; if (!phone.equals(null)) contact_num
			 * = Integer.parseInt(phone);
			 */
			
           //Database call to persist user data and save the given new user.
			PreparedStatement stmt = connection
					.prepareStatement("insert into userdetails(username,password,email,contact_num,roles) " + "values('"
							+ name + "','" + hashedPassword + "','" + email + "'," + contact_num + "," + " '" + role + "')");

			stmt.executeUpdate();

			//Redirect to Login page: To enable user to Login to the web application.
			response.sendRedirect("Login.jsp");

			connection.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

}
