package service;

import java.util.ArrayList;
import java.util.HashMap;

import beans.Restaurant;
import repository.restaurants.RestaurantRepository;

public class RestaurantService {

	private RestaurantRepository repository;
	
	public RestaurantService() {
		this.repository=new RestaurantRepository();
	}
	
	public ArrayList<Restaurant> getAll() {
		return repository.getAll();
	}
	public Restaurant getById(String id) {
		return repository.getById(id);
	}
	public Restaurant addNew(Restaurant restaurant) {
		return repository.addNew(restaurant);
	}

	public void update(Restaurant user) {
		repository.update(user);
	}
}
