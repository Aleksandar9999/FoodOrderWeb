package repository.comment;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

import DAO.CommentDAO;
import beans.Buyer;
import beans.Comment;
import generic.GenericRepository;
import repository.restaurants.RestaurantRepository;
import repository.users.UsersRepository;

public class CommentRepository extends GenericRepository<Comment,CommentDAO>{

    public CommentRepository() {
        super("./repo/comments.json");
    }

    @Override
    public HashMap<String, Comment> readAll() {
        String json = readFromFile();
		Type type = new TypeToken<HashMap<String, CommentDAO>>() {
		}.getType();
		HashMap<String, CommentDAO> data = gson.fromJson(json, type);
		if(data==null) data=new HashMap<>();
		return transformDAO(data);
    }

    @Override
    public HashMap<String, CommentDAO> transformData(HashMap<String, Comment> data) {
        HashMap<String, CommentDAO> ret=new HashMap<>();
        for (Comment cart : data.values()) {
            ret.put(cart.getId(), new CommentDAO(cart));
        }
        return ret;
    }

    @Override
    public HashMap<String, Comment> transformDAO(HashMap<String, CommentDAO> data) {
        HashMap<String, Comment> ret=new HashMap<>();
        RestaurantRepository repository=new RestaurantRepository();
        UsersRepository usersRepository=new UsersRepository();
        for (CommentDAO dao : data.values()) {
            Comment comment=new Comment(dao); 
            comment.setBuyer((Buyer)usersRepository.getByUsername(dao.getBuyerId()));
            comment.setRestaurant(repository.getById(dao.getRestaurantId()));
            ret.put(dao.getId(), comment);
        }
        return ret;
    }
    
}
