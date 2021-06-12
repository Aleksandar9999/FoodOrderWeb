package beans;

public class BuyerType {
    private String name;
    private double discount;
    private double reqCollectedPoints;
    
    public BuyerType() {
    }
    
    public BuyerType(String name, double discount, double reqCollectedPoints) {
        this.name = name;
        this.discount = discount;
        this.reqCollectedPoints = reqCollectedPoints;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getReqCollectedPoints() {
        return reqCollectedPoints;
    }
    public void setReqCollectedPoints(double reqCollectedPoints) {
        this.reqCollectedPoints = reqCollectedPoints;
    }


    
}
