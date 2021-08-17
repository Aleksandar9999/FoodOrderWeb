package repository.deliverRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.DeliverRequest;
import beans.Deliverer;
import beans.Order;
import generic.GenericFileRepository;
import repository.orders.OrdersRepository;
import repository.users.UsersRepository;

public class DeliverRequestRepository extends GenericFileRepository<DeliverRequest> {

    public DeliverRequestRepository() {
        super("./repo/deliverer_requests.json");
    }

    @Override
    public HashMap<String, DeliverRequest> readAll() {
        String json = readFromFile();
        Type type = new TypeToken<HashMap<String, DeliverRequest>>() {
        }.getType();
        HashMap<String, DeliverRequest> data = gson.fromJson(json, type);
        if (data == null)
            data = new HashMap<String, DeliverRequest>();
        return mergeWithObjects(data);
    }

    private HashMap<String, DeliverRequest> mergeWithObjects(HashMap<String, DeliverRequest> data){
        for (DeliverRequest request : data.values()) {
            UsersRepository usersRepository=new UsersRepository();
            request.setDeliverer((Deliverer)usersRepository.getByUsername(request.getDelivererId()));
            OrdersRepository orderRepository =new OrdersRepository();
            request.setOrder(orderRepository.getById(request.getOrderId()));
        }
        return data;
    }

    public Collection<DeliverRequest> getAllByRestaurantId(String id){
        List<DeliverRequest> requests= new ArrayList<>(readAll().values());
        requests.removeIf(req->!req.getOrder().getRestaurant().getId().equals(id));
        mergeWithAnotherOrders(requests,id);
        return requests;
    }

    private void mergeWithAnotherOrders(List<DeliverRequest> requests,String id){
        OrdersRepository ordersRepository=new OrdersRepository();
        for (Order order : ordersRepository.getAllByRestaurant(id)) {
            if(!orderInRequests(order,requests)) requests.add(new DeliverRequest(order));
        }
    }
    private boolean orderInRequests(Order order, List<DeliverRequest> requests) {
        for (DeliverRequest deliverRequest : requests) {
            if(deliverRequest.getOrder().equals(order)) return true;
        }
        return false;
    }
}
