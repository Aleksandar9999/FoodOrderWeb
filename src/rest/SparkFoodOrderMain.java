package rest;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;

import controller.OrdersController;
import controller.RestaurantController;
import controller.UserController;
public class SparkFoodOrderMain {
    public static void main(String[] args) throws Exception{
        port(8080);
        staticFiles.externalLocation(new File("./static").getCanonicalPath()); 
		
        post("/rest/user/login", UserController.handleLoginPost);
        post("/rest/user/registration", UserController.handleRegisterPost);
        get("/rest/user", UserController.handleGetAllUsers);
        
        get("/rest/restaurant",RestaurantController.handleGetAllRestaurantsSorted);
        get("/rest/restaurant/:id",RestaurantController.handleGetRestaurantById);
        post("/rest/restaurant",RestaurantController.handleAddNewRestaurant);
        post("/rest/restaurant/:id/article",RestaurantController.handleAddNewArticle);
        post("/rest/restaurant/:id/article/:name",RestaurantController.handleUpdateArticle);
        
        get("/rest/restaurant/:id/orders",OrdersController.handleGetOrdersByRestaurant);
        get("/rest/restaurant/:id/buyers",OrdersController.handleGetBuyersByRestaurant);
        
        
        
        	
    }
}
