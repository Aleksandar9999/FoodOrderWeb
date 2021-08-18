package service;

import java.util.List;

import beans.Comment;
import generic.GenericFileRepository;
import generic.GenericFileService;
import repository.comment.CommentRepository;

public class CommentService extends GenericFileService<Comment> {

    public CommentService(GenericFileRepository<Comment> repository) {
        super(repository);
    }

    public CommentService() {
        super(new CommentRepository());
    }
    public List<Comment> getAllCommentsByRestaurant(String id){
        return ((CommentRepository) repository).getAllCommentsByRestaurant(id);
    }
    public List<Comment> getApprovedCommentsByRestaurant(String id){
        return ((CommentRepository) repository).getApprovedCommentsByRestaurant(id);
    }
    public List<Comment> getCommentsByUser(String username){
        return ((CommentRepository) repository).getCommentsByUser(username);     
    }
}
