package controller;

import java.util.List;

import com.google.gson.Gson;

import beans.*;
import exceptions.UnauthorizedUserException;
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
            validateUser(request, id);
            List<Order> orders = ordersService.getAllByRestaurant(id);
            return gson.toJson(orders);
        } catch (UnauthorizedUserException er) {
            response.status(401);
            return (er.getMessage());
        }

    };

    private static void validateUser(Request request, String restaurantId) {
        Manager manager = (Manager) UserController.getLoggedingUser(request);
        if (!manager.getRestaurant().getId().equals(restaurantId))
            throw new UnauthorizedUserException();
    }

    public static Route handleGetBuyersByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        try {
            validateUser(request, id);
            List<User> orders = ordersService.getAllBuyersByRestaruantId(id);
            return gson.toJson(orders);
        } catch (UnauthorizedUserException er) {
            response.status(401);
            return (er.getMessage());
        }

    };

    public static Route handleCreateOrder = (Request request, Response response) -> {
        response.type("application/json");
        Cart cart = gson.fromJson(request.body(), Cart.class);
        ordersService.addNew(new Order(cart));
        return ("OK");
    };

}
