package service;
import DAO.CartDAO;
import beans.Cart;
import generic.GenericService;
import repository.cart.CartRepository;

public class CartService extends GenericService<Cart,CartDAO> {

    public CartService() {
        super(new CartRepository());
    }
}
