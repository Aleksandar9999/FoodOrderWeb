package service;
import java.util.List;

import DAO.RestaurantDAO;
import beans.Restaurant;
import enumerations.RestaurantType;
import generic.GenericService;
import repository.restaurants.RestaurantRepository;

public class RestaurantService extends GenericService<Restaurant,RestaurantDAO> {
	public RestaurantService() {
		super(new RestaurantRepository());	
	}
	public List<Restaurant> getAllRestaurantsSorted(){
		return ((RestaurantRepository)repository).getAllRestaurantsSorted();
	}
	public List<Restaurant> getAllByName(String name) {
		return ((RestaurantRepository)repository).getAllByName(name);
	}

	public List<Restaurant> getAllByType(RestaurantType type) {
		return ((RestaurantRepository)repository).getAllByType(type);
	}

	public List<Restaurant> getAllByCity(String locString) {
		return ((RestaurantRepository)repository).getAllByCity(locString);
	}
}
