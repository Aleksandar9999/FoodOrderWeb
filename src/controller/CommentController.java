package controller;

import com.google.gson.Gson;

import service.CommentService;
import spark.Request;
import spark.Response;
import spark.Route;

public class CommentController {
	private static CommentService commentService=new CommentService();
	private static Gson gson=new Gson();
	
	public static Route handleGetCommentsByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String id = request.params("id");
        return gson.toJson(commentService.getApprovedCommentsForRestaurant(id));
    };
}
