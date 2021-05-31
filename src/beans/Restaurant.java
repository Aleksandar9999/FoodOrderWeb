package beans;

import java.util.List;

import enumerations.RestaurantType;

public class Restaurant {

	private String id;
	private String name;
	private RestaurantType restaurantType;
	private List<Article> articles;
	private boolean status;
	private Location location;
	private String logoUrl;

	public Restaurant(String id,String name, RestaurantType restaurantType, List<Article> articles, boolean status,
			Location location, String logoUrl) {
		super();
		this.id=id;
		this.name = name;
		this.restaurantType = restaurantType;
		this.articles = articles;
		this.status = status;
		this.location = location;
		this.logoUrl = logoUrl;
	}

	public Restaurant() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RestaurantType getRestaurantType() {
		return restaurantType;
	}

	public void setRestaurantType(RestaurantType restaurantType) {
		this.restaurantType = restaurantType;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	
	
}
