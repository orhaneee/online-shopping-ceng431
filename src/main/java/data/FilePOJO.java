package data;

import domain.Customer;
import domain.Order;

import java.util.List;

public class FilePOJO {

    private List<Customer> customerList;
    private List<Order> orderList;

    public FilePOJO() {
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
