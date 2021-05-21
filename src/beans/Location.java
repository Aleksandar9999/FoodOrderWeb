package beans;

public class Location {
	private double longitude;
	private double latitude;
	private Address address;
	
	
	public Location(double longitude, double latitude, Address address) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
	}

	public Location() {
		// TODO Auto-generated constructor stub
	}
}
