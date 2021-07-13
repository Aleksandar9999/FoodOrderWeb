package rest;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;
import controller.*;
public class SparkFoodOrderMain {
    public static void main(String[] args) throws Exception{
        port(8080);
        staticFiles.externalLocation(new File("./static").getCanonicalPath()); 
		
        post("/rest/users/login", UserController.handleLoginPost);
        post("/rest/users/buyers", UserController.handleRegisterBuyer);
        post("/rest/users/managers", UserController.handleRegisterManager);
        get("/rest/users", UserController.handleGetAllUsers);
        get("/rest/users/managers", UserController.handleGetAllManagers);
        get("/rest/users/:id", UserController.handleGetUser); 
        put("/rest/users/:id", UserController.handleUpdateUser);

        post("/rest/cart/articles",CartController.addArticleInCart);
        get("/rest/cart",CartController.getCart);
        get("/rest/cart/total",CartController.getTotal);
        
        put("/rest/restaurants/:id/managers/:username",UserController.handleUpdateManager);
        
        get("/rest/restaurants",RestaurantController.handleGetAllRestaurantsSorted);
        get("/rest/restaurants/:id",RestaurantController.handleGetRestaurantById);
        post("/rest/restaurants",RestaurantController.handleAddNewRestaurant);
        put("/rest/restaurants/:id",RestaurantController.handleUpdateRestaurant);
        
       get("/rest/restaurants/:id/articles",ArticleController.handleGetArticlesInCartByRestaurant);
        post("/rest/restaurants/:id/articles",ArticleController.handleAddNewArticle);
        put("/rest/restaurants/:idRest/articles/:idArticle",ArticleController.handleUpdateArticle);
        
        get("/rest/restaurants/:id/orders",OrdersController.handleGetOrdersByRestaurant);
        get("/rest/restaurants/:id/buyers",OrdersController.handleGetBuyersByRestaurant);
        post("rest/orders",OrdersController.handleCreateOrder);
        
        get("/rest/restaurants/:id/comments",CommentController.handleGetCommentsByRestaurant);
      
    }
}
