package controller;

import com.google.gson.Gson;

import beans.ArticleInCart;
import beans.Buyer;
import beans.Cart;
import exceptions.CartException;
import service.RestaurantService;
import service.UsersService;
import spark.Request;
import spark.Route;
import spark.Session;

public class CartController {
    private static Gson gson = new Gson();
    public static Route addArticleInCart = (request, response) -> {
        response.type("application/json");
        ArticleInCart articleInCart = gson.fromJson(request.body(), ArticleInCart.class);
        try {
            UserController.validateLoggedinBuyer(request);
            valideteRestaurantStatus(articleInCart.getArticle().getRestaurantId());
            getCart(request).addArticle(articleInCart);
            return ("OK");
        } catch (RuntimeException e) {
            response.status(401);
            return (e.getMessage());
        }
    };

    public static Route getCart = (request, response) -> {
        try {
            UserController.validateLoggedinBuyer(request);
            return gson.toJson(getCart(request));
        } catch (RuntimeException e) {
            response.status(401);
            return (e.getMessage());
        }
    };
    public static Route deleteCartRoute = (request, response) -> {
        try {
            UserController.validateLoggedinBuyer(request);
            CartController.deleteCart(request);
            return ("ok");
        } catch (RuntimeException e) {
            response.status(401);
            return (e.getMessage());
        }
    };
    public static Route getTotal = (request, respons) -> {
        return gson.toJson(calculatePriceWithDiscount(request));
    };

    public static Cart getCart(Request request) {
        Session ss = request.session(true);
        Cart cart = ss.attribute("cart");
        if (cart == null) {
            UsersService usersService = new UsersService();
            cart = new Cart((Buyer) usersService.getByUsername(UserController.getLoggedingUsername(request)));
            ss.attribute("cart", cart);
        }
        return cart;
    }
    public static void deleteCart(Request request) {
        Session ss = request.session(true);
        ss.removeAttribute("cart");
    }
    private static double calculatePriceWithDiscount(Request request) {
        if (((Buyer) UserController.getLoggedingUser(request)).getBuyerType() != null) {
            double price = getCart(request).getPrice();
            return price - price * ((Buyer) UserController.getLoggedingUser(request)).getBuyerType().getDiscount();
        }
        return getCart(request).getPrice();
    }

    private static void valideteRestaurantStatus(String id){
        RestaurantService service=new RestaurantService();
        if(!service.getById(id).isStatus()) throw new CartException("Restaruant is closed.");
    }
}
