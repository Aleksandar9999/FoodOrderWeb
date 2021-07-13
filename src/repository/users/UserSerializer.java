package repository.users;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import beans.Administrator;
import beans.Buyer;
import beans.Deliverer;
import beans.Manager;
import beans.User;

public class UserSerializer implements JsonSerializer<HashMap<String, User>>{

	@Override
	public JsonElement serialize(HashMap<String, User> map, Type arg1, JsonSerializationContext arg2) {
		Gson g=new Gson();
		JsonArray array=new JsonArray();
		for (User us : map.values()) {
			if(us instanceof Administrator)
			{
				JsonElement jsonElement = g.toJsonTree((Administrator)us);
				array.add(jsonElement);
				continue;
			}
				
			if(us instanceof Buyer)
			{
				JsonElement jsonElement = g.toJsonTree((Buyer)us);
				array.add(jsonElement);
				continue;
			}
			
			if(us instanceof Deliverer)
			{
				JsonElement jsonElement = g.toJsonTree((Deliverer)us);
				array.add(jsonElement);
				continue;
			}
			if(us instanceof Manager)
			{
				JsonElement jsonElement = g.toJsonTree((Manager)us);
				array.add(jsonElement);
				continue;
			}else{
				JsonElement jsonElement = g.toJsonTree(us);
				array.add(jsonElement);
				continue;
			}
		}
		return array;
	}

}
