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

// This is a servlet to enable the user to Add an apartment posting to the network.
//@WebServlet(urlPatterns = "/AddApartment")
public class AddApartmentListingServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddApartmentListingServlet2() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.sendRedirect("AddApartmentListing.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Inside add apartment post");
		HttpSession session = request.getSession();
		
		Integer aptNo =null;
		Integer maxOcc =null;
		Integer bedRoomCount =null;
		Integer bathRoomCount =null;
				Double rent =null;
						Double deposit =null;
		
		if (!(request.getParameter("aptNo").equals(null) || request.getParameter("maxOcc").equals(null) || request.getParameter("bedRoomCount").equals(null) || request.getParameter("bathRoomCount").equals(null) ||request.getParameter("rent").equals(null) || request.getParameter("deposit").equals(null)))
		{//int aptID = Integer.parseInt(request.getParameter("aptID"));
		 aptNo = Integer.parseInt(request.getParameter("aptNo"));
		maxOcc = Integer.parseInt(request.getParameter("maxOcc"));
		bedRoomCount = Integer.parseInt(request.getParameter("bedRoomCount"));
		bathRoomCount = Integer.parseInt(request.getParameter("bathRoomCount"));
		rent = (double) Integer.parseInt(request.getParameter("rent"));
		deposit = (double) Integer.parseInt(request.getParameter("deposit"));}
		
		String type = request.getParameter("type");
		String society = request.getParameter("society");
		String building = request.getParameter("building");
		
		boolean vacant= true;
		String user=(String) session.getAttribute("username");
		System.out.println("user" + user);
		
		
		
		PreparedStatement ps = null;
		ResultSet rs1 = null;

		
		//ResultSet rs = null;
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
			
			//To check all fields are furnished by the user.
			if (request.getParameter("aptNo").equals(null) || request.getParameter("maxOcc").equals(null) || request.getParameter("bedRoomCount").equals(null) || request.getParameter("bathRoomCount").equals(null) ||request.getParameter("rent").equals(null) || request.getParameter("deposit").equals(null) || type.equals(null) || society.equals(null) || building.equals(null) || (user.equals(null))) {
				request.setAttribute("error", "All field are mandatory!");
				request.getRequestDispatcher("AddApartmentListing.jsp").forward(request, response);
			}
			
			
			String query = "SELECT userID FROM userdetails " + "WHERE username = ?";

			ps = connection.prepareStatement(query);
			ps.setString(1, user);
			rs1 = ps.executeQuery();

			int userID = 0;
			if (rs1.next())
				userID = rs1.getInt("userID");

			// Integer userID= rsUser.getInt("userID");

			System.out.print("XXXXXXXXXXXXXXX"+userID+"XXXXXXXXXXXXXXX");
			
			
			
			
			PreparedStatement stmt = connection
					.prepareStatement("insert into RentalUnit(apartmentNo,deposit,type,society,building,rent,maxPeople,bedRoomCount,bathRoomCount,userID,vacant) " + "values('"
							 + aptNo + "','" + deposit + "','" + type + "','" + society + "','" + building + "','" +  rent + "','" + maxOcc + "','" + bedRoomCount + "','" + bathRoomCount + "','" + userID + "','" + vacant + "')");

			stmt.executeUpdate();
					
					rs1.close();
					stmt.close();
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
		request.getRequestDispatcher("ManagerHome.jsp").forward(request, response);
	
			response.sendRedirect("ManagerHome.jsp");
		} 
	

}