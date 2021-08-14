package controller;

import com.google.gson.Gson;

import beans.Manager;
import beans.Restaurant;
import enumerations.UserRole;
import exceptions.AccessException;
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
                UserController.validateLoggedinManager(request, id);
                Restaurant restaurant = restaurantService.getById(id);
                return gson.toJson(restaurant);
            }
        } catch (UnauthorizedUserException er) {
            response.status(401);
            return (er.getMessage());
        } catch (AccessException ex) {
            response.status(403);
            return (ex.getMessage());
        }
    };
    public static Route handleAddNewRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        System.out.println(request.body());
        try {
            Restaurant restaurant = gson.fromJson(request.body(), Restaurant.class);
            validateAdministrator(request);
            restaurantService.addNew(restaurant);
            return gson.toJson(restaurant);
        } catch (UnauthorizedUserException er) {
            response.status(401);
            return (er.getMessage());
        } catch (AccessException ex) {
            response.status(403);
            return (ex.getMessage());
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
        if (UserController.getLoggedingUsername(request) == null)
            throw new UnauthorizedUserException("Please login.");
        if (!UserController.getLoggedingUser(request).getUserRole().equals(UserRole.Administrator))
            throw new AccessException("Loggedin user is not administrator.");
    }

}
