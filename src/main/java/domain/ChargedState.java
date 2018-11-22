package domain;

public class ChargedState implements State {

    public ChargedState() {
    }

    @Override
    public State submitOrder(Order order, Customer customer, int orderId) {
        System.out.println("ChargedState is not responsible for submitOrder.");
        return null;
    }

    @Override
    public State cancelOrder(Order order, Customer customer) {
        customer.cancelOrder(order);

        return order.getShoppingState().getState();
    }

    @Override
    public State deleteOrder(Order order, Customer customer) {
        System.out.println("ChargedState is not responsible for deleteOrder.");
        return null;
    }

    @Override
    public State chargeCustomer(Order order, Customer customer) {
        System.out.println("ChargedState is not responsible for chargeCustomer.");
        return null;
    }

    @Override
    public State cancelPayment(Order order, Customer customer) {
        order = Store.cancelPayment(customer, order);
        order.getShoppingState().setState(new PlacedState());

        return order.getShoppingState().getState();
    }

    @Override
    public State shipOrder(Order order, Customer customer) {
        order = Store.shipOrder(customer, order);
        order.getShoppingState().setState(new ShippedState());

        return order.getShoppingState().getState();
    }

    @Override
    public State deliverOrder(Order order) {
        System.out.println("ChargedState is not responsible for deliverOrder.");
        return null;
    }

    @Override
    public State notShipped(Order order) {
        System.out.println("ChargedState is not responsible for notShipped.");
        return null;
    }

    @Override
    public State lostInShipping(Order order, Customer customer) {
        System.out.println("ChargedState is not responsible for lostInShipping.");
        return null;
    }

}
