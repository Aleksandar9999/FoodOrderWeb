package beans;

public class Comment extends Entity {
	private transient Buyer buyer;
	private String buyerUsername;
	private transient Restaurant restaurant;
	private String restaurantId;
	private String comment;
	private int mark;
	private boolean approved;

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Comment(String id,Buyer buyer, Restaurant restaurant, String comment, int mark,boolean approved) {
		super(id);
		this.approved=approved;
		this.buyer = buyer;
		this.restaurant = restaurant;
		this.restaurantId=restaurant.getId();
		this.buyerUsername=buyer.getUsername();
		this.comment = comment;
		this.mark = mark;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

	public String getRestaurantId() {
		return restaurantId;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}

}
