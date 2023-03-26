package me.zdziszkee.isfsystem;

import me.zdziszkee.isfsystem.loaders.DefaultConfigLoader;
import me.zdziszkee.isfsystem.loaders.DefaultOrdersLoader;
import me.zdziszkee.isfsystem.model.Order;
import me.zdziszkee.isfsystem.model.StoreConfiguration;
import me.zdziszkee.isfsystem.store.Store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ISFSystem {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("Wrong amount of program arguments!");
            System.out.println("Expected arguments: ");
            System.out.println("1: path to store configuration json file.");
            System.out.println("2: path to store orders json file.");
            return;
        }

        final String storeConfigurationPath = args[0];
        final String storeOrdersPath = args[1];

        DefaultConfigLoader configLoader = new DefaultConfigLoader();
        DefaultOrdersLoader orderLoader = new DefaultOrdersLoader();

        StoreConfiguration config = configLoader.load(Files.newInputStream(Path.of(storeConfigurationPath)));
        List<Order> orders = orderLoader.load(Files.newInputStream(Path.of(storeOrdersPath)));

        Comparator<Order> task1Comparator = Comparator.comparing(Order::lastMatchingTime);


        Store store = new Store(task1Comparator, config, orders);
        store.createAssignment();
    }
}