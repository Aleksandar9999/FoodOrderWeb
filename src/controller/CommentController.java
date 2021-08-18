package controller;

import com.google.gson.Gson;

import beans.Comment;
import enumerations.RequestStatus;
import enumerations.UserRole;
import beans.Buyer;
import service.CommentService;
import spark.Request;
import spark.Response;
import spark.Route;

public class CommentController {
    private static CommentService commentService = new CommentService();
    private static Gson gson = new Gson();

    public static Route handleGetCommentsByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        return gson.toJson(commentService.getApprovedCommentsByRestaurant(id));
    };
    public static Route handleGetCommentsByRestaurantSettings = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        try {
            UserController.validateLoggedinManager(request, id);
            return gson.toJson(commentService.getAllCommentsByRestaurant(id));
        } catch (RuntimeException e) {
            response.status(401);
            return (e.getMessage());
        }
    };
    public static Route handleCreateComment = (Request request, Response response) -> {
        response.type("application/json");
        Comment comment = gson.fromJson(request.body(), Comment.class);
        comment.setBuyer((Buyer) UserController.getLoggedingUser(request));
        comment.setStatus(RequestStatus.Pending);
        commentService.addNew(comment);
        return gson.toJson(comment);
    };
    public static Route handleUpdateComment = (Request request, Response response) -> {
        response.type("application/json");
        Comment comment = gson.fromJson(request.body(), Comment.class);
        commentService.update(comment);;
        return gson.toJson(comment);
    };
    public static Route handleGetCommentsByUser = (Request request, Response response) -> {
        response.type("application/json");
        String username=request.params("username");
        return gson.toJson(commentService.getCommentsByUser(username));
    };


}
