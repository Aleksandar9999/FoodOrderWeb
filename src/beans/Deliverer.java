package beans;

import java.util.ArrayList;
import java.util.List;

public class Deliverer extends User {
	private transient List<Order> orders;
	private List<String> orderIds;

	public void addOrder(Order order) {
		if (this.orders == null)
			this.orders = new ArrayList<>();
		this.orders.add(order);
		this.addOrderId(order);
	}

	public void addOrderId(Order order) {
		if (this.orderIds == null)
			this.orderIds = new ArrayList<>();
		if (!this.orderIds.contains(order.getId()))
			this.orderIds.add(order.getId());
	}

	public List<Order> getOrders() {
		if (orders == null)
			this.orders = new ArrayList<>();
		return orders;
	}

	public List<String> getOrderIds() {
		if (orderIds == null)
			this.orderIds = new ArrayList<>();
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
