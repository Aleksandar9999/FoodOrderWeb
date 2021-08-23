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
        put("/rest/users/:id", UserController.handleUpdateUserInfo);
        post("/rest/cart/articles",CartController.addArticleInCart);
        get("/rest/cart",CartController.getCart);
        get("/rest/cart/total",CartController.getTotal);
        
        put("/rest/restaurants/:id/managers/:username",UserController.handleUpdateManager);
        
        get("/rest/restaurants",RestaurantController.handleGetAllRestaurantsSorted);
        get("/rest/restaurants/:id",RestaurantController.handleGetRestaurantById);

        get("/rest/restaurants/:id/settings",RestaurantController.handleGetRestaurantByIdSettings);
        
        post("/rest/restaurants",RestaurantController.handleAddNewRestaurant);
        put("/rest/restaurants/:id",RestaurantController.handleUpdateRestaurant);
        
        get("/rest/restaurants/:id/articlesincart",ArticleController.handleGetArticlesInCartByRestaurant);
        get("/rest/restaurants/:id/articles",ArticleController.handleGetArticlesByRestaurantId);
        post("/rest/restaurants/:id/articles",ArticleController.handleAddNewArticle);
        put("/rest/restaurants/:idRest/articles/:idArticle",ArticleController.handleUpdateArticle);
        
        get("/rest/restaurants/:id/orders",DeliverRequestController.handleGetRequestsByRestaurant);
        get("/rest/restaurants/:id/buyers",OrdersController.handleGetBuyersByRestaurant);
        
        post("rest/orders",OrdersController.handleCreateOrder);
        put("/rest/orders/:id",OrdersController.handleUpdateOrder);
        
        get("/rest/orders/deliverer/:id",OrdersController.handleGetDelivererOrders);
        get("/rest/orders/buyer/:id",OrdersController.handleGetOrdersByBuyer);

        get("/rest/restaurants/:id/comments",CommentController.handleGetCommentsByRestaurant);
        get("/rest/restaurants/:id/comments/settings",CommentController.handleGetCommentsByRestaurantSettings);
        post("/rest/comments",CommentController.handleCreateComment);
        get("/rest/user/:username/comments",CommentController.handleGetCommentsByUser);
        put("/rest/comments/:id",CommentController.handleUpdateComment);
        
        get("/rest/orders/users/suspicious",OrdersController.handleGetSuspiciousUsers);

        post("/rest/deliver-request",DeliverRequestController.handleAddRequest);
        put("/rest/deliver-request/:id",DeliverRequestController.handleUpdateRequest);
        
    }
}
