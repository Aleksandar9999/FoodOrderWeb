package repository.orders;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.Buyer;
import beans.Deliverer;
import beans.Order;
import beans.User;
import enumerations.OrderStatus;
import generic.GenericFileRepository;
import repository.deliverRequest.DeliverRequestRepository;
import repository.restaurants.RestaurantRepository;
import repository.users.UsersRepository;
import service.UsersService;

public class OrdersRepository extends GenericFileRepository<Order> {

	public OrdersRepository() {
		super("./repo/orders.json");
	}

	@Override
	public HashMap<String, Order> readAll() {
		String json = readFromFile();
		Type type = new TypeToken<HashMap<String, Order>>() {
		}.getType();
		HashMap<String, Order> data = gson.fromJson(json, type);
		if (data == null)
			data = new HashMap<>();
		return mergeWithObjects(data);

	}

	private HashMap<String, Order> mergeWithObjects(HashMap<String, Order> data) {
		UsersRepository repository = new UsersRepository();
		RestaurantRepository restaurantRepository = new RestaurantRepository();
		for (Order order : data.values()) {
			order.setBuyer((Buyer) repository.getByUsername(order.getBuyerUsername()));
			order.setRestaurant(restaurantRepository.getById(order.getRestaurantId()));
		}
		return data;
	}

	public List<Order> getAllByRestaurant(String id) {
		List<Order> orders = getAll();
		orders.removeIf(order -> !order.getRestaurantId().equals(id));
		return orders;
	}

	public List<User> getAllBuyersByRestaruantId(String id) {
		List<User> users = new ArrayList<User>();
		for (Order order : readAll().values()) {
			if (order.getRestaurant().getId().equals(id))
				users.add(order.getBuyer());
		}
		return users;
	}
	
	public List<Order> getAllByBuyerUsername(String username){
		List<Order> buyerOrders=new ArrayList<Order>();
		for (Order order : readAll().values()) {
			if(order.getBuyerUsername().equals(username)) buyerOrders.add(order);
		}
		return buyerOrders;
	}

	public ArrayList<Order> getAllForDeliverer(String username){
		//getAllWaitingForDevliver
		ArrayList<Order> orders= new ArrayList<>(readAll().values());
		orders.removeIf(order -> !order.getOrderStatus().equals(OrderStatus.WaitingDeliverer));
		//getAllFor transport
		UsersService usersService=new UsersService();
		Deliverer deliverer=(Deliverer) usersService.getByUsername(username);
		for (Order ord : deliverer.getOrders()) {
			orders.add(ord);
		}
		return orders;
	}
}
