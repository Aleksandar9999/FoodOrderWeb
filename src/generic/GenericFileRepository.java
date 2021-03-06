package generic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import beans.Entity;

public abstract class GenericFileRepository<T extends Entity> {
    private String filePath;
	protected Gson gson;
	private Type type = new TypeToken<HashMap<String, T>>() {}.getType();
	public GenericFileRepository(String filepath) {
		this.filePath = filepath;
		this.gson= new GsonBuilder().setPrettyPrinting().create();
	}
	
	public abstract HashMap<String, T> readAll();

	public ArrayList<T> getAll() {
		return new ArrayList<T>(readAll().values());
	}
	public T getById(String id) {
		HashMap<String, T> restaurants = readAll();
		return restaurants.get(id);
	}
	public T addNew(T restaurant) {
		HashMap<String, T> restaurants = readAll();
		if (restaurants == null)
			restaurants = new HashMap<String, T>();
		restaurants.put(restaurant.getId(), restaurant);
		saveAll(restaurants);
		return restaurant;
	}

	public void update(T user) {
		HashMap<String, T> users = readAll();
		if (users == null)
			users = new HashMap<String, T>();
		users.put(user.getId(), user);
		saveAll(users);
	}

	public void saveAll(HashMap<String, T> data) {
		String json = gson.toJson(data, type);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(this.filePath));
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected String readFromFile() {
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
