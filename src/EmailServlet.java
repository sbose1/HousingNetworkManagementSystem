/**
 * 
 */

/**
 * @author snigdha Bose
 *
 */
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


// Utility class to dynamically generate and send Email to seeker with requisite owner details:
@WebServlet(urlPatterns = "/ContactOwner")
public class EmailServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// MailUtilGmail mailUtil=new MailUtilGmail();

		String url2 = null;

		System.out.print("In mail servlet");

		// send email to user functionality:

		String ownerID = request.getParameter("owner");
		String customerName = request.getParameter("customer");
		String listingID = request.getParameter("listingID");

		Connection connection = null;
		try {
			/*
			 * String url = "jdbc:mysql://localhost:3306/UserDB"; String
			 * usernameDB = "webapp"; String passwordDB = "password";
			 */

			// AWS RDS connection:

			String url = "jdbc:mysql://userdb.ct8wwnb4oc22.us-east-2.rds.amazonaws.com:3306/userdb";
			String usernameDB = "root";
			String passwordDB = "Tanmoy*2";

			connection = DriverManager.getConnection(url, usernameDB, passwordDB);
			PreparedStatement ps = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;

			// to retrieve Owner details:
			String query1 = "SELECT email FROM userdetails " + "WHERE userID = ?";

			ps = connection.prepareStatement(query1);

			// Owner email:

			ps.setInt(1, Integer.parseInt(ownerID));
			rs1 = ps.executeQuery();

			String ownerEmail = null;
			if (rs1.next())
				ownerEmail = rs1.getString("email");

			// to retrieve User details:

			String query = "SELECT email FROM userdetails " + "WHERE username = ?";

			ps = connection.prepareStatement(query);

			// Retrieve seeker email:

			ps.setString(1, customerName);
			rs2 = ps.executeQuery();

			String seekerEmail = null;
			if (rs2.next())
				seekerEmail = rs2.getString("email");

			// Defaults: Housing Network support mailing server:

			String supportEmail = "housingforummail@gmail.com";

			String subject = "Interested in the Rental Posting ID:" + listingID;

			String body = "Dear Seeker,\n\n" + "Here is the Contact detail/Email of the listing Owner:" + ownerEmail
					+ ",\n\n" + "Thanks for using our services." + "We'll make sure to send you additional "
					+ "announcements about new listings " + "and promotions.\n"
					+ "Have a great day and thanks again!\n\n"
					+ "For additional services, please get in touch with: housingforummail@gmail.com \n" + "Regards,\n"
					+ "Housing Network Support Team";

			boolean isBodyHTML = false;
			try {
				MailUtilGmail.sendMail(seekerEmail, supportEmail, subject, body, isBodyHTML);
			} catch (MessagingException e) {
				String errorMessage = "ERROR: Unable to send email. " + e.getMessage();
				request.setAttribute("errorMessage", errorMessage);
				this.log("Unable to send email. \n");
			}
			url2 = "/Success.html";

			getServletContext().getRequestDispatcher(url2).forward(request, response);

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
