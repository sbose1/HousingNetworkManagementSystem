package HousingNetwork.Model;
import java.io.Serializable;

public class UserDetails implements Serializable {
	private static final long serialVersionUID = -6470090944414208496L;
	private int userID;
	private String email;
	private String pwd;
	private String username;
	private int contact_num;
	private int people_num;
	private String occupation;
	private String type;
	private String preference;
	private String dateNeededFrom;

	//Non-Parameterized constructor for UserDetails
	public UserDetails() {
		super();
		
	}
	
	//Parameterized constructor for UserDetails
	public UserDetails(int userID, String occupation, String type, String preference, String nfd, String email,
			String pwd, String username, int contact_num, int people_num) {
		super();
		this.userID = userID;
		this.occupation = occupation;
		this.type = type;
		this.preference = preference;
		this.dateNeededFrom = nfd;
		this.email = email;
		this.pwd = pwd;
		this.username = username;
		this.contact_num = contact_num;
		this.people_num = people_num;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getContact_num() {
		return contact_num;
	}
	public void setContact_num(int contact_num) {
		this.contact_num = contact_num;
	}
	public int getPeople_num() {
		return people_num;
	}
	public void setPeople_num(int people_num) {
		this.people_num = people_num;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public String getDateNeededFrom() {
		return dateNeededFrom;
	}
	public void setDateNeededFrom(String dateNeededFrom) {
		this.dateNeededFrom = dateNeededFrom;
	}
	

}
