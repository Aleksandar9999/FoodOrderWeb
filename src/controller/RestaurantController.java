package controller;
import com.google.gson.Gson;

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
        if(id.equals("-1")) {
            Restaurant rs=new Restaurant();
            System.out.println(rs);
            return gson.toJson(new Restaurant());
        }
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
    }
