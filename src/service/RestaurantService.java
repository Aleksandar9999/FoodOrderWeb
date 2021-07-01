package service;
import java.util.List;

import DAO.RestaurantDAO;
import beans.ArticleInCart;
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
}
