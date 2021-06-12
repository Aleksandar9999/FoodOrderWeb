package DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import beans.Article;
import beans.ArticleInCart;
import beans.Entity;
import beans.Order;
import enumerations.OrderStatus;

public class OrderDAO extends Entity{
    private List<ArticleInCart> articles;
	private String restaurantId;
	private LocalDateTime timestamp;
	private double price;
	private String buyerUsername;
	private OrderStatus orderStatus;

    public OrderDAO(Order order){
        this.setId(order.getId());
        this.articles=order.getArticles();
        this.restaurantId=order.getRestaurant().getId();
        this.timestamp=order.getTimestamp();
        this.price=order.getPrice();
        this.buyerUsername=order.getBuyer().getUsername();
        this.orderStatus=order.getOrderStatus();
    }
    public OrderDAO(String id,List<ArticleInCart> articles, String restaurantId, LocalDateTime timestamp, double price, String buyerId,
            OrderStatus orderStatus) {
        super(id);
        this.articles = articles;
        this.restaurantId = restaurantId;
        this.timestamp = timestamp;
        this.price = price;
        this.buyerUsername = buyerId;
        this.orderStatus = orderStatus;
    }
    public List<ArticleInCart> getArticles() {
        return articles;
    }
    public void setArticles(List<ArticleInCart> articles) {
        this.articles = articles;
    }
    public String getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
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
    public String getBuyerUsername() {
        return buyerUsername;
    }
    public void setBuyerUsername(String buyerId) {
        this.buyerUsername = buyerId;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
}
