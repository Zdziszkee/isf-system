package me.zdziszkee.isfsystem;

import me.zdziszkee.isfsystem.model.Assignment;
import me.zdziszkee.isfsystem.model.Order;
import me.zdziszkee.isfsystem.store.StoreWorker;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreWorkerTest {


    @Test
    public void testGetWorkerAvailabilityWhenNoAssignments() {
        StoreWorker worker = new StoreWorker("Alice", LocalTime.of(9, 0), LocalTime.of(9, 0));

        LocalTime availability = worker.getWorkerAvailability();

        assertEquals(LocalTime.of(9, 0), availability);
    }

    @Test
    public void testGetWorkerAvailabilityWhenHasAssignments() {
        StoreWorker worker = new StoreWorker("Bob", LocalTime.of(10, 0), LocalTime.of(9, 0));
        Assignment assignment1 = new Assignment(new Order("order-1", new BigDecimal(2), Duration.ofHours(1), LocalTime.of(11, 0), LocalTime.of(10, 0)), LocalTime.of(10, 0));
        Assignment assignment2 = new Assignment(new Order("order-2", new BigDecimal(2), Duration.ofHours(1), LocalTime.of(12, 0), LocalTime.of(10, 30)), LocalTime.of(11,
                0));


        worker.add(assignment1);
        worker.add(assignment2);

        LocalTime availability = worker.getWorkerAvailability();

        assertEquals(LocalTime.of(12, 0), availability);
    }
}
