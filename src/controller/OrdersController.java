package controller;

import java.util.List;

import com.google.gson.Gson;

import beans.*;
import service.OrderService;
import spark.Request;
import spark.Response;
import spark.Route;

public class OrdersController {
	private static OrderService ordersService=new OrderService();
	private static Gson gson=new Gson();
	
	public static Route handleGetOrdersByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        List<Order> orders=ordersService.getAllByRestaurant(id);
        for (Order order : orders) {
            System.out.println(order.getId());
        }
        return gson.toJson(orders);
    };
    public static Route handleGetBuyersByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        List<User> orders=ordersService.getAllBuyersByRestaruantId(id);
        return gson.toJson(orders);
    };
}
