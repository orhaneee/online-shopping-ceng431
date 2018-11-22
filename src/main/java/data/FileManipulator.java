package data;

import com.google.gson.*;
import domain.Customer;
import domain.Order;
import domain.State;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class FileManipulator {
    private static FileManipulator ourInstance = new FileManipulator();


    public static FileManipulator getInstance() {
        return ourInstance;
    }

    private FileManipulator() {
    }

    private void writeToFile(String content, String filename) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(filename));
            os.write(content.getBytes(), 0, content.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert os != null;
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void writeCustomerToFile(Customer customer, String filename) {
        List<Customer> customerList = new ArrayList<>();
        if (checkFile(filename)) {
            customerList = readCustomersFromFile(filename);
        }
        customerList.add(customer);

        if (customer != null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new GsonDateDeserializer())
                    .create();
            FilePOJO serializer = new FilePOJO();
            serializer.setCustomerList(customerList);
            writeToFile(gson.toJson(serializer, FilePOJO.class), filename);
        }
    }

    public void writeCustomerListToFile(List<Customer> customerList) {
        if (customerList != null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new GsonDateDeserializer())
                    .create();
            FilePOJO serializer = new FilePOJO();
            serializer.setCustomerList(customerList);
            String filename = "customers.json";
            writeToFile(gson.toJson(serializer, FilePOJO.class), filename);
        }
    }

    public List<Customer> readCustomersFromFile(String filename) {
        if (checkFile(filename)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new GsonDateDeserializer())
                    .create();
            FilePOJO serializer = gson.fromJson(readFile(filename),
                    FilePOJO.class);
            return serializer.getCustomerList();
        } else return null;
    }

    public void writeOrderToFile(Order order, String filename) {
        List<Order> orderList = new ArrayList<>();
        if (checkFile(filename)) {
            orderList = readOrdersFromFile(filename);
        }
        orderList.add(order);

        if (order != null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(State.class, new StateInstanceCreator())
                    .registerTypeAdapter(Date.class, new GsonDateDeserializer())
                    .create();
            FilePOJO serializer = new FilePOJO();
            serializer.setOrderList(orderList);
            writeToFile(gson.toJson(serializer, FilePOJO.class), filename);
        }
    }

    public List<Order> readOrdersFromFile(String filename) {
        if (checkFile(filename)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(State.class, new StateInstanceCreator())
                    .registerTypeAdapter(Date.class, new GsonDateDeserializer())
                    .create();
            FilePOJO serializer = gson.fromJson(readFile(filename),
                    FilePOJO.class);
            return serializer.getOrderList();
        } else return null;
    }

    private String readFile(String filename) {
        if (filename != null) {
            Scanner in = null;
            try {
                in = new Scanner(new FileReader(filename));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            while (in.hasNext()) {
                sb.append(in.next());
            }
            in.close();
            return sb.toString();
        } else {
            return null;
        }
    }

    private boolean checkFile(String filename) {
        File folder = new File("./");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (filename.equals(file.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

}
