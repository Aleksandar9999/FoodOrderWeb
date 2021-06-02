package repository.restaurants;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.Restaurant;
import enumerations.RestaurantType;
import generic.GenericRepository;

public class RestaurantRepository extends GenericRepository<Restaurant> {
	public RestaurantRepository() {
		super("./repo/restaurants.json");
	}

	// TODO Ukoliko se nalazi u GenericRepo kao value u HashMap dobijam StringMap
	@Override
	public HashMap<String, Restaurant> readAll() {
		String json = readFromFile();
		Type type = new TypeToken<HashMap<String, Restaurant>>() {
		}.getType();
		HashMap<String, Restaurant> users = gson.fromJson(json, type);
		return users;
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
