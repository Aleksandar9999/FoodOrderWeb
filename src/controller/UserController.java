package controller;
import exceptions.*;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.util.ArrayList;

import org.eclipse.jetty.util.log.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Administrator;
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
        String body = request.body();
        User user=g.fromJson(request.body(), User.class);
        user=usersService.login(user);
        return g.toJson(user);
        
    };
	
    public static Route handleRegisterPost = (Request request, Response response) -> {
        response.type("application/json");
        String body = request.body();
        User user=g.fromJson(request.body(), User.class);
        Buyer buyer=new Buyer(user);
        try {
			user=usersService.addNew(buyer); 
			return g.toJson(user);
		} catch (RegistrationException ex) {
	        response.status(401);
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
    
    
}
