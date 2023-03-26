package me.zdziszkee.isfsystem.model;

import java.time.LocalTime;
import java.util.List;

public record StoreConfiguration(List<String> pickers, LocalTime pickingStartTime,
                                 LocalTime pickingEndTime) {
}
