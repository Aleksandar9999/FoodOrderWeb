package beans;

import java.time.LocalDateTime;
import java.util.List;

import enumerations.OrderStatus;

public class Order extends Entity {
	private List<ArticleInCart> articles;
	private transient Restaurant restaurant;
	private String buyerUsername;
	private String restaurantId;
	private LocalDateTime timestamp;
	private double price;
	private OrderStatus orderStatus;
	private String buyerNameSurname; 
	public Order(Cart cart) {
		this.articles = cart.getArticles();
		this.timestamp = LocalDateTime.now();
		this.price = cart.getPrice();
		this.orderStatus = OrderStatus.Processing;
		this.restaurantId=cart.getArticles().get(0).getArticle().getRestaurantId();
		this.setBuyer(cart.getBuyer());
	}
	public void setBuyer(Buyer buyer) {
		this.buyerUsername=buyer.getUsername();
		this.buyerNameSurname=buyer.getName()+" "+ buyer.getSurname();
	}
	public void setBuyerUsername(String buyerUsername) {
		this.buyerUsername = buyerUsername;
	}
	
	public String getBuyerNameSurname() {
		return buyerNameSurname;
	}
	public void setBuyerNameSurname(String buyerNameSurname) {
		this.buyerNameSurname = buyerNameSurname;
	}
	public String getBuyerUsername() {
		return buyerUsername;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Order() {
	}

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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getPointsCollected() {
		return this.price / 1000 * 133;
	}

	public double getPointsForCanceledOrder() {
		return -(this.price / 1000 * 133 * 4);
	}
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return ((Order)obj).getId().equals(this.getId());
	}

}
