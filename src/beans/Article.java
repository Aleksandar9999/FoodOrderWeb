package beans;

import java.math.BigDecimal;

import enumerations.ArticleType;

public class Article {
	
	private String name;
	private double price;
	private ArticleType articleType;
	private Restaurant restaurant;
	private double quantity;
	private String comment;
	private String imageUrl;

	public Article() {
	}

	public Article(String name, double price, ArticleType articleType, Restaurant restaurant, double quantity,
			String comment, String imageUrl) {
		super();
		this.name = name;
		this.price = price;
		this.articleType = articleType;
		this.restaurant = restaurant;
		this.quantity = quantity;
		this.comment = comment;
		this.imageUrl = imageUrl;
	}
}
