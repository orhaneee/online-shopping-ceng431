package domain;

import java.util.Date;

public class Store {

    public static Order chargeCustomer(Customer customer, Order order) {
        order.setProductPrice();

        return order;
    }

    public static Order cancelPayment(Customer customer, Order order) {
        double refundedSavings = customer.getSavings() + order.getTotalPrice();
        customer.setSavings(refundedSavings);

        return order;
    }

    public static Order shipOrder(Customer customer, Order order) {
        order.setCargoPrice();
        order.setTotalPrice();
        if (customer.getSavings() < order.getTotalPrice()) {
            order.getShoppingState().setState(new PlacedState());
            return order;
        } else {
            double remainingSavings = customer.getSavings() - order.getTotalPrice();
            customer.setSavings(remainingSavings);
            return order;
        }
    }

    public static Order deliverOrder(Order order) {
        Date dateShipped = new Date();
        order.setDateShipped(dateShipped);

        order.getShoppingState().setState(new DeliveredState());

        return order;
    }

}
