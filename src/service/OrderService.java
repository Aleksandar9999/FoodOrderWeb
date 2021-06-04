package service;
import java.util.List;

import DAO.OrderDAO;
import beans.Order;
import beans.User;
import generic.GenericService;
import repository.orders.OrdersRepository;

public class OrderService extends GenericService<Order,OrderDAO>{

	public OrderService() {
		super(new OrdersRepository());
	}
	public List<Order> getAllByRestaurant(String id){
		return ((OrdersRepository)this.repository).getAllByRestaurant(id);
	}
	
	public List<User> getAllBuyersByRestaruantId(String id){
		return ((OrdersRepository)this.repository).getAllBuyersByRestaruantId(id);
	}
}
