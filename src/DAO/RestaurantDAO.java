package DAO;

import java.util.List;

import beans.Article;
import beans.Entity;
import beans.Location;
import beans.Restaurant;
import enumerations.RestaurantType;

public class RestaurantDAO extends Entity {
    private String name;
	private RestaurantType restaurantType;
	private List<Article> articles;
	private boolean status;
	private Location location;
	private String logoUrl;
    public RestaurantDAO() {
    }
    public RestaurantDAO(Restaurant restaurant) {
        super(restaurant.getId());
        this.name=restaurant.getName();
        this.restaurantType=restaurant.getRestaurantType();
        this.articles=restaurant.getArticles();
        this.status=restaurant.isStatus();
        this.location=restaurant.getLocation();
        this.logoUrl=restaurant.getLogoUrl();
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
