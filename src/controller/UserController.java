package controller;

import exceptions.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Administrator;
import beans.Buyer;
import beans.Deliverer;
import beans.Manager;
import beans.Restaurant;
import beans.User;
import enumerations.UserRole;
import service.RestaurantService;
import service.UsersService;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class UserController {

    static Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    private static UsersService usersService = new UsersService();

    public static Route handleLoginPost = (Request request, Response response) -> {
        response.type("application/json");
        User user = g.fromJson(request.body(), User.class);
        try {
            user = usersService.login(user);
            setLoggedinUser(request, user);
            return g.toJson(user);
        } catch (UserDataException ex) {
            response.status(401);
            return ex.getMessage();
        }
    };
    public static Route handleRegisterBuyer = (Request request, Response response) -> {
        response.type("application/json");
        Buyer user = g.fromJson(request.body(), Buyer.class);
        try {
            user.setUserRole(UserRole.Buyer);
            user = (Buyer) usersService.addNew(user);
            return g.toJson(user);
        } catch (RegistrationException ex) {
            response.body(ex.getMessage());
            return response;
        }
    };
    public static Route handleRegisterManager = (Request request, Response response) -> {
        response.type("application/json");
        Manager user = g.fromJson(request.body(), Manager.class);
        try {
            user = (Manager) usersService.addNew(user);
            response.status(201);
            return g.toJson(user);
        } catch (RegistrationException ex) {

            response.body(ex.getMessage());
            System.out.print(response.body());
            return response;
        }
    };
    public static Route handleGetAllUsers = (Request request, Response response) -> {
        response.type("application/json");
        try {
            validateAdministrator(request);
            return g.toJson(usersService.getAll());
        }catch (AccessException er){
            response.status(401);
            return (er.getMessage());
        } 
    };
    public static Route handleGetAllManagers = (Request request, Response response) -> {
        response.type("application/json");
        try {
            String restaurantId = request.queryParams("restaurantId");
            if (restaurantId.equals("-1")) {
                return g.toJson(usersService.getFreeManagers());
            }
            return null;
        } catch (Exception e) {
            response.status(401);
            return (e.getMessage());
        }
    };
    public static Route handleUpdateUser = (Request request, Response response) -> {
        response.type("application/json");
        String username = request.params("id");
        User user = g.fromJson(request.body(), User.class);
        if (user.getUserRole().equals(UserRole.Buyer)) {
            Buyer buyer = g.fromJson(request.body(), Buyer.class);
            usersService.update(username, buyer);
            response.cookie("user", user.getUsername());
            return g.toJson(user);
        } else if (user.getUserRole().equals(UserRole.Manager)) {
            Manager buyer = g.fromJson(request.body(), Manager.class);
            usersService.update(username, buyer);
            response.cookie("user", user.getUsername());
            return g.toJson(user);
        } else if (user.getUserRole().equals(UserRole.Deliverer)) {
            Deliverer buyer = g.fromJson(request.body(), Deliverer.class);
            usersService.update(username, buyer);
            response.cookie("user", user.getUsername());
            return g.toJson(user);
        } else if (user.getUserRole().equals(UserRole.Administrator)) {
            Administrator buyer = g.fromJson(request.body(), Administrator.class);
            usersService.update(username, buyer);
            response.cookie("user", user.getUsername());
            return g.toJson(user);
        }
        response.status(404);
        return null;
    };

    public static Route handleUpdateManager = (Request request, Response response) -> {
        response.type("application/json");
        String username = request.params("username");
        Manager user = (Manager) usersService.getByUsername(username);
        RestaurantService restaurantService=new RestaurantService();
        user.setRestaurant(restaurantService.getById(request.params("id")));
        usersService.update(username, user);
        return g.toJson(user);
    };

    public static Route handleGetUser = (Request request, Response response) -> {
        response.type("application/json");
        String username = request.params("id");
        if (username.equals("me")) {
            return g.toJson(usersService.getByUsername(getLoggedingUsername(request)));
        } else {
            User user = usersService.getByUsername(username);
            return g.toJson(user);
        }
    };

    public static Route handleGetFreeManagers = (Request request, Response response) -> {
        return g.toJson(usersService.getFreeManagers());
    };

    private static void setLoggedinUser(Request request, User user) {
        Session ss = request.session(true);
        ss.attribute("user", user.getUsername());
    }

    private static void validateAdministrator(Request request) {
        if(!getLoggedingUser(request).getUserRole().equals(UserRole.Administrator)) throw new AccessException("Loggedin user is not administrator");
    }

    public static String getLoggedingUsername(Request request) {
        Session ss = request.session(true);
        return ss.attribute("user");
    }
    public static User getLoggedingUser(Request request) {
        return usersService.getByUsername(getLoggedingUsername(request));
    }
}
