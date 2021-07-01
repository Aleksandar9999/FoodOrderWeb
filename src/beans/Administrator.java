package beans;
import enumerations.UserRole;

public class Administrator extends User {
	public Administrator(String username,String password, String name, String surname) {
		super(username,password,name,surname, UserRole.Administrator);
	}
}
