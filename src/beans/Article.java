package beans;

import java.util.UUID;

import enumerations.ArticleType;

public class Article {
	private String id;
	private String name;
	private double price;
	private ArticleType articleType;
	private Restaurant restaurant;
	private double amount;
	private String comment;
	private String imageUrl;

	public Article() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getWeight() {
		return amount;
	}

	public void setWeight(double weight) {
		this.amount = weight;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Article) obj).getId().equals(id);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}


	public Article(String id,String name, double price, ArticleType articleType, Restaurant restaurant, double quantity,
			String comment, String imageUrl) {
		this.name = name;
		this.id=id;
		this.price = price;
		this.articleType = articleType;
		this.restaurant = restaurant;
		this.amount = quantity;
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
		return amount;
	}

	public void setQuantity(double quantity) {
		this.amount = quantity;
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
