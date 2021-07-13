package controller;

import java.util.List;

import com.google.gson.Gson;

import beans.*;
import service.OrderService;
import spark.Request;
import spark.Response;
import spark.Route;
import service.UsersService;
public class OrdersController {
	private static OrderService ordersService=new OrderService();
	private static Gson gson=new Gson();
	
	public static Route handleGetOrdersByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        List<Order> orders=ordersService.getAllByRestaurant(id);
        return gson.toJson(orders);
    };
    public static Route handleGetBuyersByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        List<User> orders=ordersService.getAllBuyersByRestaruantId(id);
        return gson.toJson(orders);
    };

    public static Route handleCreateOrder = (Request request, Response response) -> {
        response.type("application/json");
        Cart cart=gson.fromJson(request.body(), Cart.class);
        Order order=new Order(cart);
        order.setBuyerId(cart.getBuyer().getUsername());
        ordersService.addNew(new Order(cart));
        return ("OK");
    };


}
