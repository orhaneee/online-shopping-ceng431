package domain;

import java.util.Date;
import java.util.Random;

public class Order {

    private int id;
    private int trackingNumber;
    private int customerId;
    private String customerName;
    private double weight;
    private String shippingAddress;
    private Date dateShipped;
    private Date dateDelivered;
    private double productPrice;
    private double cargoPrice;
    private double totalPrice;
    private Shopping shoppingState;

    public Order(String shippingAddress, double weight, Shopping shoppingState) {
        this.id = 0;
        this.trackingNumber = 0;
        this.customerId = 0;
        this.customerName = "";
        this.weight = weight;
        this.shippingAddress = shippingAddress;
        this.dateShipped = null;
        this.dateDelivered = null;
        this.productPrice = 0.0;
        this.cargoPrice = 0.0;
        this.totalPrice = 0.0;
        this.shoppingState = shoppingState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber() {
        Random rand = new Random();

        this.trackingNumber = (rand.nextInt(900000) + 100000);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public Date getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(Date dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice() {
        this.productPrice = (55 * getWeight());
    }

    public double getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice() {
        Random rand = new Random();

        int distance = (rand.nextInt(401) + 100);

        this.cargoPrice = (0.53 * distance);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = getCargoPrice() + getProductPrice();
    }

    public void setShoppingState(Shopping shoppingState) {
        this.shoppingState = shoppingState;
    }

    public Shopping getShoppingState() {
        return shoppingState;
    }
}
