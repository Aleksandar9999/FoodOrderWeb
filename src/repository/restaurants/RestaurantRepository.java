package repository.restaurants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.Article;
import beans.Restaurant;
import generic.GenericFileRepository;
import repository.articles.ArticlesRepository;

public class RestaurantRepository extends GenericFileRepository<Restaurant> {
	public RestaurantRepository() {
		super("./repo/restaurants.json");
	}

	@Override
	public HashMap<String, Restaurant> readAll() {
		String json = readFromFile();
		Type type = new TypeToken<HashMap<String, Restaurant>>() {
		}.getType();
		HashMap<String, Restaurant> data = gson.fromJson(json, type);
		return data;
	}

	/*private HashMap<String, Restaurant> mergeWithArticles(HashMap<String, Restaurant> data) {
		ArticlesRepository repository=new ArticlesRepository();
		Collection<Restaurant> restaurants=data.values();
		for (Restaurant restaurant : restaurants) {
			System.out.println(restaurant.getName());
			ArrayList<Article> articles=repository.getAllArticlesByRestaurantId(restaurant.getId());
			restaurant.setArticles(articles);
		}
		return data;
	}*/

	public List<Restaurant> getAllRestaurantsSorted() {
		List<Restaurant> restaurants = getAll();
		restaurants.sort(new Comparator<Restaurant>() {
			@Override
			public int compare(Restaurant o1, Restaurant o2) {
				return Boolean.compare(o2.isStatus(), o1.isStatus());
			}
		});
		return restaurants;
	}

	public List<Restaurant> getAllByAvgRate(String name) {
		throw new UnsupportedOperationException("Implementirj");
	}

}
