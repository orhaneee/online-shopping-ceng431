package domain;

public interface State {

    State submitOrder(Order order, Customer customer, int orderId);

    State cancelOrder(Order order, Customer customer);

    State deleteOrder(Order order, Customer customer);

    State chargeCustomer(Order order, Customer customer);

    State cancelPayment(Order order, Customer customer);

    State shipOrder(Order order, Customer customer);

    State deliverOrder(Order order);

    State notShipped(Order order);

    State lostInShipping(Order order, Customer customer);

}
