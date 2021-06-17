package service;

import java.util.List;
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
    public List<Comment> getApprovedCommentsForRestaurant(String id){
        return ((CommentRepository) repository).getApprovedCommentsForRestaurant(id);
    }
}
