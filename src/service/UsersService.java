package service;

import java.util.ArrayList;
import java.util.HashMap;

import beans.User;
import exceptions.LoginException;
import repository.UsersRepository;

public class UsersService {

	private UsersRepository repository;
	
	public UsersService() {
		this.repository=new UsersRepository();
	}
	
	public User AddNew(User user) {
		return this.repository.AddNew(user);
	} 
	public ArrayList<User> GetAll() {
		return this.repository.GetAll();
	}

	public User Login(User user) {
		return this.repository.Login(user);
	}

	public void Update(User user) {
		this.repository.Update(user);
	}
	
}
