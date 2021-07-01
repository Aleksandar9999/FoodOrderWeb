package beans;

import enumerations.UserRole;

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
