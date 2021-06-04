package service;

import java.util.UUID;

import DAO.CommentDAO;
import beans.Buyer;
import beans.Comment;
import beans.Restaurant;
import generic.GenericService;
import repository.comment.CommentRepository;

public class CommentService extends GenericService<Comment,CommentDAO> {

    public CommentService() {
        super(new CommentRepository());
    }


public static void main(String[] args) {
    CommentService service=new CommentService();
    UsersService userserService=new UsersService();
    Buyer buyer=(Buyer) userserService.getByUsername("aca");
    RestaurantService restaurantService=new RestaurantService();
    Restaurant restaurant=restaurantService.getAll().get(0);
    Comment comment=service.getAll().get(0);
    comment.setComment("TOPCIDER");
    service.update(comment);
}

    
}
