package beans;

public class Comment {
	private Buyer buyer;
	private Restaurant restaurant;
	private String comment;
	private int mark;

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(Buyer buyer, Restaurant restaurant, String comment, int mark) {
		super();
		this.buyer = buyer;
		this.restaurant = restaurant;
		this.comment = comment;
		this.mark = mark;
	}

}
