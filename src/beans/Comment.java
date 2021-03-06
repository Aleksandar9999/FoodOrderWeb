package beans;

import enumerations.RequestStatus;

public class Comment extends Entity {
	private transient Buyer buyer;
	private String buyerUsername;
	private transient Restaurant restaurant;
	private String restaurantId;
	private String comment;
	private int mark;
	private RequestStatus status;
	private String orderId;
	public RequestStatus getStatus() {
		return status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
		this.buyerUsername = buyer.getUsername();
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.restaurantId = restaurant.getId();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getMark() {
		return mark;
	}

	public String getBuyerId() {
		return buyerUsername;
	}

	public String getBuyerUsername() {
		return buyerUsername;
	}

	public void setBuyerUsername(String buyerUsername) {
		this.buyerUsername = buyerUsername;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

}
