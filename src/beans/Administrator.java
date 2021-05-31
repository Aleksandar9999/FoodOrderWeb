package beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.crypto.Data;

import enumerations.Role;
import service.UsersService;

public class Administrator extends User {
	public Administrator(String username,String password, String name, String surname) {
		super(username,password,name,surname, Role.Administrator);
	}
}
