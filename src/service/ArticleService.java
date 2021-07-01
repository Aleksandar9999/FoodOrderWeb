package service;

import java.util.Collection;
import java.util.List;

import DAO.ArticleDAO;
import beans.Article;
import beans.ArticleInCart;
import generic.GenericService;
import repository.articles.ArticleRepository;

public class ArticleService extends GenericService<Article,ArticleDAO>{

    public ArticleService() {
        super(new ArticleRepository());
    }

    public List<ArticleInCart> getAllArticlesForCartByRestaurantId(String id){
        return ((ArticleRepository)repository).getAllArticlesForCartByRestaurantId(id);
    }
    
    public Collection<Article> getAllArticlesByRestaurantId(String id) {
        return ((ArticleRepository)repository).getAllArticlesByRestaurantId(id);
    }
}
