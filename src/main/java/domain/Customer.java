package domain;

public class Customer {

    private int id;
    private String name;
    private String address;
    private double savings;
    private int phoneNumber;
    private String email;
    private String password;

    public Customer(int id, String name, String address, double savings, int phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.savings = savings;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Order saveOrder(String shippingAddress, double weight) {
        Shopping shoppingState = new Shopping();
        shoppingState.setState(new SavedState());
        return new Order(shippingAddress, weight, shoppingState);
    }

    public Order submitOrder(Order order, int orderId) {
        order.setId(orderId);
        order.setTrackingNumber();
        order.setCustomerId(this.getId());
        order.setCustomerName(this.getName());
        State placedState = new PlacedState();
        order.getShoppingState().setState(placedState);

        return order;
    }

    public void cancelOrder(Order order) {
        order.getShoppingState().setState(new CancelledState());
        System.out.println("You have successfully cancelled your order.");
    }

    public void deleteOrder(Order order) {
        order.getShoppingState().setState(new CancelledState());
        System.out.println("You have successfully deleted your order.");
    }

}
