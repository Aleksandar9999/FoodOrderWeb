package repository.restaurants;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.Restaurant;
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

	public List<Restaurant> getAllRestaurantsSorted(){
		List<Restaurant> restaurants=getAll();

		restaurants.sort(new Comparator<Restaurant>(){

			@Override
			public int compare(Restaurant o1, Restaurant o2) {
				return Boolean.compare(o2.isStatus(),o1.isStatus());
			}
			
		});
		return restaurants;
	}
}
