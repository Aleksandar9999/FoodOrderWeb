package beans;

import java.util.ArrayList;
import java.util.List;

import enumerations.RestaurantType;
import exceptions.ArticleExistException;

public class Restaurant extends Entity{
	private String name;
	private RestaurantType restaurantType;
	private transient ArrayList<Article> articles;
	private boolean status;
	private Location location;
	private String logoUrl;
	private double avgRate;
	public Restaurant(){
		name="";
		restaurantType=RestaurantType.Other;
		location=new Location();
		
	}
	public Restaurant(String name, RestaurantType restaurantType, ArrayList<Article> articles, boolean status,
			Location location, String logoUrl) {
		super();
		this.name = name;
		this.restaurantType = restaurantType;
		this.articles = articles;
		this.status = status;
		this.location = location;
		this.logoUrl = logoUrl;
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

	public void setArticles(ArrayList<Article> articles) {
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
	
	public void addArticle(Article article){
		if(articles==null)
			articles=new ArrayList<Article>();
		if(!articles.contains(article)){
			articles.add(article);
		}else throw new ArticleExistException();
	}
	public void updateArticle(String idArticle,Article newArticle){
		ValidateNewName(newArticle);
		articles.remove(newArticle);
		articles.add(newArticle);
	}

	private void ValidateNewName(Article newArticle) {
		for (Article article : articles) {
			if(article.getName().equals(newArticle.getName()) && !article.getId().equals(newArticle.getId())) throw new ArticleExistException();
		}
	}
	public double getAvgRate() {
		return avgRate;
	}
	public void setAvgRate(double avgRate) {
		this.avgRate = avgRate;
	}
	
}
