package controller;

import java.util.List;

import com.google.gson.Gson;

import beans.*;
import exceptions.UnauthorizedUserException;
import enumerations.UserRole;
import exceptions.AccessException;
import service.OrderService;
import spark.Request;
import spark.Response;
import spark.Route;

public class OrdersController {
    private static OrderService ordersService = new OrderService();
    private static Gson gson = new Gson();

    public static Route handleGetOrdersByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        try {
            UserController.validateLoggedinManager(request, id);
            List<Order> orders = ordersService.getAllByRestaurant(id);
            return gson.toJson(orders);
        } catch (UnauthorizedUserException er) {
            response.status(401);
            return (er.getMessage());
        }

    };

    public static Route handleGetBuyersByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        try {
            UserController.validateLoggedinManager(request, id);
            List<User> orders = ordersService.getAllBuyersByRestaruantId(id);
            return gson.toJson(orders);
        } catch (UnauthorizedUserException er) {
            response.status(401);
            return (er.getMessage());
        } catch (AccessException ex) {
            response.status(403);
            return (ex.getMessage());
        }
    };

    public static Route handleCreateOrder = (Request request, Response response) -> {
        response.type("application/json");
        Cart cart = gson.fromJson(request.body(), Cart.class);
        ordersService.addNew(new Order(cart));
        return ("OK");
    };

    public static Route handleGetOrdersByBuyer = (Request request, Response response) -> {
        response.type("application/json");
        String username = request.params("id");
        try {
            if (username.equals("me")) {
                User user = UserController.getLoggedingUser(request);
                switch (user.getUserRole()) {
                    case Buyer: {
                        UserController.validateLoggedinBuyer(request);
                        return gson.toJson(
                                ordersService.getAllByBuyerUsername(UserController.getLoggedingUsername(request)));
                    }
                    case Deliverer:{

                    }
                    default:
                        break;
                }

            }
            return gson.toJson(ordersService.getAllByBuyerUsername(username));
        } catch (RuntimeException e) {
            response.status(401);
            return e.getMessage();
        }
    };
    public static Route handleUpdateOrder = (Request request, Response response) -> {
        response.type("application/json");
        Order order = gson.fromJson(request.body(), Order.class);
        try {
            UserController.validateLoggedinManager(request, order.getRestaurantId());
            ordersService.update(order); 
            return ("OK");
        } catch (RuntimeException e) {
            response.status(401);
            return (e.getMessage());
        }
    };

}
