package com.no1.geniestore.controllers;

import static com.no1.geniestore.accounts.Account.accountIdCounter;
import static com.no1.geniestore.products.Order.orderIdCounter;
import static com.no1.geniestore.products.Stock.itemIdCounter;
import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class AmountParser {
    
    /**
     * Reads {@code itemIdCounter} from the amount text file.
     *
     * @param filePath the path to amount file; cannot be null.
     */
    public static Optional<Integer> readItemIdCounter(String filePath) {
        requireNonNull(filePath);
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            return Optional.of(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
    
    /**
     * Reads the {@code accountIdCounter} from the amount text file.
     *
     * @param filePath the path to the amount file; cannot be null.
     */
    public static Optional<Integer> readAccountIdCounter(String filePath) {
        requireNonNull(filePath);
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            return Optional.of(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
    
    /**
     * Reads the {@code orderIdCounter} from the amount text file.
     *
     * @param filePath the path to the amount file; cannot be null.
     */
    public static Optional<Integer> readOrderIdCounter(String filePath) {
        requireNonNull(filePath);
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            reader.readLine();
            return Optional.of(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
    
    /**
     * Writes all required data into the amount text file.
     *
     * @param filePath the path to the amount file; cannot be null.
     * @throws IOException if the file is not found.
     */
    public static void writeAmountData(String filePath) throws IOException {
        requireNonNull(filePath);
        
        PrintWriter writer = new PrintWriter(new FileWriter(filePath));
        writer.printf("%d\n%d\n%d", itemIdCounter, accountIdCounter, orderIdCounter);
        writer.close();
    }
}
