package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import beans.Order;
import generic.GenericService;
import repository.orders.OrdersRepository;

public class OrderService extends GenericService<Order> {

	public OrderService() {
		super(new OrdersRepository());
	}
	public List<Order> getAllByRestaurant(String id){
		return ((OrdersRepository)this.repository).getAllByRestaurant(id);
	}

}
