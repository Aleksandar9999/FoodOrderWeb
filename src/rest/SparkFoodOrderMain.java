package rest;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
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
		
        post("/rest/users/login", UserController.handleLoginPost);
        post("/rest/users", UserController.handleRegisterPost);
        get("/rest/users", UserController.handleGetAllUsers);
        get("/rest/users/:id", UserController.handleGetUser); 
        put("/rest/users/:id", UserController.handleUpdateUser);

        get("/rest/restaurants",RestaurantController.handleGetAllRestaurantsSorted);
        get("/rest/restaurants/:id",RestaurantController.handleGetRestaurantById);
        post("/rest/restaurants",RestaurantController.handleAddNewRestaurant);
        put("/rest/restaurants/:id",RestaurantController.handleUpdateRestaurant);
        
        post("/rest/restaurants/:id/article",RestaurantController.handleAddNewArticle);
        put("/rest/restaurants/:id/article/:name",RestaurantController.handleUpdateArticle);
        
        get("/rest/restaurants/:id/orders",OrdersController.handleGetOrdersByRestaurant);
        get("/rest/restaurants/:id/buyers",OrdersController.handleGetBuyersByRestaurant);
        
    
    }
}
