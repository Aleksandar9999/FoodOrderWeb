package beans;

import java.time.LocalDateTime;

import enumerations.UserRole;

public class User {
	private String username;
	private String password;
	private String name;
	private String surname;
	private LocalDateTime dateOfBirth;
	private UserRole userRole;
	private String dateOfBirthString;
	private boolean valid;
	public User() {}
	
	public User(UserRole userRole) {
		this.userRole = userRole;
	}
	public User(User user){
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.name=user.getName();
		this.surname=user.getSurname();
		this.dateOfBirth=user.getDateOfBirth();
		this.userRole=user.getUserRole();
		this.valid=user.isValid();
	}
	public User copy() {
        return new User(this);
    }
	
	
	public User(String username, String password, String name, String surname,UserRole userRole) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.userRole = userRole;
	}

	public String getDateOfBirthString() {
		return dateOfBirthString;
	}

	public void setDateOfBirthString(String dateOfBirthString) {
		this.dateOfBirthString = dateOfBirthString;
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

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
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

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
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
		setValid(newUserInfo.isValid());
		if(!newUserInfo.getPassword().isEmpty() && !newUserInfo.getPassword().equals(getPassword())){
			setPassword(newUserInfo.getPassword());
		}
	}

}
