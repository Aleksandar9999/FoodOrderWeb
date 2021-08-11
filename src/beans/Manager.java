package beans;

public class Manager extends User {
	private String restaurantId;
	private transient Restaurant restaurant;
	public Manager(){}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.restaurantId=restaurant.getId();
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	
}
