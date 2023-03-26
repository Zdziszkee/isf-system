package me.zdziszkee.isfsystem.loaders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.zdziszkee.isfsystem.model.Order;
import me.zdziszkee.isfsystem.serializers.OrderMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DefaultOrdersLoader implements Loader<List<Order>> {
    private final ObjectMapper objectMapper = new OrderMapper();

    @Override
    public List<Order> load(InputStream source) {
        try {
            TypeReference<List<Order>> type = new TypeReference<>() {
            };
            return objectMapper.readValue(source, type);
        }
        catch (IOException exception) {
            throw new RuntimeException("Error occurred while loading the orders.", exception);
        }
    }
}
