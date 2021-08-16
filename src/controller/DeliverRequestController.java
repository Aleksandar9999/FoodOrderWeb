package controller;

import com.google.gson.Gson;

import beans.DeliverRequest;
import beans.Deliverer;
import beans.Order;
import enumerations.OrderStatus;
import enumerations.RequestStatus;
import service.DeliverRequestService;
import service.OrderService;
import spark.*;
import spark.Route;

public class DeliverRequestController {
    private static DeliverRequestService deliverRequestService = new DeliverRequestService();
    private static Gson gson = new Gson();
    public static Route handleGetRequestsByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String idRestaurant = request.params("id");
        return gson.toJson(deliverRequestService.getAllByRestaurantId(idRestaurant));
    };
    public static Route handleAddRequest = (Request request, Response response) -> {
        response.type("application/json");
        Order order = gson.fromJson(request.body(), Order.class);
        try {
            UserController.validateLoggedinDeliverer(request);
            DeliverRequest deliverRequest = new DeliverRequest(order,(Deliverer) UserController.getLoggedingUser(request));
            deliverRequestService.addNew(deliverRequest);
            return gson.toJson(deliverRequest);
        } catch (RuntimeException e) {
            response.status(401);
            return (e.getMessage());
        }
    };
    public static Route handleUpdateRequest = (Request request, Response response) -> {
        response.type("application/json");
        DeliverRequest deliverRequest=gson.fromJson(request.body(), DeliverRequest.class);
        try {
            deliverRequestService.update(deliverRequest);
            return gson.toJson(deliverRequest);
        } catch (RuntimeException e) {
            response.status(401);
            return (e.getMessage());
        }
    };
}
