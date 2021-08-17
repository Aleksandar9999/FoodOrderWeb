package beans;

import java.util.ArrayList;
import java.util.List;

import enumerations.UserRole;

public class Buyer extends User {
	private double pointsCollected;
	private List<Order> orders;
	private Cart cart;
	private BuyerType buyerType;

	public Buyer() {
		super(UserRole.Buyer);
		pointsCollected = 0;
		orders = new ArrayList<Order>();
	}

	public double getPointsCollected() {
		return pointsCollected;
	}

	public void setPointsCollected(double pointsCollected) {
		this.pointsCollected = pointsCollected;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void addNewOrder(Order order) {
		if (this.orders == null)
			this.orders = new ArrayList<Order>();
		this.orders.add(order);
	}

	public void addCollectedPoints(double points) {
		this.pointsCollected += points;
	}

	
}
