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
    public List<Comment> getApprovedCommentsForRestaurant(String id){
        return ((CommentRepository) repository).getApprovedCommentsForRestaurant(id);
    }
}
