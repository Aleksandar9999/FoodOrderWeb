package rest;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;

import beans.Administrator;
import controller.RestaurantController;
import controller.UserController;
import service.UsersService;
import spark.Route;
public class SparkFoodOrderMain {
    public static void main(String[] args) throws Exception{
        port(8080);
        staticFiles.externalLocation(new File("./static").getCanonicalPath()); 
		
        post("/rest/user/login", UserController.handleLoginPost);
        
        post("/rest/user/registration", UserController.handleRegisterPost);
        
        get("/rest/user", UserController.handleGetAllUsers);
        
        
        get("/rest/restaurant/:id",RestaurantController.handleGetRestaurantById);
        	
    }
}
