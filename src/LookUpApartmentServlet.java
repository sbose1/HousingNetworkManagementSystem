
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

import HousingNetwork.Model.RentalUnit;



@WebServlet(urlPatterns = "/LookUpApartment")
public class LookUpApartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LookUpApartmentServlet() {
		super();
	}

	//Lookup Servlet doGet method to enable static page requests.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.sendRedirect("LookupApartment.jsp");
	}

	
	//Lookup Server doPost method to handle dynamic web page requests.
	//This method enables users to view Rentals posted based on search criteria.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String maxOcc = request.getParameter("maxOcc");
		String bedRoomCount = request.getParameter("bedRoomCount");
		String bathRoomCount = request.getParameter("bathRoomCount");
		String maxRent = request.getParameter("maxRent");
		
		
		
		
		Connection connection = null;
		try{
			/*String url = "jdbc:mysql://localhost:3306/UserDB";
			String usernameDB = "webapp";
			String passwordDB = "password";*/
			
			
			
			//AWS RDS connection:
			
			String url = "jdbc:mysql://userdb.ct8wwnb4oc22.us-east-2.rds.amazonaws.com:3306/userdb";
			String usernameDB = "root";
			String passwordDB = "Tanmoy*2";
			
	        connection = DriverManager
	            .getConnection( url, usernameDB, passwordDB );
	        
	        ArrayList<RentalUnit> availableUnits= new ArrayList<RentalUnit>();
	       
	        PreparedStatement ps = null;
			ResultSet rs = null;

			String query2 = "SELECT * FROM RentalUnit";
			
				ps = connection.prepareStatement(query2);
				
				rs = ps.executeQuery();
	        
	        
	       
	        
	 
			while(rs.next())
			{
				RentalUnit unit= new RentalUnit();
				//Filtering the response based on search/look up criteria.
				if((!maxRent.equals(null)) && (!maxRent.equals("")) && (!maxOcc.equals(null)) && (!maxOcc.equals("")) && (!bedRoomCount.equals(null)) && (!bedRoomCount.equals("")) && (!bathRoomCount.equals(null)) && (!bathRoomCount.equals(""))){
				if((rs.getInt("rent")<=Integer.valueOf(maxRent)) && rs.getString("vacant").equals("true") && (rs.getInt("maxPeople")<=Integer.valueOf(maxOcc) ) 
						&& (rs.getInt("bedRoomCount")==Integer.valueOf(bedRoomCount)) && (rs.getInt("bathRoomCount")==Integer.valueOf(bathRoomCount)) ){
				
				unit.setApartmentID(rs.getInt("apartmentID"));
				unit.setDeposit(rs.getInt("deposit"));
				unit.setFacility(rs.getString("facility"));
				unit.setMaxPeople(rs.getInt("maxPeople"));
				unit.setRent(rs.getInt("rent"));
				unit.setType(rs.getString("type"));
				unit.setBathRoomCount(rs.getInt("bathRoomCount"));
				unit.setBedRoomCount(rs.getInt("bedRoomCount"));
				unit.setSociety(rs.getString("society"));
				unit.setBuilding(rs.getString("building"));
				
				//To fetch Owner details to connect using email.
				unit.setOwner(Integer.toString((rs.getInt("userID"))));
				
				availableUnits.add(unit);}
				
				}}
			
			
			request.setAttribute("listing", availableUnits);
			String message=null;
			//If no matches found:
			if(availableUnits.size()==0){
				message="No matches found for this search !";
						request.setAttribute("message", message);
			}
				
			request.getRequestDispatcher("LookupApartment.jsp").forward(request, response);
			}
			
			
		
		
				
				
				
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
            {
                if( connection != null ) 
                {
                	connection.close();
                }
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
		}

	}

}