package controller;

import java.util.UUID;

import com.google.gson.Gson;

import beans.Article;
import beans.Restaurant;
import service.RestaurantService;
import spark.Request;
import spark.Response;
import spark.Route;

public class RestaurantController {

	private static RestaurantService restaurantService=new RestaurantService();
	private static Gson gson=new Gson();
	
	public static Route handleGetRestaurantById = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        System.out.println("ID RESTORANA"+id);
        Restaurant restaurant=restaurantService.getById(id);
        System.out.println(restaurant.getName());
        return gson.toJson(restaurant);
    };

    public static Route handleAddNewRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String body=request.body();
        Restaurant restaurant=gson.fromJson(body, Restaurant.class);
        restaurantService.addNew(restaurant);
        return gson.toJson(restaurant);
    };

    public static Route handleUpdateRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String body=request.body();
        Restaurant restaurant=gson.fromJson(body, Restaurant.class);
        restaurantService.update(restaurant);
        return gson.toJson(restaurant);
    };

    public static Route handleGetAllRestaurantsSorted = (Request request, Response response) -> {
        response.type("application/json");
        return gson.toJson(restaurantService.getAllRestaurantsSorted());
    };
    public static Route handleAddNewArticle = (Request request, Response response) -> {
        response.type("application/json");
        Article article = gson.fromJson(request.body(), Article.class);
        article.setId(UUID.randomUUID().toString());
        String idRestaurant = request.params("id");
        Restaurant restaurant=restaurantService.getById(idRestaurant);
        restaurant.addArticle(article);
        restaurantService.update(restaurant);
        return gson.toJson(restaurant);
    };
    public static Route handleUpdateArticle = (Request request, Response response) -> {
        response.type("application/json");
        Article article = gson.fromJson(request.body(), Article.class);
        String idRestaurant = request.params("id");
        String nameArticle=request.params("name");
        Restaurant restaurant=restaurantService.getById(idRestaurant);
        restaurant.updateArticle(nameArticle, article);
        restaurantService.update(restaurant);
        return gson.toJson(restaurant);
    };
}
