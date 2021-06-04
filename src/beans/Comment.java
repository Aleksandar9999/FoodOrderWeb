package beans;

public class Comment {
	private Buyer buyer;
	private Restaurant restaurant;
	private String comment;
	private int mark;

	public Comment() {
	}

	public Comment(Buyer buyer, Restaurant restaurant, String comment, int mark) {
		super();
		this.buyer = buyer;
		this.restaurant = restaurant;
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

	public void setMark(int mark) {
		this.mark = mark;
	}
	

}
