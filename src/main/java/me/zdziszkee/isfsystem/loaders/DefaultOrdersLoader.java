package me.zdziszkee.isfsystem.loaders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.zdziszkee.isfsystem.model.Order;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DefaultOrderLoader implements Loader<List<Order>> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Order> load(InputStream source) {
        try {
            TypeReference<List<Order>> type = new TypeReference<>() {
            };
            final List<Order> orders = objectMapper.readValue(source, type);
            orders.forEach(order -> System.out.println(order.toString()));
            return orders;
        }
        catch (IOException exception) {
            throw new RuntimeException("Error occurred while loading the orders.", exception);
        }
    }
}
