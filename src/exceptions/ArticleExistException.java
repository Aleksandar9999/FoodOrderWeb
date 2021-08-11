package exceptions;

public class ArticleExistException extends RuntimeException {
 public ArticleExistException(){
     super("Article already exists.");
 }
}
