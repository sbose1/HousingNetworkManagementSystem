
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import com.mysql.jdbc.PreparedStatement;*/

import HousingNetwork.Model.RentalUnit;

@WebServlet(urlPatterns = "/ViewApartment")
public class ViewApartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewApartmentServlet() {
		super();
	}

	//ViewApartment Servlet doGet method to enable handling page requests.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String hidden = request.getParameter("userName");

		Connection connection = null;
		try {
		/*	String url = "jdbc:mysql://localhost:3306/UserDB";
			String usernameDB = "webapp";
			String passwordDB = "password";*/
			
			
			//AWS RDS connection:
			
			String url = "jdbc:mysql://userdb.ct8wwnb4oc22.us-east-2.rds.amazonaws.com:3306/userdb";
			String usernameDB = "root";
			String passwordDB = "Tanmoy*2";
			
			connection = DriverManager.getConnection(url, usernameDB, passwordDB);

			// String selectSQLforUser = "select userID from userdetails where
			// username= ?";
			// System.out.print(selectSQLforUser);
			// String selectSQLforUser = "select * from userdetails";
			// java.sql.PreparedStatement preparedStatement =
			// connection.prepareStatement(selectSQLforUser);
			// preparedStatement.setString(1, hidden);
			// ResultSet rsUser =
			// preparedStatement.executeQuery(selectSQLforUser);

			/*
			 * Statement stmt = connection.createStatement();
			 * 
			 * 
			 * 
			 * 
			 * ResultSet rsUser = stmt.executeQuery( "select * from userdetails"
			 * );
			 */

			// Database call to find userID from UserDetails table.
			PreparedStatement ps = null;
			ResultSet rs1 = null;

			String query = "SELECT userID FROM userdetails " + "WHERE username = ?";

			ps = connection.prepareStatement(query);
			ps.setString(1, hidden);
			rs1 = ps.executeQuery();

			int userID = 0;
			if (rs1.next())
				userID = rs1.getInt("userID");

			// Integer userID= rsUser.getInt("userID");

			 System.out.print("XXXXXXXXXXXXXXX"+userID+"XXXXXXXXXXXXXXX");

			
			
			// Database call to find all RentalUnits by this userID .
			ArrayList<RentalUnit> availableUnits = new ArrayList<RentalUnit>();

			PreparedStatement ps2 = null;
			ResultSet rs2 = null;

			String query2 = "SELECT * FROM RentalUnit " + "WHERE userID = ?";

			ps2 = connection.prepareStatement(query2);
			ps2.setInt(1, userID);
			rs2 = ps2.executeQuery();

			String userID2 = null;
			// if(rs1.next())
			while (rs2.next()) {
				userID2 = rs2.getString("apartmentNo");

				RentalUnit unit = new RentalUnit();
				unit.setApartmentID(rs2.getInt("apartmentID"));
				unit.setDeposit(rs2.getInt("deposit"));
				unit.setFacility(rs2.getString("facility"));
				unit.setMaxPeople(rs2.getInt("maxPeople"));
				unit.setRent(rs2.getInt("rent"));
				unit.setType(rs2.getString("type"));
				unit.setBathRoomCount(rs2.getInt("bathRoomCount"));
				unit.setBedRoomCount(rs2.getInt("bedRoomCount"));
				unit.setSociety(rs2.getString("society"));
				unit.setBuilding(rs2.getString("building"));

				availableUnits.add(unit);
			}
			request.setAttribute("listing", availableUnits);
			String message = null;

			if (availableUnits.size() == 0) {
				message = "No Rental Units Posted !";
				request.setAttribute("message", message);
			}
			request.getRequestDispatcher("ViewApartment.jsp").forward(request, response);

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

	}

}
