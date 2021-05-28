package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import beans.Buyer;
import beans.User;
import exceptions.LoginException;
import exceptions.RegistrationException;

public class UsersRepository {

	private String filePath;

	public UsersRepository() {
		this.filePath = "./repo/users.json";
	}

	private void SaveAll(HashMap<String, User> users) {
		Gson gson = new Gson();
		String json = gson.toJson(users);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(this.filePath));
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private HashMap<String, User> ReadAll() {
		String json = ReadFromFile();
		Gson gson = new Gson();
		Type type = new TypeToken<HashMap<String, User>>() {
		}.getType();
		HashMap<String, User> users = gson.fromJson(json, type);
		return users;
	}

	private String ReadFromFile() {
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

	public ArrayList<User> GetAll() {
		return new ArrayList<User>(ReadAll().values());
	}

	public User Login(User user) {
		HashMap<String, User> users = ReadAll();
		if (users.containsKey(user.getUsername())) {
			if (users.get(user.getUsername()).getPassword().equals(user.getPassword()))
				return users.get(user.getUsername());
			throw new LoginException("Neispravna lozinka");
		} else
			throw new LoginException("Neispravno korisnicko ime");

	}

	public User AddNew(User user) {
		HashMap<String, User> users = ReadAll();
		if (users == null)
			users = new HashMap<String, User>();
		if(users.containsKey(user.getUsername())) {
			throw new RegistrationException("Korisnicko ime je zauzeto.");
		}
		users.put(user.getUsername(), user);
		SaveAll(users);
		return user;
	}

	public void Update(User user) {
		HashMap<String, User> users = ReadAll();
		if (users == null)
			users = new HashMap<String, User>();
		users.put(user.getUsername(), user);
		SaveAll(users);
	}
}
