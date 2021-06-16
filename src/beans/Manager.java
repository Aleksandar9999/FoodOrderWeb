package beans;

import enumerations.Role;

public class Manager extends User {

	private String restaurantId;

	public Manager(){}
	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
}
