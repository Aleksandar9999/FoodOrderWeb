package beans;

import java.util.Date;

import enumerations.Role;

public class User {
	private String userName;
	private String password;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private Role userRole;

	public User() {
	}

	public User(String userName, String password, String name, String surname, Date dateOfBirth, Role userRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.userRole = userRole;
	}

}
