package me.zdziszkee.isfsystem.workers;

import me.zdziszkee.isfsystem.model.Order;
import me.zdziszkee.isfsystem.model.Picker;
import me.zdziszkee.isfsystem.model.StoreConfiguration;

import java.math.BigDecimal;
import java.util.*;

public class Store {
    private final StoreConfiguration storeConfiguration;
    private final Set<Worker> workers = new HashSet<>();
    private final PriorityQueue<Order> orderPool;

    public Store(Set<Picker> pickers, final Comparator<Worker> workerComparator,
                 final Comparator<Order> orderPoolComparator,
                 StoreConfiguration storeConfiguration) {
        this.storeConfiguration = storeConfiguration;
        pickers.forEach(picker -> workers.add(new Worker(picker)));
        Comparator<Order> comparator = (first, second) -> {
            BigDecimal firstOrderValue = first.orderValue();
            BigDecimal secondOrderValue = second.orderValue();
            return firstOrderValue.compareTo(secondOrderValue);
        };
        comparator.thenComparing(Order::pickingTime);
        comparator.thenComparing(Order::completeBy);
        this.orderPool = new PriorityQueue<>(comparator);
    }

    private Optional<Worker> getBestWorker() {
        Optional<Worker> optionalWorker = workers.stream()
                                                 .min(Comparator.comparing(Worker::getWorkerJobDuration));

    }


}
