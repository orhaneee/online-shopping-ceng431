package domain;

public class ShippedState implements State {

    public ShippedState() {
    }

    @Override
    public State submitOrder(Order order, Customer customer, int orderId) {
        System.out.println("ShippedState is not responsible for submitOrder.");
        return null;
    }

    @Override
    public State cancelOrder(Order order, Customer customer) {
        System.out.println("ShippedState is not responsible for cancelOrder.");
        return null;
    }

    @Override
    public State deleteOrder(Order order, Customer customer) {
        System.out.println("ShippedState is not responsible for deleteOrder.");
        return null;
    }

    @Override
    public State chargeCustomer(Order order, Customer customer) {
        System.out.println("ShippedState is not responsible for chargeCustomer.");
        return null;
    }

    @Override
    public State cancelPayment(Order order, Customer customer) {
        System.out.println("ShippedState is not responsible for cancelPayment.");
        return null;
    }

    @Override
    public State shipOrder(Order order, Customer customer) {
        System.out.println("ShippedState is not responsible for shipOrder.");
        return null;
    }

    @Override
    public State deliverOrder(Order order) {
        Order orderDelivered = Store.deliverOrder(order);

        return orderDelivered.getShoppingState().getState();
    }

    @Override
    public State notShipped(Order order) {
        System.out.println("Error: Not shipped");
        order.getShoppingState().setState(new ChargedState());

        return order.getShoppingState().getState();
    }

    @Override
    public State lostInShipping(Order order, Customer customer) {
        System.out.println("ShippedState is not responsible for lostInShipping.");
        return null;
    }

}
