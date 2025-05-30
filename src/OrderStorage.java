package src.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.models.Order;
import src.util.OrderStorage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderStorage {
    private static final String FILE_PATH = "data/orders.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Order> loadOrders() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(file, new TypeReference<List<Order>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveOrder(Order order) {
        List<Order> orders = loadOrders();
        orders.add(order);
        try {
            mapper.writeValue(new File(FILE_PATH), orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
