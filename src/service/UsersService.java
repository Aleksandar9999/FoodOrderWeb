package service;

import java.util.ArrayList;

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

	public void update(User user) {
		this.repository.update(user);
	}

	public User getById(String id) {
		return this.repository.getByUsername(id);
	}
}
