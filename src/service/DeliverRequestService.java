package service;

import java.util.Collection;

import beans.DeliverRequest;
import beans.Deliverer;
import beans.Order;
import beans.User;
import enumerations.OrderStatus;
import enumerations.RequestStatus;
import generic.GenericFileService;
import repository.deliverRequest.DeliverRequestRepository;

public class DeliverRequestService extends GenericFileService<DeliverRequest> {

    public DeliverRequestService() {
        super(new DeliverRequestRepository());
    }

    public Collection<DeliverRequest> getAllByRestaurantId(String id) {
        return ((DeliverRequestRepository) repository).getAllByRestaurantId(id);
    }

    @Override
    public void update(DeliverRequest deliverRequest) {
        if (deliverRequest.getStatus().equals(RequestStatus.Confirmed)) {
            OrderService orderService = new OrderService();
            Order order = orderService.getById(deliverRequest.getOrderId());
            order.setOrderStatus(OrderStatus.Transport);
            orderService.update(order);
            UsersService usersService = new UsersService();
            Deliverer deliverer = (Deliverer) usersService.getByUsername(deliverRequest.getDelivererId());
            deliverer.addOrder(order);
            usersService.update(deliverer.getUsername(), deliverer);
        }
        super.update(deliverRequest);
    }

}
