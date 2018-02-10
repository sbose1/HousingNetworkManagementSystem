
import java.io.IOException;

import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*@WebServlet("/HousingNetworkManagementSystem/login")*/

@WebServlet(urlPatterns = "/Login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	//Login Servlet init method:
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
		/*
		 * List<UserDetails> userEntries = new ArrayList<UserDetails>();
		 * ArrayList<RentalUnit> listing= new ArrayList<RentalUnit>();
		 * 
		 * 
		 * config.getServletContext().setAttribute("userEntries", userEntries);
		 * config.getServletContext().setAttribute("listing",listing);
		 */

	}

	//Login Servlet: doGet method to handle idempotent web page requests.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("../HousingNetworkManagementSystem/Login.jsp").forward(request, response);

	}

	//Login Servlet: doPost method to handle dynamic requests to the application server.
	//This method enables users to Login into the Web application.
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get current action and perform functions

		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String password = request.getParameter("pwd");
		String hashedPassword=null;
		
		//Hashing Password for match:
		try {
			hashedPassword=PasswordUtil.hashPassword(password);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (password.equals(null) || password.trim().equals("") || email.equals(null) || email.trim().equals("")) {
			request.setAttribute("error", "All field required!");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}

		else {

			Connection c = null;
			try {
				/*String url = "jdbc:mysql://localhost:3306/UserDB";
				String usernameDB = "webapp";
				String passwordDB = "password";*/
				
				//AWS RDS connection:
				
				String url = "jdbc:mysql://userdb.ct8wwnb4oc22.us-east-2.rds.amazonaws.com:3306/userdb";
				String usernameDB = "root";
				String passwordDB = "Tanmoy*2";
				
				c = DriverManager.getConnection(url, usernameDB, passwordDB);
				Statement stmt = c.createStatement();

				ResultSet rs = stmt.executeQuery("select * from userdetails");

				while (rs.next()) {
					session.setAttribute("email", rs.getString("email"));
					session.setAttribute("username", rs.getString("username"));
					session.setAttribute("userID", rs.getInt("userID"));

					//Matching the password with stored hash value of the User password:
					if (rs.getString("email").equals(email) && rs.getString("password").equals(hashedPassword)) {

						// Divert calls to different landing pages based on the
						// User Role and type:
						if (rs.getString("roles").equals("manager")) {

							request.setAttribute("username", rs.getString("username"));
							session.setAttribute("username", rs.getString("username"));
							request.getRequestDispatcher("ManagerHome.jsp").forward(request, response);
							response.sendRedirect("ManagerHome.jsp");
							return;
						} else if (rs.getString("roles").equals("prospect")) {
							
							request.setAttribute("username", rs.getString("username"));
							session.setAttribute("username", rs.getString("username"));
							
							
							response.sendRedirect("ApartmentSearch.jsp");
							return;
						}
					}

				}
				// For invalid username and password : return with the error
				// message
				request.setAttribute("error", "Invalid Username or Password !");
				request.getRequestDispatcher("Login.jsp").forward(request, response);

			}

			catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (c != null) {
						c.close();
					}
				} catch (SQLException e) {
					throw new ServletException(e);
				}
			}

		}

	}
}