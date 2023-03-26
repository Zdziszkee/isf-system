package me.zdziszkee.isfsystem.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public record Order(String orderId, BigDecimal orderValue, Duration pickingTime,
                    LocalTime completeBy, LocalTime lastMatchingTime) {
}
