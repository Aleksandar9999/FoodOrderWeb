package service;

import java.util.Collection;
import java.util.List;

import beans.Article;
import beans.ArticleInCart;
import generic.GenericFileService;
import repository.articles.ArticlesRepository;
public class ArticleService extends GenericFileService<Article> {

    public ArticleService() {
        super(new ArticlesRepository());
    }

    public List<ArticleInCart> getAllArticlesForCartByRestaurantId(String id){
        return ((ArticlesRepository)repository).getAllArticlesForCartByRestaurantId(id);
    }
    
    public Collection<Article> getAllArticlesByRestaurantId(String id) {
        return ((ArticlesRepository)repository).getAllArticlesByRestaurantId(id);
    }
}
