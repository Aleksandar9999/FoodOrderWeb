package beans;

import java.time.LocalDate;
import enumerations.UserRole;

public class User {
	private String username;
	private String password;
	private String name;
	private String surname;
	private LocalDate dateOfBirth;
	private UserRole userRole;

	public User() {}
	
	public User(UserRole userRole) {
		this.userRole = userRole;
	}

	public User(String username, String password, String name, String surname,UserRole userRole) {
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
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
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return ((User)obj).getUsername().equals(username);
	}
	public void updateUserInfo(User newUserInfo) {
		setUsername(newUserInfo.getUsername());
		setName(newUserInfo.getName());
		setSurname(newUserInfo.getSurname());
		if(!newUserInfo.getPassword().isEmpty() && !newUserInfo.getPassword().equals(getPassword())){
			setPassword(newUserInfo.getPassword());
		}
	}

}
