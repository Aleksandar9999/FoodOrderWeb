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
        return gson.toJson(articleService.getAllArticlesForCartByRestaurantId(idRestaurant));
    };

    public static Route handleAddNewArticle = (Request request, Response response) -> {
        response.type("application/json");

        Article article = gson.fromJson(request.body(), Article.class);
        System.out.println(request.body());
        System.out.println(article.getArticleType());
        String idRestaurant = request.params("id");
        try {
            RestaurantService restaurantService = new RestaurantService();
            restaurantService.getById(idRestaurant).addArticle(article);
            article.setRestaurant(restaurantService.getById(idRestaurant));
            articleService.addNew(article);
            return gson.toJson(article);
        } catch (ArticleExistException ex) {
            response.status(400);
            return (ex.getMessage());
        }
    };

    public static Route handleGetArticlesByRestaurantId = (Request request, Response response) -> {
        response.type("application/json");
        String idRes=request.params("id");
        return gson.toJson(articleService.getAllArticlesByRestaurantId(idRes));
    };

    public static Route handleUpdateArticle = (Request request, Response response) -> {
        response.type("application/json");
        try {
            Article article = gson.fromJson(request.body(), Article.class);
            RestaurantService restaurantService = new RestaurantService();
            article.setRestaurant(restaurantService.getById(request.params("idRest")));
            articleService.update(article);
            return gson.toJson(article);
        } catch (ArticleExistException e) {
            response.status(401);
            return e.getMessage();
        }
    };

}
