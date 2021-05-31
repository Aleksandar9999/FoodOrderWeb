package beans;

import java.time.LocalDateTime;
import java.util.List;

import enumerations.OrderStatus;

public class Order extends Entity {
	private List<Article> articles;
	private Restaurant restaurant;
	private String restaurantId;
	private LocalDateTime timestamp;
	private double price;
	private Buyer buyer;
	private OrderStatus orderStatus;

	public Order() {}

	public Order(String id, List<Article> articles, Restaurant restaurant, LocalDateTime timestamp, double price,
			Buyer buyer, OrderStatus orderStatus) {
		super(id);
		this.restaurantId=restaurant.getId();
		this.articles = articles;
		this.restaurant = restaurant;
		this.timestamp = timestamp;
		this.price = price;
		this.buyer = buyer;
		this.orderStatus = orderStatus;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurantId=restaurant.getId();
		this.restaurant = restaurant;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
}
