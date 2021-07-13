package repository.comment;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.Buyer;
import beans.Comment;
import generic.GenericFileRepository;
import repository.restaurants.RestaurantRepository;
import repository.users.UsersRepository;

public class CommentRepository extends GenericFileRepository<Comment>{

    public CommentRepository() {
        super("./repo/comments.json");
    }

    @Override
    public HashMap<String, Comment> readAll() {
        String json = readFromFile();
		Type type = new TypeToken<HashMap<String, Comment>>() {
		}.getType();
		HashMap<String, Comment> data = gson.fromJson(json, type);
		if(data==null) data=new HashMap<>();
		return margeWithObjects(data);
    }

    
    private HashMap<String, Comment> margeWithObjects(HashMap<String, Comment> data) {
        UsersRepository repository=new UsersRepository();
        RestaurantRepository restaurantRepository=new RestaurantRepository();
        for (Comment comment : data.values()) {
            comment.setBuyer((Buyer)repository.getByUsername(comment.getBuyerId()));
            comment.setRestaurant(restaurantRepository.getById(comment.getRestaurantId()));
        }
        return data;
    }

    public List<Comment> getApprovedCommentsForRestaurant(String id){
        List<Comment> list=getAll();
        list.removeIf(com-> !com.isApproved() || !com.getRestaurant().getId().equals(id));
        return list;
    }
}
