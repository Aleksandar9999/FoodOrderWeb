package controller;

import java.util.List;

import com.google.gson.Gson;

import beans.*;
import exceptions.UnauthorizedUserException;
import enumerations.OrderStatus;
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
        CartController.deleteCart(request);
        return ("OK");
    };

    public static Route handleGetOrdersByBuyer = (Request request, Response response) -> {
        response.type("application/json");
        String username = request.params("id");
        try {
            if (username.equals("me")) {
                UserController.validateLoggedinBuyer(request);
                return gson.toJson(ordersService.getAllByBuyerUsername(UserController.getLoggedingUsername(request)));
            }
            return gson.toJson(ordersService.getAllByBuyerUsername(username));
        } catch (RuntimeException e) {
            response.status(401);
            return e.getMessage();
        }
    };
    public static Route handleGetDelivererOrders = (Request request, Response response) -> {
        response.type("application/json");
        String username = request.params("id");
        try {
            UserController.validateLoggedinDeliverer(request);
            if (username.equals("me")) {
                return gson.toJson(ordersService.getAllForDeliverer(UserController.getLoggedingUsername(request)));
            }
            return gson.toJson(ordersService.getAllForDeliverer(username));

        } catch (RuntimeException e) {
            response.status(401);
            return e.getMessage();
        }
    };

    public static Route handleGetSuspiciousUsers = (Request request, Response response) -> {
        response.type("application/json");
        return gson.toJson(ordersService.getSuspiciousUsers());
    };

    public static Route handleUpdateOrder = (Request request, Response response) -> {
        response.type("application/json");
        Order order = gson.fromJson(request.body(), Order.class);
        try {
            validateUserByOrder(request, order);
            ordersService.update(order);
            return ("OK");
        } catch (RuntimeException e) {
            response.status(401);
            return (e.getMessage());
        }
    };

    private static void validateUserByOrder(Request request, Order order) {
        try {
            UserController.validateLoggedinManager(request, order.getRestaurantId());
        } catch (RuntimeException e) {
            try {
                UserController.validateLoggedinDeliverer(request);
            } catch (RuntimeException e1) {
                if (order.getOrderStatus().equals(OrderStatus.Canceled)
                        && !order.getBuyerUsername().equals(UserController.getLoggedingUsername(request))) {
                    throw new UnauthorizedUserException("Loggedin user is not authorized to change state of order.");
                }
            }

        }
    }


}
