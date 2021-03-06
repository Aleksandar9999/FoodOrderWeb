package beans;

public class Address {

	private String street;
	private String number;
	private String city;
	private String zipCode;
	public Address(){
		this.street = "";
		this.number = "";
		this.city = "";
		this.zipCode = "";	
	}
	public Address(String street, String number, String city, String zipCode) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.zipCode = zipCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return String.format("{0} {1} \n {2} {3}", street,number,city,zipCode);
	}
}
