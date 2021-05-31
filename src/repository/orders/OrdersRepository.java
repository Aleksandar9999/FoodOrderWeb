package repository.orders;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.Order;
import beans.Restaurant;
import beans.User;
import generic.GenericRepository;

public class OrdersRepository extends GenericRepository<Order> {

	public OrdersRepository() {
		super("./repo/orders.json");	
	}

	@Override
	public HashMap<String, Order> readAll() {
		String json = readFromFile();
		Type type = new TypeToken<HashMap<String, Order>>() {
		}.getType();
		HashMap<String, Order> users = gson.fromJson(json, type);
		return users;
	}
	public List<Order> getAllByRestaurant(String id){
		HashMap<String, Order> orders=readAll();
		List<Order> retVal=new ArrayList<Order>();
		for (Order order : orders.values()) {
			if(order.getRestaurant().getId().equals(id))
				retVal.add(order);
		}
		return retVal;
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
