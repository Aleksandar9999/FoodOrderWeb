package beans;

import enumerations.RequestStatus;

public class DeliverRequest extends Entity {
    private String orderId;
    private String delivererId;

    private Order order;
    private Deliverer deliverer;
    private RequestStatus status;
    public DeliverRequest(Order order) {
        setOrder(order);
        status=RequestStatus.Pending;
    }
    public DeliverRequest(Order order,Deliverer deliverer) {
        setOrder(order);
        setDeliverer(deliverer);
        status=RequestStatus.Pending;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDelivererId() {
        return delivererId;
    }

    public void setDelivererId(String delivererId) {
        this.delivererId = delivererId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        this.orderId=order.getId();
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
        this.delivererId=deliverer.getUsername();
    }
    public RequestStatus getStatus() {
        return status;
    }
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

}
