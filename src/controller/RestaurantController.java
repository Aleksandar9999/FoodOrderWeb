package controller;

import com.google.gson.Gson;

import beans.Manager;
import beans.Restaurant;
import enumerations.UserRole;
import exceptions.UnauthorizedUserException;
import service.RestaurantService;
import spark.Request;
import spark.Response;
import spark.Route;

public class RestaurantController {

    private static RestaurantService restaurantService = new RestaurantService();
    private static Gson gson = new Gson();

    public static Route handleGetRestaurantById = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        Restaurant restaurant = restaurantService.getById(id);
        return gson.toJson(restaurant);

    };
    public static Route handleGetRestaurantByIdSettings = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        try {
            if (id.equals("-1")) {
                validateAdministrator(request);
                return gson.toJson(new Restaurant());
            } else {
                validateManager(request, id);
                Restaurant restaurant = restaurantService.getById(id);
                return gson.toJson(restaurant);
            }
        } catch (UnauthorizedUserException er) {
            response.status(401);
            return (er.getMessage());
        }
    };
    public static Route handleAddNewRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        try {
            Restaurant restaurant = gson.fromJson(request.body(), Restaurant.class);
            validateAdministrator(request);
            restaurantService.addNew(restaurant);
            return gson.toJson(restaurant);
        } catch (UnauthorizedUserException er) {
            response.status(401);
            return (er.getMessage());
        }
    };

    public static Route handleUpdateRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String body = request.body();
        Restaurant restaurant = gson.fromJson(body, Restaurant.class);
        restaurantService.update(restaurant);
        return gson.toJson(restaurant);
    };

    public static Route handleGetAllRestaurantsSorted = (Request request, Response response) -> {
        response.type("application/json");
        return gson.toJson(restaurantService.getAllRestaurantsSorted());
    };

    private static void validateAdministrator(Request request) {
        if (!UserController.getLoggedingUser(request).getUserRole().equals(UserRole.Administrator))
            throw new UnauthorizedUserException("Loggedin user is not administrator.");
    }

    private static void validateManager(Request request, String restaurantId) {
        if (!((Manager) UserController.getLoggedingUser(request)).getRestaurant().getId().equals(restaurantId))
            throw new UnauthorizedUserException("Loggedin user is not manager of restaurant.");
    }

}
