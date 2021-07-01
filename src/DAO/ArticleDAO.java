package DAO;

import beans.Article;
import beans.Entity;
import beans.Restaurant;
import enumerations.ArticleType;

public class ArticleDAO extends Entity{
	private String name;
	private double price;
	private ArticleType articleType;
	private String restaurantId;
	private double amount;
	private String comment;
	private String imageUrl;
    public ArticleDAO(Article article){
		this.setId(article.getId());
        this.name=article.getName();
        this.price=article.getPrice();
        this.articleType=article.getArticleType();
        this.restaurantId=article.getRestaurant().getId();
        this.amount=article.getWeight();
        this.comment=article.getComment();
        this.imageUrl=article.getImageUrl();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public ArticleType getArticleType() {
        return articleType;
    }
    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }
    public String getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
}
