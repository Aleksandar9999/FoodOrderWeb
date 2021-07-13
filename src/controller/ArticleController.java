package controller;

import com.google.gson.Gson;

import beans.Article;
import service.ArticleService;
import service.RestaurantService;
import spark.Request;
import spark.*;
import spark.Route;
import exceptions.*;

public class ArticleController {
    private static ArticleService articleService = new ArticleService();
    private static Gson gson = new Gson();

    public static Route handleGetArticlesInCartByRestaurant = (Request request, Response response) -> {
        response.type("application/json");
        String idRestaurant = request.params("id");
        System.out.println(gson.toJson(articleService.getAllArticlesForCartByRestaurantId(idRestaurant)));
        return gson.toJson(articleService.getAllArticlesForCartByRestaurantId(idRestaurant));
    };

    public static Route handleAddNewArticle = (Request request, Response response) -> {
        response.type("application/json");

        Article article = gson.fromJson(request.body(), Article.class);
        String idRestaurant = request.params("id");

        RestaurantService restaurantService = new RestaurantService();
        article.setRestaurant(restaurantService.getById(idRestaurant));

        articleService.addNew(article);
        return gson.toJson(article);
    };

    public static Route handleUpdateArticle = (Request request, Response response) -> {
        response.type("application/json");
        try {
            Article article = gson.fromJson(request.body(), Article.class);
            articleService.update(article);
            return gson.toJson(article);
        } catch (ArticleExistException e) {
            response.status(401);
            return e.getMessage();
        }

    };

}
