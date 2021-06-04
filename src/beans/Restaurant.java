package beans;

import java.util.ArrayList;
import java.util.List;

import DAO.RestaurantDAO;
import enumerations.RestaurantType;
import exceptions.ArticleExistException;

public class Restaurant extends Entity{
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

	public Restaurant(RestaurantDAO restaurantDAO) {
		super(restaurantDAO.getId());
        this.name=restaurantDAO.getName();
        this.restaurantType=restaurantDAO.getRestaurantType();
        this.articles=restaurantDAO.getArticles();
        this.status=restaurantDAO.isStatus();
        this.location=restaurantDAO.getLocation();
        this.logoUrl=restaurantDAO.getLogoUrl();
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
	
	public void addArticle(Article article){
		if(articles==null)
			articles=new ArrayList<Article>();
		if(!articles.contains(article)){
			articles.add(article);
		}else throw new ArticleExistException();
	}
	public void updateArticle(String idArticle,Article newArticle){
		ValidateNewName(newArticle);
		for (Article article : articles) {
			if(article.getId().equals(idArticle)){
				articles.remove(article);
				break;
			}
		}
		articles.add(newArticle);
	}

	private void ValidateNewName(Article newArticle) {
		for (Article article : articles) {
			if(article.getName().equals(newArticle.getName()) && !article.getId().equals(newArticle.getId())) throw new ArticleExistException();
		}
	}
}
