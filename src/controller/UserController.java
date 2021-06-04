package controller;
import exceptions.*;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Buyer;
import beans.User;
import service.UsersService;
import spark.Request;
import spark.Response;
import spark.Route;
public class UserController {

	static Gson g = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd")
			.create();
	private static UsersService usersService=new UsersService();
	
	public static Route handleLoginPost = (Request request, Response response) -> {
        response.type("application/json");
        User user=g.fromJson(request.body(), User.class);
        try {
        	user=usersService.login(user);
        	return g.toJson(user);
        }catch(LoginException ex) {
        	response.status(401);
        	return g.toJson(null);
        }
    };
	
    public static Route handleRegisterPost = (Request request, Response response) -> {
        response.type("application/json");
        User user=g.fromJson(request.body(), User.class);
        Buyer buyer=new Buyer(user);
        try {
			user=usersService.addNew(buyer); 
			response.status(201);
			return g.toJson(user);
		} catch (RegistrationException ex) {
	        
			response.body(ex.getMessage());
			System.out.print(response.body());
			return response;
		}
        //TODO: HandlingExceptions
       
    };
    public static Route handleGetAllUsers = (Request request, Response response) -> {
        response.type("application/json");
        ArrayList<User> users=usersService.getAll();
        return g.toJson(users);
    };
    public static Route handleUpdateUser = (Request request, Response response) -> {
        throw new Exception();
        /*response.type("application/json");
        String username=request.queryParams("id");
        //TODO: Provjera koja je uloga korisnika
        User user=g.fromJson(request.body(), User.class);
        usersService.update(user);
        return g.toJson(user);*/
    };

    
}
