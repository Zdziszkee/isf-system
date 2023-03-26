package me.zdziszkee.isfsystem.store;

import me.zdziszkee.isfsystem.model.Order;
import me.zdziszkee.isfsystem.model.Picker;

import java.time.Duration;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Worker {
    private final Picker picker;
    private final PriorityQueue<Order> orders = new PriorityQueue<>(Comparator.comparing(Order::completeBy));

    public Worker(final Picker picker) {
        this.picker = picker;
    }

    public Duration getWorkerJobDuration() {
        return orders.stream()
                     .map(Order::pickingTime)
                     .reduce(Duration::plus)
                     .orElse(Duration.ofHours(0));
    }

    public void addJob(Order order) {
        orders.add(order);
    }
}
