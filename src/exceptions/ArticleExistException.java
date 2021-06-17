package exceptions;

public class ArticleExistException extends RuntimeException {
 public ArticleExistException(){
     super("Artikal vec postoji");
 }
}
