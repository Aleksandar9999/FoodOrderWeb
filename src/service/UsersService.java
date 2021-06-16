package service;

import java.util.ArrayList;
import java.util.List;

import beans.User;
import repository.users.UsersRepository;

public class UsersService {

	private UsersRepository repository;

	public UsersService() {
		this.repository = new UsersRepository();
	}

	public User addNew(User user) {
		return this.repository.addNew(user);
	}

	public ArrayList<User> getAll() {
		return this.repository.getAll();
	}

	public User login(User user) {
		return this.repository.login(user);
	}

	public void update(String username,User user) {
		this.repository.update(username,user);
	}

	public User getByUsername(String id) {
		return this.repository.getByUsername(id);
	}
	public List<User> getFreeManagers(){
		return this.repository.getFreeManagers();
	}
}
