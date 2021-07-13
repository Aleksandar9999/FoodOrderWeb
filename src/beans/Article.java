package beans;

import enumerations.ArticleType;

public class Article extends Entity {

	private String name;
	private double price;
	private ArticleType articleType;
	private transient Restaurant restaurant;
	private double amount;
	private String comment;
	private String imageUrl;
	private String restaurantId;

	public Article() {
	}

	public double getWeight() {
		return amount;
	}

	public void setWeight(double weight) {
		this.amount = weight;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Article) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
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
		this.restaurantId = restaurant.getId();
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

	public String getRestaurantId() {
		return this.restaurantId;
	}
}
