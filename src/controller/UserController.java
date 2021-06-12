package controller;
import exceptions.*;
import javaxt.utils.string;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Administrator;
import beans.Buyer;
import beans.Deliverer;
import beans.Manager;
import beans.User;
import enumerations.Role;
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
            response.cookie("user", user.getUsername());
        	return g.toJson(user);
        }catch(LoginException ex) {
        	response.status(401);
        	return ex.getErrorMessage();
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
    };
    public static Route handleGetAllUsers = (Request request, Response response) -> {
        response.type("application/json");
        System.out.println("User"+request.cookie("user"));
        //TODO: provjera da li je admin 
        //if(!request.cookie("user").equals("admin")) return g.toJson(new ArrayList<>());
        ArrayList<User> users=usersService.getAll();
        return g.toJson(users);
    };
    public static Route handleUpdateUser = (Request request, Response response) -> {
        response.type("application/json");
        String username=request.params("id");
        User user=g.fromJson(request.body(), User.class);
        if(user.getUserRole().equals(Role.Buyer)){
           Buyer buyer=g.fromJson(request.body(),Buyer.class);
           usersService.update(username,buyer);
           return g.toJson(user);
        }else if(user.getUserRole().equals(Role.Manager)){
            Manager buyer=g.fromJson(request.body(),Manager.class);
            usersService.update(username,buyer);
            return g.toJson(user);
        }else if(user.getUserRole().equals(Role.Deliverer)){
            Deliverer buyer=g.fromJson(request.body(),Deliverer.class);
            usersService.update(username,buyer);
            return g.toJson(user);
        }
        else if(user.getUserRole().equals(Role.Administrator)){
            Administrator buyer=g.fromJson(request.body(),Administrator.class);
            usersService.update(username,buyer);
            return g.toJson(user);
        }
        response.status(404);
        return null;
    };
    public static Route handleGetUser = (Request request, Response response) -> {
        response.type("application/json");
        String username=request.params("id");
        System.out.println(username);
        User user=usersService.getByUsername(username);
        System.out.println("username"+ user);
        return g.toJson(user);
    };
    private static String GenerateCokie(User user) {
        return user.getUsername();
    }

    
}
