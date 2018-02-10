import java.io.Serializable;

public class RentalUnit implements Serializable {
	
    private static final long serialVersionUID = -6470090944414208496L;
	private int apartmentID;
	private String owner;
	private int rent;
	private int maxPeople;
	private int bedRoomCount;
	private int bathRoomCount;
	private String apartmentNo;
	
	private String vacant;
	private int deposit;
	private String type;
	private String facility;
	
	private String society;
	private String building;
	
	
	
	//Zero Parameterized constructor for RentalUnit
	public RentalUnit(){};
	
	//Parameterized constructor for RentalUnit
	public RentalUnit(String apartmentNo, int maxPeople, int rent, int deposit, String type) {
		super();
		this.apartmentNo = apartmentNo;
		this.maxPeople = maxPeople;
		this.rent = rent;
		this.deposit = deposit;
		this.type = type;
		
	}
	
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}
	
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
	public String getSociety() {
		return society;
	}

	public void setSociety(String society) {
		this.society = society;
	}


	public int getBedRoomCount() {
		return bedRoomCount;
	}

	public void setBedRoomCount(int bedRoomCount) {
		this.bedRoomCount = bedRoomCount;
	}

	public int getBathRoomCount() {
		return bathRoomCount;
	}

	public void setBathRoomCount(int bathRoomCount) {
		this.bathRoomCount = bathRoomCount;
	}
	
	public int getApartmentID() {
		return apartmentID;
	}
	public void setApartmentID(int apartmetnID) {
		this.apartmentID = apartmetnID;
	}
		
	
	public String getApartmentNo() {
		return apartmentNo;
	}
	public void setApartmentNo(String apartmentNo) {
		this.apartmentNo = apartmentNo;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String isVacant() {
		return vacant;
	}
	public void setVacant(String vacant) {
		this.vacant = vacant;
	}
}