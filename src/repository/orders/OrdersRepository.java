package repository.orders;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import DAO.OrderDAO;
import beans.Buyer;
import beans.Order;
import beans.User;
import enumerations.OrderStatus;
import enumerations.RestaurantType;
import generic.GenericRepository;
import repository.restaurants.RestaurantRepository;
import repository.users.UsersRepository;

public class OrdersRepository extends GenericRepository<Order,OrderDAO> {

	public OrdersRepository() {
		super("./repo/orders.json");	
	}

	@Override
	public HashMap<String, Order> readAll() {
		String json = readFromFile();
		Type type = new TypeToken<HashMap<String, OrderDAO>>() {
		}.getType();
		HashMap<String, OrderDAO> data = gson.fromJson(json, type);
		if(data==null) data=new HashMap<>();
		return transformDAO(data);
	}
	@Override
	public HashMap<String, OrderDAO> transformData(HashMap<String, Order> data) {
		HashMap<String, OrderDAO> retVal=new HashMap<>();
		for (Order order : data.values()) {
			retVal.put(order.getId(), new OrderDAO(order));
		}
		return retVal;
	}

	@Override
	public HashMap<String, Order> transformDAO(HashMap<String, OrderDAO> data) {
		HashMap<String, Order> retVal=new HashMap<>();
		UsersRepository usersRepository=new UsersRepository();
		RestaurantRepository restaurantRepository=new RestaurantRepository();
		for (OrderDAO dao : data.values()) {
			Order order=new Order(dao);
			order.setRestaurant(restaurantRepository.getById(dao.getRestaurantId()));
			order.setBuyer((Buyer)usersRepository.getByUsername(dao.getBuyerUsername()));
			retVal.put(dao.getId(), order);
		}
		return retVal;
	}
	public List<Order> getAllByRestaurant(String id){
		List<Order> orders= getAll();
		orders.removeIf(or->!or.getRestaurant().getId().equals(id));
		return orders;
	}
	public List<User> getAllBuyersByRestaruantId(String id){
		List<User> users=new ArrayList<User>();
		for (Order order : readAll().values()) {
			if(order.getRestaurant().getId().equals(id))
				users.add(order.getBuyer());
		}
		return users;
	}	
}
