package service;
import java.util.List;

import beans.Order;
import beans.User;
import enumerations.OrderStatus;
import exceptions.CanUpdateOrderException;
import generic.GenericFileRepository;
import generic.GenericFileService;
import repository.orders.OrdersRepository;

public class OrderService extends GenericFileService<Order>{

	public OrderService(GenericFileRepository<Order> repository) {
		super(repository);
	}

	public OrderService() {
		super(new OrdersRepository());
	}
	public List<Order> getAllByRestaurant(String id){
		return ((OrdersRepository)this.repository).getAllByRestaurant(id);
	}
	
	public List<User> getAllBuyersByRestaruantId(String id){
		return ((OrdersRepository)this.repository).getAllBuyersByRestaruantId(id);
	}
	public void updateOrder(Order newOrder){
		Order order=((OrdersRepository)this.repository).getById(newOrder.getId());
		if(!order.getOrderStatus().equals(OrderStatus.Processing)){ throw new CanUpdateOrderException();}
		this.update(newOrder);
	}
	
}
