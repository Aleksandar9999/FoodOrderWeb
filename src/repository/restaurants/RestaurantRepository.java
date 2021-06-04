package repository.restaurants;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import DAO.RestaurantDAO;
import beans.Restaurant;
import enumerations.RestaurantType;
import generic.GenericRepository;

public class RestaurantRepository extends GenericRepository<Restaurant,RestaurantDAO> {
	public RestaurantRepository() {
		super("./repo/restaurants.json");
	}
	@Override
	public HashMap<String, RestaurantDAO> transformData(HashMap<String, Restaurant> data) {
		HashMap<String, RestaurantDAO> retVal=new HashMap<>();
		for (Restaurant order : data.values()) {
			retVal.put(order.getId(), new RestaurantDAO(order));
		}
		return retVal;
	}

	@Override
	public HashMap<String, Restaurant> transformDAO(HashMap<String, RestaurantDAO> data) {
		HashMap<String, Restaurant> retVal=new HashMap<>();
		for (RestaurantDAO order : data.values()) {
			retVal.put(order.getId(), new Restaurant(order));
		}
		return retVal;
	}

	@Override
	public HashMap<String, Restaurant> readAll() {
		String json = readFromFile();
		Type type = new TypeToken<HashMap<String, RestaurantDAO>>() {
		}.getType();
		HashMap<String, RestaurantDAO> data = gson.fromJson(json, type);
		return transformDAO(data);
	}
	public List<Restaurant> getAllRestaurantsSorted() {
		List<Restaurant> restaurants = getAll();
		restaurants.sort(new Comparator<Restaurant>() {

			@Override
			public int compare(Restaurant o1, Restaurant o2) {
				return Boolean.compare(o2.isStatus(), o1.isStatus());
			}
		});
		return restaurants;
	}

	public List<Restaurant> getAllByName(String name) {
		List<Restaurant> restaurants = getAll();
		restaurants.removeIf(res -> !res.getName().toLowerCase().contains(name.toLowerCase()));
		return restaurants;
	}

	public List<Restaurant> getAllByType(RestaurantType type) {
		List<Restaurant> restaurants = getAll();
		restaurants.removeIf(res -> !res.getRestaurantType().equals(type));
		return restaurants;
	}

	public List<Restaurant> getAllByCity(String locString) {
		List<Restaurant> restaurants = getAll();
		restaurants
				.removeIf(res -> !res.getLocation().getAddress().getCity().toLowerCase().contains(locString.toLowerCase()));
		return restaurants;
	}

	public List<Restaurant> getAllByCountry(String locString) {
		/* List<Restaurant> restaurants = getAll();
		restaurants
				.removeIf(res -> !res.getLocation().getAddress().get().toLowerCase().contains(locString.toLowerCase()));
		return restaurants; */
		
		throw new UnsupportedOperationException("Implementirj");
	}
	public List<Restaurant> getAllByAvgRate(String name) {
		throw new UnsupportedOperationException("Implementirj");
	}
	
	public static void main(String[] args) {
		RestaurantRepository restaurantRepository = new RestaurantRepository();
		for (Restaurant res : restaurantRepository.getAllByName("pizza")) {
			System.out.println(res.getName());
		}
	}

	
}
