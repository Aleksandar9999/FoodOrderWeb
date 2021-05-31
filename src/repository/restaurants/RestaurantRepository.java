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
import beans.Restaurant;
import enumerations.RestaurantType;

public class RestaurantRepository {
	private String filePath;
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public RestaurantRepository() {
		this.filePath = "./repo/restaurants.json";
	}
	
	public ArrayList<Restaurant> getAll() {
		return new ArrayList<Restaurant>(readAll().values());
	}
	public Restaurant getById(String id) {
		HashMap<String, Restaurant> restaurants = readAll();
		return restaurants.get(id);
	}
	public Restaurant addNew(Restaurant restaurant) {
		HashMap<String, Restaurant> restaurants = readAll();
		if (restaurants == null)
			restaurants = new HashMap<String, Restaurant>();
		restaurants.put(restaurant.getId(), restaurant);
		saveAll(restaurants);
		return restaurant;
	}

	public void update(Restaurant user) {
		HashMap<String, Restaurant> users = readAll();
		if (users == null)
			users = new HashMap<String, Restaurant>();
		users.put(user.getId(), user);
		saveAll(users);
	}

	private void saveAll(HashMap<String, Restaurant> users) {
		Type type = new TypeToken<HashMap<String, Restaurant>>() {
		}.getType();
		String json = gson.toJson(users, type);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(this.filePath));
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private HashMap<String, Restaurant> readAll() {
		String json = readFromFile();
		Type type = new TypeToken<HashMap<String, Restaurant>>() {
		}.getType();
		HashMap<String, Restaurant> users = gson.fromJson(json, type);
		return users;
	}

	private String readFromFile() {
		StringBuilder resultStringBuilder = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(this.filePath));
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
			br.close();
			return resultStringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
