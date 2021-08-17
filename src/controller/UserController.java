package controller;

import exceptions.*;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Administrator;
import beans.Buyer;
import beans.Deliverer;
import beans.Manager;
import beans.Order;
import beans.User;
import enumerations.OrderStatus;
import enumerations.UserRole;
import service.RestaurantService;
import service.UsersService;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class UserController {

    static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    private static UsersService usersService = new UsersService();

    public static Route handleLoginPost = (Request request, Response response) -> {
        response.type("application/json");
        User user = gson.fromJson(request.body(), User.class);
        try {
            user = usersService.login(user);
            setLoggedinUsername(request, user);
            
            return gson.toJson(user);
        } catch (UserDataException ex) {
            response.status(401);
            return ex.getMessage();
        }
    };
    public static Route handleRegisterBuyer = (Request request, Response response) -> {
        response.type("application/json");
        Buyer user = gson.fromJson(request.body(), Buyer.class);
        try {
            user.setUserRole(UserRole.Buyer);
            user = (Buyer) usersService.addNew(user);
            return gson.toJson(user);
        } catch (RegistrationException ex) {
            response.body(ex.getMessage());
            return response;
        }
    };
    public static Route handleRegisterManager = (Request request, Response response) -> {
        response.type("application/json");
        Manager user = gson.fromJson(request.body(), Manager.class);
        try {
            user = (Manager) usersService.addNew(user);
            response.status(201);
           
            return gson.toJson(user);
        } catch (RegistrationException ex) {

            response.body(ex.getMessage());
            return response;
        }
    };
    public static Route handleGetAllUsers = (Request request, Response response) -> {
        response.type("application/json");
        try {
            validateAdministrator(request);
            List<User> users = usersService.getAll();
            
            return gson.toJson(users);
        } catch (AccessException er) {
            response.status(401);
            return (er.getMessage());
        }
    };
    public static Route handleGetAllManagers = (Request request, Response response) -> {
        response.type("application/json");
        try {
            String restaurantId = request.queryParams("restaurantId");
            if (restaurantId.equals("-1")) {
                return gson.toJson(usersService.getFreeManagers());
            }
            return null;
        } catch (Exception e) {
            response.status(401);
            return (e.getMessage());
        }
    };
    public static Route handleUpdateUserInfo = (Request request, Response response) -> {
        response.type("application/json");
        String existUsername = request.params("id");
        User newUserInfo = gson.fromJson(request.body(), User.class);
        User existUser = usersService.getByUsername(existUsername);
        existUser.updateUserInfo(newUserInfo);
        usersService.update(existUsername, existUser);
        if (existUsername.equals(getLoggedingUsername(request)))
            setLoggedinUsername(request, newUserInfo);
        
        return gson.toJson(existUser);
    };

    public static Route handleUpdateManager = (Request request, Response response) -> {
        response.type("application/json");
        String username = request.params("username");
        Manager user = (Manager) usersService.getByUsername(username);
        RestaurantService restaurantService = new RestaurantService();
        user.setRestaurant(restaurantService.getById(request.params("id")));
        usersService.update(username, user);
        return gson.toJson(user);
    };

    public static Route handleGetUser = (Request request, Response response) -> {
        response.type("application/json");
        String username = request.params("id");
        User user;
        if (username.equals("me"))
            user = usersService.getByUsername(getLoggedingUsername(request));
        else
            user = usersService.getByUsername(username);
        
        return gson.toJson(user);
    };

    public static Route handleGetFreeManagers = (Request request, Response response) -> {
        return gson.toJson(usersService.getFreeManagers());
    };

    private static void setLoggedinUsername(Request request, User user) {
        Session ss = request.session(true);
        ss.attribute("user", user.getUsername());
    }

    private static void validateAdministrator(Request request) {
        if (!getLoggedingUser(request).getUserRole().equals(UserRole.Administrator))
            throw new AccessException("Loggedin user is not administrator");
    }

    public static String getLoggedingUsername(Request request) {
        Session ss = request.session(true);
        return ss.attribute("user");
    }

    public static User getLoggedingUser(Request request) {
        return usersService.getByUsername(getLoggedingUsername(request));
    }

    private static void validateLoggedinUserExist(Request request) {
        if (getLoggedingUsername(request) == null)
            throw new UnauthorizedUserException("Please login.");
    }
    private static void validateLoggedinUserByRole(Request request, UserRole role) {
        validateLoggedinUserExist(request);
        if (!getLoggedingUser(request).getUserRole().equals(role))
            throw new AccessException("Loggedin user is not" + role.toString().toLowerCase());
    }

    public static void validateLoggedinManager(Request request, String restaurantId) {
        validateLoggedinUserByRole(request, UserRole.Manager);
        if (!((Manager) getLoggedingUser(request)).getRestaurant().getId().equals(restaurantId))
            throw new AccessException("Loggedin user is not manager of restaurant.");
    }

    public static void validateLoggedinBuyer(Request request) {
        validateLoggedinUserByRole(request, UserRole.Buyer);
    }

    public static void validateLoggedinDeliverer(Request request) {
        validateLoggedinUserByRole(request, UserRole.Deliverer);
    }
}
