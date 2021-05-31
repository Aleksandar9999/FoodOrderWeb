package service;

import java.util.ArrayList;
import java.util.HashMap;

import beans.Restaurant;
import generic.GenericService;
import repository.restaurants.RestaurantRepository;

public class RestaurantService extends GenericService<Restaurant> {

	private RestaurantRepository repository;
	
	public RestaurantService() {
		super(new RestaurantRepository());
		
	}
}
