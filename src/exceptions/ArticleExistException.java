package exceptions;

public class ArticleExistException extends RuntimeException {
 public ArticleExistException(){
     super("Article with same name exists in restaurant.");
 }
}
