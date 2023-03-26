package me.zdziszkee.isfsystem.store;

import me.zdziszkee.isfsystem.model.Assignment;

import java.time.LocalTime;
import java.util.*;

public class StoreWorker {
    private final String picker;
    private final LocalTime pickingStartTime;
    private final LocalTime pickingEndTime;
    private final TreeSet<Assignment> assignments =
            new TreeSet<>(Comparator.comparing(Assignment::startTime));

    public StoreWorker(final String picker, LocalTime pickingStartTime, LocalTime pickingEndTime) {
        this.picker = picker;
        this.pickingStartTime = pickingStartTime;
        this.pickingEndTime = pickingEndTime;
    }

    public LocalTime getWorkerAvailability() {
        if (assignments.isEmpty()) {
            return pickingStartTime;
        }
        Assignment assignment = assignments.last();
        return assignment.startTime()
                         .plus(assignment.order()
                                         .pickingTime());
    }

    public void add(Assignment assignment) {
        assignments.add(assignment);
    }

    public void printAssignments() {
        for (Assignment assignment : assignments) {
            System.out.printf(picker + " ");
            System.out.printf(assignment.order()
                                        .orderId() + " ");
            System.out.printf(assignment.startTime() + " \n");
        }
    }

    public String getId() {
        return picker;
    }
}
