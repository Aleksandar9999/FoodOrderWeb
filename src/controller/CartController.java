package controller;
import com.google.gson.Gson;

import beans.ArticleInCart;
import beans.Buyer;
import beans.Cart;
import service.UsersService;
import spark.Request;
import spark.Route;
import spark.Session;

public class CartController {
    private static Gson gson =new Gson();
    public static Route addArticleInCart =(request,response)->{
        response.type("application/json");
        ArticleInCart articleInCart=gson.fromJson(request.body(), ArticleInCart.class);
        try{
            UserController.validateLoggedinBuyer(request);
            getCart(request).addArticle(articleInCart);
            return ("OK");
        }catch(RuntimeException e){
            response.status(401);
            return (e.getMessage());
        }
    };

    public static Route getCart=(request,respons)->{    
        return gson.toJson(getCart(request));
    };
    public static Route getTotal=(request,respons)->{    
        return gson.toJson(getCart(request).getPrice());
    };
    
    
    public static Cart getCart(Request request){
        Session ss = request.session(true);
		Cart cart = ss.attribute("cart"); 
		if (cart == null) {
            UsersService usersService =new UsersService();
            cart = new Cart((Buyer)usersService.getByUsername(UserController.getLoggedingUsername(request)));
			ss.attribute("cart", cart);
		}
		return cart;
    }

}
