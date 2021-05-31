package controller;

import com.google.gson.Gson;

import beans.Restaurant;
import beans.User;
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
        Restaurant restaurant=restaurantService.getById(id);
        return gson.toJson(restaurant);
    };
}
