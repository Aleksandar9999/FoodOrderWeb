package beans;

import java.util.ArrayList;
import java.util.List;

import enumerations.Role;

public class Buyer extends User {
	private double pointsCollected;
	private List<Order> orders;
	private Cart cart;

	public Buyer() {
		super(Role.Buyer);
		pointsCollected=0;
		orders=new ArrayList<Order>();
	}

	public Buyer(User user) {
		super(Role.Buyer);
		this.setDateOfBirth(user.getDateOfBirth());
		this.setName(user.getName());
		this.setPassword(user.getPassword());
		this.setSurname(user.getSurname());
		this.setUsername(user.getUsername());
		pointsCollected=0;
		orders=new ArrayList<Order>();
	}

}
