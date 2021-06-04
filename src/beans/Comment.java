package beans;

import DAO.CommentDAO;

public class Comment extends Entity {
	private Buyer buyer;
	private Restaurant restaurant;
	private String comment;
	private int mark;
	private boolean approved;

	public Comment(CommentDAO dao) {
		super(dao.getId());
		this.comment = dao.getComment();
		this.mark = dao.getMark();
	}

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
