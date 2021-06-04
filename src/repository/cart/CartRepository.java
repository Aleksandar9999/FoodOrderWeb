package repository.cart;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;
import DAO.CartDAO;
import beans.Buyer;
import beans.Cart;
import generic.GenericRepository;
import repository.users.UsersRepository;

public class CartRepository extends GenericRepository<Cart,CartDAO> {

    public CartRepository() {
        super("./repo/carts.json");
    }
    //#region ImplementationOfAbstractMethods
    @Override
    public HashMap<String, Cart> readAll() {
        String json = readFromFile();
		Type type = new TypeToken<HashMap<String, CartDAO>>() {
		}.getType();
		HashMap<String, CartDAO> data = gson.fromJson(json, type);
		if(data==null) data=new HashMap<>();
		return transformDAO(data);
    }

    @Override
    public HashMap<String, CartDAO> transformData(HashMap<String, Cart> data) {
        HashMap<String, CartDAO> ret=new HashMap<>();
        for (Cart cart : data.values()) {
            ret.put(cart.getId(), new CartDAO(cart));
        }
        return ret;
    }

    @Override
    public HashMap<String, Cart> transformDAO(HashMap<String, CartDAO> data) {
        HashMap<String, Cart> ret=new HashMap<>();
        UsersRepository repository=new UsersRepository();
        for (CartDAO cartDAO : data.values()) {
            Cart cart = new Cart(cartDAO);
            cart.buyer=(Buyer)repository.getByUsername(cartDAO.getBuyerId());
            ret.put(cartDAO.getId(),cart);
        }
        return ret;
    }
    //#endregion

    public Cart getCartByBuyerUsername(String username){
        HashMap<String, Cart> map=readAll();
        for (Cart cart : map.values()) {
            if(cart.getBuyer().getUsername().equals(username))
                return cart;
        }
        UsersRepository usersRepository=new UsersRepository();
        return new Cart((Buyer)usersRepository.getByUsername(username));
    }

}
