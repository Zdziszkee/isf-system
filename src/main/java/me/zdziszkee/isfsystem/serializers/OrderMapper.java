package me.zdziszkee.isfsystem.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.zdziszkee.isfsystem.model.Order;

public class OrderMapper extends ObjectMapper {
    public OrderMapper() {
        registerModule(new JavaTimeModule());
        registerModule(new SimpleModule().addDeserializer(Order.class, new OrderDeserializer()));
    }
}
