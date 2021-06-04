package beans;
import enumerations.Role;

public class Administrator extends User {
	public Administrator(String username,String password, String name, String surname) {
		super(username,password,name,surname, Role.Administrator);
	}
}
