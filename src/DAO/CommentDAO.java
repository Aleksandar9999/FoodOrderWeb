package DAO;

import beans.Comment;
import beans.Entity;

public class CommentDAO extends Entity {
    private String buyerUsername;
    private String restaurantId;
    private String comment;
    private int mark;
    private boolean approved;

    public CommentDAO(Comment comment) {
        super(comment.getId());
        this.approved=comment.isApproved();
        this.buyerUsername = comment.getBuyer().getUsername();
        this.restaurantId = comment.getRestaurant().getId();
        this.comment = comment.getComment();
        this.mark = comment.getMark();
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getBuyerId() {
        return buyerUsername;
    }

    public void setBuyerId(String buyerId) {
        this.buyerUsername = buyerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
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
