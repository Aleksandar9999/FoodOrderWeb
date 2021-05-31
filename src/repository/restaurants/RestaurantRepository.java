package repository.restaurants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import beans.Address;
import beans.Location;
import beans.Order;
import beans.Restaurant;
import enumerations.RestaurantType;
import generic.GenericRepository;

public class RestaurantRepository extends GenericRepository<Restaurant> {
	public RestaurantRepository() {
		super("./repo/restaurants.json");
	}
	//TODO Ukoliko se nalazi u GenericRepo kao value u HashMap dobijam StringMap
	@Override
	public HashMap<String, Restaurant> readAll() {
		String json = readFromFile();
		Type type = new TypeToken<HashMap<String, Restaurant>>() {
		}.getType();
		HashMap<String, Restaurant> users = gson.fromJson(json, type);
		return users;
	}
}
