package me.zdziszkee.isfsystem;

import me.zdziszkee.isfsystem.model.Order;
import me.zdziszkee.isfsystem.model.StoreConfiguration;
import me.zdziszkee.isfsystem.store.Store;
import me.zdziszkee.isfsystem.store.StoreWorker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreTest {
    private Store store;
    private Set<StoreWorker> workers;
    private PriorityQueue<Order> orderPool;
    private StoreConfiguration storeConfiguration;
    Order order1;
    Order order2;
    Order order3;

    @BeforeEach
    void setUp() {
        storeConfiguration = new StoreConfiguration(List.of("p1"), LocalTime.of(9, 0),
                LocalTime.of(17, 0));
        List<Order> orders = new ArrayList<>();
        order1 = new Order("order-1", new BigDecimal(2), Duration.ofHours(1), LocalTime.of(11, 0)
                , LocalTime.of(10, 0));
        order2 = new Order("order-2", new BigDecimal(2), Duration.ofHours(1), LocalTime.of(11, 0)
                , LocalTime.of(10, 0));
        order3 = new Order("order-3", new BigDecimal(2), Duration.ofHours(1), LocalTime.of(11, 0)
                , LocalTime.of(10, 0));
        orders.add(order3);
        orders.add(order1);
        orders.add(order2);
        Comparator<Order> storeComparator = Comparator.comparing(Order::completeBy);
        store = new Store(storeComparator, storeConfiguration, orders);
        workers = new HashSet<>();
        workers.add(new StoreWorker("picker1", LocalTime.of(9, 0), LocalTime.of(17, 0)));
        workers.add(new StoreWorker("picker2", LocalTime.of(9, 0), LocalTime.of(17, 0)));
        orderPool = new PriorityQueue<>(storeComparator);
    }


    @Test
    void testGetWorkerForOrder() {
        Optional<StoreWorker> optionalWorker = store.getWorkerForOrder(order1);
        assertTrue(optionalWorker.isPresent());
        StoreWorker storeWorker = optionalWorker.get();
        assertEquals("p1", storeWorker.getId());
    }
}
