package domain;

public class SavedState implements State {

    public SavedState() {
    }

    @Override
    public State submitOrder(Order order, Customer customer, int orderId) {
        order = customer.submitOrder(order, orderId);

        return order.getShoppingState().getState();
    }

    @Override
    public State cancelOrder(Order order, Customer customer) {
        System.out.println("SavedState is not responsible for cancelOrder.");
        return null;
    }

    @Override
    public State deleteOrder(Order order, Customer customer) {
        customer.deleteOrder(order);

        return order.getShoppingState().getState();
    }

    @Override
    public State chargeCustomer(Order order, Customer customer) {
        System.out.println("SavedState is not responsible for chargeCustomer.");
        return null;
    }

    @Override
    public State cancelPayment(Order order, Customer customer) {
        System.out.println("SavedState is not responsible for cancelPayment.");
        return null;
    }

    @Override
    public State shipOrder(Order order, Customer customer) {
        System.out.println("SavedState is not responsible for shipOrder.");
        return null;
    }

    @Override
    public State deliverOrder(Order order) {
        System.out.println("SavedState is not responsible for deliverOrder.");
        return null;
    }

    @Override
    public State notShipped(Order order) {
        System.out.println("SavedState is not responsible for notShipped.");
        return null;
    }

    @Override
    public State lostInShipping(Order order, Customer customer) {
        System.out.println("SavedState is not responsible for lostInShipping.");
        return null;
    }

}
