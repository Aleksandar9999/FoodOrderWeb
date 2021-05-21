package beans;

import java.time.LocalDateTime;
import java.util.List;

import enumerations.OrderStatus;

public class Order {
	private String id;
	private List<Article> articles;
	private Restaurant restaurant;
	private LocalDateTime timestamp;
	private double price;
	private Buyer buyer;
	private OrderStatus orderStatus;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String id, List<Article> articles, Restaurant restaurant, LocalDateTime timestamp, double price,
			Buyer buyer, OrderStatus orderStatus) {
		super();
		this.id = id;
		this.articles = articles;
		this.restaurant = restaurant;
		this.timestamp = timestamp;
		this.price = price;
		this.buyer = buyer;
		this.orderStatus = orderStatus;
	}
}
