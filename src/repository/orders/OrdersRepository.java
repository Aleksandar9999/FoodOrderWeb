package repository.orders;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.Buyer;
import beans.DeliverRequest;
import beans.Deliverer;
import beans.Order;
import beans.SuspiciousUser;
import beans.User;
import enumerations.OrderStatus;
import enumerations.RequestStatus;
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
		// UsersRepository repository = new UsersRepository();
		RestaurantRepository restaurantRepository = new RestaurantRepository();
		for (Order order : data.values()) {
			// order.setBuyer((Buyer) repository.getByUsername(order.getBuyerUsername()));
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
		UsersRepository usersRepository = new UsersRepository();
		for (Order order : readAll().values()) {
			if (order.getRestaurant().getId().equals(id))
				users.add(usersRepository.getByUsername(order.getBuyerUsername()));
		}
		return users;
	}

	public List<Order> getAllByBuyerUsername(String username) {
		List<Order> buyerOrders = new ArrayList<Order>();
		for (Order order : readAll().values()) {
			if (order.getBuyerUsername().equals(username))
				buyerOrders.add(order);
		}
		return buyerOrders;
	}

	public ArrayList<Order> getAllForDeliverer(String username) {
		ArrayList<Order> orders = new ArrayList<>(readAll().values());
		removeIfOrdersNotWaitingForDeliverer(orders);
		removeIfDeliverRequestExist(orders);
		addUserOrders(orders, username);
		return orders;
	}

	private void addUserOrders(ArrayList<Order> orders, String username) {
		UsersService usersService = new UsersService();
		Deliverer deliverer = (Deliverer) usersService.getByUsername(username);
		for (Order ord : deliverer.getOrders()) {
			orders.add(ord);
		}
	}

	private void removeIfOrdersNotWaitingForDeliverer(ArrayList<Order> orders) {
		orders.removeIf(order -> !order.getOrderStatus().equals(OrderStatus.WaitingDeliverer));
	}

	private void removeIfDeliverRequestExist(ArrayList<Order> orders) {
		DeliverRequestRepository deliverRequestRepository = new DeliverRequestRepository();
		for (DeliverRequest request : deliverRequestRepository.getAll()) {
			orders.removeIf(order -> order.getId().equals(request.getOrderId())
					&& request.getStatus().equals(RequestStatus.Pending));
		}
	}

	private ArrayList<Order> getCanceledOrders() {
		ArrayList<Order> orders = new ArrayList<>(readAll().values());
		orders.removeIf(order -> !order.getOrderStatus().equals(OrderStatus.Canceled));
		return orders;
	}

	public ArrayList<SuspiciousUser> getSuspiciousUsers() {
		ArrayList<Order> orders = getCanceledOrders();
		ArrayList<SuspiciousUser> suspiciousUsers = new ArrayList<SuspiciousUser>();
		for (Order order : orders) {
			SuspiciousUser suspiciousUser = this.createSuspiciousUser(orders, order);
			if (suspiciousUser.isValid())
				suspiciousUsers.add(suspiciousUser);
		}
		return suspiciousUsers;
	}

	private SuspiciousUser createSuspiciousUser(ArrayList<Order> orders, Order order) {
		UsersRepository usersRepository = new UsersRepository();
		String buyerUsername = order.getBuyerUsername();
		SuspiciousUser suspiciousUser = new SuspiciousUser(usersRepository.getByUsername(buyerUsername));
		suspiciousUser.setCountOfCanceledOrders(this.countCanceledOrdersThisMonth(orders, buyerUsername));
		return suspiciousUser;
	}

	private int countCanceledOrdersThisMonth(ArrayList<Order> orders, String buyerUsername) {
		int count = 0;
		for (Order order : orders) {
			if (order.getBuyerUsername().equals(buyerUsername) && this.isOrderInLast30Days(order))
				count++;
		}
		return count;
	}

	private boolean isOrderInLast30Days(Order order) {
		LocalDateTime monthAgo = LocalDateTime.now().minusDays(30);
		return order.getTimestamp().isAfter(monthAgo);
	}

}
