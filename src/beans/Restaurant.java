package beans;

import java.util.List;

import enumerations.RestaurantType;

public class Restaurant {

	private String name;
	private RestaurantType restaurantType;
	private List<Article> articles;
	private boolean status;
	private Location location;
	private String logoUrl;

	public Restaurant(String name, RestaurantType restaurantType, List<Article> articles, boolean status,
			Location location, String logoUrl) {
		super();
		this.name = name;
		this.restaurantType = restaurantType;
		this.articles = articles;
		this.status = status;
		this.location = location;
		this.logoUrl = logoUrl;
	}

	public Restaurant() {
		super();
	}
}
