package beans;

import java.util.UUID;

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

	@Override
	public boolean equals(Object obj) {
		return ((Article) obj).getName().equals(name);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}


	public Article(String name, double price, ArticleType articleType, Restaurant restaurant, double quantity,
			String comment, String imageUrl) {
		this.name = name;
		this.price = price;
		this.articleType = articleType;
		this.restaurant = restaurant;
		this.quantity = quantity;
		this.comment = comment;
		this.imageUrl = imageUrl;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
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
