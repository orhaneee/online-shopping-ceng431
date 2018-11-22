package domain;

public class PlacedState implements State {

    public PlacedState() {
    }

    @Override
    public State submitOrder(Order order, Customer customer, int orderId) {
        System.out.println("PlacedState is not responsible for submitOrder.");
        return null;
    }

    @Override
    public State cancelOrder(Order order, Customer customer) {
        customer.cancelOrder(order);

        return new CancelledState();
    }

    @Override
    public State deleteOrder(Order order, Customer customer) {
        System.out.println("PlacedState is not responsible for deleteOrder.");
        return null;
    }

    @Override
    public State chargeCustomer(Order order, Customer customer) {
        order = Store.chargeCustomer(customer, order);
        order.getShoppingState().setState(new ChargedState());

        return order.getShoppingState().getState();
    }

    @Override
    public State cancelPayment(Order order, Customer customer) {
        System.out.println("PlacedState is not responsible for cancelPayment.");
        return null;
    }

    @Override
    public State shipOrder(Order order, Customer customer) {
        System.out.println("PlacedState is not responsible for shipOrder.");
        return null;
    }

    @Override
    public State deliverOrder(Order order) {
        System.out.println("PlacedState is not responsible for deliverOrder.");
        return null;
    }

    @Override
    public State notShipped(Order order) {
        System.out.println("PlacedState is not responsible for notShipped.");
        return null;
    }

    @Override
    public State lostInShipping(Order order, Customer customer) {
        System.out.println("PlacedState is not responsible for lostInShipping.");
        return null;
    }

}
