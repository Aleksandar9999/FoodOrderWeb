package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import beans.Restaurant;
import generic.GenericService;
import repository.restaurants.RestaurantRepository;

public class RestaurantService extends GenericService<Restaurant> {
	public RestaurantService() {
		super(new RestaurantRepository());	
	}
	public List<Restaurant> getAllRestaurantsSorted(){
		return ((RestaurantRepository)repository).getAllRestaurantsSorted();
		
	}
}
