package beans;

import java.util.ArrayList;
import java.util.List;

public class Deliverer extends User {
	private List<Order> orders;
	public void addOrder(Order order){
		if(this.orders== null)
			this.orders=new ArrayList<>();
		this.orders.add(order);
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
