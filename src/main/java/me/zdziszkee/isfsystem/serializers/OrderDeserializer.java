package me.zdziszkee.isfsystem.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import me.zdziszkee.isfsystem.model.Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class OrderDeserializer extends JsonDeserializer<Order> {
    @Override
    public Order deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        ObjectCodec codec = parser.getCodec();
        TreeNode node = codec.readTree(parser);
        String orderId = node.get("orderId").toString();
        BigDecimal orderValue = ((TextNode) node.get("orderValue")).decimalValue();
        Duration pickingTime = context.readTreeAsValue((JsonNode) node.get("pickingTime"), Duration.class);
        LocalTime completeBy = context.readTreeAsValue((JsonNode) node.get("completeBy"), LocalTime.class);
        LocalTime lastMatchingTime = completeBy.minus(pickingTime);
        return new Order(orderId,orderValue,pickingTime,completeBy,lastMatchingTime);
    }
}
