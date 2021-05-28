package beans;

import java.util.Date;

import enumerations.Role;

public class User {
	private String username;
	private String password;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private Role userRole;

	public User() {
	}
	
	public User(Role userRole) {
		this.userRole = userRole;
	}

	public User(String username, String password, String name, String surname, Date dateOfBirth, Role userRole) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.userRole = userRole;
	}

	public User(User user) {
		// TODO Auto-generated constructor stub
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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
