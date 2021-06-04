package beans;

import enumerations.Role;

public class Manager extends User {

	private String restaurantId;
	public Manager(String username,String password,String name, String surname) {
		super(username, password, name, surname, Role.Manager);
	}
	
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
}
