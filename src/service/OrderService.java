package service;
import java.util.Collection;
import java.util.List;

import beans.Buyer;
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
	public List<Order> getAllByBuyerUsername(String username){
		return ((OrdersRepository)this.repository).getAllByBuyerUsername(username);
	}
	public Collection<Order> getAllForDeliverer(String username){
		return ((OrdersRepository)this.repository).getAllForDeliverer(username);
	}

	@Override
	public Order addNew(Order order) {
		updateBuyerCollectedPoints(order.getBuyerUsername(),order.getPointsCollected());
		return super.addNew(order);
	}

	@Override
	public void update(Order order) {
		if(order.getOrderStatus().equals(OrderStatus.Canceled)){
			updateBuyerCollectedPoints(order.getBuyerUsername(), order.getPointsForCanceledOrder());
		}
		super.update(order);
	}
	
	private void updateBuyerCollectedPoints(String buyerUsername,double collectedPoints){
		UsersService usersService = new UsersService();
		Buyer buyer=(Buyer) usersService.getByUsername(buyerUsername);
		buyer.addCollectedPoints(collectedPoints);
		usersService.update(buyer.getUsername(), buyer);
	}
}
