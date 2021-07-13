package repository.articles;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.Article;
import beans.ArticleInCart;
import exceptions.ArticleExistException;
import generic.GenericFileRepository;
import repository.restaurants.RestaurantRepository;

public class ArticlesRepository extends GenericFileRepository<Article> {

    public ArticlesRepository() {
        super("./repo/articles.json");
    }

    @Override
    public HashMap<String, Article> readAll() {
        String json = readFromFile();
        Type type = new TypeToken<HashMap<String, Article>>() {
        }.getType();
        HashMap<String, Article> data = gson.fromJson(json, type);
        if (data == null)
            data = new HashMap<String, Article>();
        return mergeWithRestaurants(data);
    }

    private HashMap<String, Article> mergeWithRestaurants(HashMap<String, Article> data) {
        RestaurantRepository repository=new RestaurantRepository();
        for (Article item : data.values()) {
            item.setRestaurant(repository.getById(item.getRestaurantId()));
        }
        return data;
    }

    public List<ArticleInCart> getAllArticlesForCartByRestaurantId(String id) {
        // REFACTOR
        List<ArticleInCart> retVal = new ArrayList<ArticleInCart>();
        for (Article article : getAllArticlesByRestaurantId(id)) {
            retVal.add(new ArticleInCart(article, 1));
        }
        return retVal;
    }

    public ArrayList<Article> getAllArticlesByRestaurantId(String id) {
        ArrayList<Article> articles = new ArrayList<>();
        for (Article article : getAll()) {
            if(article.getRestaurantId().equals(id))
                articles.add(article);
        }
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
