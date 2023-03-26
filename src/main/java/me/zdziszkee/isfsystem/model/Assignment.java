package me.zdziszkee.isfsystem.model;

import java.time.LocalTime;

public record Assignment(Order order, LocalTime startTime) {
    public Order order() {
        return order;
    }
}
