package repository.users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import beans.Deliverer;
import beans.Manager;
import beans.User;
import enumerations.UserRole;
import exceptions.RegistrationException;
import exceptions.UserDataException;
import repository.orders.OrdersRepository;

public class UsersRepository{

	private String filePath;

	public UsersRepository() {
		this.filePath = "./repo/users.json";
	}

	public ArrayList<User> getAll() {
		return new ArrayList<User>(readAll().values());
	}

	public User login(User user) {
		HashMap<String, User> users = readAll();
		if (users.containsKey(user.getUsername())) {
			if (users.get(user.getUsername()).getPassword().equals(user.getPassword()))
				return users.get(user.getUsername());
			throw new UserDataException("Wrong password.");
		} else
			throw new UserDataException("Wrong username.");
	}
	public User getByUsername(String id){
		return readAll().get(id);
	}
	public User addNew(User user) {
		HashMap<String, User> users = readAll();
		if (users == null)
			users = new HashMap<String, User>();
		if (users.containsKey(user.getUsername())) {
			throw new RegistrationException("Username is already in use.");
		}
		users.put(user.getUsername(), user);
		saveAll(users);
		return user;
	}

	public void update(String username,User user) {
		HashMap<String, User> users = readAll();
		if (users == null)
			users = new HashMap<String, User>();
		users.put(username, user);
		saveAll(users);
	}

	private void saveAll(HashMap<String, User> users) {
		Type type = new TypeToken<HashMap<String, User>>() {
		}.getType();
		Gson gson = new GsonBuilder().registerTypeAdapter(type, new UserSerializer()).setPrettyPrinting().create();
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

	private HashMap<String, User> readAll() {
		String json = readFromFile();
		Type type = new TypeToken<HashMap<String, User>>() {
		}.getType();
		Gson gson = new GsonBuilder().registerTypeAdapter(type, new UsersDeserializer()).create();
		HashMap<String, User> users = gson.fromJson(json, type);
		mergeWithObjects(users);
		return users;
	}

	private void mergeWithObjects(HashMap<String, User> users){
		for (User user : users.values()) {
			if(user.getUserRole().equals(UserRole.Deliverer)){
				mergeWithOrders((Deliverer)user);
			}
		}
	}
	private void mergeWithOrders(Deliverer user) {
		OrdersRepository ordersRepository=new OrdersRepository();
		List<String> orderIds=user.getOrderIds();
		for (String id : orderIds) {
			user.addOrder(ordersRepository.getById(id));
		}
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

	public ArrayList<User> getAllByType(UserRole type){
		ArrayList<User> users=getAll();
		users.removeIf(us->!us.getUserRole().equals(type));
		return users;
	}
	public List<User> getFreeManagers(){
		ArrayList<User> managers=getAllByType(UserRole.Manager);
		managers.removeIf(us->((Manager)us).getRestaurant()!=null);
		return managers;
	}

}
