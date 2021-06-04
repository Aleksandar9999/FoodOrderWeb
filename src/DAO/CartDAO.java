package DAO;

import java.util.List;

import beans.ArticleInCart;
import beans.Cart;
import beans.Entity;

public class CartDAO extends Entity {
	public List<ArticleInCart> articles;
	public String buyerUsername;
	public double price;
	
	public CartDAO(List<ArticleInCart> articles, String buyerId, double price) {
		this.articles = articles;
		this.buyerUsername = buyerId;
		this.price = price;
	}

	public CartDAO(Cart cart){
		super(cart.getId());
		this.articles=cart.getArticles();
		this.buyerUsername=cart.getBuyer().getUsername();
		this.price=cart.getPrice();
	}
	public List<ArticleInCart> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleInCart> articles) {
		this.articles = articles;
	}
	public String getBuyerId() {
		return buyerUsername;
	}
	public void setBuyerId(String buyerId) {
		this.buyerUsername = buyerId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
