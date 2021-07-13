package service;
import java.util.List;

import beans.Restaurant;
import generic.GenericFileRepository;
import generic.GenericFileService;
import repository.restaurants.RestaurantRepository;

public class RestaurantService extends GenericFileService<Restaurant> {

	public RestaurantService(GenericFileRepository<Restaurant> repository) {
		super(repository);
	}
	public RestaurantService() {
		super(new RestaurantRepository());	
	}
	public List<Restaurant> getAllRestaurantsSorted(){
		return ((RestaurantRepository)repository).getAllRestaurantsSorted();
	}
}
