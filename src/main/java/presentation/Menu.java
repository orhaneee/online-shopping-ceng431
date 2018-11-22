package presentation;

import data.FileManipulator;
import domain.*;

import java.util.*;

public class Menu {
    private static Menu ourInstance = new Menu();

    public static Menu getInstance() {
        return ourInstance;
    }

    private Menu() {
    }

    private FileManipulator manipulator = FileManipulator.getInstance();

    public void init() {
        while (true) {
            System.out.println("Welcome to Online Shopping System.");
            System.out.println("If you have an account, press 1 to login with your email and password.");
            System.out.println("If you don't, press 2 to register.");
            System.out.println();
            System.out.println("Press 0 to exit.");

            Scanner scanner = new Scanner(System.in);

            int number = scanner.nextInt();
            scanner.nextLine();
            switch (number) {
                case 1:
                    System.out.println("Please enter your email address you registered with:");
                    String emailAddress = scanner.nextLine();
                    System.out.println("Please enter your password:");
                    String password = scanner.nextLine();
                    if (checkLoginInfo(emailAddress, password) != null) {
                        saveOrder(checkLoginInfo(emailAddress, password));
                    } else {
                        System.out.println("Your email address and password does not match.");
                    }
                    break;
                case 2:
                    System.out.println("Please enter your name:");
                    String name = scanner.nextLine();
                    System.out.println("Please enter your address:");
                    String address = scanner.nextLine();
                    System.out.println("Please enter how much money do you want to add:");
                    double savings = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Please enter your phone number:");
                    int phoneNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter your email address:");
                    String emailAddress2 = scanner.nextLine();
                    System.out.println("Please set your password:");
                    String password2 = scanner.nextLine();
                    String filename = "customers.json";
                    Random rand = new Random();
                    int customerId = rand.nextInt(100);
                    manipulator.writeCustomerToFile(new Customer(customerId, name, address, savings,
                            phoneNumber, emailAddress2, password2), filename);
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

        }
    }

    private Customer checkLoginInfo(String emailAddress, String password) {
        List<Customer> customerList = manipulator.readCustomersFromFile("customers.json");
        if (customerList != null && customerList.size() > 0) {
            for (Customer customer : customerList) {
                if (customer.getEmail().equals(emailAddress)) {
                    if (customer.getPassword().equals(password)) {
                        return customer;
                    }
                }
            }
        }
        return null;
    }

    private void saveOrder(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("In order to save new order, please enter some information about it.");
        System.out.println("Please enter the shipping address:");
        String shippingAddress = scanner.nextLine();
        System.out.println("Please enter the weight of the order:");
        Double weight = scanner.nextDouble();
        scanner.nextLine();

        Order order = customer.saveOrder(shippingAddress, weight);

        Shopping state = new Shopping();
        state.setState(order.getShoppingState().getState());

        decideOnSavedOrder(order, customer, state);
    }

    private void decideOnSavedOrder(Order order, Customer customer, Shopping state) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Please press 1 to submit the order or press 2 to delete the order.");
        int index = scanner.nextInt();
        scanner.nextLine();
        switch (index) {
            case 1:
                int orderId = assignOrderId();
                state.setState(state.getState().submitOrder(order, customer, orderId));

                decideOnPlacedOrder(order, customer, state);
                break;
            case 2:
                state.setState(state.getState().deleteOrder(order, customer));
                order.getShoppingState().setState(new CancelledState());
                break;
        }

    }

    private void decideOnPlacedOrder(Order order, Customer customer, Shopping state) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Please press 1 to confirm to purchase the order or press 2 to cancel the order.");
        int index = scanner.nextInt();
        scanner.nextLine();
        switch (index) {
            case 1:
                state.setState(state.getState().chargeCustomer(order, customer));

                decideOnChargedOrder(order, customer, state);
                break;
            case 2:
                state.setState(state.getState().cancelOrder(order, customer));
                break;
        }

    }

    private void decideOnChargedOrder(Order order, Customer customer, Shopping state) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Please press 1 to ship the order, press " +
                "2 to cancel the payment, or press 3 to cancel the order.");
        int index = scanner.nextInt();
        scanner.nextLine();
        switch (index) {
            case 1:
                state.setState(state.getState().shipOrder(order, customer));

                if (order.getShoppingState().getState() instanceof ShippedState)
                    checkShippedOrder(order, customer, state);
                else {
                    System.out.println("Your savings cannot afford this order.");
                    decideOnPlacedOrder(order, customer, state);
                }
                break;
            case 2:
                state.setState(state.getState().cancelPayment(order, customer));

                decideOnPlacedOrder(order, customer, state);
                break;
            case 3:
                state.setState(state.getState().cancelOrder(order, customer));
                break;
        }
    }

    private void checkShippedOrder(Order order, Customer customer, Shopping state) {
        Random rand = new Random();

        int realShippingDurationInDays = rand.nextInt(10) + 1;
        int plannedShippingDurationInDays = rand.nextInt(10) + 1;

        if ((realShippingDurationInDays - plannedShippingDurationInDays) > 7) {

            state.setState(state.getState().notShipped(order));

            decideOnChargedOrder(order, customer, state);
        } else {

            state.setState(state.getState().deliverOrder(order));

            checkDeliveredOrder(order, customer, state);
        }
    }

    private void checkDeliveredOrder(Order order, Customer customer, Shopping state) {
        Random rand = new Random();

        int realDeliveryDurationInDays = rand.nextInt(10) + 3;
        int plannedDeliveryDurationInDays = rand.nextInt(10) + 3;

        if ((realDeliveryDurationInDays - plannedDeliveryDurationInDays) > 8) {
            state.setState(state.getState().notShipped(order));

            decideOnChargedOrder(order, customer, state);
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(order.getDateShipped());
            c.add(Calendar.DATE, realDeliveryDurationInDays);
            Date dateDelivered = c.getTime();
            order.setDateDelivered(dateDelivered);
            order.getShoppingState().setState(new DeliveredState());
            System.out.println("Delivery Date: " + order.getDateDelivered().toString());

            manipulator.writeOrderToFile(order, "orders.json");
            updateCustomerSavings(customer);
        }
    }

    private void updateCustomerSavings(Customer customer) {
        List<Customer> customerList = manipulator.readCustomersFromFile("customers.json");
        for (Customer customer1 : customerList) {
            if (customer1.getId() == customer.getId()) {
                customer1.setSavings(customer.getSavings());
            }
        }
        manipulator.writeCustomerListToFile(customerList);
    }

    private int assignOrderId() {
        List<Order> orderList = manipulator.readOrdersFromFile("orders.json");
        if (orderList != null) {
            return orderList.get(orderList.size() - 1).getId() + 1;
        } else return 0;
    }

}
