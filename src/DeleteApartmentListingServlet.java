import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

/**
 * 
 */

/**
 * @author snigdhabose
 *
 */
// Class to enable owner to delete an apartment post after it has been rented out:

@WebServlet(urlPatterns = "/DeleteApartment")
public class DeleteApartmentListingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteApartmentListingServlet() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//ToDo
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Inside delete apartment post");
		HttpSession session = request.getSession();
		
		int unitID = Integer.parseInt(request.getParameter("unitID"));
		
		System.out.println("unitID" + unitID);
		
		String user=(String) session.getAttribute("username");
		System.out.println("user" + user);
		
		PreparedStatement ps = null;
		ResultSet rs1 = null;

		
		
		Connection connection = null;
		try {

			/*String url = "jdbc:mysql://localhost:3306/UserDB";
			String usernameDB = "webapp";
			String passwordDB = "password";*/
			
			
			//AWS RDS db SErver connection:
			String url = "jdbc:mysql://userdb.ct8wwnb4oc22.us-east-2.rds.amazonaws.com:3306/userdb";
			String usernameDB = "root";
			String passwordDB = "Tanmoy*2";
			
			connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			
			
			
			
			String query = "DELETE FROM RentalUnit " + "WHERE apartmentID = ?";

			ps = connection.prepareStatement(query);
			ps.setInt(1, unitID);
			ps.executeUpdate();

			
					
					rs1.close();
					//stmt.close();
					connection.close();
					
		
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		request.setAttribute("username", user);
		
		request.getRequestDispatcher("DeleteSuccess.html").forward(request, response);
	
			//response.sendRedirect("ManagerHome.jsp");
		} 
	

}