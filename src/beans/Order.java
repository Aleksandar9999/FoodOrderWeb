package beans;

import java.time.LocalDateTime;
import java.util.List;

import DAO.OrderDAO;
import enumerations.OrderStatus;

public class Order extends Entity {
	private List<ArticleInCart> articles;
	private Restaurant restaurant;
	private LocalDateTime timestamp;
	private double price;
	private Buyer buyer;
	private OrderStatus orderStatus;

	public Order(OrderDAO dAo) {
		this.articles=dAo.getArticles();
		this.timestamp=dAo.getTimestamp();
		this.price=dAo.getPrice();
		this.orderStatus=dAo.getOrderStatus();
		this.setId(dAo.getId());
	}
	public Order(Cart cart){
		this.articles=cart.getArticles();
		this.buyer=cart.getBuyer();
		this.timestamp=LocalDateTime.now();
		this.price=cart.getPrice();
		this.orderStatus=OrderStatus.Processing;
	}
	public Order(List<ArticleInCart> articles, Restaurant restaurant, LocalDateTime timestamp, double price,
			Buyer buyer, OrderStatus orderStatus) {
		super();
		this.articles = articles;
		this.restaurant = restaurant;
		this.timestamp = timestamp;
		this.price = price;
		this.buyer = buyer;
		this.orderStatus = orderStatus;
	}
public Order(){}
	public List<ArticleInCart> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleInCart> articles) {
		this.articles = articles;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
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
	public double getPointsCollected(){
		return this.price/1000*133;
	}
	public double getPointsForCanceledOrder(){
		return this.price/1000*133*4;
	}
	
	
}
