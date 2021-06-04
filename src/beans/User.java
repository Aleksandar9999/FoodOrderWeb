package beans;

import java.time.LocalDate;
import enumerations.Role;

public class User {
	private String username;
	private String password;
	private String name;
	private String surname;
	private LocalDate dateOfBirth;
	private Role userRole;

	public User() {
	}
	
	public User(Role userRole) {
		this.userRole = userRole;
	}

	public User(String username, String password, String name, String surname,Role userRole) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.userRole = userRole;
	}

	public User(User user) {
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return ((User)obj).getUsername().equals(username);
	}
	

}
