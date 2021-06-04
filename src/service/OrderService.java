package service;
import java.util.List;

import DAO.OrderDAO;
import beans.Buyer;
import beans.Cart;
import beans.Order;
import beans.User;
import enumerations.OrderStatus;
import exceptions.CanUpdateOrderException;
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
	public void updateOrder(Order newOrder){
		Order order=((OrdersRepository)this.repository).getById(newOrder.getId());
		if(!order.getOrderStatus().equals(OrderStatus.Processing)){ throw new CanUpdateOrderException();}
		this.update(newOrder);
	}
	public static void main(String[] args) {
		OrderService orderService=new OrderService();
		CartService cartService=new CartService();
		Order order=orderService.getAll().get(0);
		order.setOrderStatus(OrderStatus.Canceled);
		orderService.updateOrder(order);
	}
}
