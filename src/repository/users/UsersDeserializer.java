package repository.users;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import beans.Administrator;
import beans.Buyer;
import beans.Deliverer;
import beans.Manager;
import beans.User;
import enumerations.UserRole;
import repository.restaurants.RestaurantRepository;

public class UsersDeserializer implements JsonDeserializer<HashMap<String, User>> {

	@Override
	public HashMap<String, User> deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		Gson g = new Gson();
		HashMap<String, User> map=new HashMap<String, User>();
		JsonArray object=json.getAsJsonArray();
		for (var memr : object) {
			String rola=memr.getAsJsonObject().get("userRole").toString();
			rola=rola.substring(1, rola.length()-1);
			if(rola.equals(UserRole.Buyer.toString())) {
				Buyer buyer=g.fromJson(memr, Buyer.class);
				map.put(buyer.getUsername(), buyer);
				continue;
			}
			if(rola.equals(UserRole.Manager.toString())) {
				Manager manager=g.fromJson(memr, Manager.class);
				RestaurantRepository restaurantRepository=new RestaurantRepository();
				if(manager.getRestaurantId()!=null)
					manager.setRestaurant(restaurantRepository.getById(manager.getRestaurantId()));
				map.put(manager.getUsername(), manager);
				continue;
			}
			if(rola.equals(UserRole.Administrator.toString())) {
				Administrator buyer=g.fromJson(memr, Administrator.class);
				map.put(buyer.getUsername(), buyer);
				continue;
			}
			if(rola.equals(UserRole.Deliverer.toString())) {
				Deliverer buyer=g.fromJson(memr, Deliverer.class);
				map.put(buyer.getUsername(), buyer);
				continue;
			}
		}
		return map;
	}
}
