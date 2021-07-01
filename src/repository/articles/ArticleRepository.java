package repository.articles;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import DAO.ArticleDAO;
import beans.Article;
import beans.ArticleInCart;
import exceptions.ArticleExistException;
import generic.GenericRepository;
import repository.restaurants.RestaurantRepository;

public class ArticleRepository extends GenericRepository<Article, ArticleDAO> {

    public ArticleRepository() {
        super("./repo/articles.json");
    }

    @Override
    public HashMap<String, Article> readAll() {
        String json = readFromFile();
        Type type = new TypeToken<HashMap<String, ArticleDAO>>() {
        }.getType();
        HashMap<String, ArticleDAO> data = gson.fromJson(json, type);
        if (data == null)
            data = new HashMap<>();
        return transformDAO(data);
    }

    @Override
    public HashMap<String, ArticleDAO> transformData(HashMap<String, Article> data) {
        HashMap<String, ArticleDAO> ret = new HashMap<>();
        for (Article article : data.values()) {
            ret.put(article.getId(), new ArticleDAO(article));
        }
        return ret;
    }

    @Override
    public HashMap<String, Article> transformDAO(HashMap<String, ArticleDAO> data) {
        HashMap<String, Article> ret = new HashMap<>();
        RestaurantRepository restaurantRepository = new RestaurantRepository();
        for (ArticleDAO articleDAO : data.values()) {
            Article article = new Article(articleDAO);
            article.setRestaurant(restaurantRepository.getById(articleDAO.getRestaurantId()));
            ret.put(articleDAO.getId(), article);
        }
        return ret;
    }

    public List<ArticleInCart> getAllArticlesForCartByRestaurantId(String id) {
        // REFACTOR
        List<ArticleInCart> retVal = new ArrayList<ArticleInCart>();
        for (Article article : getAllArticlesByRestaurantId(id)) {
            retVal.add(new ArticleInCart(article, 1));
        }
        return retVal;
    }

    public Collection<Article> getAllArticlesByRestaurantId(String id) {
        Collection<Article> articles = readAll().values();
        articles.removeIf(ar -> !ar.getRestaurant().getId().equals(id));
        return articles;
    }

    @Override
    public void update(Article article) {
        HashMap<String, Article> articlesMap = readAll();
        if (articlesMap == null)
            articlesMap = new HashMap<String, Article>();
        ValidateNameOfArticle(article);
        articlesMap.put(article.getId(), article);
        saveAll(articlesMap);
    }

    private void ValidateNameOfArticle(Article candidaArticle) {
        for (Article article : readAll().values())
            if (article.getName().equals(candidaArticle.getName()))
                throw new ArticleExistException();
    }
}
