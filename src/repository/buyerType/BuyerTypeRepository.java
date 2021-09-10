package repository.buyerType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import beans.BuyerType;

import com.google.gson.reflect.TypeToken;

public class BuyerTypeRepository {
    String filePath = "./repo/buyerType.json";
    Gson gson=new GsonBuilder().setPrettyPrinting().create();
    private Type type = new TypeToken<HashMap<String, BuyerType>>() {}.getType();

    public BuyerType getBuyerTypeByCollectedPoints(double collectedPoints){
        BuyerType buyerType=null;
        for (BuyerType type : getAll()) {
            if(collectedPoints >= type.getReqCollectedPoints())
                buyerType=type;      
        }
        return buyerType;
    }
    
    public HashMap<String, BuyerType> readAll() {
        String json = readFromFile();
        Type type = new TypeToken<HashMap<String, BuyerType>>() {
        }.getType();
        HashMap<String, BuyerType> data = gson.fromJson(json, type);
        if (data == null)
            data = new HashMap<String, BuyerType>();
        return data;
    }

    public ArrayList<BuyerType> getAll() {
        return new ArrayList<BuyerType>(readAll().values());
    }

    public BuyerType getById(String id) {
        HashMap<String, BuyerType> restaurants = readAll();
        return restaurants.get(id);
    }

    public BuyerType addNew(BuyerType restaurant) {
        HashMap<String, BuyerType> restaurants = readAll();
        restaurants.put(restaurant.getName(), restaurant);
        saveAll(restaurants);
        return restaurant;
    }

    public void saveAll(HashMap<String, BuyerType> data) {
        String json = gson.toJson(data, type);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String readFromFile() {
        StringBuilder resultStringBuilder = new StringBuilder();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(this.filePath));
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
            br.close();
            return resultStringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
