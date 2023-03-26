package me.zdziszkee.isfsystem.store;

import me.zdziszkee.isfsystem.model.Assignment;
import me.zdziszkee.isfsystem.model.Order;
import me.zdziszkee.isfsystem.model.StoreConfiguration;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Store {
    private final StoreConfiguration storeConfiguration;
    private final Set<StoreWorker> workers;
    private final PriorityQueue<Order> orderPool;

    public Store(final Comparator<Order> storeComparator,
                 final StoreConfiguration storeConfiguration, Collection<Order> orders) {
        this.storeConfiguration = storeConfiguration;
        this.workers = new HashSet<>();
        this.orderPool = new PriorityQueue<>(storeComparator);
        orderPool.addAll(orders);
        storeConfiguration.pickers().forEach(picker -> workers.add(new StoreWorker(picker, storeConfiguration.pickingStartTime(), storeConfiguration.pickingEndTime())));
    }

    public void createAssignment() {
        Order order = orderPool.peek();
        while (order != null) {
            Optional<StoreWorker> optionalWorker = getWorkerForOrder(order);
            if (optionalWorker.isPresent()) {
                StoreWorker storeWorker = optionalWorker.get();
                storeWorker.add(new Assignment(order, storeWorker.getWorkerAvailability()));
                orderPool.poll();
            } else {
                break;
            }
            order = orderPool.peek();
        }
        workers.forEach(StoreWorker::printAssignments);
    }

    public Optional<StoreWorker> getWorkerForOrder(final Order order) {

        Duration pickingTime = order.pickingTime();
        LocalTime localTime = order.completeBy();
        LocalTime bestStartTime = localTime.minus(pickingTime);

        return workers.stream()
                      .filter(storeWorker -> ChronoUnit.MINUTES.between(storeWorker.getWorkerAvailability()
                                                                                   .plus(pickingTime), storeConfiguration.pickingEndTime()) >= 0)
                      .min((first, second) -> {
                          long betweenFirst =
                                  ChronoUnit.MINUTES.between(first.getWorkerAvailability(),
                                          bestStartTime);
                          long betweenSecond =
                                  ChronoUnit.MINUTES.between(second.getWorkerAvailability(),
                                          bestStartTime);
                          return Long.compare(betweenSecond, betweenFirst);
                      });
    }


}
