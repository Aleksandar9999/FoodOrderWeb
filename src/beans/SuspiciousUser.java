package beans;

public class SuspiciousUser extends User{
    private int countOfCanceledOrders;
    public SuspiciousUser(User user) {
        super(user);
    }
    public int getCountOfCanceledOrders() {
        return countOfCanceledOrders;
    }

    public void setCountOfCanceledOrders(int numberOfCanceledOrders) {
        this.countOfCanceledOrders = numberOfCanceledOrders;
    }
    @Override
    public User copy() {
        return new SuspiciousUser(this);
    }
}
